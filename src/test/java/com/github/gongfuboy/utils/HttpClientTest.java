package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.wechat.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author GongFuBoy
 * @date 2017/12/14
 * @time 17:43
 */
public class HttpClientTest {

    @org.junit.Test
    public void test() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        HttpGet httpGet = new HttpGet("http://localhost:8080/washcar/wechatquery/queryIndexInfo?mobile=17674130318");
        HttpPost httpPost = new HttpPost();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse execute = httpClient.execute(httpGet);
        HttpEntity entity = execute.getEntity();
        InputStream content = entity.getContent();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, Constants.ENCODING));
        String line = bufferedReader.readLine();
        while (StringUtils.isNotBlank(line)) {
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }
        System.out.println(stringBuilder.toString());
    }
}
