package subway.constant;

public enum ErrorMessage {

    FORMAT_ERROR("잘못된 형식입니다."),

    NO_EXIST_STATION("존재하지 않는 역입니다."),
    SAME_START_END_STATION("출발역과 도착역이 동일합니다."),
    NO_CONNECTED_STATIONS("출발역과 도착역이 연결되어있지 않습니다."),
    ;

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(Object... args) {
        return ERROR_MESSAGE_PREFIX + String.format(errorMessage, args);
    }
}
