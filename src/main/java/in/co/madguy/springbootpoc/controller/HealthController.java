package in.co.madguy.springbootpoc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(description = "Operations for health check")
public class HealthController {
    @RequestMapping(
        value = "/",
        method = RequestMethod.GET
    )
    @ApiOperation(value = "Checks API health", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "API is up and running..."),
        @ApiResponse(code = 503, message = "Service unavailable")
    })
    public String root() {
        return "API is up and running...";
    }
}
