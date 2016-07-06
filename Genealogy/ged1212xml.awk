#! /usr/bin/env awk -f 
###############################################################################
## GEDCOM one-to-one to XML 
## USAGE: [g|m|n]awk [-v var=value [-v ...]] -f ged1212xml.awk [<]infile.GED [>outfile.XML]
## NOTES: v-Options are required to be set before f-Options
##        NCName represents XML "non-colonized" Names (no-colon constraint)
## OPTIONS:
##		-v ANSEL=0|1
##			start "ANSEL to Entity"-mode before 1st occurence of +n CHAR ANSEL
##		-v nsPFX=""|<ncname>
##			xml namespace prefix, requires setting of nsURI too, default=none
##		-v nsURI=""|<uri>
##			xml namespace URI for xmlns[:nsPFX]="...", default=none
##		-v xmlEnc="iso-8859-1"|<encoding>
##			replace xml declaration's default <?xml ... encoding="iso-8859-1" ?>
##		-v xmlStyle=""|<file.css|file.xsl>
##			insert processing-instruction <?xml-stylesheet ... href="..."?>, default=none
##		-v xmlRoot="GED"|<ncname>
##			replace root-element's default tag-name "GED"
##		-v xmlID="ID"|"xml:id"|<ncname>
##			replace attribute-name's default "ID" for GEDCOM's @<XREF>@s
##		-v xmlIDREF="REF"|<ncname>
##			replace attribute-name's default "REF" for GEDCOM's @<XREF>@s
##		-v xmlDTD=""|<file.dtd>
##			insert doctype-definition <!DOCTYPE ... SYSTEM "...">, default=none
##		-v xsiXSD=""|<file.xsd>
##			insert root's xsi:XMLSchema-instance-location-definition, default=none
##		-v idPFX=""|"id."|"ged-"|<ncname>
##			ID-prefix for valid xmlID/REF-values (NCNames), default=none
##			ID-prefix == string-additive, don't confuse it with namespace-prefixes!
##		-v escDATE=""|"ESC"|<ncname>
##			given name ("ESC" preferred, default=none=noop) 
##          moves @#<DATE_CALENDAR_ESCAPE>@s into attributes
##		-v surNAME="SURN"|"S"|<ncname>|<!ncname>
##			alter node-name ("S" preferred, default="SURN") for slashed surname-part
##			to avoid double SURN-subnodes in an extended NAME-node/structure
##			a non-ncname char/string prevents slash-replacement at all
##		-v piSTY=""|"attr"|"func"|"void"|"nopi"
##          predefined attribute- or function-style for processing-instructions
##			default="void" ~ empty for user-defined styles, otherwise plain style
##			a non-defined value (like "nopi") prevents PI-generation at all
##		-v piNCN=""|<ncname>
##          PI-ncname for processing-instruction-targetnames, default=none
##		-v datePI="DATE"|<ncname>|<!ncname>
##			processing-instruction-targetname, default="DATE" becomes <?DATE ...?>
##          date-format converted (if possible) to YYYY-MM-DD according to ISO 8601
##			a non-ncname char/string prevents DATE PI-generation
##		-v uuidPI="_UID"|"GUID"|"UUID"|"XUID"|"UURN"|"XURN"|<ncname>|<!ncname>
##			processing-instruction-targetname, default="UUID" becomes <?UUID ...?>
##          Universally Unique IDentifiers v4 (pseudo-random) according to RFC 4122
##          a standard-name is default-format for uuidSTY-option
##			a non-ncname char/string prevents UUID PI-generation
##		-v _uidPI="_UID"|"GUID"|"UUID"|"XUID"|"UURN"|"XURN"|<ncname>|<!ncname>
##			processing-instruction-targetname, default="_UID" becomes <?_UID_n ...?>
##          checks _UID-tag, default according to PAF-style UUID+Checksum (n=0|1|X)
##          a standard-name is default-format for _uidSTY-option
##			a non-ncname char/string prevents _UID PI-generation
##		-v uuidSTY=<uuidPI-standard-targetname-format>|"UUID"|<targetformat>
##		-v _uidSTY=<_uidPI-standard-targetname-format>|"_UID"|<targetformat>
##          default-format-1 : targetname of PI if predef'd standard-format
##          default-format-2 : formatname "[_U]UID" if PI-name is non-standard
##          default-format-3 : else-clause-format if user-def'd is non-standard
##          "_UID" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXCCCC  (_uidSTY-default)
##                 PAF-GEDCOM-_UID 16+2 bytes, 36 chars uppercase hexdigit with checksum
##          "UUID" xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx  (uuidSTY-default)
##                 RFC-4122-UUIDv4 16 bytes, 32+4 chars lowercase hexdigit hyphen-grouped
##          "GUID" {XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX}
##                 embraced {UUIDv4} 16 bytes, 32+6 chars uppercase hexdigit hyphen-grouped
##          "XUID" {XXXxXXxx-XXxX-XxXx-Xxxx-xxXXxXXxXXxx}cccc
##                 extended mixedcase and -style {GUIDv4}, 4-hexdigit checksum appended
##          "UURN" urn:uuid:xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
##                 prefixed lowercase "urn:uuid:UUIDv4" (RFC-4122, UUID as URN)
##          "XURN" urn:uuid:xXXxxXXx-XXxx-XXxx-XXXX-xXxxXXxXxxxX+cccc
##                 extended mixedcase "urn:uuid:UUIDv4+checksum" (RFCs 2141+3986+4122)
##           else: XXXXxxxx-XXxX-XxXX-XXXX-XXXxXxXxxxXx cccc
##                 combined mixedcase UUIDv4 with 4-hexdigit checksum (set apart)
##		-v uuidSEED=<integer>
##          default=srand()
###############################################################################
#~
#~ Copyright (c) 2008 ff. Stefan Unterstein <http://www.unterstein.net/ged1212xml>
#~ 
#~ By operation of rights, permission is hereby granted to copy, distribute and/or 
#~ modify this program under the terms of the GNU General Public License Version 3 
#~ or any later version published by the Free Software Foundation. See the current 
#~ License at <http://www.gnu.org/licenses/gpl.html> for more details.
#~
#~ Such free(d) "copylefted" software is distributed 
#~ WITHOUT ANY WARRANTY OF OR ABOUT ANYTHING but the "copyleft" itself.
#~
###############################################################################
#~ And now for something completely different:
#~ The Decline of The Civilized Code by The Rise of Barbarizing Exceptions ...
###############################################################################

BEGIN {
	
	REncname	  = "^[a-z_A-Z][-a-z_A-Z.0-9]*$"; # <http://www.w3.org/TR/xml-names/#NT-NCName>
	REncnORnone	  = "^$|" REncname;
	
	ANSEL		  = ANSEL ? ANSEL : 0 ;
	nsPFX	  	  = nsPFX ? nsPFX : "" ; if (nsPFX!~REncnORnone) exit;
	nsURI	  	  = nsURI ? nsURI : "" ; if (nsPFX && !nsURI) exit;
	idPFX		  = idPFX ? idPFX : "" ; if (idPFX!~REncnORnone) exit;
	
	xmlEnc		  = xmlEnc   ? xmlEnc   : "iso-8859-1" ;
	xmlStyle	  = match(tolower(xmlStyle), /(css|xsl)$/) ? "\n<?xml-stylesheet type=\"text/" tolower(substr(xmlStyle,RSTART)) "\" href=\"" xmlStyle "\"?>" : "" ;
	xmlRoot		  = xmlRoot  ? xmlRoot  : "GED" ;
	xmlID		  = xmlID    ? xmlID    : "ID"  ;
	xmlIDREF	  = xmlIDREF ? xmlIDREF : "REF" ;
	xmlnsATTR	  = nsURI ? nsPFX ? " xmlns:"nsPFX"=\""nsURI"\"" : " xmlns=\""nsURI"\"" : "" ;
	xmlnsPFX	  = nsPFX ? nsPFX":" : "" ;
	xmlDTD		  = xmlDTD ? "\n<!DOCTYPE " xmlnsPFX xmlRoot " SYSTEM \"" xmlDTD "\">" : "" ;
	xsiXSD		  = xsiXSD ? " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" (nsURI ? " xsi:schemaLocation=\"" nsURI " " : " xsi:noNamespaceSchemaLocation=\"") xsiXSD "\"" : "" ;
	surNAME		  = surNAME!="" ? (surNAME~REncname) ? surNAME : "" : "SURN" ; 
	escDATE		  = escDATE ? escDATE : "" ;
	
	#
	# PI variables cf functions: xmlPI(); gedPIDATE(); gedPIUUID(); gedPI_UID();
	#
	
	piNCN		  = piNCN ? piNCN : "" ; if (piNCN!~REncnORnone) exit;
	piSTY		  = (piSTY~/^(attr|func|void)$/) ? piSTY : (piSTY~/^$/) ? "void" : "" ;

	piSET["attr","ncn"] = piNCN ? piNCN : xmlRoot ;
	piSET["attr","pfx"] = piPFX ? piPFX : "" ;
	piSET["attr","ifx"] = piIFX ? piIFX : "=\"" ;
	piSET["attr","sfx"] = piSFX ? piSFX : "\"" ;
	
	piSET["func","ncn"] = piNCN ? piNCN : xmlRoot ;
	piSET["func","pfx"] = piPFX ? piPFX : "" ;
	piSET["func","ifx"] = piIFX ? piIFX : "(\"" ;
	piSET["func","sfx"] = piSFX ? piSFX : "\");" ;
	
	piSET["void","ncn"] = piNCN ? piNCN : "" ;
	piSET["void","pfx"] = piPFX ? piPFX : "" ;
	piSET["void","ifx"] = piIFX ? piIFX : "" ;
	piSET["void","sfx"] = piSFX ? piSFX : "" ;
	
	datePI		  = datePI!="" ? (datePI~REncname) ? datePI : "" : "DATE" ; 
	uuidPI		  = uuidPI!="" ? (uuidPI~REncname) ? uuidPI : "" : "UUID" ; 
if (uuidPI) print "UUIDv4-Random-Seed: " ((uuidSEED~/^[0-9]+$/) ? uuidSEED=srand(int(uuidSEED)) : uuidSEED=srand()) > "/dev/stderr" ;
	_uidPI		  = _uidPI!="" ? (_uidPI~REncname) ? _uidPI : "" : "_UID" ; 
	_uidSTY		  = _uidSTY ? _uidSTY : ((_uidPI~/^([_GUX]UID|[UX]URN)$/) ? _uidPI : "_UID") ;
	uuidSTY		  = uuidSTY ? uuidSTY : ((uuidPI~/^([_GUX]UID|[UX]URN)$/) ? uuidPI : "UUID") ;
	
	#
	# UUIDv4 = xxxxxxxx-xxxx-4xxx-Yxxx-xxxxxxxxxxxx
	#
	# workaround w/o bit-ops for y
	# (y = x & 0x3 | 0x8) == (Pos19 = Hex AND 0x3 OR 0x8) 
	# to set Msb7=1 Msb6=0 of "clock_seq_hi_and_reserved"
	#
	# gawk: y = or(and(x,3),8)
	# gawk: y = or(and(x,strtonum("0x3")),strtonum("0x8"))
	#
	# or restrict rand() to return values 8-11 (0x8-0xb) = xchar pos.13-20
	#
	
	mkXB2N(xbyte); split("01234567cdef89ab89AB01234567CDEF",xchar,"");	
	
	#
	# xbyte for array of HexDigit-Byte-(zero-filled)-Indices-to-Number
	# xbyte["00"]=0 xbyte["01"]=1 .. "ff"="Ff"="fF"="FF"=255
	#
	
	#
	# xchar for UUIDv4 = xxxxxxxx-xxxx-4xxx-Yxxx-xxxxxxxxxxxx
	#
	# usage lower case:
	#	x = xchar[int(rand()*16+1)]
	#	y = xchar[int(rand()*4+13)]
	#
	# usage mixed case:
	#	x = xchar[int(rand()*32+1)]
	#	y = xchar[int(rand()*8+13)]
	#
	# usage upper case:
	#	x = xchar[int(rand()*16+17)]
	#	y = xchar[int(rand()*4+17)]
	#

	REws		  = "[ \t]";			# "[[:blank:]]" or "[[:space:]]"
	REnonws		  = "[^ \t]";			# "[^[:blank:]]" or "[^[:space:]]"
	REindent	  = "^" REws "*";
	REgedId		  = "@[a-zA-Z_0-9]" REnonws "*@";
	REgedLevel	  = "[0-9][0-9]?";		# "[[:digit:][:digit:]?]"
	REgedToken	  = "[a-zA-Z_0-9]+";	# "[[:alnum:]_]+"
	REgedDelim	  = REws "+";			# "[[:blank:]]+"
	REgedDATExct  = "^[0-3]?[0-9]" REgedDelim "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)" REgedDelim "[0-9]?[0-9]?[0-9]?[0-9]$";
	REgedDATEesc  = "@#D(GREGORIAN|JULIAN|HEBREW|FRENCH R|ROMAN|UNKNOWN)@";	
	#~ REescDATE	  = "@#D[A-Z]+[ R]?@";	
	#~ cf http://homepages.rootsweb.ancestry.com/~pmcbride/gedcom/55gcch2.htm#DATE_CALENDAR_ESCAPE	
	
	mm["JAN"]=1; mm["FEB"]=2; mm["MAR"]=3; mm["APR"]=4; mm["MAY"]=5; mm["JUN"]=6; mm["JUL"]=7; mm["AUG"]=8; mm["SEP"]=9; mm["OCT"]=10; mm["NOV"]=11; mm["DEC"]=12; 

	Hx01RE = "[0-9a-fA-F]";
	Hx02RE = Hx01RE Hx01RE; # octet/byte
	Hx04RE = Hx02RE "-?" Hx02RE;
	Hx08RE = Hx04RE "-?" Hx04RE;
	Hx12RE = Hx04RE "-?" Hx04RE "-?" Hx04RE;
	chksRE = "([- +]?" Hx04RE ")?"
	xuidRE = "{?" Hx08RE "-?" Hx04RE "-?" Hx04RE "-?" Hx04RE "-?" Hx12RE "}?" chksRE;
	xurnRE = "([uU][rR][nN]:[uU][uU][iI][dD]:)?" xuidRE;
	
	# 
	#  captures GUIDs, UUIDs, _UIDs, URNs prefix, with or w/o plus|minus|space checksum, any lettercase, any hyphen-byte-grouping
	#> marks output- or replacement-formats, four of them canonical or quasi-standards
	# 
	#   xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	#>  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXCCCC
	#  {xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx}
	#  {xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx}cccc
	#>  xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
	#   xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxxcccc
	#> {XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX}
	#> {XxXXXxXX-xxxX-XXXx-XxxX-XxXXxxxXXXxX}cccc
	#> urn:uuid:xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
	#> urn:uuid:XXxXXXXx-XXxX-xXxx-Xxxx-xxxxXxxXXxxX+cccc
	#
	#  ... any hyphen-byte-grouping from none to all (grouping half-byte "nibbles" doesn't make any sense to me)
	#
	#   xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx
	#   xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx cc-cc
	#  {xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx-xx}±cc-cc
	#

    gedId      	  = "";
    gedIdRef   	  = "";
    gedLevel      = -1;
    gedToken      = "";
    gedValue   	  = "";
	gedPI		  = "";
    gedPrevId     = "";
    gedPrevIdRef  = "";
    gedPrevLevel  = -1;
    gedPrevToken  = "";
    gedPrevValue  = "";
	gedPrevPI	  = "";
	
	print  "<?xml version=\"1.0\" encoding=\""xmlEnc"\"?>"xmlStyle;
	print  "<!-- ================================================= -->";
	print  "<!-- GEDCOM one-to-one to XML by ged1212xml.awk (StUs) -->";
	print  "<!-- script source at http://unterstein.net/ged1212xml -->";
	print  "<!-- ================================================= -->"xmlDTD;
	printf("<%s%s%s%s", xmlnsPFX, xmlRoot, xmlnsATTR, xsiXSD);

}

