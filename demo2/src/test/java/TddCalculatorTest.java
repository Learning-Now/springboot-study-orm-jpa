import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("문자열 계산 테스트 with tdd")
class TddCalculatorTest {

	private TddCalculator tddCalculator;

	@BeforeEach
	void setUp() {
		tddCalculator = new TddCalculator();
	}

	@DisplayName("3을 입력시 3을 반환한다.")
	@Test
	void singleNumberWhenValue3() {
		//given
		final String number = "3";
		//when
		int actual = tddCalculator.add(number);
		//then
		assertThat(actual).isEqualTo(3);
	}

	@DisplayName("5를 입력시 5를 반환한다.")
	@Test
	void singleNumberWhenValue5() {
		//given
		final String number = "5";
		//when
		int actual = tddCalculator.add(number);
		//then
		assertThat(actual).isEqualTo(5);
	}

	@DisplayName("공백을 입력시 0을 반혼한다")
	@Test
	void convertToZero() {
		//given
		final String number = "";
		//when
		final int actual = tddCalculator.add(number);
		//then
		assertThat(actual).isEqualTo(0);
	}

	@DisplayName("쉼표 구분자를 입력시 값을 더한다")
	@Test
	void addWhenDefaultSeparator() {
		//given
		final String expect = "2,3";
		//when
		final int actual = tddCalculator.add(expect);
		//then
		assertThat(actual).isEqualTo(5);
	}

	@DisplayName("콜론 구분자를 입력시 값을 더한다")
	@Test
	void addWhenColonSeparator() {
		//given
		final String expect = "2;3";
		//when
		final int actual = tddCalculator.add(expect);
		//then
		assertThat(actual).isEqualTo(5);
	}

	@DisplayName("여러개의 콜론 구분자를 입력시 값을 더한다")
	@Test
	void addWhenColonSeparatorWhenMultiValue() {
		//given
		final String expect = "2;3;4";
		//when
		final int actual = tddCalculator.add(expect);
		//then
		assertThat(actual).isEqualTo(9);
	}
}