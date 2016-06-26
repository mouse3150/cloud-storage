package com.buaa.cloudstore.store;

public class StoreException extends Exception {
	private static final long serialVersionUID = -6371427172486913862L;

	public StoreException() {
	}

	public StoreException(String message) {
		super(message);
	}

	public StoreException(Throwable cause) {
		super(cause);
	}

	public StoreException(String message, Throwable cause) {
		super(message, cause);
	}
}
