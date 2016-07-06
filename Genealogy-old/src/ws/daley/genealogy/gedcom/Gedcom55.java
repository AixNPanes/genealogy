package ws.daley.genealogy.gedcom;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.xerces.dom.NodeImpl;

public class Gedcom55 extends GedDocument
{
	private GedHeaderRecord gedHeader = null;
	private GedSubmitterRecord gedSubmitter = null;
	private ArrayList<GedSourceRecord> gedSource = new ArrayList<GedSourceRecord>();
	private ArrayList<GedRepositoryRecord> gedRepository = new ArrayList<GedRepositoryRecord>();
	private ArrayList<GedPlaceRecord> gedPlace = new ArrayList<GedPlaceRecord>();
	private TreeMap<String, GedPlaceRecord> gedPlaceMap = new TreeMap<String, GedPlaceRecord>(); 
	private ArrayList<GedNoteRecord> gedNote = new ArrayList<GedNoteRecord>();
	private ArrayList<GedIndividualRecord> gedIndividual = new ArrayList<GedIndividualRecord>();
	private ArrayList<GedFamilyRecord> gedFamily = new ArrayList<GedFamilyRecord>();
	private int maxPlaceId = -1;
	
	public Gedcom55(NodeImpl node)
	{
		super(node);
	}
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedHeader = (GedHeaderRecord)getChildDocument("HeaderRecord");
//		this.gedSubmitter = (GedSubmitterRecord)getChildDocument("SubmitterRecord");
//		for(GedDocument sourceCitation: getChildRecords("SourceRecord"))
//			this.gedSource.add((GedSourceRecord)sourceCitation);
//		for(GedDocument repository: getChildRecords("RepositoryRecord"))
//			this.gedRepository.add((GedRepositoryRecord)repository);
//		for(GedDocument place: getChildRecords("PlaceRecord"))
//			this.gedPlace.add((GedPlaceRecord)place);
//		for(GedDocument note: getChildRecords("NoteRecord"))
//			this.gedNote.add((GedNoteRecord)note);
//		for(GedDocument individual: getChildRecords("IndividualRecord"))
//			this.gedIndividual.add((GedIndividualRecord)individual);
//		for(GedDocument family: getChildRecords("FamilyRecord"))
//			this.gedFamily.add((GedFamilyRecord)family);
//	}

    public GedHeaderRecord getGedHeader() {return this.gedHeader;}
    public GedSubmitterRecord getGedSubmitter() {return this.gedSubmitter;}
    public ArrayList<GedSourceRecord> getGedSource() {return this.gedSource;}
    public ArrayList<GedRepositoryRecord> getGedRepository() {return this.gedRepository;}
    public ArrayList<GedPlaceRecord> getGedPlace() {return this.gedPlace;}
    public ArrayList<GedNoteRecord> getGedNote() {return this.gedNote;}
    public ArrayList<GedIndividualRecord> getGedIndividual() {return this.gedIndividual;}
    public ArrayList<GedFamilyRecord> getGedFamily() {return this.gedFamily;}
}
