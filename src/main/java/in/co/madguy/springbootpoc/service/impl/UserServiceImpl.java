package in.co.madguy.springbootpoc.service.impl;

import com.google.common.base.Preconditions;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import in.co.madguy.springbootpoc.cache.UserCache;
import in.co.madguy.springbootpoc.exception.EntityNotFoundException;
import in.co.madguy.springbootpoc.model.User;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.response.dto.UserResponse;
import in.co.madguy.springbootpoc.service.UserService;
import in.co.madguy.springbootpoc.service.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserCache userCache;
    private final UserClient userClient;

    public UserServiceImpl(@Value("${api.user.url}") String url,
                           UserCache userCache) {
        Preconditions.checkNotNull(url, "User api url is not configured");
        this.userCache = userCache;
        this.userClient = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(UserClient.class, url);
    }

    @Override
    public User getUser(String id) throws EntityNotFoundException {
        User user = userCache.get(id);
        if (null == user) {
            throw new EntityNotFoundException(User.class, "id", id);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws EntityNotFoundException {
        return userCache.get();
    }

    @Override
    public User addUser(User user) {
        UserResponse response = this.userClient.create(CreateUserRequest.from(user));
        User savedUser = response.getUser();
        userCache.add(savedUser);
        return savedUser;
    }

    @Override
    public User modifyUser(User user) throws  EntityNotFoundException {
        User existingUser = userCache.get(user.getId());
        if (null == existingUser) {
            throw new EntityNotFoundException(User.class, "id", user.getId());
        }

        User updatedUser = existingUser.builder()
            .name(user.getName())
            .age(user.getAge())
            .build();
        userCache.add(updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(User user) throws EntityNotFoundException {
        User existingUser = userCache.get(user.getId());
        if (null == existingUser) {
            throw new EntityNotFoundException(User.class, "id", user.getId());
        }
        userCache.remove(existingUser);
    }
}
