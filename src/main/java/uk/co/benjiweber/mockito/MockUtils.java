package uk.co.benjiweber.mockito;

import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockUtils {

    public static <T> T strictMock(Class<T> cls) {
        return Mockito.mock(cls, new StrictMockHandler<T>());
    }

    public static class StrictMockHandler<T> implements Answer<T> {
        public boolean strict = false;

        public T answer(InvocationOnMock invocation) throws Throwable {
            if (strict) throw new NotStubbedException(invocation.getMethod().getName());
            return null;
        }
    }

    public static void verifyNoUnstubbedInteractions(Object mock) {
        StrictMockHandler handler = ((StrictMockHandler) new MockUtil().getMockHandler(mock).getMockSettings().getDefaultAnswer());
        handler.strict = true;
    }

    public static class NotStubbedException extends RuntimeException {
        public NotStubbedException(String message) {
            super(message);
        }
    }

}
