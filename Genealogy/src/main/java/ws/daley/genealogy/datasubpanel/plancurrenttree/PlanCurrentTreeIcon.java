package ws.daley.genealogy.datasubpanel.plancurrenttree;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.components.family.Sex;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanCurrentTreeIcon extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanCurrentTreeIcon.class);

	private static File sexIconFolder = new File(MyFamily.iconFolder, "sex");

	private BufferedImage sexImage = null;

	public PlanCurrentTreeIcon(Sex sex)
	{
		super(null, null, null);
		log.trace("Entering");
		File imageFile = new File(sexIconFolder, sex.getSex() + ".png");
		try {this.sexImage = Util.scaleImage(ImageIO.read(imageFile));}
		catch (IOException e) {throw new RuntimeException(imageFile.getAbsolutePath(), e);}
		log.trace("Exiting");
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.drawImage(this.sexImage, 0, 0, this);
		g2d.dispose();
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.sexImage.getWidth(), this.sexImage.getHeight());
	}
}
