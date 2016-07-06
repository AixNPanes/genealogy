package ws.daley.genealogy.components.family;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class IntegerLabel extends JLabel
{
	public IntegerLabel(int number)
	{
		super(NumberFormat.getNumberInstance(Locale.US).format(number));
	}
}
