package ws.daley.genealogy.gedcom;

import java.io.OutputStream;
import java.util.Vector;

import ws.daley.genealogy.Ged2XML;
import ws.daley.genealogy.gedcom.line.GcNameLine;
import ws.daley.genealogy.gedcom.line.GcVersionLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcTags;
import ws.daley.genealogy.gedcom.structure.GcDateStructure;
import ws.daley.genealogy.gedcom.structure.GcHeaderStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceStructure;
import ws.daley.genealogy.gedcom.structure.GcSubmitterStructure;
import ws.daley.genealogy.gedcom.util.Util;
import ws.daley.genealogy.gedcom.xref.GcSubmitterXref;
/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

//#include <stdio.h>

class GenXML
{
	private	static String XMLHeader="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
	private	static String Start="<genxml xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"genxml20.xsd\">";
	private	static String File="<file><version>200</version><level>3</level></file>";
	private static String End="</genxml>";
	private static String ExpSys="<header><exportingsystem>";
	private static String Version="</exportingsystem><version>";
	private static String VersionEnd="</version>";
	private static String szEndHeader="</header>";
	private static String szOwnerStart="<owner>";
	private static String szOwnerEnd="</owner>";
	private static String szNameStart="<name>";
	private static String szNameEnd="</name>";
	private static String szRepStart1="<repository id=\"";
	private static String szRepStart2="\">";
	private static String szRepEnd="</repository>";
	private static String szSourceStart1="<source id=\"";
	private static String szSourceStart2="\">";
	private static String szSourceEnd="</source>";
	private static String szAuthorStart="<author>";
	private static String szAuthorEnd="</author>";
	private static String szTitleStart="<title>";
	private static String szTitleEnd="</title>";
	private static String szShortStart="<shorttitle>";
	private static String szShortEnd="</shorttitle>";
	private static String szPublishedStart="<published>";
	private static String szPublishedEnd="</published>";
	private static String szReprefStart="<repositoryref ref=\"";
	private static String szReprefEnd="</repositoryref>";
	private static String szCallStart="<callnumber>";
	private static String szCallEnd="</callnumber>";
	private static String szTextStart="<text>";
	private static String szTextEnd="</text>";
	private static String szExcerptStart1="<excerpt id=\"";
	private static String szExcerptStart2="\">";
	private static String szExcerptEnd="</excerpt>";
	private static String szQualityStart="<quality>";
	private static String szQualityEnd="</quality>";
	private static String szPageStart="<page>";
	private static String szPageEnd="</page>";
	private static String szRefStart="<sourceref>";
	private static String szRefEnd="</sourceref>";
	private static String szEventtypeStart1="<eventtype id=\"";
	private static String szEventtypeStart2="\" class=\"";
	private static String szEventtypeStart3="\">";
	private static String szEventtypeEnd="</eventtype>";
	private static String szDescrStart="<description>";
	private static String szDescrEnd="</description>";
	private static String szTagStart="<gedcomtag>";
	private static String szTagEnd="</gedcomtag>";
	private static String szRolesStart="<roles>";
	private static String szRolesEnd="</roles>";
	private static String szPersonStart1="<person id=\"";
	private static String szPersonStart2="\" sex=\"";
	private static String szPersonStart3="\">";
	private static String szPersonEnd="</person>";
	private static String szPersonalNameUnknown="<personalname><np tp=\"unkw\">Unknown</np></personalname>";
	private static String szAssertionStart1="<assertion id=\"";
	private static String szAssertionStart2="\">";
	private static String szAssertionEnd="</assertion>";
	private static String szAliasStart="<alias>";
	private static String szAliasEnd="</alias>";
	private static String szEventStart1="<event type=\"";
	private static String szEventStart2="\">";
	private static String szEventEnd="</event>";
	private static String szPrincipalStart="<principal>";
	private static String szPrincipalEnd="</principal>";
	private static String szAttributeStart="<attribute>";
	private static String szAttributeEnd="</attribute>";
	private static String szPersonrefStart="<personref>";
	private static String szPersonrefEnd="</personref>";
	private static String szTextclassStart="<textclass>";
	private static String szTextclassEnd="</textclass>";
	private static String szInfoStart="<info>";
	private static String szInfoEnd="</info>";
	private static String szRelationshipStart="<relationship>";
	private static String szRelationshipEnd="</relationship>";
	private static String szRelation="<relation>biological</relation>";
	private static String szFatherStart="<father>";
	private static String szFatherEnd="</father>";
	private static String szMotherStart="<mother>";
	private static String szMotherEnd="</mother>";
	private static String szChildStart="<child>";
	private static String szChildEnd="</child>";
	private static String szRepositories="<total><repositories>";
	private static String szSources="</repositories><sources>";
	private static String szExcerpts="</sources><excerpts>";
	private static String szEventtypes="</excerpts><eventtypes>";
	private static String szPersons="</eventtypes><persons>";
	private static String szAssertions="</persons><assertions>";
	private static String szAssertionsEnd="</assertions><objectives>0</objectives><tasks>0</tasks></total>";
	private static String szPersonalNameStart="<personalname>";
	private static String szPersonalNameEnd="</personalname>";
	private static String szUnknown="<np tp=\"unkw\">";
	private static String szGivenName="<np tp=\"givn\">";
	private static String szSurname="<np tp=\"surn\">";
	private static String szPrefix="<np tp=\"pref\">";
	private static String szSuffix="<np tp=\"sufx\">";
	private static String szArticle="<np tp=\"art\">";
	private static String szNickname="<np tp=\"nick\">";
	private static String szPartEnd="</np>";
	private static String szPlaceStart="<place>";
	private static String szPlaceEnd="</place>";
	private static String szPlacePartStart="<pnp>";
	private static String szPlacePartEnd="</pnp>";
	private static String szAddressStart="<address>";
	private static String szAddressEnd="</address>";
	private static String szAddressPartStart="<ap>";
	private static String szAddressPartEnd="</ap><lf/>";
	private static String szPhoneStart="<phone>";
	private static String szPhoneEnd="</phone>";
	private static String szNoteStart="<note>";
	private static String szNoteEnd="</note>";
	private static String szExcerptrefStart="<excerptref>";
	private static String szExcerptrefEnd="</excerptref>";
	private static String szDateStart="<date>";
	private static String szDateEnd="</date>";

