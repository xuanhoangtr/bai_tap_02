package vn.hcmute.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.hcmute.dao.DBConnection;
import vn.hcmute.dao.IUserDao;
import vn.hcmute.models.UserModel;

public class UserDaoImpl extends DBConnection implements IUserDao {

    private static final List<UserModel> memoryUsers = new ArrayList<>();

    static {
        // Tai khoan mac dinh: xuan / 123
        memoryUsers.add(new UserModel(1, "xuanhoang@hcmute.edu.vn", "xuan", "Tran Xuan Hoang", "123"));
    }

    @Override
    public UserModel get(String username) {
        Connection con = super.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM [User] WHERE username = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        UserModel user = new UserModel();
                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        user.setUserName(rs.getString("username"));
                        user.setFullName(rs.getString("fullname"));
                        user.setPassWord(rs.getString("password"));
                        return user;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

        for (UserModel u : memoryUsers) {
            if (u.getUserName().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.get(username);
        if (user != null && password != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }
}
