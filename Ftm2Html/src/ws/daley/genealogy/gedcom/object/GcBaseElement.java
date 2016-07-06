package ws.daley.genealogy.gedcom.object;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

public class GcBaseElement implements IGcBaseElement
{
	private static String TABS = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	protected GcInputLine line;
	public String tabs = null;
	protected Vector<GcBaseElement> elements = new Vector<GcBaseElement>();
	
	public GcBaseElement(){}
	
	public GcBaseElement(IGcBaseElement e)
	{
		this(e==null?(GcInputLine)null:e.getLine());
		if (e != null)
			this.elements = e.getElements();
	}
	
	public GcBaseElement(GcInputLine _line)
	{
		this.line = _line;
		this.tabs = (this.line==null||this.line.levelno<0)?"":TABS.substring(0, this.line.levelno);
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#emitXML(java.io.PrintStream, int)
	 */
	@SuppressWarnings("unused")
    public void emitXML(PrintStream stream, int indent) throws IOException
	{
		throw new RuntimeException("emitXML");
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#interpret()
	 */
	public boolean interpret()
	{
		throw new RuntimeException("interpret");
	}
	
	public static Vector<GcBaseElement> getBaseElements()
	{
		Vector<GcBaseElement> newElements = new Vector<GcBaseElement>();
		return newElements;
	}

	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#getLevel()
	 */
	public int getLevel() {return this.line.levelno;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#setLevel(int)
	 */
	public void setLevel(int level) {this.line.levelno = level;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#getTag()
	 */
	public GcTags.TAG getTag() {return this.line.gcTag;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#getTagName()
	 */
	public String getTagName() {return GcTags.getTagName(this);}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#setTag(ws.daley.genealogy.gedcom.object.GcTags.TAG)
	 */
	public void setTag(GcTags.TAG tag) {this.line.gcTag = tag;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#getLineText()
	 */
	public String getLineText() {return this.line.lineText;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#setLineText(java.lang.String)
	 */
	public void setLineText(String text) {this.line.lineText = text;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#getElements()
	 */
	public Vector<GcBaseElement> getElements() {return this.elements;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#setElements(java.util.Vector)
	 */
	public void setElements(Vector<GcBaseElement> elements) {this.elements = elements;}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#getParameters()
	 */
	public String getParameters() {return this.line.getParameters();}
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.object.IGcBaseElement#getParametersTokenRange()
	 */
	public TokenRange getParametersTokenRange() {return this.line.getParametersTokenRange();}

	public GcInputLine getLine() {return this.line;}
	
	public static String getTabs(int i)
	{
		return i==0?"":TABS.substring(0, i);
	}
}
