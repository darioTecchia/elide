package elide.exception;

@SuppressWarnings("serial")
public class ItemAlreadyPresentException extends Exception {

	public ItemAlreadyPresentException() {
		super();
	}

	public ItemAlreadyPresentException(String message) {
		super(message);
	}

}