	private OutputStream GXFile;

	private ConversionData ConvData;
/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

	public GenXML(OutputStream file,ConversionData convdata)
	{
		ConvData = convdata;
		GXFile = file;
		Write(XMLHeader);
		Write(Start);
		Write(File);
	}
	
	public void endGENXML()
	{
			Write(End);
	}
	
	public void Write(char[] szText)
	{
		Util.WriteFile(GXFile,new String(szText),Util.strlen(szText),null);
	}
	
	public void Write(String szText)
	{
		Util.WriteFile(GXFile,szText,szText.length(),null);
	}
	
	private void Write(STRING string)
	{
		STRING	conv;
	
		conv = string.ConvertToXML();
		Util.WriteFile(GXFile,conv.Get(),(int)conv.Size(),null);
	}
	
	public void Header(GcHeaderStructure header,Vector<GcSubmitterStructure> Submitters)
	{
	
		Write(ExpSys);
		GcSourceStructure sourceStructure = (GcSourceStructure)header.getFirstStructureElement("SOUR");
		String headerSourceName = null;
		String headerSourceVersion = null;

		if (sourceStructure != null)
		{
			GcNameLine nameLine = (GcNameLine)sourceStructure.getFirstStructureElement("NAME");
			if (nameLine != null)
				headerSourceName = nameLine.getParameters();

			GcVersionLine versionLine = (GcVersionLine)sourceStructure.getFirstStructureElement("VERS");
			if (versionLine != null)
				headerSourceVersion = versionLine.getParameters();

			Write("".equals(headerSourceName)?"Unknown":headerSourceName);
			Write(Version);
			Write("".equals(headerSourceVersion)?"Unknown":headerSourceVersion);
			Write(VersionEnd);
		}
		
		GcDateStructure dateStructure = (GcDateStructure)header.getFirstStructureElement("DATE");
		if (dateStructure != null)
			SimpleDate(dateStructure.Date1,"exported",null,Boolean.TRUE);
		GcSubmitterXref submitterXref = (GcSubmitterXref)header.getFirstStructureElement("SUBM");
		if (submitterXref != null)
		{
			for (int i=1;i<=Submitters.size();i++)
			{
				if (Util.strcmp(header.submitter,Submitters.get((int)(i-1)).id)!=0)
				{
					Write(szOwnerStart);
					Write(szNameStart);
					Write(Submitters.get((int)(i-1)).name);
					Write(szNameEnd);
					Address(Submitters.get((int)(i-1)).address);
					Write(szOwnerEnd);
					break;
				}
			}
		}
		Write(szEndHeader);
	}
	
