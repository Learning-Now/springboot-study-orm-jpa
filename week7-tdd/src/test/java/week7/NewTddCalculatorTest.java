package week7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class NewTddCalculatorTest {

    private NewTddCalculator newTddCalculator;

    @BeforeEach
    void setUp() {
        newTddCalculator = new NewTddCalculator();
    }

    @Test
    @DisplayName("빈 문자열 입력 시 0 출력")
    public void inputEmptyDataTest() {
        //given
        String data = "";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("3 입력 시 3 출력")
    public void inputSingleDataTest1() {
        //given
        String data = "3";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("5 입력 시 5 출력")
    public void inputSingDataTest2() {
        //given
        String data = "5";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("쉼표 구분자 입력 시 더한 값 출력")
    public void inputDoubleDataSeparatedByCommaTest() {
        //given
        String data = "2,3";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(5);
    }
    
    @Test
    @DisplayName("콜론 구분자 입력 시 더한 값 출력")
    public void inputDoubleDataSeparatedByColonTest() {
        //given
        String data = "2:3";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(5);
    }
    
    @Test
    @DisplayName("여러 개의 쉼표 구분자 입력 시 더한 값 출력")
    public void inputMultiDataSeparatedByCommaTest() {
        //given
        String data = "2,3,4,5";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("여러 개의 콜론 구분자 입력 시 더한 값 출력")
    public void inputMultiDataSeparatedByColonTest() {
        //given
        String data = "2:3:4:5";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("여러 개의 쉼표, 콜론 구분자 입력 시 더한 값 출력")
    public void inputMultiDataSeparatedByCommanAndColonTest() {
        //given
        String data = "2,3:4,5:6";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(20);
    }
    
    @Test
    @DisplayName("커스텀 구분자 지정 후 더한 값 출력")
    public void setCustomSeparatorTest() {
        //given
        String data = "//;\n1;2;3";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(6);
    }
    
    @Test
    @DisplayName("숫자 이외의 값 전달 시 예외 발생")
    public void makeNoNumberErrorTest() {
        //given
        String data = "a,1,2";
        // when
        Throwable thrown = catchThrowable(() -> {newTddCalculator.add(data);});
        // then
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("음수 값 전달 시 예외 발생")
    public void makeNegativeNumberErrorTest() {
        //given
        String data = "-1,1,2";
        // when
        Throwable thrown = catchThrowable(() -> {newTddCalculator.add(data);});
        // then
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }
}
