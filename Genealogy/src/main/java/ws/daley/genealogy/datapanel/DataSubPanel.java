package ws.daley.genealogy.datapanel;

import java.awt.Color;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public abstract class DataSubPanel extends JPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(DataSubPanel.class);

	public DataSubPanel(String title)
	{
		log.trace("Entering");
		this.add(new Label(title));
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 6));
		log.trace("Exitting");
	}

	public void setPanelEnabled(boolean enable)
	{
        log.trace(this.getClass().getSimpleName() + " Entering setPanelEnabled(" + enable + ")");
		this.setEnabled(enable);
		this.setVisible(enable);
// TODO
//		this.setMinimumSize(new Dimension(800, 400));
        log.trace(this.getClass().getSimpleName() + " Exitting setPanelEnabled(" + enable + ")");
	}
}
