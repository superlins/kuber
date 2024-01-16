package org.example.kuber.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.mvel2.MVEL;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author renc
 */
class ApplicationTest {

    String s = "{\"name\":\"test\",\"phone\":\"000ffeab94ae728def7160c08917f270\",\"idcardno\":\"000ffeab94ae728def7160c08917f270\"}";


    @Test
    public void test() throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost("http://uat.oceanum.cn/api/75c150c3");
            httpPost.addHeader("content-type", "application/json;charset=UTF-8");
            httpPost.addHeader("nonce", "d42e6af82aee436fa7171a4be7ce234f");
            // httpPost.addHeader("accept-encoding", "gzip,deflate");
            httpPost.addHeader("connection", "keep-alive");
            httpPost.addHeader("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJQQm12RE5OSExoWUhsQmZ3RWQ1a0JwWmtYRk1IMXdSWiJ9.BfagL_QzAXMh0dxQydQ1XfJT-FB0J9RmyL_xBI6S6Zc");
            httpPost.setEntity(new StringEntity(s));


            String content = httpClient.execute(httpPost, resp -> {

                System.out.println(">> RESP HEADERS: " + Arrays.toString(resp.getAllHeaders()));

                int status = resp.getStatusLine().getStatusCode();
                if (status == HttpStatus.SC_OK) {
                    HttpEntity entity = resp.getEntity();
                    if (entity != null) {
                        return EntityUtils.toString(entity);
                    }
                }
                return null;
            });
            System.out.println(">> RESP BODY:" + content);
        }
    }

    @Test
    public void testMVEL() {
        String s = """
                a = a1;
                b = b1;
                                
                def res(a, b) {
                if(a >= 300 && a < 630) {
                 b = b - 20;
                } else if(a >= 630 && a < 650) {
                 b = b - 10;
                } else if(a >= 650 && a < 680) {
                 b = b - 5;
                } else if(a >= 680 && a < 700) {
                 b = b - 3;
                 System.out.printf("[a >= 680 && a < 700] >>> : %d/%d \n", a, b);
                } else if(a >= 700 && a < 730) {
                 b = b - 1;
                } else if(a >= 820 && a <= 850) {
                 b = b + 2;
                } else {
                 b = -1;
                }
                }
                
                b = res(a, b);
                
                if(b > 1 && b < 300) {
                 return 300;
                } else if(b > 850) {
                 return 850;
                } else {
                 return -1;
                }
                """;
        Map<String, Object> params = new HashMap<>();
        params.put("a1", 680);
        params.put("b1", 860);
        Object eval = MVEL.eval(s, params);
        System.out.println(eval);
    }
}