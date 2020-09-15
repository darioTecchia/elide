package elide.model.user;

import java.io.Serializable;
import java.util.ArrayList;

import elide.model.ticket.Ticket;
import elide.model.match.Match;
import elide.model.seat.Seat;
import elide.model.stadium.Stadium;

/**
 * The Class User.
 */
@SuppressWarnings("all")
public class User implements Serializable, Cloneable {

	/**
	 * The Enum ClientKindEnum.
	 */
	public enum ClientKindEnum {

		NORMAL  (0),
		ADMIN		(1),
		KIDS 		(50),
		ELDERLY (15),
		STUDENT (25);

		private int value;

		/**
		 * Instantiates a new client kind enum.
		 *
		 * @param value the value
		 */
		private ClientKindEnum(int value) {
			this.value = value;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public int getValue() {
			return this.value;
		}

	}

	private String name;
	private String password;
	private boolean admin;
	private ArrayList<Ticket> preOrderedTickets;
	private ArrayList<Ticket> purchasedTickets;
	private ClientKindEnum kind;

	/**
	 * Instantiates a new admin user.
	 *
	 * @param name the admin's name
	 * @param password the admin's password
	 */
	public User(String name, String password) {
		if(!name.matches("[a-zA-Z]{2,}")) {
			throw new IllegalArgumentException("The name should contains at leat 2 letter");			
		}
		if(!password.matches("[a-zA-Z]{2,}")) {
			throw new IllegalArgumentException("The password should contains at leat 2 letter");			
		}
		this.name 							= name;
		this.password 					= password;
		this.admin 							= true;
		this.preOrderedTickets	= new ArrayList<Ticket>();
		this.purchasedTickets 	= new ArrayList<Ticket>();
		this.kind 							= ClientKindEnum.ADMIN;
	}

	/**
	 * Instantiates a new client user.
	 *
	 * @param name the client's name
	 * @param password the client's password
	 * @param kind the client's kind
	 */
	public User(String name, String password, ClientKindEnum kind) {
		if(!name.matches("[a-zA-Z]{2,}")) {
			throw new IllegalArgumentException("The name should contains at leat 2 letter");			
		}
		if(!password.matches("[a-zA-Z]{2,}")) {
			throw new IllegalArgumentException("The password should contains at leat 2 letter");			
		}
		if(kind.equals(ClientKindEnum.ADMIN)) {
			throw new IllegalArgumentException("This is the constructor for the client, so the kind can't be ADMIN");
		}
		this.name 							= name;
		this.password						= password;
		this.admin 							= false;
		this.preOrderedTickets 	= new ArrayList<Ticket>();
		this.purchasedTickets 	= new ArrayList<Ticket>();
		this.kind 							= kind;			
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
		this.name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Checks if is admin.
	 *
	 * @return true, if is admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Sets the admin.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Gets the pre ordered tickets.
	 *
	 * @return the pre ordered tickets
	 */
	public ArrayList<Ticket> getPreOrderedTickets() {
		return preOrderedTickets;
	}

	/**
	 * Sets the pre ordered tickets.
	 *
	 * @param preOrderedTickets the new pre ordered tickets
	 */
	public void setPreOrderedTickets(ArrayList<Ticket> preOrderedTickets) {
		this.preOrderedTickets = preOrderedTickets;
	}

	/**
	 * Gets the purchased tickets.
	 *
	 * @return the purchased tickets
	 */
	public ArrayList<Ticket> getPurchasedTickets() {
		return purchasedTickets;
	}

	/**
	 * Sets the purchased tickets.
	 *
	 * @param purchasedTickets the new purchased tickets
	 */
	public void setPurchasedTickets(ArrayList<Ticket> purchasedTickets) {
		this.purchasedTickets = purchasedTickets;
	}

	/**
	 * Gets the kind.
	 *
	 * @return the kind
	 */
	public ClientKindEnum getKind() {
		return kind;
	}

	/**
	 * Sets the kind.
	 *
	 * @param kind the new kind
	 */
	public void setKind(ClientKindEnum kind) {
		if(kind.equals(ClientKindEnum.ADMIN) && !this.isAdmin()) {
			throw new IllegalArgumentException("This user is not a admin");
		}
		this.kind = kind;
	}

	/** 
	 * Return a string rapresentation of the user object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"admin: " 						+ this.admin +
				"kind: "							+ this.kind	+
				"name: "							+ this.name +
				"password"						+ this.password + 
				"preOrderTickets: " 	+ this.preOrderedTickets +
				"purchasedTickets: " 	+ this.purchasedTickets +
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
		User other = (User) anotherObject;
		return this.admin == other.admin &&
				this.kind.equals(other.kind) &&
				this.name.equals(other.name) &&
				this.password.equals(other.password) &&
				this.preOrderedTickets.equals(other.preOrderedTickets) &&
				this.purchasedTickets.equals(other.purchasedTickets);
	}

	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			User cloned = (User) super.clone();

			cloned.admin 							= this.admin;
			cloned.kind								=	this.kind;
			cloned.name								= this.name;
			cloned.password 					= this.password;
			cloned.preOrderedTickets 	= (ArrayList<Ticket>) this.preOrderedTickets.clone();				
			cloned.purchasedTickets 	= (ArrayList<Ticket>) this.purchasedTickets.clone();				

			return cloned;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}

}
