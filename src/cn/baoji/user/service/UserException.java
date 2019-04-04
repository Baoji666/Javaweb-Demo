package cn.baoji.user.service;

public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }

    public UserException() {
        super();
    }
}
