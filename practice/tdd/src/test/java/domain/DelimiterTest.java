package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


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

}