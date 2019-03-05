package in.co.madguy.springbootpoc.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import in.co.madguy.springbootpoc.model.Contributor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GithubService {
    private final String githubUrl;

    public GithubService(@Value("${github.url}") String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public List<Contributor> getGithubContributors(String owner, String repo) {
        IGithub github = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(IGithub.class, githubUrl);

        List<Contributor> contributors = github.contributors(owner, repo);
        return contributors;
    }
}
