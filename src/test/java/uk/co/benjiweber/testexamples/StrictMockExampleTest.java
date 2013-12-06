package uk.co.benjiweber.testexamples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static uk.co.benjiweber.mockito.MockUtils.*;

@RunWith(MockitoJUnitRunner.class)
public class StrictMockExampleTest {

    @Test
    public void exampleStubbedAll() throws Exception {
        Duck duck = strictMock(Duck.class);
        when(duck.quack()).thenReturn("quack");

        verifyNoUnstubbedInteractions(duck);

        duck.quack();
    }

    @Test(expected = NotStubbedException.class)
    public void exampleNotStubbedAll() throws Exception {
        Duck duck = strictMock(Duck.class);
        when(duck.quack()).thenReturn("quack");

        verifyNoUnstubbedInteractions(duck);

        duck.quack();
        duck.waddle();
    }

    @Test
    public void exampleOfRedundantVerify() throws Exception {
        Duck duck = mock(Duck.class);
        when(duck.quack()).thenReturn("quack");
        when(duck.waddle()).thenReturn("waddle");

        duck.quack();
        duck.waddle();

        //Why do we need to do this?
        verify(duck).quack();
        verify(duck).waddle();

        verifyNoMoreInteractions(duck);
    }
}
