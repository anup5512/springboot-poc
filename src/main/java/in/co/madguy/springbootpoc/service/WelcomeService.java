package in.co.madguy.springbootpoc.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class WelcomeService {

    public String greet(String name) {
        return MessageFormat.format("Hello! {0}...", StringUtils.isBlank(name) ? "User" : name);
    }
}
