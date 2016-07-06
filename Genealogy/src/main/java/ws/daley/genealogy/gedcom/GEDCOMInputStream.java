package ws.daley.genealogy.gedcom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class GEDCOMInputStream extends MyFamilyInputStream
{
	private InputStreamReader inputStreamReader;
	private BufferedReader reader;
	@SuppressWarnings("unused")
	private boolean eof = true;

	public GEDCOMInputStream(String fileName) throws FileNotFoundException
	{
		this(new File(fileName));
	}

	public GEDCOMInputStream(File file) throws FileNotFoundException
	{
		this(new FileInputStream(file));
	}

	public GEDCOMInputStream(InputStream in)
	{
		this(in, (String)null);
	}

	public GEDCOMInputStream(InputStream in, String charsetName)
	{
		this(in, charsetName==null?Charset.defaultCharset():Charset.forName(charsetName));
	}

	public GEDCOMInputStream(InputStream in, Charset cs)
	{
		this(in, cs.newDecoder());
	}

	public GEDCOMInputStream(InputStream in, CharsetDecoder dec)
	{
		super();
		ProcessBuilder processbuilder = new ProcessBuilder()
				.command("/bin/sh", "-c", "awk -f ged1212xml.awk");
		Process process;
		try {process = processbuilder.start();}
		catch (IOException e) {throw new RuntimeException(e);}
		this.inputStreamReader = new InputStreamReader(in, dec);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
		Thread thread = new Thread()
		{
			@SuppressWarnings("synthetic-access")
			@Override
			public void run()
			{
				try
				{
					int c;
					while((c = GEDCOMInputStream.this.inputStreamReader.read()) != -1)
						writer.write(c);
					GEDCOMInputStream.this.inputStreamReader.close();
					writer.flush();
					writer.close();
				}
				catch (IOException e) {throw new RuntimeException(e);}
			}
		};
		thread.start();
		this.reader =  new BufferedReader(new InputStreamReader(process.getInputStream()));
		this.eof = false;
	}

	@Override
	public int read() throws IOException
	{
		return this.reader.read();
	}

	@Override
	public void close() throws IOException
	{
		this.inputStreamReader.close();
		this.eof = true;
	}
}
