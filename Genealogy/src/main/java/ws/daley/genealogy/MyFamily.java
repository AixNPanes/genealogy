package ws.daley.genealogy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.Painter;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuBar;
import ws.daley.genealogy.state.MyFamilyFile;
import ws.daley.genealogy.state.MyFamilyState;

@SuppressWarnings("serial")
public class MyFamily extends JFrame implements RefreshData
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyFamily.class);

	public static File resourceFolder = new File("src/main/resources");
	public static File iconFolder = new File(resourceFolder, "icons");

	public static MyFamily myFamily = null;

	public MyMenuBar myMenuBar;
	public MyAppArea myAppArea;
	public MyFamilyState myFamilyState;
	public MyFamilyFile myFamilyFile;

	public MyFamily()
	{
		log.trace("Entering");
		myFamily = this;
		this.myFamilyState = MyFamilyState.getState("myfamily.ini");
		MyFamilyState.putState(this.myFamilyState, "myfamily.ini");
		this.setBackground(new Color(217, 214, 182));
		this.myMenuBar = new MyMenuBar();
		setJMenuBar(this.myMenuBar);
		this.myAppArea = new MyAppArea();
		this.myAppArea.setAssociatedEntries();
		this.setTitle("My family");

		this.add(this.myAppArea);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.pack();
		this.setVisible(true);
		this.myAppArea.myHeaderPanel.myToolBar.initialDoClick();
		this.setSize(new Dimension(1000, 800));
		this.setPreferredSize(new Dimension(1000, 800));
		log.trace("Exitting");
	}

	public static class BackgroundPainter implements Painter<JComponent>
	{
		private Color color = null;
		BackgroundPainter(Color c) {this.color = c;}

		@Override
		public void paint(Graphics2D g, @SuppressWarnings("unused") JComponent object, int width, int height)
		{
			if (this.color != null)
			{
				Graphics2D g2d = (Graphics2D)g.create();
				g2d.setColor(this.color);
				g2d.fillRect(0,  0,  width - 1, height - 1);
				g2d.dispose();
			}
		}
	}

	public static void main(String[] args)
	{
		myFamily = new MyFamily();
	}

	@Override
	public void refreshData()
	{
		this.myAppArea.refreshData();
	}
}
