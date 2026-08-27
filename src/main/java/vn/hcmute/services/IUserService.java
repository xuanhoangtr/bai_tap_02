package vn.hcmute.services;

import vn.hcmute.models.UserModel;

public interface IUserService {
    UserModel get(String username);
    UserModel login(String username, String password);
}
