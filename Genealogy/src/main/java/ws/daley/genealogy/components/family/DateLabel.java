package ws.daley.genealogy.components.family;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DateLabel extends JLabel
{
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyy.MMMMM.dd hh:mm aaa");

	public DateLabel(Date date)
	{
		super(date==null?"":format.format(date));
	}

	public DateLabel(ZonedDateTime date)
	{
		this(date==null?(Date)null:Date.from(date.toInstant()));
	}
}
