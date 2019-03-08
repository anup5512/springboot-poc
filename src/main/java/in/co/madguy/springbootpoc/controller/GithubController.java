package in.co.madguy.springbootpoc.controller;

import in.co.madguy.springbootpoc.response.model.GithubContributor;
import in.co.madguy.springbootpoc.response.model.GithubRepo;
import in.co.madguy.springbootpoc.service.impl.GithubServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/github")
public class GithubController {
    private final GithubServiceImpl githubService;

    @Autowired
    public GithubController(GithubServiceImpl githubService) {
        this.githubService = githubService;
    }

    @RequestMapping(
        value = "/repos/{username}",
        method = RequestMethod.GET
    )
    @ApiOperation(
        value = "Fetches public Github repos of a user",
        response = GithubRepo.class,
        responseContainer = "List"
    )
    public List<GithubRepo> repos(@PathVariable("username") String username) {
        return this.githubService.getGithubRepos(username);
    }

    @RequestMapping(
        value = "/contributors/{owner}/{repo}",
        method = RequestMethod.GET
    )
    @ApiOperation(
        value = "Fetches Github repo contributor details",
        response = GithubContributor.class,
        responseContainer = "List"
    )
    public List<GithubContributor> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        return this.githubService.getGithubContributors(owner, repo);
    }
}
