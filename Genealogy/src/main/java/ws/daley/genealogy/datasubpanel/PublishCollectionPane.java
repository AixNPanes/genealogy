package ws.daley.genealogy.datasubpanel;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.RefreshData;
import ws.daley.genealogy.components.MyPane;

@SuppressWarnings("serial")
public class PublishCollectionPane extends MyPane implements RefreshData
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PublishCollectionPane.class);

	public PublishCollectionPane()
	{
		super("Collection", null, null);
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

	@Override
	public void refreshData()
	{
		throw new RuntimeException("RefreshData not implemented");
	}
}
