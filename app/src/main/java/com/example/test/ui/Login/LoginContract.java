package com.example.test.ui.Login;

public interface LoginContract {
    interface view{
        void loginSent();
        void loginError();
        void loginSuccess();
        void loginApiError();
        void findById();
    }
    interface presenter{
        void LoginSent(String password,String account);
    }
}
