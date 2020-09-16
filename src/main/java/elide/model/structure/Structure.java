package elide.model.structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import elide.exception.ItemNotFoundException;
import elide.exception.ItemAlreadyPresentException;
import elide.model.salepolicy.SalePolicy;
import elide.model.stadium.Stadium;
import elide.model.team.Team;
import elide.model.user.User;
import elide.model.user.User.ClientKindEnum;

/**
 * The Class Structure.
 */
@SuppressWarnings("all")
public class Structure implements Serializable, Cloneable {

	private String name;
	private ArrayList<Stadium> stadiums;
	private ArrayList<Team> teams;
	private ArrayList<User> registreredUsers;
	private Double taking;
	private User currentUser;
	private ArrayList<SalePolicy> discountPolicies;
	private int saleDay;
	private int discountPercentage;
	private boolean saleDayActivated;

	/**
	 * Instantiates a new structure.
	 *
	 * @param name the structure's name
	 * @throws ClassNotFoundException the class not found exception.
	 * @throws IOException            Signals that an I/O exception has occurred.
	 */
	public Structure(String name) throws ClassNotFoundException, IOException {
		this.name = name;
		this.teams = new ArrayList<Team>();
		this.stadiums = new ArrayList<Stadium>();
		this.registreredUsers = new ArrayList<User>();
		this.taking = this.getTaking();
		this.currentUser = null;
		this.discountPolicies = new ArrayList<SalePolicy>();
		this.saleDay = 1;
		this.discountPercentage = 0;
		this.saleDayActivated = false;

		if (new File("structure.ser").exists()) {
			System.out.println("File exist!");
			load();
		} else {
			System.out.println("File not exist!");
			this.discountPolicies.add(new SalePolicy(ClientKindEnum.ELDERLY, false));
			this.discountPolicies.add(new SalePolicy(ClientKindEnum.KIDS, false));
			this.discountPolicies.add(new SalePolicy(ClientKindEnum.STUDENT, false));
			save();
		}
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
	 * Gets the stadiums.
	 *
	 * @return the stadiums
	 */
	public ArrayList<Stadium> getStadiums() {
		return stadiums;
	}

	/**
	 * Gets the stadium by name.
	 *
	 * @param name the name of the stadium we want
	 * @return the stadium with this name
	 */
	public Stadium getStadiumByName(String name) {
		for (Stadium s : this.stadiums) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		throw new ItemNotFoundException("No stadium founded with this name: " + name);
	}

	/**
	 * Sets the stadiums.
	 *
	 * @param stadiums the new stadiums
	 */
	public void setStadiums(ArrayList<Stadium> stadiums) {
		this.stadiums = stadiums;
	}

	/**
	 * Adds a new team in the structure
	 * 
	 * @param team will be added
	 * @throws ItemAlreadyPresentException if is already present a team with this
	 *                                     name
	 */
	public void addStadium(Stadium stadium) throws ItemAlreadyPresentException {
		try {
			Stadium tempStadium = this.getStadiumByName(stadium.getName());
			if (tempStadium != null) {
				throw new ItemAlreadyPresentException("Team gi� presente con questo nome");
			}
		} catch (ItemNotFoundException e) {
			this.stadiums.add(stadium);
		}
	}

	/**
	 * Gets the taking. It's the sum of all stadium's taking.
	 *
	 * @return the taking
	 */
	public Double getTaking() {
		double taking = 0;
		for (Stadium s : this.stadiums) {
			taking = taking + s.getTaking();
		}
		return taking;
	}

	/**
	 * Sets the taking.
	 *
	 * @param taking the new taking
	 */
	public void setTaking(Double taking) {
		this.taking = taking;
	}

	/**
	 * Gets the current user logged in the system.
	 *
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Sets the current user.
	 *
	 * @param currentUser the new current user
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * Gets the registrered users.
	 *
	 * @return the registrered users
	 */
	public ArrayList<User> getRegistreredUsers() {
		return registreredUsers;
	}

	/**
	 * Gets the teams.
	 *
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * Gets the team by name.
	 *
	 * @param name the name of the team we want
	 * @return the team by name
	 * @throws ItemNotFoundException Signals that the structure don't have a team
	 *                               with this name
	 */
	public Team getTeamByName(String name) {
		for (Team t : this.teams) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		throw new ItemNotFoundException("No team founded with this name: " + name);
	}

	/**
	 * Sets the teams.
	 *
	 * @param teams the new teams
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	/**
	 * Adds a new team in the structure
	 * 
	 * @param team will be added
	 * @throws ItemAlreadyPresentException if is already present a team with this
	 *                                     name
	 */
	public void addTeam(Team team) throws ItemAlreadyPresentException {
		try {
			Team tempTeam = this.getTeamByName(team.getName());
			if (tempTeam != null) {
				throw new ItemAlreadyPresentException("Team gi� presente con questo nome");
			}
		} catch (ItemNotFoundException e) {
			this.teams.add(team);
		}
	}

	/**
	 * Sets the registrered users.
	 *
	 * @param registreredUsers the new registrered users
	 */
	public void setRegistreredUsers(ArrayList<User> registreredUsers) {
		this.registreredUsers = registreredUsers;
	}

	/**
	 * Gets the discount policies.
	 *
	 * @return the discount policies
	 */
	public ArrayList<SalePolicy> getDiscountPolicies() {
		return discountPolicies;
	}

	/**
	 * Sets the discount policies.
	 *
	 * @param discountPolicy the new discount policies
	 */
	public void setDiscountPolicies(ArrayList<SalePolicy> discountPolicy) {
		this.discountPolicies = discountPolicy;
	}

	/**
	 * Gets the discount policy by kind.
	 *
	 * @param discountKind the discount kind of the sale policy we want
	 * @return the discount policy by kind
	 */
	public SalePolicy getDiscountPolicyByKind(ClientKindEnum discountKind) {
		for (SalePolicy discountPolicy : this.discountPolicies) {
			if (discountPolicy.getKind().equals(discountKind)) {
				return discountPolicy;
			}
		}
		throw new ItemNotFoundException("Nessuna politica di sconto trovata con il tipo: " + discountKind.name());
	}

	/**
	 * Gets the sale day.
	 *
	 * @return the sale day
	 */
	public int getSaleDay() {
		return saleDay;
	}

	/**
	 * Sets the sale day.
	 *
	 * @param saleDay the new sale day
	 */
	public void setSaleDay(int saleDay) {
		this.saleDay = saleDay;
	}

	/**
	 * Checks if is sale day activated.
	 *
	 * @return true, if is sale day activated
	 */
	public boolean isSaleDayActivated() {
		return saleDayActivated;
	}

	/**
	 * Sets the sale dat activated.
	 *
	 * @param saleDayActivated the new sale dat activated
	 */
	public void setSaleDayActivated(boolean saleDayActivated) {
		this.saleDayActivated = saleDayActivated;
	}

	/**
	 * Serialize the structure.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void save() throws IOException {
		File structure = new File("structure.ser");
		try {
			FileOutputStream fileOut = new FileOutputStream(structure);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(this);
			objOut.close();
			fileOut.close();
			System.out.println("Structure saved!");
		} catch (IOException e) {
			structure.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(structure);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(this);
			objOut.close();
			fileOut.close();
			System.out.println("Structure saved!");
		}
	}

	/**
	 * Deserialize the structure.
	 *
	 * @throws IOException            Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception, Signals that a
	 *                                model class has changed
	 */
	public void load() throws IOException, ClassNotFoundException {

		Structure structure = null;
		try {
			FileInputStream fileIn = new FileInputStream("structure.ser");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);

			structure = (Structure) objIn.readObject();
			this.name = structure.getName();
			this.registreredUsers = structure.getRegistreredUsers();
			this.stadiums = structure.getStadiums();
			for (Stadium stadium : this.stadiums) {
				stadium.removeFinishedMatches();
			}
			this.taking = structure.getTaking();
			this.teams = structure.getTeams();
			this.discountPolicies = structure.getDiscountPolicies();
			this.saleDay = structure.getSaleDay();
			this.saleDayActivated = structure.isSaleDayActivated();
			this.discountPercentage = structure.getDiscountPercentage();

			objIn.close();
			fileIn.close();
			System.out.println("Structure loaded!");
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

	/**
	 * Gets the discount percentage.
	 *
	 * @return the discount percentage
	 */
	public int getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * Sets the discount percentage.
	 *
	 * @param discountPercentage the new discount percentage
	 */
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	/**
	 * Login.
	 *
	 * @param username the user's name
	 * @param password the user's password
	 */
	public boolean login(String username, String password) {
		System.out.println(username + "-" + password);

		for (User user : this.getRegistreredUsers()) {
			if (user.getName().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				System.out.println("Someone has logged with: " + username + "-" + password);
				this.setCurrentUser(user);
				return true;
			}
		}
		System.out.println("Account not found!");
		return false;
	}

	/**
	 * Logout.
	 */
	public void logout() {
		System.out.println(this.getCurrentUser().getName() + " Logged Out!");
		this.setCurrentUser(null);
	}

	/**
	 * Return a string rapresentation of the structure object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" + "currentUser: " + this.currentUser + "discountPercentage: "
				+ this.discountPercentage + "discountPolicies: " + this.discountPolicies + "name: " + this.name
				+ "registreredUsers: " + this.registreredUsers + "saleDayActivated: " + this.saleDayActivated + "saleDay: "
				+ this.saleDay + "stadiums: " + this.stadiums + "taking: " + this.taking + "teams: " + this.teams + "}";
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
		Structure other = (Structure) anotherObject;
		return this.currentUser.equals(other.currentUser) && this.discountPercentage == other.discountPercentage
				&& this.discountPolicies.equals(other.discountPolicies) && this.name.equals(other.name)
				&& this.registreredUsers.equals(other.registreredUsers) && this.saleDayActivated == other.saleDayActivated
				&& this.saleDay == other.saleDay && this.stadiums.equals(other.stadiums) && this.taking == other.taking
				&& this.teams.equals(other.teams);
	}

	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			Structure cloned = (Structure) super.clone();

			cloned.currentUser = (User) this.currentUser.clone();
			cloned.discountPercentage = this.discountPercentage;
			cloned.discountPolicies = (ArrayList<SalePolicy>) this.discountPolicies.clone();
			cloned.name = this.name;
			cloned.registreredUsers = (ArrayList<User>) this.registreredUsers.clone();
			cloned.saleDayActivated = this.saleDayActivated;
			cloned.saleDay = this.saleDay;
			cloned.stadiums = (ArrayList<Stadium>) this.stadiums.clone();
			cloned.taking = this.taking;
			cloned.teams = (ArrayList<Team>) this.teams.clone();

			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
