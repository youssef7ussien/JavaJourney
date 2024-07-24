package com.login.springweb.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class OnlineUserServiceImpl implements OnlineUserService {

    private int onlineUserCount = 0;

    @Override
    public void userLoggedIn() {
        onlineUserCount++;
    }

    @Override
    public void userLoggedOut() {
        if (onlineUserCount > 0)
            onlineUserCount--;
    }

    @Override
    public int currentOnlineUsers() {
        return onlineUserCount;
    }

}
