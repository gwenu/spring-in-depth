package ag.spring.in.depth;

import ag.spring.in.depth.annotation.PostProxy;

public class SheldonQuoteV2 implements Quote {
	
	private String SHELDON_QUOTE_V2 = "What computer do you have? And please don't say a white one.";
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SheldonQuoteV2() {
		System.out.println("Will be executed on Java obj creation: Phase 1_V2");
	}
	
	public void setDefaultMessage() {
		this.message = SHELDON_QUOTE_V2;
	}
	
	@Override
	@PostProxy
	public void sayQuote() {
		System.out.println("Quote Version 2: " + SHELDON_QUOTE_V2);
	}
}
