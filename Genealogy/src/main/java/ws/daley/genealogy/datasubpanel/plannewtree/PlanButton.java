package ws.daley.genealogy.datasubpanel.plannewtree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import ws.daley.genealogy.MyFamily;

@SuppressWarnings("serial")
public class PlanButton extends JButton
{
	private boolean minimumDimension = false;
	private boolean gradientFill = false;

	private static final Color gradient1 = new Color(248, 213, 105);
	private static final Color gradient2 = new Color(235, 135, 0);

	public PlanButton()
	{
		this(null, false);
	}

	public PlanButton(String text)
	{
		this(text, false);
	}

	public PlanButton(String text, boolean minimumDimension)
	{
		super(text);
		this.minimumDimension = minimumDimension;
		this.setBackground(MyFamily.myFamily.getBackground());
		this.setBorderPainted(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		this.setOpaque(true);
	}

	public void setGradientFill(boolean gradientFill)
	{
		this.gradientFill = gradientFill;
	}

	@Override
	public Dimension getPreferredSize()
	{
		FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
		int width = 0;
		int height = 0;
		Border border = this.getBorder();
		if (border != null)
		{
			Insets insets = border.getBorderInsets(this);
			width = insets.left + insets.right;
			height = insets.top + insets.bottom;
		}
		return new Dimension(
				width + fontMetrics.stringWidth(this.getText()),
				height + fontMetrics.getHeight());
	}

	@Override
	public void paintComponent(Graphics g)
	{
        setContentAreaFilled(!this.gradientFill);
        setFocusPainted(!this.gradientFill);
		if (this.gradientFill)
		{
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setPaint(new GradientPaint(
                    new Point(0, 0), 
                    gradient1, 
                    new Point(0, getHeight()), 
                    gradient2));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
		}
		else
			this.setBackground(MyFamily.myFamily.getBackground());
		super.paintComponent(g);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if (this.minimumDimension)
		{
			FontMetrics fontMetrics = g.getFontMetrics();
			this.setPreferredSize(new Dimension(fontMetrics.stringWidth(this.getText()) + 2, fontMetrics.getHeight() + 4));
			super.paint(g);
		}
	}
}
