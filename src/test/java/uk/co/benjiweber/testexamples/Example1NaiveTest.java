package uk.co.benjiweber.testexamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static uk.co.benjiweber.testexamples.FoxBuilder.Speed.QUICK;
import static uk.co.benjiweber.testexamples.FoxBuilder.Colour.BROWN;
import static uk.co.benjiweber.testexamples.FoxBuilder.Gender.MALE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Example1NaiveTest {
	@Mock FoxBuilder foxBuilder;
	@Mock Fox fox;
	@Mock Dog dog;
	
	@InjectMocks
	Example1 example = new Example1();
	
	// This is a naive implementation
	// The stubbing is very verbose (and could be worse with a less trivial example
	// We are also testing several things in one test and there's a number of ways we could break it
	// This is undesireable
	@Test public void naiveTest() {
		when(foxBuilder.speed(QUICK)).thenReturn(foxBuilder);	
		when(foxBuilder.colour(BROWN)).thenReturn(foxBuilder);
		when(foxBuilder.legs(4)).thenReturn(foxBuilder);
		when(foxBuilder.longTail()).thenReturn(foxBuilder);
		when(foxBuilder.gender(MALE)).thenReturn(foxBuilder);
		when(foxBuilder.build()).thenReturn(fox);
		
		example.someMethod();
		
		verify(fox).jumpOver(dog);
	}
	

}
