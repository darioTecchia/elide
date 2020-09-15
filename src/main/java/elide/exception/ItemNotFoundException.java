package elide.exception;

/**
 * The Class ItemNotFoundException.
 */
@SuppressWarnings("serial")
public class ItemNotFoundException extends RuntimeException {
	
	/**
	 * Instantiates a new ItemNotFoundException.
	 */
	public ItemNotFoundException() {
		super();
	}
	
	/**
	 * Instantiates a new ItemNotFoundException.
	 *
	 * @param errMessage the error message
	 */
	public ItemNotFoundException(String errMessage) {
		super(errMessage);
	}

}
