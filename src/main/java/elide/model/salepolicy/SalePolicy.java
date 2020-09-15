package elide.model.salepolicy;

import java.io.Serializable;

import elide.model.user.User.ClientKindEnum;

/**
 * The Class SalePolicy.
 */
@SuppressWarnings("serial")
public class SalePolicy implements Serializable, Cloneable {

	private ClientKindEnum kind;
	private int discountPercentage;
	private boolean actived;
	
	/**
	 * Instantiates a new sale policy.
	 *
	 * @param kind the kind of user we want to facilitate
	 * @param actived is this discount policy active?
	 */
	public SalePolicy(ClientKindEnum kind, boolean actived) {
		this.kind 							= kind;
		this.discountPercentage = kind.getValue();
		this.actived 						= actived;
	}
	
	/**
	 * Calculate the final price given a percetange to remove.
	 *
	 * @param 	number the original number
	 * @param 	discountPercentage the percentage to remove
	 * @return 	the final number
	 */
	public static double calcPerc(double number, double discountPercentage) {
		if(number < 0 || discountPercentage < 0) {
			throw new IllegalArgumentException();
		}
		return number - ((number/100) * discountPercentage);			
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
		this.kind = kind;
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
	 * Checks if is actived.
	 *
	 * @return true, if is actived
	 */
	public boolean isActived() {
		return actived;
	}

	/**
	 * Sets the actived.
	 *
	 * @param actived the new actived
	 */
	public void setActived(boolean actived) {
		this.actived = actived;
	}

	/** 
	 * Return a string rapresentation of the sale policy object
	 * 
	 * @return string rapresentation of the object
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"actived: "							+ this.actived +
				"discountPercentage: " 	+ this.discountPercentage + 
				"kind: "								+ this.kind + 
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
		SalePolicy other = (SalePolicy) anotherObject;
		return this.actived == other.actived &&
				this.discountPercentage == other.discountPercentage &&
				this.kind.equals(other.kind);
	}
	
	/**
	 * Make a "deep" copy of this object.
	 * 
	 * @return cloned, the clone of this object
	 */
	@Override
	public Object clone() {
		try {
			SalePolicy cloned = (SalePolicy) super.clone();
			
			cloned.actived 						= this.actived;
			cloned.discountPercentage = this.discountPercentage;
			cloned.kind 							= this.kind;
			
			return cloned;			
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}

}
