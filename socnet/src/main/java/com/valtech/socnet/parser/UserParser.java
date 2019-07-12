package com.valtech.socnet.parser;


import com.valtech.socnet.model.SocialUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit.thakur on 23-02-2016.
 */
public class UserParser {

     public static SocialUser parseFBUserInfo(JSONObject object){

         SocialUser user=new SocialUser();
         try {
             if (object.has("picture")) {

                 JSONObject data = object.getJSONObject("picture");
                 JSONObject url = data.getJSONObject("data");
                 user.setProfileImage(url.getString("url"));
             }

             if (object.has("cover")) {
                 JSONObject source = object.getJSONObject("cover");
                 user.setCoverImage(source.getString("source"));

             }
             if (object.has("email")) {
                 user.setEmailId(object.getString("email"));
             }
             if (object.has("name")) {
                 user.setUsername(object.getString("name"));
             }
             if (object.has("id")) {
                 user.setUserId(object.getString("id"));
             }


         } catch (JSONException e) {
             e.printStackTrace();
         }

         return  user;
     }

    public static List<SocialUser> getFriendsList(JSONObject response){
        List<SocialUser> friends=new ArrayList<>();
        if(response != null) {
            try {
                JSONArray resp = response.getJSONArray("data");


                for (int i = 0; i < resp.length(); i++) {
                    SocialUser user = new SocialUser();
                    JSONObject object = resp.getJSONObject(i);
                    if (object.has("picture")) {

                        JSONObject data = object.getJSONObject("picture");
                        JSONObject url = data.getJSONObject("data");
                        user.setProfileImage(url.getString("url"));
                    }


                    if (object.has("id")) {
                        user.setUserId(object.getString("id"));
                    }
                    if (object.has("name")) {
                        user.setUsername(object.getString("name"));
                    }
                    friends.add(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return friends;

    }
}
