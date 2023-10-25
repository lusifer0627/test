package com.example.test.ui.Main;

public interface MainContract {
    interface view{
        void username();
        void usernameReturn(String name);
        void getToken();
        void startPage();
        void findById();
    }
    interface presenter{
        void UserName(String token);
    }
}
