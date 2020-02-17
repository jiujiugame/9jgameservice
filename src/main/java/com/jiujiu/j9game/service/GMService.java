package com.jiujiu.j9game.service;

import com.google.gson.Gson;
import com.jiujiu.j9game.model.GMUserInfo;
import com.jiujiu.j9game.model.GMUserReq;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GMService {

    @Value("#{'${gm.servers}'.split(',')}")
    private List<String> gmServers;

    private Logger logger = LoggerFactory.getLogger(GMService.class);

    private String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            logger.error("http request error", e);
        }
        return null;
    }

    private String postJson(String url, String body) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");

        StringEntity postingString = new StringEntity(body, "utf-8");
        post.setEntity(postingString);
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(30 * 1000)
                    .setConnectTimeout(3 * 1000)
                    .build();
            post.setConfig(requestConfig);
            try (CloseableHttpResponse response = client.execute(post)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    try (InputStream inputStream = httpEntity.getContent()) {
                        String resp = IOUtils.toString(inputStream, "UTF-8");
                        return resp;
                    }
                }
            } catch (Exception e) {
                logger.error("http request error", e);
            }
        } catch (Exception e) {
            logger.error("http request error", e);
        }
        return null;
    }

    private String postText(String url, String body) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(30 * 1000)
                    .setConnectTimeout(3 * 1000)
                    .build();
            post.setConfig(requestConfig);
            post.setHeader("Content-Type", "text/plain; charset=utf-8");
            post.setEntity(new StringEntity(body));
            try(CloseableHttpResponse response = client.execute(post)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    try (InputStream inputStream = httpEntity.getContent()) {
                        String resp = IOUtils.toString(inputStream, "UTF-8");
                        return resp;
                    }
                }
            } catch (Exception e) {
                logger.error("http request error", e);
            }
        } catch (Exception e) {
            logger.error("http request error", e);
        }
        return null;
    }


    public void deposit(Long uid, Integer productId, Integer gameZoneId) {
        String gmserver = gmServers.get(gameZoneId);
        String url = gmserver + "/cgi-bin/gm_operate:query_player_info";
        Gson gson = new Gson();
        GMUserReq req = new GMUserReq();
        req.setCondition(String.valueOf(uid));
        req.setSign(getMD5(req.getCondition()+req.getFlag()+"36dacf71144959ab376e0b6000ded605"));
        String resp = postJson(url, gson.toJson(req));
        if(resp == null)
            throw new RuntimeException("error got user info");
        logger.info(resp);
        GMUserInfo gmUinfo = gson.fromJson(resp, GMUserInfo.class);
        long time = System.currentTimeMillis()/1000;
        String uuid = String.valueOf(gmUinfo.getId());
        String body = "role_id=" + uuid + "&";
        body += "extra_params=" + String.format("1,%s,%s,10,com.huiqu.jx.jxfyl.6,%s,1", uuid, time, productId) + "&";
        body += "order_id=" + time + "&";
        body += "amount=10&pay_time=1531407538";
        logger.info(body);
        try {
            resp = postText(gmserver + "/cgi-bin/pay_request:order_info", body);
            logger.info(resp);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding error.");
        }
    }

}
