package net.gobbz.ratenrechner;

@SuppressWarnings("serial")
public class RatenRechnerException extends RuntimeException {
	public RatenRechnerException(String msg) {
		super(msg);
	}
	
	public RatenRechnerException() {
		super();
	}
}