###############################################################################

$1 ~ REgedLevel && $2 ~ REgedId && $3 ~ REgedToken { # Level Id Token [Value(s)]
	
	# save this line

	#~ only (?) NOTE-Token may have inline Id AND Value(s) i.e. SUBMITTER_TEXT ???  Sigh!
	#~ cf http://homepages.rootsweb.ancestry.com/~pmcbride/gedcom/55gcch2.htm#NOTE_RECORD
	#~
	#~ NOTE_RECORD: =
	  #~ n  @<XREF:NOTE>@ NOTE <SUBMITTER_TEXT>  {1:1}
	#~
	if ($4) LINEentify();

    gedId     =  idPFX substr($2, 2, length($2)-2);
    gedIdRef  =  "";
    gedLevel  =  $1; 
    gedToken  =  $3;
    gedValue  =  $4 ? gedAfter($1 REgedDelim $2 REgedDelim $3 REgedDelim) : "" ;
	gedPI	  =  ($1==0 && piSTY && uuidPI) ? gedPIUUID() : "" ;

	# todo prev line

	xmlClosings();

	# todo this line
	
	if (!gedLevel && !gedValue) printf("\n<%s%s %s=\"%s\"", xmlnsPFX, gedToken, xmlID, gedId);
	else printf(((gedLevel?"%"gedLevel*2:"\n%")"s<%s%s %s=\"%s\""(gedValue?">%s":"%s")), "", xmlnsPFX, gedToken, xmlID, gedId, gedValue);
	
	# info next line
	
	gedBackings(); next;
	
}	# done

###############################################################################

