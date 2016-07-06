package ws.daley.genealogy.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityTransaction;
import org.w3c.dom.Node;

import ws.daley.genealogy.db.people.FamilyRecord;
import ws.daley.genealogy.db.people.HeaderRecord;
import ws.daley.genealogy.db.people.IndividualRecord;
import ws.daley.genealogy.db.people.NoteRecord;
import ws.daley.genealogy.db.people.RepositoryRecord;
import ws.daley.genealogy.db.people.SourceRecord;
import ws.daley.genealogy.db.people.SubmitterRecord;
import ws.daley.genealogy.db.people.TrailerRecord;

@Entity
public class Gedcom55 extends GenealogyItem
{
	@Transient
    private static final long serialVersionUID = 1L;
	
	@Transient
	private OpenJPAEntityManager em;
	
	@Transient
	private OpenJPAEntityTransaction et;
	
	@Transient
	private static long idNo = -1;
	
	@Basic
	private long id = ++idNo;
	public long getId() {return this.id;}
	public void setId(long newId) {this.id = newId;}
	
    @OneToMany
    private ArrayList<FamilyRecord> families = null;
    public ArrayList<FamilyRecord> getFamilies() {return this.families;}
    public void setFamilies(ArrayList<FamilyRecord> newIndividuals) {this.families = newIndividuals;}
    public void addFamilies(FamilyRecord newIndividual)
    {
    	if (this.getFamilies() == null)
    		this.setFamilies(new ArrayList<FamilyRecord>());
    	this.getFamilies().add(newIndividual);
    }
	
    @OneToMany
    private ArrayList<HeaderRecord> headers = null;
    public ArrayList<HeaderRecord> getHeaders() {return this.headers;}
    public void setHeaders(ArrayList<HeaderRecord> newHeaders) {this.headers = newHeaders;}
    public void addHeaders(HeaderRecord newHeader)
    {
    	if (this.getHeaders() == null)
    		this.setHeaders(new ArrayList<HeaderRecord>());
    	this.getHeaders().add(newHeader);
    }
	
    @OneToMany
    private ArrayList<IndividualRecord> individuals = null;
    public ArrayList<IndividualRecord> getIndividuals() {return this.individuals;}
    public void setIndividuals(ArrayList<IndividualRecord> newIndividuals) {this.individuals = newIndividuals;}
    public void addIndividuals(IndividualRecord newIndividual)
    {
    	if (this.getIndividuals() == null)
    		this.setIndividuals(new ArrayList<IndividualRecord>());
    	this.getIndividuals().add(newIndividual);
    }
	
    @OneToMany
    private ArrayList<NoteRecord> notes = null;
    public ArrayList<NoteRecord> getNotes() {return this.notes;}
    public void setNotes(ArrayList<NoteRecord> newNotes) {this.notes = newNotes;}
    public void addNotes(NoteRecord newNote)
    {
    	if (this.getNotes() == null)
    		this.setNotes(new ArrayList<NoteRecord>());
    	this.getNotes().add(newNote);
    }
	
    @OneToMany
    private ArrayList<RepositoryRecord> repositories = null;
    public ArrayList<RepositoryRecord> getRepositories() {return this.repositories;}
    public void setRepositories(ArrayList<RepositoryRecord> newRepositoriess) {this.repositories = newRepositoriess;}
    public void addRepositories(RepositoryRecord newRepository)
    {
    	if (this.getRepositories() == null)
    		this.setRepositories(new ArrayList<RepositoryRecord>());
    	this.getRepositories().add(newRepository);
    }
	
    @OneToMany
    private ArrayList<SourceRecord> sources = null;
    public ArrayList<SourceRecord> getSources() {return this.sources;}
    public void setSources(ArrayList<SourceRecord> newSources) {this.sources = newSources;}
    public void addSources(SourceRecord newSource)
    {
    	if (this.getSources() == null)
    		this.setSources(new ArrayList<SourceRecord>());
    	this.getSources().add(newSource);
    }
	
    @OneToMany
    private ArrayList<SubmitterRecord> submitters = null;
    public ArrayList<SubmitterRecord> getSubmitters() {return this.submitters;}
    public void setSubmitters(ArrayList<SubmitterRecord> newSubmitters) {this.submitters = newSubmitters;}
    public void addSubmitters(SubmitterRecord newSubmitter)
    {
    	if (this.getSubmitters() == null)
    		this.setSubmitters(new ArrayList<SubmitterRecord>());
    	this.getSubmitters().add(newSubmitter);
    }
	
    @OneToMany
    private ArrayList<TrailerRecord> trailers = null;
    public ArrayList<TrailerRecord> getTrailers() {return this.trailers;}
    public void setTrailers(ArrayList<TrailerRecord> newTrailers) {this.trailers = newTrailers;}
    public void addTrailers(TrailerRecord newTrailer)
    {
    	if (this.getTrailers() == null)
    		this.setTrailers(new ArrayList<TrailerRecord>());
    	this.getTrailers().add(newTrailer);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("FAMRecord", "families"),
    	new Instruction("HEADRecord", "headers"),
    	new Instruction("INDIRecord", "individuals"),
    	new Instruction("NOTERecord", "notes"),
    	new Instruction("REPORecord", "repositories"),
    	new Instruction("SOURRecord", "sources"),
    	new Instruction("SUBMRecord", "submitters"),
    	new Instruction("TRLRRecord", "trailers")
    };

	public Gedcom55() {}
	
	public Gedcom55(Node node) {super(node);}
	
	public void processInstructions(OpenJPAEntityManager newEm)
	{
		Instruction.buildChildren(this, instructions, this.nodeChildren);
		this.em = newEm;
		this.et = this.em.getTransaction();
		
		begin();
		System.out.println("generating Header record");
		processRecords(this.headers);
		System.out.println("generating Trailer record");
		processRecords(this.trailers);
		System.out.println("generating Repository record");
		processRecords(this.repositories);
		System.out.println("generating Submitter record");
		processRecords(this.submitters);
		System.out.println("generating Family records");
		processRecords(this.families);
		System.out.println("generating Individual records");
		processRecords(this.individuals);
		System.out.println("generating Note records");
		processRecords(this.notes);
		System.out.println("generating Source records");
		processRecords(this.sources);
		System.out.println("committing Individual records");
		commit();
		begin();
		System.out.println("resolving ids");
		resolveIds();
		commit();
	}
	
	public void begin() {this.et.begin();}

	public void commit() {this.et.commit();}
	
	public void persist(Object o) {this.em.persist(o);}
	
	private void processRecords(ArrayList<? extends GenealogyItem> records)
	{
	    for(GenealogyItem record:records)
	    	persist(record);
	}
	
	private void resolveIds()
	{
		commit();
		for(FamilyRecord family:this.families)
		{
			begin();
			family.processInstructions();
			family.resolveIds(this.em);
			commit();
		}
		for(IndividualRecord individual:this.individuals)
		{
			begin();
			individual.resolveIds();
			commit();
		}
		for(NoteRecord note:this.notes)
		{
			begin();
			note.resolveIds();
			commit();
		}
		for(RepositoryRecord repository:this.repositories)
		{
			begin();
			repository.resolveIds();
			commit();
		}
		for(SourceRecord source:this.sources)
		{
			begin();
			source.resolveIds();
			commit();
		}
		for(SubmitterRecord submitter:this.submitters)
		{
			begin();
			submitter.resolveIds();
			commit();
		}
		begin();
	}
}