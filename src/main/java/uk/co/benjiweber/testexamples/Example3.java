package uk.co.benjiweber.testexamples;

import static uk.co.benjiweber.testexamples.FoxBuilder.Speed.QUICK;
import static uk.co.benjiweber.testexamples.FoxBuilder.Colour.BROWN;
import static uk.co.benjiweber.testexamples.FoxBuilder.Gender.MALE;

public class Example3 {
	FoxBuilder foxBuilder;
	Cache cache;
	
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
		
		dog = cache.put(dog);
		
		fox.jumpOver(dog);
	}
}
