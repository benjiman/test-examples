package uk.co.benjiweber.testexamples;

import org.mockito.Answers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class Return {
	public static Answer<?> Self = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) throws Throwable {
			if (invocation.getMethod().getReturnType().isAssignableFrom(invocation.getMock().getClass())) {
				return invocation.getMock();
			}
			
			return null;
		}
	};
}
