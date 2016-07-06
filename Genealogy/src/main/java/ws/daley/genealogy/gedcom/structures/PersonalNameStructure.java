package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class PersonalNameStructure extends GedcomXMLRecord
	{
		/**
  n  NAME <NAME_PERSONAL>  {1:1}
    +1 NPFX <NAME_PIECE_PREFIX>  {0:1}
    +1 GIVN <NAME_PIECE_GIVEN>  {0:1}
    +1 NICK <NAME_PIECE_NICKNAME>  {0:1}
    +1 SPFX <NAME_PIECE_SURNAME_PREFIX>  {0:1}
    +1 SURN <NAME_PIECE_SURNAME>  {0:1}
    +1 NSFX <NAME_PIECE_SUFFIX>  {0:1}
    +1 <<SOURCE_CITATION>>  {0:M}
      +2 <<NOTE_STRUCTURE>>  {0:M}
      +2 <<MULTIMEDIA_LINK>>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
		 */
		public String npfx;
		public String maiden;
		public String givn;
		public String nick;
		public String spfx;
		public String surn;
		public String nsfx;
		public ArrayList<SourceCitation> sourceCitation = new ArrayList<SourceCitation>();
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public ArrayList<String> nameParts = new ArrayList<String>();
		public PersonalNameStructure(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "NPFX": this.npfx = subRecord.lineValue; break;
					case "GIVN": this.givn = subRecord.lineValue; break;
					case "NICK": this.nick = subRecord.lineValue.trim(); break;
					case "SPFX": this.spfx = subRecord.lineValue; break;
					case "SURN": this.surn = subRecord.lineValue.trim(); break;
					case "NSFX": this.nsfx = subRecord.lineValue; break;
					case "SOUR": this.sourceCitation.add(new SourceCitation(subRecord)); break;
					case "NOTE": this.noteStructure.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
			String nameData = this.data.trim();
			if (nameData.length() == 0)
				return;
			Pattern pq = Pattern.compile("((?:\\(|\\)\"|/|\\w|\\.|<|>)+)");
			Matcher mq = pq.matcher(nameData.trim());
			boolean first = true;
			while(mq.find())
			{
				String namePart = nameData.substring(mq.start(), mq.end());
				if (namePart.endsWith("."))
					this.npfx = first?namePart:this.npfx;
				else if(namePart.startsWith("\"") && namePart.endsWith("\""))
					this.nick = namePart.substring(1, namePart.length() - 1);
				else if(namePart.startsWith("(") && namePart.endsWith(")"))
					this.maiden = namePart.substring(1, namePart.length() - 1);
				else if(namePart.startsWith("/") && namePart.endsWith("/"))
					this.surn = namePart.substring(1, namePart.length() - 1);
				else
					this.nameParts.add(namePart);
				first = false;
			}

		}
		@Override
		public String toString()
		{
			String ret = "";
			if (this.npfx != null)
				ret += "<NamePart Type=\"title\">" + this.npfx + "</NamePart>";
			else if (this.nameParts != null)
				for(String namePart:this.nameParts)
					ret += "<NamePart Type=\"given\" Level=\"3\">" + namePart + "</NamePart>";
			else if (this.nick != null)
				ret += "<NamePart>" + this.nick + "</NamePart>\n";
			else if (this.maiden != null)
				ret += "<NamePart Type=\"maiden\" Level=\"2\">" + this.maiden + "</NamePart>";
			else if (this.spfx != null)
				ret += "<NamePart>" + this.spfx + "</NamePart>\n";
			else if (this.surn != null)
				ret += "<NamePart Type=\"surname\" Level=\"1\">" + this.surn + "</NamePart>";
			else if (this.nsfx != null)
				ret += "<NamePart>" + this.nsfx + "</NamePart>";
			return ret;
		}
	}