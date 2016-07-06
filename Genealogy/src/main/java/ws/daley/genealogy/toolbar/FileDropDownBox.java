package ws.daley.genealogy.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.state.MyFamilyFile;

@SuppressWarnings("serial")
public class FileDropDownBox extends JComboBox<String>
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FileDropDownBox.class);

	public FileDropDownBox()
	{
		log.trace("Entering");
		for(String fileName:MyFamily.myFamily.myFamilyState.getFileNameList())
		{
			this.addItem(fileName);
			System.out.println("filename="+fileName);
		}
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				@SuppressWarnings("unchecked")
				JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
				String fileName = (String)comboBox.getSelectedItem();
				if (! "".equals(fileName))
					MyFamily.myFamily.myFamilyFile = MyFamilyFile.getJson(fileName);
			}});
		log.trace("Exitting");
	}
}
