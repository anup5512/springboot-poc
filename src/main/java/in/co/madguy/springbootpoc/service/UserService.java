package in.co.madguy.springbootpoc.service;

import in.co.madguy.springbootpoc.exception.EntityNotFoundException;
import in.co.madguy.springbootpoc.model.User;

import java.util.List;

public interface UserService {
    User getUser(String id) throws EntityNotFoundException;
    List<User> getAllUsers() throws EntityNotFoundException;
    User addUser(User user);
    User modifyUser(User user) throws EntityNotFoundException;
    void deleteUser(User user) throws EntityNotFoundException;
}
