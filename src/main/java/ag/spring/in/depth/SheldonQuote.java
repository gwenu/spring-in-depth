package ag.spring.in.depth;

import ag.spring.in.depth.annotation.InjectRandomInt;

public class SheldonQuote implements Quote {

	@InjectRandomInt(min = 2, max = 7)
	private int randomNumber;
	private String message;

	@Override
	public void sayQuote() {
		System.out.println("Quote: " + message + "\nRandom: " + randomNumber);
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
}
