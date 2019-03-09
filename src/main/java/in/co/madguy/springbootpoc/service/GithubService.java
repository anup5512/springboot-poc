package in.co.madguy.springbootpoc.service;

import in.co.madguy.springbootpoc.response.dto.GithubContributor;
import in.co.madguy.springbootpoc.response.dto.GithubRepo;

import java.util.List;

public interface GithubService {
    List<GithubRepo> getGithubRepos(String username);
    List<GithubContributor> getGithubContributors(String owner, String repo);
}
