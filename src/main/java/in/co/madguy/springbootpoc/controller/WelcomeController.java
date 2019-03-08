package in.co.madguy.springbootpoc.controller;

import in.co.madguy.springbootpoc.service.impl.WelcomeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
@Api(description = "Operations for proof of concept")
public class WelcomeController {
    private final WelcomeServiceImpl welcomeService;

    @Autowired
    public WelcomeController(WelcomeServiceImpl welcomeService) {
        this.welcomeService = welcomeService;
    }

    @RequestMapping(
        value = "/",
        method = RequestMethod.GET
    )
    @ApiOperation(value = "Greets user", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Hello! User...")
    })
    public String greet() {
        return welcomeService.greet(null);
    }

    @RequestMapping(
        value = "/{name}",
        method = RequestMethod.GET
    )
    @ApiOperation(value = "Greets user by name", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Hello! &lt;Username&gt;...")
    })
    public String greet(@PathVariable String name) {
        return welcomeService.greet(name);
    }
}
