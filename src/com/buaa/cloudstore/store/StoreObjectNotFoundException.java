package com.buaa.cloudstore.store;

public class StoreObjectNotFoundException extends Exception {
	private static final long serialVersionUID = -6371427172486913862L;

	public StoreObjectNotFoundException() {
	}

	public StoreObjectNotFoundException(String message) {
		super(message);
	}

	public StoreObjectNotFoundException(Throwable cause) {
		super(cause);
	}

	public StoreObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
