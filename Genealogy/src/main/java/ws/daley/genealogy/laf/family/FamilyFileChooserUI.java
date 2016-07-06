/*
 * Copyright (c) 1998, 2015, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package ws.daley.genealogy.laf.family;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;

import javax.accessibility.AccessibleContext;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDirectoryModel;
import javax.swing.plaf.basic.BasicFileChooserUI;

import sun.awt.shell.ShellFolder;
import sun.swing.FilePane;
import sun.swing.SwingUtilities2;

/**
 * Family L&amp;F implementation of a FileChooser.
 *
 * @author Jeff Dinkins
 */
public class FamilyFileChooserUI extends BasicFileChooserUI
{

	// Much of the Family UI for JFilechooser is just a copy of
	// the windows implementation, but using Family themed buttons, lists,
	// icons, etc. We are planning a complete rewrite, and hence we've
	// made most things in this class private.

	private JLabel lookInLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox directoryComboBox;
	private DirectoryComboBoxModel directoryComboBoxModel;
	private Action directoryComboBoxAction = new DirectoryComboBoxAction();

	private FilterComboBoxModel filterComboBoxModel;

	private JTextField fileNameTextField;

	private FilePane filePane;
	private JToggleButton listViewButton;
	private JToggleButton detailsViewButton;

	private JButton approveButton;
	private JButton cancelButton;

	private JPanel buttonPanel;
	private JPanel bottomPanel;

	@SuppressWarnings("rawtypes")
	private JComboBox filterComboBox;

	private static final Dimension hstrut5 = new Dimension(5, 1);
	@SuppressWarnings("unused")
	private static final Dimension hstrut11 = new Dimension(11, 1);

	private static final Dimension vstrut5 = new Dimension(1, 5);

	private static final Insets shrinkwrap = new Insets(0, 0, 0, 0);

	// Preferred and Minimum sizes for the dialog box
	private static int PREF_WIDTH = 500;
	private static int PREF_HEIGHT = 326;
	private static Dimension PREF_SIZE = new Dimension(PREF_WIDTH, PREF_HEIGHT);

	private static int MIN_WIDTH = 500;
	private static int MIN_HEIGHT = 326;
	private static int LIST_PREF_WIDTH = 405;
	private static int LIST_PREF_HEIGHT = 135;
	private static Dimension LIST_PREF_SIZE = new Dimension(LIST_PREF_WIDTH, LIST_PREF_HEIGHT);

	// Labels, mnemonics, and tooltips (oh my!)
	private int lookInLabelMnemonic = 0;
	private String lookInLabelText = null;
	private String saveInLabelText = null;

	private int fileNameLabelMnemonic = 0;
	private String fileNameLabelText = null;
	private int folderNameLabelMnemonic = 0;
	private String folderNameLabelText = null;

	private int filesOfTypeLabelMnemonic = 0;
	private String filesOfTypeLabelText = null;

	private String upFolderToolTipText = null;
	private String upFolderAccessibleName = null;

	private String homeFolderToolTipText = null;
	private String homeFolderAccessibleName = null;

	private String newFolderToolTipText = null;
	private String newFolderAccessibleName = null;

	private String listViewButtonToolTipText = null;
	private String listViewButtonAccessibleName = null;

	private String detailsViewButtonToolTipText = null;
	private String detailsViewButtonAccessibleName = null;

	private AlignedLabel fileNameLabel;

	private void populateFileNameLabel()
	{
		if (getFileChooser().getFileSelectionMode() == JFileChooser.DIRECTORIES_ONLY)
		{
			this.fileNameLabel.setText(this.folderNameLabelText);
			this.fileNameLabel.setDisplayedMnemonic(this.folderNameLabelMnemonic);
		}
		else
		{
			this.fileNameLabel.setText(this.fileNameLabelText);
			this.fileNameLabel.setDisplayedMnemonic(this.fileNameLabelMnemonic);
		}
	}

	//
	// ComponentUI Interface Implementation methods
	//
	public static ComponentUI createUI(JComponent c)
	{
		return new FamilyFileChooserUI((JFileChooser) c);
	}

