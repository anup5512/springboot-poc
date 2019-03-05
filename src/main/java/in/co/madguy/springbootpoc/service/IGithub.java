package in.co.madguy.springbootpoc.service;

import feign.Param;
import feign.RequestLine;
import in.co.madguy.springbootpoc.model.GithubContributor;
import in.co.madguy.springbootpoc.model.GithubRepo;

import java.util.List;

public interface IGithub {
    @RequestLine("GET /users/{username}/repos")
    List<GithubRepo> repos(@Param("username") String username);

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<GithubContributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
}
