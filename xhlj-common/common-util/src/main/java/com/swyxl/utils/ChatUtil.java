package com.swyxl.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.swyxl.model.constant.ChatConstant;
import com.swyxl.model.entity.chat.Message;
import com.swyxl.model.entity.chat.RequestMessage;
import com.swyxl.model.entity.chat.ResponseMessage;
import com.swyxl.model.vo.chat.ChatVo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ChatUtil {

    private static OkHttpClient client = new OkHttpClient.Builder().readTimeout(5000, TimeUnit.SECONDS).build();
    private static ChatVo chatVo;
    private static String accessToken;

    public static boolean getAccessToken(String apiKey, String secretKey){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url(ChatConstant.ACCESS_TOKEN_URI + "?client_id=" + apiKey + "&client_secret=" + secretKey + "&grant_type=client_credentials=")
                .addHeader("Content-Type", "application/json")
                .method("Post", requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseMessage = response.body().string();
            JSONObject jsonObject = JSON.parseObject(responseMessage);
            accessToken = (String) jsonObject.get("access_token");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ChatVo getAnswer(String question, RequestMessage requestBody){
        //通过参数获取一个Message
        Message message = new Message("user",question);
        //将新的问题添加到消息上下文
        requestBody.addMessage(message);
        String jsonStr = JSON.toJSONString(requestBody);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonStr);
        Request request = new Request.Builder()
                .url(ChatConstant.CHAT_URL + "?access_token=" + accessToken)
                .addHeader("Content-Type", "application/json")
                .method("POST",body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseJsonStr = response.body().string();
            ResponseMessage responseMessage = JSON.parseObject(responseJsonStr, ResponseMessage.class);
            String result = responseMessage.getResult();
            String answer = result.replaceAll("\n+", "\n");
            log.debug("{}",answer);
            Message assistant = new Message("assistant", answer);
            requestBody.addMessage(assistant);
            chatVo.setRequestMessage(requestBody);
            chatVo.setAnswer(answer);
            return chatVo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
