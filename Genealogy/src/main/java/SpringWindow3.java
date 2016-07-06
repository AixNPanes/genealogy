

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class SpringWindow3 extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static Spring xPad = Spring.constant(5);
	private static Spring ySpring = Spring.constant(5);
	private static Spring xSpring = xPad;
	private static Spring maxHeightSpring = Spring.constant(0);

	private JButton button1 = new JButton("Button 1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("Button 3");
	private JButton button4 = new JButton("Long-Named Button 4");
	private JButton button5 = new JButton("Button 5");

	public SpringWindow3()
    {
        SpringLayout.Constraints constraints = null;
		Container contentPane = getContentPane();
        contentPane.setLayout(new SpringLayout());
        SpringLayout layout = (SpringLayout)contentPane.getLayout();
   
        contentPane.add(this.button1);
        constraints = layout.getConstraints(this.button1);
        constraints.setX(xSpring);
        xSpring = Spring.sum(xPad, constraints.getConstraint("East")); //use proxy instead?
        constraints.setY(ySpring);
        maxHeightSpring = Spring.max(maxHeightSpring, constraints.getConstraint("South"));

        contentPane.add(this.button2);
        constraints = layout.getConstraints(this.button2);
        constraints.setX(xSpring);
        xSpring = Spring.sum(xPad, constraints.getConstraint("East")); //use proxy instead?
        constraints.setY(ySpring);
        maxHeightSpring = Spring.max(maxHeightSpring, constraints.getConstraint("South"));

        contentPane.add(this.button3);
        constraints = layout.getConstraints(this.button3);
        constraints.setX(xSpring);
        xSpring = Spring.sum(xPad, constraints.getConstraint("East")); //use proxy instead?
        constraints.setY(ySpring);
        maxHeightSpring = Spring.max(maxHeightSpring, constraints.getConstraint("South"));

        contentPane.add(this.button4);
        constraints = layout.getConstraints(this.button4);
        constraints.setX(xSpring);
        xSpring = Spring.sum(xPad, constraints.getConstraint("East")); //use proxy instead?
        constraints.setY(ySpring);
        maxHeightSpring = Spring.max(maxHeightSpring, constraints.getConstraint("South"));

        contentPane.add(this.button5);
        constraints = layout.getConstraints(this.button5);
        constraints.setX(xSpring);
        xSpring = Spring.sum(xPad, constraints.getConstraint("East")); //use proxy instead?
        constraints.setY(ySpring);
        maxHeightSpring = Spring.max(maxHeightSpring, constraints.getConstraint("South"));

//        Component[] components = contentPane.getComponents();
//
//        // Make every component 5 pixels away from the component to its left.
//        for (int i = 0; i < components.length; i++)
//        {
//            constraints = springLayout.getConstraints(components[i]);
//            constraints.setX(myXSpring);
//            myXSpring = Spring.sum(xPad, constraints.getConstraint("East")); //use proxy instead?
//            constraints.setY(myYSpring);
//            myMaxHeightSpring = Spring.max(myMaxHeightSpring, constraints.getConstraint("South"));
//        }

        // Make the window's preferred size depend on its components.
        SpringLayout.Constraints pCons = layout.getConstraints(contentPane);

        // This doesn't work.  Why not?
        //pCons.setWidth(myXSpring);
        //pCons.setHeight(Spring.sum(myMaxHeightSpring, myYSpring));

        // Neither does this (just checking).
        // pCons.setConstraint("Width", myXSpring);
        // pCons.setConstraint("Height", Spring.sum(myMaxHeightSpring, myYSpring));

        // This works.  Why?
        pCons.setConstraint("East", xSpring);
        pCons.setConstraint("South", Spring.sum(maxHeightSpring, ySpring));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[])
    {
        SpringWindow3 window = new SpringWindow3();

        window.setTitle("SpringLayout");
        window.pack();
        window.setVisible(true);
    }
}