package uk.co.benjiweber.testexamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static uk.co.benjiweber.testexamples.FoxBuilder.Speed.QUICK;
import static uk.co.benjiweber.testexamples.FoxBuilder.Colour.BROWN;
import static uk.co.benjiweber.testexamples.FoxBuilder.Gender.MALE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Example1Test {
	FoxBuilder foxBuilder = mock(FoxBuilder.class, Return.Self);
	FoxBuilder quickFoxBuilder = mock(FoxBuilder.class, Return.Self);
	
	@Mock Fox fox;
	@Mock Dog dog;
	
	@InjectMocks
	Example1 example = new Example1();
	
	@Test public void whenSomeMethodCalled_aFox_shouldJumpOverTheLazyDog() {
		when(foxBuilder.build()).thenReturn(fox);
		example.someMethod();
		verify(fox).jumpOver(dog);
	}
	
	// Suppose we were particularly concerned that we construct a QUICK fox.
	// We could do this if it's the call to the builder we want to ensure happens
	// However this doesn't tell us that the /quick/ fox jumped over the lazy dog
	@Test public void whenSomeMethodCalled_shouldCreateQuickFox() {
		when(foxBuilder.build()).thenReturn(fox);
		example.someMethod();
		verify(foxBuilder).speed(QUICK);
	}
		
	// To check that the fox jumping over the dog is quick we can utilise two mock builders like so
	// Notice that we have not had to stub out colour/legs/longTail/gender in this test because it's irrelevant
	@Test public void whenSomeMethodCalled_shouldJumpOverAFoxThatIsQuick() {
		when(foxBuilder.speed(QUICK)).thenReturn(quickFoxBuilder);
		when(quickFoxBuilder.build()).thenReturn(fox);
		example.someMethod();
		verify(fox).jumpOver(dog);
	}

}
