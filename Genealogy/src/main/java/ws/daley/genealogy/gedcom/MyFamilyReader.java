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

public abstract class MyFamilyReader extends Reader
{
	private InputStreamReader in = null;
    private char cb[];
    private int nChars;
    private int nextChar;
    private static final int INVALIDATED = -2;
    private static final int UNMARKED = -1;
    private int markedChar = UNMARKED;
    private int readAheadLimit = 0; /* Valid only when markedChar > 0 */
    private boolean skipLF = false;

    private BufferedReader reader = null;
//	private CharsetDecoder charsetDecoder = null;
//	private String buffer = null;
	private boolean eof = true;

	MyFamilyReader()
	{
		super();
	}

	public MyFamilyReader(InputStream in)
	{
		this(in, (String)null);
	}

	public MyFamilyReader(InputStream in, String charsetName)
	{
		this(in, charsetName==null?Charset.defaultCharset():Charset.forName(charsetName));
	}

	public MyFamilyReader(InputStream in, Charset cs)
	{
		this(in, cs.newDecoder());
	}

	public MyFamilyReader(InputStream in, CharsetDecoder dec)
	{
		super();
		ProcessBuilder processbuilder = new ProcessBuilder()
				.command("/bin/sh", "-c", "awk -f ged1212xml.awk");
		Process process;
		try {process = processbuilder.start();}
		catch (IOException e) {throw new RuntimeException(e);}
		this.in = new InputStreamReader(in, dec);
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
					while((c = MyFamilyReader.this.in.read()) != -1)
						writer.write(c);
					MyFamilyReader.this.in.close();
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

	private void ensureOpen() throws IOException
	{
		if (this.in == null)
			throw new IOException("Stream closed");
    }

	@Override
	public int read(char cbuf[], int off, int len) throws IOException
	{
        synchronized (this.lock) {
            ensureOpen();
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }

            int n = read1(cbuf, off, len);
            if (n <= 0) return n;
            while ((n < len) && this.in.ready()) {
                int n1 = read1(cbuf, off + n, len - n);
                if (n1 <= 0) break;
                n += n1;
            }
            return n;
        }
	}
    private int read1(char[] cbuf, int off, int len) throws IOException {
        if (this.nextChar >= this.nChars) {
            /* If the requested length is at least as large as the buffer, and
               if there is no mark/reset activity, and if line feeds are not
               being skipped, do not bother to copy the characters into the
               local buffer.  In this way buffered streams will cascade
               harmlessly. */
            if (len >= this.cb.length && this.markedChar <= UNMARKED && !this.skipLF) {
                return this.in.read(cbuf, off, len);
            }
            fill();
        }
        if (this.nextChar >= this.nChars) return -1;
        if (this.skipLF) {
        	this.skipLF = false;
            if (this.cb[this.nextChar] == '\n') {
            	this.nextChar++;
                if (this.nextChar >= this.nChars)
                    fill();
                if (this.nextChar >= this.nChars)
                    return -1;
            }
        }
        int n = Math.min(len, this.nChars - this.nextChar);
        System.arraycopy(this.cb, this.nextChar, cbuf, off, n);
        this.nextChar += n;
        return n;
    }
    private void fill() throws IOException {
        int dst;
        if (this.markedChar <= UNMARKED) {
            /* No mark */
            dst = 0;
        } else {
            /* Marked */
            int delta = this.nextChar - this.markedChar;
            if (delta >= this.readAheadLimit) {
                /* Gone past read-ahead limit: Invalidate mark */
            	this.markedChar = INVALIDATED;
            	this. readAheadLimit = 0;
                dst = 0;
            } else {
                if (this.readAheadLimit <= this.cb.length) {
                    /* Shuffle in the current buffer */
                    System.arraycopy(this.cb, this.markedChar, this.cb, 0, delta);
                    this.markedChar = 0;
                    dst = delta;
                } else {
                    /* Reallocate buffer to accommodate read-ahead limit */
                    char ncb[] = new char[this.readAheadLimit];
                    System.arraycopy(this.cb, this.markedChar, ncb, 0, delta);
                    this.cb = ncb;
                    this.markedChar = 0;
                    dst = delta;
                }
                this.nextChar = this.nChars = delta;
            }
        }

        int n;
        do {
            n = this.in.read(this.cb, dst, this.cb.length - dst);
        } while (n == 0);
        if (n > 0) {
        	this.nChars = dst + n;
        	this.nextChar = dst;
        }
    }

	@Override
	public void close() throws IOException
	{
		this.in.close();
		this.eof = true;
	}
}
