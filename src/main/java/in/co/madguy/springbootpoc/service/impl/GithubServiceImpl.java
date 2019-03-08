package in.co.madguy.springbootpoc.service.impl;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import in.co.madguy.springbootpoc.response.model.GithubContributor;
import in.co.madguy.springbootpoc.response.model.GithubRepo;
import in.co.madguy.springbootpoc.service.GithubClient;
import in.co.madguy.springbootpoc.service.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GithubServiceImpl implements GithubService {
    private final GithubClient githubClient;

    public GithubServiceImpl(@Value("${github.url}") String githubUrl) {
        this.githubClient = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(GithubClient.class, githubUrl);
    }

    public List<GithubRepo> getGithubRepos(String username) {
        return githubClient.repos(username);
    }

    public List<GithubContributor> getGithubContributors(String owner, String repo) {
        return githubClient.contributors(owner, repo);
    }
}
