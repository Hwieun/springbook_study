package com.springbook.TobySpring.one.six.user;

import com.springbook.TobySpring.one.five.MailSender;
import com.springbook.TobySpring.one.five.UserDao;
import com.springbook.TobySpring.one.one.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    MailSender mailSender;

    @Override
    public void add(User user) {

    }

    @Override
    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        }
    }

    private void upgradeLevel(User user) {

    }

    private boolean canUpgradeLevel(User user) {
        return false;
    }
}
