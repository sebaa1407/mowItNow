package fr.xebia.mowitNow.exception;

public class MowerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;	

	
	public MowerException(String message) {
		this.setMessage(message);
 
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

 
}