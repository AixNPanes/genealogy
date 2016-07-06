package ws.daley.genealogy;

import ws.daley.genealogy.gedcom.MsgProvider;

public class Ged2XMLMsgProvider implements MsgProvider
{
	private static final String[] message = new String[]{
		"No error",
		"End of file reached (without TRLR)",
		"The line is ignored because of a user defined tag",
		"The tag is unknown (not standard)",
		"The header record is missing",
		"Unexpected tag",
		"The line does not conform to the Gedcom standard",
		"The Gedcom file could not be opened",
		"An XREF was expected on this line.",
		"An XREF is limited to 20 characters according to the GEDCOM standard.",
		"Erroneous date",
		"The conversion was aborted",
		"An unknown character set was detected, ANSEL is assumed",
		"Warning: BETWEEN detected in date, treated as BET",
		"Warning: CA, CA. or CIR detected in date, treated as ABT",
		"Warning: TIME detected after date, accepted but not standard GEDCOM",
		"A pointer was expected after the tag"
	};

	public String msgProvider(int TextID)
	{
		if (TextID == Ged2XML.ERROR_IGNORE)
			return "The line will be ignored.";
		return message[TextID];
	}
}
