package in.co.madguy.springbootpoc.controller;

import in.co.madguy.springbootpoc.response.dto.GithubContributor;
import in.co.madguy.springbootpoc.response.dto.GithubRepo;
import in.co.madguy.springbootpoc.response.dto.Response;
import in.co.madguy.springbootpoc.response.enums.ResponseStatus;
import in.co.madguy.springbootpoc.service.impl.GithubServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class GithubController {
    private final GithubServiceImpl githubService;

    @Autowired
    public GithubController(GithubServiceImpl githubService) {
        this.githubService = githubService;
    }

    @RequestMapping(
        value = "/github/repos/{username}",
        method = RequestMethod.GET
    )
    @ApiOperation(
        value = "Fetches public Github repos of a user",
        response = GithubRepo.class,
        responseContainer = "List"
    )
    public ResponseEntity<Response> repos(@PathVariable("username") String username) {
        try {
            List<GithubRepo> repos = this.githubService.getGithubRepos(username);
            return new ResponseEntity(Response.frame(ResponseStatus.SUCCESS, repos, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(Response.frame(ResponseStatus.FAIL, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
        value = "/github/contributors/{owner}/{repo}",
        method = RequestMethod.GET
    )
    @ApiOperation(
        value = "Fetches Github repo contributor details",
        response = GithubContributor.class,
        responseContainer = "List"
    )
    public ResponseEntity<Response> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        try {
            List<GithubContributor> contributors = this.githubService.getGithubContributors(owner, repo);
            return new ResponseEntity(Response.frame(ResponseStatus.SUCCESS, contributors, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(Response.frame(ResponseStatus.FAIL, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
