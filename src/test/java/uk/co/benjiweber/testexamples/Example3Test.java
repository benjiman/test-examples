package uk.co.benjiweber.testexamples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static uk.co.benjiweber.testexamples.Argument.argument;
import static uk.co.benjiweber.testexamples.FoxBuilder.Speed.QUICK;
import static uk.co.benjiweber.testexamples.FoxBuilder.Colour.BROWN;
import static uk.co.benjiweber.testexamples.FoxBuilder.Gender.MALE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Example3Test {
	FoxBuilder foxBuilder = mock(FoxBuilder.class, Return.Self);
	FoxBuilder quickFoxBuilder = mock(FoxBuilder.class, Return.Self);

	@Mock
	Cache mockCache;
	@Mock
	Fox fox;
	@Mock 
	Dog dog;

	@InjectMocks
	Example3 example = new Example3();

	// Let's add one more layer of complexity.
	// Suppose we want to put our dog in a cache for later use as above and the
	// dog the fox jumps over is returned by the cache
	// This presents some challenges to test if we have stubbed the cache.
	// One approach would be to test both the dog being lazy and the cache
	// adding in the same test
	// Re-using the argumentCaptor from above
	// This is bad because there are two things being asserted in one test
	@Test
	public void bad_whenSomeMethodCalled_aRealFox_shouldJumpOverTheLazyDog() {
		when(foxBuilder.build()).thenReturn(fox);
		when(mockCache.put(any(Dog.class))).thenReturn(dog);

		example.someMethod();

		ArgumentCaptor<Dog> dogCaptor = ArgumentCaptor.forClass(Dog.class);
		verify(mockCache).put(dogCaptor.capture());
		assertTrue(dogCaptor.getValue().isLazy());

		verify(fox).jumpOver(dog);
	}

	// The trick is to use a Mockito Answer again to create a custom stubbing
	// rule.
	// Now the test is only one line longer than before adding the cache.
	// We could even move the cache stubbing to an @Before section to unclutter
	// the test (as it's generic cache behaviour)
	@Test
	public void whenSomeMethodCalled_aRealFox_shouldJumpOverTheLazyDog() {
		when(foxBuilder.build()).thenReturn(fox);
		when(mockCache.put(any())).thenAnswer(argument(1));

		example.someMethod();
		ArgumentCaptor<Dog> dogCaptor = ArgumentCaptor.forClass(Dog.class);

		verify(fox).jumpOver(dogCaptor.capture());
		assertTrue(dogCaptor.getValue().isLazy());
	}

	// If we actually want to assert that the caching happens we can write
	// another testing
	// This test is consise and when it fails we'll know why.
	// If we cared about the specifics of the dog being cached we could add the
	// ArgumentCaptor back in
	@Test
	public void whenSomeMethodCalled_aFoxShouldBeCached() {
		when(foxBuilder.build()).thenReturn(fox);
		example.someMethod();
		verify(mockCache).put(any(Dog.class));
	}

}
