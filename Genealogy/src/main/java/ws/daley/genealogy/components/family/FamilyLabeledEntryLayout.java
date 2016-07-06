package ws.daley.genealogy.components.family;

import java.awt.AWTError;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.beans.ConstructorProperties;

import javax.swing.MySizeRequirements;

public class FamilyLabeledEntryLayout implements LayoutManager2
{
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	public static final int LINE_AXIS = 2;
	public static final int PAGE_AXIS = 3;

	private int axis;
	private Container target;
	private transient MySizeRequirements[] xChildren;
	private transient MySizeRequirements[] yChildren;
	private transient MySizeRequirements xTotal;
	private transient MySizeRequirements yTotal;

	@ConstructorProperties({"target", "axis"})
    public FamilyLabeledEntryLayout(Container target, int axis)
	{
        if (axis != Y_AXIS)
            throw new AWTError("Invalid axis");
        this.axis = axis;
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
			throw new AWTError("FamilyLabeledEntryLayout can't be shared");
	}

	private int resolveAxis( @SuppressWarnings("hiding") int axis, @SuppressWarnings("unused") ComponentOrientation o )
	{
		int absoluteAxis;
		absoluteAxis = axis;
		return absoluteAxis;
	}

	void checkRequests()
	{
		if (this.xChildren == null || this.yChildren == null)
		{
			// The requests have been invalidated... recalculate
			// the request information.
			int n = this.target.getComponentCount();
			this.xChildren = new MySizeRequirements[n];
			this.yChildren = new MySizeRequirements[n];
			for (int i = 0; i < n; i++)
			{
				Component c = this.target.getComponent(i);
				if (!c.isVisible())
				{
					this.xChildren[i] = new MySizeRequirements(0,0,0, c.getAlignmentX());
					this.yChildren[i] = new MySizeRequirements(0,0,0, c.getAlignmentY());
					continue;
				}
				Dimension min = c.getMinimumSize();
				Dimension typ = c.getPreferredSize();
				Dimension max = c.getMaximumSize();
				this.xChildren[i] = new MySizeRequirements(min.width, typ.width, max.width, c.getAlignmentX());
				this.yChildren[i] = new MySizeRequirements(min.height, typ.height, max.height, c.getAlignmentY());
			}
			
			// Resolve axis to an absolute value (either X_AXIS or Y_AXIS)
			int absoluteAxis = resolveAxis(this.axis,this.target.getComponentOrientation());
			
			if (absoluteAxis == X_AXIS)
			{
				this.xTotal = MySizeRequirements.getTiledSizeRequirements(this.xChildren);
				this.yTotal = MySizeRequirements.getAlignedSizeRequirements(this.yChildren);
			}
			else
			{
				this.xTotal = MySizeRequirements.getAlignedSizeRequirements(this.xChildren);
				this.yTotal = MySizeRequirements.getTiledSizeRequirements(this.yChildren);
			}
		}
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		Dimension size;
		synchronized(this)
		{
			checkContainer(parent);
			checkRequests();
			size = new Dimension(this.xTotal.preferred, this.yTotal.preferred);
		}
		
		Insets insets = parent.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left + insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top + insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	@Override
	public Dimension minimumLayoutSize(@SuppressWarnings("hiding") Container target)
	{
		Dimension size;
		synchronized(this)
		{
			checkContainer(target);
			checkRequests();
			size = new Dimension(this.xTotal.minimum, this.yTotal.minimum);
		}
		
		Insets insets = target.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left + insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top + insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	@Override
	public void layoutContainer(Container parent)
	{
		checkContainer(parent);
		int nChildren = parent.getComponentCount();
		int[] xOffsets = new int[nChildren];
		int[] xSpans = new int[nChildren];
		int[] yOffsets = new int[nChildren];
		int[] ySpans = new int[nChildren];
		
		Dimension alloc = parent.getSize();
		Insets in = parent.getInsets();
		alloc.width -= in.left + in.right;
		alloc.height -= in.top + in.bottom;
		
		// Resolve axis to an absolute value (either X_AXIS or Y_AXIS)
		ComponentOrientation o = parent.getComponentOrientation();
		int absoluteAxis = resolveAxis( this.axis, o );
		boolean ltr = (absoluteAxis != this.axis) ? o.isLeftToRight() : true;
		
		
		// determine the child placements
		synchronized(this)
		{
			checkRequests();
			
			if (absoluteAxis == X_AXIS)
			{
				MySizeRequirements.calculateTiledPositions(alloc.width, this.xTotal, this.xChildren, xOffsets, xSpans, ltr);
				MySizeRequirements.calculateAlignedPositions(alloc.height, this.yTotal, this.yChildren, yOffsets, ySpans);
			}
			else
			{
				MySizeRequirements.calculateAlignedPositions(alloc.width, this.xTotal, this.xChildren, xOffsets, xSpans, ltr);
				MySizeRequirements.calculateTiledPositions(alloc.height, this.yTotal, this.yChildren, yOffsets, ySpans);
			}
		}
	
		// flush changes to the container
		for (int i = 0; i < nChildren; i++)
		{
			Component c = parent.getComponent(i);
			c.setBounds((int) Math.min((long) in.left + (long) xOffsets[i], Integer.MAX_VALUE),
				(int) Math.min((long) in.top + (long) yOffsets[i], Integer.MAX_VALUE),
				xSpans[i], ySpans[i]);
		}
	}

	@Override
	public void addLayoutComponent(Component comp, @SuppressWarnings("unused") Object constraints)
	{
		invalidateLayout(comp.getParent());
	}

	@Override
	public Dimension maximumLayoutSize(@SuppressWarnings("hiding") Container target)
	{
		Dimension size;
		synchronized(this)
		{
			checkContainer(target);
			checkRequests();
			size = new Dimension(this.xTotal.maximum, this.yTotal.maximum);
		}
		
		Insets insets = target.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left + insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top + insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	@Override
	public float getLayoutAlignmentX(@SuppressWarnings("hiding") Container target)
	{
		checkContainer(target);
		checkRequests();
		return this.xTotal.alignment;
	}

	@Override
	public float getLayoutAlignmentY(@SuppressWarnings("hiding") Container target)
	{
		checkContainer(target);
		checkRequests();
		return this.yTotal.alignment;
	}

	@Override
	public void invalidateLayout(@SuppressWarnings("hiding") Container target)
	{
		checkContainer(target);
		this.xChildren = null;
		this.yChildren = null;
		this.xTotal = null;
		this.yTotal = null;
	}
}
