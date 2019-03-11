package in.co.madguy.springbootpoc.controller;

import in.co.madguy.springbootpoc.exception.EntityNotFoundException;
import in.co.madguy.springbootpoc.model.User;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.request.dto.UpdateUserRequest;
import in.co.madguy.springbootpoc.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(description = "Operations for User entity")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
        value = "/users",
        method = RequestMethod.GET
    )
    public List<Resource<User>> getUsers() throws EntityNotFoundException {
        List<User> users = this.userService.getAllUsers();
        List<Resource<User>> resources = new ArrayList<>();
        for (User user : users) {
            Resource<User> resource = new Resource<>(user);
            ControllerLinkBuilder linkToSelf = linkTo(methodOn(this.getClass()).getUser(user.getId()));
            resource.add(linkToSelf.withRel("self"));
            resources.add(resource);
        }
        return resources;
    }

    @RequestMapping(
        value = "/users/{id}",
        method = RequestMethod.GET
    )
    public Resource<User> getUser(@PathVariable String id) throws EntityNotFoundException {
        Optional<User> user = Optional.ofNullable(this.userService.getUser(id));
        Resource<User> resource = new Resource<>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @RequestMapping(
        value = "/users",
        method = RequestMethod.POST
    )
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = this.userService.addUser(User.from(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(user.getId())
            .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(
        value = "/users/{id}",
        method = RequestMethod.PUT
    )
    public ResponseEntity<User> updateUser(@Valid @RequestBody UpdateUserRequest request, @PathVariable String id) throws EntityNotFoundException {
        Optional<User> user = Optional.ofNullable(this.userService.getUser(id));
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        this.userService.addUser(User.from(request, id));
        return ResponseEntity.noContent().build();
    }
}
