package elide.model.match;

import java.io.Serializable;

import org.joda.time.DateTime;

import elide.model.stadium.Stadium;
import elide.model.team.Team;

/**
 * The Class Match.
 */
@SuppressWarnings("serial")
public class Match implements Serializable, Cloneable {

	private int ID;
	private DateTime date;
	private Team team_1;
	private Team team_2;
	private Stadium stadium;
	private int avaiableSeat;

	/**
	 * Instantiates a new match.
	 *
	 * @param date the date of the match
	 * @param team_1 the first team of the match
	 * @param team_2 the second team of the match
	 * @param stadium the stadium of the match
	 */
	public Match(DateTime date, Team team_1, Team team_2, Stadium stadium) {
		this.ID 					= this.hashCode();
		this.date 				= date;
		this.team_1 			= team_1;
		this.team_2 			= team_2;
		this.stadium 			= stadium;
		this.avaiableSeat = stadium.getCapiency();
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
	 * Gets the team_1.
	 *
	 * @return the team_1
	 */
	public Team getTeam_1() {
		return team_1;
	}

	/**
	 * Sets the team_1.
	 *
	 * @param team_1 the new team_1
	 */
	public void setTeam_1(Team team_1) {
		this.team_1 = team_1;
	}

	/**
	 * Gets the team_2.
	 *
	 * @return the team_2
	 */
	public Team getTeam_2() {
		return team_2;
	}

	/**
	 * Sets the team_2.
	 *
	 * @param team_2 the new team_2
	 */
	public void setTeam_2(Team team_2) {
		this.team_2 = team_2;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}


	/**
	 * Gets the avaiable seat.
	 *
	 * @return the avaiable seat
	 */
	public int getAvaiableSeat() {
		return avaiableSeat;
	}

	/**
	 * Sets the avaiable seat.
	 *
	 * @param avaiableSeat the new avaiable seat
	 */
	public void setAvaiableSeat(int avaiableSeat) {
		this.avaiableSeat = avaiableSeat;
	}

	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return DateTime.now().isAfter(this.date.getMillis() + 1000*60*90);
	}

	/**
	 * Checks if is started.
	 *
	 * @return true, if is started
	 */
	public boolean isStarted() {
		return DateTime.now().isAfter(this.date.getMillis()) && !isFinished();
	}

	/** 
	 * Return a string rapresentation of the match object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"ID: " 						+ this.ID +
				"avaiableSeat: " 	+ this.avaiableSeat + 
				"date: " 					+ this.date.toString("EEEE dd MMMM yyyy HH:mm") + 
				"stadium: " 			+ this.stadium + 
				"team_1: " 				+ this.team_1 + 
				"team_2: " 				+ this.team_2
				+ "}";
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
		Match other = (Match) anotherObject;
		return this.ID == other.ID &&
				this.avaiableSeat == other.avaiableSeat &&
				this.date.equals(other.date) &&
				this.stadium.equals(other.stadium) &&
				this.team_1.equals(other.team_1) &&
				this.team_2.equals(other.team_2);
	}

	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			Match cloned = (Match) super.clone();
			
			cloned.avaiableSeat = this.avaiableSeat;
			cloned.date					= this.date;
			cloned.ID						= this.ID;
			cloned.stadium			= (Stadium) this.stadium.clone();
			cloned.team_1				= (Team) this.team_1.clone();
			cloned.team_2				= (Team) this.team_2.clone();
			
			return cloned;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}

}
