package in.co.madguy.springbootpoc.service.impl;

import in.co.madguy.springbootpoc.service.WelcomeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class WelcomeServiceImpl implements WelcomeService {

    public String greet(String name) {
        return MessageFormat.format("Hello! {0}...", StringUtils.isBlank(name) ? "User" : name);
    }
}
