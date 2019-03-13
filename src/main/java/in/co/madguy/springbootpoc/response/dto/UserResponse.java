package in.co.madguy.springbootpoc.response.dto;

import in.co.madguy.springbootpoc.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String message;
    private User user;
}
