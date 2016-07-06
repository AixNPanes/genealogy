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

public abstract class Gc_CombinationStructure extends GcBaseElement implements IGc_Structure, IGc_Line
{
	protected Gc_Structure structure = null;
	
	public Gc_CombinationStructure() {}
	
	public Gc_CombinationStructure(@SuppressWarnings("unused") GcBaseElement e, 
			@SuppressWarnings("unused") Vector<GcBaseElement> _vector)
	{
		throw new RuntimeException("Constructor not valid");
	}

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
	
	public Gc_Structure getStructure() {return this.structure;}

	public void setStructure(Gc_Structure structure) {this.structure = structure;}
};
