package uk.co.benjiweber.testexamples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.benjiweber.mockito.MockUtils;

import static org.mockito.Mockito.*;
import static uk.co.benjiweber.mockito.MockUtils.NotStubbedException;
import static uk.co.benjiweber.mockito.MockUtils.strictMock;
import static uk.co.benjiweber.mockito.MockUtils.verifyNoUnstubbedInteractions;

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

        duck.quack();
        duck.waddle();

        //Why do we need to do this?
        verify(duck).quack();
        verify(duck).waddle();

        verifyNoMoreInteractions(duck);
    }
}
