package org.example;

import com.google.gson.Gson;
import org.example.beans.MessageBean;


public class TchatAPIRequestUtils {

    public static void main(String[] args) throws Exception {
        sendMessage(new MessageBean("Toto", "Coucou"));
    }

    public static void sendMessage(MessageBean message) throws Exception {
        //controle
        String jsonToSend = new Gson().toJson(message);
        RequestUtils.sendPost(MyAPIRequestUtils.URL_MY_API + "/tchat/saveMessage", jsonToSend);

    }

//    public static Arraylist<MessageBean> getAllMessage() {
//
//    }
}
