package ws.daley.genealogy.util;

import java.util.ArrayList;


public class MultipleReplacement extends ArrayList<Replacement>
{
    private static final long serialVersionUID = 1L;

	public MultipleReplacement(ArrayList<Replacement> replacement)
	{
		super(replacement.size());
		this.addAll(replacement);
	}

	public MultipleReplacement(Replacement[] replacement)
	{
		super(replacement.length);
		for(Replacement repl:replacement)
			this.add(repl);
	}

	public MultipleReplacement(Replacement replacement)
	{
		this(new Replacement[] {replacement});
	}
	
	public String translatePatterns(String str)
	{
		String s = str;
		for(Replacement repl: this)
			s = repl.translatePattern(s);
		return s;
	}
}
