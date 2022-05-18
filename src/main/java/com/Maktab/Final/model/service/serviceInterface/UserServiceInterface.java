package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.baseEntity.User;

import java.util.List;

public interface UserServiceInterface {
    public User login(String email, String password);

    public void changePassword(Integer userId, String oldPassword, String newPassword);

    public List<User> gridSearch(Integer userId, String email, String firstName, String lastName);
}
