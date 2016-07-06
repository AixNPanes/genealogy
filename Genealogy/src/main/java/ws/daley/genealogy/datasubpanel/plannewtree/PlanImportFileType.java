package ws.daley.genealogy.datasubpanel.plannewtree;

import ws.daley.genealogy.gedcom.FTMInputStreamReader;
import ws.daley.genealogy.gedcom.GEDCOMInputStream;
import ws.daley.genealogy.gedcom.LegacyInputStreamReader;
import ws.daley.genealogy.gedcom.PAFInputStreamReader;

public enum PlanImportFileType
{
	FAMILY_TREE_MAKER_5("Family Tree Maker (5 or Newer)", new String[]{".ftm", ".ftw"}, "ftm", false, FTMInputStreamReader.class),
	FAMILY_TREE_MAKER_BACKUP("Family Tree Maker Backup", new String[]{".ftmb"}, "ftmb", false, FTMInputStreamReader.class),
	GEDCOM("GEDCOM", new String[]{".ged"}, "ged", true, GEDCOMInputStream.class),
	PAF("Personal Ancestral File (PAF)", new String[]{".paf"}, "paf", false, PAFInputStreamReader.class),
	LEGACY_FAMILY_TREE("Legacy Family Tree", new String[]{".fdb"}, "legacy", false, LegacyInputStreamReader.class);

	private String name;
	public String getName() {return this.name;}

	private String[] importFileType;
	public String[] getFileType() {return this.importFileType;}

	private String actionCommand;
	public String getActionCommand() {return this.actionCommand;}

	private boolean enabled;
	public boolean enabled() {return this.enabled;}

	private Class<?> filterClass;
	public Class<?> getFilterClass() {return this.filterClass;}

	public static PlanImportFileType getType(String command)
	{
		for(PlanImportFileType type:PlanImportFileType.values())
			if (type.getActionCommand().equals(command))
				return type;
		throw new RuntimeException("actionCommand '" + command + "' not found");
	}

	private PlanImportFileType(String name, String[] importFileType, String actionCommand, boolean enabled, Class<?> filterClass)
	{
		this.name = name;
		this.importFileType = importFileType;
		this.actionCommand = actionCommand;
		this.enabled = enabled;
		this.filterClass = filterClass;
	}
}
