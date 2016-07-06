package ws.daley.genealogy.datasubpanel.plancurrenttree;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ClickableLabel extends JButton
{
	URL url;

	public ClickableLabel(String text, String urlString)
	{
		super(text);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){throw new RuntimeException(e);}
		try {this.url = new URL(urlString);}
		catch (MalformedURLException e1) {};
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setOpaque(true);
		this.getModel().addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e)
			{
				@SuppressWarnings("hiding")
				ButtonModel model = (ButtonModel)e.getSource();
				if (model.isRollover())
					ClickableLabel.this.setBackground(Color.CYAN);
				else
					ClickableLabel.this.setBackground(null);
			}
		});
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(@SuppressWarnings("unused") MouseEvent e)
			{
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
					try {desktop.browse(ClickableLabel.this.url.toURI());}
					catch(Exception e1){}
			}

			@Override public void mousePressed(@SuppressWarnings("unused") MouseEvent e) {}
			@Override public void mouseReleased(@SuppressWarnings("unused") MouseEvent e) {}
			@Override public void mouseEntered(@SuppressWarnings("unused") MouseEvent e) {}
			@Override public void mouseExited(@SuppressWarnings("unused") MouseEvent e) {}
		});
	}
}
