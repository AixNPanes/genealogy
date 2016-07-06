package ws.daley.genealogy.shortcutbar;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.components.family.FamilyLabeledEntryBox;

@SuppressWarnings("serial")
public class MyShortcutButton extends JButton implements Action, ActionListener
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FamilyLabeledEntryBox.class);

	public static final File shortcutIconFolder = new File(MyFamily.iconFolder, "shortcutbar");

//	private DataSubPanel associatedSubPanel = null;
	private Icon activeIcon = null;
	private Icon inactiveIcon = null;
	private Icon hoverIcon = null;
	public int maxIconHeight = 0;

	public MyShortcutButton(
			File activeIconFile,
			File inactiveIconFile,
			File hoverIconFile)
	{
		log.trace("Entering");
		this.activeIcon = getIcon(activeIconFile);
		this.inactiveIcon = getIcon(inactiveIconFile);
		this.hoverIcon = getIcon(hoverIconFile);
		setMaxSize(this.activeIcon, this.inactiveIcon, this.hoverIcon);
		this.activeIcon.getIconWidth();
		this.setInactiveIcon();
		this.setRolloverIcon(this.hoverIcon);
		this.addActionListener(this);
		log.trace("Exitting");
	}

	private void setMaxSize(Icon...icons )
	{
        log.trace(this.getClass().getSimpleName() + " Entering setMaxSize(" + icons + ")");
		for(Icon icon:icons)
			this.maxIconHeight = Math.max(this.maxIconHeight, icon.getIconHeight());
        log.trace(this.getClass().getSimpleName() + " Exitting setMaxSize(" + icons + ")");
	}

	public void setActiveIcon()
	{
        log.trace(this.getClass().getSimpleName() + " Entering setActiveIcon()");
		this.setIcon(this.activeIcon);
        log.trace(this.getClass().getSimpleName() + " Exitting setActiveIcon()");
	}

	public void setInactiveIcon()
	{
        log.trace(this.getClass().getSimpleName() + " Entering setInactiveIcon()");
		this.setIcon(this.inactiveIcon);
        log.trace(this.getClass().getSimpleName() + " Exitting setInactiveIcon()");
	}

	private BufferedImage getScaledImage(BufferedImage originalImage)
	{
        log.trace(this.getClass().getSimpleName() + " Entering getScaledImage(" + originalImage + ")");
		int width = originalImage.getWidth() / 2;
		int height = originalImage.getHeight() / 2;
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = newImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
        log.trace(this.getClass().getSimpleName() + " Exitting getScaledImage(" + originalImage + ")");
		return newImage;
	}

	private Icon getIcon(File imageFile)
	{
        log.trace(this.getClass().getSimpleName() + " Entering getIcon(" + imageFile + ")");
		try
		{
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			BufferedImage scaledImage = getScaledImage(bufferedImage);
			ImageIcon imageIcon = new ImageIcon(scaledImage);
	        log.trace(this.getClass().getSimpleName() + " Entering getIcon(" + imageFile + ")");
			return imageIcon;
		}
		catch (IOException e)
		{
			System.out.println(imageFile.getAbsolutePath());
			throw new RuntimeException(imageFile.getAbsolutePath(), e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
        log.trace(this.getClass().getSimpleName() + " Entering actionPerformed(" + e + ")");
//		MyToolBar.deactivateAll();
//		MyDataPanel.disablePanels();
//		this.setActiveIcon();
//		if (this.associatedSubPanel != null)
//			this.associatedSubPanel.setPanelEnabled(true);
        log.trace(this.getClass().getSimpleName() + " Exitting actionPerformed(" + e + ")");
	}

	@SuppressWarnings("unused")
	@Override
	public Object getValue(String key) {return null;}

	@SuppressWarnings("unused")
	@Override
	public void putValue(String key, Object value) {}
}
