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

public class MyFamilyState
{
	public static final String UTF8 = "UTF-8";

	private String fileName = "";
	public String getFileName() {return this.fileName;}

	private ArrayList<String> fileNameList = new ArrayList<String>();
	public ArrayList<String> getFileNameList() {return this.fileNameList;}

	public static void putState(MyFamilyState variables, OutputStream outputStream)
	{
		try
		{
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, UTF8);
			Gson gson = new Gson();
			outputStreamWriter.write(gson.toJson(variables));
			outputStreamWriter.flush();
			outputStreamWriter.close();
		}
		catch (IOException e) {throw new RuntimeException(e);}
	}

	public static void putState(MyFamilyState variables, String fileName)
	{
		putState(variables, new File(fileName));
	}

	public static void putState(MyFamilyState variables, File file)
	{
			try {putState(variables, new FileOutputStream(file));}
			catch (FileNotFoundException e) {throw new RuntimeException(e);}
	}

	public static MyFamilyState getState(InputStream inputStream)
	{
		InputStreamReader inputStreamReader;
		try {inputStreamReader = new InputStreamReader(inputStream, UTF8);}
		catch(UnsupportedEncodingException e) {throw new RuntimeException(e);}
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(inputStreamReader, MyFamilyState.class);
	}

	public static MyFamilyState getState(String fileName)
	{
		return MyFamilyState.getState(new File(fileName));
	}

	public static MyFamilyState getState(File file)
	{
		InputStream inputStream;
		try{inputStream = new FileInputStream(file);}
		catch(FileNotFoundException e) {throw new RuntimeException(e);}
		return getState(inputStream);
	}
}