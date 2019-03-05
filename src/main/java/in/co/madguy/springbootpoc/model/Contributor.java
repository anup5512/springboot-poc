package in.co.madguy.springbootpoc.model;

import lombok.Data;

@Data
public class Contributor {
    private String login;
    private String id;
    private String html_url;
    private String repos_url;
    private int contributions;
}
