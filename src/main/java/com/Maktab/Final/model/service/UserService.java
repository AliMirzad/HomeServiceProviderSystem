package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.exception.LogicErrorException;
import com.Maktab.Final.model.repository.UserRepository;
import com.Maktab.Final.model.service.serviceInterface.UserServiceInterface;
import com.Maktab.Final.model.util.UserGridSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface {
    private final UserGridSearch userGridSearch;
    private final UserRepository userRepository;

    public UserService(UserGridSearch userGridSearch, UserRepository userRepository) {
        this.userGridSearch = userGridSearch;
        this.userRepository = userRepository;
    }


    @Override
    public User findById(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) throw new LogicErrorException("user not found");
        return user;
    }

    @Override
    public User login(String nationalCode, String password) {
        User users = userRepository.findByNationalCodeAndPassword(nationalCode, password);
        if (users == null) throw new LogicErrorException("username or password is wrong");
        return users;
    }

    @Override
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).get();
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public List<User> gridSearch(Integer userId, String email, String firstName, String lastName, String type) {
        Specification<User> specification = userGridSearch.gridSearch(userId, email, firstName, lastName, type);
        return userRepository.findAll(specification);
    }
}