	public long Repository(Vector<GcRepository> Repositories,Vector<GcNoteRecord> Notes,IdTable NoteIDs)
	{
		String tmp;
		GcRepository Repository;
	
		for (long i=1;i<=Repositories.size();i++)
		{
			Repository = Repositories.get((int)(i-1));
			Write(szRepStart1);
			tmp = Util.sprintf("R%i",i);
			Write(tmp);
			Write(szRepStart2);
			// Write name
			Write(szNameStart);
			Write(Repository.Name);
			Write(szNameEnd);
			// Write address
			Address(Repository.Address);
			// Write note
			Note(Repository.Note,Notes,NoteIDs);
			// Write change
			Change(Repository.Change);
			Write(szRepEnd);
		}
		return Repositories.size();
	}
	
	public long Source(Vector<GcSourceStructure> Sources,Vector<GcSourceCite> Sourcerefs,IdTable RepIDs,Vector<GcNoteRecord> Notes,IdTable NoteIDs)
	{
		String tmp;
		long id,nSources;
		GcSourceStructure Source;
		GcSourceCite sc;
	
		for (long i=1;i<=Sources.size();i++)
		{
			Source = Sources.get((int)(i-1));
			Write(szSourceStart1);
			tmp = Util.sprintf("S%i",i);
			Write(tmp);
			Write(szSourceStart2);
			// Write author
			Write(szAuthorStart);
			Write(Source.Author.text);
			Write(szAuthorEnd);
			// Write title
			Write(szTitleStart);
			Write(Source.Title.text);
			Write(szTitleEnd);
			// Write short title
			Write(szShortStart);
			Write(Source.Abbreviation);
			Write(szShortEnd);
			// Write published
			if (!Source.Publication.text.equals(""))
			{
				Write(szPublishedStart);
				Write(Source.Publication.text);
				Write(szPublishedEnd);
			}
			// Repositoryref
			if ((Source.Repref.Rep[0]!=0) && ((id=RepIDs.Find(Source.Repref.Rep))>0))
			{
				Write(szReprefStart);
				tmp = Util.sprintf("R%i",id);
				Write(tmp);
				Write(szSourceStart2);
				if (!Source.Repref.Callnumber.equals(""))
				{
					Write(szCallStart);
					Write(Source.Repref.Callnumber);
					Write(szCallEnd);
				}
				Note(Source.Repref.Note,Notes,NoteIDs);
				Write(szReprefEnd);
			}
			// Write text
			if (!Source.Text.text.equals(""))
			{
				Write(szTextStart);
				Write(Source.Text.text);
				Write(szTextEnd);
			}
			// Write note
			Note(Source.Note,Notes,NoteIDs);
			// Write change
			Change(Source.Change);
			Write(szSourceEnd);
		}
		nSources = Sources.size();
		// Generate sources from unstructured source cites
		for (long i=1;i<=Sourcerefs.size();i++)
		{
			sc = Sourcerefs.get((int)(i-1));
			if (sc.sourceID[0]!=0)
			{
				nSources++;
				Write(szSourceStart1);
				tmp = Util.sprintf("S%i",nSources);
				Write(tmp);
				Write(szSourceStart2);
				sc.newSourceID = nSources;	// Store the id for later reference
				// Write author
				Write(szAuthorStart);
				Write("");
				Write(szAuthorEnd);
				// Write title
				Write(szTitleStart);
				Write(sc.sourceDescription.text);
				Write(szTitleEnd);
				// Write short title
				Write(szShortStart);
				Write("");
				Write(szShortEnd);
				// Write note
				Note(sc.note,Notes,NoteIDs);
				Write(szSourceEnd);
			}
		}
		return nSources;
	}
	
