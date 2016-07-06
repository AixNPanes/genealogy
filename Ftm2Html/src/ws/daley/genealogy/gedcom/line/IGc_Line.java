package ws.daley.genealogy.gedcom.line;

import java.io.IOException;
import java.io.PrintStream;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;

public interface IGc_Line {

	public abstract void mergeMapItems(
			AttributeDescriptorMap _attributeDescriptorMap);

	public abstract boolean interpret();

	public abstract void emitXML(PrintStream stream, int indent)
			throws IOException;

}