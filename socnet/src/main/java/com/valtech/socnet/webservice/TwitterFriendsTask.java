package com.valtech.socnet.webservice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.valtech.socnet.listener.CallBack;
import com.valtech.socnet.model.SocialUser;

import java.util.ArrayList;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;


/**
 * Created by ankit.thakur on 08-02-2016.
 */
public class TwitterFriendsTask extends AsyncTask<String, Void, ArrayList<SocialUser>> {


    private CallBack.OnFriendsList mOnFriendsList;

    Integer error;
    Twitter twitter;
Context context;
    public TwitterFriendsTask(Twitter twitter, CallBack.OnFriendsList delegates, Context context) {
this.context=context;
        this.twitter = twitter;
        this.mOnFriendsList = delegates;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected ArrayList<SocialUser> doInBackground(String... keys) {
        ArrayList<SocialUser> socialUsers = new ArrayList<>();
        try {
            long followerCursor = -1;
            IDs followerIds;
            // get followers
            do {
                followerIds = twitter.getFollowersIDs(
                        twitter.getId(), followerCursor);

             //   Log.i("twitter no of id ",followerIds.getIDs().length+"");
                if(followerIds.getIDs().length >0) {
                    ResponseList<User> followers = twitter.lookupUsers(followerIds.getIDs());
                    for (User follower : followers) {
                        SocialUser user = new SocialUser();
                        user.setUserId(follower.getId() + "");
                        user.setScreeName(follower.getScreenName());
                        user.setProfileImage(follower.getProfileImageURL());
                        user.setCoverImage(follower.getProfileBannerMobileURL());
                        user.setUsername(follower.getName());
                        socialUsers.add(user);

                    }
                }
            } while ((followerCursor = followerIds.getNextCursor()) != 0);

        } catch (TwitterException e) {
            socialUsers=null;
            
            Log.d("Twitter Exception", e.getMessage());
            if (e.getErrorCode() == 135) {
                error = 135;
            }

        }
        return socialUsers;
    }

    @Override
    protected void onPostExecute(ArrayList<SocialUser> friends) {
        super.onPostExecute(friends);

        if (mOnFriendsList != null) {
            if (error!=null&&error == 135) {

                Toast.makeText(context, "Please update system date", Toast.LENGTH_SHORT).show();
                mOnFriendsList.getList(null, false);


            }else {
                mOnFriendsList.getList(friends, false);
            }
        }
    }
}
