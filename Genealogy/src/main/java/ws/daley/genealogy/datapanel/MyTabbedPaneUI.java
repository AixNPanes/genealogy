package ws.daley.genealogy.datapanel;

import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

public class MyTabbedPaneUI extends BasicTabbedPaneUI
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyTabbedPaneUI.class);

//	private String fontFace = null;
//	private int tabMargins = -1;
//	private Dimension tabSize = new Dimension(0, 0);

	public static GeneralPath rectangleToPath(Rectangle rect)
    {
		log.trace("Entering");
    	GeneralPath path = new GeneralPath(Path2D.WIND_EVEN_ODD, 4);
    	path.moveTo(rect.x, rect.y + rect.height);
    	path.lineTo(rect.x, rect.y + 3);
    	path.lineTo(rect.x + 3, rect.y);
    	path.lineTo(rect.x + rect.width - 3, rect.y);
    	path.lineTo(rect.x + rect.width, rect.y + 3);
    	path.lineTo(rect.x + rect.width, rect.y + rect.height);
    	log.trace("Exitting");
        return path;
    }
//
//	private Dimension getTabSize(Graphics2D g2d)
//	{
//		if (this.fontFace == null)
//		{
//			this.fontRenderContext = g2d.getFontRenderContext();
//			for(MyPane myPane:MyFamily.myFamily.myAppArea.myDataPanel.myTabbedPane.dataPanelList)
//			{
//				Rectangle2D bounds = myPane.textLayout.getBounds();
//				System.out.println("\"" + myPane.getTitle() + "\" -> " + bounds);;
//				if (bounds.getWidth() > this.tabSize.width)
//					this.tabSize.width = (int)bounds.getWidth();
//				if (bounds.getHeight() > this.tabSize.height)
//					this.tabSize.height = (int)bounds.getHeight();
//			}
//			this.tabSize = new Dimension(this.tabSize.width + this.tabMargins * 2, this.tabSize.height + this.tabMargins * 2);
//			System.out.println(this.tabSize);
//		}
//		return this.tabSize;
//	}

//	protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect)
//	{
//    	Graphics2D g2d = (Graphics2D)g.create();
//    	Dimension tabSize = getTabSize(g2d);
//    	Rectangle rect = new Rectangle(rects[tabIndex].getLocation(), tabSize);
//    	rect.x = rects[0].x + rect.width * tabIndex;
//        g2d.setFont(tr);
//		FontMetrics fm = getFontMetrics();
//		Icon icon = getIconForTab(tabIndex);
//		GeneralPath path = rectangleToPath(rect);
//		JTabbedPane tp = this.tabPane;
//		String title = tp.getTitleAt(tabIndex);
//		boolean isSelected = tabIndex == this.tabPane.getSelectedIndex();
////		g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
////		layoutLabel(tabPlacement, fm, tabIndex, title, icon, rect, iconRect, textRect, isSelected);
////		paintText(g2d, tabPlacement, this.tabPane.getFont(), fm, tabIndex, title, textRect, isSelected);
//		g2d.drawString(title, rect.x + this.tabMargins, rect.y+rect.height - this.tabMargins);
//		g2d.draw(path);
//		g2d.dispose();
//	}

//	protected void paintTab2(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect)
//	{
//		Rectangle rect = rects[tabIndex];
//		boolean isSelected = tabIndex == this.tabPane.getSelectedIndex();
//		
//		// Paint border.
//		paintTabBorder(g, tabPlacement, tabIndex, rect.x, rect.y, rect.width, rect.height, isSelected);
//		
//		// Layout label.
//		FontMetrics fm = getFontMetrics();
//		Icon icon = getIconForTab(tabIndex);
//		String title = this.tabPane.getTitleAt(tabIndex);
//		layoutLabel(tabPlacement, fm, tabIndex, title, icon, rect, iconRect, textRect, isSelected);
//		// Paint the text.
//		paintText(g, tabPlacement, this.tabPane.getFont(), fm, tabIndex, title, textRect, isSelected);
//		
//		// Paint icon if necessary.
//		paintIcon(g, tabPlacement, tabIndex, icon, iconRect, isSelected);
//		
//		// Paint focus indicator.
//		paintFocusIndicator(g, tabPlacement, rects, tabIndex, iconRect, textRect, isSelected);
//	}
}
