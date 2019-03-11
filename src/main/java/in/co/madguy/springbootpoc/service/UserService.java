package in.co.madguy.springbootpoc.service;

import in.co.madguy.springbootpoc.exception.EntityNotFoundException;
import in.co.madguy.springbootpoc.exception.ServiceException;
import in.co.madguy.springbootpoc.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(String id) throws EntityNotFoundException;
    User addUser(User user) throws ServiceException;
    User modifyUser(User user) throws EntityNotFoundException;
    void deleteUser(String id) throws EntityNotFoundException;
}
