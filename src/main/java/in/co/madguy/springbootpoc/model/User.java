package in.co.madguy.springbootpoc.model;

import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.request.dto.UpdateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String name;
    private Integer age;

    public static User from(CreateUserRequest request) {
        return User.builder()
            .name(request.getName())
            .age(request.getAge())
            .build();
    }

    public static User from(UpdateUserRequest request, String id) {
        return User.builder()
            .id(id)
            .name(request.getName())
            .age(request.getAge())
            .build();
    }
}
