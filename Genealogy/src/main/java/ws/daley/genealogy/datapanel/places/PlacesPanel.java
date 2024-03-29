package ws.daley.genealogy.datapanel.places;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.datapanel.DataSubPanel;

@SuppressWarnings("serial")
public class PlacesPanel extends DataSubPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlacesPanel.class);

	public PlacesPanel()
	{
		super("Places");
		log.trace("Entering");
	}

	@Override
	public void paint(Graphics g)
	{
        log.trace(this.getClass().getSimpleName() + " Entering paint(" + g + ")");
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.dispose();
        log.trace(this.getClass().getSimpleName() + " Exitting paint(" + g + ")");
	}
}
