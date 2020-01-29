package com.jiujiu.j9game.service;

import com.google.gson.Gson;
import com.jiujiu.j9game.model.JiuJiuUser;
import com.jiujiu.j9game.model.LoginResult;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public LoginResult checkLogin(String tokenHeader) {
        return new LoginResult();
    }


    private static class JiuJiuResp {
        private int status;

        private String message;

        private Account data;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Account getData() {
            return data;
        }

        public void setData(Account data) {
            this.data = data;
        }

        static class Account {
            private JiuJiuUser account;

            public JiuJiuUser getAccount() {
                return account;
            }

            public void setAccount(JiuJiuUser account) {
                this.account = account;
            }
        }
    }

    public JiuJiuUser getJiuJiuUser(String tokenHeader) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://www.jiujiuapp.cn/app/api/userinfo");
        post.setHeader("Authorization", tokenHeader);
        post.setHeader("Accept-Charset", "UTF-8");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        try(CloseableHttpResponse response = client.execute(post)) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                try (InputStream inputStream = httpEntity.getContent()) {
                    String resp = IOUtils.toString(inputStream, "UTF-8");
                    logger.info("userinfo {}", resp);
                    Gson gson = new Gson();
                    JiuJiuResp jjresp = gson.fromJson(resp, JiuJiuResp.class);
                    if(jjresp.status == 1) {
                        return jjresp.getData().getAccount();
                    } else
                        return null;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("http requet error", e);
        }
        return null;
    }

}
