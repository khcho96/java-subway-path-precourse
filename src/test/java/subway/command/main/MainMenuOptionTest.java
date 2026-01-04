package subway.command.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import subway.constant.ErrorMessage;

class MainMenuOptionTest {

    @Test
    void 기능_선택_오류() {
        assertThatThrownBy(() -> MainMenuOption.from("3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.FORMAT_ERROR.getErrorMessage());
    }

    @Test
    void 기능_선택_정상() {
        MainMenuOption option = MainMenuOption.from("1");
        assertThat(option).isEqualTo(MainMenuOption.QUERY);
    }
}
