package ws.daley.genealogy.components.family;

import java.awt.AWTError;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.beans.ConstructorProperties;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

public class FamilyFamilyEntryLayout implements LayoutManager2
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FamilyLabeledEntryBox.class);

	private static final int INDIVIDUAL = 0;
	private static final int FATHER = 1;
	private static final int MOTHER = 2;

	private static int xpad = 25;
	private static int ypad = 1;

	private Container target;

	@ConstructorProperties({"target"})
	public FamilyFamilyEntryLayout(Container target)
	{
		this.target = target;
	}

	@Override
	public void addLayoutComponent(@SuppressWarnings("unused") String name, Component comp)
	{
		invalidateLayout(comp.getParent());
	}

	@Override
	public void removeLayoutComponent(Component comp)
	{
		invalidateLayout(comp.getParent());
	}

	void checkContainer(@SuppressWarnings("hiding") Container target)
	{
		if (this.target != target)
			throw new AWTError("FamilyFamilyEntryLayout can't be shared");
	}

	private Dimension getMaxSize(Dimension... dimensions)
	{
		Dimension d = new Dimension(0, 0);
		for(Dimension dimension:dimensions)
			d.setSize((int)Math.max(d.getWidth(), dimension.getWidth()), (int)Math.max(d.getHeight(), dimension.getWidth()));
		return d;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		Dimension size;
		synchronized(this)
		{
			checkContainer(parent);
			Dimension individualSize = parent.getComponent(INDIVIDUAL).getPreferredSize();
			Dimension fatherSize = parent.getComponent(FATHER).getPreferredSize();
			Dimension motherSize = parent.getComponent(MOTHER).getPreferredSize();
			Dimension maxSize = getMaxSize(individualSize, fatherSize, motherSize);
			size = new Dimension(
					(int)(maxSize.getWidth() * 2 + xpad),
					(int)(maxSize.getHeight() * 2 + ypad));
		}
		
		Insets insets = parent.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left + insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top + insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		Dimension size;
		synchronized(this)
		{
			Dimension individualSize = parent.getComponent(INDIVIDUAL).getMinimumSize();
			Dimension fatherSize = parent.getComponent(FATHER).getMinimumSize();
			Dimension motherSize = parent.getComponent(MOTHER).getMinimumSize();
			size = new Dimension(
					(int)(Math.max(fatherSize.getWidth(), motherSize.getWidth()) + individualSize.getWidth() + xpad),
					(int)(fatherSize.getHeight() + motherSize.getHeight() + ypad));
		}
		
		Insets insets = parent.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left + insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top + insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	@Override
	public Dimension maximumLayoutSize(Container parent)
	{
		Dimension size;
		synchronized(this)
		{
			checkContainer(parent);
			Dimension individualSize = parent.getComponent(INDIVIDUAL).getMaximumSize();
			Dimension fatherSize = parent.getComponent(FATHER).getMaximumSize();
			Dimension motherSize = parent.getComponent(MOTHER).getMaximumSize();
			size = new Dimension(
					(int)(Math.max(fatherSize.getWidth(), motherSize.getWidth()) + individualSize.getWidth() + xpad),
					(int)(fatherSize.getHeight() + motherSize.getHeight() + ypad));
		}
		
		Insets insets = parent.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left + insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top + insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	private void setFamilyBounds(@SuppressWarnings("unused") Container parent, Component individual, Component father, Component mother, Dimension xDimension, Dimension yDimension)
	{
		father.setBounds((int)xDimension.getWidth() + xpad, 
				0, 
				(int)xDimension.getWidth(), 
				(int)yDimension.getHeight());
		mother.setBounds(
				(int)xDimension.getWidth() + xpad, 
				(int)yDimension.getHeight() + ypad, 
				(int)xDimension.getWidth(), 
				(int)yDimension.getHeight());
		individual.setBounds(
				0, 
				(father.getY() + mother.getY()) /2, 
				(int)xDimension.getWidth(), 
				(int)yDimension.getHeight());
	}

	@Override
	public void layoutContainer(Container parent)
	{
		log.debug(this.getClass().getSimpleName() + ".layoutContainer(" + parent + ")");
		Dimension parentSize = parent.getSize();
		Dimension minimumLayout = minimumLayoutSize(parent);
		Dimension preferredLayout = preferredLayoutSize(parent);

		Component individual = parent.getComponent(INDIVIDUAL);
		Component father = parent.getComponent(FATHER);
		Component mother = parent.getComponent(MOTHER);
		if (parentSize.getWidth() >= preferredLayout.getWidth()) 
		{
			if (parentSize.getHeight() >= preferredLayout.getHeight())
			{
				Dimension preferred = getMaxSize(individual.getPreferredSize(), father.getPreferredSize(), mother.getPreferredSize());
				setFamilyBounds(parent, individual, father, mother, preferred, preferred);
			}
			else
			{
				Dimension preferred = getMaxSize(individual.getPreferredSize(), father.getPreferredSize(), mother.getPreferredSize());
				Dimension minimum = getMaxSize(individual.getMinimumSize(), father.getMinimumSize(), mother.getMinimumSize());
				setFamilyBounds(parent, individual, father, mother, preferred, minimum);
			}
		}
		else if (parentSize.getWidth() >= minimumLayout.getWidth() && parentSize.getHeight() >= minimumLayout.getHeight())
		{
			father.setBounds((int)individual.getPreferredSize().getWidth() + xpad, 
					0, 
					(int)father.getPreferredSize().getWidth(), 
					(int)father.getPreferredSize().getHeight());
			mother.setBounds(
					(int)individual.getPreferredSize().getWidth() + xpad, 
					(int)father.getPreferredSize().getHeight() + ypad, 
					(int)mother.getPreferredSize().getWidth(), 
					(int)mother.getPreferredSize().getHeight());
			individual.setBounds(
					0, 
					(father.getY() + mother.getY()) /2, 
					(int)individual.getPreferredSize().getWidth(), 
					(int)individual.getPreferredSize().getHeight());
		}
		else
		{
			throw new RuntimeException("not implemented");
		}
	}

	@Override
	public void addLayoutComponent(Component comp, @SuppressWarnings("unused") Object constraints)
	{
		invalidateLayout(comp.getParent());
	}

	@Override
	public float getLayoutAlignmentX(@SuppressWarnings("hiding") Container target)
	{
		checkContainer(target);
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(@SuppressWarnings("hiding") Container target)
	{
		checkContainer(target);
		return 0;
	}

	@Override
	public void invalidateLayout(@SuppressWarnings("hiding") Container target)
	{
		checkContainer(target);
	}
}
