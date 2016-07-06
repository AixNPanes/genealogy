import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class Test
{
	public static final Spring SPRING_0 = Spring.constant(0);

	public static Icon icon = null;

	public class ConstraintObject
	{
		public Container contentPane;
		public SpringLayout.Constraints constraints = null;
		public SpringLayout springLayout = new SpringLayout();
		public Spring xSpring = SPRING_0;
		public Spring ySpring = SPRING_0;
		public Spring maxWidthSpring = SPRING_0;
		public Spring maxHeightSpring = SPRING_0;
		public Spring xPad = null;
		public Spring yPad = null;
		public Spring borderSpring = null;

		private boolean moveRight = false;
		private boolean moveDown = false;

		public ConstraintObject(Container contentPane, int xPad, int yPad, int border)
		{
			this.contentPane = contentPane;
			this.contentPane.setLayout(this.springLayout);
			this.xPad = Spring.constant(xPad);
			this.yPad = Spring.constant(yPad);
			this.borderSpring = Spring.constant(border);
		}

		public Component addRight(JComponent p)
		{
			Component component = this.contentPane.add(p);
			this.constraints = this.springLayout.getConstraints(p);
			this.constraints.setX(this.xSpring);
			this.constraints.setY(this.ySpring);
			this.xSpring = Spring.sum(this.xPad, this.constraints.getConstraint("East"));
			this.maxHeightSpring = Spring.max(this.maxHeightSpring, this.constraints.getConstraint("South"));
			this.moveRight = true;
			return component;
		}

		public Component addDown(JComponent p)
		{
			Component component = this.contentPane.add(p);
			this.constraints = this.springLayout.getConstraints(p);
			this.constraints.setX(this.xSpring);
			this.constraints.setY(this.ySpring);
			this.ySpring = Spring.sum(this.yPad, this.constraints.getConstraint("South"));
			this.maxWidthSpring = Spring.max(this.maxWidthSpring, this.constraints.getConstraint("East"));
			this.moveDown = true;
			return component;
		}

		public Spring sum(Spring... springs)
		{
			Spring s = SPRING_0;
			for(Spring spring:springs)
				s = Spring.sum(s,  spring);
			return s;
		}

		public void setConstraint(String edgeName, Spring... s)
		{
			this.constraints.setConstraint(edgeName, sum(s));
		}

		public void setMinimumSize()
		{
			JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this.contentPane);
			Container container = null;
			if (this.contentPane.equals(frame.getContentPane()))
				container = frame;
			else
				container = this.contentPane;
			container.setMinimumSize(new Dimension(this.constraints.getConstraint("East").getValue(), this.constraints.getConstraint("South").getValue()));
		}

		public void trim()
		{
			this.constraints = this.springLayout.getConstraints(this.contentPane);
			this.setConstraint("East", this.xSpring,  this.moveRight?Spring.sum(this.xPad, this.maxWidthSpring):SPRING_0);
			this.setConstraint("South", this.ySpring, this.moveDown?Spring.sum(this.yPad, this.maxHeightSpring):SPRING_0);
			this.setMinimumSize();
		}
	}

	class ToolBarPanel extends P
	{
		public ToolBarPanel(ConstraintObject constraintObject)
		{
			super(Color.BLUE, constraintObject);

			ConstraintObject p1ConstraintObject = new ConstraintObject(this, 5, 5, 5);

			JLabel toolBarButton1 = new JLabel("L1A");
			JLabel toolBarButton2 = new JLabel("L1B");
			JLabel toolBarButton3 = new JLabel("L1BC");
			JLabel toolBarButton4 = new JLabel("L1D");
//	TODO 3 - Comment out the 4 lines above and uncomment the lines below
//			JButton toolBarButton1 = new JButton(Test.icon);
//			JButton toolBarButton2 = new JButton(Test.icon);
//			JButton toolBarButton3 = new JButton(Test.icon);
//			JButton toolBarButton4 = new JButton(Test.icon);

			p1ConstraintObject.addRight(toolBarButton1);
			p1ConstraintObject.addRight(toolBarButton2);
			p1ConstraintObject.addRight(toolBarButton3);
			p1ConstraintObject.addRight(toolBarButton4);

			p1ConstraintObject.trim();
		}
	}

	class P extends JPanel
	{
		public ConstraintObject constraintObject = null;
		public P(Color borderColor, ConstraintObject constraintObject)
		{
			this.constraintObject = constraintObject;
			this.setBorder(BorderFactory.createLineBorder(borderColor, 10));
			this.constraintObject.addDown(this);
		}

		public P(Color borderColor, Container container)
		{
			this.setBorder(BorderFactory.createLineBorder(borderColor, 10));
			container.add(this);
		}
	}

	class P1 extends P
	{
		JLabel l1a = new JLabel("L1A");
		JLabel l1b = new JLabel("L1B");

		public P1(ConstraintObject constraintObject)
		{
			super(Color.BLUE, constraintObject);

			ConstraintObject p1ConstraintObject = new ConstraintObject(this, 5, 5, 5);
			p1ConstraintObject.addDown(l1a);
			p1ConstraintObject.addDown(l1b);

			p1ConstraintObject.trim();
		}

		public P1(Container container)
		{
			super(Color.BLUE, container);
			this.add(l1a);
			this.add(l1b);
		}
	}

	class P2 extends P
	{
		public P2(ConstraintObject constraintObject)
		{
			this(constraintObject.contentPane);
		}

		public P2(Container container)
		{
			super(Color.GREEN, container);
			JLabel label = new JLabel("L2A");
			this.add(label);
		}

	}

	class P3 extends P
	{
		public P3(ConstraintObject constraintObject)
		{
			this(constraintObject.contentPane);
		}
		public P3(Container container)
		{
			super(Color.RED, container);
			JLabel label = new JLabel("L3A");
			this.add(label);
		}
	}

	private void initComponents1(Container contentPane)
	{
		P1 p1 = new P1(contentPane);
		contentPane.add(p1);

		P2 p2 = new P2(contentPane);
		contentPane.add(p2);

		P3 p3 = new P3(contentPane);
		contentPane.add(p3);

	}

	private void initComponents2(Container contentPane)
	{
		ConstraintObject constraintObject = new ConstraintObject(contentPane, 5, 5, 5);

		P1 toolBar = new P1(constraintObject);
// TODO 2 - comment out the line above and uncomment the line below
//		ToolBarPanel toolBar = new ToolBarPanel(constraintObject);
		constraintObject.addDown(toolBar);
		
		P2 p2 = new P2(constraintObject);
		constraintObject.addDown(p2);
		
		P3 p3 = new P3(constraintObject);
		constraintObject.addDown(p3);

		constraintObject.trim();		
	}

	public Test()
	{
		try {icon = new ImageIcon(ImageIO.read(new File("src/main/resources/icon.png")));}
		catch (IOException e) {throw new RuntimeException(e);}
		
		JFrame frame = new JFrame("Test");
		Container contentPane = frame.getContentPane();

		initComponents1(contentPane);
// TODO
//		initComponents2(contentPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		new Test();
	}
}
