package ws.daley.genealogy.components.family;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Separator extends JPanel
{
	private int myWidth = 0;
	private int myHeight = 0;

	public void setMyWidth(int width) {this.myWidth = width;}
	public void setMyHeight(int height) {this.myHeight = height;}
	
	public Separator()
	{	
		this(0, 0);
	}

	public Separator(int width)
	{	
		this(width, 0);
	}

	public Separator(int width, int height)
	{	
		this.myWidth = width;
		this.myHeight = height;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);
		g2d.dispose();
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(Math.max(1, this.myWidth), Math.max(1, this.myHeight));
	}
}
