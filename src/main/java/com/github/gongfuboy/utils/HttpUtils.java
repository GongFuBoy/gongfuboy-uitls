package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.wechat.Constants;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * http代理工具
 *
 * @author GongFuBoy
 * @date 2017/12/28
 * @time 17:02
 */
public class HttpUtils {

    /**
     * get方式进行http请求
     * @param url
     * @return
     */
    public static String get(String url) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream content = null;
        BufferedReader bufferedReader = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse execute;
            execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            content = entity.getContent();
            bufferedReader = new BufferedReader(new InputStreamReader(content, Constants.ENCODING));
            String line = bufferedReader.readLine();
            while (StringUtils.isNotBlank(line)) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(content);
            IOUtils.closeQuietly(bufferedReader);
        }
        return stringBuilder.toString();
    }
}
