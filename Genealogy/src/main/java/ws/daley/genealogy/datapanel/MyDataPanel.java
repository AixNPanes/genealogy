package ws.daley.genealogy.datapanel;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

public class MyDataPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyDataPanel.class);

	public MyTabbedPane myTabbedPane;

	public MyDataPanel()
	{
		log.trace("Entering");
		this.myTabbedPane = new MyTabbedPane();
		log.trace("Exitting");
	}
//
//	public static void disablePanels()
//	{
//		log.trace("Entering disablePanels()");
//		for(MyPane myPane:MyTabbedPane.dataPanelList)
//			myPane.setVisible(false);
//		log.trace("Exitting disablePanels()");
//	}
}
