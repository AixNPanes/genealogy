package jaxb.gedcom;

import java.text.SimpleDateFormat;
import java.util.Date;

import jaxb.gedcom.DateElement;
import jaxb.gedcom.EventRecElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.ParticipantElement;

/**
 * A GEDCOM Event record.
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class EventRec extends EventRecElement {
	
	/**
	 * Vital types for the Event Rec
	 */
	public enum VitalType {
		/**
		 * a marriage event
		 */
		marriage,
		/**
		 * a birth event
		 */
		birth,
		/**
		 * a death event
		 */
		death
	};
	/**
	 * Role of an individual in an event
	 */	
	public enum Role {
		/**
		 * this individual is the husband in a marriage event
		 */
		husband,
		/**
		 * this individual is the wife in a marriage event
		 */
		wife,
		/**
		 * this individual is the person who was born or who died for this event
		 */
		principle
	};

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd MMM yyyy");

	/**
	 * Create a new Event.
	 * @param key event id
	 * @param vitalType the type of event
	 */
	public EventRec(String key, VitalType vitalType) {
		super();
		super.setId(key);
		super.setVitalType(vitalType.toString());
	}

	/**
	 *
	 * @param date the date of the event
	 */
	public void setDate(Date date) {
		if (date != null) {
			DateElement d = new DateElement();
			d.setValue(dateFormat.format(date));
			super.setDate(d);
		}
	}

	/**
	 * Add a participant to an event
	 * @param indi the participant
	 * @param role the role of the participant
	 */
	public void addParticipant(IndividualRec indi, Role role) {
		LinkElement link = new LinkElement();
		link.setTarget("IndividualRec");
		link.setRef(indi);
		ParticipantElement participant = new ParticipantElement();
		participant.setLink(link);
		participant.setRole(role.toString());
		super.getParticipant().add(participant);
	}

	/**
	 * 
	 * @param place the place where the event took place
	 */
	public void setPlace(Place place) {
		super.setPlace(place);
	}

	/**
	 * @param value notes about the event
	 */
	public void addNote(String value) {
		NoteHelper.addNote(super.getNote(), value);
	}
}
