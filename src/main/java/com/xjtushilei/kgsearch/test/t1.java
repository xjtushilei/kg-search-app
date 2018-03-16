package com.xjtushilei.kgsearch.test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class t1 {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("ClassName", "数据结构")
                .add("TermName", "二叉树")
                .add("HasFragment", "true")
                .build();
        Request request = new Request.Builder()
                .url("http://yotta.xjtushilei.com:9218/Yotta/AssembleAPI/getTreeByTopicForFragment")
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        JsonObject data = new JsonParser().parse(Objects.requireNonNull(response.body()).string()).getAsJsonObject();
        data.addProperty("ClassName_4_es", "ClassName");
        data.addProperty("TermName_4_es", "TermName");
        System.out.println(data.toString());

    }
}
