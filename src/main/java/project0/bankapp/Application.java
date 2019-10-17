package project0.bankapp;

import project0.views.Landing;
import project0.views.View;

public class Application {

	public static void main(String[] args) {
		View currentview = new Landing();
		Landing greeter = new Landing();
		
		greeter.greet();
		
		while(currentview != null) {
			currentview = currentview.process();
		}
		
		greeter.goodbye();
	}

}
