package in.co.madguy.springbootpoc.response.dto;

import in.co.madguy.springbootpoc.response.enums.ResponseStatus;
import lombok.Data;

/**
 * JSend compliant response
 *
 * @author z002tbx
 * @see <a href="https://github.com/anup5512/jsend">JSend Repository</a>
 * @param <T>
 */
@Data
public class Response<T> {
    private final String status;
    private final T data;
    private final String message;

    public static <T> Response frame(ResponseStatus status, T data, String message) {
        return new Response(status.getCode(), data, message);
    }
}
