package ws.daley.genealogy.datasubpanel.plannewtree.panes;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.components.MyPane;

@SuppressWarnings("serial")
public class PlanNewTreeStartANewPane extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreeStartANewPane.class);

//	private SpringLayout springLayout = new SpringLayout();
//	private static final int xPad = 5;
//	private static final int yPad = 5;

	public PlanNewTreeStartANewPane()
	{
		super("Getting started", null, null);
		log.trace("Entering");
		log.trace("Exiting");
	}
}
