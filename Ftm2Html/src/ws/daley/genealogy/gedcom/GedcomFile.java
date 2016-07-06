package ws.daley.genealogy.gedcom;
/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

//#include <windows.h>
//#include <stdio.h>
import java.io.File;
import java.io.FileInputStream;
import java.util.Formatter;
import java.util.Locale;
import java.util.Vector;

import ws.daley.genealogy.Ged2XML;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcInputLine;
import ws.daley.genealogy.gedcom.object.GcTags;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcLineageLinkedGedcomStructure;
import ws.daley.genealogy.gedcom.util.Util;

public class GedcomFile
{
	// Maximum size of text in gedcom lines
	public static final int TEXTSIZE	= 256;

	// Constants for CharacterSet
	public static final int CHARSET_ANSI	= 0;
	public static final int CHARSET_ANSEL	= 1;
	public static final int CHARSET_IBMPC	= 2;

	private int LineNumber = 0;
	private int LastErrorLine = 0;	// Last line on which there was an error
	private int Index = 0;
	private int CurrentLine = 0;
	private int EndOfLine = 0;
	private int Level;
	private long fileSize;
	private String szTag = "";
	private String file;
	private char[]  szText = new char[256];
	public ConversionData convData;
	
	//private int MessageLevel;
	private GcTags.TAG tag = GcTags.TAG.TAG_UNDEFINED;
	private char XRef[] = new char[Util.XREFSIZE+1];
	private char Pointer[] = new char[Util.XREFSIZE+1];
	private int CharacterSet = CHARSET_ANSEL;
	private Vector<GcBaseElement> elements = null;


	public int GetLevel(){return this.Level;}
	public GcTags.TAG GetTag(){return this.tag;}
	public char[] GetXRef(){return this.XRef;}
	public char[] GetPointer(){return this.Pointer;}
	public String GetText(){return (new String(this.szText)).substring(this.Index);}
	public int GetLineNumber(){return this.LineNumber;}
	//public int GetIndex(){return Index;}
	public void AddIndex(int x){this.Index+=x;}
	public int GetMsgLevel(){return this.convData.MessageLevel;}
	public long GetGedcomFileSize(){return this.fileSize;}
	public long GetDataRead(){return this.CurrentLine;}
	public void SetCharacterSet(int CharSet){this.CharacterSet=CharSet;}
	private GcLineageLinkedGedcomStructure gedcom;
	
	private static final char FromANSEL[]={76,216,208,222,198,32,145,183,32,174,177,79,85,39,32,
		96,108,248,100,254,230,32,34,105,163,240,208,111,117,32,32,
		176,108,32,169,32,191,161,32,32,32,32,32,32,32,32,223,
		32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32};
	
	private static final char FromIBMPC[]={199,252,233,226,228,224,229,231,234,235,232,239,238,236,196,197,
		201,230,198,244,246,242,251,249,255,214,220,248,163,216,80,102,
		225,237,243,250,241,209,170,32,191,32,172,189,188,161,171,187,
		32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
		32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
		32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,		
		32,223,32,32,32,32,181,222,254,32,32,32,32,32,32,32,
		61,177,32,32,32,32,247,32,176,176,183,32,32,178,32,32};
	
	private static final String szSkip="The following lines were skipped:\r\n";

	public GedcomFile(File hFile, ConversionData _convdata) {
	//HANDLE LogFile,char* (*ErrMsg)(int ErrorID),int MsgLevel)
		this.szText[0] = 0;
		this.XRef[0] = 0;
		this.Pointer[0] = 0;
		this.LastErrorLine = 0;

		this.convData = _convdata;
		this.fileSize = hFile.length();
		byte[] b = getBytes(hFile);
		Vector<GcBaseElement> inputElements = getInputLines(b);
		this.elements = GcInputLine.getLevels(inputElements);
		this.gedcom = new GcLineageLinkedGedcomStructure(null, this.elements);
		IGcBaseElement base = new GcBaseElement((GcInputLine)null);
		base.setElements(this.elements);
//		gedcom = new GcLineageLinkedGedcomStructure(base);
//		gedcom.buildVector();
		base = null;
		this.gedcom.interpret();
	}
	
	public Vector<GcBaseElement> getBaseElements() {return this.elements;}
	
