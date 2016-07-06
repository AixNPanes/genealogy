package ws.daley.genealogy.components.family;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HumanReadableIntegerLabel extends JLabel
{
	private static final int KiB = 1024;
	private static final int MiB = 1024 * KiB;
	private static final int GiB = 1024 * MiB;

	private static String readable(int number)
	{
		if (number >= GiB)
			return NumberFormat.getNumberInstance(Locale.US).format(number / MiB) + " MiB";
		if (number >= MiB)
			return NumberFormat.getNumberInstance(Locale.US).format(number / KiB) + " KiB";
		return NumberFormat.getNumberInstance(Locale.US).format(number) + " B";
	}

	public HumanReadableIntegerLabel(int number)
	{
		super(readable(number));
	}
}
