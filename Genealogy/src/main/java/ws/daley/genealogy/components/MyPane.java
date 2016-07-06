package ws.daley.genealogy.components;

import java.awt.GridLayout;
import java.awt.font.TextLayout;

import javax.swing.Icon;
import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public abstract class MyPane extends JPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyPane.class);

	private String title = null;
	private Icon icon = null;
	private String tip = null;
	public TextLayout textLayout = null;
	
	public MyPane(String title, Icon icon, String tip)
	{
		super(false);
		log.trace(this.getClass().getSimpleName() + " Entering");
		this.title = title;
		this.icon = icon;
		this.tip = tip;
		this.setLayout(new GridLayout(1, 1));
		log.trace(this.getClass().getSimpleName() + "Exitting");
	}

	public String getTitle() {return this.title;}
	public Icon getIcon() {return this.icon;}
	public String getTip() {return this.tip;}

	@Override
	public void setVisible(boolean aFlag)
	{
		log.trace(this.getClass().getSimpleName() + " Entering setVisible(" + aFlag + ")");
		super.setVisible(aFlag);
		log.trace(this.getClass().getSimpleName() + " Exitting setVisible(" + aFlag + ")");
	}
}
