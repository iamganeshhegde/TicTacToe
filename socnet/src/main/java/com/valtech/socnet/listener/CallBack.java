package com.valtech.socnet.listener;

import com.valtech.socnet.model.SocialUser;

import java.util.List;

import twitter4j.auth.RequestToken;

/**
 * Created by ankit.thakur on 03-03-2016.
 */
public interface CallBack {
    public interface OnFriendsList {
        public void getList(List<SocialUser> friends, boolean hasNext);
    }

    public interface OnLogin {
        public void getUserInfo(SocialUser user);
    }

    public interface OnShare {
        public void onComplete(String response);
    }

    public interface OnUrl {
        public void getURL(String url, RequestToken requestToken, OnLogin success,Integer error);
    }

    public interface OnShareResponse {
        public void onComplete(String response);
    }
}
