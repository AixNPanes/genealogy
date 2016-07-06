package ws.daley.genealogy.datasubpanel.plancurrenttree.tasks;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class TasksTablePane extends JTable
{
	private JTable table;
	private DefaultTableModel tableModel;
	@SuppressWarnings("hiding")
	private DefaultTableColumnModel columnModel;
	private TableColumn selectColumn = new TableColumn(1);
	private TableColumn completeColumn = new TableColumn(2);
	private TableColumn taskDescriptionColumn = new TableColumn(3);
	private TableColumn taskForColumn = new TableColumn(4);
	private TableColumn categoryLocationColumn = new TableColumn(5);
	private TableColumn dueColumn = new TableColumn(6);
	private TableColumn createdColumn = new TableColumn(7);
	@SuppressWarnings({ "hiding", "unused" })
	private JTableHeader tableHeader;
	private JScrollPane scrollPane;
	private String[] columnNames = new String[]{"Select", "Complete", "Task Description" ,"Task For", "Category/Location", "Due", "Created"};
	private Object[][] data = new Object[][]{
			{Boolean.FALSE, Boolean.FALSE, "$$research Leroy's burial", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - A California Middle Border", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Bakersfield City Directory 1934", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Bakersfield City Directory 1939", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Butts, Alice May - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Butts, Ralph H. and Mildred L. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Butts, Samuel Perry - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Campbell, Heath E. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Chronister, Ida Ann - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Dailey, Daniel P. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Dailey, George W. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Desert Fever", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, Arron A. and Edna M. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, Berniece E. G. R. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, Clara E. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, Cliffor O. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, Frank Mose - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, M. G. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, Pauline May - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Hart, Thomas M. - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - High Country Communities", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Historic Kern County", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - History of Kern County Ca", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Land Of Havilay 1854 to 1874", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Long Road To Tehachapi", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Lower Kern River Country 1850 - 1950 Wilderness To Empire", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Polks Taft City Directory 1926", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Porterville Cemetery District - Home of Peace", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Porterville Cemetery District - St. Annes", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Porterville Evening Recorder Obit Index", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Raynor, Angelina Estella - headstone", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "$Transcribe Source - Tehachapi Pass", "", "", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "Research 1910 United States Federal Census, find census images on genealogy.com", "", "Census", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "Research 1940 U.S. Census, Calfornia, Fresno, Fresno, 10-36, page 24 of 40, find census images on genealogy.com", "", "Census", "", "2015-08-15"},
			{Boolean.FALSE, Boolean.FALSE, "Research 1910 United States Federal Census, AR, Pope, Liverty Twp, Series T624, Roll 61, Part 3, Page 199A, find census images on genealogy.com", "", "Census", "", "2015-08-15"},
	};

	public TasksTablePane()
	{
		this.columnModel = new DefaultTableColumnModel();
		this.columnModel.addColumn(this.selectColumn);
		this.columnModel.addColumn(this.completeColumn);
		this.columnModel.addColumn(this.taskDescriptionColumn);
		this.columnModel.addColumn(this.taskForColumn);
		this.columnModel.addColumn(this.categoryLocationColumn);
		this.columnModel.addColumn(this.dueColumn);
		this.columnModel.addColumn(this.createdColumn);
		this.tableHeader = new JTableHeader(this.columnModel);
		this.tableModel = new DefaultTableModel(this.data, this.columnNames);
		this.table = new JTable();
		this.table.setModel(this.tableModel);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollPane = new JScrollPane(this.table);
		this.setLayout(new BorderLayout());
		this.add(this.scrollPane, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(500, 200);
	}
}
