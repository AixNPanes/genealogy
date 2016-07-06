package ws.daley.genealogy.menubar;

import javax.swing.JMenuItem;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public abstract class MyMenuItem extends JMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyMenuItem.class);

	public MyMenuItem(String text, int mnemonic)
	{
		super(text, mnemonic);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
