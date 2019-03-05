package in.co.madguy.springbootpoc.model;

import lombok.Data;

@Data
public class GithubRepo {
    private String id;
    private String name;
    private String description;
    private boolean fork;
}
