package com.xjtushilei.kgsearch.service;

import com.github.pagehelper.PageHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xjtushilei.kgsearch.mapper.YottaMapper;
import com.xjtushilei.kgsearch.model.AssembleFragment;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class YottaService {
    @Autowired
    YottaMapper yottaMapper;

    @Value("${yotta.url}")
    String yottaUrl;

    public List<AssembleFragment> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return yottaMapper.findAll();
    }

    public List<AssembleFragment> getAllByClassName(String className, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return yottaMapper.findAllByClassName(className);
    }

    public List<AssembleFragment> getAllByClassName(String className) {
        return yottaMapper.findAllByClassName(className);
    }

    public long countAll() {
        return yottaMapper.count();
    }

    public long countByClassName(String className) {
        return yottaMapper.countByClassName(className);
    }

    public List<String> getAllClassName() {
        return yottaMapper.findAllClassName();
    }


    public Map<String, Object> getAllTermNameByClassName(String className) throws IOException {
        List<String> termNameList = yottaMapper.findTermNameByClassName(className);
        List<Map<String, String>> list = new ArrayList<>();
        List<String> error = new ArrayList<>();
        termNameList.parallelStream().forEach(term -> {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("ClassName", className)
                    .add("TermName", term)
                    .add("HasFragment", "true")
                    .build();
            Request request = new Request.Builder()
                    .url(yottaUrl)
                    .post(formBody)
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    error.add(term);
                }
                JsonObject data = new JsonParser().parse(Objects.requireNonNull(response.body()).string()).getAsJsonObject();
                data.addProperty("ClassName_4_es", "ClassName");
                data.addProperty("TermName_4_es", "TermName");
                Map<String, String> map = new HashMap<>();
                map.put("ClassName", className);
                map.put("TermName", term);
                map.put("data", data.toString());
                list.add(map);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        Map<String, Object> result = new HashMap<>();
        result.put("error", error);
        result.put("data", list);

        return result;
    }

}
