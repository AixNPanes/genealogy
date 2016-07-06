package ws.daley.genealogy.db;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.FamilyRecord;
import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.db.record.HeaderRecord;
import ws.daley.genealogy.db.record.IndividualRecord;
import ws.daley.genealogy.db.record.NoteRecord;
import ws.daley.genealogy.db.record.RepositoryRecord;
import ws.daley.genealogy.db.record.SourceRecord;
import ws.daley.genealogy.db.record.SubmitterRecord;
import ws.daley.genealogy.db.record.TrailerRecord;

/**
 * 
 * @author Tim.Daley
 *	0 <<HEADER>>															{1:1}
 *	0 <<SUBMISSION_RECORD>>													{0:1}
 *	0 <<RECORD>>															{1:M}
 *	0 TRLR																	{1:1}
 */

public class Gedcom55 extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private HeaderRecord headerRecord = null;
    public HeaderRecord getHeaderRecord() {return this.headerRecord;}
    public void setHeaderRecord(HeaderRecord headerRecord) {this.headerRecord = headerRecord;}
    
    private List<IndividualRecord> individualRecords = new ArrayList<IndividualRecord>();
    public List<IndividualRecord> getIndividuals() {return this.individualRecords;}
    public void setIndividuals(List<IndividualRecord> individuals) {this.individualRecords = individuals;}
    public void addIndividualRecord(IndividualRecord individual) {this.individualRecords.add(individual);}
    
    private List<FamilyRecord> familyRecords = new ArrayList<FamilyRecord>();
    public List<FamilyRecord> getFamilies() {return this.familyRecords;}
    public void setFamilies(List<FamilyRecord> families) {this.familyRecords = families;}
    public void addFamilyRecord(FamilyRecord family) {this.familyRecords.add(family);}
    
    private List<SubmitterRecord> submitterRecords = new ArrayList<SubmitterRecord>();
    public List<SubmitterRecord> getSubmitters() {return this.submitterRecords;}
    public void setSubmitters(List<SubmitterRecord> submitters) {this.submitterRecords = submitters;}
    public void addSubmitterRecord(SubmitterRecord submitter) {this.submitterRecords.add(submitter);}
    
    private List<NoteRecord> noteRecords = new ArrayList<NoteRecord>();
    public List<NoteRecord> getNotes() {return this.noteRecords;}
    public void setNotes(List<NoteRecord> notes) {this.noteRecords = notes;}
    public void addNoteRecord(NoteRecord note) {this.noteRecords.add(note);}
    
    private List<SourceRecord> sourceRecords = new ArrayList<SourceRecord>();
    public List<SourceRecord> getSources() {return this.sourceRecords;}
    public void setSources(List<SourceRecord> sources) {this.sourceRecords = sources;}
    public void addSourceRecord(SourceRecord source) {this.sourceRecords.add(source);}
    
    private List<RepositoryRecord> repositoryRecords = new ArrayList<RepositoryRecord>();
    public List<RepositoryRecord> getRepositories() {return this.repositoryRecords;}
    public void setRepositories(List<RepositoryRecord> repositories) {this.repositoryRecords = repositories;}
    public void addRepositoryRecord(RepositoryRecord repositorie) {this.repositoryRecords.add(repositorie);}
    
    private TrailerRecord trailerRecord = null;
    public TrailerRecord getTrailerRecord() {return this.trailerRecord;}
    public void setTrailerRecord(TrailerRecord trailerRecord) {this.trailerRecord = trailerRecord;}
    
    public Gedcom55() {}
    
    public Gedcom55(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("HEAD".equals(childNode.getNodeName()))
			{
				if (getHeaderRecord() != null)
					throw new RuntimeException(childNode.getNodeName()+" duplicated in "+this.getClass().getSimpleName());
				this.setHeaderRecord(new HeaderRecord(this, childNode));
			}
			else if ("INDI".equals(childNode.getNodeName()))
			{
				this.addIndividualRecord(new IndividualRecord(this, childNode));
			}
			else if ("FAM".equals(childNode.getNodeName()))
			{
				this.addFamilyRecord(new FamilyRecord(this, childNode));
			}
			else if ("SUBM".equals(childNode.getNodeName()))
			{
				this.addSubmitterRecord(new SubmitterRecord(this, childNode));
			}
			else if ("NOTE".equals(childNode.getNodeName()))
			{
				this.addNoteRecord(new NoteRecord(this, childNode));
			}
			else if ("SOUR".equals(childNode.getNodeName()))
			{
				this.addSourceRecord(new SourceRecord(this, childNode));
			}
			else if ("REPO".equals(childNode.getNodeName()))
			{
				this.addRepositoryRecord(new RepositoryRecord(this, childNode));
			}
			else if ("TRLR".equals(childNode.getNodeName()))
			{
				if (getTrailerRecord() != null)
					throw new RuntimeException(childNode.getNodeName()+" duplicated in "+this.getClass().getSimpleName());
				this.setTrailerRecord(new TrailerRecord(this, childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
