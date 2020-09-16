package elide.model.stadium;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.joda.time.DateTime;

import elide.exception.ItemNotFoundException;
import elide.model.match.Match;
import elide.model.seat.Seat;
import elide.model.seat.Seat.SeatEnum;
import elide.model.user.User;

/**
 * The Class Stadium.
 */
@SuppressWarnings("all")
public class Stadium implements Serializable, Cloneable {

	private String name;
	private double taking;
	private ArrayList<Match> matches;
	private int capiency;
	private ArrayList<Seat> seats;
	private double pricePerMatch;
	private int discountPercentage;
	private boolean activeSale;

	/**
	 * Instantiates a new stadium.
	 *
	 * @param name          the stadium's name
	 * @param capiency      the stadium's capiency
	 * @param pricePerMatch the price per match
	 * @throws IllegalArgumentException the structure's name should be not empty or
	 *                                  starts with space
	 */
	public Stadium(String name, int capiency, double pricePerMatch) {
		if (!name.matches("(([a-zA-Z]{1,}(\\W)*(\\s)*[a-zA-Z]{1,})(\\s)*)*")) {
			throw new IllegalArgumentException("Stadium's name is not valid");
		}
		this.name = name;
		this.taking = 0.0;
		this.matches = new ArrayList<Match>();
		this.capiency = capiency;
		this.seats = seatFiller();
		this.pricePerMatch = pricePerMatch;
		this.discountPercentage = 0;
		this.activeSale = false;
		seatsInitializer();
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
		if (!name.matches("(([a-zA-Z]{1,}(\\W)*(\\s)*[a-zA-Z]{1,})(\\s)*)*")) {
			throw new IllegalArgumentException("Stadium's name is not valid");
		}
		this.name = name;
	}

	/**
	 * Gets the taking.
	 *
	 * @return the taking
	 */
	public double getTaking() {
		return taking;
	}

	/**
	 * Sets the taking.
	 *
	 * @param taking the new taking
	 */
	public void setTaking(double taking) {
		this.taking = taking;
	}

	/**
	 * Gets the matches.
	 *
	 * @return the matches
	 */
	public ArrayList<Match> getMatches() {
		return matches;
	}

	/**
	 * Gets the matches by id.
	 *
	 * @param ID the match's ID
	 * @return the matches with this ID
	 */
	public Match getMatchesByID(int ID) {
		for (Match m : this.matches) {
			if (m.getID() == ID) {
				return m;
			}
		}
		throw new ItemNotFoundException("No match founded with this ID: " + ID);
	}

	/**
	 * Sets the matches.
	 *
	 * @param matches the new matches
	 */
	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

	/**
	 * Gets the capiency.
	 *
	 * @return the capiency
	 */
	public int getCapiency() {
		return capiency;
	}

	/**
	 * Sets the capiency.
	 *
	 * @param capiency the new capiency
	 */
	public void setCapiency(int capiency) {
		// Reduce the capiency
		if (this.capiency > capiency) {
			for (int i = 0; i < this.capiency - capiency; i++) {
				this.seats.remove(this.seats.size() - 1);
			}
			this.capiency = capiency;
		}
		// Increment the capiency
		else if (capiency > this.capiency) {
			for (int i = 0; i < capiency; i++) {
				this.seats.add(new Seat());
			}
		}
		this.capiency = capiency;
	}

	/**
	 * Gets the seats.
	 *
	 * @return the seats
	 */
	public ArrayList<Seat> getSeats() {
		return seats;
	}

	/**
	 * Sets the seats.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	/**
	 * Seats entry cleaner. This function remove all the expired entry from the
	 * stadium's seats
	 */
	public void seatsEntryCleaner() {
		for (Seat seat : this.seats) {
			for (Entry<DateTime, User> entry : seat.getUsers().entrySet()) {
				if (entry.getKey().isBeforeNow()) {
					seat.getUsers().remove(entry.getKey());
				}
			}
			for (Entry<DateTime, SeatEnum> entry : seat.getStatuses().entrySet()) {
				if (entry.getKey().isBeforeNow()) {
					seat.getStatuses().remove(entry.getKey());
				}
			}
		}
	}

	/**
	 * Gets the price per match.
	 *
	 * @return the price per match
	 */
	public double getPricePerMatch() {
		return pricePerMatch;
	}

	/**
	 * Sets the price per match.
	 *
	 * @param pricePerMatch the new price per match
	 */
	public void setPricePerMatch(double pricePerMatch) {
		this.pricePerMatch = pricePerMatch;
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
	 * Checks if is active sale.
	 *
	 * @return true, if is active sale
	 */
	public boolean isActiveSale() {
		return activeSale;
	}

	/**
	 * Sets the active sale.
	 *
	 * @param activeSale the new active sale
	 */
	public void setActiveSale(boolean activeSale) {
		this.activeSale = activeSale;
	}

	/**
	 * Removes the finished matches.
	 */
	public void removeFinishedMatches() {
		ArrayList<Match> matchesToRemove = new ArrayList<Match>();
		for (Match match : this.matches) {
			if (match.isFinished()) {
				matchesToRemove.add(match);
			}
		}
		this.matches.removeAll(matchesToRemove);
	}

	/**
	 * Seat filler. This function fill the {@code stadiums} list with empty seats
	 *
	 * @return the filled array list
	 */
	private ArrayList<Seat> seatFiller() {
		ArrayList<Seat> s = new ArrayList<Seat>(this.capiency);
		for (int i = 0; i < this.capiency; i++) {
			s.add(new Seat());
		}
		return s;
	}

	/**
	 * Seats initializer. This function set the avaiable status to the seat and a
	 * null user
	 */
	public void seatsInitializer() {
		for (Match match : this.matches) {
			for (Seat seat : this.seats) {
				seat.getStatuses().put(match.getDate(), SeatEnum.AVAIABLE);
				seat.getUsers().put(match.getDate(), null);
			}
		}
	}

	/**
	 * Return a string rapresentation of the stadium object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" + "activeSale: " + this.activeSale + "capiency: " + this.capiency
				+ "discountPercentage: " + this.discountPercentage + "matches: " + this.matches + "name: " + this.name
				+ "pricePerMatch: " + this.pricePerMatch + "seats: " + this.seats + "taking: " + this.taking + "}";
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
		Stadium other = (Stadium) anotherObject;
		return this.activeSale == other.activeSale && this.capiency == other.capiency
				&& this.discountPercentage == other.discountPercentage && this.matches.equals(other.matches)
				&& this.name.equals(other.name) && this.pricePerMatch == other.pricePerMatch && this.seats.equals(other.seats)
				&& this.taking == other.taking;
	}

	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			Stadium cloned = (Stadium) super.clone();

			cloned.activeSale = this.activeSale;
			cloned.capiency = this.capiency;
			cloned.discountPercentage = this.discountPercentage;
			cloned.matches = (ArrayList<Match>) this.matches.clone();
			cloned.name = this.name;
			cloned.pricePerMatch = this.pricePerMatch;
			cloned.seats = (ArrayList<Seat>) this.seats.clone();
			cloned.taking = this.taking;

			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}