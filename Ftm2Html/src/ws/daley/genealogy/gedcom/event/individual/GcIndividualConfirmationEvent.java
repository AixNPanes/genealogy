package ws.daley.genealogy.gedcom.event.individual;

import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/**
 * used in INDIVIDUAL_RECORD:=
 * 
 * 
 * INDIVIDUAL_EVENT_STRUCTURE:=
 * [ 
 * n [ BIRT | CHR ] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 	+1 FAMC @<XREF:FAM>@						{0:1} 
 * | 
 * n [ DEAT | BURI | CREM ] [Y|<NULL>] 			{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n ADOP [Y|<NULL>]							{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 	+1 FAMC @<XREF:FAM>@						{0:1} 
 * 		+2 ADOP <ADOPTED_BY_WHICH_PARENT>		{0:1} 
 * | 
 * n [ BAPM | BARM | BASM | BLES ] [Y|<NULL>]	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ CHRA | CONF | FCOM | ORDN ] [Y|<NULL>]	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ NATU | EMIG | IMMI ] [Y|<NULL>]			{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ CENS | PROB | WILL] [Y|<NULL>]			{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ GRAD | RETI ] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n EVEN										{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1}
 * ]
 * 
 * EVENT_DETAIL:= 
 * n TYPE <EVENT_DESCRIPTOR>	{0:1}	p.46 
 * n DATE <DATE_VALUE>	{0:1}	p.45/44 
 * n <<PLACE_STRUCTURE>>	{0:1}	p.37 
 * n <<ADDRESS_STRUCTURE>>	{0:1}	p.33 
 * n AGE <AGE_AT_EVENT>	{0:1}	p.41 
 * n AGNC <RESPONSIBLE_AGENCY>	{0:1}	p.54 
 * n CAUS <CAUSE_OF_EVENT>	{0:1}	p.42 
 * n <<SOURCE_CITATION>>	{0:M}	p.37 
 * n <<MULTIMEDIA_LINK>>	{0:M}	p.36,29 
 * n <<NOTE_STRUCTURE>>	{0:M}	p.37
 */

public class GcIndividualConfirmationEvent extends Gc_IndividualEventStructureEvent
{
	public GcIndividualConfirmationEvent(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "CONF", null, null, _vector);
	}
}
