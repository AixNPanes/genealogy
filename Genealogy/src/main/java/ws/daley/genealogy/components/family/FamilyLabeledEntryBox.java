package ws.daley.genealogy.components.family;

import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class FamilyLabeledEntryBox extends JPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FamilyLabeledEntryBox.class);

	private SpringLayout springLayout = new SpringLayout();
	private int xPad = 5;
	private int yPad = 2;

	public JLabel nameLabel = new JLabel();
	public JTextField textField = new JTextField(10);

	public Point leftAttach = new Point(0, 0);
	public Point rightAttach = new Point(0, 0);

	public FamilyLabeledEntryBox(String name)
	{
		log.trace("Entering");
		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());
		this.nameLabel.setText(name);
		this.add(this.nameLabel);
		this.add(this.textField);
		this.springLayout.putConstraint(NORTH, this.nameLabel, 0, NORTH, this);
		this.springLayout.putConstraint(WEST, this.nameLabel, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.textField, this.yPad, SOUTH, this.nameLabel);
		this.springLayout.putConstraint(WEST, this.textField, this.xPad, WEST, this);
		this.setPreferredSize(new Dimension(1, 1));
		log.trace("Exitting");
	}

	@Override
	public void paint(Graphics g)
	{
        log.trace(this.getClass().getSimpleName() + " Entering paint(" + g + ")");
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g.create();
		String label = this.nameLabel.getText();
		Font labelFont = this.nameLabel.getFont();
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
		int labelHeight = labelFontMetrics.getHeight();
		int labelWidth = labelFontMetrics.stringWidth(label);
		Font textFont = this.textField.getFont();
		FontMetrics textFontMetrics = g.getFontMetrics(textFont);
		int textHeight = textFontMetrics.getHeight();
		int textWidth = textFontMetrics.stringWidth("WWWWWWWWWWWWWWW");
		int totalHeight = labelHeight + textHeight + this.yPad;
		int totalWidth = Math.max(labelWidth, textWidth + this.xPad);
		this.nameLabel.setPreferredSize(new Dimension(labelWidth + 2, labelHeight + 2));
		this.nameLabel.setMinimumSize(this.nameLabel.getPreferredSize());
		this.nameLabel.setMaximumSize(this.nameLabel.getPreferredSize());
		this.textField.setPreferredSize(new Dimension(textWidth + 2, textHeight + 2));
		this.textField.setMinimumSize(this.textField.getPreferredSize());
		this.textField.setMaximumSize(this.textField.getPreferredSize());
		this.setPreferredSize(new Dimension(totalWidth + 2, totalHeight + 2));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());

		g2d.dispose();
		super.paint(g);
		int y = this.getY() + this.textField.getY() + (this.textField.getHeight() / 2);
		int left = this.getX() + this.textField.getX();
		int right = this.getX() + this.textField.getX() + this.textField.getWidth();
		this.leftAttach = new Point(left, y);
		this.rightAttach = new Point(right, y);
        log.trace(this.getClass().getSimpleName() + " Exitting paint(" + g + ")");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				Util.getMax(this.nameLabel.getWidth(), this.textField.getWidth()),
				this.nameLabel.getHeight() + this.textField.getHeight() + this.yPad);
	}
}
