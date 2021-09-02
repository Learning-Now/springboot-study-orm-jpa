package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;


class DelimiterTest {

    @DisplayName("구분자 생성 테스트")
    @Test
    void createDelimiter() {
        //given
        Delimiter delimiter = new Delimiter(";");
        //when
        //then
        assertThat(delimiter).isEqualTo(new Delimiter(";"));
    }

    @DisplayName("split 테스트")
    @Test
    void splitTest() {
        //given
        Delimiter delimiter = new Delimiter(";");
        String input = "1;2;3;4";
        //when
        Numbers numbers = delimiter.split(input);
        //then
        assertThat(numbers.count()).isEqualTo(4);
    }
}