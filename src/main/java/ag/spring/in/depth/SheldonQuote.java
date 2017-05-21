package ag.spring.in.depth;

import javax.annotation.PostConstruct;

import ag.spring.in.depth.annotation.InjectRandomInt;
import ag.spring.in.depth.annotation.Profiling;

@Profiling
public class SheldonQuote implements Quote {

	@InjectRandomInt(min = 2, max = 7)
	private int randomNumber;
	private String message;
	
	public SheldonQuote() {
		System.out.println("Will be executed on Java obj creation: Phase 1");
	}

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
	
	@PostConstruct
	public void init() {
		System.out.println("Will be executed after Java obj was created and after Spring finished it work with beans: Phase 2");
	}
}
