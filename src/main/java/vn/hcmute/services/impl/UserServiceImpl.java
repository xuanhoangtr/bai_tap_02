package vn.hcmute.services.impl;

import vn.hcmute.dao.IUserDao;
import vn.hcmute.dao.impl.UserDaoImpl;
import vn.hcmute.models.UserModel;
import vn.hcmute.services.IUserService;

public class UserServiceImpl implements IUserService {

    private final IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel get(String username) {
        return userDao.get(username);
    }

    @Override
    public UserModel login(String username, String password) {
        return userDao.login(username, password);
    }
}
