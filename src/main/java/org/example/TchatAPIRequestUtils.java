package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.beans.MessageBean;

import java.util.ArrayList;
import java.util.Collections;


public class TchatAPIRequestUtils {

    public static void main(String[] args) throws Exception {
        sendMessage(new MessageBean("Toto", "Coucou"));

        System.out.println(getAllMessage());
    }

    public static void sendMessage(MessageBean message) throws Exception {
        //controle
        String jsonToSend = new Gson().toJson(message);
        RequestUtils.sendPost(MyAPIRequestUtils.URL_MY_API + "/tchat/saveMessage", jsonToSend);
    }

    public static ArrayList<MessageBean> getAllMessage() throws Exception {
        String jsonReceive = RequestUtils.sendGet(MyAPIRequestUtils.URL_MY_API + "/tchat/allMessages");
        return  new Gson().fromJson(jsonReceive, new TypeToken<ArrayList<MessageBean>>(){}.getType());
    }
}
