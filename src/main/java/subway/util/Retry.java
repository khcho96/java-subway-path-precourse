package subway.util;

import java.util.function.Supplier;
import subway.view.OutputView;

public final class Retry {

    private Retry() {}

    public static <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}
