package ws.daley.genealogy.gedcom.object;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

public interface IGcBaseElement {

	public abstract void emitXML(PrintStream stream, int indent)
			throws IOException;

	public abstract boolean interpret();

	public abstract int getLevel();

	public abstract void setLevel(int level);

	public abstract GcTags.TAG getTag();

	public abstract String getTagName();

	public abstract void setTag(GcTags.TAG tag);

	public abstract String getLineText();

	public abstract void setLineText(String text);

	public abstract Vector<GcBaseElement> getElements();

	public abstract void setElements(Vector<GcBaseElement> elements);

	public abstract String getParameters();

	public abstract TokenRange getParametersTokenRange();

	public abstract GcInputLine getLine();
}