package in.co.madguy.springbootpoc.service.feign;

import feign.Param;
import feign.RequestLine;
import in.co.madguy.springbootpoc.response.dto.GithubContributor;
import in.co.madguy.springbootpoc.response.dto.GithubRepo;

import java.util.List;

public interface GithubClient {
    @RequestLine("GET /users/{username}/repos")
    List<GithubRepo> repos(@Param("username") String username);

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<GithubContributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
}
