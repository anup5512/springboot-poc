package in.co.madguy.springbootpoc.service.impl;

import com.google.common.base.Preconditions;
import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import in.co.madguy.springbootpoc.cache.Cache;
import in.co.madguy.springbootpoc.exception.EntityNotFoundException;
import in.co.madguy.springbootpoc.exception.ServiceException;
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
    private final Cache<User> userCache;
    private final UserClient userClient;

    public UserServiceImpl(@Value("${api.user.url}") String url,
                           Cache<User> userCache) {
        Preconditions.checkNotNull(url, "User api url is not configured");
        this.userCache = userCache;
        this.userClient = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(UserClient.class, url);
    }

    @Override
    public List<User> getAllUsers() {
        return userCache.get();
    }

    @Override
    public User getUser(String id) throws EntityNotFoundException {
        User user = userCache.get(id);
        if (null == user) {
            throw new EntityNotFoundException(User.class, "userId", id);
        }
        return user;
    }

    @Override
    public User addUser(User user) throws ServiceException {
        try {
            UserResponse response = this.userClient.create(CreateUserRequest.from(user));
            User savedUser = response.getUser();
            userCache.add(savedUser);
            return savedUser;
        } catch (FeignException e) {
            throw new ServiceException(UserService.class, "status", String.valueOf(e.status()), "message", e.getMessage());
        }
    }

    @Override
    public User modifyUser(User user) throws EntityNotFoundException {
        User existingUser = this.getUser(user.getUserId());

        // UpdateUserRequest request = UpdateUserRequest.from(user);
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        userCache.add(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(String id) throws EntityNotFoundException {
        User existingUser = this.getUser(id);
        userCache.remove(existingUser);
    }
}
