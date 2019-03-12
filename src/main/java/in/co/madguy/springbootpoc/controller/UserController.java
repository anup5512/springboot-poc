package in.co.madguy.springbootpoc.controller;

import in.co.madguy.springbootpoc.exception.EntityNotFoundException;
import in.co.madguy.springbootpoc.exception.ServiceException;
import in.co.madguy.springbootpoc.mapper.UserMapper;
import in.co.madguy.springbootpoc.model.User;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.request.dto.UpdateUserRequest;
import in.co.madguy.springbootpoc.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static in.co.madguy.springbootpoc.util.Constants.ALL_REL;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(description = "Operations for User entity")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
        value = "/users",
        method = RequestMethod.GET
    )
    public Resources<Resource<User>> getUsers() {
        List<User> users = this.userService.getAllUsers();
        List<Resource<User>> userResources = users.stream()
            .map(user -> {
                try {
                    Link selfRel = linkTo(methodOn(UserController.class).getUser(user.getUserId())).withSelfRel();
                    return new Resource<>(user, selfRel);
                } catch (EntityNotFoundException e) {
                    // Do nothing
                }
                return null;
            }).collect(Collectors.toList());

        Link selfRel = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();
        return new Resources<>(userResources, selfRel);
    }

    @RequestMapping(
        value = "/users/{id}",
        method = RequestMethod.GET
    )
    public Resource<User> getUser(@PathVariable String id) throws EntityNotFoundException {
        User user = this.userService.getUser(id);
        Link selfRel = linkTo(methodOn(UserController.class).getUser(id)).withSelfRel();
        Resource<User> userResource = new Resource<>(user, selfRel);

        Link allRel = linkTo(methodOn(UserController.class).getUsers()).withRel(ALL_REL);
        userResource.add(allRel);
        return userResource;
    }

    @RequestMapping(
        value = "/users",
        method = RequestMethod.POST
    )
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) throws ServiceException {
        User user = this.userService.addUser(UserMapper.from(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userId}")
            .buildAndExpand(user.getUserId())
            .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(
        value = "/users/{id}",
        method = RequestMethod.PUT
    )
    public ResponseEntity<User> updateUser(@Valid @RequestBody UpdateUserRequest request, @PathVariable String id) throws EntityNotFoundException {
        this.userService.modifyUser(UserMapper.from(request, id));
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(
        value = "/users/{id}",
        method = RequestMethod.DELETE
    )
    public ResponseEntity<User> deleteUser(@PathVariable String id) throws EntityNotFoundException {
        this.userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }
}
