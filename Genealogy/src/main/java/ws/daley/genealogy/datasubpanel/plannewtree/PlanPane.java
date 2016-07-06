package ws.daley.genealogy.datasubpanel.plannewtree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.components.MyPane;

@SuppressWarnings("serial")
public abstract class PlanPane extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanPane.class);

	@Override
	public abstract Dimension getPreferredSize();

	protected SpringLayout springLayout = new SpringLayout();
	protected static final int xPad = 5;
	protected static final int yPad = 5;

	protected static final Color shadowColor = Color.BLACK;
	protected static final int shadowAlpha = 150;
	protected static final int highlightAlpha = 250;
	protected static final int strokeSize = 1;
	protected static final Dimension arcs = new Dimension(20, 20);

	protected JLabel header;
	protected Component lastComponent;

	public PlanPane(String text)
	{
		super(text, null, null);
		log.trace("Entering");
		this.setOpaque(false);
		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());
		log.trace("Exiting");
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D)g.create();
		Dimension headerLabelSize = this.header.getPreferredSize();
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		int headerBottom = headerLabelSize.height + arcs.height;
		Color shadowColorA = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
		Color highlightColorA = new Color(193, 197, 147);
		this.setForeground(highlightColorA);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.fillRoundRect(0, 0, width - strokeSize, headerBottom, arcs.width, arcs.height);
		this.setForeground(highlightColorA);
		graphics.setColor(shadowColorA);
		graphics.drawRoundRect(0, 0, width - strokeSize, headerBottom, arcs.width, arcs.height);
		graphics.drawRoundRect(0, 0, width - strokeSize, height - strokeSize, arcs.width, arcs.height);
		graphics.dispose();
	}
}
