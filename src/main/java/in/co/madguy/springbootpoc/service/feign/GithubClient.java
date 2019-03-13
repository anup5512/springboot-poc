package in.co.madguy.springbootpoc.service.feign;

import feign.Param;
import feign.RequestLine;
import feign.RetryableException;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import in.co.madguy.springbootpoc.response.dto.GithubContributor;
import in.co.madguy.springbootpoc.response.dto.GithubRepo;

import java.util.List;

public interface GithubClient {
    @RequestLine("GET /users/{username}/repos")
    List<GithubRepo> repos(@Param("username") String username);

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<GithubContributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    static GithubClient init(String url) {
        return HystrixFeign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .retryer(new Retryer() {
                @Override
                public void continueOrPropagate(RetryableException e) {

                }

                @Override
                public Retryer clone() {
                    return null;
                }
            })
            .target(GithubClient.class, url);
    }
}
