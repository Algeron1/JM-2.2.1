package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User getUserBySeries(int series);

    User getUserByCarModel(String carmodel);
}