	public long Excerpt(Vector<GcSourceCite> Sourcerefs,IdTable SourceIDs,Vector<GcNoteRecord> Notes,IdTable NoteIDs)
	{
		String tmp;
		long id;
		GcSourceCite sc;
	
		for (long i=1;i<=Sourcerefs.size();i++)
		{
			sc = Sourcerefs.get((int)(i-1));
			Write(szExcerptStart1);
			tmp = Util.sprintf("X%i",i);
			Write(tmp);
			Write(szExcerptStart2);
			// Write text
			if (!sc.text.text.equals(""))
			{
				Write(szTextStart);
				Write(sc.text.text);
				Write(szTextEnd);
			}
			if (sc.sourceID[0]!=0)	// structured source cite
			{
				// Write quality
				if (!sc.quality.equals(""))
				{
					Write(szQualityStart);
					if (sc.quality.equals("3"))
						Write("98");
					else if (sc.quality.equals("2"))
						Write("95");
					else if (sc.quality.equals("1"))
						Write("90");
					else
						Write("80");
					Write(szQualityEnd);
				}
				// Write page
				if (!sc.page.equals(""))
				{
					Write(szPageStart);
					Write(sc.page);
					Write(szPageEnd);
				}
				// Write sourceref
				id = SourceIDs.Find(sc.sourceID);
			}
			else					// unstructured source cite
				id = sc.newSourceID;
			Util._ASSERT(id);
			Write(szRefStart);
			tmp = Util.sprintf("S%i",id);
			Write(tmp);
			Write(szRefEnd);
			// Write note
			Note(sc.note,Notes,NoteIDs);
			Write(szExcerptEnd);
		}
		return Sourcerefs.size();
	}
	
