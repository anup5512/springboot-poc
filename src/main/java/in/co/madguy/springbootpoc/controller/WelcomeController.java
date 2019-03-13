package in.co.madguy.springbootpoc.controller;

import in.co.madguy.springbootpoc.response.dto.Response;
import in.co.madguy.springbootpoc.response.enums.ResponseStatus;
import in.co.madguy.springbootpoc.service.impl.WelcomeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Operations for proof of concept")
public class WelcomeController {
    private final WelcomeServiceImpl welcomeService;

    @Autowired
    public WelcomeController(WelcomeServiceImpl welcomeService) {
        this.welcomeService = welcomeService;
    }

    @RequestMapping(
        value = "/greet",
        method = RequestMethod.GET
    )
    @ApiOperation(value = "Greets user", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Hello! User...")
    })
    public ResponseEntity<Response> greet() {
        try {
            String welcomeMessage = welcomeService.greet(null);
            return new ResponseEntity(Response.frame(ResponseStatus.SUCCESS, welcomeMessage, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(Response.frame(ResponseStatus.FAIL, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
        value = "/greet2",
        method = RequestMethod.GET
    )
    @ApiOperation(value = "Greets user", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Hello! User...")
    })
    public String greet2() {
        return welcomeService.greet(null);
    }

    @RequestMapping(
        value = "/greet/{name}",
        method = RequestMethod.GET
    )
    @ApiOperation(value = "Greets user by name", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Hello! &lt;Username&gt;...")
    })
    public ResponseEntity<Response> greet(@PathVariable String name) {
        try {
            String welcomeMessage = welcomeService.greet(name);
            return new ResponseEntity(Response.frame(ResponseStatus.SUCCESS, welcomeMessage, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(Response.frame(ResponseStatus.FAIL, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
