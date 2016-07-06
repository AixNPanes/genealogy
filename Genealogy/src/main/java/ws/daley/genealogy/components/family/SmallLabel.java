package ws.daley.genealogy.components.family;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.Icon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SmallLabel extends JLabel
{

	public SmallLabel(int fontIncrease) {this(fontIncrease, "", null, LEADING);}

	public SmallLabel(int fontIncrease, Icon image, int horizontalAlignment) {this(fontIncrease, null, image, horizontalAlignment);}

	public SmallLabel(int fontIncrease, Icon image) {this(fontIncrease, null, image, CENTER);}

	public SmallLabel(int fontIncrease, String text, int horizontalAlignment) {this(fontIncrease, text, null, horizontalAlignment);}

	public SmallLabel(int fontIncrease, String text) {this(fontIncrease, text, null, LEADING);}

	public SmallLabel(int fontIncrease, String text, Icon icon, int horizontalAlignment)
	{
		super(text, icon, horizontalAlignment);
		Font font = this.getFont();
		this.setFont(font.deriveFont(font.getSize2D() + fontIncrease));
	}

	@Override
	public Dimension getPreferredSize()
	{
		FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
		int width = fontMetrics.stringWidth(this.getText());
		int height = fontMetrics.getHeight();
		return new Dimension(width, height);
	}
}
