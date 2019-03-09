package in.co.madguy.springbootpoc.service.impl;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import in.co.madguy.springbootpoc.response.dto.GithubContributor;
import in.co.madguy.springbootpoc.response.dto.GithubRepo;
import in.co.madguy.springbootpoc.service.GithubService;
import in.co.madguy.springbootpoc.service.feign.GithubClient;
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

    @Override
    public List<GithubRepo> getGithubRepos(String username) {
        return githubClient.repos(username);
    }

    @Override
    public List<GithubContributor> getGithubContributors(String owner, String repo) {
        return githubClient.contributors(owner, repo);
    }
}