	public FamilyFileChooserUI(JFileChooser filechooser)
	{
		super(filechooser);
	}

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);
	}

	@Override
	public void uninstallComponents(JFileChooser fc)
	{
		fc.removeAll();
		this.bottomPanel = null;
		this.buttonPanel = null;
	}

	private class FamilyFileChooserUIAccessor implements FilePane.FileChooserUIAccessor
	{
		@Override
		public JFileChooser getFileChooser()
		{
			return FamilyFileChooserUI.this.getFileChooser();
		}

		@Override
		public BasicDirectoryModel getModel()
		{
			return FamilyFileChooserUI.this.getModel();
		}

		@Override
		public JPanel createList()
		{
			return FamilyFileChooserUI.this.createList(getFileChooser());
		}

		@Override
		public JPanel createDetailsView()
		{
			return FamilyFileChooserUI.this.createDetailsView(getFileChooser());
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public boolean isDirectorySelected()
		{
			return FamilyFileChooserUI.this.isDirectorySelected();
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public File getDirectory()
		{
			return FamilyFileChooserUI.this.getDirectory();
		}

		@Override
		public Action getChangeToParentDirectoryAction()
		{
			return FamilyFileChooserUI.this.getChangeToParentDirectoryAction();
		}

		@Override
		public Action getApproveSelectionAction()
		{
			return FamilyFileChooserUI.this.getApproveSelectionAction();
		}

		@Override
		public Action getNewFolderAction()
		{
			return FamilyFileChooserUI.this.getNewFolderAction();
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public MouseListener createDoubleClickListener(@SuppressWarnings("rawtypes") JList list)
		{
			return FamilyFileChooserUI.this.createDoubleClickListener(getFileChooser(), list);
		}

		@Override
		public ListSelectionListener createListSelectionListener()
		{
			return FamilyFileChooserUI.this.createListSelectionListener(getFileChooser());
		}
	}

	@SuppressWarnings({ "synthetic-access", "rawtypes", "serial", "unchecked" })
	@Override
	public void installComponents(JFileChooser fc)
	{
		FileSystemView fsv = fc.getFileSystemView();

		fc.setBorder(new EmptyBorder(12, 12, 11, 11));
		fc.setLayout(new BorderLayout(0, 11));

		this.filePane = new FilePane(new FamilyFileChooserUIAccessor());
		fc.addPropertyChangeListener(this.filePane);

		// ********************************* //
		// **** Construct the top panel **** //
		// ********************************* //

		// Directory manipulation buttons
		JPanel topPanel = new JPanel(new BorderLayout(11, 0));
		JPanel topButtonPanel = new JPanel();
		topButtonPanel.setLayout(new BoxLayout(topButtonPanel, BoxLayout.LINE_AXIS));
		topPanel.add(topButtonPanel, BorderLayout.AFTER_LINE_ENDS);

		// Add the top panel to the fileChooser
		fc.add(topPanel, BorderLayout.NORTH);

		// ComboBox Label
		this.lookInLabel = new JLabel(this.lookInLabelText);
		this.lookInLabel.setDisplayedMnemonic(this.lookInLabelMnemonic);
		topPanel.add(this.lookInLabel, BorderLayout.BEFORE_LINE_BEGINS);

		// CurrentDir ComboBox
		this.directoryComboBox = new JComboBox()
		{
			@Override
			public Dimension getPreferredSize()
			{
				Dimension d = super.getPreferredSize();
				// Must be small enough to not affect total width.
				d.width = 150;
				return d;
			}
		};
		this.directoryComboBox.putClientProperty(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY,
		        this.lookInLabelText);
		this.directoryComboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
		this.lookInLabel.setLabelFor(this.directoryComboBox);
		this.directoryComboBoxModel = createDirectoryComboBoxModel(fc);
		this.directoryComboBox.setModel(this.directoryComboBoxModel);
		this.directoryComboBox.addActionListener(this.directoryComboBoxAction);
		this.directoryComboBox.setRenderer(createDirectoryComboBoxRenderer(fc));
		this.directoryComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.directoryComboBox.setAlignmentY(Component.TOP_ALIGNMENT);
		this.directoryComboBox.setMaximumRowCount(8);

		topPanel.add(this.directoryComboBox, BorderLayout.CENTER);

		// Up Button
		JButton upFolderButton = new JButton(getChangeToParentDirectoryAction());
		upFolderButton.setText(null);
		upFolderButton.setIcon(this.upFolderIcon);
		upFolderButton.setToolTipText(this.upFolderToolTipText);
		upFolderButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, this.upFolderAccessibleName);
		upFolderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		upFolderButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		upFolderButton.setMargin(shrinkwrap);

		topButtonPanel.add(upFolderButton);
		topButtonPanel.add(Box.createRigidArea(hstrut5));

		// Home Button
		File homeDir = fsv.getHomeDirectory();
		String toolTipText = this.homeFolderToolTipText;
		if (fsv.isRoot(homeDir))
			toolTipText = getFileView(fc).getName(homeDir); // Probably "Desktop".

		JButton b = new JButton(this.homeFolderIcon);
		b.setToolTipText(toolTipText);
		b.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, this.homeFolderAccessibleName);
		b.setAlignmentX(Component.LEFT_ALIGNMENT);
		b.setAlignmentY(Component.CENTER_ALIGNMENT);
		b.setMargin(shrinkwrap);

		b.addActionListener(getGoHomeAction());
		topButtonPanel.add(b);
		topButtonPanel.add(Box.createRigidArea(hstrut5));

		// New Directory Button
		if (!UIManager.getBoolean("FileChooser.readOnly"))
		{
			b = new JButton(this.filePane.getNewFolderAction());
			b.setText(null);
			b.setIcon(this.newFolderIcon);
			b.setToolTipText(this.newFolderToolTipText);
			b.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, this.newFolderAccessibleName);
			b.setAlignmentX(Component.LEFT_ALIGNMENT);
			b.setAlignmentY(Component.CENTER_ALIGNMENT);
			b.setMargin(shrinkwrap);
		}
		topButtonPanel.add(b);
		topButtonPanel.add(Box.createRigidArea(hstrut5));

		// View button group
		ButtonGroup viewButtonGroup = new ButtonGroup();

		// List Button
		this.listViewButton = new JToggleButton(this.listViewIcon);
		this.listViewButton.setToolTipText(this.listViewButtonToolTipText);
		this.listViewButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
		        this.listViewButtonAccessibleName);
		this.listViewButton.setSelected(true);
		this.listViewButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.listViewButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.listViewButton.setMargin(shrinkwrap);
		this.listViewButton.addActionListener(this.filePane.getViewTypeAction(FilePane.VIEWTYPE_LIST));
		topButtonPanel.add(this.listViewButton);
		viewButtonGroup.add(this.listViewButton);

		// Details Button
		this.detailsViewButton = new JToggleButton(this.detailsViewIcon);
		this.detailsViewButton.setToolTipText(this.detailsViewButtonToolTipText);
		this.detailsViewButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
		        this.detailsViewButtonAccessibleName);
		this.detailsViewButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.detailsViewButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.detailsViewButton.setMargin(shrinkwrap);
		this.detailsViewButton.addActionListener(this.filePane.getViewTypeAction(FilePane.VIEWTYPE_DETAILS));
		topButtonPanel.add(this.detailsViewButton);
		viewButtonGroup.add(this.detailsViewButton);

		this.filePane.addPropertyChangeListener(new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent e)
			{
				if ("viewType".equals(e.getPropertyName()))
				{
					int viewType = FamilyFileChooserUI.this.filePane.getViewType();
					switch(viewType)
					{
						case FilePane.VIEWTYPE_LIST:
							FamilyFileChooserUI.this.listViewButton.setSelected(true);
							break;

						case FilePane.VIEWTYPE_DETAILS:
							FamilyFileChooserUI.this.detailsViewButton.setSelected(true);
							break;
					}
				}
			}
		});

		// ************************************** //
		// ******* Add the directory pane ******* //
		// ************************************** //
		fc.add(getAccessoryPanel(), BorderLayout.AFTER_LINE_ENDS);
		JComponent accessory = fc.getAccessory();
		if (accessory != null)
			getAccessoryPanel().add(accessory);
		this.filePane.setPreferredSize(LIST_PREF_SIZE);
		fc.add(this.filePane, BorderLayout.CENTER);

		// ********************************** //
		// **** Construct the bottom panel ** //
		// ********************************** //
		@SuppressWarnings("hiding")
		JPanel bottomPanel = getBottomPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		fc.add(bottomPanel, BorderLayout.SOUTH);

		// FileName label and textfield
		JPanel fileNamePanel = new JPanel();
		fileNamePanel.setLayout(new BoxLayout(fileNamePanel, BoxLayout.LINE_AXIS));
		bottomPanel.add(fileNamePanel);
		bottomPanel.add(Box.createRigidArea(vstrut5));

		this.fileNameLabel = new AlignedLabel();
		populateFileNameLabel();
		fileNamePanel.add(this.fileNameLabel);

		this.fileNameTextField = new JTextField(35)
		{
			@Override
			public Dimension getMaximumSize()
			{
				return new Dimension(Short.MAX_VALUE, super.getPreferredSize().height);
			}
		};
		fileNamePanel.add(this.fileNameTextField);
		this.fileNameLabel.setLabelFor(this.fileNameTextField);
		this.fileNameTextField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(@SuppressWarnings("unused") FocusEvent e)
			{
				if (!getFileChooser().isMultiSelectionEnabled())
					FamilyFileChooserUI.this.filePane.clearSelection();
			}
		});
		if (fc.isMultiSelectionEnabled())
			setFileName(fileNameString(fc.getSelectedFiles()));
		else
			setFileName(fileNameString(fc.getSelectedFile()));

		// Filetype label and combobox
		JPanel filesOfTypePanel = new JPanel();
		filesOfTypePanel.setLayout(new BoxLayout(filesOfTypePanel, BoxLayout.LINE_AXIS));
		bottomPanel.add(filesOfTypePanel);

		AlignedLabel filesOfTypeLabel = new AlignedLabel(this.filesOfTypeLabelText);
		filesOfTypeLabel.setDisplayedMnemonic(this.filesOfTypeLabelMnemonic);
		filesOfTypePanel.add(filesOfTypeLabel);

		this.filterComboBoxModel = createFilterComboBoxModel();
		fc.addPropertyChangeListener(this.filterComboBoxModel);
		this.filterComboBox = new JComboBox(this.filterComboBoxModel);
		this.filterComboBox.putClientProperty(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY, this.filesOfTypeLabelText);
		filesOfTypeLabel.setLabelFor(this.filterComboBox);
		this.filterComboBox.setRenderer(createFilterComboBoxRenderer());
		filesOfTypePanel.add(this.filterComboBox);

		// buttons
		getButtonPanel().setLayout(new ButtonAreaLayout());

		this.approveButton = new JButton(getApproveButtonText(fc));
		// Note: Family does not use mnemonics for approve and cancel
		this.approveButton.addActionListener(getApproveSelectionAction());
		this.approveButton.setToolTipText(getApproveButtonToolTipText(fc));
		getButtonPanel().add(this.approveButton);

		this.cancelButton = new JButton(this.cancelButtonText);
		this.cancelButton.setToolTipText(this.cancelButtonToolTipText);
		this.cancelButton.addActionListener(getCancelSelectionAction());
		getButtonPanel().add(this.cancelButton);

		if (fc.getControlButtonsAreShown())
			addControlButtons();

		groupLabels(new AlignedLabel[] { this.fileNameLabel, filesOfTypeLabel });
	}

	protected JPanel getButtonPanel()
	{
		if (this.buttonPanel == null)
			this.buttonPanel = new JPanel();
		return this.buttonPanel;
	}

	protected JPanel getBottomPanel()
	{
		if (this.bottomPanel == null)
			this.bottomPanel = new JPanel();
		return this.bottomPanel;
	}

	@Override
	protected void installStrings(JFileChooser fc)
	{
		super.installStrings(fc);

		Locale l = fc.getLocale();

		this.lookInLabelMnemonic = getMnemonic("FileChooser.lookInLabelMnemonic", l);
		this.lookInLabelText = UIManager.getString("FileChooser.lookInLabelText", l);
		this.saveInLabelText = UIManager.getString("FileChooser.saveInLabelText", l);

		this.fileNameLabelMnemonic = getMnemonic("FileChooser.fileNameLabelMnemonic", l);
		this.fileNameLabelText = UIManager.getString("FileChooser.fileNameLabelText", l);
		this.folderNameLabelMnemonic = getMnemonic("FileChooser.folderNameLabelMnemonic", l);
		this.folderNameLabelText = UIManager.getString("FileChooser.folderNameLabelText", l);

		this.filesOfTypeLabelMnemonic = getMnemonic("FileChooser.filesOfTypeLabelMnemonic", l);
		this.filesOfTypeLabelText = UIManager.getString("FileChooser.filesOfTypeLabelText", l);

		this.upFolderToolTipText = UIManager.getString("FileChooser.upFolderToolTipText", l);
		this.upFolderAccessibleName = UIManager.getString("FileChooser.upFolderAccessibleName", l);

		this.homeFolderToolTipText = UIManager.getString("FileChooser.homeFolderToolTipText", l);
		this.homeFolderAccessibleName = UIManager.getString("FileChooser.homeFolderAccessibleName", l);

		this.newFolderToolTipText = UIManager.getString("FileChooser.newFolderToolTipText", l);
		this.newFolderAccessibleName = UIManager.getString("FileChooser.newFolderAccessibleName", l);

		this.listViewButtonToolTipText = UIManager.getString("FileChooser.listViewButtonToolTipText", l);
		this.listViewButtonAccessibleName = UIManager.getString("FileChooser.listViewButtonAccessibleName", l);

		this.detailsViewButtonToolTipText = UIManager.getString("FileChooser.detailsViewButtonToolTipText", l);
		this.detailsViewButtonAccessibleName = UIManager.getString("FileChooser.detailsViewButtonAccessibleName", l);
	}

	private Integer getMnemonic(String key, Locale l)
	{
		return SwingUtilities2.getUIDefaultsInt(key, l);
	}

	@Override
	protected void installListeners(JFileChooser fc)
	{
		super.installListeners(fc);
		ActionMap actionMap = getActionMap();
		SwingUtilities.replaceUIActionMap(fc, actionMap);
	}

	protected ActionMap getActionMap()
	{
		return createActionMap();
	}

	protected ActionMap createActionMap()
	{
		ActionMap map = new ActionMapUIResource();
		FilePane.addActionsToMap(map, this.filePane.getActions());
		return map;
	}

	protected JPanel createList(@SuppressWarnings("unused") JFileChooser fc)
	{
		return this.filePane.createList();
	}

	protected JPanel createDetailsView(@SuppressWarnings("unused") JFileChooser fc)
	{
		return this.filePane.createDetailsView();
	}

	/**
	 * Creates a selection listener for the list of files and directories.
	 *
	 * @param fc
	 *            a <code>JFileChooser</code>
	 * @return a <code>ListSelectionListener</code>
	 */
	@Override
	public ListSelectionListener createListSelectionListener(JFileChooser fc)
	{
		return super.createListSelectionListener(fc);
	}

	// Obsolete class, not used in this version.
	protected class SingleClickListener extends MouseAdapter
	{
		public SingleClickListener(@SuppressWarnings({ "unused", "rawtypes" }) JList list)
		{}
	}

	// Obsolete class, not used in this version.
	@SuppressWarnings("serial")
	protected class FileRenderer extends DefaultListCellRenderer
	{}

	@Override
	public void uninstallUI(JComponent c)
	{
		// Remove listeners
		c.removePropertyChangeListener(this.filterComboBoxModel);
		c.removePropertyChangeListener(this.filePane);
		this.cancelButton.removeActionListener(getCancelSelectionAction());
		this.approveButton.removeActionListener(getApproveSelectionAction());
		this.fileNameTextField.removeActionListener(getApproveSelectionAction());

		if (this.filePane != null)
		{
			this.filePane.uninstallUI();
			this.filePane = null;
		}

		super.uninstallUI(c);
	}

	/**
	 * Returns the preferred size of the specified <code>JFileChooser</code>.
	 * The preferred size is at least as large, in both height and width, as the
	 * preferred size recommended by the file chooser's layout manager.
	 *
	 * @param c
	 *            a <code>JFileChooser</code>
	 * @return a <code>Dimension</code> specifying the preferred width and
	 *         height of the file chooser
	 */
	@Override
	public Dimension getPreferredSize(JComponent c)
	{
		int prefWidth = PREF_SIZE.width;
		Dimension d = c.getLayout().preferredLayoutSize(c);
		if (d != null)
			return new Dimension(d.width < prefWidth ? prefWidth : d.width, d.height < PREF_SIZE.height ? PREF_SIZE.height : d.height);
		return new Dimension(prefWidth, PREF_SIZE.height);
	}

	/**
	 * Returns the minimum size of the <code>JFileChooser</code>.
	 *
	 * @param c
	 *            a <code>JFileChooser</code>
	 * @return a <code>Dimension</code> specifying the minimum width and height
	 *         of the file chooser
	 */
	@Override
	public Dimension getMinimumSize(JComponent c)
	{
		return new Dimension(MIN_WIDTH, MIN_HEIGHT);
	}

	/**
	 * Returns the maximum size of the <code>JFileChooser</code>.
	 *
	 * @param c
	 *            a <code>JFileChooser</code>
	 * @return a <code>Dimension</code> specifying the maximum width and height
	 *         of the file chooser
	 */
	@Override
	public Dimension getMaximumSize(JComponent c)
	{
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	private String fileNameString(File file)
	{
		if (file == null) { return null; }
		JFileChooser fc = getFileChooser();
		if ((fc.isDirectorySelectionEnabled() && !fc.isFileSelectionEnabled())
		        || (fc.isDirectorySelectionEnabled() && fc.isFileSelectionEnabled() && fc.getFileSystemView().isFileSystemRoot(file)))
			return file.getPath();
		return file.getName();
	}

	private String fileNameString(File[] files)
	{
		StringBuffer buf = new StringBuffer();
		for (int i = 0; files != null && i < files.length; i++)
		{
			if (i > 0)
				buf.append(" ");
			if (files.length > 1)
				buf.append("\"");
			buf.append(fileNameString(files[i]));
			if (files.length > 1)
				buf.append("\"");
		}
		return buf.toString();
	}

	/* The following methods are used by the PropertyChange Listener */

	private void doSelectedFileChanged(PropertyChangeEvent e)
	{
		File f = (File) e.getNewValue();
		JFileChooser fc = getFileChooser();
		if (f != null && ((fc.isFileSelectionEnabled() && !f.isDirectory()) || (f.isDirectory() && fc.isDirectorySelectionEnabled())))
			setFileName(fileNameString(f));
	}

	private void doSelectedFilesChanged(PropertyChangeEvent e)
	{
		File[] files = (File[]) e.getNewValue();
		JFileChooser fc = getFileChooser();
		if (files != null && files.length > 0 && (files.length > 1 || fc.isDirectorySelectionEnabled() || !files[0].isDirectory()))
			setFileName(fileNameString(files));
	}

	@SuppressWarnings("synthetic-access")
	private void doDirectoryChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{
		JFileChooser fc = getFileChooser();
		FileSystemView fsv = fc.getFileSystemView();

		clearIconCache();
		File currentDirectory = fc.getCurrentDirectory();
		if (currentDirectory != null)
		{
			this.directoryComboBoxModel.addItem(currentDirectory);

			if (fc.isDirectorySelectionEnabled() && !fc.isFileSelectionEnabled())
				if (fsv.isFileSystem(currentDirectory))
					setFileName(currentDirectory.getPath());
				else
					setFileName(null);
		}
	}

	private void doFilterChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{
		clearIconCache();
	}

	@SuppressWarnings("unqualified-field-access")
	private void doFileSelectionModeChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{
		if (fileNameLabel != null)
			populateFileNameLabel();
		clearIconCache();

		JFileChooser fc = getFileChooser();
		File currentDirectory = fc.getCurrentDirectory();
		if (currentDirectory != null && fc.isDirectorySelectionEnabled() && !fc.isFileSelectionEnabled()
		        && fc.getFileSystemView().isFileSystem(currentDirectory))
			setFileName(currentDirectory.getPath());
		else
			setFileName(null);
	}

	private void doAccessoryChanged(PropertyChangeEvent e)
	{
		if (getAccessoryPanel() != null)
		{
			if (e.getOldValue() != null)
				getAccessoryPanel().remove((JComponent) e.getOldValue());
			JComponent accessory = (JComponent) e.getNewValue();
			if (accessory != null)
				getAccessoryPanel().add(accessory, BorderLayout.CENTER);
		}
	}

	private void doApproveButtonTextChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{
		JFileChooser chooser = getFileChooser();
		this.approveButton.setText(getApproveButtonText(chooser));
		this.approveButton.setToolTipText(getApproveButtonToolTipText(chooser));
	}

	private void doDialogTypeChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{
		JFileChooser chooser = getFileChooser();
		this.approveButton.setText(getApproveButtonText(chooser));
		this.approveButton.setToolTipText(getApproveButtonToolTipText(chooser));
		if (chooser.getDialogType() == JFileChooser.SAVE_DIALOG)
			this.lookInLabel.setText(this.saveInLabelText);
		else
			this.lookInLabel.setText(this.lookInLabelText);
	}

	private void doApproveButtonMnemonicChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{
		// Note: Family does not use mnemonics for approve and cancel
	}

	private void doControlButtonsChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{
		if (getFileChooser().getControlButtonsAreShown())
			addControlButtons();
		else
			removeControlButtons();
	}

	/*
	 * Listen for filechooser property changes, such as the selected file
	 * changing, or the level of the dialog changing.
	 */
	@Override
	public PropertyChangeListener createPropertyChangeListener(@SuppressWarnings("unused") JFileChooser fc)
	{
		return new PropertyChangeListener()
		{
			@SuppressWarnings("synthetic-access")
			@Override
			public void propertyChange(PropertyChangeEvent e)
			{
				String s = e.getPropertyName();
				if (s.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
					doSelectedFileChanged(e);
				else if (s.equals(JFileChooser.SELECTED_FILES_CHANGED_PROPERTY))
					doSelectedFilesChanged(e);
				else if (s.equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY))
					doDirectoryChanged(e);
				else if (s.equals(JFileChooser.FILE_FILTER_CHANGED_PROPERTY))
					doFilterChanged(e);
				else if (s.equals(JFileChooser.FILE_SELECTION_MODE_CHANGED_PROPERTY))
					doFileSelectionModeChanged(e);
				else if (s.equals(JFileChooser.ACCESSORY_CHANGED_PROPERTY))
					doAccessoryChanged(e);
				else if (s.equals(JFileChooser.APPROVE_BUTTON_TEXT_CHANGED_PROPERTY)
		                || s.equals(JFileChooser.APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY))
					doApproveButtonTextChanged(e);
				else if (s.equals(JFileChooser.DIALOG_TYPE_CHANGED_PROPERTY))
					doDialogTypeChanged(e);
				else if (s.equals(JFileChooser.APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY))
					doApproveButtonMnemonicChanged(e);
				else if (s.equals(JFileChooser.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY))
					doControlButtonsChanged(e);
				else if (s.equals("componentOrientation"))
				{
					ComponentOrientation o = (ComponentOrientation) e.getNewValue();
					JFileChooser cc = (JFileChooser) e.getSource();
					if (o != e.getOldValue())
						cc.applyComponentOrientation(o);
				}
				else if (s == "FileChooser.useShellFolder")
					doDirectoryChanged(e);
				else if (s.equals("ancestor"))
					if (e.getOldValue() == null && e.getNewValue() != null)
					{
						// Ancestor was added, set initial focus
						FamilyFileChooserUI.this.fileNameTextField.selectAll();
						FamilyFileChooserUI.this.fileNameTextField.requestFocus();
					}
			}
		};
	}

	protected void removeControlButtons()
	{
		getBottomPanel().remove(getButtonPanel());
	}

	protected void addControlButtons()
	{
		getBottomPanel().add(getButtonPanel());
	}

	@Override
	public void ensureFileIsVisible(JFileChooser fc, File f)
	{
		this.filePane.ensureFileIsVisible(fc, f);
	}

	@Override
	public void rescanCurrentDirectory(@SuppressWarnings("unused") JFileChooser fc)
	{
		this.filePane.rescanCurrentDirectory();
	}

	@Override
	public String getFileName()
	{
		if (this.fileNameTextField != null)
			return this.fileNameTextField.getText();
		return null;
	}

	@Override
	public void setFileName(String filename)
	{
		if (this.fileNameTextField != null)
			this.fileNameTextField.setText(filename);
	}

	/**
	 * Property to remember whether a directory is currently selected in the UI.
	 * This is normally called by the UI on a selection event.
	 *
	 * @param directorySelected
	 *            if a directory is currently selected.
	 * @since 1.4
	 */
	@Override
	protected void setDirectorySelected(boolean directorySelected)
	{
		super.setDirectorySelected(directorySelected);
		JFileChooser chooser = getFileChooser();
		if (directorySelected)
		{
			if (this.approveButton != null)
			{
				this.approveButton.setText(this.directoryOpenButtonText);
				this.approveButton.setToolTipText(this.directoryOpenButtonToolTipText);
			}
		}
		else
			if (this.approveButton != null)
			{
				this.approveButton.setText(getApproveButtonText(chooser));
				this.approveButton.setToolTipText(getApproveButtonToolTipText(chooser));
			}
	}

	@Override
	public String getDirectoryName()
	{
		// PENDING(jeff) - get the name from the directory combobox
		return null;
	}

	@Override
	public void setDirectoryName(@SuppressWarnings("unused") String dirname)
	{
		// PENDING(jeff) - set the name in the directory combobox
	}

	protected DirectoryComboBoxRenderer createDirectoryComboBoxRenderer(@SuppressWarnings("unused") JFileChooser fc)
	{
		return new DirectoryComboBoxRenderer();
	}

	//
	// Renderer for DirectoryComboBox
	//
	@SuppressWarnings("serial")
	class DirectoryComboBoxRenderer extends DefaultListCellRenderer
	{
		IndentIcon ii = new IndentIcon();

		@SuppressWarnings("synthetic-access")
		@Override
		public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{

			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value == null)
			{
				setText("");
				return this;
			}
			File directory = (File) value;
			setText(getFileChooser().getName(directory));
			Icon icon = getFileChooser().getIcon(directory);
			this.ii.icon = icon;
			this.ii.depth = FamilyFileChooserUI.this.directoryComboBoxModel.getDepth(index);
			setIcon(this.ii);

			return this;
		}
	}

	final static int space = 10;

	class IndentIcon implements Icon
	{

		Icon icon = null;
		int depth = 0;

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y)
		{
			if (c.getComponentOrientation().isLeftToRight())
				this.icon.paintIcon(c, g, x + this.depth * space, y);
			else
				this.icon.paintIcon(c, g, x, y);
		}

		@Override
		public int getIconWidth()
		{
			return this.icon.getIconWidth() + this.depth * space;
		}

		@Override
		public int getIconHeight()
		{
			return this.icon.getIconHeight();
		}

	}

	//
	// DataModel for DirectoryComboxbox
	//
	protected DirectoryComboBoxModel createDirectoryComboBoxModel(@SuppressWarnings("unused") JFileChooser fc)
	{
		return new DirectoryComboBoxModel();
	}

	/**
	 * Data model for a level-face selection combo-box.
	 */
	@SuppressWarnings("serial")
	protected class DirectoryComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object>
	{
		Vector<File> directories = new Vector<File>();
		int[] depths = null;
		File selectedDirectory = null;
		JFileChooser chooser = getFileChooser();
		FileSystemView fsv = this.chooser.getFileSystemView();

		public DirectoryComboBoxModel()
		{
			// Add the current directory to the model, and make it the
			// selectedDirectory
			File dir = getFileChooser().getCurrentDirectory();
			if (dir != null)
				addItem(dir);
		}

		/**
		 * Adds the directory to the model and sets it to be selected,
		 * additionally clears out the previous selected directory and the paths
		 * leading up to it, if any.
		 */
		private void addItem(File directory)
		{

			if (directory == null)
				return;

			boolean useShellFolder = FilePane.usesShellFolder(this.chooser);

			this.directories.clear();

			File[] baseFolders = (useShellFolder) ? (File[]) ShellFolder.get("fileChooserComboBoxFolders") : this.fsv.getRoots();
			this.directories.addAll(Arrays.asList(baseFolders));

			// Get the canonical (full) path. This has the side
			// benefit of removing extraneous chars from the path,
			// for example /foo/bar/ becomes /foo/bar
			File canonical;
			try {canonical = ShellFolder.getNormalizedFile(directory);}
			catch (IOException e)
			{
				// Maybe drive is not ready. Can't abort here.
				canonical = directory;
			}

			// create File instances of each directory leading up to the top
			try
			{
				File sf = useShellFolder ? ShellFolder.getShellFolder(canonical) : canonical;
				File f = sf;
				Vector<File> path = new Vector<File>(10);
				do
					path.addElement(f);
				while ((f = f.getParentFile()) != null);

				int pathCount = path.size();
				// Insert chain at appropriate place in vector
				for (int i = 0; i < pathCount; i++)
				{
					f = path.get(i);
					if (this.directories.contains(f))
					{
						int topIndex = this.directories.indexOf(f);
						for (int j = i - 1; j >= 0; j--)
							this.directories.insertElementAt(path.get(j), topIndex + i - j);
						break;
					}
				}
				calculateDepths();
				setSelectedItem(sf);
			}
			catch (FileNotFoundException ex) {calculateDepths();}
		}

		private void calculateDepths()
		{
			this.depths = new int[this.directories.size()];
			for (int i = 0; i < this.depths.length; i++)
			{
				File dir = this.directories.get(i);
				File parent = dir.getParentFile();
				this.depths[i] = 0;
				if (parent != null)
					for (int j = i - 1; j >= 0; j--)
						if (parent.equals(this.directories.get(j)))
						{
							this.depths[i] = this.depths[j] + 1;
							break;
						}
			}
		}

		public int getDepth(int i)
		{
			return (this.depths != null && i >= 0 && i < this.depths.length) ? this.depths[i] : 0;
		}

		@Override
		public void setSelectedItem(Object selectedDirectory)
		{
			this.selectedDirectory = (File) selectedDirectory;
			fireContentsChanged(this, -1, -1);
		}

		@Override
		public Object getSelectedItem()
		{
			return this.selectedDirectory;
		}

		@Override
		public int getSize()
		{
			return this.directories.size();
		}

		@Override
		public Object getElementAt(int index)
		{
			return this.directories.elementAt(index);
		}
	}

	//
	// Renderer for Types ComboBox
	//
	protected FilterComboBoxRenderer createFilterComboBoxRenderer()
	{
		return new FilterComboBoxRenderer();
	}

	/**
	 * Render different level sizes and styles.
	 */
	@SuppressWarnings("serial")
	public class FilterComboBoxRenderer extends DefaultListCellRenderer
	{
		@Override
		public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index,
		        boolean isSelected, boolean cellHasFocus)
		{

			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null && value instanceof FileFilter)
				setText(((FileFilter) value).getDescription());

			return this;
		}
	}

	//
	// DataModel for Types Comboxbox
	//
	protected FilterComboBoxModel createFilterComboBoxModel()
	{
		return new FilterComboBoxModel();
	}

	/**
	 * Data model for a level-face selection combo-box.
	 */
	@SuppressWarnings("serial")
	protected class FilterComboBoxModel extends AbstractListModel<Object>
	        implements ComboBoxModel<Object>, PropertyChangeListener
	{
		protected FileFilter[] filters;

		protected FilterComboBoxModel()
		{
			super();
			this.filters = getFileChooser().getChoosableFileFilters();
		}

		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			String prop = e.getPropertyName();
			if (prop == JFileChooser.CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY)
			{
				this.filters = (FileFilter[]) e.getNewValue();
				fireContentsChanged(this, -1, -1);
			}
			else if (prop == JFileChooser.FILE_FILTER_CHANGED_PROPERTY)
				fireContentsChanged(this, -1, -1);
		}

		@Override
		public void setSelectedItem(Object filter)
		{
			if (filter != null)
			{
				getFileChooser().setFileFilter((FileFilter) filter);
				fireContentsChanged(this, -1, -1);
			}
		}

		@Override
		public Object getSelectedItem()
		{
			// Ensure that the current filter is in the list.
			// NOTE: we shouldnt' have to do this, since JFileChooser adds
			// the filter to the choosable filters list when the filter
			// is set. Lets be paranoid just in case someone overrides
			// setFileFilter in JFileChooser.
			FileFilter currentFilter = getFileChooser().getFileFilter();
			boolean found = false;
			if (currentFilter != null)
			{
				for (FileFilter filter: this.filters)
					if (filter == currentFilter)
						found = true;
				if (found == false)
					getFileChooser().addChoosableFileFilter(currentFilter);
			}
			return getFileChooser().getFileFilter();
		}

		@Override
		public int getSize()
		{
			if (this.filters != null)
				return this.filters.length;
			return 0;
		}

		@Override
		public Object getElementAt(int index)
		{
			if (index > getSize() - 1)
				// This shouldn't happen. Try to recover gracefully.
				return getFileChooser().getFileFilter();
			if (this.filters != null)
				return this.filters[index];
			return null;
		}
	}

	public void valueChanged(ListSelectionEvent e)
	{
		JFileChooser fc = getFileChooser();
		File f = fc.getSelectedFile();
		if (!e.getValueIsAdjusting() && f != null && !getFileChooser().isTraversable(f))
			setFileName(fileNameString(f));
	}

	/**
	 * Acts when DirectoryComboBox has changed the selected item.
	 */
	@SuppressWarnings("serial")
	protected class DirectoryComboBoxAction extends AbstractAction
	{
		protected DirectoryComboBoxAction()
		{
			super("DirectoryComboBoxAction");
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(@SuppressWarnings("unused") ActionEvent e)
		{
			FamilyFileChooserUI.this.directoryComboBox.hidePopup();
			File f = (File) FamilyFileChooserUI.this.directoryComboBox.getSelectedItem();
			if (!getFileChooser().getCurrentDirectory().equals(f))
				getFileChooser().setCurrentDirectory(f);
		}
	}

	@Override
	protected JButton getApproveButton(@SuppressWarnings("unused") JFileChooser fc)
	{
		return this.approveButton;
	}

	/**
	 * <code>ButtonAreaLayout</code> behaves in a similar manner to
	 * <code>FlowLayout</code>. It lays out all components from left to right,
	 * flushed right. The widths of all components will be set to the largest
	 * preferred size width.
	 */
	private static class ButtonAreaLayout implements LayoutManager
	{
		private int hGap = 5;
		private int topMargin = 17;

		@Override
		public void addLayoutComponent(@SuppressWarnings("unused") String string,
		        @SuppressWarnings("unused") Component comp)
		{}

		@Override
		public void layoutContainer(Container container)
		{
			Component[] children = container.getComponents();

			if (children != null && children.length > 0)
			{
				int numChildren = children.length;
				Dimension[] sizes = new Dimension[numChildren];
				Insets insets = container.getInsets();
				int yLocation = insets.top + this.topMargin;
				int maxWidth = 0;

				for (int counter = 0; counter < numChildren; counter++)
				{
					sizes[counter] = children[counter].getPreferredSize();
					maxWidth = Math.max(maxWidth, sizes[counter].width);
				}
				int xLocation, xOffset;
				if (container.getComponentOrientation().isLeftToRight())
				{
					xLocation = container.getSize().width - insets.left - maxWidth;
					xOffset = this.hGap + maxWidth;
				}
				else
				{
					xLocation = insets.left;
					xOffset = -(this.hGap + maxWidth);
				}
				for (int counter = numChildren - 1; counter >= 0; counter--)
				{
					children[counter].setBounds(xLocation, yLocation, maxWidth, sizes[counter].height);
					xLocation -= xOffset;
				}
			}
		}

		@Override
		public Dimension minimumLayoutSize(Container c)
		{
			if (c != null)
			{
				Component[] children = c.getComponents();

				if (children != null && children.length > 0)
				{
					int numChildren = children.length;
					int height = 0;
					Insets cInsets = c.getInsets();
					int extraHeight = this.topMargin + cInsets.top + cInsets.bottom;
					int extraWidth = cInsets.left + cInsets.right;
					int maxWidth = 0;

					for (int counter = 0; counter < numChildren; counter++)
					{
						Dimension aSize = children[counter].getPreferredSize();
						height = Math.max(height, aSize.height);
						maxWidth = Math.max(maxWidth, aSize.width);
					}
					return new Dimension(extraWidth + numChildren * maxWidth + (numChildren - 1) * this.hGap, extraHeight + height);
				}
			}
			return new Dimension(0, 0);
		}

		@Override
		public Dimension preferredLayoutSize(Container c)
		{
			return minimumLayoutSize(c);
		}

		@Override
		public void removeLayoutComponent(@SuppressWarnings("unused") Component c)
		{}
	}

	@SuppressWarnings("synthetic-access")
	private static void groupLabels(AlignedLabel[] group)
	{
		for (int i = 0; i < group.length; i++)
			group[i].group = group;
	}

	@SuppressWarnings("serial")
	private class AlignedLabel extends JLabel
	{
		private AlignedLabel[] group;
		private int maxWidth = 0;

		AlignedLabel()
		{
			super();
			setAlignmentX(Component.LEFT_ALIGNMENT);
		}

		AlignedLabel(String text)
		{
			super(text);
			setAlignmentX(Component.LEFT_ALIGNMENT);
		}

		@Override
		public Dimension getPreferredSize()
		{
			Dimension d = super.getPreferredSize();
			// Align the width with all other labels in group.
			return new Dimension(getMaxWidth() + 11, d.height);
		}

		private int getMaxWidth()
		{
			if (this.maxWidth == 0 && this.group != null)
			{
				int max = 0;
				for (int i = 0; i < this.group.length; i++)
					max = Math.max(this.group[i].getSuperPreferredWidth(), max);
				for (int i = 0; i < this.group.length; i++)
					this.group[i].maxWidth = max;
			}
			return this.maxWidth;
		}

		private int getSuperPreferredWidth()
		{
			return super.getPreferredSize().width;
		}
	}
}
