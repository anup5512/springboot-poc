package in.co.madguy.springbootpoc.request.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public abstract class UserRequest {
    @NotNull
    private String name;
    @NotNull
    private Integer age;
}
