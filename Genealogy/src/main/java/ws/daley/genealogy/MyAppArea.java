package ws.daley.genealogy;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.datapanel.MyHeaderPanel;
import ws.daley.genealogy.datapanel.MyTabbedPane;


@SuppressWarnings("serial")
public class MyAppArea extends JPanel implements RefreshData
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyAppArea.class);

	public MyHeaderPanel myHeaderPanel;
	public MyTabbedPane myTabbedPane;

	public MyAppArea()
	{
		log.trace("Entering");
		this.setLayout(new BorderLayout());
		this.setBackground(MyFamily.myFamily.getBackground());
		this.myHeaderPanel = new MyHeaderPanel();
		this.myTabbedPane = new MyTabbedPane();
		add(this.myHeaderPanel, NORTH);
		add(this.myTabbedPane, CENTER);
		log.trace("Exitting");
	}

	public void setAssociatedEntries()
	{
        log.trace(this.getClass().getSimpleName() + " Entering setAssociatedEntries()");
		this.myHeaderPanel.setAssociatedEntries();
        log.trace(this.getClass().getSimpleName() + " Exitting setAssociatedEntries()");
	}

	@Override
	public void refreshData()
	{
		throw new RuntimeException("RefreshData not implemented");
	}
}
