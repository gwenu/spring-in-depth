package ag.spring.in.depth;

import javax.annotation.PostConstruct;

import ag.spring.in.depth.annotation.DeprecatedClass;
import ag.spring.in.depth.annotation.InjectRandomInt;
import ag.spring.in.depth.annotation.PostProxy;
import ag.spring.in.depth.annotation.Profiling;

@Profiling
@DeprecatedClass(newImplementation = SheldonQuoteV2.class)
public class SheldonQuote implements Quote {

	private static final String SHELDON_QUOTE = "For the record, it could kill us to meet new people. They could be murderers or the carriers of unusual pathogens. And I'm not insane, my mother had me tested.";

	@InjectRandomInt(min = 2, max = 7)
	private int randomNumber;
	private String message;

	public SheldonQuote() {
		System.out.println("Will be executed on Java obj creation: Phase 1");
	}

	@Override
	@PostProxy
	public void sayQuote() {
		System.out.println("After all will be configured and all proxy will be applied - Phase 3");
		System.out.println("Quote: " + message + "\nRandom: " + randomNumber);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public void setDefaultMessage() {
		this.message = SHELDON_QUOTE;
	}

	@PostConstruct
	public void init() {
		System.out.println(
				"Will be executed after Java obj was created and after Spring finished it work with beans: Phase 2");
	}
}