escDATE && $1 ~ REgedLevel && $2 == "DATE" && $0 ~ REgedDATEesc { # Level DATE Escape Value(s)

	# hack this line 

	gsub(/@#DFRENCH R@/,"@#DFRENCH_R@"); # otherwise fails with @#DFRENCH R@'s annoying whitespace!

	# save this line

	if ($4) LINEentify();

    gedId     =  "";
    gedIdRef  =  "";
    gedAtEsc  =  substr($3, 3, length($3)-3); sub("_"," ",gedAtEsc);
    gedLevel  =  $1; 
    gedToken  =  $2;
    gedValue  =  $4 ? gedAfter($1 REgedDelim $2 REgedDelim $3 REgedDelim) : "" ;
	gedPI	  =  (piSTY && datePI) ? gedPIDATE(toupper(gedValue)) : "" ;

	# todo prev line

	xmlClosings();

	# todo this line
	
	printf(("%"(gedLevel*2)"s<%s%s %s=\"%s\""(gedValue?">%s":"%s")), "", xmlnsPFX, gedToken, escDATE, gedAtEsc, gedValue);
	
	# info next line
	
	gedBackings(); next;
	
}	# done

###############################################################################

$1 ~ REgedLevel && $2 ~ REgedToken && $3 ~ REgedId { # Level Token IdRef
	
	# save this line

    gedId     =  "";
    gedIdRef  =  idPFX substr($3, 2, length($3)-2);
    gedLevel  =  $1; 
    gedToken  =  $2;
    gedValue  =  "";
	gedPI	  =  "";

	# todo prev line

	xmlClosings();

	# todo this line
	
	printf(("%"(gedLevel*2)"s<%s%s %s=\"%s\""), "", xmlnsPFX, gedToken, xmlIDREF, gedIdRef);
	
	# info next line
	
	gedBackings(); next;
	
}	# done

###############################################################################

NF>1 && $1 ~ REgedLevel && ($2 $3) !~ REgedId { # Level Token [Value(s)]
	
	# save this line

	if ($3)  LINEentify();
	if ($2=="CHAR") { ANSEL = (toupper($3)=="ANSEL") ? 1 : 0 ; }
	if ($2=="NAME" && surNAME) {
		if (sub(/\/[^\/]+\//,"<"xmlnsPFX surNAME">&</"xmlnsPFX surNAME">")) { 
			sub("/",""); 
			sub("/",""); 
		} else sub("//","<"xmlnsPFX surNAME"/>");
	}
	
    gedId     =  "";
    gedIdRef  =  "";
    gedLevel  =  $1; 
    gedToken  =  $2;
    gedValue  =  $3 ? gedAfter($1 REgedDelim $2 REgedDelim) : "" ;
	gedPI	  = ($2=="DATE" && piSTY && datePI) ? gedPIDATE(toupper(gedValue)) : "" ;
	gedPI	  = ($2=="_UID" && piSTY && _uidPI) ? gedPI_UID(gedValue)          : gedPI ;

	# todo prev line
	
	xmlClosings();
	
	# todo this line
	
	printf(((gedLevel?"%"gedLevel*2:"\n%")"s<%s%s"(gedValue?">%s":"%s")), "", xmlnsPFX, gedToken, gedValue);
	
	# info next line
	
	gedBackings(); next;
	
}	# done

###############################################################################
# fallback
###############################################################################

NF>0 { # capture and report all non-empty lines not handled by previous patterns

	# fake this line, dupe next line = hacking the backing

	if (gedLevel<0) {}  # do nothing b4 1st gedcom-line
	else { 				# insert skipped line as xml-comment
		gedPI = sprintf("\n%"(gedPrevLevel*2)"s<!-- skipped source line %s : %s -->", "", FNR, ((NF) ? $0 : "<empty>")); 
		gedPrevPI = gedPrevPI gedPI;
		tagStack[gedPrevLevel] = tagStack[gedPrevLevel] gedPI;
	}

	# todo this line

	print " skipped source line " FNR " : " ((NF) ? $0 : "<empty>") > "/dev/stderr"; 
		
} 	# done

###############################################################################

END {
	
	if (nsPFX && !nsURI) { print "Error: xmlNamespace -v nsPFX=prefix requires -v nsURI=identifier" > "/dev/stderr"; exit 1; }
	if (nsPFX!~REncnORnone || idPFX!~REncnORnone || piNCN!~REncnORnone) { print "Error: (nsPFX|idPFX|piNCN) require a valid NCName" > "/dev/stderr"; exit 1; }
	
	gedLevel = 0;
	xmlClosings();
	
	printf("\n</%s%s>", xmlnsPFX, xmlRoot);
	
}	# done

###############################################################################
# functions
###############################################################################

function gedBackings() {
    gedPrevId      = gedId;
    gedPrevIdRef   = gedIdRef;
    gedPrevLevel   = gedLevel;
    gedPrevToken   = gedToken;
    gedPrevValue   = gedValue;
    gedPrevPI      = gedPI;
	tagStack[gedLevel] = "</" xmlnsPFX gedToken ">" gedPI; 
}

function gedAfter(RE) {
	return match($0,RE) ? substr($0,RSTART+RLENGTH) : "" ;
}

function xmlClosings(    Level) {
	if (gedLevel> gedPrevLevel) { print gedPrevValue ? "" : ">" ; return; }
	if (gedLevel==gedPrevLevel) { print gedPrevValue ? tagStack[gedPrevLevel] : "/>"gedPrevPI ; return; }
	if (gedLevel <gedPrevLevel) {
		print gedPrevValue ? tagStack[gedPrevLevel] : "/>"gedPrevPI ;
		for (Level=gedPrevLevel-1; Level>=gedLevel; Level--) {
			printf(("%"(Level*2)"s%s\n"), "", tagStack[Level]);
		}
	}
}

function xmlPI(target, value)
{
	return "<?" ((piSET[piSTY,"ncn"])?piSET[piSTY,"ncn"]" "target:target" ") piSET[piSTY,"pfx"] piSET[piSTY,"ifx"] value piSET[piSTY,"sfx"] "?>";
}

function gedPIDATE(DATE,    part) { # ISO: YYYY-MM-DD
	split(DATE, part);
	if (DATE ~ /^[0-3]?[0-9][ \t]+(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)[ \t]+[0-9]?[0-9]?[0-9]?[0-9]$/) {
		return xmlPI(datePI, sprintf("%04s-%02s-%02s", part[3], mm[part[2]], part[1]));
	}
	if (DATE ~ /^(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)[ \t]+[0-9]?[0-9]?[0-9]?[0-9]$/) {
		return xmlPI(datePI, sprintf("%04s-%02s-%02s", part[2], mm[part[1]], 0));
	}
	if (DATE ~ /^[0-9]?[0-9]?[0-9]?[0-9]$/) { 
		return xmlPI(datePI, sprintf("%04s-%02s-%02s", DATE, 0, 0)); 
	}
	return "";
}

function LINEentify() { 
		gsub(/@@/,"@" );
		gsub(/&/,"\\&amp;");
		gsub(/</,"\\&lt;" );
		gsub(/>/,"\\&gt;" );
		if (ANSEL && /[\xA0-\xCF\xE0-\xFF]/) { ANSELentify(); }
}

###############################################################################
# UUIDv4 functions
###############################################################################
##
## UUID-spec http://tools.ietf.org/html/rfc4122.html
## diff.spec.: output of randomly mixed-case letters
##
###############################################################################

function mkUUID(	UUID)  #  31 rand() per UUID, miXed case; depends on global xchar[]
{
	UUID = "xxxxxxxx-xxxx-4xxx-" xchar[int(rand()*8+13)] "xxx-xxxxxxxxxxxx";
	while(sub(/x/,xchar[int(rand()*32+1)],UUID));
	return UUID;
}

function mkXB2N(a,	i,j,x,X,n)  # make HexDigit-Byte-(zero-filled)-to-Number Array
{
	split("0123456789abcdef",x,""); split("0123456789ABCDEF",X,""); n=0;
	for (i=1; i<17; i++)
	{
		for (j=1; j<17; j++)
		{
			a[x[i]""x[j]]=a[x[i]""X[j]]=a[X[i]""x[j]]=a[X[i]""X[j]]=n++; 
		}
	}
}

function uuid4matter(UUID,fmt,	BytesSum1,BytesSum2,ChecksHex,CanonUUID,n) 
{
	gsub(/([uU][rR][nN]:[uU][uU][iI][dD]:)|[-{ }+]/,"",UUID); UUID = substr(UUID,1,32);
	for (n=1; n<17; n++) 
	{
		BytesSum1 += xbyte[substr(UUID,n*2-1,2)]; # mkXB2N(xbyte); # xbyte["00"]=0 xbyte["01"]=1 .. "ff"="Ff"="fF"="FF"=255
		BytesSum2 += BytesSum1;
	}
	ChecksHex = sprintf("%02x%02x",BytesSum1 % 256,BytesSum2 % 256);
	CanonUUID = substr(UUID,1,8) "-" substr(UUID,9,4) "-" substr(UUID,13,4) "-" substr(UUID,17,4) "-" substr(UUID,21,12);
	
	if (fmt=="_UID") {
		return toupper(UUID ChecksHex);
	} else if (fmt=="GUID") {
		return "{" toupper(CanonUUID) "}";
	} else if (fmt=="UUID") {
		return tolower(CanonUUID);
	} else if (fmt=="XUID") {
		return "{" CanonUUID "}" ChecksHex;
	} else if (fmt=="UURN") {
		return "urn:uuid:" tolower(CanonUUID);
	} else if (fmt=="XURN") {
		return "urn:uuid:" CanonUUID "+" ChecksHex;
	} else return CanonUUID " " ChecksHex;
}

function gedPIUUID(_argh_)
{
	return xmlPI(uuidPI,uuid4matter(mkUUID(),uuidSTY));
}

function gedPI_UID(arg_UID,		gvn_UID,cmp_UID)
{
	gvn_UID = match(arg_UID,xuidRE) ? substr(arg_UID,RSTART,RLENGTH) : "" ;
	if (gvn_UID)
	{
		cmp_UID = uuid4matter(gvn_UID,_uidSTY);  
		if (arg_UID==cmp_UID)
		{
			return xmlPI(_uidPI "_1",cmp_UID);
			#
			#  true _uidSTY-format and value, comp'd and given ID+checksum are identical
			#  if _UID-style (default), value and format are likely to be accepted by PAF-compatibles
			#
		} else {
			return xmlPI(_uidPI "_X",cmp_UID);
			#
			#  true UUID 128-bit value, but false format or checksum, or surplus characters 
			#  value now preserved and transformed into _uidSTY-format, accordingly plus new checksum 
			#  if _UID-style and not eXchanged, this and next are likely to be rejected by PAF-compatibles
			#
		}							
	} else {
		return xmlPI(_uidPI "_0",uuid4matter(mkUUID(),_uidSTY));
		#
		#  false, no (valid) UUID or 128-bit-value available, new UUID in _uidSTY-format generated
		#
	}
}

###############################################################################
## ANSEL to Entities
###############################################################################
##
## This part heavily depends on "ans2uni.con"
## <http://www.heiner-eichmann.de/gedcom/ans2uni.con.zip>
## of
## Heiner Eichmann's GEDCOM 5.5 Sample Page: ANSEL to Unicode conversion 
## at  <http://www.heiner-eichmann.de/gedcom/charintr.htm>
## and <http://www.heiner-eichmann.de/gedcom/ans2uni.htm>
##
###############################################################################

function ANSELentify() { # this is brute force, btw., but I don't know better
	
##
## combining double diacritic characters (triple composits)
##

if      (/[\xE0-\xFF][\xE0-\xFF]/) {
	gsub(/\xE0\xE3\x41/, "\\&#x1EA8;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND HOOK ABOVE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE0\xE3\x45/, "\\&#x1EC2;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND HOOK ABOVE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE0\xE3\x4F/, "\\&#x1ED4;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND HOOK ABOVE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE0\xE3\x61/, "\\&#x1EA9;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND HOOK ABOVE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE0\xE3\x65/, "\\&#x1EC3;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND HOOK ABOVE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE0\xE3\x6F/, "\\&#x1ED5;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND HOOK ABOVE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE0\xE6\x41/, "\\&#x1EB2;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND HOOK ABOVE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING HOOK ABOVE
	gsub(/\xE0\xE6\x61/, "\\&#x1EB3;"); 		# LATIN SMALL LETTER A WITH BREVE AND HOOK ABOVE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING HOOK ABOVE

	gsub(/\xE1\xE3\x41/, "\\&#x1EA6;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND GRAVE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE3\x45/, "\\&#x1EC0;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND GRAVE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE3\x4F/, "\\&#x1ED2;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND GRAVE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE3\x61/, "\\&#x1EA7;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND GRAVE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE3\x65/, "\\&#x1EC1;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND GRAVE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE3\x6F/, "\\&#x1ED3;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND GRAVE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE5\x45/, "\\&#x1E14;"); 		# LATIN CAPITAL LETTER E WITH MACRON AND GRAVE = LATIN CAPITAL LETTER E + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE5\x4F/, "\\&#x1E50;"); 		# LATIN CAPITAL LETTER O WITH MACRON AND GRAVE = LATIN CAPITAL LETTER O + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE5\x65/, "\\&#x1E15;"); 		# LATIN SMALL LETTER E WITH MACRON AND GRAVE = LATIN SMALL LETTER E + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE5\x6F/, "\\&#x1E51;"); 		# LATIN SMALL LETTER O WITH MACRON AND GRAVE = LATIN SMALL LETTER O + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE6\x41/, "\\&#x1EB0;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND GRAVE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE6\x61/, "\\&#x1EB1;"); 		# LATIN SMALL LETTER A WITH BREVE AND GRAVE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE8\x55/, "\\&#x01DB;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND GRAVE = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING GRAVE ACCENT
	gsub(/\xE1\xE8\x75/, "\\&#x01DC;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND GRAVE = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING GRAVE ACCENT

	gsub(/\xE2\xE3\x41/, "\\&#x1EA4;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND ACUTE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE3\x45/, "\\&#x1EBE;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND ACUTE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE3\x4F/, "\\&#x1ED0;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND ACUTE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE3\x61/, "\\&#x1EA5;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND ACUTE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE3\x65/, "\\&#x1EBF;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND ACUTE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE3\x6F/, "\\&#x1ED1;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND ACUTE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE4\x4F/, "\\&#x1E4C;"); 		# LATIN CAPITAL LETTER O WITH TILDE AND ACUTE = LATIN CAPITAL LETTER O + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE4\x55/, "\\&#x1E78;"); 		# LATIN CAPITAL LETTER U WITH TILDE AND ACUTE = LATIN CAPITAL LETTER U + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE4\x6F/, "\\&#x1E4D;"); 		# LATIN SMALL LETTER O WITH TILDE AND ACUTE = LATIN SMALL LETTER O + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE4\x75/, "\\&#x1E79;"); 		# LATIN SMALL LETTER U WITH TILDE AND ACUTE = LATIN SMALL LETTER U + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE5\x45/, "\\&#x1E16;"); 		# LATIN CAPITAL LETTER E WITH MACRON AND ACUTE = LATIN CAPITAL LETTER E + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE5\x4F/, "\\&#x1E52;"); 		# LATIN CAPITAL LETTER O WITH MACRON AND ACUTE = LATIN CAPITAL LETTER O + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE5\x65/, "\\&#x1E17;"); 		# LATIN SMALL LETTER E WITH MACRON AND ACUTE = LATIN SMALL LETTER E + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE5\x6F/, "\\&#x1E53;"); 		# LATIN SMALL LETTER O WITH MACRON AND ACUTE = LATIN SMALL LETTER O + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE6\x41/, "\\&#x1EAE;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND ACUTE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE6\x61/, "\\&#x1EAF;"); 		# LATIN SMALL LETTER A WITH BREVE AND ACUTE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE7\x53/, "\\&#x1E64;"); 		# LATIN CAPITAL LETTER S WITH ACUTE AND DOT ABOVE = LATIN CAPITAL LETTER S + COMBINING ACUTE ACCENT + COMBINING DOT ABOVE
	gsub(/\xE2\xE7\x73/, "\\&#x1E65;"); 		# LATIN SMALL LETTER S WITH ACUTE AND DOT ABOVE = LATIN SMALL LETTER S + COMBINING ACUTE ACCENT + COMBINING DOT ABOVE
	gsub(/\xE2\xE8\x49/, "\\&#x1E2E;"); 		# LATIN CAPITAL LETTER I WITH DIAERESIS AND ACUTE = LATIN CAPITAL LETTER I + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE8\x55/, "\\&#x01D7;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND ACUTE = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE8\x69/, "\\&#x1E2F;"); 		# LATIN SMALL LETTER I WITH DIAERESIS AND ACUTE = LATIN SMALL LETTER I + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE2\xE8\x75/, "\\&#x01D8;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND ACUTE = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE2\xEA\x41/, "\\&#x01FA;"); 		# LATIN CAPITAL LETTER A WITH RING ABOVE AND ACUTE = LATIN CAPITAL LETTER A + COMBINING RING ABOVE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xEA\x61/, "\\&#x01FB;"); 		# LATIN SMALL LETTER A WITH RING ABOVE AND ACUTE = LATIN SMALL LETTER A + COMBINING RING ABOVE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xF0\x43/, "\\&#x1E08;"); 		# LATIN CAPITAL LETTER C WITH CEDILLA AND ACUTE = LATIN CAPITAL LETTER C + COMBINING CEDILLA + COMBINING ACUTE ACCENT
	gsub(/\xE2\xF0\x63/, "\\&#x1E09;"); 		# LATIN SMALL LETTER C WITH CEDILLA AND ACUTE = LATIN SMALL LETTER C + COMBINING CEDILLA + COMBINING ACUTE ACCENT

	gsub(/\xE3\xE0\x41/, "\\&#x1EA8;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND HOOK ABOVE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE3\xE0\x45/, "\\&#x1EC2;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND HOOK ABOVE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE3\xE0\x4F/, "\\&#x1ED4;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND HOOK ABOVE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE3\xE0\x61/, "\\&#x1EA9;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND HOOK ABOVE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE3\xE0\x65/, "\\&#x1EC3;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND HOOK ABOVE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE3\xE0\x6F/, "\\&#x1ED5;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND HOOK ABOVE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING HOOK ABOVE
	gsub(/\xE3\xE1\x41/, "\\&#x1EA6;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND GRAVE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE3\xE1\x45/, "\\&#x1EC0;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND GRAVE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE3\xE1\x4F/, "\\&#x1ED2;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND GRAVE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE3\xE1\x61/, "\\&#x1EA7;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND GRAVE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE3\xE1\x65/, "\\&#x1EC1;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND GRAVE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE3\xE1\x6F/, "\\&#x1ED3;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND GRAVE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING GRAVE ACCENT
	gsub(/\xE3\xE2\x41/, "\\&#x1EA4;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND ACUTE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE3\xE2\x45/, "\\&#x1EBE;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND ACUTE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE3\xE2\x4F/, "\\&#x1ED0;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND ACUTE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE3\xE2\x61/, "\\&#x1EA5;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND ACUTE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE3\xE2\x65/, "\\&#x1EBF;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND ACUTE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE3\xE2\x6F/, "\\&#x1ED1;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND ACUTE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING ACUTE ACCENT
	gsub(/\xE3\xE4\x41/, "\\&#x1EAA;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND TILDE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE3\xE4\x45/, "\\&#x1EC4;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND TILDE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE3\xE4\x4F/, "\\&#x1ED6;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND TILDE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE3\xE4\x61/, "\\&#x1EAB;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND TILDE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE3\xE4\x65/, "\\&#x1EC5;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND TILDE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE3\xE4\x6F/, "\\&#x1ED7;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND TILDE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE3\xF2\x41/, "\\&#x1EAC;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND DOT BELOW = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xE3\xF2\x45/, "\\&#x1EC6;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND DOT BELOW = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xE3\xF2\x4F/, "\\&#x1ED8;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND DOT BELOW = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xE3\xF2\x61/, "\\&#x1EAD;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND DOT BELOW = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xE3\xF2\x65/, "\\&#x1EC7;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND DOT BELOW = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xE3\xF2\x6F/, "\\&#x1ED9;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND DOT BELOW = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW

	gsub(/\xE4\xE2\x4F/, "\\&#x1E4C;"); 		# LATIN CAPITAL LETTER O WITH TILDE AND ACUTE = LATIN CAPITAL LETTER O + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE4\xE2\x55/, "\\&#x1E78;"); 		# LATIN CAPITAL LETTER U WITH TILDE AND ACUTE = LATIN CAPITAL LETTER U + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE4\xE2\x6F/, "\\&#x1E4D;"); 		# LATIN SMALL LETTER O WITH TILDE AND ACUTE = LATIN SMALL LETTER O + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE4\xE2\x75/, "\\&#x1E79;"); 		# LATIN SMALL LETTER U WITH TILDE AND ACUTE = LATIN SMALL LETTER U + COMBINING TILDE + COMBINING ACUTE ACCENT
	gsub(/\xE4\xE3\x41/, "\\&#x1EAA;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND TILDE = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE4\xE3\x45/, "\\&#x1EC4;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND TILDE = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE4\xE3\x4F/, "\\&#x1ED6;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND TILDE = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE4\xE3\x61/, "\\&#x1EAB;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND TILDE = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE4\xE3\x65/, "\\&#x1EC5;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND TILDE = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE4\xE3\x6F/, "\\&#x1ED7;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND TILDE = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING TILDE
	gsub(/\xE4\xE6\x41/, "\\&#x1EB4;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND TILDE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING TILDE
	gsub(/\xE4\xE6\x61/, "\\&#x1EB5;"); 		# LATIN SMALL LETTER A WITH BREVE AND TILDE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING TILDE
	gsub(/\xE4\xE8\x4F/, "\\&#x1E4E;"); 		# LATIN CAPITAL LETTER O WITH TILDE AND DIAERESIS = LATIN CAPITAL LETTER O + COMBINING TILDE + COMBINING DIAERESIS
	gsub(/\xE4\xE8\x6F/, "\\&#x1E4F;"); 		# LATIN SMALL LETTER O WITH TILDE AND DIAERESIS = LATIN SMALL LETTER O + COMBINING TILDE + COMBINING DIAERESIS

	gsub(/\xE5\xE1\x45/, "\\&#x1E14;"); 		# LATIN CAPITAL LETTER E WITH MACRON AND GRAVE = LATIN CAPITAL LETTER E + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE5\xE1\x4F/, "\\&#x1E50;"); 		# LATIN CAPITAL LETTER O WITH MACRON AND GRAVE = LATIN CAPITAL LETTER O + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE5\xE1\x65/, "\\&#x1E15;"); 		# LATIN SMALL LETTER E WITH MACRON AND GRAVE = LATIN SMALL LETTER E + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE5\xE1\x6F/, "\\&#x1E51;"); 		# LATIN SMALL LETTER O WITH MACRON AND GRAVE = LATIN SMALL LETTER O + COMBINING MACRON + COMBINING GRAVE ACCENT
	gsub(/\xE5\xE2\x45/, "\\&#x1E16;"); 		# LATIN CAPITAL LETTER E WITH MACRON AND ACUTE = LATIN CAPITAL LETTER E + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE5\xE2\x4F/, "\\&#x1E52;"); 		# LATIN CAPITAL LETTER O WITH MACRON AND ACUTE = LATIN CAPITAL LETTER O + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE5\xE2\x65/, "\\&#x1E17;"); 		# LATIN SMALL LETTER E WITH MACRON AND ACUTE = LATIN SMALL LETTER E + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE5\xE2\x6F/, "\\&#x1E53;"); 		# LATIN SMALL LETTER O WITH MACRON AND ACUTE = LATIN SMALL LETTER O + COMBINING MACRON + COMBINING ACUTE ACCENT
	gsub(/\xE5\xE7\x41/, "\\&#x01E0;"); 		# LATIN CAPITAL LETTER A WITH DOT ABOVE AND MACRON = LATIN CAPITAL LETTER A + COMBINING DOT ABOVE + COMBINING MACRON
	gsub(/\xE5\xE7\x61/, "\\&#x01E1;"); 		# LATIN SMALL LETTER A WITH DOT ABOVE AND MACRON = LATIN SMALL LETTER A + COMBINING DOT ABOVE + COMBINING MACRON
	gsub(/\xE5\xE8\x41/, "\\&#x01DE;"); 		# LATIN CAPITAL LETTER A WITH DIAERESIS AND MACRON = LATIN CAPITAL LETTER A + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE5\xE8\x55/, "\\&#x1E7A;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND MACRON = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE5\xE8\x61/, "\\&#x01DF;"); 		# LATIN SMALL LETTER A WITH DIAERESIS AND MACRON = LATIN SMALL LETTER A + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE5\xE8\x75/, "\\&#x1E7B;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND MACRON = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE5\xF1\x4F/, "\\&#x01EC;"); 		# LATIN CAPITAL LETTER O WITH OGONEK AND MACRON = LATIN CAPITAL LETTER O + COMBINING OGONEK + COMBINING MACRON
	gsub(/\xE5\xF1\x6F/, "\\&#x01ED;"); 		# LATIN SMALL LETTER O WITH OGONEK AND MACRON = LATIN SMALL LETTER O + COMBINING OGONEK + COMBINING MACRON
	gsub(/\xE5\xF2\x4C/, "\\&#x1E38;"); 		# LATIN CAPITAL LETTER L WITH DOT BELOW AND MACRON = LATIN CAPITAL LETTER L + COMBINING DOT BELOW + COMBINING MACRON
	gsub(/\xE5\xF2\x52/, "\\&#x1E5C;"); 		# LATIN CAPITAL LETTER R WITH DOT BELOW AND MACRON = LATIN CAPITAL LETTER R + COMBINING DOT BELOW + COMBINING MACRON
	gsub(/\xE5\xF2\x6C/, "\\&#x1E39;"); 		# LATIN SMALL LETTER L WITH DOT BELOW AND MACRON = LATIN SMALL LETTER L + COMBINING DOT BELOW + COMBINING MACRON
	gsub(/\xE5\xF2\x72/, "\\&#x1E5D;"); 		# LATIN SMALL LETTER R WITH DOT BELOW AND MACRON = LATIN SMALL LETTER R + COMBINING DOT BELOW + COMBINING MACRON

	gsub(/\xE6\xE0\x41/, "\\&#x1EB2;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND HOOK ABOVE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING HOOK ABOVE
	gsub(/\xE6\xE0\x61/, "\\&#x1EB3;"); 		# LATIN SMALL LETTER A WITH BREVE AND HOOK ABOVE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING HOOK ABOVE
	gsub(/\xE6\xE1\x41/, "\\&#x1EB0;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND GRAVE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING GRAVE ACCENT
	gsub(/\xE6\xE1\x61/, "\\&#x1EB1;"); 		# LATIN SMALL LETTER A WITH BREVE AND GRAVE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING GRAVE ACCENT
	gsub(/\xE6\xE2\x41/, "\\&#x1EAE;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND ACUTE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING ACUTE ACCENT
	gsub(/\xE6\xE2\x61/, "\\&#x1EAF;"); 		# LATIN SMALL LETTER A WITH BREVE AND ACUTE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING ACUTE ACCENT
	gsub(/\xE6\xE4\x41/, "\\&#x1EB4;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND TILDE = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING TILDE
	gsub(/\xE6\xE4\x61/, "\\&#x1EB5;"); 		# LATIN SMALL LETTER A WITH BREVE AND TILDE = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING TILDE
	gsub(/\xE6\xF0\x45/, "\\&#x1E1C;"); 		# LATIN CAPITAL LETTER E WITH CEDILLA AND BREVE = LATIN CAPITAL LETTER E + COMBINING CEDILLA + COMBINING BREVE
	gsub(/\xE6\xF0\x65/, "\\&#x1E1D;"); 		# LATIN SMALL LETTER E WITH CEDILLA AND BREVE = LATIN SMALL LETTER E + COMBINING CEDILLA + COMBINING BREVE
	gsub(/\xE6\xF2\x41/, "\\&#x1EB6;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND DOT BELOW = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING DOT BELOW
	gsub(/\xE6\xF2\x61/, "\\&#x1EB7;"); 		# LATIN SMALL LETTER A WITH BREVE AND DOT BELOW = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING DOT BELOW

	gsub(/\xE7\xE2\x53/, "\\&#x1E64;"); 		# LATIN CAPITAL LETTER S WITH ACUTE AND DOT ABOVE = LATIN CAPITAL LETTER S + COMBINING ACUTE ACCENT + COMBINING DOT ABOVE
	gsub(/\xE7\xE2\x73/, "\\&#x1E65;"); 		# LATIN SMALL LETTER S WITH ACUTE AND DOT ABOVE = LATIN SMALL LETTER S + COMBINING ACUTE ACCENT + COMBINING DOT ABOVE
	gsub(/\xE7\xE5\x41/, "\\&#x01E0;"); 		# LATIN CAPITAL LETTER A WITH DOT ABOVE AND MACRON = LATIN CAPITAL LETTER A + COMBINING DOT ABOVE + COMBINING MACRON
	gsub(/\xE7\xE5\x61/, "\\&#x01E1;"); 		# LATIN SMALL LETTER A WITH DOT ABOVE AND MACRON = LATIN SMALL LETTER A + COMBINING DOT ABOVE + COMBINING MACRON
	gsub(/\xE7\xE9\x53/, "\\&#x1E66;"); 		# LATIN CAPITAL LETTER S WITH CARON AND DOT ABOVE = LATIN CAPITAL LETTER S + COMBINING CARON + COMBINING DOT ABOVE
	gsub(/\xE7\xE9\x73/, "\\&#x1E67;"); 		# LATIN SMALL LETTER S WITH CARON AND DOT ABOVE = LATIN SMALL LETTER S + COMBINING CARON + COMBINING DOT ABOVE
	gsub(/\xE7\xF2\x53/, "\\&#x1E68;"); 		# LATIN CAPITAL LETTER S WITH DOT BELOW AND DOT ABOVE = LATIN CAPITAL LETTER S + COMBINING DOT BELOW + COMBINING DOT ABOVE
	gsub(/\xE7\xF2\x73/, "\\&#x1E69;"); 		# LATIN SMALL LETTER S WITH DOT BELOW AND DOT ABOVE = LATIN SMALL LETTER S + COMBINING DOT BELOW + COMBINING DOT ABOVE

	gsub(/\xE8\xE1\x55/, "\\&#x01DB;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND GRAVE = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING GRAVE ACCENT
	gsub(/\xE8\xE1\x75/, "\\&#x01DC;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND GRAVE = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING GRAVE ACCENT
	gsub(/\xE8\xE2\x49/, "\\&#x1E2E;"); 		# LATIN CAPITAL LETTER I WITH DIAERESIS AND ACUTE = LATIN CAPITAL LETTER I + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE8\xE2\x55/, "\\&#x01D7;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND ACUTE = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE8\xE2\x69/, "\\&#x1E2F;"); 		# LATIN SMALL LETTER I WITH DIAERESIS AND ACUTE = LATIN SMALL LETTER I + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE8\xE2\x75/, "\\&#x01D8;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND ACUTE = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING ACUTE ACCENT
	gsub(/\xE8\xE4\x4F/, "\\&#x1E4E;"); 		# LATIN CAPITAL LETTER O WITH TILDE AND DIAERESIS = LATIN CAPITAL LETTER O + COMBINING TILDE + COMBINING DIAERESIS
	gsub(/\xE8\xE4\x6F/, "\\&#x1E4F;"); 		# LATIN SMALL LETTER O WITH TILDE AND DIAERESIS = LATIN SMALL LETTER O + COMBINING TILDE + COMBINING DIAERESIS
	gsub(/\xE8\xE5\x41/, "\\&#x01DE;"); 		# LATIN CAPITAL LETTER A WITH DIAERESIS AND MACRON = LATIN CAPITAL LETTER A + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE8\xE5\x55/, "\\&#x1E7A;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND MACRON = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE8\xE5\x61/, "\\&#x01DF;"); 		# LATIN SMALL LETTER A WITH DIAERESIS AND MACRON = LATIN SMALL LETTER A + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE8\xE5\x75/, "\\&#x1E7B;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND MACRON = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING MACRON
	gsub(/\xE8\xE9\x55/, "\\&#x01D9;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND CARON = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING CARON
	gsub(/\xE8\xE9\x75/, "\\&#x01DA;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND CARON = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING CARON

	gsub(/\xE9\xE7\x53/, "\\&#x1E66;"); 		# LATIN CAPITAL LETTER S WITH CARON AND DOT ABOVE = LATIN CAPITAL LETTER S + COMBINING CARON + COMBINING DOT ABOVE
	gsub(/\xE9\xE7\x73/, "\\&#x1E67;"); 		# LATIN SMALL LETTER S WITH CARON AND DOT ABOVE = LATIN SMALL LETTER S + COMBINING CARON + COMBINING DOT ABOVE
	gsub(/\xE9\xE8\x55/, "\\&#x01D9;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS AND CARON = LATIN CAPITAL LETTER U + COMBINING DIAERESIS + COMBINING CARON
	gsub(/\xE9\xE8\x75/, "\\&#x01DA;"); 		# LATIN SMALL LETTER U WITH DIAERESIS AND CARON = LATIN SMALL LETTER U + COMBINING DIAERESIS + COMBINING CARON

	gsub(/\xEA\xE2\x41/, "\\&#x01FA;"); 		# LATIN CAPITAL LETTER A WITH RING ABOVE AND ACUTE = LATIN CAPITAL LETTER A + COMBINING RING ABOVE + COMBINING ACUTE ACCENT
	gsub(/\xEA\xE2\x61/, "\\&#x01FB;"); 		# LATIN SMALL LETTER A WITH RING ABOVE AND ACUTE = LATIN SMALL LETTER A + COMBINING RING ABOVE + COMBINING ACUTE ACCENT

	gsub(/\xF0\xE2\x43/, "\\&#x1E08;"); 		# LATIN CAPITAL LETTER C WITH CEDILLA AND ACUTE = LATIN CAPITAL LETTER C + COMBINING CEDILLA + COMBINING ACUTE ACCENT
	gsub(/\xF0\xE2\x63/, "\\&#x1E09;"); 		# LATIN SMALL LETTER C WITH CEDILLA AND ACUTE = LATIN SMALL LETTER C + COMBINING CEDILLA + COMBINING ACUTE ACCENT
	gsub(/\xF0\xE6\x45/, "\\&#x1E1C;"); 		# LATIN CAPITAL LETTER E WITH CEDILLA AND BREVE = LATIN CAPITAL LETTER E + COMBINING CEDILLA + COMBINING BREVE
	gsub(/\xF0\xE6\x65/, "\\&#x1E1D;"); 		# LATIN SMALL LETTER E WITH CEDILLA AND BREVE = LATIN SMALL LETTER E + COMBINING CEDILLA + COMBINING BREVE

	gsub(/\xF1\xE5\x4F/, "\\&#x01EC;"); 		# LATIN CAPITAL LETTER O WITH OGONEK AND MACRON = LATIN CAPITAL LETTER O + COMBINING OGONEK + COMBINING MACRON
	gsub(/\xF1\xE5\x6F/, "\\&#x01ED;"); 		# LATIN SMALL LETTER O WITH OGONEK AND MACRON = LATIN SMALL LETTER O + COMBINING OGONEK + COMBINING MACRON

	gsub(/\xF2\xE3\x41/, "\\&#x1EAC;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX AND DOT BELOW = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xF2\xE3\x45/, "\\&#x1EC6;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX AND DOT BELOW = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xF2\xE3\x4F/, "\\&#x1ED8;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX AND DOT BELOW = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xF2\xE3\x61/, "\\&#x1EAD;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX AND DOT BELOW = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xF2\xE3\x65/, "\\&#x1EC7;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX AND DOT BELOW = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xF2\xE3\x6F/, "\\&#x1ED9;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX AND DOT BELOW = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT + COMBINING DOT BELOW
	gsub(/\xF2\xE5\x4C/, "\\&#x1E38;"); 		# LATIN CAPITAL LETTER L WITH DOT BELOW AND MACRON = LATIN CAPITAL LETTER L + COMBINING DOT BELOW + COMBINING MACRON
	gsub(/\xF2\xE5\x52/, "\\&#x1E5C;"); 		# LATIN CAPITAL LETTER R WITH DOT BELOW AND MACRON = LATIN CAPITAL LETTER R + COMBINING DOT BELOW + COMBINING MACRON
	gsub(/\xF2\xE5\x6C/, "\\&#x1E39;"); 		# LATIN SMALL LETTER L WITH DOT BELOW AND MACRON = LATIN SMALL LETTER L + COMBINING DOT BELOW + COMBINING MACRON
	gsub(/\xF2\xE5\x72/, "\\&#x1E5D;"); 		# LATIN SMALL LETTER R WITH DOT BELOW AND MACRON = LATIN SMALL LETTER R + COMBINING DOT BELOW + COMBINING MACRON
	gsub(/\xF2\xE6\x41/, "\\&#x1EB6;"); 		# LATIN CAPITAL LETTER A WITH BREVE AND DOT BELOW = LATIN CAPITAL LETTER A + COMBINING BREVE + COMBINING DOT BELOW
	gsub(/\xF2\xE6\x61/, "\\&#x1EB7;"); 		# LATIN SMALL LETTER A WITH BREVE AND DOT BELOW = LATIN SMALL LETTER A + COMBINING BREVE + COMBINING DOT BELOW
	gsub(/\xF2\xE7\x53/, "\\&#x1E68;"); 		# LATIN CAPITAL LETTER S WITH DOT BELOW AND DOT ABOVE = LATIN CAPITAL LETTER S + COMBINING DOT BELOW + COMBINING DOT ABOVE
	gsub(/\xF2\xE7\x73/, "\\&#x1E69;"); 		# LATIN SMALL LETTER S WITH DOT BELOW AND DOT ABOVE = LATIN SMALL LETTER S + COMBINING DOT BELOW + COMBINING DOT ABOVE
}

##
## combining single diacritic characters (double composits)
##

if      (/\xE0/) {
	gsub(/\xE0\x41/, "\\&#x1EA2;"); 		# LATIN CAPITAL LETTER A WITH HOOK ABOVE = LATIN CAPITAL LETTER A + COMBINING HOOK ABOVE
	gsub(/\xE0\x45/, "\\&#x1EBA;"); 		# LATIN CAPITAL LETTER E WITH HOOK ABOVE = LATIN CAPITAL LETTER E + COMBINING HOOK ABOVE
	gsub(/\xE0\x49/, "\\&#x1EC8;"); 		# LATIN CAPITAL LETTER I WITH HOOK ABOVE = LATIN CAPITAL LETTER I + COMBINING HOOK ABOVE
	gsub(/\xE0\x4F/, "\\&#x1ECE;"); 		# LATIN CAPITAL LETTER O WITH HOOK ABOVE = LATIN CAPITAL LETTER O + COMBINING HOOK ABOVE
	gsub(/\xE0\x55/, "\\&#x1EE6;"); 		# LATIN CAPITAL LETTER U WITH HOOK ABOVE = LATIN CAPITAL LETTER U + COMBINING HOOK ABOVE
	gsub(/\xE0\x59/, "\\&#x1EF6;"); 		# LATIN CAPITAL LETTER Y WITH HOOK ABOVE = LATIN CAPITAL LETTER Y + COMBINING HOOK ABOVE
	gsub(/\xE0\x61/, "\\&#x1EA3;"); 		# LATIN SMALL LETTER A WITH HOOK ABOVE = LATIN SMALL LETTER A + COMBINING HOOK ABOVE
	gsub(/\xE0\x65/, "\\&#x1EBB;"); 		# LATIN SMALL LETTER E WITH HOOK ABOVE = LATIN SMALL LETTER E + COMBINING HOOK ABOVE
	gsub(/\xE0\x69/, "\\&#x1EC9;"); 		# LATIN SMALL LETTER I WITH HOOK ABOVE = LATIN SMALL LETTER I + COMBINING HOOK ABOVE
	gsub(/\xE0\x6F/, "\\&#x1ECF;"); 		# LATIN SMALL LETTER O WITH HOOK ABOVE = LATIN SMALL LETTER O + COMBINING HOOK ABOVE
	gsub(/\xE0\x75/, "\\&#x1EE7;"); 		# LATIN SMALL LETTER U WITH HOOK ABOVE = LATIN SMALL LETTER U + COMBINING HOOK ABOVE
	gsub(/\xE0\x79/, "\\&#x1EF7;"); 		# LATIN SMALL LETTER Y WITH HOOK ABOVE = LATIN SMALL LETTER Y + COMBINING HOOK ABOVE
	gsub(/\xE0/, "\\&#x0309;"); 		#combining hook above
}

if      (/\xE1/) {
	gsub(/\xE1\x41/, "\\&#x00C0;"); 		# LATIN CAPITAL LETTER A WITH GRAVE = LATIN CAPITAL LETTER A + COMBINING GRAVE ACCENT
	gsub(/\xE1\x45/, "\\&#x00C8;"); 		# LATIN CAPITAL LETTER E WITH GRAVE = LATIN CAPITAL LETTER E + COMBINING GRAVE ACCENT
	gsub(/\xE1\x49/, "\\&#x00CC;"); 		# LATIN CAPITAL LETTER I WITH GRAVE = LATIN CAPITAL LETTER I + COMBINING GRAVE ACCENT
	gsub(/\xE1\x4F/, "\\&#x00D2;"); 		# LATIN CAPITAL LETTER O WITH GRAVE = LATIN CAPITAL LETTER O + COMBINING GRAVE ACCENT
	gsub(/\xE1\x55/, "\\&#x00D9;"); 		# LATIN CAPITAL LETTER U WITH GRAVE = LATIN CAPITAL LETTER U + COMBINING GRAVE ACCENT
	gsub(/\xE1\x57/, "\\&#x1E80;"); 		# LATIN CAPITAL LETTER W WITH GRAVE = LATIN CAPITAL LETTER W + COMBINING GRAVE ACCENT
	gsub(/\xE1\x59/, "\\&#x1EF2;"); 		# LATIN CAPITAL LETTER Y WITH GRAVE = LATIN CAPITAL LETTER Y + COMBINING GRAVE ACCENT
	gsub(/\xE1\x61/, "\\&#x00E0;"); 		# LATIN SMALL LETTER A WITH GRAVE = LATIN SMALL LETTER A + COMBINING GRAVE ACCENT
	gsub(/\xE1\x65/, "\\&#x00E8;"); 		# LATIN SMALL LETTER E WITH GRAVE = LATIN SMALL LETTER E + COMBINING GRAVE ACCENT
	gsub(/\xE1\x69/, "\\&#x00EC;"); 		# LATIN SMALL LETTER I WITH GRAVE = LATIN SMALL LETTER I + COMBINING GRAVE ACCENT
	gsub(/\xE1\x6F/, "\\&#x00F2;"); 		# LATIN SMALL LETTER O WITH GRAVE = LATIN SMALL LETTER O + COMBINING GRAVE ACCENT
	gsub(/\xE1\x75/, "\\&#x00F9;"); 		# LATIN SMALL LETTER U WITH GRAVE = LATIN SMALL LETTER U + COMBINING GRAVE ACCENT
	gsub(/\xE1\x77/, "\\&#x1E81;"); 		# LATIN SMALL LETTER W WITH GRAVE = LATIN SMALL LETTER W + COMBINING GRAVE ACCENT
	gsub(/\xE1\x79/, "\\&#x1EF3;"); 		# LATIN SMALL LETTER Y WITH GRAVE = LATIN SMALL LETTER Y + COMBINING GRAVE ACCENT
	gsub(/\xE1/, "\\&#x0300;"); 		#combining grave accent
}

if      (/\xE2/) {
	gsub(/\xE2\x41/, "\\&#x00C1;"); 		# LATIN CAPITAL LETTER A WITH ACUTE = LATIN CAPITAL LETTER A + COMBINING ACUTE ACCENT
	gsub(/\xE2\x43/, "\\&#x0106;"); 		# LATIN CAPITAL LETTER C WITH ACUTE = LATIN CAPITAL LETTER C + COMBINING ACUTE ACCENT
	gsub(/\xE2\x45/, "\\&#x00C9;"); 		# LATIN CAPITAL LETTER E WITH ACUTE = LATIN CAPITAL LETTER E + COMBINING ACUTE ACCENT
	gsub(/\xE2\x47/, "\\&#x01F4;"); 		# LATIN CAPITAL LETTER G WITH ACUTE = LATIN CAPITAL LETTER G + COMBINING ACUTE ACCENT
	gsub(/\xE2\x49/, "\\&#x00CD;"); 		# LATIN CAPITAL LETTER I WITH ACUTE = LATIN CAPITAL LETTER I + COMBINING ACUTE ACCENT
	gsub(/\xE2\x4B/, "\\&#x1E30;"); 		# LATIN CAPITAL LETTER K WITH ACUTE = LATIN CAPITAL LETTER K + COMBINING ACUTE ACCENT
	gsub(/\xE2\x4C/, "\\&#x0139;"); 		# LATIN CAPITAL LETTER L WITH ACUTE = LATIN CAPITAL LETTER L + COMBINING ACUTE ACCENT
	gsub(/\xE2\x4D/, "\\&#x1E3E;"); 		# LATIN CAPITAL LETTER M WITH ACUTE = LATIN CAPITAL LETTER M + COMBINING ACUTE ACCENT
	gsub(/\xE2\x4E/, "\\&#x0143;"); 		# LATIN CAPITAL LETTER N WITH ACUTE = LATIN CAPITAL LETTER N + COMBINING ACUTE ACCENT
	gsub(/\xE2\x4F/, "\\&#x00D3;"); 		# LATIN CAPITAL LETTER O WITH ACUTE = LATIN CAPITAL LETTER O + COMBINING ACUTE ACCENT
	gsub(/\xE2\x50/, "\\&#x1E54;"); 		# LATIN CAPITAL LETTER P WITH ACUTE = LATIN CAPITAL LETTER P + COMBINING ACUTE ACCENT
	gsub(/\xE2\x52/, "\\&#x0154;"); 		# LATIN CAPITAL LETTER R WITH ACUTE = LATIN CAPITAL LETTER R + COMBINING ACUTE ACCENT
	gsub(/\xE2\x53/, "\\&#x015A;"); 		# LATIN CAPITAL LETTER S WITH ACUTE = LATIN CAPITAL LETTER S + COMBINING ACUTE ACCENT
	gsub(/\xE2\x55/, "\\&#x00DA;"); 		# LATIN CAPITAL LETTER U WITH ACUTE = LATIN CAPITAL LETTER U + COMBINING ACUTE ACCENT
	gsub(/\xE2\x57/, "\\&#x1E82;"); 		# LATIN CAPITAL LETTER W WITH ACUTE = LATIN CAPITAL LETTER W + COMBINING ACUTE ACCENT
	gsub(/\xE2\x59/, "\\&#x00DD;"); 		# LATIN CAPITAL LETTER Y WITH ACUTE = LATIN CAPITAL LETTER Y + COMBINING ACUTE ACCENT
	gsub(/\xE2\x5A/, "\\&#x0179;"); 		# LATIN CAPITAL LETTER Z WITH ACUTE = LATIN CAPITAL LETTER Z + COMBINING ACUTE ACCENT
	gsub(/\xE2\x61/, "\\&#x00E1;"); 		# LATIN SMALL LETTER A WITH ACUTE = LATIN SMALL LETTER A + COMBINING ACUTE ACCENT
	gsub(/\xE2\x63/, "\\&#x0107;"); 		# LATIN SMALL LETTER C WITH ACUTE = LATIN SMALL LETTER C + COMBINING ACUTE ACCENT
	gsub(/\xE2\x65/, "\\&#x00E9;"); 		# LATIN SMALL LETTER E WITH ACUTE = LATIN SMALL LETTER E + COMBINING ACUTE ACCENT
	gsub(/\xE2\x67/, "\\&#x01F5;"); 		# LATIN SMALL LETTER G WITH ACUTE = LATIN SMALL LETTER G + COMBINING ACUTE ACCENT
	gsub(/\xE2\x69/, "\\&#x00ED;"); 		# LATIN SMALL LETTER I WITH ACUTE = LATIN SMALL LETTER I + COMBINING ACUTE ACCENT
	gsub(/\xE2\x6B/, "\\&#x1E31;"); 		# LATIN SMALL LETTER K WITH ACUTE = LATIN SMALL LETTER K + COMBINING ACUTE ACCENT
	gsub(/\xE2\x6C/, "\\&#x013A;"); 		# LATIN SMALL LETTER L WITH ACUTE = LATIN SMALL LETTER L + COMBINING ACUTE ACCENT
	gsub(/\xE2\x6D/, "\\&#x1E3F;"); 		# LATIN SMALL LETTER M WITH ACUTE = LATIN SMALL LETTER M + COMBINING ACUTE ACCENT
	gsub(/\xE2\x6E/, "\\&#x0144;"); 		# LATIN SMALL LETTER N WITH ACUTE = LATIN SMALL LETTER N + COMBINING ACUTE ACCENT
	gsub(/\xE2\x6F/, "\\&#x00F3;"); 		# LATIN SMALL LETTER O WITH ACUTE = LATIN SMALL LETTER O + COMBINING ACUTE ACCENT
	gsub(/\xE2\x70/, "\\&#x1E55;"); 		# LATIN SMALL LETTER P WITH ACUTE = LATIN SMALL LETTER P + COMBINING ACUTE ACCENT
	gsub(/\xE2\x72/, "\\&#x0155;"); 		# LATIN SMALL LETTER R WITH ACUTE = LATIN SMALL LETTER R + COMBINING ACUTE ACCENT
	gsub(/\xE2\x73/, "\\&#x015B;"); 		# LATIN SMALL LETTER S WITH ACUTE = LATIN SMALL LETTER S + COMBINING ACUTE ACCENT
	gsub(/\xE2\x75/, "\\&#x00FA;"); 		# LATIN SMALL LETTER U WITH ACUTE = LATIN SMALL LETTER U + COMBINING ACUTE ACCENT
	gsub(/\xE2\x77/, "\\&#x1E83;"); 		# LATIN SMALL LETTER W WITH ACUTE = LATIN SMALL LETTER W + COMBINING ACUTE ACCENT
	gsub(/\xE2\x79/, "\\&#x00FD;"); 		# LATIN SMALL LETTER Y WITH ACUTE = LATIN SMALL LETTER Y + COMBINING ACUTE ACCENT
	gsub(/\xE2\x7A/, "\\&#x017A;"); 		# LATIN SMALL LETTER Z WITH ACUTE = LATIN SMALL LETTER Z + COMBINING ACUTE ACCENT
	gsub(/\xE2\xA5/, "\\&#x01FC;"); 		# LATIN CAPITAL LETTER AE WITH ACUTE = LATIN CAPITAL LETTER AE + COMBINING ACUTE ACCENT
	gsub(/\xE2\xB5/, "\\&#x01FD;"); 		# LATIN SMALL LETTER AE WITH ACUTE = LATIN SMALL LETTER AE + COMBINING ACUTE ACCENT
	gsub(/\xE2/, "\\&#x0301;"); 		#combining acute accent
}

if      (/\xE3/) {
	gsub(/\xE3\x41/, "\\&#x00C2;"); 		# LATIN CAPITAL LETTER A WITH CIRCUMFLEX = LATIN CAPITAL LETTER A + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x43/, "\\&#x0108;"); 		# LATIN CAPITAL LETTER C WITH CIRCUMFLEX = LATIN CAPITAL LETTER C + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x45/, "\\&#x00CA;"); 		# LATIN CAPITAL LETTER E WITH CIRCUMFLEX = LATIN CAPITAL LETTER E + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x47/, "\\&#x011C;"); 		# LATIN CAPITAL LETTER G WITH CIRCUMFLEX = LATIN CAPITAL LETTER G + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x48/, "\\&#x0124;"); 		# LATIN CAPITAL LETTER H WITH CIRCUMFLEX = LATIN CAPITAL LETTER H + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x49/, "\\&#x00CE;"); 		# LATIN CAPITAL LETTER I WITH CIRCUMFLEX = LATIN CAPITAL LETTER I + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x4A/, "\\&#x0134;"); 		# LATIN CAPITAL LETTER J WITH CIRCUMFLEX = LATIN CAPITAL LETTER J + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x4F/, "\\&#x00D4;"); 		# LATIN CAPITAL LETTER O WITH CIRCUMFLEX = LATIN CAPITAL LETTER O + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x53/, "\\&#x015C;"); 		# LATIN CAPITAL LETTER S WITH CIRCUMFLEX = LATIN CAPITAL LETTER S + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x55/, "\\&#x00DB;"); 		# LATIN CAPITAL LETTER U WITH CIRCUMFLEX = LATIN CAPITAL LETTER U + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x57/, "\\&#x0174;"); 		# LATIN CAPITAL LETTER W WITH CIRCUMFLEX = LATIN CAPITAL LETTER W + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x59/, "\\&#x0176;"); 		# LATIN CAPITAL LETTER Y WITH CIRCUMFLEX = LATIN CAPITAL LETTER Y + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x5A/, "\\&#x1E90;"); 		# LATIN CAPITAL LETTER Z WITH CIRCUMFLEX = LATIN CAPITAL LETTER Z + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x61/, "\\&#x00E2;"); 		# LATIN SMALL LETTER A WITH CIRCUMFLEX = LATIN SMALL LETTER A + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x63/, "\\&#x0109;"); 		# LATIN SMALL LETTER C WITH CIRCUMFLEX = LATIN SMALL LETTER C + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x65/, "\\&#x00EA;"); 		# LATIN SMALL LETTER E WITH CIRCUMFLEX = LATIN SMALL LETTER E + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x67/, "\\&#x011D;"); 		# LATIN SMALL LETTER G WITH CIRCUMFLEX = LATIN SMALL LETTER G + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x68/, "\\&#x0125;"); 		# LATIN SMALL LETTER H WITH CIRCUMFLEX = LATIN SMALL LETTER H + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x69/, "\\&#x00EE;"); 		# LATIN SMALL LETTER I WITH CIRCUMFLEX = LATIN SMALL LETTER I + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x6A/, "\\&#x0135;"); 		# LATIN SMALL LETTER J WITH CIRCUMFLEX = LATIN SMALL LETTER J + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x6F/, "\\&#x00F4;"); 		# LATIN SMALL LETTER O WITH CIRCUMFLEX = LATIN SMALL LETTER O + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x73/, "\\&#x015D;"); 		# LATIN SMALL LETTER S WITH CIRCUMFLEX = LATIN SMALL LETTER S + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x75/, "\\&#x00FB;"); 		# LATIN SMALL LETTER U WITH CIRCUMFLEX = LATIN SMALL LETTER U + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x77/, "\\&#x0175;"); 		# LATIN SMALL LETTER W WITH CIRCUMFLEX = LATIN SMALL LETTER W + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x79/, "\\&#x0177;"); 		# LATIN SMALL LETTER Y WITH CIRCUMFLEX = LATIN SMALL LETTER Y + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3\x7A/, "\\&#x1E91;"); 		# LATIN SMALL LETTER Z WITH CIRCUMFLEX = LATIN SMALL LETTER Z + COMBINING CIRCUMFLEX ACCENT
	gsub(/\xE3/, "\\&#x0302;"); 		#combining circumflex accent
}

if      (/\xE4/) {
	gsub(/\xE4\x41/, "\\&#x00C3;"); 		# LATIN CAPITAL LETTER A WITH TILDE = LATIN CAPITAL LETTER A + COMBINING TILDE
	gsub(/\xE4\x45/, "\\&#x1EBC;"); 		# LATIN CAPITAL LETTER E WITH TILDE = LATIN CAPITAL LETTER E + COMBINING TILDE
	gsub(/\xE4\x49/, "\\&#x0128;"); 		# LATIN CAPITAL LETTER I WITH TILDE = LATIN CAPITAL LETTER I + COMBINING TILDE
	gsub(/\xE4\x4E/, "\\&#x00D1;"); 		# LATIN CAPITAL LETTER N WITH TILDE = LATIN CAPITAL LETTER N + COMBINING TILDE
	gsub(/\xE4\x4F/, "\\&#x00D5;"); 		# LATIN CAPITAL LETTER O WITH TILDE = LATIN CAPITAL LETTER O + COMBINING TILDE
	gsub(/\xE4\x55/, "\\&#x0168;"); 		# LATIN CAPITAL LETTER U WITH TILDE = LATIN CAPITAL LETTER U + COMBINING TILDE
	gsub(/\xE4\x56/, "\\&#x1E7C;"); 		# LATIN CAPITAL LETTER V WITH TILDE = LATIN CAPITAL LETTER V + COMBINING TILDE
	gsub(/\xE4\x59/, "\\&#x1EF8;"); 		# LATIN CAPITAL LETTER Y WITH TILDE = LATIN CAPITAL LETTER Y + COMBINING TILDE
	gsub(/\xE4\x61/, "\\&#x00E3;"); 		# LATIN SMALL LETTER A WITH TILDE = LATIN SMALL LETTER A + COMBINING TILDE
	gsub(/\xE4\x65/, "\\&#x1EBD;"); 		# LATIN SMALL LETTER E WITH TILDE = LATIN SMALL LETTER E + COMBINING TILDE
	gsub(/\xE4\x69/, "\\&#x0129;"); 		# LATIN SMALL LETTER I WITH TILDE = LATIN SMALL LETTER I + COMBINING TILDE
	gsub(/\xE4\x6E/, "\\&#x00F1;"); 		# LATIN SMALL LETTER N WITH TILDE = LATIN SMALL LETTER N + COMBINING TILDE
	gsub(/\xE4\x6F/, "\\&#x00F5;"); 		# LATIN SMALL LETTER O WITH TILDE = LATIN SMALL LETTER O + COMBINING TILDE
	gsub(/\xE4\x75/, "\\&#x0169;"); 		# LATIN SMALL LETTER U WITH TILDE = LATIN SMALL LETTER U + COMBINING TILDE
	gsub(/\xE4\x76/, "\\&#x1E7D;"); 		# LATIN SMALL LETTER V WITH TILDE = LATIN SMALL LETTER V + COMBINING TILDE
	gsub(/\xE4\x79/, "\\&#x1EF9;"); 		# LATIN SMALL LETTER Y WITH TILDE = LATIN SMALL LETTER Y + COMBINING TILDE
	gsub(/\xE4/, "\\&#x0303;"); 		#combining tilde
}

if      (/\xE5/) {
	gsub(/\xE5\x41/, "\\&#x0100;"); 		# LATIN CAPITAL LETTER A WITH MACRON = LATIN CAPITAL LETTER A + COMBINING MACRON
	gsub(/\xE5\x45/, "\\&#x0112;"); 		# LATIN CAPITAL LETTER E WITH MACRON = LATIN CAPITAL LETTER E + COMBINING MACRON
	gsub(/\xE5\x47/, "\\&#x1E20;"); 		# LATIN CAPITAL LETTER G WITH MACRON = LATIN CAPITAL LETTER G + COMBINING MACRON
	gsub(/\xE5\x49/, "\\&#x012A;"); 		# LATIN CAPITAL LETTER I WITH MACRON = LATIN CAPITAL LETTER I + COMBINING MACRON
	gsub(/\xE5\x4F/, "\\&#x014C;"); 		# LATIN CAPITAL LETTER O WITH MACRON = LATIN CAPITAL LETTER O + COMBINING MACRON
	gsub(/\xE5\x55/, "\\&#x016A;"); 		# LATIN CAPITAL LETTER U WITH MACRON = LATIN CAPITAL LETTER U + COMBINING MACRON
	gsub(/\xE5\x61/, "\\&#x0101;"); 		# LATIN SMALL LETTER A WITH MACRON = LATIN SMALL LETTER A + COMBINING MACRON
	gsub(/\xE5\x65/, "\\&#x0113;"); 		# LATIN SMALL LETTER E WITH MACRON = LATIN SMALL LETTER E + COMBINING MACRON
	gsub(/\xE5\x67/, "\\&#x1E21;"); 		# LATIN SMALL LETTER G WITH MACRON = LATIN SMALL LETTER G + COMBINING MACRON
	gsub(/\xE5\x69/, "\\&#x012B;"); 		# LATIN SMALL LETTER I WITH MACRON = LATIN SMALL LETTER I + COMBINING MACRON
	gsub(/\xE5\x6F/, "\\&#x014D;"); 		# LATIN SMALL LETTER O WITH MACRON = LATIN SMALL LETTER O + COMBINING MACRON
	gsub(/\xE5\x75/, "\\&#x016B;"); 		# LATIN SMALL LETTER U WITH MACRON = LATIN SMALL LETTER U + COMBINING MACRON
	gsub(/\xE5\xA5/, "\\&#x01E2;"); 		# LATIN CAPITAL LETTER AE WITH MACRON = LATIN CAPITAL LETTER AE + COMBINING MACRON
	gsub(/\xE5\xB5/, "\\&#x01E3;"); 		# LATIN SMALL LETTER AE WITH MACRON = LATIN SMALL LETTER AE + COMBINING MACRON
	gsub(/\xE5/, "\\&#x0304;"); 		#combining macron
}

if      (/\xE6/) {
	gsub(/\xE6\x41/, "\\&#x0102;"); 		# LATIN CAPITAL LETTER A WITH BREVE = LATIN CAPITAL LETTER A + COMBINING BREVE
	gsub(/\xE6\x45/, "\\&#x0114;"); 		# LATIN CAPITAL LETTER E WITH BREVE = LATIN CAPITAL LETTER E + COMBINING BREVE
	gsub(/\xE6\x47/, "\\&#x011E;"); 		# LATIN CAPITAL LETTER G WITH BREVE = LATIN CAPITAL LETTER G + COMBINING BREVE
	gsub(/\xE6\x49/, "\\&#x012C;"); 		# LATIN CAPITAL LETTER I WITH BREVE = LATIN CAPITAL LETTER I + COMBINING BREVE
	gsub(/\xE6\x4F/, "\\&#x014E;"); 		# LATIN CAPITAL LETTER O WITH BREVE = LATIN CAPITAL LETTER O + COMBINING BREVE
	gsub(/\xE6\x55/, "\\&#x016C;"); 		# LATIN CAPITAL LETTER U WITH BREVE = LATIN CAPITAL LETTER U + COMBINING BREVE
	gsub(/\xE6\x61/, "\\&#x0103;"); 		# LATIN SMALL LETTER A WITH BREVE = LATIN SMALL LETTER A + COMBINING BREVE
	gsub(/\xE6\x65/, "\\&#x0115;"); 		# LATIN SMALL LETTER E WITH BREVE = LATIN SMALL LETTER E + COMBINING BREVE
	gsub(/\xE6\x67/, "\\&#x011F;"); 		# LATIN SMALL LETTER G WITH BREVE = LATIN SMALL LETTER G + COMBINING BREVE
	gsub(/\xE6\x69/, "\\&#x012D;"); 		# LATIN SMALL LETTER I WITH BREVE = LATIN SMALL LETTER I + COMBINING BREVE
	gsub(/\xE6\x6F/, "\\&#x014F;"); 		# LATIN SMALL LETTER O WITH BREVE = LATIN SMALL LETTER O + COMBINING BREVE
	gsub(/\xE6\x75/, "\\&#x016D;"); 		# LATIN SMALL LETTER U WITH BREVE = LATIN SMALL LETTER U + COMBINING BREVE
	gsub(/\xE6/, "\\&#x0306;"); 		#combining breve
}

if      (/\xE7/) {
	gsub(/\xE7\x42/, "\\&#x1E02;"); 		# LATIN CAPITAL LETTER B WITH DOT ABOVE = LATIN CAPITAL LETTER B + COMBINING DOT ABOVE
	gsub(/\xE7\x43/, "\\&#x010A;"); 		# LATIN CAPITAL LETTER C WITH DOT ABOVE = LATIN CAPITAL LETTER C + COMBINING DOT ABOVE
	gsub(/\xE7\x44/, "\\&#x1E0A;"); 		# LATIN CAPITAL LETTER D WITH DOT ABOVE = LATIN CAPITAL LETTER D + COMBINING DOT ABOVE
	gsub(/\xE7\x45/, "\\&#x0116;"); 		# LATIN CAPITAL LETTER E WITH DOT ABOVE = LATIN CAPITAL LETTER E + COMBINING DOT ABOVE
	gsub(/\xE7\x46/, "\\&#x1E1E;"); 		# LATIN CAPITAL LETTER F WITH DOT ABOVE = LATIN CAPITAL LETTER F + COMBINING DOT ABOVE
	gsub(/\xE7\x47/, "\\&#x0120;"); 		# LATIN CAPITAL LETTER G WITH DOT ABOVE = LATIN CAPITAL LETTER G + COMBINING DOT ABOVE
	gsub(/\xE7\x48/, "\\&#x1E22;"); 		# LATIN CAPITAL LETTER H WITH DOT ABOVE = LATIN CAPITAL LETTER H + COMBINING DOT ABOVE
	gsub(/\xE7\x49/, "\\&#x0130;"); 		# LATIN CAPITAL LETTER I WITH DOT ABOVE = LATIN CAPITAL LETTER I + COMBINING DOT ABOVE
	gsub(/\xE7\x4D/, "\\&#x1E40;"); 		# LATIN CAPITAL LETTER M WITH DOT ABOVE = LATIN CAPITAL LETTER M + COMBINING DOT ABOVE
	gsub(/\xE7\x4E/, "\\&#x1E44;"); 		# LATIN CAPITAL LETTER N WITH DOT ABOVE = LATIN CAPITAL LETTER N + COMBINING DOT ABOVE
	gsub(/\xE7\x50/, "\\&#x1E56;"); 		# LATIN CAPITAL LETTER P WITH DOT ABOVE = LATIN CAPITAL LETTER P + COMBINING DOT ABOVE
	gsub(/\xE7\x52/, "\\&#x1E58;"); 		# LATIN CAPITAL LETTER R WITH DOT ABOVE = LATIN CAPITAL LETTER R + COMBINING DOT ABOVE
	gsub(/\xE7\x53/, "\\&#x1E60;"); 		# LATIN CAPITAL LETTER S WITH DOT ABOVE = LATIN CAPITAL LETTER S + COMBINING DOT ABOVE
	gsub(/\xE7\x54/, "\\&#x1E6A;"); 		# LATIN CAPITAL LETTER T WITH DOT ABOVE = LATIN CAPITAL LETTER T + COMBINING DOT ABOVE
	gsub(/\xE7\x57/, "\\&#x1E86;"); 		# LATIN CAPITAL LETTER W WITH DOT ABOVE = LATIN CAPITAL LETTER W + COMBINING DOT ABOVE
	gsub(/\xE7\x58/, "\\&#x1E8A;"); 		# LATIN CAPITAL LETTER X WITH DOT ABOVE = LATIN CAPITAL LETTER X + COMBINING DOT ABOVE
	gsub(/\xE7\x59/, "\\&#x1E8E;"); 		# LATIN CAPITAL LETTER Y WITH DOT ABOVE = LATIN CAPITAL LETTER Y + COMBINING DOT ABOVE
	gsub(/\xE7\x5A/, "\\&#x017B;"); 		# LATIN CAPITAL LETTER Z WITH DOT ABOVE = LATIN CAPITAL LETTER Z + COMBINING DOT ABOVE
	gsub(/\xE7\x62/, "\\&#x1E03;"); 		# LATIN SMALL LETTER B WITH DOT ABOVE = LATIN SMALL LETTER B + COMBINING DOT ABOVE
	gsub(/\xE7\x63/, "\\&#x010B;"); 		# LATIN SMALL LETTER C WITH DOT ABOVE = LATIN SMALL LETTER C + COMBINING DOT ABOVE
	gsub(/\xE7\x64/, "\\&#x1E0B;"); 		# LATIN SMALL LETTER D WITH DOT ABOVE = LATIN SMALL LETTER D + COMBINING DOT ABOVE
	gsub(/\xE7\x65/, "\\&#x0117;"); 		# LATIN SMALL LETTER E WITH DOT ABOVE = LATIN SMALL LETTER E + COMBINING DOT ABOVE
	gsub(/\xE7\x66/, "\\&#x1E1F;"); 		# LATIN SMALL LETTER F WITH DOT ABOVE = LATIN SMALL LETTER F + COMBINING DOT ABOVE
	gsub(/\xE7\x67/, "\\&#x0121;"); 		# LATIN SMALL LETTER G WITH DOT ABOVE = LATIN SMALL LETTER G + COMBINING DOT ABOVE
	gsub(/\xE7\x68/, "\\&#x1E23;"); 		# LATIN SMALL LETTER H WITH DOT ABOVE = LATIN SMALL LETTER H + COMBINING DOT ABOVE
	gsub(/\xE7\x6D/, "\\&#x1E41;"); 		# LATIN SMALL LETTER M WITH DOT ABOVE = LATIN SMALL LETTER M + COMBINING DOT ABOVE
	gsub(/\xE7\x6E/, "\\&#x1E45;"); 		# LATIN SMALL LETTER N WITH DOT ABOVE = LATIN SMALL LETTER N + COMBINING DOT ABOVE
	gsub(/\xE7\x70/, "\\&#x1E57;"); 		# LATIN SMALL LETTER P WITH DOT ABOVE = LATIN SMALL LETTER P + COMBINING DOT ABOVE
	gsub(/\xE7\x72/, "\\&#x1E59;"); 		# LATIN SMALL LETTER R WITH DOT ABOVE = LATIN SMALL LETTER R + COMBINING DOT ABOVE
	gsub(/\xE7\x73/, "\\&#x1E61;"); 		# LATIN SMALL LETTER S WITH DOT ABOVE = LATIN SMALL LETTER S + COMBINING DOT ABOVE
	gsub(/\xE7\x74/, "\\&#x1E6B;"); 		# LATIN SMALL LETTER T WITH DOT ABOVE = LATIN SMALL LETTER T + COMBINING DOT ABOVE
	gsub(/\xE7\x77/, "\\&#x1E87;"); 		# LATIN SMALL LETTER W WITH DOT ABOVE = LATIN SMALL LETTER W + COMBINING DOT ABOVE
	gsub(/\xE7\x78/, "\\&#x1E8B;"); 		# LATIN SMALL LETTER X WITH DOT ABOVE = LATIN SMALL LETTER X + COMBINING DOT ABOVE
	gsub(/\xE7\x79/, "\\&#x1E8F;"); 		# LATIN SMALL LETTER Y WITH DOT ABOVE = LATIN SMALL LETTER Y + COMBINING DOT ABOVE
	gsub(/\xE7\x7A/, "\\&#x017C;"); 		# LATIN SMALL LETTER Z WITH DOT ABOVE = LATIN SMALL LETTER Z + COMBINING DOT ABOVE
	gsub(/\xE7/, "\\&#x0307;"); 		#combining dot above
}

if      (/\xE8/) {
	gsub(/\xE8\x41/, "\\&#x00C4;"); 		# LATIN CAPITAL LETTER A WITH DIAERESIS = LATIN CAPITAL LETTER A + COMBINING DIAERESIS
	gsub(/\xE8\x45/, "\\&#x00CB;"); 		# LATIN CAPITAL LETTER E WITH DIAERESIS = LATIN CAPITAL LETTER E + COMBINING DIAERESIS
	gsub(/\xE8\x48/, "\\&#x1E26;"); 		# LATIN CAPITAL LETTER H WITH DIAERESIS = LATIN CAPITAL LETTER H + COMBINING DIAERESIS
	gsub(/\xE8\x49/, "\\&#x00CF;"); 		# LATIN CAPITAL LETTER I WITH DIAERESIS = LATIN CAPITAL LETTER I + COMBINING DIAERESIS
	gsub(/\xE8\x4F/, "\\&#x00D6;"); 		# LATIN CAPITAL LETTER O WITH DIAERESIS = LATIN CAPITAL LETTER O + COMBINING DIAERESIS
	gsub(/\xE8\x55/, "\\&#x00DC;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS = LATIN CAPITAL LETTER U + COMBINING DIAERESIS
	gsub(/\xE8\x57/, "\\&#x1E84;"); 		# LATIN CAPITAL LETTER W WITH DIAERESIS = LATIN CAPITAL LETTER W + COMBINING DIAERESIS
	gsub(/\xE8\x58/, "\\&#x1E8C;"); 		# LATIN CAPITAL LETTER X WITH DIAERESIS = LATIN CAPITAL LETTER X + COMBINING DIAERESIS
	gsub(/\xE8\x59/, "\\&#x0178;"); 		# LATIN CAPITAL LETTER Y WITH DIAERESIS = LATIN CAPITAL LETTER Y + COMBINING DIAERESIS
	gsub(/\xE8\x61/, "\\&#x00E4;"); 		# LATIN SMALL LETTER A WITH DIAERESIS = LATIN SMALL LETTER A + COMBINING DIAERESIS
	gsub(/\xE8\x65/, "\\&#x00EB;"); 		# LATIN SMALL LETTER E WITH DIAERESIS = LATIN SMALL LETTER E + COMBINING DIAERESIS
	gsub(/\xE8\x68/, "\\&#x1E27;"); 		# LATIN SMALL LETTER H WITH DIAERESIS = LATIN SMALL LETTER H + COMBINING DIAERESIS
	gsub(/\xE8\x69/, "\\&#x00EF;"); 		# LATIN SMALL LETTER I WITH DIAERESIS = LATIN SMALL LETTER I + COMBINING DIAERESIS
	gsub(/\xE8\x6F/, "\\&#x00F6;"); 		# LATIN SMALL LETTER O WITH DIAERESIS = LATIN SMALL LETTER O + COMBINING DIAERESIS
	gsub(/\xE8\x74/, "\\&#x1E97;"); 		# LATIN SMALL LETTER T WITH DIAERESIS = LATIN SMALL LETTER T + COMBINING DIAERESIS
	gsub(/\xE8\x75/, "\\&#x00FC;"); 		# LATIN SMALL LETTER U WITH DIAERESIS = LATIN SMALL LETTER U + COMBINING DIAERESIS
	gsub(/\xE8\x77/, "\\&#x1E85;"); 		# LATIN SMALL LETTER W WITH DIAERESIS = LATIN SMALL LETTER W + COMBINING DIAERESIS
	gsub(/\xE8\x78/, "\\&#x1E8D;"); 		# LATIN SMALL LETTER X WITH DIAERESIS = LATIN SMALL LETTER X + COMBINING DIAERESIS
	gsub(/\xE8\x79/, "\\&#x00FF;"); 		# LATIN SMALL LETTER Y WITH DIAERESIS = LATIN SMALL LETTER Y + COMBINING DIAERESIS
	gsub(/\xE8/, "\\&#x0308;"); 		#combining diaeresis
}

if      (/\xE9/) {
	gsub(/\xE9\x41/, "\\&#x01CD;"); 		# LATIN CAPITAL LETTER A WITH CARON = LATIN CAPITAL LETTER A + COMBINING CARON
	gsub(/\xE9\x43/, "\\&#x010C;"); 		# LATIN CAPITAL LETTER C WITH CARON = LATIN CAPITAL LETTER C + COMBINING CARON
	gsub(/\xE9\x44/, "\\&#x010E;"); 		# LATIN CAPITAL LETTER D WITH CARON = LATIN CAPITAL LETTER D + COMBINING CARON
	gsub(/\xE9\x45/, "\\&#x011A;"); 		# LATIN CAPITAL LETTER E WITH CARON = LATIN CAPITAL LETTER E + COMBINING CARON
	gsub(/\xE9\x47/, "\\&#x01E6;"); 		# LATIN CAPITAL LETTER G WITH CARON = LATIN CAPITAL LETTER G + COMBINING CARON
	gsub(/\xE9\x49/, "\\&#x01CF;"); 		# LATIN CAPITAL LETTER I WITH CARON = LATIN CAPITAL LETTER I + COMBINING CARON
	gsub(/\xE9\x4B/, "\\&#x01E8;"); 		# LATIN CAPITAL LETTER K WITH CARON = LATIN CAPITAL LETTER K + COMBINING CARON
	gsub(/\xE9\x4C/, "\\&#x013D;"); 		# LATIN CAPITAL LETTER L WITH CARON = LATIN CAPITAL LETTER L + COMBINING CARON
	gsub(/\xE9\x4E/, "\\&#x0147;"); 		# LATIN CAPITAL LETTER N WITH CARON = LATIN CAPITAL LETTER N + COMBINING CARON
	gsub(/\xE9\x4F/, "\\&#x01D1;"); 		# LATIN CAPITAL LETTER O WITH CARON = LATIN CAPITAL LETTER O + COMBINING CARON
	gsub(/\xE9\x52/, "\\&#x0158;"); 		# LATIN CAPITAL LETTER R WITH CARON = LATIN CAPITAL LETTER R + COMBINING CARON
	gsub(/\xE9\x53/, "\\&#x0160;"); 		# LATIN CAPITAL LETTER S WITH CARON = LATIN CAPITAL LETTER S + COMBINING CARON
	gsub(/\xE9\x54/, "\\&#x0164;"); 		# LATIN CAPITAL LETTER T WITH CARON = LATIN CAPITAL LETTER T + COMBINING CARON
	gsub(/\xE9\x55/, "\\&#x01D3;"); 		# LATIN CAPITAL LETTER U WITH CARON = LATIN CAPITAL LETTER U + COMBINING CARON
	gsub(/\xE9\x5A/, "\\&#x017D;"); 		# LATIN CAPITAL LETTER Z WITH CARON = LATIN CAPITAL LETTER Z + COMBINING CARON
	gsub(/\xE9\x61/, "\\&#x01CE;"); 		# LATIN SMALL LETTER A WITH CARON = LATIN SMALL LETTER A + COMBINING CARON
	gsub(/\xE9\x63/, "\\&#x010D;"); 		# LATIN SMALL LETTER C WITH CARON = LATIN SMALL LETTER C + COMBINING CARON
	gsub(/\xE9\x64/, "\\&#x010F;"); 		# LATIN SMALL LETTER D WITH CARON = LATIN SMALL LETTER D + COMBINING CARON
	gsub(/\xE9\x65/, "\\&#x011B;"); 		# LATIN SMALL LETTER E WITH CARON = LATIN SMALL LETTER E + COMBINING CARON
	gsub(/\xE9\x67/, "\\&#x01E7;"); 		# LATIN SMALL LETTER G WITH CARON = LATIN SMALL LETTER G + COMBINING CARON
	gsub(/\xE9\x69/, "\\&#x01D0;"); 		# LATIN SMALL LETTER I WITH CARON = LATIN SMALL LETTER I + COMBINING CARON
	gsub(/\xE9\x6A/, "\\&#x01F0;"); 		# LATIN SMALL LETTER J WITH CARON = LATIN SMALL LETTER J + COMBINING CARON
	gsub(/\xE9\x6B/, "\\&#x01E9;"); 		# LATIN SMALL LETTER K WITH CARON = LATIN SMALL LETTER K + COMBINING CARON
	gsub(/\xE9\x6C/, "\\&#x013E;"); 		# LATIN SMALL LETTER L WITH CARON = LATIN SMALL LETTER L + COMBINING CARON
	gsub(/\xE9\x6E/, "\\&#x0148;"); 		# LATIN SMALL LETTER N WITH CARON = LATIN SMALL LETTER N + COMBINING CARON
	gsub(/\xE9\x6F/, "\\&#x01D2;"); 		# LATIN SMALL LETTER O WITH CARON = LATIN SMALL LETTER O + COMBINING CARON
	gsub(/\xE9\x72/, "\\&#x0159;"); 		# LATIN SMALL LETTER R WITH CARON = LATIN SMALL LETTER R + COMBINING CARON
	gsub(/\xE9\x73/, "\\&#x0161;"); 		# LATIN SMALL LETTER S WITH CARON = LATIN SMALL LETTER S + COMBINING CARON
	gsub(/\xE9\x74/, "\\&#x0165;"); 		# LATIN SMALL LETTER T WITH CARON = LATIN SMALL LETTER T + COMBINING CARON
	gsub(/\xE9\x75/, "\\&#x01D4;"); 		# LATIN SMALL LETTER U WITH CARON = LATIN SMALL LETTER U + COMBINING CARON
	gsub(/\xE9\x7A/, "\\&#x017E;"); 		# LATIN SMALL LETTER Z WITH CARON = LATIN SMALL LETTER Z + COMBINING CARON
	gsub(/\xE9/, "\\&#x030C;"); 		#combining caron
}

if      (/\xEA/) {
	gsub(/\xEA\x41/, "\\&#x00C5;"); 		# LATIN CAPITAL LETTER A WITH RING ABOVE = LATIN CAPITAL LETTER A + COMBINING RING ABOVE
	gsub(/\xEA\x55/, "\\&#x016E;"); 		# LATIN CAPITAL LETTER U WITH RING ABOVE = LATIN CAPITAL LETTER U + COMBINING RING ABOVE
	gsub(/\xEA\x61/, "\\&#x00E5;"); 		# LATIN SMALL LETTER A WITH RING ABOVE = LATIN SMALL LETTER A + COMBINING RING ABOVE
	gsub(/\xEA\x75/, "\\&#x016F;"); 		# LATIN SMALL LETTER U WITH RING ABOVE = LATIN SMALL LETTER U + COMBINING RING ABOVE
	gsub(/\xEA\x77/, "\\&#x1E98;"); 		# LATIN SMALL LETTER W WITH RING ABOVE = LATIN SMALL LETTER W + COMBINING RING ABOVE
	gsub(/\xEA\x79/, "\\&#x1E99;"); 		# LATIN SMALL LETTER Y WITH RING ABOVE = LATIN SMALL LETTER Y + COMBINING RING ABOVE
	gsub(/\xEA/, "\\&#x030A;"); 		#combining ring above
}

gsub(/\xEB/, "\\&#xFE20;"); 		#combining ligature left half

gsub(/\xEC/, "\\&#xFE21;"); 		#combining ligature right half

gsub(/\xED/, "\\&#x0315;"); 		#combining comma above right

if      (/\xEE/) {
	gsub(/\xEE\x4F/, "\\&#x0150;"); 		# LATIN CAPITAL LETTER O WITH DOUBLE ACUTE = LATIN CAPITAL LETTER O + COMBINING DOUBLE ACUTE ACCENT
	gsub(/\xEE\x55/, "\\&#x0170;"); 		# LATIN CAPITAL LETTER U WITH DOUBLE ACUTE = LATIN CAPITAL LETTER U + COMBINING DOUBLE ACUTE ACCENT
	gsub(/\xEE\x6F/, "\\&#x0151;"); 		# LATIN SMALL LETTER O WITH DOUBLE ACUTE = LATIN SMALL LETTER O + COMBINING DOUBLE ACUTE ACCENT
	gsub(/\xEE\x75/, "\\&#x0171;"); 		# LATIN SMALL LETTER U WITH DOUBLE ACUTE = LATIN SMALL LETTER U + COMBINING DOUBLE ACUTE ACCENT
	gsub(/\xEE/, "\\&#x030B;"); 		#combining double acute accent
}

gsub(/\xEF/, "\\&#x0310;"); 		#combining candrabindu

if      (/\xF0/) {
	gsub(/\xF0\x43/, "\\&#x00C7;"); 		# LATIN CAPITAL LETTER C WITH CEDILLA = LATIN CAPITAL LETTER C + COMBINING CEDILLA
	gsub(/\xF0\x44/, "\\&#x1E10;"); 		# LATIN CAPITAL LETTER D WITH CEDILLA = LATIN CAPITAL LETTER D + COMBINING CEDILLA
	gsub(/\xF0\x47/, "\\&#x0122;"); 		# LATIN CAPITAL LETTER G WITH CEDILLA = LATIN CAPITAL LETTER G + COMBINING CEDILLA
	gsub(/\xF0\x48/, "\\&#x1E28;"); 		# LATIN CAPITAL LETTER H WITH CEDILLA = LATIN CAPITAL LETTER H + COMBINING CEDILLA
	gsub(/\xF0\x4B/, "\\&#x0136;"); 		# LATIN CAPITAL LETTER K WITH CEDILLA = LATIN CAPITAL LETTER K + COMBINING CEDILLA
	gsub(/\xF0\x4C/, "\\&#x013B;"); 		# LATIN CAPITAL LETTER L WITH CEDILLA = LATIN CAPITAL LETTER L + COMBINING CEDILLA
	gsub(/\xF0\x4E/, "\\&#x0145;"); 		# LATIN CAPITAL LETTER N WITH CEDILLA = LATIN CAPITAL LETTER N + COMBINING CEDILLA
	gsub(/\xF0\x52/, "\\&#x0156;"); 		# LATIN CAPITAL LETTER R WITH CEDILLA = LATIN CAPITAL LETTER R + COMBINING CEDILLA
	gsub(/\xF0\x53/, "\\&#x015E;"); 		# LATIN CAPITAL LETTER S WITH CEDILLA = LATIN CAPITAL LETTER S + COMBINING CEDILLA
	gsub(/\xF0\x54/, "\\&#x0162;"); 		# LATIN CAPITAL LETTER T WITH CEDILLA = LATIN CAPITAL LETTER T + COMBINING CEDILLA
	gsub(/\xF0\x63/, "\\&#x00E7;"); 		# LATIN SMALL LETTER C WITH CEDILLA = LATIN SMALL LETTER C + COMBINING CEDILLA
	gsub(/\xF0\x64/, "\\&#x1E11;"); 		# LATIN SMALL LETTER D WITH CEDILLA = LATIN SMALL LETTER D + COMBINING CEDILLA
	gsub(/\xF0\x67/, "\\&#x0123;"); 		# LATIN SMALL LETTER G WITH CEDILLA = LATIN SMALL LETTER G + COMBINING CEDILLA
	gsub(/\xF0\x68/, "\\&#x1E29;"); 		# LATIN SMALL LETTER H WITH CEDILLA = LATIN SMALL LETTER H + COMBINING CEDILLA
	gsub(/\xF0\x6B/, "\\&#x0137;"); 		# LATIN SMALL LETTER K WITH CEDILLA = LATIN SMALL LETTER K + COMBINING CEDILLA
	gsub(/\xF0\x6C/, "\\&#x013C;"); 		# LATIN SMALL LETTER L WITH CEDILLA = LATIN SMALL LETTER L + COMBINING CEDILLA
	gsub(/\xF0\x6E/, "\\&#x0146;"); 		# LATIN SMALL LETTER N WITH CEDILLA = LATIN SMALL LETTER N + COMBINING CEDILLA
	gsub(/\xF0\x72/, "\\&#x0157;"); 		# LATIN SMALL LETTER R WITH CEDILLA = LATIN SMALL LETTER R + COMBINING CEDILLA
	gsub(/\xF0\x73/, "\\&#x015F;"); 		# LATIN SMALL LETTER S WITH CEDILLA = LATIN SMALL LETTER S + COMBINING CEDILLA
	gsub(/\xF0\x74/, "\\&#x0163;"); 		# LATIN SMALL LETTER T WITH CEDILLA = LATIN SMALL LETTER T + COMBINING CEDILLA
	gsub(/\xF0/, "\\&#x0327;"); 		#combining cedilla
}

if      (/\xF1/) {
	gsub(/\xF1\x41/, "\\&#x0104;"); 		# LATIN CAPITAL LETTER A WITH OGONEK = LATIN CAPITAL LETTER A + COMBINING OGONEK
	gsub(/\xF1\x45/, "\\&#x0118;"); 		# LATIN CAPITAL LETTER E WITH OGONEK = LATIN CAPITAL LETTER E + COMBINING OGONEK
	gsub(/\xF1\x49/, "\\&#x012E;"); 		# LATIN CAPITAL LETTER I WITH OGONEK = LATIN CAPITAL LETTER I + COMBINING OGONEK
	gsub(/\xF1\x4F/, "\\&#x01EA;"); 		# LATIN CAPITAL LETTER O WITH OGONEK = LATIN CAPITAL LETTER O + COMBINING OGONEK
	gsub(/\xF1\x55/, "\\&#x0172;"); 		# LATIN CAPITAL LETTER U WITH OGONEK = LATIN CAPITAL LETTER U + COMBINING OGONEK
	gsub(/\xF1\x61/, "\\&#x0105;"); 		# LATIN SMALL LETTER A WITH OGONEK = LATIN SMALL LETTER A + COMBINING OGONEK
	gsub(/\xF1\x65/, "\\&#x0119;"); 		# LATIN SMALL LETTER E WITH OGONEK = LATIN SMALL LETTER E + COMBINING OGONEK
	gsub(/\xF1\x69/, "\\&#x012F;"); 		# LATIN SMALL LETTER I WITH OGONEK = LATIN SMALL LETTER I + COMBINING OGONEK
	gsub(/\xF1\x6F/, "\\&#x01EB;"); 		# LATIN SMALL LETTER O WITH OGONEK = LATIN SMALL LETTER O + COMBINING OGONEK
	gsub(/\xF1\x75/, "\\&#x0173;"); 		# LATIN SMALL LETTER U WITH OGONEK = LATIN SMALL LETTER U + COMBINING OGONEK
	gsub(/\xF1/, "\\&#x0328;"); 		#combining ogonek
}

if      (/\xF2/) {
	gsub(/\xF2\x41/, "\\&#x1EA0;"); 		# LATIN CAPITAL LETTER A WITH DOT BELOW = LATIN CAPITAL LETTER A + COMBINING DOT BELOW
	gsub(/\xF2\x42/, "\\&#x1E04;"); 		# LATIN CAPITAL LETTER B WITH DOT BELOW = LATIN CAPITAL LETTER B + COMBINING DOT BELOW
	gsub(/\xF2\x44/, "\\&#x1E0C;"); 		# LATIN CAPITAL LETTER D WITH DOT BELOW = LATIN CAPITAL LETTER D + COMBINING DOT BELOW
	gsub(/\xF2\x45/, "\\&#x1EB8;"); 		# LATIN CAPITAL LETTER E WITH DOT BELOW = LATIN CAPITAL LETTER E + COMBINING DOT BELOW
	gsub(/\xF2\x48/, "\\&#x1E24;"); 		# LATIN CAPITAL LETTER H WITH DOT BELOW = LATIN CAPITAL LETTER H + COMBINING DOT BELOW
	gsub(/\xF2\x49/, "\\&#x1ECA;"); 		# LATIN CAPITAL LETTER I WITH DOT BELOW = LATIN CAPITAL LETTER I + COMBINING DOT BELOW
	gsub(/\xF2\x4B/, "\\&#x1E32;"); 		# LATIN CAPITAL LETTER K WITH DOT BELOW = LATIN CAPITAL LETTER K + COMBINING DOT BELOW
	gsub(/\xF2\x4C/, "\\&#x1E36;"); 		# LATIN CAPITAL LETTER L WITH DOT BELOW = LATIN CAPITAL LETTER L + COMBINING DOT BELOW
	gsub(/\xF2\x4D/, "\\&#x1E42;"); 		# LATIN CAPITAL LETTER M WITH DOT BELOW = LATIN CAPITAL LETTER M + COMBINING DOT BELOW
	gsub(/\xF2\x4E/, "\\&#x1E46;"); 		# LATIN CAPITAL LETTER N WITH DOT BELOW = LATIN CAPITAL LETTER N + COMBINING DOT BELOW
	gsub(/\xF2\x4F/, "\\&#x1ECC;"); 		# LATIN CAPITAL LETTER O WITH DOT BELOW = LATIN CAPITAL LETTER O + COMBINING DOT BELOW
	gsub(/\xF2\x52/, "\\&#x1E5A;"); 		# LATIN CAPITAL LETTER R WITH DOT BELOW = LATIN CAPITAL LETTER R + COMBINING DOT BELOW
	gsub(/\xF2\x53/, "\\&#x1E62;"); 		# LATIN CAPITAL LETTER S WITH DOT BELOW = LATIN CAPITAL LETTER S + COMBINING DOT BELOW
	gsub(/\xF2\x54/, "\\&#x1E6C;"); 		# LATIN CAPITAL LETTER T WITH DOT BELOW = LATIN CAPITAL LETTER T + COMBINING DOT BELOW
	gsub(/\xF2\x55/, "\\&#x1EE4;"); 		# LATIN CAPITAL LETTER U WITH DOT BELOW = LATIN CAPITAL LETTER U + COMBINING DOT BELOW
	gsub(/\xF2\x56/, "\\&#x1E7E;"); 		# LATIN CAPITAL LETTER V WITH DOT BELOW = LATIN CAPITAL LETTER V + COMBINING DOT BELOW
	gsub(/\xF2\x57/, "\\&#x1E88;"); 		# LATIN CAPITAL LETTER W WITH DOT BELOW = LATIN CAPITAL LETTER W + COMBINING DOT BELOW
	gsub(/\xF2\x59/, "\\&#x1EF4;"); 		# LATIN CAPITAL LETTER Y WITH DOT BELOW = LATIN CAPITAL LETTER Y + COMBINING DOT BELOW
	gsub(/\xF2\x5A/, "\\&#x1E92;"); 		# LATIN CAPITAL LETTER Z WITH DOT BELOW = LATIN CAPITAL LETTER Z + COMBINING DOT BELOW
	gsub(/\xF2\x61/, "\\&#x1EA1;"); 		# LATIN SMALL LETTER A WITH DOT BELOW = LATIN SMALL LETTER A + COMBINING DOT BELOW
	gsub(/\xF2\x62/, "\\&#x1E05;"); 		# LATIN SMALL LETTER B WITH DOT BELOW = LATIN SMALL LETTER B + COMBINING DOT BELOW
	gsub(/\xF2\x64/, "\\&#x1E0D;"); 		# LATIN SMALL LETTER D WITH DOT BELOW = LATIN SMALL LETTER D + COMBINING DOT BELOW
	gsub(/\xF2\x65/, "\\&#x1EB9;"); 		# LATIN SMALL LETTER E WITH DOT BELOW = LATIN SMALL LETTER E + COMBINING DOT BELOW
	gsub(/\xF2\x68/, "\\&#x1E25;"); 		# LATIN SMALL LETTER H WITH DOT BELOW = LATIN SMALL LETTER H + COMBINING DOT BELOW
	gsub(/\xF2\x69/, "\\&#x1ECB;"); 		# LATIN SMALL LETTER I WITH DOT BELOW = LATIN SMALL LETTER I + COMBINING DOT BELOW
	gsub(/\xF2\x6B/, "\\&#x1E33;"); 		# LATIN SMALL LETTER K WITH DOT BELOW = LATIN SMALL LETTER K + COMBINING DOT BELOW
	gsub(/\xF2\x6C/, "\\&#x1E37;"); 		# LATIN SMALL LETTER L WITH DOT BELOW = LATIN SMALL LETTER L + COMBINING DOT BELOW
	gsub(/\xF2\x6D/, "\\&#x1E43;"); 		# LATIN SMALL LETTER M WITH DOT BELOW = LATIN SMALL LETTER M + COMBINING DOT BELOW
	gsub(/\xF2\x6E/, "\\&#x1E47;"); 		# LATIN SMALL LETTER N WITH DOT BELOW = LATIN SMALL LETTER N + COMBINING DOT BELOW
	gsub(/\xF2\x6F/, "\\&#x1ECD;"); 		# LATIN SMALL LETTER O WITH DOT BELOW = LATIN SMALL LETTER O + COMBINING DOT BELOW
	gsub(/\xF2\x72/, "\\&#x1E5B;"); 		# LATIN SMALL LETTER R WITH DOT BELOW = LATIN SMALL LETTER R + COMBINING DOT BELOW
	gsub(/\xF2\x73/, "\\&#x1E63;"); 		# LATIN SMALL LETTER S WITH DOT BELOW = LATIN SMALL LETTER S + COMBINING DOT BELOW
	gsub(/\xF2\x74/, "\\&#x1E6D;"); 		# LATIN SMALL LETTER T WITH DOT BELOW = LATIN SMALL LETTER T + COMBINING DOT BELOW
	gsub(/\xF2\x75/, "\\&#x1EE5;"); 		# LATIN SMALL LETTER U WITH DOT BELOW = LATIN SMALL LETTER U + COMBINING DOT BELOW
	gsub(/\xF2\x76/, "\\&#x1E7F;"); 		# LATIN SMALL LETTER V WITH DOT BELOW = LATIN SMALL LETTER V + COMBINING DOT BELOW
	gsub(/\xF2\x77/, "\\&#x1E89;"); 		# LATIN SMALL LETTER W WITH DOT BELOW = LATIN SMALL LETTER W + COMBINING DOT BELOW
	gsub(/\xF2\x79/, "\\&#x1EF5;"); 		# LATIN SMALL LETTER Y WITH DOT BELOW = LATIN SMALL LETTER Y + COMBINING DOT BELOW
	gsub(/\xF2\x7A/, "\\&#x1E93;"); 		# LATIN SMALL LETTER Z WITH DOT BELOW = LATIN SMALL LETTER Z + COMBINING DOT BELOW
	gsub(/\xF2/, "\\&#x0323;"); 		#combining dot below
}

if      (/\xF3/) {
	gsub(/\xF3\x55/, "\\&#x1E72;"); 		# LATIN CAPITAL LETTER U WITH DIAERESIS BELOW = LATIN CAPITAL LETTER U + COMBINING DIAERESIS BELOW
	gsub(/\xF3\x75/, "\\&#x1E73;"); 		# LATIN SMALL LETTER U WITH DIAERESIS BELOW = LATIN SMALL LETTER U + COMBINING DIAERESIS BELOW
	gsub(/\xF3/, "\\&#x0324;"); 		#combining diaeresis below
}

if      (/\xF4/) {
	gsub(/\xF4\x41/, "\\&#x1E00;"); 		# LATIN CAPITAL LETTER A WITH RING BELOW = LATIN CAPITAL LETTER A + COMBINING RING BELOW
	gsub(/\xF4\x61/, "\\&#x1E01;"); 		# LATIN SMALL LETTER A WITH RING BELOW = LATIN SMALL LETTER A + COMBINING RING BELOW
	gsub(/\xF4/, "\\&#x0325;"); 		#combining ring below
}

gsub(/\xF5/, "\\&#x0333;"); 		#combining double low line

if      (/\xF6/) {
	gsub(/\xF6\x42/, "\\&#x1E06;"); 		# LATIN CAPITAL LETTER B WITH LINE BELOW = LATIN CAPITAL LETTER B + COMBINING LOW LINE
	gsub(/\xF6\x44/, "\\&#x1E0E;"); 		# LATIN CAPITAL LETTER D WITH LINE BELOW = LATIN CAPITAL LETTER D + COMBINING LOW LINE
	gsub(/\xF6\x4B/, "\\&#x1E34;"); 		# LATIN CAPITAL LETTER K WITH LINE BELOW = LATIN CAPITAL LETTER K + COMBINING LOW LINE
	gsub(/\xF6\x4C/, "\\&#x1E3A;"); 		# LATIN CAPITAL LETTER L WITH LINE BELOW = LATIN CAPITAL LETTER L + COMBINING LOW LINE
	gsub(/\xF6\x4E/, "\\&#x1E48;"); 		# LATIN CAPITAL LETTER N WITH LINE BELOW = LATIN CAPITAL LETTER N + COMBINING LOW LINE
	gsub(/\xF6\x52/, "\\&#x1E5E;"); 		# LATIN CAPITAL LETTER R WITH LINE BELOW = LATIN CAPITAL LETTER R + COMBINING LOW LINE
	gsub(/\xF6\x54/, "\\&#x1E6E;"); 		# LATIN CAPITAL LETTER T WITH LINE BELOW = LATIN CAPITAL LETTER T + COMBINING LOW LINE
	gsub(/\xF6\x5A/, "\\&#x1E94;"); 		# LATIN CAPITAL LETTER Z WITH LINE BELOW = LATIN CAPITAL LETTER Z + COMBINING LOW LINE
	gsub(/\xF6\x62/, "\\&#x1E07;"); 		# LATIN SMALL LETTER B WITH LINE BELOW = LATIN SMALL LETTER B + COMBINING LOW LINE
	gsub(/\xF6\x64/, "\\&#x1E0F;"); 		# LATIN SMALL LETTER D WITH LINE BELOW = LATIN SMALL LETTER D + COMBINING LOW LINE
	gsub(/\xF6\x68/, "\\&#x1E96;"); 		# LATIN SMALL LETTER H WITH LINE BELOW = LATIN SMALL LETTER H + COMBINING LOW LINE
	gsub(/\xF6\x6B/, "\\&#x1E35;"); 		# LATIN SMALL LETTER K WITH LINE BELOW = LATIN SMALL LETTER K + COMBINING LOW LINE
	gsub(/\xF6\x6C/, "\\&#x1E3B;"); 		# LATIN SMALL LETTER L WITH LINE BELOW = LATIN SMALL LETTER L + COMBINING LOW LINE
	gsub(/\xF6\x6E/, "\\&#x1E49;"); 		# LATIN SMALL LETTER N WITH LINE BELOW = LATIN SMALL LETTER N + COMBINING LOW LINE
	gsub(/\xF6\x72/, "\\&#x1E5F;"); 		# LATIN SMALL LETTER R WITH LINE BELOW = LATIN SMALL LETTER R + COMBINING LOW LINE
	gsub(/\xF6\x74/, "\\&#x1E6F;"); 		# LATIN SMALL LETTER T WITH LINE BELOW = LATIN SMALL LETTER T + COMBINING LOW LINE
	gsub(/\xF6\x7A/, "\\&#x1E95;"); 		# LATIN SMALL LETTER Z WITH LINE BELOW = LATIN SMALL LETTER Z + COMBINING LOW LINE
	gsub(/\xF6/, "\\&#x0332;"); 		#combining low line
}

gsub(/\xF7/, "\\&#x0326;"); 		#combining comma below

gsub(/\xF8/, "\\&#x031C;"); 		#combining left half ring below

if      (/\xF9/) {
	gsub(/\xF9\x48/, "\\&#x1E2A;"); 		# LATIN CAPITAL LETTER H WITH BREVE BELOW = LATIN CAPITAL LETTER H + COMBINING BREVE BELOW
	gsub(/\xF9\x68/, "\\&#x1E2B;"); 		# LATIN SMALL LETTER H WITH BREVE BELOW = LATIN SMALL LETTER H + COMBINING BREVE BELOW
	gsub(/\xF9/, "\\&#x032E;"); 		#combining breve below
}

gsub(/\xFA/, "\\&#xFE22;"); 		#combining double tilde left half

gsub(/\xFB/, "\\&#xFE23;"); 		#combining double tilde right half

gsub(/\xFE/, "\\&#x0313;"); 		#comma above, high comma, centered

##
## remaining single spacing characters
##

if      (/[\xA0-\xCF]/) {
	gsub(/\xA1/, "\\&#x0141;"); 		#latin capital letter L with stroke
	gsub(/\xA2/, "\\&#x00D8;"); 		#latin capital letter O with stroke
	gsub(/\xA3/, "\\&#x0110;"); 		#latin capital letter D with stroke
	gsub(/\xA4/, "\\&#x00DE;"); 		#latin capital letter thorn
	gsub(/\xA5/, "\\&#x00C6;"); 		#latin capital letter AE
	gsub(/\xA6/, "\\&#x0152;"); 		#latin capital ligature OE
	gsub(/\xA7/, "\\&#x02B9;"); 		#modified letter prime
	gsub(/\xA8/, "\\&#x00B7;"); 		#middle dot
	gsub(/\xA9/, "\\&#x266D;"); 		#music flat sign
	gsub(/\xAA/, "\\&#x00AE;"); 		#registered sign
	gsub(/\xAB/, "\\&#x00B1;"); 		#plus-minus sign
	gsub(/\xAC/, "\\&#x01A0;"); 		#latin capital letter O with horn
	gsub(/\xAD/, "\\&#x01AF;"); 		#latin capital letter U with horn
	gsub(/\xAE/, "\\&#x02BC;"); 		#modifier letter apostrophe
	gsub(/\xB0/, "\\&#x02BB;"); 		#modifier letter turned comma
	gsub(/\xB1/, "\\&#x0142;"); 		#latin small letter L with stroke
	gsub(/\xB2/, "\\&#x00F8;"); 		#latin small letter O with stroke
	gsub(/\xB3/, "\\&#x0111;"); 		#latin small letter D with stroke
	gsub(/\xB4/, "\\&#x00FE;"); 		#latin small letter thorn
	gsub(/\xB5/, "\\&#x00E6;"); 		#latin small letter AE
	gsub(/\xB6/, "\\&#x0153;"); 		#latin small ligature OE
	gsub(/\xB7/, "\\&#x02BA;"); 		#modified letter double prime
	gsub(/\xB8/, "\\&#x0131;"); 		#latin small letter dotless i
	gsub(/\xB9/, "\\&#x00A3;"); 		#pound sign
	gsub(/\xBA/, "\\&#x00F0;"); 		#latin small letter eth
	gsub(/\xBC/, "\\&#x01A1;"); 		#latin small letter O with horn
	gsub(/\xBD/, "\\&#x01B0;"); 		#latin small letter U with horn
	gsub(/\xC0/, "\\&#x00B0;"); 		#degree sign
	gsub(/\xC1/, "\\&#x2113;"); 		#script small L
	gsub(/\xC2/, "\\&#x2117;"); 		#sound recording copyright
	gsub(/\xC3/, "\\&#x00A9;"); 		#copyright sign
	gsub(/\xC4/, "\\&#x266F;"); 		#music sharp sign
	gsub(/\xC5/, "\\&#x00BF;"); 		#inverted question mark
	gsub(/\xC6/, "\\&#x00A1;"); 		#inverted exclamation mark
	gsub(/\xCF/, "\\&#x00DF;"); 		#latin small letter sharp S
}

} # end function ANSELentify()

###############################################################################
# EOF 
###############################################################################
