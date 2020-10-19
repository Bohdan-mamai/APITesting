package core.order;

import java.util.Objects;

public class NotFoundOrderObject {
    private int code;
    private String type;
    private String message;

    public NotFoundOrderObject(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public NotFoundOrderObject() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotFoundOrderObject that = (NotFoundOrderObject) o;
        return code == that.code &&
                Objects.equals(type, that.type) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, message);
    }
}
