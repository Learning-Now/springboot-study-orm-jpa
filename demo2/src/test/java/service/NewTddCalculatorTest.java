package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class NewTddCalculatorTest {

    public static NewTddCalculator newTddCalculator;


    @BeforeEach
    void setUp() {
        newTddCalculator = new NewTddCalculator();
    }

    @Test
    public void emptyInputTest(){
        // given
        String input = "";
        // when
        int result = (int) newTddCalculator.add(input);
        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void singleInputTest(){
        // given
        String input = "3";
        // when
        int result = newTddCalculator.add(input);
        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void singleInputTest2(){
        // given
        String input = "5";
        // when
        int result = newTddCalculator.add(input);
        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void doubleInputAddByCommaTest(){
        // given
        final String input = "1,2";
        // when
        final int result = newTddCalculator.add(input);
        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void customInputAddByCommaTest(){
        // given
        final String input = "1;2";
        // when
        final int result =newTddCalculator.add(input);
        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void checkMinusTest(){
        // given
        final String input = "1;2;-1";
        // when
        final int result =newTddCalculator.add(input);
        // then
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void customPatternTest(){
        // given
        final String input = "//@\n1@2@3";
        // when
        final int result =newTddCalculator.add(input);
        // then
        assertThat(result).isEqualTo(6);
    }
}

