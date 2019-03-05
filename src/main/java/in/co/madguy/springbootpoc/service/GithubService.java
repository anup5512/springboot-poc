package in.co.madguy.springbootpoc.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import in.co.madguy.springbootpoc.model.GithubContributor;
import in.co.madguy.springbootpoc.model.GithubRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GithubService {
    private final String githubUrl;
    private final IGithub githubProxy;

    public GithubService(@Value("${github.url}") String githubUrl) {
        this.githubUrl = githubUrl;
        this.githubProxy = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(IGithub.class, githubUrl);
    }

    public List<GithubRepo> getGithubRepos(String username) {
        return githubProxy.repos(username);
    }

    public List<GithubContributor> getGithubContributors(String owner, String repo) {
        return githubProxy.contributors(owner, repo);
    }
}
