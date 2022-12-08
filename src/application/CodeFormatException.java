package application;

public class CodeFormatException extends Exception {

	public CodeFormatException(String message) {
		super(message);
		System.out.println(message);
	}

}
