package elide.model.team;

import java.io.Serializable;

/**
 * The Class Team.
 */
@SuppressWarnings("serial")
public class Team implements Serializable, Cloneable {

	private String name;

	/**
	 * Instantiates a new team.
	 *
	 * @param name the name
	 */
	public Team(String name) {
		if(!name.matches("(([a-zA-Z]{1,}(\\W)*(\\s)*[a-zA-Z]{1,})(\\s)*)*")) {
			throw new IllegalArgumentException("Team's name not valid");
		}
		this.name = name;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		if(!name.matches("(([a-zA-Z]{1,}(\\W)*(\\s)*[a-zA-Z]{1,})(\\s)*)*")) {
			throw new IllegalArgumentException("Team's name not valid");
		}
		this.name = name;
	}

	/** 
	 * Return a string rapresentation of the team object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"name: " + this.name + 
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
		Team other = (Team) anotherObject;
		return this.name.equals(other.name);
	}

	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			Team cloned = (Team) super.clone();

			cloned.name = this.name;

			return cloned;
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
