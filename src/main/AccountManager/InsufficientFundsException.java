package main.AccountManager;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 2170399928426194541L;

	public InsufficientFundsException() {
		super();
	}

	public InsufficientFundsException(String errorMessage) {
		super(errorMessage);
	}
	
}
