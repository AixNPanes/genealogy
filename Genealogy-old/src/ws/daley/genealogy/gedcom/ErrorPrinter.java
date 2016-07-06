package ws.daley.genealogy.gedcom;
import java.text.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ErrorPrinter
	extends DefaultHandler
{
	private static MessageFormat message =
		new MessageFormat("({0}: {1}, {2}): {3}");

	private void print(SAXParseException x)
	{
		String msg = message.format(new Object[]
			{
				x.getSystemId(),
				new Integer(x.getLineNumber()),
				new Integer(x.getColumnNumber()),
				x.getMessage()
			});
		System.out.println(msg);
	}

	@Override
    public void warning(SAXParseException x)
   		throws SAXParseException
	{
		print(x);
		throw x;
	}

	@Override
    public void error(SAXParseException x)
		throws SAXParseException
	{
		print(x);
		throw x;
	}

   @Override
public void fatalError(SAXParseException x)
		throws SAXParseException
	{
		print(x);
		throw x;
	}
}
