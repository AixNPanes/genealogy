package ws.daley.genealogy.toolbar.button;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyAppArea;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.datapanel.MyDataPanel;
import ws.daley.genealogy.datapanel.MyTabbedPane;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuBar;
import ws.daley.genealogy.shortcutbar.MyShortcutBar;
import ws.daley.genealogy.shortcutbar.MyShortcutButton;

@SuppressWarnings("serial")
public abstract class MyToolbarButton extends JButton implements Action, ActionListener
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyToolbarButton.class);

	public static final File toolbarIconFolder = new File(MyFamily.iconFolder, "toolbar");

	protected List<MyPane> associatedSubPanel = null;
	protected List<MyMenu> associatedMenu = null;
	protected List<MyShortcutButton> associatedShortcutButton = null;
	protected List<MyPane> associatedTabbedPanePanes = null;

	protected MyDataPanel myDataPanel;
	protected MyAppArea myAppArea;
	protected MyTabbedPane myTabbedPane;
	protected MyMenuBar myMenuBar;
	protected MyShortcutBar myShortcutBar;

	@SuppressWarnings("unused")
	private ImageIcon activeIcon = null;
	private void setActiveIcon(File activeIconFile)
	{
		log.trace("Entering setActiveIcon(" + activeIconFile + ")");
		this.activeIcon = new ImageIcon(this.getBufferedImage(activeIconFile));
		log.trace("Exitting setActiveIcon(" + activeIconFile + ")");
	}

	private ImageIcon inactiveIcon = null;
	private void setInactiveIcon(File inactiveIconFile)
	{
		log.trace("Entering setInactiveIcon(" + inactiveIconFile + ")");
		this.inactiveIcon = new ImageIcon(this.getBufferedImage(inactiveIconFile));
		log.trace("Exitting setInactiveIcon(" + inactiveIconFile + ")");
	}

	private ImageIcon hoverIcon = null;
	private void setHoverIcon(File hoverIconFile)
	{
		log.trace("Entering setHoverIcon(" + hoverIconFile + ")");
		this.hoverIcon = new ImageIcon(getBufferedImage(hoverIconFile));
		log.trace("Exitting setHoverIcon(" + hoverIconFile + ")");
	}

	public void setAssociatedEntries(List<MyPane> associatedSubPanel, List<MyMenu> associatedMenu,
			List<MyShortcutButton> associatedShortcutButton, List<MyPane> associatedTabbedPanePanes)
	{
		log.trace("Entering setAssociatedEntries(" + associatedSubPanel + ", " + associatedMenu + ", " + associatedShortcutButton + ", " + associatedTabbedPanePanes + ")");
		this.associatedSubPanel = associatedSubPanel;
		this.associatedMenu = associatedMenu;
		this.associatedShortcutButton = associatedShortcutButton;
		this.associatedTabbedPanePanes = associatedTabbedPanePanes;
		log.trace("Entering setAssociatedEntries(" + associatedSubPanel + ", " + associatedMenu + ", " + associatedShortcutButton + ", " + associatedTabbedPanePanes + ")");
	}

	public void setIcons(File activeIconFile, File inactiveIconFile, File hoverIconFile)
	{
		log.trace("Entering setIcons(" + activeIconFile + ", " + inactiveIconFile + ", " + hoverIconFile + ")");
		setActiveIcon(activeIconFile);
		setInactiveIcon(inactiveIconFile);
		setHoverIcon(hoverIconFile);
		this.setIcon(this.inactiveIcon);
		this.setRolloverIcon(this.hoverIcon);
		log.trace("Exitting setIcons(" + activeIconFile + ", " + inactiveIconFile + ", " + hoverIconFile + ")");
	}

	public MyToolbarButton()
	{
		log.trace("Entering");
		this.addActionListener(this);
		this.setBorder(BorderFactory.createEmptyBorder());
		log.trace("Exitting");
	}

	private BufferedImage getScaledImage(BufferedImage originalImage)
	{
		log.trace(this.getClass().getSimpleName()+ " Entering getScaledImage(" + originalImage.toString() + ")");
		int width = originalImage.getWidth() / 2;
		int height = originalImage.getHeight() / 2;
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = newImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		log.trace(this.getClass().getSimpleName()+ " Exitting getScaledImage(" + originalImage.toString() + ")");
		return newImage;
	}

	private BufferedImage getBufferedImage(File imageFile)
	{
		log.trace(this.getClass().getSimpleName() + " Entering getIcon(" + imageFile.toString() + ")");
		try
		{
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			BufferedImage scaledImage = getScaledImage(bufferedImage);
			log.trace(this.getClass().getSimpleName() + " Exitting getIcon(" + imageFile.toString() + ")");
			return scaledImage;
		}
		catch (IOException e)
		{
			log.trace(this.getClass().getSimpleName() + " Exitting getIcon(" + imageFile.toString() + ") + with exception "+e.toString());
			throw new RuntimeException(imageFile.getAbsolutePath(), e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		log.trace(this.getClass().getSimpleName() + " Entering actionPerformed(" + e.toString() + ")");
		MyFamily.myFamily.myAppArea.myHeaderPanel.myToolBar.deactivateAll();
		MyFamily.myFamily.myAppArea.myTabbedPane.disablePanels();
		for(MyPane myPane:this.associatedSubPanel)
			myPane.setVisible(true);
		for(MyMenu myMenu:this.associatedMenu)
			myMenu.setVisible(true);
		for(MyShortcutButton myShortcutButton:this.associatedShortcutButton)
			myShortcutButton.setVisible(true);
		for(MyPane myPane:this.associatedTabbedPanePanes)
			MyTabbedPane.myTabbedPane.addTab(myPane);
		log.trace(this.getClass().getSimpleName() + " Exitting actionPerformed(" + e.toString() + ")");
	}

	@Override
	public Object getValue(String key)
	{
		log.trace(this.getClass().getSimpleName() + " Entering getValue(" + key + ")");
		log.trace(this.getClass().getSimpleName() + " Exitting getValue(" + key + ")");
		return null;
	}

	@Override
	public void putValue(String key, Object value)
	{
		log.trace(this.getClass().getSimpleName() + " Entering putValue(" + key + ", " + value + ")");
		log.warn("Not Implemented putValue(" + key + ", " + value + ")");
		log.trace(this.getClass().getSimpleName() + " Exitting putValue(" + key + ", " + value + ")");
	}

	public void setAssociatedEntries()
	{
		log.trace("Entering setAssociatedEntries()");
		this.myAppArea = MyFamily.myFamily.myAppArea;
		this.myTabbedPane = this.myAppArea.myTabbedPane;
		this.myMenuBar = MyFamily.myFamily.myMenuBar;
		this.myShortcutBar = this.myAppArea.myHeaderPanel.myShortcutBar;
		log.trace("Exitting setAssociatedEntries()");
	}
}