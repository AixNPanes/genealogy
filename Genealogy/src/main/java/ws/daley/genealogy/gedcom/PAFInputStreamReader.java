package ws.daley.genealogy.gedcom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class PAFInputStreamReader extends Reader
{
	private InputStreamReader inputStreamReader;
	private BufferedReader reader;
//	private CharsetDecoder charsetDecoder;
//	private String buffer;
	private boolean eof = true;

	public PAFInputStreamReader(InputStream in)
	{
		this(in, (String)null);
		throw new RuntimeException("PAFInputStreamReader not implemented");
	}

	public PAFInputStreamReader(InputStream in, String charsetName)
	{
		this(in, charsetName==null?Charset.defaultCharset():Charset.forName(charsetName));
		throw new RuntimeException("PAFInputStreamReader not implemented");
	}

	public PAFInputStreamReader(InputStream in, Charset cs)
	{
		this(in, cs.newDecoder());
		throw new RuntimeException("PAFInputStreamReader not implemented");
	}

	public PAFInputStreamReader(InputStream in, CharsetDecoder dec)
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
					while((c = PAFInputStreamReader.this.inputStreamReader.read()) != -1)
						writer.write(c);
					PAFInputStreamReader.this.inputStreamReader.close();
					writer.flush();
					writer.close();
				}
				catch (IOException e) {throw new RuntimeException(e);}
			}
		};
		thread.start();
		this.reader =  new BufferedReader(new InputStreamReader(process.getInputStream()));
		this.eof = false;
		throw new RuntimeException("PAFInputStreamReader not implemented");
	}

	@Override
	public int read() throws IOException
	{
		return this.reader.read();
	}

	public String readLine() throws IOException
	{
		StringBuffer stringBuffer = new StringBuffer();
		if (this.eof)
			return null;
		int c;
		while((c = read()) != -1 && c != '\n')
			stringBuffer.append((char)c);
		if (c == -1)
			this.eof = true;
		return stringBuffer.toString();
	}

	@SuppressWarnings("unused")
	@Override
	public int read(char cbuf[], int offset, int length) throws IOException
	{
//		return super.read(cbuf, offset, length);
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void close() throws IOException
	{
		this.inputStreamReader.close();
		this.eof = true;
	}
}
