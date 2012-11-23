package uk.co.benjiweber.testexamples;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.cartesianProduct;
import static java.util.EnumSet.allOf;
import static org.junit.Assert.assertEquals;
import static uk.co.benjiweber.testexamples.StateTest.Result.OK;
import static uk.co.benjiweber.testexamples.StateTest.State.FIRST_STATE;
import static uk.co.benjiweber.testexamples.StateTest.State.SECOND_STATE;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Function;

@RunWith(value = Parameterized.class)
public class StateTest {
	private final UnderTest underTest;
	private final Result expectedResult;
	
	public StateTest(State a, State b) {
		this.underTest = new UnderTest(a, b);
		
		if (a == FIRST_STATE) {
			expectedResult = OK;
		} else if (a == SECOND_STATE && b == SECOND_STATE) {
			expectedResult = OK;
		} else {
			expectedResult = Result.NOT_OK;
		}
	}

	@Parameters
	@SuppressWarnings("unchecked")
	public static Collection<Object[]> data() {
		return newArrayList(transform(cartesianProduct(allOf(State.class), allOf(State.class)),TO_OBJ_ARRAY));
	}
	
	@Test
	public void testStates() {
		assertEquals(expectedResult, underTest.methodUnderTest());
	}
	

	public enum State {
		FIRST_STATE, SECOND_STATE
	}
	
	public enum Result {
		OK, NOT_OK
	}

	static class UnderTest {
		State A;
		State B;

		public UnderTest(State a, State b) {
			A = a;
			B = b;
		}
		
		public Result methodUnderTest() {
			return OK;
		}
	}
	
	private static Function<List<?>,Object[]> TO_OBJ_ARRAY = new Function<List<?>, Object[]>() {
		public Object[] apply(List<?> input) {
			return input.toArray();
		}
	};
}
