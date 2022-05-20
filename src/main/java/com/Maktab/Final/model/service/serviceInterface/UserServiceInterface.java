package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.baseEntity.User;

import java.util.List;

public interface UserServiceInterface {
    User login(String email, String password);

    void changePassword(Integer userId, String oldPassword, String newPassword);

    List<User> gridSearch(Integer userId, String email, String firstName, String lastName, String type);

    User findById(Integer id);
}