	private byte[] getBytes(File hFile) {
		this.fileSize = hFile.length();
		byte[] b = new byte[(int)this.fileSize];
		FileInputStream fis;
		try {
			fis = new FileInputStream(hFile);
			fis.read(b);
			new String(b);
			fis.close();
			this.file = new String(b);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return b;
	}
	
	private Vector<GcBaseElement> getInputLines(byte[] b) {
		return GcInputLine.getBaseElements(new String(b));
	}
	
	public void NextLine()
	{
		this.LineNumber++;
		// Update pointers
		this.CurrentLine = this.EndOfLine;
		while (this.file.charAt(this.CurrentLine)=='\r' || this.file.charAt(this.CurrentLine)=='\n')
		{
			this.CurrentLine++;
			if (this.CurrentLine>=this.fileSize)
				return;
		}
		this.EndOfLine = this.CurrentLine + 1;
		while (this.EndOfLine<this.fileSize && this.file.charAt(this.EndOfLine)!='\r' && this.file.charAt(this.EndOfLine)!='\n')
			this.EndOfLine++;
	}
	
	private Boolean CheckSyntax()
	{
		this.Index = 0;
		// Check if file is finished
		if (this.CurrentLine>=this.fileSize)
		{
			PrintError(Ged2XML.ERROR_EOF,Boolean.TRUE);
			return (Boolean.FALSE);
		}
		// Get level
		while(this.file.charAt(this.CurrentLine)==' ')
			this.CurrentLine++;
		if (!Util.isdigit(this.file.charAt(this.CurrentLine)))
		{
			PrintError(Ged2XML.ERROR_LINE,Boolean.TRUE);
			return Boolean.FALSE;	// Fatal error
		}
		this.Level = this.file.charAt(this.CurrentLine)-0x30;
		this.Index++;
		if (this.file.charAt(this.CurrentLine+this.Index)!=' ')
		{
			PrintError(Ged2XML.ERROR_LINE,Boolean.TRUE);
			return Boolean.FALSE;	// Fatal error
		}
		this.Index++;
		return Boolean.TRUE;
	}
	
	private void TranslateANSEL(char[] szTo,char[] szFrom,int start,int nCharacters)
	{
		char[] szToPtr = szTo;
		char[] szFromPtr = szFrom;
		int i,j;
		char chr,chr2;
	
		i=0;
		j=0;
		while (i<nCharacters)
		{
			chr = szFromPtr[start+i];
			if (chr<128)
			{
				szToPtr[j] = szFromPtr[start+i];
				j++;
				i++;
			}
			else if (chr<161) i++;
			else if (chr<225)
			{
				szToPtr[j] = FromANSEL[chr-161];
				j++;
				i++;
			}
			else
			{
				i++;
				chr2 = szFromPtr[start+i];
				i++;
				switch (chr)
				{
					case 225:     //grave accent
						switch (chr2)
						{
							case 'a':
								szToPtr[j] = 224;
								break;
							case 'A':
								szToPtr[j] = 192;
								break;
							case 'e':
								szToPtr[j] = 232;
								break;
							case 'E':
								szToPtr[j] = 200;
								break;
							case 'i':
								szToPtr[j] = 236;
								break;
							case 'I':
								szToPtr[j] = 204;
								break;
							case 'o':
								szToPtr[j] = 242;
								break;
							case 'O':
								szToPtr[j] = 210;
								break;
							case 'u':
								szToPtr[j] = 249;
								break;
							case 'U':
								szToPtr[j] = 217;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					case 226:     //acute accent
						switch (chr2)
						{
							case 'a':
								szToPtr[j] = 225;
								break;
							case 'A':
								szToPtr[j] = 193;
								break;
							case 'e':
								szToPtr[j] = 233;
								break;
							case 'E':
								szToPtr[j] = 201;
								break;
							case 'i':
								szToPtr[j] = 237;
								break;
							case 'I':
								szToPtr[j] = 205;
								break;
							case 'o':
								szToPtr[j] = 243;
								break;
							case 'O':
								szToPtr[j] = 211;
								break;
							case 'u':
								szToPtr[j] = 250;
								break;
							case 'U':
								szToPtr[j] = 218;
								break;
							case 'y':
								szToPtr[j] = 254;
								break;
							case 'Y':
								szToPtr[j] = 222;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					case 227:     //circumflex accent
						switch (chr2)
						{
							case 'a':
								szToPtr[j] = 226;
								break;
							case 'A':
								szToPtr[j] = 194;
								break;
							case 'e':
								szToPtr[j] = 234;
								break;
							case 'E':
								szToPtr[j] = 202;
								break;
							case 'i':
								szToPtr[j] = 238;
								break;
							case 'I':
								szToPtr[j] = 206;
								break;
							case 'o':
								szToPtr[j] = 244;
								break;
							case 'O':
								szToPtr[j] = 212;
								break;
							case 'u':
								szToPtr[j] = 251;
								break;
							case 'U':
								szToPtr[j] = 219;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					case 228:     //tilde
						switch (chr2)
						{
							case 'a':
								szToPtr[j] = 227;
								break;
							case 'A':
								szToPtr[j] = 195;
								break;
							case 'o':
								szToPtr[j] = 245;
								break;
							case 'O':
								szToPtr[j] = 213;
								break;
							case 'n':
								szToPtr[j] = 241;
								break;
							case 'N':
								szToPtr[j] = 209;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					case 232:     //umlaut
						switch (chr2)
						{
							case 'a':
								szToPtr[j] = 228;
								break;
							case 'A':
								szToPtr[j] = 196;
								break;
							case 'e':
								szToPtr[j] = 235;
								break;
							case 'E':
								szToPtr[j] = 203;
								break;
							case 'i':
								szToPtr[j] = 239;
								break;
							case 'I':
								szToPtr[j] = 207;
								break;
							case 'o':
								szToPtr[j] = 246;
								break;
							case 'O':
								szToPtr[j] = 214;
								break;
							case 'u':
								szToPtr[j] = 252;
								break;
							case 'U':
								szToPtr[j] = 220;
								break;
							case 'y':
								szToPtr[j] = 255;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					case 234:     //circle above
						switch (chr2)
						{
							case 'a':
								szToPtr[j] = 229;
								break;
							case 'A':
								szToPtr[j] = 197;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					case 240:     //cedilla
						switch (chr2)
						{
							case 'c':
								szToPtr[j] = 231;
								break;
							case 'C':
								szToPtr[j] = 199;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					case 246:     //underscore
						switch (chr2)
						{
							case 'a':
								szToPtr[j] = 170;
								break;
							case 'o':
								szToPtr[j] = 186;
								break;
							default:
								if (chr2<128) szToPtr[j] = chr2;
								else j--;
								break;
						}
						break;
					default:
						if (chr2<128) szToPtr[j] = chr2;
						else j--;
						break;
				}
				j++;
			}
		}
		szToPtr[j] = 0;
	}
	
	private void TranslateIBMPC(char[] szTo,char[] szFrom,int start, int nCharacters)
	{
		int i;
		for (i=0;i<nCharacters;i++)
		{
			if (szFrom[start+i]<128)
				szTo[i] = szFrom[i];
			else
				szTo[i] = FromIBMPC[szTo[i]-128];
		}
		szTo[i] = 0;
	}
	
	public Boolean InterpretLine()
	{
		while(true)
		{
			this.tag = GcTags.TAG.TAG_UNDEFINED;
			this.XRef[0] = 0;
			this.Pointer[0] = 0;
			if (!CheckSyntax())
				return Boolean.FALSE;
			// Copy and translate line
			if (this.EndOfLine-this.CurrentLine>TEXTSIZE-1)
			{
				PrintError(Ged2XML.ERROR_LINE,Boolean.TRUE);
				return Boolean.FALSE;	// Fatal error
			}
			if (this.CharacterSet==CHARSET_ANSI)
			{
				this.szText = this.file.substring(this.CurrentLine, this.EndOfLine).toCharArray();
			}
			else if (this.CharacterSet==CHARSET_IBMPC)
				TranslateIBMPC(this.szText,this.file.toCharArray(),this.CurrentLine,this.EndOfLine-this.CurrentLine);
			else
				TranslateANSEL(this.szText,this.file.toCharArray(),this.CurrentLine,this.EndOfLine-this.CurrentLine);
			// Get XRef
			if (this.szText[this.Index]=='@')
			{
				TranslatePointer(this.XRef);
				if (this.XRef[0] != 0)
				{
					PrintError(Ged2XML.ERROR_XREFTOOLONG,Boolean.TRUE);
					return Boolean.FALSE;	// Fatal error
				}
				if (this.szText[this.Index]!=' ')
				{
					PrintError(Ged2XML.ERROR_LINE,Boolean.TRUE);
					return Boolean.FALSE;	// Fatal error
				}
				this.Index++;
			}
			// Get tag
			if (this.szText[this.Index]=='_')
			{
				PrintError(Ged2XML.ERROR_USERDEFINED,Boolean.TRUE);
				if (!SkipStructure())
					return Boolean.FALSE;
				continue;
			}
			this.tag = GcTags.getTag((new String(this.szText)).substring(this.Index, this.Index+4));
			if (this.tag==GcTags.TAG.TAG_UNDEFINED)
			{
				PrintError(Ged2XML.ERROR_UNKNOWNTAG,Boolean.TRUE);
				NextLine();
				continue;
			}
			break;
		}
		this.szTag = GcTags.getTagName(this.tag);
		this.Index += this.szTag.length();
		while  (this.szText[this.Index]==' ')
			this.Index++;
		// Get pointer
		if (this.szText[this.Index]=='@')
		{
			TranslatePointer(this.Pointer);
			if (this.Pointer[0] != 0)
			{
				PrintError(Ged2XML.ERROR_XREFTOOLONG,Boolean.TRUE);
				return Boolean.FALSE;	// Fatal error
			}
			if (this.szText[this.Index]!=0 && this.szText[this.Index]!=' ')
			{
				PrintError(Ged2XML.ERROR_LINE,Boolean.TRUE);
				return Boolean.FALSE;	// Fatal error
			}
			this.Index++;
		}
		return Boolean.TRUE;
	}
	
	private void TranslatePointer(char[] result)
	{
		//long id;
		int x;
	
		this.Index++;		// Skipping the first '@'
		x = 0;
		while (x<Util.XREFSIZE && this.szText[this.Index+x]!='@')
		{
			result[x] = this.szText[this.Index+x];
			x++;
		}
		if (x>=Util.XREFSIZE)	// XREF too long?
		{
			result[0] = 0;
			return;
		}
		result[x] = 0;
		this.Index += x;
		this.Index++;
		/*if (isalpha(file[CurrentLine+Index]))
			Index++;
		x = Index;
		while (file[CurrentLine+x]!='@')
			x++;
		if (x-Index>5)
			return -1;	// id too long
		id = atol(file+CurrentLine+Index);
		if (!id && (file[CurrentLine+Index]!='0' || x-Index>1))
			return -1;	// alphanumeric id
		Index = x + 1;
		return id;*/
	}
	
	public void PrintError(int Error,Boolean ShowLine)
	{
		StringBuffer buffer = new StringBuffer();
		String Message;
	
		if (this.LineNumber==this.LastErrorLine)
			return;	// Don't display more than one error on a line
		this.LastErrorLine = this.LineNumber;
		// Check for known errors
		if (Error==Ged2XML.ERROR_UNKNOWNTAG && this.convData.MessageLevel>=Ged2XML.MESSAGELEVEL_SKIPERRORS)
		{
			if (Util.strcmp(this.szTag,"NUMB") != 0 ||
				Util.strcmp(this.szTag,"LVG ") != 0 ||
				Util.strcmp(this.szTag,"ILLE") != 0) // Known ROOTSIII errors
				return;
		}
		if (Error==Ged2XML.ERROR_IGNORE && this.convData.MessageLevel>=Ged2XML.MESSAGELEVEL_SKIPIGNORE)
			return;
		// Process error
		Message = this.convData.MsgProvider.msgProvider(Error);
		String fmt = Error==Ged2XML.ERROR_IGNORE?"\r\nLine %d: ":"\r\nError on line %d: ";
		Formatter fmtr = new Formatter(buffer, Locale.US);
		fmtr.format(fmt, this.LineNumber);
		Util.WriteFile(this.convData.hLogFile,buffer.toString(),Util.strlen(buffer.toString()),null);
		Util.WriteFile(this.convData.hLogFile,Message,Util.strlen(Message),null);
		Util.WriteFile(this.convData.hLogFile,"\r\n",2,null);
		if (ShowLine)
		{
			/*int length = EndOfLine-CurrentLine;
			if (length>80)
				length = 80;*/
			Util.WriteFile(this.convData.hLogFile,new String(this.szText),Util.strlen(this.szText),null);
			buffer = new StringBuffer("\r\n");
			for	(int i=0;i<this.Index;i++)
				buffer.append(" ");
			buffer.append("^\r\n");
			Util.WriteFile(this.convData.hLogFile,buffer.toString(),Util.strlen(buffer.toString()),null);
		}
		//WriteFile(LogFile,"\r\n",2,&BytesWritten,null);
	}
	
	public Boolean SkipStructure()
	{
		int TopLevel,len;
		
		TopLevel = this.Level;
		NextLine();
		if (!CheckSyntax())
			return Boolean.FALSE;
		if (this.Level<=TopLevel)
			return Boolean.TRUE;	// No lines to skip
		Util.WriteFile(this.convData.hLogFile,szSkip,Util.strlen(szSkip),null);
		while (this.Level>TopLevel)
		{
			len = this.EndOfLine-this.CurrentLine;
			if (len>80)
				len = 80;
			Util.WriteFile(this.convData.hLogFile,this.file.substring(this.CurrentLine),len,null);
			Util.WriteFile(this.convData.hLogFile,"\r\n",2,null);
			NextLine();
			if (!CheckSyntax())
				return Boolean.FALSE;
		}
		Util.WriteFile(this.convData.hLogFile,"\r\n",2,null);
		return Boolean.TRUE;
	}
	
	public void IllegalTag()
	{
		this.Index = 2;
		PrintError(Ged2XML.ERROR_ILLEGALTAG,Boolean.TRUE);
		SkipStructure();
	}

	public GcLineageLinkedGedcomStructure getGedcom()
	{
		return this.gedcom;
	}
};
