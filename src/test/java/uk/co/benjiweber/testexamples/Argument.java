package uk.co.benjiweber.testexamples;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class Argument {
	public static <T> Answer<T> argument(final int num) {
		return new Answer<T>() {
			@SuppressWarnings("unchecked")
			public T answer(InvocationOnMock invocation) throws Throwable {
				return (T) invocation.getArguments()[num - 1];
			}
		};
	}
}
