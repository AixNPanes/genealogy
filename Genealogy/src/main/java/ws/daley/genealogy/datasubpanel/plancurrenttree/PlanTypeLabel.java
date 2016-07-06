package ws.daley.genealogy.datasubpanel.plancurrenttree;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicLabelUI;

@SuppressWarnings("serial")
public class PlanTypeLabel extends JLabel
{
	private Dimension preferredSize;

	public PlanTypeLabel(PlanType planType)
	{
		super(planType.getPlanType());
		this.setUI(BasicLabelUI.createUI(this));
	}

	public Rectangle getBounds(PlanType planType)
	{
		FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
		Rectangle iconR = new Rectangle(0, 0, 0, 0);
		Rectangle textR = new Rectangle(0, 0, 0, 0);
		Rectangle viewR = new Rectangle(0, 0, Short.MAX_VALUE, Short.MAX_VALUE);
		SwingUtilities.layoutCompoundLabel(this, fontMetrics, planType.getPlanType(), null,
			this.getVerticalAlignment(), this.getHorizontalAlignment(), this.getVerticalTextPosition(), this.getHorizontalTextPosition(),
			viewR, iconR, textR, this.getIconTextGap());
		return new Rectangle(textR.x, textR.y, textR.width, textR.height);
	}

	@Override
	public Dimension getPreferredSize()
	{
		if (this.preferredSize == null)
		{
			Rectangle homeR = getBounds(PlanType.Home);
			Rectangle currentR = getBounds(PlanType.Current);
			this.preferredSize = new Dimension(Math.max(homeR.width, currentR.width), Math.max(homeR.height, currentR.height));
		}
		return this.preferredSize;
	}
}
