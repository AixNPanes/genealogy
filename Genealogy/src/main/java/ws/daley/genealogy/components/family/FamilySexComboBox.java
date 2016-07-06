package ws.daley.genealogy.components.family;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class FamilySexComboBox extends JComboBox<String>
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FamilySexComboBox.class);

	private static String[] sexes = new String[]{"Male", "Female", "Unknown"};

	private Dimension mySize = null;

	public FamilySexComboBox()
	{
		super(sexes);
		log.trace("Entering");
//TODO
		Util.setSize(this, new Dimension(101, 36));
		log.trace("Exitting");
	}

	@Override
	public void paint(Graphics g)
	{
        log.trace(this.getClass().getSimpleName() + " Entering paint(" + g + ")");
		super.paint(g);
		if (this.mySize == null)
		{
			Component[] component=this.getComponents();
			Font font = component[1].getFont();
			FontMetrics fontMetrics = g.getFontMetrics(font);
			ComboBoxModel<String> model = this.getModel();
			int modelSize = model.getSize();
			Rectangle2D bounds = (Rectangle2D)Util.SMALL_RECTANGLE.clone();
			for(int i = 0; i < modelSize; i++)
				bounds = fontMetrics.getStringBounds(model.getElementAt(i), g).createUnion(bounds);
			this.mySize = new Dimension((int)Math.ceil(bounds.getWidth()) + 30, (int)Math.ceil(bounds.getHeight())+24);
			this.setSize(this.mySize);
		}
        log.trace(this.getClass().getSimpleName() + " Exitting paint(" + g + ")");
	}
}
