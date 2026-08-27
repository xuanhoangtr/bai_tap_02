package vn.hcmute.dao;

import vn.hcmute.models.UserModel;

public interface IUserDao {
    UserModel get(String username);
    UserModel login(String username, String password);
}
