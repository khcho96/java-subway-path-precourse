package subway.command.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import subway.constant.ErrorMessage;

class QueryMenuOptionTest {

    @Test
    void 기능_선택_오류() {
        assertThatThrownBy(() -> QueryMenuOption.from("3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.FORMAT_ERROR.getErrorMessage());
    }

    @Test
    void 기능_선택_정상() {
        QueryMenuOption option = QueryMenuOption.from("1");
        assertThat(option).isEqualTo(QueryMenuOption.DISTANCE);
    }
}