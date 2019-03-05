package in.co.madguy.springbootpoc.service;

import feign.Param;
import feign.RequestLine;
import in.co.madguy.springbootpoc.model.Contributor;

import java.util.List;

public interface IGithub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
}
