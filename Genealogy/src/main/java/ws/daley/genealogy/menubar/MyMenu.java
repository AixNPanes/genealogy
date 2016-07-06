package ws.daley.genealogy.menubar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JMenu;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public abstract class MyMenu extends JMenu 
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyMenu.class);

	private static final Pattern p = Pattern.compile(".*&(.).*(&)?.*");

	public MyMenu(String s, List<List<MyMenuItem>> menuItemLists)
	{
		super("<HTML>" + s.replaceAll("&(.)", "<U>$1</U>") + "</HTML>");
//		this.setAlignmentY(0f);
//		this.setHorizontalAlignment(CENTER);
//		this.setVerticalAlignment(TOP);
		Character mnemonic = getMnemonicCharacter(s);
		if (mnemonic != null)
			this.setMnemonic(mnemonic);
		this.setVisible(true);
		boolean notFirstSection = false;
		for(List<MyMenuItem> menuItemList:menuItemLists)
		{
			if (notFirstSection)
				this.addSeparator();
			for(MyMenuItem menuItem:menuItemList)
			{
				add(menuItem);
			}
			notFirstSection=true;
		}
		log.trace("Exitting");
	}

	private Character getMnemonicCharacter(String s)
	{
		Matcher m = p.matcher(s);
		if (m.matches())
		{
			if (m.groupCount() > 1 && m.group(2) != null)
				throw new RuntimeException("Menu " + s + " can contain only 1 &");
			return m.group(1).toLowerCase().charAt(0);
		}
		return null;
	}

	@Override
	public void setVisible(boolean aFlag)
	{
		log.trace(this.getClass().getSimpleName() + " Entering setVisible(" + aFlag + ")");
		super.setVisible(aFlag);
		log.trace(this.getClass().getSimpleName() + " Exitting setVisible(" + aFlag + ")");
	}
}
