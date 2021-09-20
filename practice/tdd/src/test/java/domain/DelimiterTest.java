package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DelimiterTest {

    @DisplayName("구분자 생성 테스트")
    @Test
    void createDelimiter() {
        //given
        Delimiter delimiter = new DefaultDelimiter();
        //when
        //then
        assertThat(delimiter).isEqualTo(new DefaultDelimiter());
    }

    @DisplayName("구분자 생성 테스트")
    @Test
    void createCustomDelimiter() {
        //given
        Delimiter delimiter = new CustomDelimiter("//!\n");
        //when
        //then
        assertThat(delimiter).isEqualTo(new CustomDelimiter("//!\n"));
    }

    @DisplayName("split 테스트")
    @Test
    void splitTest() {
        //given
        Delimiter delimiter = new DefaultDelimiter();
        String input = "1;2;3;4";
        //when
        delimiter.split(input);
        //then
    }
}