	public long Eventtype(Vector<GcEventType> Eventtypes)
	{
		String tmp;
		String descr;
		GcEventType et;
	
		for (long i=1;i<=Eventtypes.size();i++)
		{
			et = Eventtypes.get((int)(i-1));
			Write(szEventtypeStart1);
			tmp = Util.sprintf("E%i",i);
			Write(tmp);
			Write(szEventtypeStart2);
			switch (et.tag)
			{
			case TAG_BIRT:
				Write("birth");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_BIRT);
				break;
			case TAG_CHR:
				Write("baptism");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_CHR);
				break;
			case TAG_DEAT:
				Write("death");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_DEAT);
				break;
			case TAG_BURI:
				Write("burial");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_BURI);
				break;
			case TAG_CREM:
				Write("cremation");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_CREM);
				break;
			case TAG_ADOP:
				Write("adoption");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_ADOP);
				break;
			case TAG_BAPM:
				Write("baptism");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_BAPM);
				break;
			case TAG_BARM:
				Write("confirmation");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_BARM);
				break;
			case TAG_BASM:
				Write("confirmation");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_BASM);
				break;
			case TAG_BLES:
				Write("blessing");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_BLES);
				break;
			case TAG_CHRA:
				Write("baptism");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_CHRA);
				break;
			case TAG_CONF:
				Write("confirmation");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_CONF);
				break;
			case TAG_FCOM:
				Write("other");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_FCOM);
				break;
			case TAG_ORDN:
				Write("ordination");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_ORDN);
				break;
			case TAG_NATU:
				Write("naturalization");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_NATU);
				break;
			case TAG_EMIG:
				Write("emigration");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_EMIG);
				break;
			case TAG_IMMI:
				Write("immigration");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_IMMI);
				break;
			case TAG_CENS:
				Write("census");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_CENS);
				break;
			case TAG_PROB:
				Write("other");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_PROB);
				break;
			case TAG_WILL:
				Write("other");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_WILL);
				break;
			case TAG_GRAD:
				Write("graduation");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_GRAD);
				break;
			case TAG_RESI:
				Write("residence");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_RESI);
				break;
			case TAG_RETI:
				Write("retirement");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_RETI);
				break;
			case TAG_ANUL:
				Write("annulment");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_ANUL);
				break;
			case TAG_DIV:
				Write("divorce");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_DIV);
				break;
			case TAG_DIVF:
				Write("divorce");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_DIVF);
				break;
			case TAG_ENGA:
				Write("marriage");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_ENGA);
				break;
			case TAG_MARR:
				Write("marriage");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_MARR);
				break;
			case TAG_MARB:
				Write("marriage");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_MARB);
				break;
			case TAG_MARC:
				Write("marriage");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_MARC);
				break;
			case TAG_MARL:
				Write("marriage");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_MARL);
				break;
			case TAG_MARS:
				Write("marriage");
				descr = ConvData.MsgProvider.msgProvider(Ged2XML.EVENTTYPE_MARS);
				break;
			default:
				Write("other");
				descr = "";
				break;
			}
			Write(szEventtypeStart3);
			Write(szDescrStart);
			if (et.description.equals(""))
				Write(descr);
			else
				Write(et.description);
			Write(szDescrEnd);
			Write(szTagStart);
			Write(GcTags.getTagName(et.tag));
			Write(szTagEnd);
			Write(szRolesStart);
			Write(Util.itoa(et.roles,null,10));
			Write(szRolesEnd);
			Write(szEventtypeEnd);
		}
		return Eventtypes.size();
	}
	
	public long Person(Vector<GcPerson> Persons)
	{
		String tmp;
		GcPerson Person;
	
		for (long i=1;i<=Persons.size();i++)
		{
			Person = Persons.get((int)(i-1));
			Write(szPersonStart1);
			tmp = Util.sprintf("P%i",Person.NewID);
			Write(tmp);
			Write(szPersonStart2);
			if (Person.Sex==1)
				Write("male");
			else if (Person.Sex==2)
				Write("female");
			else
				Write("unknown");
			Write(szPersonStart3);
			// Write name
			if (Person.Names.size()!=0)
				PersonalName(Person.Names.get(0));
			else
				Write(szPersonalNameUnknown);
			// Write excerptref
			Excerptref(Person.SourcerefIDs);
			// Write change
			Change(Person.Change);
			Write(szPersonEnd);
		}
		return Persons.size();
	}
	
	public long Assertion(Vector<GcPerson> Persons,Vector<GcFamily> Families,IdTable ids,Vector<GcNoteRecord> Notes,IdTable NoteIDs)
	{
		int descr;
		String textclass;
		GcPerson Person;
		GcFamily Family;
		GcNameStructure Name;
		GcEvent Event;
		GcAttribute Attribute;
		GcNote NoteStr;
		long i,k,iAssertions;
		long father,mother,child;
	
		iAssertions = 0;
		// Write Person assertions
		for (i=1;i<=Persons.size();i++)
		{
			Person = Persons.get((int)(i-1));
			// Write alias
			for (k=1;k<=Person.Names.size();k++)
			{
				Name = Person.Names.get((int)(k-1));
				if (k>1 || Name.SourcerefIDs.size()!=0 || !Name.Note.Note.equals("") || Name.Note.NoteID[0]!=0)
				{
					iAssertions++;
					Write(szAssertionStart1);
					Write(Util.sprintf("A%i",iAssertions));
					Write(szAssertionStart2);
					Write(szAliasStart);
					PersonalName(Name);
					Write(szPersonrefStart);
					Write(Util.sprintf("P%i",i));
					Write(szPersonrefEnd);
					Write(szAliasEnd);
					Excerptref(Name.SourcerefIDs);
					Note(Name.Note,Notes,NoteIDs);
					Write(szAssertionEnd);
				}
			}
			// Write attributes
			for (k=1;k<=Person.Attributes.size();k++)
			{
				iAssertions++;
				Attribute = Person.Attributes.get((int)(k-1));
				switch (Attribute.Tag)
				{
				case TAG_CAST:
					descr = Ged2XML.EVENTTYPE_CAST;
					textclass = "caste";
					break;
				case TAG_DSCR:
					descr = Ged2XML.EVENTTYPE_DSCR;
					textclass = "physical";
					break;
				case TAG_EDUC:
					descr = Ged2XML.EVENTTYPE_EDUC;
					textclass = "education";
					break;
				case TAG_IDNO:
					descr = Ged2XML.EVENTTYPE_IDNO;
					textclass = "idnumber";
					break;
				case TAG_NATI:
					descr = Ged2XML.EVENTTYPE_NATI;
					textclass = "nationality";
					break;
				case TAG_NCHI:
					descr = Ged2XML.EVENTTYPE_NCHI;
					textclass = "other";
					break;
				case TAG_NMR:
					descr = Ged2XML.EVENTTYPE_NMR;
					textclass = "other";
					break;
				case TAG_OCCU:
					descr = Ged2XML.EVENTTYPE_OCCU;
					textclass = "work";
					break;
				case TAG_PROP:
					descr = Ged2XML.EVENTTYPE_PROP;
					textclass = "property";
					break;
				case TAG_RELI:
					descr = Ged2XML.EVENTTYPE_RELI;
					textclass = "religion";
					break;
				case TAG_SSN:
					descr = Ged2XML.EVENTTYPE_SSN;
					textclass = "idnumber";
					break;
				case TAG_TITL:
					descr = Ged2XML.EVENTTYPE_TITL;
					textclass = "title";
					break;
				default:
					descr = 0;
					textclass = "other";
					break;
				}
				Write(szAssertionStart1);
				Write(Util.sprintf("A%i",iAssertions));
				Write(szAssertionStart2);
				Write(szAttributeStart);
				Write(szDescrStart);
				if (!Attribute.Type.equals(""))
					Write(Attribute.Type);
				else
					Write(ConvData.MsgProvider.msgProvider(descr));
				Write(szDescrEnd);
				Write(szTextclassStart);
				Write(textclass);
				Write(szTextclassEnd);
				Write(szTextStart);
				Write(Attribute.Text);
				Write(szTextEnd);
				Write(szPersonrefStart);
				Write(Util.sprintf("P%i",i));
				Write(szPersonrefEnd);
				Date(Attribute.Date);
				Place(Attribute.Place);
				Address(Attribute.Address);
				Write(szAttributeEnd);
				Excerptref(Attribute.SourcerefIDs);
				Note(Attribute.Note,Notes,NoteIDs);
				Write(szAssertionEnd);
			}
			// Write events
			for (k=1;k<=Person.Events.size();k++)
			{
				iAssertions++;
				Event = Person.Events.get((int)(k-1));
				Write(szAssertionStart1);
				Write(Util.sprintf("A%i",iAssertions));
				Write(szAssertionStart2);
				Write(szEventStart1);
				Write(Util.sprintf("E%i",Event.EventType));
				Write(szEventStart2);
				Write(szPrincipalStart);
				Write(Util.sprintf("P%i",i));
				Write(szPrincipalEnd);
				Date(Event.Date);
				Place(Event.Place);
				Address(Event.Address);
				Write(szEventEnd);
				Excerptref(Event.SourcerefIDs);
				Note(Event.Note,Notes,NoteIDs);
				Write(szAssertionEnd);
			}
			// Write infos
			for (k=1;k<=Person.Notes.size();k++)
			{
				iAssertions++;
				NoteStr = Person.Notes.get((int)(k-1));
				Write(szAssertionStart1);
				Write(Util.sprintf("A%i",iAssertions));
				Write(szAssertionStart2);
				Write(szInfoStart);
				Write(szTextStart);
				NoteText(NoteStr,Notes,NoteIDs);
				Write(szTextEnd);
				Write(szPersonrefStart);
				Write(Util.sprintf("P%i",i));
				Write(szPersonrefEnd);
				Write(szInfoEnd);
				Write(szAssertionEnd);
			}
		}
		// Write family assertions
		for (i=1;i<=Families.size();i++)
		{
			Family = Families.get((int)(i-1));
			if (Family.Husb[0]!=0)
				father = ids.Find(Family.Husb);
			else
				father = 0;
			if (Family.Wife[0]!=0)
				mother = ids.Find(Family.Wife);
			else
				mother = 0;
			if (father!=0 && mother!=0)
				continue;
			// Write relationships
			for (k=1;k<=Family.Children.GetSize();k++)
			{
				iAssertions++;
				child = ids.Find(Family.Children.Get((int)k));
				Util._ASSERT(child);
				Write(szAssertionStart1);
				Write(Util.sprintf("A%i",iAssertions));
				Write(szAssertionStart2);
				Write(szRelationshipStart);
				Write(szRelation);
				if (father!=0)
				{
					Write(szFatherStart);
					Write(Util.sprintf("P%i",father));
					Write(szFatherEnd);
				}
				if (mother!=0)
				{
					Write(szMotherStart);
					Write(Util.sprintf("P%i",mother));
					Write(szMotherEnd);
				}
				Write(szChildStart);
				Write(Util.sprintf("P%i",child));
				Write(szChildEnd);
				Write(szRelationshipEnd);
				Excerptref(Family.SourcerefIDs);
				Write(szAssertionEnd);
			}
			// Write family events
			for (k=1;k<=Family.Events.size();k++)
			{
				iAssertions++;
				Event = Family.Events.get((int)(k-1));
				Write(szAssertionStart1);
				Write(Util.sprintf("A%i",iAssertions));
				Write(szAssertionStart2);
				Write(szEventStart1);
				Write(Util.sprintf("E%i",Event.EventType));
				Write(szEventStart2);
				if (father!=0)
				{
					Write(szPrincipalStart);
					Write(Util.sprintf("P%i",father));
					Write(szPrincipalEnd);
				}
				if (mother!=0)
				{
					Write(szPrincipalStart);
					Write(Util.sprintf("P%i",mother));
					Write(szPrincipalEnd);
				}
				Date(Event.Date);
				Place(Event.Place);
				Address(Event.Address);
				Write(szEventEnd);
				Excerptref(Family.SourcerefIDs);
				Note(Event.Note,Notes,NoteIDs);
				Write(szAssertionEnd);
			}
			// Write family infos
			for (k=1;k<=Family.Notes.size();k++)
			{
				iAssertions++;
				NoteStr = Family.Notes.get((int)(k-1));
				Write(szAssertionStart1);
				Write(Util.sprintf("A%i",iAssertions));
				Write(szAssertionStart2);
				Write(szInfoStart);
				Write(szTextStart);
				NoteText(NoteStr,Notes,NoteIDs);
				Write(szTextEnd);
				if (father!=0)
				{
					Write(szPersonrefStart);
					Write(Util.sprintf("P%i",father));
					Write(szPersonrefEnd);
				}
				if (mother!=0)
				{
					Write(szPersonrefStart);
					Write(Util.sprintf("P%i",mother));
					Write(szPersonrefEnd);
				}
				Write(szInfoEnd);
				Write(szAssertionEnd);
			}
		}
		return iAssertions;
	}
	
	public void Total(long iRepositories,long iSources,long iExcerpts,long iEventtypes,long iPersons,long iAssertions)
	{
		Write(szRepositories);
		Write(Util.itoa(iRepositories,null,10));
		Write(szSources);
		Write(Util.itoa(iSources,null,10));
		Write(szExcerpts);
		Write(Util.itoa(iExcerpts,null,10));
		Write(szEventtypes);
		Write(Util.itoa(iEventtypes,null,10));
		Write(szPersons);
		Write(Util.itoa(iPersons,null,10));
		Write(szAssertions);
		Write(Util.itoa(iAssertions,null,10));
		Write(szAssertionsEnd);
	}
	
	public void Date(GcDateStructure date)
	{
		if (date.DateType==GcDateStructure.DATETYPE_NONE)
			return;
		Write(szDateStart);
		if (date.DateType==GcDateStructure.DATETYPE_EXACT)
			SimpleDate(date.Date1,"exact");
		else if (date.DateType==GcDateStructure.DATETYPE_BET)
		{
			SimpleDate(date.Date1,"begin");
			SimpleDate(date.Date2,"end");
		}
		else if (date.DateType==GcDateStructure.DATETYPE_FROMTO)
		{
			SimpleDate(date.Date1,"from");
			SimpleDate(date.Date2,"to");
		}
		else if (date.DateType==GcDateStructure.DATETYPE_FROM)
			SimpleDate(date.Date1,"from");
		else if (date.DateType==GcDateStructure.DATETYPE_TO)
			SimpleDate(date.Date1,"to");
		else if (date.DateType==GcDateStructure.DATETYPE_ABT)
			SimpleDate(date.Date1,"exact","about");
		else if (date.DateType==GcDateStructure.DATETYPE_EST)
			SimpleDate(date.Date1,"exact","estimated");
		else if (date.DateType==GcDateStructure.DATETYPE_AFT)
			SimpleDate(date.Date1,"exact","after");
		else if (date.DateType==GcDateStructure.DATETYPE_BEF)
			SimpleDate(date.Date1,"exact","before");
		else
		{
			Util._ASSERT(date.DateType==GcDateStructure.DATETYPE_TEXT);
			Write(szTextStart);
			Write(date.Text);
			Write(szTextEnd);
		}
		Write(szDateEnd);
	}
	
	public void SimpleDate(GcSimpleDate date,String tag)
	{
		SimpleDate(date, tag, null, false);
	}
	
	public void SimpleDate(GcSimpleDate date,String tag,String modifier)
	{
		SimpleDate(date, tag, modifier, false);
	}
	
	public void SimpleDate(GcSimpleDate date,String tag,String modifier,Boolean time)
	{
		Write("<");
		Write(tag);
		// TODO: Check calendar
		if (modifier!=null)
		{
			Write(" mod=\"");
			Write(modifier);
			Write("\"");
		}
		if (date.year<0)
			Write(" era=\"before\"");
		Write(">");
		Write((date.alternateyear>=0?
				Util.sprintf("%04i/%02i-%02i-%02i",date.year,date.alternateyear,date.month,date.day):
					Util.sprintf("%04i-%02i-%02i",date.year,date.month,date.day))+
				(time?"T00:00:00":""));
		Write("</");
		Write(tag);
		Write(">");
	}
	
	public void Change(GcChange Change)
	{
		if (Change.Date.year!=0)
			SimpleDate(Change.Date,"change",null,Boolean.TRUE);
	}
	
	public void PersonalName(GcNameStructure name)
	{
		Write(szPersonalNameStart);
		if (ConvData.UseStructuredNames)
		{
			if (!name.Npfx.equals(""))
			{
				Write(szPrefix);
				Write(name.Npfx);
				Write(szPartEnd);
			}
			if (!name.Givn.equals(""))
			{
				Write(szGivenName);
				Write(name.Givn);
				Write(szPartEnd);
			}
			if (!name.Nick.equals(""))
			{
				Write(szNickname);
				Write(name.Nick);
				Write(szPartEnd);
			}
			if (!name.Spfx.equals(""))
			{
				Write(szArticle);
				Write(name.Spfx);
				Write(szPartEnd);
			}
			if (!name.Surn.equals(""))
			{
				Write(szSurname);
				Write(name.Surn);
				Write(szPartEnd);
			}
			if (!name.Nsfx.equals(""))
			{
				Write(szSuffix);
				Write(name.Nsfx);
				Write(szPartEnd);
			}
		}
		else
		{
			String[] split = name.Name.Get().split("/", 2);
			for(int i = 0; i < split.length; i++)
			{
				if (split[i].length()>0)
				{
					Write(i==0?
							(ConvData.AssumeGivenName?szGivenName:szUnknown):
							(i==1?szSurname:szUnknown));
					Write(split[i]);
					Write(szPartEnd);
				}
			}
		}
		Write(szPersonalNameEnd);
	}
	
	public void Place(GcPlace Place)
	{
		String[] token;
	
		if (Place.Place.equals(""))
			return;
		Write(szPlaceStart);
		token = Place.Place.Get().split(",");
		for(int i=0; i<token.length; i++)
		{
			while(token[i].startsWith(" "))
				token[i] = token[i].substring(1);
			if (token[i].length()>0)
			{
				Write(szPlacePartStart);
				Write(token[i]);
				Write(szPlacePartEnd);
			}
		}
		Write(szPlaceEnd);
	}
	
	public void Address(GcAddress Address)
	{
		if (Address.addressLines.size()!=0 && Address.phon.equals(""))
			return;
		Write(szAddressStart);
		for (int i=1;i<=Address.addressLines.size();i++)
		{
			Write(szAddressPartStart);
			Write(Address.addressLines.get((int)(i-1)));
			Write(szAddressPartEnd);
		}
		if (!Address.phon.equals(""))
		{
			Write(szPhoneStart);
			Write(Address.phon);
			Write(szPhoneEnd);
		}
		Write(szAddressEnd);
	}
	
	public void Note(GcNote Note,Vector<GcNoteRecord> Notes, IdTable NoteIDs)
	{
		if (Note.Note.equals(""))
			return;
		Write(szNoteStart);
		NoteText(Note,Notes,NoteIDs);
		Write(szNoteEnd);
	}
	
	public void NoteText(GcNote Note,Vector<GcNoteRecord> Notes, IdTable NoteIDs)
	{
		if (Note.NoteID[0]!=0)
		{
			Write(Notes.get((int)(NoteIDs.Find(Note.NoteID)-1)).Note);
		}
		else
			Write(Note.Note);
	}
	
	public void Excerptref(IdList IDs)
	{
		for (int i=1;i<=IDs.size();i++)
		{
			Write(szExcerptrefStart);
			Write(Util.sprintf("X%i",IDs.get((int)(i-1))));
			Write(szExcerptrefEnd);
		}
	}
};
