package uk.co.benjiweber.testexamples;

import static uk.co.benjiweber.testexamples.FoxBuilder.Speed.QUICK;
import static uk.co.benjiweber.testexamples.FoxBuilder.Colour.BROWN;
import static uk.co.benjiweber.testexamples.FoxBuilder.Gender.MALE;

public class Example2 {
	FoxBuilder foxBuilder;
	
	public void someMethod() {
		Fox fox = foxBuilder
			.speed(QUICK)
			.colour(BROWN)
			.legs(4)
			.longTail()
			.gender(MALE)
			.build();
		
		Dog dog = new Dog();
			
		dog.setLazy();
		
		fox.jumpOver(dog);
	}
}
