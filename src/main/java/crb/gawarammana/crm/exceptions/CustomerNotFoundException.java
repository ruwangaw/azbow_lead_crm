package crb.gawarammana.crm.exceptions;

public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
