package in.co.madguy.springbootpoc.controller;

import in.co.madguy.springbootpoc.model.Contributor;
import in.co.madguy.springbootpoc.service.GithubService;
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
    private final GithubService githubService;

    @Autowired
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @RequestMapping(
        value = "/{owner}/{repo}",
        method = RequestMethod.GET
    )
    @ApiOperation(
        value = "Fetches Github repo contributor details",
        response = Contributor.class,
        responseContainer = "List"
    )
    public List<Contributor> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        return this.githubService.getGithubContributors(owner, repo);
    }
}
