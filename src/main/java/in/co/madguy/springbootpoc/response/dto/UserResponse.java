package in.co.madguy.springbootpoc.response.dto;

import in.co.madguy.springbootpoc.model.User;
import lombok.Data;

@Data
public class UserResponse {
    private String message;
    private User user;
}
