package ws.daley.genealogy.gedcom;

import java.util.Vector;

import ws.daley.genealogy.Ged2XML;
import ws.daley.genealogy.gedcom.object.GcAutomatedRecordId;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcSourceCitation;
import ws.daley.genealogy.gedcom.object.GcSubmitterText;
import ws.daley.genealogy.gedcom.object.GcUserReferenceNumber;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

// Class for a note record
public class GcNoteRecord extends GcBaseElement
{
	public STRING Note = new STRING();
	
	public String noteText = "";
	public GcSubmitterText gcSubmitterText = new GcSubmitterText();
	public GcSourceCitation gcSourceCitation = new GcSourceCitation();
	public GcUserReferenceNumber gcUserReferenceNumber = new GcUserReferenceNumber();
	public GcAutomatedRecordId gcAutomatedRecordId = new GcAutomatedRecordId();
	public GcChange gcChange = new GcChange();

	public GcNoteRecord() {}

	public GcNoteRecord(GcBaseElement e)
	{
		super(e);
	}
	
	@Override
    public boolean interpret() {
		this.noteText = getParameters();
		if (this.noteText.length() == 0)
			return false;
		Vector<GcBaseElement> newElements = new Vector<GcBaseElement>(this.elements.size());
		for(IGcBaseElement e:this.elements)
		{
			switch(e.getTag())
			{
				case TAG_CONT:
					this.gcSubmitterText = new GcSubmitterText(e);
					this.gcSubmitterText.retrn = "\r\n";
					this.gcSubmitterText.interpret();
					newElements.add(this.gcSubmitterText);
					break;
				case TAG_CONC:
					this.gcSubmitterText = new GcSubmitterText(e);
					this.gcSubmitterText.interpret();
					newElements.add(this.gcSubmitterText);
					break;
//				case TAG_CONT:
//					gcSubmitterText = new GcSubmitterText(e);
//					gcSubmitterText.interpret();
//					newElements.add(gcSubmitterText);
//					break;
				case TAG_REFN:
					this.gcUserReferenceNumber = new GcUserReferenceNumber(e);
					this.gcUserReferenceNumber.interpret();
					newElements.add(this.gcUserReferenceNumber);
					break;
				case TAG_RIN:
					this.gcAutomatedRecordId = new GcAutomatedRecordId(e);
					this.gcAutomatedRecordId.interpret();
					newElements.add(this.gcAutomatedRecordId);
					break;
				case TAG_CHAN:
					this.gcChange = new GcChange(e);
					this.gcChange.interpret();
					newElements.add(this.gcChange);
					break;
				default:
					return false;
			}
		}
		return true;
	}
	
	public Boolean Import(GedcomFile file,Vector<IdTable> NoteIDs)
	{
		IdTable idt = new IdTable();
		idt.Add(file.GetXRef());
		NoteIDs.add(idt);
		this.Note = new STRING(file.GetText());
		file.NextLine();
		if (!file.InterpretLine())
			return (Boolean.FALSE);
		while (file.GetLevel()>0)
		{
			switch(file.GetTag())
			{
			case TAG_CONT:
				this.Note.Add("\r\n");
				this.Note.Add(file.GetText());
				file.NextLine();
				break;
			case TAG_CONC:
				this.Note.Add(file.GetText());
				file.NextLine();
				break;
			case TAG_SOUR:
			case TAG_REFN:
			case TAG_CHAN:
				file.PrintError(Ged2XML.ERROR_IGNORE,Boolean.TRUE);
				file.SkipStructure();
				break;
			case TAG_RIN:
				file.PrintError(Ged2XML.ERROR_IGNORE,Boolean.TRUE);
				file.NextLine();
				break;
			default:
				file.IllegalTag();
				break;
			}
			if (!file.InterpretLine())
				return (Boolean.FALSE);
		}
		return (Boolean.TRUE);
	}
};
