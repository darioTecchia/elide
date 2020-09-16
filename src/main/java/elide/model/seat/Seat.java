package elide.model.seat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import elide.model.user.User;

/**
 * The Class Seat.
 */
@SuppressWarnings("serial")
public class Seat implements Serializable, Cloneable {

	/**
	 * The Enum SeatEnum.
	 */
	public static enum SeatEnum {
		AVAIABLE, PRENOATED, NOT_AVAIABLE
	};

	private Map<DateTime, User> users;
	private Map<DateTime, SeatEnum> statuses;

	/**
	 * Instantiates a new seat.
	 */
	public Seat() {
		this.users = new HashMap<DateTime, User>();
		this.statuses = new HashMap<DateTime, SeatEnum>();
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public Map<DateTime, User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the users
	 */
	public void setUsers(HashMap<DateTime, User> users) {
		this.users = users;
	}

	/**
	 * Gets the statuses.
	 *
	 * @return the statuses
	 */
	public Map<DateTime, SeatEnum> getStatuses() {
		return statuses;
	}

	/**
	 * Sets the status.
	 *
	 * @param statuses the statuses
	 */
	public void setStatus(Map<DateTime, SeatEnum> statuses) {
		this.statuses = statuses;
	}

	/**
	 * Return a string rapresentation of the seat object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" + "statuses: " + this.statuses + "users: " + this.users + "}";
	}

	/**
	 * Make a "deep" comparision between this object and another object.
	 * 
	 * @return true, if the comparated object have the same class and the same
	 *         proprierties
	 */
	@Override
	public boolean equals(Object anotherObject) {
		if (anotherObject == null)
			return false;
		if (this.getClass() != anotherObject.getClass())
			return false;
		Seat other = (Seat) anotherObject;
		return this.statuses.equals(other.statuses) && this.users.equals(other.users);
	}

	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			Seat cloned = (Seat) super.clone();

			cloned.statuses = this.statuses;
			cloned.users = this.users;

			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
