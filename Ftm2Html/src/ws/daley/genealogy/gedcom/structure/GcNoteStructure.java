package ws.daley.genealogy.gedcom.structure;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.line.IGc_Line;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;
import ws.daley.genealogy.gedcom.object.TokenRange;
import ws.daley.genealogy.gedcom.object.GcTags.TAG;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * NOTE_STRUCTURE:= 
 * [ 
 * n NOTE @<XREF:NOTE>@													{1:1} 
 * 	+1 <<SOURCE_CITATION>>												{0:M} 
 * | 
 * n NOTE [SUBMITTER_TEXT> | <NULL>]										{1:1} 
 * 	+1 [ CONC | CONT ] <SUBMITTER_TEXT>									{0:M} 
 * 	+1 <<SOURCE_CITATION>>												{0:M}
 * ]
 */

public class GcNoteStructure extends GcBaseElement implements IGc_Structure, IGc_Line
{
	private Gc_Structure structure = null;
	
	public GcNoteStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		try {
			setStructure(new GcNoteXrefStructure(e, _vector));
			return;
		} catch(Throwable t) {}
		setStructure(new GcNoteSubmitterStructure(e, _vector));
	}
	
	public boolean isXref() {return getStructure() instanceof GcNoteXrefStructure;}

	public void buildVectorMap(Vector<GcBaseElement> vector) {getStructure().buildVectorMap(vector);}

	@Override
	public void emitXML(PrintStream stream, int indent) throws IOException {getStructure().emitXML(stream, indent);}

	public IGcBaseElement getFirstStructureElement(String _tag) {return getStructure().getFirstStructureElement(_tag);}

	public BaseElementVectorMap getVectorMap() {return getVectorMap();}

	@Override
	public boolean interpret() {return getStructure().interpret();}

	public void interpretTag(String t, IGcBaseElement e) {getStructure().interpretTag(t, e);}

	public void mergeMapItems(TagDescriptorMap descriptorMap) {getStructure().mergeMapItems(descriptorMap);}

	public void mustHaveParameters() {getStructure().mustHaveParameters();}

	public void mustNotHaveParameters() {getStructure().mustNotHaveParameters();}

	public void mergeMapItems(AttributeDescriptorMap descriptorMap) {getStructure().mergeMapItems(descriptorMap);}

	@Override
	public Vector<GcBaseElement> getElements() {return getStructure().getElements();}

	@Override
	public int getLevel() {return getStructure().getLevel();}

	@Override
	public String getLineText() {return getStructure().getLineText();}

	@Override
	public String getParameters() {return getStructure().getParameters();}

	@Override
	public TokenRange getParametersTokenRange() {return getStructure().getParametersTokenRange();}

	@Override
	public TAG getTag() {return getStructure().getTag();}

	@Override
	public String getTagName() {return getStructure().getTagName();}

	@Override
	public void setElements(Vector<GcBaseElement> elements) {getStructure().setElements(elements);}

	@Override
	public void setLevel(int level) {getStructure().setLevel(level);}

	@Override
	public void setLineText(String text) {getStructure().setLineText(text);}

	@Override
	public void setTag(TAG tag) {getStructure().setTag(tag);}
	
	public String getText()
	{
		if (getStructure() instanceof GcNoteSubmitterStructure)
			return ((GcNoteSubmitterStructure)getStructure()).getText();
		return "";
	}

	public void setStructure(Gc_Structure structure) {this.structure = structure;}

	public Gc_Structure getStructure() {return this.structure;}
};
