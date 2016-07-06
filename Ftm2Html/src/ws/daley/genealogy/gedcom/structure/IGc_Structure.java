package ws.daley.genealogy.gedcom.structure;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

public interface IGc_Structure {

	public abstract void buildVectorMap(Vector<GcBaseElement> vector);

	public abstract void mergeMapItems(TagDescriptorMap _tagDescriptorMap);

	public abstract void interpretTag(String t, IGcBaseElement e);

	public abstract boolean interpret();

	public abstract void mustNotHaveParameters();

	public abstract void mustHaveParameters();

	public abstract BaseElementVectorMap getVectorMap();

	public abstract IGcBaseElement getFirstStructureElement(String _tag);

	public abstract void emitXML(PrintStream stream, int indent)
			throws IOException;

}