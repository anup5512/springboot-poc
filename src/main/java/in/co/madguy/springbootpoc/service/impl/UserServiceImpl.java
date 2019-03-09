package in.co.madguy.springbootpoc.service.impl;

import com.google.common.base.Preconditions;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import in.co.madguy.springbootpoc.cache.UserCache;
import in.co.madguy.springbootpoc.model.User;
import in.co.madguy.springbootpoc.request.dto.CreateUserRequest;
import in.co.madguy.springbootpoc.response.dto.UserResponse;
import in.co.madguy.springbootpoc.service.UserService;
import in.co.madguy.springbootpoc.service.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public User getUser(String id) {
        return userCache.get(id);
    }

    @Override
    public List<User> getAllUsers() {
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
    public User modifyUser(User user) {
        Optional<User> existingUserOpt = Optional.ofNullable(userCache.get(user.getId()));
        if (existingUserOpt.isPresent()) {
            User updatedUser = existingUserOpt.get().builder()
                .name(user.getName())
                .age(user.getAge())
                .build();
            userCache.add(updatedUser);
            return updatedUser;
        }
        throw new RuntimeException("User not found; id- " + user.getId());
    }

    @Override
    public void deleteUser(User user) {
        Optional<User> existingUserOpt = Optional.ofNullable(userCache.get(user.getId()));
        if (existingUserOpt.isPresent()) {
            userCache.remove(existingUserOpt.get());
            return;
        }
        throw new RuntimeException("User not found; id- " + user.getId());
    }
}
