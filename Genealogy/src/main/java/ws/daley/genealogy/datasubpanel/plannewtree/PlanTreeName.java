package ws.daley.genealogy.datasubpanel.plannewtree;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFileChooser;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.components.family.FamilyLabeledEntryBox;
import ws.daley.genealogy.components.family.SmallLabel;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanTreeName extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanTreeName.class);

	private SpringLayout springLayout = new SpringLayout();
	private static final int xPad = 5;
	private static final int yPad = 5;

	private PlanOpenType planOpenType;

	public FamilyLabeledEntryBox importFileName;
	public PlanButton browseButton;
	public FamilyLabeledEntryBox newTreeName = new FamilyLabeledEntryBox("New tree name:");
	public PlanButton continueButton = new PlanButton("Continue", true);
	public SmallLabel fileLocation = new SmallLabel(-2, "x");
	public PlanButton folderLocationButton = new PlanButton("Folder location...", true);

	public PlanTreeName(PlanOpenType planOpenType)
	{
		super(null, null, null);
		log.trace("Entering");
		this.planOpenType = planOpenType;
		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());

		File folder = new File(".");
		this.fileLocation.setText(folder.getAbsolutePath());
		try {this.fileLocation.setText(folder.getCanonicalPath());}
		catch (IOException e) {}

		if (PlanOpenType.SHOW.equals(planOpenType))
		{
			this.importFileName = new FamilyLabeledEntryBox("File to import:");
			this.browseButton = new PlanButton("Browse", true);
			this.add(this.importFileName);
			this.add(this.browseButton);
		}

		this.add(this.newTreeName);
		this.add(this.continueButton);
		this.add(this.folderLocationButton);
		this.add(this.fileLocation);

		if (PlanOpenType.SHOW.equals(planOpenType))
		{
			this.springLayout.putConstraint(SpringLayout.NORTH, this.importFileName, 0, SpringLayout.NORTH, this);
			this.springLayout.putConstraint(SpringLayout.WEST, this.importFileName, 0, SpringLayout.WEST, this);
			this.springLayout.putConstraint(SpringLayout.NORTH, this.browseButton, this.newTreeName.nameLabel.getPreferredSize().height + 3, SpringLayout.NORTH, this.importFileName);
			this.springLayout.putConstraint(SpringLayout.WEST, this.browseButton, xPad, SpringLayout.EAST, this.importFileName);
			this.springLayout.putConstraint(SpringLayout.NORTH, this.newTreeName, yPad, SpringLayout.SOUTH, this.importFileName);
			this.springLayout.putConstraint(SpringLayout.WEST, this.newTreeName, 0, SpringLayout.WEST, this);
			this.springLayout.putConstraint(SpringLayout.NORTH, this.continueButton, this.newTreeName.nameLabel.getPreferredSize().height + 3, SpringLayout.NORTH, this.newTreeName);
			this.springLayout.putConstraint(SpringLayout.WEST, this.continueButton, xPad, SpringLayout.EAST, this.newTreeName);
			this.springLayout.putConstraint(SpringLayout.NORTH, this.folderLocationButton, PlanTreeName.yPad, SpringLayout.SOUTH, this.newTreeName);
			this.springLayout.putConstraint(SpringLayout.WEST, this.folderLocationButton, 0, SpringLayout.WEST, this);

			this.browseButton.addMouseListener(new MouseListener()
			{
				@Override
				public void mouseClicked(@SuppressWarnings("unused") MouseEvent e)
				{
					final JFileChooser fc = new JFileChooser();
					fc.setSelectedFile(new File(PlanTreeName.this.fileLocation.getText()));
					int returnVal = fc.showOpenDialog(PlanTreeName.this);
					if (returnVal == 0)
					{
						File selectedFile = fc.getSelectedFile();
						PlanTreeName.this.fileLocation.setText(selectedFile.getAbsolutePath());
						String treeName = selectedFile.getName();
						int n = treeName.lastIndexOf(".");
						if (n != -1)
							treeName = treeName.substring(0, n);
						PlanTreeName.this.newTreeName.textField.setText(treeName);
					}
				}

				@SuppressWarnings("unused") @Override public void mousePressed(MouseEvent e) {}
				@SuppressWarnings("unused") @Override public void mouseReleased(MouseEvent e) {}
				@SuppressWarnings("unused") @Override public void mouseEntered(MouseEvent e) {}
				@SuppressWarnings("unused") @Override public void mouseExited(MouseEvent e) {}
			});
			this.continueButton.addMouseListener(new MouseListener()
			{
				@SuppressWarnings("unused")
				private void printNode(int level, Node node)
				{
					System.out.println(new String(new char[level]).replace("\0", " ") + node.getNodeName());
					NodeList nodeList = node.getChildNodes();
					for(int i = 0; i < nodeList.getLength(); i++)
						printNode(level + 1, nodeList.item(i));
				}

				@SuppressWarnings("unused") 
				@Override
				public void mouseClicked(MouseEvent e)
				{
					ButtonGroup buttonGroup = MyFamily.myFamily.myAppArea.myTabbedPane.planNewTreePane.importTreePane.planTreeFileShowPane.planTreeFileTypePane.importGroup;
					ButtonModel buttonModel = buttonGroup.getSelection();
					PlanImportFileType fileTyoe = PlanImportFileType.getType(buttonModel.getActionCommand());
					String fileName = PlanTreeName.this.fileLocation.getText();
					Class<?> clazz = fileTyoe.getFilterClass();
					Constructor<?> constructor;
					InputStream inputStream;
					try
					{
						constructor = clazz.getConstructor(String.class);
						inputStream = (InputStream)constructor.newInstance(fileName);
						int i = 0;
					}
					catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException  e1) {throw new RuntimeException(e1);}
					throw new RuntimeException();
//						MyDocument document = new MyDocument((new MyFamilyXMLParser()).getDocument(inputStream));
//						MyFamily.myFamily.myAppArea.myDocument = document;
				}

				@SuppressWarnings("unused") @Override public void mousePressed(MouseEvent e) {}
				@SuppressWarnings("unused") @Override public void mouseReleased(MouseEvent e) {}
				@SuppressWarnings("unused") @Override public void mouseEntered(MouseEvent e) {}
				@SuppressWarnings("unused") @Override public void mouseExited(MouseEvent e) {}
			});
		}
		else
		{
			this.springLayout.putConstraint(SpringLayout.NORTH, this.newTreeName, 0, SpringLayout.NORTH, this);
			this.springLayout.putConstraint(SpringLayout.WEST, this.newTreeName, 0, SpringLayout.WEST, this);
			this.springLayout.putConstraint(SpringLayout.NORTH, this.continueButton, this.newTreeName.nameLabel.getPreferredSize().height + 3, SpringLayout.NORTH, this);
			this.springLayout.putConstraint(SpringLayout.WEST, this.continueButton, xPad, SpringLayout.EAST, this.newTreeName);
			this.springLayout.putConstraint(SpringLayout.NORTH, this.folderLocationButton, PlanTreeName.yPad, SpringLayout.SOUTH, this.newTreeName);
			this.springLayout.putConstraint(SpringLayout.WEST, this.folderLocationButton, 0, SpringLayout.WEST, this);
		}
		this.springLayout.putConstraint(SpringLayout.NORTH, this.fileLocation, 0, SpringLayout.NORTH, this.folderLocationButton);
		this.springLayout.putConstraint(SpringLayout.WEST, this.fileLocation, yPad, SpringLayout.EAST, this.folderLocationButton);
		this.springLayout.putConstraint(SpringLayout.EAST, this.fileLocation, 0, SpringLayout.EAST, this);
		
		this.continueButton.setEnabled(false);
		this.newTreeName.textField.getDocument().addDocumentListener(new DocumentListener()
		{
			public void setButtonEnabled()
			{
				PlanTreeName.this.continueButton.setEnabled(PlanTreeName.this.newTreeName.textField.getText().length() > 0);
			}

			@SuppressWarnings("unused") @Override public void insertUpdate(DocumentEvent e) {setButtonEnabled();}
			@SuppressWarnings("unused") @Override public void removeUpdate(DocumentEvent e) {setButtonEnabled();}
			@SuppressWarnings("unused") @Override public void changedUpdate(DocumentEvent e) {throw new RuntimeException("changedUpdate not implemented");}
		});

		this.folderLocationButton.addMouseListener(new MouseListener()
		{
			@SuppressWarnings("unused") 
			@Override
			public void mouseClicked(MouseEvent e)
			{
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setSelectedFile(new File(PlanTreeName.this.fileLocation.getText()));
				int returnVal = fc.showOpenDialog(PlanTreeName.this);
				if (returnVal == 0)
					PlanTreeName.this.fileLocation.setText(fc.getSelectedFile().getAbsolutePath());
			}

			@SuppressWarnings("unused") @Override public void mousePressed(MouseEvent e) {}
			@SuppressWarnings("unused") @Override public void mouseReleased(MouseEvent e) {}
			@SuppressWarnings("unused") @Override public void mouseEntered(MouseEvent e) {}
			@SuppressWarnings("unused") @Override public void mouseExited(MouseEvent e) {}
		});
		log.trace("Exiting");
	}

	@Override
	public Dimension getPreferredSize()
	{
		boolean b = PlanOpenType.SHOW.equals(this.planOpenType);
		int treenameWidth = this.newTreeName.getPreferredSize().width;
		int continueButtonWidth = this.continueButton.getPreferredSize().width;
		int fileLocationControlWidth = this.fileLocation.getPreferredSize().width;
		int folderLocationButtonWidth = this.folderLocationButton.getPreferredSize().width;
		int importFileNameWidth = b?this.importFileName.getPreferredSize().width:0;
		int browseButtonWidth = b?this.browseButton.getPreferredSize().width:0;
		int fileLocationWidth = fileLocationControlWidth + folderLocationButtonWidth +
				(b?(importFileNameWidth + browseButtonWidth):0);
		int width = Util.getMax(treenameWidth + continueButtonWidth,
				fileLocationWidth);
		int treenameHeight = this.newTreeName.getPreferredSize().height;
		int continueButtonHeight = this.continueButton.getPreferredSize().height;
		int fileLocationHeight = this.fileLocation.getPreferredSize().height;
		int folderLocationButtonHeight = this.folderLocationButton.getPreferredSize().height;
		int importFilenameHeight = (b?(this.importFileName.getPreferredSize().height + this.browseButton.getPreferredSize().height) + yPad:0);
		int height = treenameHeight + continueButtonHeight + fileLocationHeight + folderLocationButtonHeight + yPad * 3 +
				importFilenameHeight;
		return new Dimension(
			width,	
			height);
	}
}
