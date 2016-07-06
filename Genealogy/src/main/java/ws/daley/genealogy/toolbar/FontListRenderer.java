package ws.daley.genealogy.toolbar;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.plaf.FontUIResource;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class FontListRenderer extends DefaultListCellRenderer
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FontListRenderer.class);

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
	        int index, boolean isSelected, boolean cellHasFocus)
	{
		log.trace("Entering");
		Component c = super.getListCellRendererComponent(list, value, index, isSelected,
		        cellHasFocus);
		Font f = c.getFont();
		Font newFont = new FontUIResource((String)value, f.getStyle(), f.getSize());
		c.setFont(newFont);
		log.trace("Exitting");
		return c;
	}
}
