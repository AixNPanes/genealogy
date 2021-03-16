package ws.daley.genealogy.state;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyFamilyFile
{
	private ArrayList<Individual> individuals = new ArrayList<Individual>();
	@SuppressWarnings("unused")
	private ArrayList<Individual> getIndividuals() {return this.individuals;}

	private String homeIndividual = "";
	public String getHomeIndividual() {return this.homeIndividual;}

	private String currentIndividual = "";
	public String getCurrentIndividual() {return this.currentIndividual;}

	public static void putJson(MyFamilyFile variables, OutputStream outputStream)
	{
		try
		{
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, MyFamilyState.UTF8);
			Gson gson = new Gson();
			outputStreamWriter.write(gson.toJson(variables));
			outputStreamWriter.flush();
			outputStreamWriter.close();
		}
		catch (IOException e) {throw new RuntimeException(e);}
	}

	public static void putJson(MyFamilyFile variables, File file)
	{
			try {putJson(variables, new FileOutputStream(file));}
			catch (FileNotFoundException e) {throw new RuntimeException(e);}
	}

	public static void putJson(MyFamilyFile variables, String fileName) {putJson(variables, new File(fileName));}

	public static MyFamilyFile getJson(InputStream inputStream)
	{
		InputStreamReader inputStreamReader;
		try {inputStreamReader = new InputStreamReader(inputStream, MyFamilyState.UTF8);}
		catch(UnsupportedEncodingException e) {throw new RuntimeException(e);}
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(inputStreamReader, MyFamilyFile.class);
	}

	public static MyFamilyFile getJson(File file)
	{
		InputStream inputStream;
		try{inputStream = new FileInputStream(file);}
		catch(FileNotFoundException e) {throw new RuntimeException(e);}
		return getJson(inputStream);
	}

	public static MyFamilyFile getJson(String fileName) {return MyFamilyFile.getJson(new File(fileName));}
	
	public static MyFamilyFile getGEDCOM(InputStream inputStream)
	{
		InputStreamReader inputStreamReader;
		try {inputStreamReader = new InputStreamReader(inputStream, MyFamilyState.UTF8);}
		catch(UnsupportedEncodingException e) {throw new RuntimeException(e);}
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(inputStreamReader, MyFamilyFile.class);
	}

	public static MyFamilyFile getGEDCOM(File file)
	{
		InputStream inputStream;
		try{inputStream = new FileInputStream(file);}
		catch(FileNotFoundException e) {throw new RuntimeException(e);}
		return getGEDCOM(inputStream);
	}

	public static MyFamilyFile getGEDCOM(String fileName) {return MyFamilyFile.getGEDCOM(new File(fileName));}	
}
