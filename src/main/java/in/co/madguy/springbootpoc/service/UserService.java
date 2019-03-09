package in.co.madguy.springbootpoc.service;

import in.co.madguy.springbootpoc.model.User;

import java.util.List;

public interface UserService {
    User getUser(String id);
    List<User> getAllUsers();
    User addUser(User user);
    User modifyUser(User user);
    void deleteUser(User user);
}
