package vn.hcmute.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String email;
    private String userName;
    private String fullName;
    private String passWord;

    public UserModel() {
    }

    public UserModel(int id, String email, String userName, String fullName, String passWord) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.fullName = fullName;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
