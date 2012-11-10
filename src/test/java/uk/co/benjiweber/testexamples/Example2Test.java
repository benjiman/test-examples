package uk.co.benjiweber.testexamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static uk.co.benjiweber.testexamples.FoxBuilder.Speed.QUICK;
import static uk.co.benjiweber.testexamples.FoxBuilder.Colour.BROWN;
import static uk.co.benjiweber.testexamples.FoxBuilder.Gender.MALE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Example2Test {
	FoxBuilder foxBuilder = mock(FoxBuilder.class, Return.Self);
	
	@Mock Fox fox;
	
	@InjectMocks
	Example2 example = new Example2();
	
	// Now suppose we decided we that the dog should be instantiated within the method instead of a field on Example. Making it hard to test.
	// We could create a dogFactory and stub out the creation of the dog. However, this adds complexity and changes the implemention for the test
	// We could use powermock to mock the constructor
	// However there can be valid reasons not to mock objects like this. For example it's good to avoid mocking Value Objects
	// So how can we test it with a real Object? Use an argumentCaptor
	// Now we can both check the fox jumps over a dog and that the dog is lazy.
	@Test public void whenSomeMethodCalled_aRealFox_shouldJumpOverTheLazyDog() {
		when(foxBuilder.build()).thenReturn(fox);
		example.someMethod();
		
		ArgumentCaptor<Dog> dogCaptor = ArgumentCaptor.forClass(Dog.class);
		verify(fox).jumpOver(dogCaptor.capture());
		assertTrue(dogCaptor.getValue().isLazy());		
	}

}
