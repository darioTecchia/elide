package elide.model.ticket;

import java.io.Serializable;

import org.joda.time.DateTime;

import elide.model.match.Match;
import elide.model.seat.Seat;
import elide.model.stadium.Stadium;

/**
 * The Class Ticket.
 */
@SuppressWarnings("serial")
public class Ticket implements Serializable, Cloneable {

	private Match match;
	private String teams;
	private DateTime date;
	private Stadium stadium;
	private Seat seat;

	/**
	 * Instantiates a new ticket.
	 *
	 * @param match the ticket's match
	 * @param seat the ticket's seat
	 */
	public Ticket(Match match, Seat seat) {
		this.match 		= match;
		this.teams 		= this.match.getTeam_1().getName() + " - " + this.match.getTeam_2().getName();
		this.date 		= this.match.getDate();
		this.stadium 	= this.match.getStadium();
		this.seat 		= seat;
	}

	/**
	 * Gets the match.
	 *
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 * Sets the match.
	 *
	 * @param match the new match
	 */
	public void setMatch(Match match) {
		this.match = match;
	}

	/**
	 * Gets the teams.
	 *
	 * @return the teams
	 */
	public String getTeams() {
		return teams;
	}

	/**
	 * Sets the teams.
	 *
	 * @param teams the new teams
	 */
	public void setTeams(String teams) {
		this.teams = teams;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}

	/**
	 * Gets the stadium.
	 *
	 * @return the stadium
	 */
	public Stadium getStadium() {
		return stadium;
	}

	/**
	 * Sets the stadium.
	 *
	 * @param stadium the new stadium
	 */
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	/**
	 * Gets the seat.
	 *
	 * @return the seat
	 */
	public Seat getSeat() {
		return seat;
	}

	/**
	 * Sets the seat.
	 *
	 * @param seat the new seat
	 */
	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	/**
	 * Checks if is expired.
	 *
	 * @return true, if is expired
	 */
	public boolean isExpired() {
		return DateTime.now().isAfter(this.date.getMillis() - 1000*60*60*12); 
	}
	
	/** 
	 * Return a string rapresentation of the ticket object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"date: " 		+ this.date.toString("EEEE dd MMMM yyyy HH:mm") + 
				"match: "		+ this.match +
				"seat: "		+ this.seat +
				"stadium: " + this.stadium +
				"teams: "		+ this.teams +
				"}";
	}
	
	/**
	 * Make a "deep" comparision between this object and another object.
	 * 
	 * @return true, if the comparated object have the same class and the same proprierties
	 */
	@Override
	public boolean equals(Object anotherObject) {
		if(anotherObject == null) return false;
		if(this.getClass() != anotherObject.getClass()) return false;
		Ticket other = (Ticket) anotherObject;
		return this.date.equals(other.date) &&
				this.match.equals(other.match) &&
				this.seat.equals(other.seat) &&
				this.stadium.equals(other.stadium) &&
				this.teams.equals(other.teams);
	}
	
	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			Ticket cloned = (Ticket) super.clone();
			
			cloned.date 		= this.date;
			cloned.match 		= (Match) this.match.clone();
			cloned.seat 		= (Seat) this.seat.clone();
			cloned.stadium 	= (Stadium) this.stadium.clone();
			cloned.teams		= this.teams;
			
			return cloned;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}

}
