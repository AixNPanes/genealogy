package jaxb.gedcom;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jaxb.gedcom.NoteElement;

class NoteHelper {
	private static final boolean splitLines = false; // XML has no length
														// limit, and there is
														// no Continue tag, so
														// it makes no sense to
														// split lines here

	static void addNote(List<NoteElement> notes, String value) {
		if (value != null) {
			if (splitLines) {
				NoteElement note = new NoteElement();
				List<String> lines = wordWrap(value);
				note.setValue(escape(lines.get(0)));
				notes.add(note);
				lines.remove(0);

				for (String line : lines) {
					note = new NoteElement();
					note.setValue(escape(line));
					notes.add(note);
				}
			} else {
				NoteElement note = new NoteElement();
				note.setValue(escape(value));
				notes.add(note);
			}
		}
	}

	// assumes UTF-8 or UTF-16 as encoding,
	private static String escape(String content) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (c == '<')
				buffer.append("&lt;");
			else if (c == '>')
				buffer.append("&gt;");
			else if (c == '&')
				buffer.append("&amp;");
			else if (c == '"')
				buffer.append("&quot");
			else if (c == '\'')
				buffer.append("&apos;");
			else
				buffer.append(c);
		}
		return buffer.toString();
	}

	private static final int WORD_WRAP = 128; // Max allowed according to
												// GEDCOM 5.5 standard is 248

	private static final Pattern wrapRE = Pattern.compile("(\\S\\S{"
			+ WORD_WRAP + ",}|.{1," + WORD_WRAP + "})(\\s+|$)");

	private static List<String> wordWrap(String str) {
		List<String> list = new LinkedList<String>();
		Matcher m = wrapRE.matcher(str);
		while (m.find())
			list.add(m.group());
		return list;
	}
}
