package in.co.madguy.springbootpoc.service.impl;

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
        this.githubClient = GithubClient.init(githubUrl);
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
