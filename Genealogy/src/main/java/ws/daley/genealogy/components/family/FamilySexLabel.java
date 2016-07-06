package ws.daley.genealogy.components.family;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class FamilySexLabel extends JLabel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FamilySexLabel.class);
//TODO
	private Dimension mySize = null;

	public FamilySexLabel()
	{
		super("Sex:");
		log.trace("Entering");
		this.setLayout(null);
		this.setSize(new Dimension(10, 10));
		log.trace("Exitting");
	}

	@Override
	public void paint(Graphics g)
	{
        log.trace(this.getClass().getSimpleName() + " Entering paint(" + g + ")");
		super.paint(g);
		if (this.mySize == null)
		{
			Font font = this.getFont();
			FontMetrics fontMetrics = g.getFontMetrics(font);
			Rectangle2D bounds = fontMetrics.getStringBounds(this.getText(), g).createUnion(Util.SMALL_RECTANGLE);
			this.mySize = new Dimension((int)Math.ceil(bounds.getWidth()), (int)Math.ceil(bounds.getHeight()));
			this.setSize(this.mySize);
		}
        log.trace(this.getClass().getSimpleName() + " Exitting paint(" + g + ")");
	}
}
