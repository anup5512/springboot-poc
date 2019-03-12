package in.co.madguy.springbootpoc.service.impl;

import in.co.madguy.springbootpoc.exception.EntityNotFoundException;
import in.co.madguy.springbootpoc.exception.ServiceException;
import in.co.madguy.springbootpoc.model.User;
import in.co.madguy.springbootpoc.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyUserServiceImpl implements UserService {
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(String id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public User addUser(User user) throws ServiceException {
        return null;
    }

    @Override
    public User modifyUser(User user) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void deleteUser(String id) throws EntityNotFoundException {

    }
}
