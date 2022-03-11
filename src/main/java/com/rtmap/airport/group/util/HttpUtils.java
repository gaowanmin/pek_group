package com.rtmap.airport.group.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;


public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private static int SOCKET_TIMEOUT = 40000;
    private static int CONNECT_TIMEOUT = 40000;
    private static int MAX_TOTAL = 100;

    /**
     * 单例http client
     */
    private static volatile CloseableHttpClient httpclient;

    public static String get(String url, HttpClientContext httpContext) {
        HttpGet httpGet = new HttpGet(url);
        //设置user-agent
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:58.0) Gecko/20100101 Firefox/58.0");
        return executeGet(httpGet, httpContext);
    }


    /**
     * 发送带query param的get
     *
     * @param url
     * @param params
     * @param httpContext
     * @return
     */
    public static String get(String url, Map<String, Object> params, HttpClientContext httpContext) {
        List<NameValuePair> nvps = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> en : params.entrySet()) {
            NameValuePair nameValuePair = new BasicNameValuePair(en.getKey(),
                    String.valueOf(en.getValue()));
            nvps.add(nameValuePair);
        }
        String paramStr;
        try {
            paramStr = EntityUtils.toString(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        } catch (IOException e) {
            LOGGER.error("HttpUtils,get error", e);
            return "";
        }
        HttpGet httpGet = new HttpGet(url + "?" + paramStr);
        //设置user-agent
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");

        return executeGet(httpGet, httpContext);
    }


    /**
     * Http Post 每个线程执行请求时带自己的线程上线文
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        // 组装参数
        List<NameValuePair> paramList = initParam(params);
        HttpPost httpPost = new HttpPost(url);
        //设置user-agent
        httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
        return executePost(params, httpPost);
    }

    public static CloseableHttpResponse post(String url, String params, SSLConnectionSocketFactory sslConnectionSocketFactory, boolean isLoadCert) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        if (isLoadCert) {
            // 加载含有证书的http请求
            return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build().execute(httpPost);
        } else {
            return HttpClients.custom().build().execute(httpPost);
        }
    }

    public static String post(String url, String params) throws IOException {
        CloseableHttpClient client = getHttpClient();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        response = client.execute(httpPost);
        // 获得响应实体
        HttpEntity entity = response.getEntity();
        // 获取响应字符串
        return EntityUtils.toString(entity, Consts.UTF_8);
    }

    //添加Header
    public static String postHeader(String url, String params, Map<String, String> headers) throws IOException {
        CloseableHttpClient client = getHttpClient();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        headers.keySet().forEach(h -> {
            httpPost.setHeader(h, headers.get(h));
        });
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        response = client.execute(httpPost);
        // 获得响应实体
        HttpEntity entity = response.getEntity();
        // 获取响应字符串
        return EntityUtils.toString(entity, Consts.UTF_8);
    }

    //艾比加Header
    public static String postPram(String url, String params, String ApiKey, String Sign, String Timestamp) throws IOException {
        CloseableHttpClient client = getHttpClient();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Aibee-Auth-ApiKey", ApiKey);
        httpPost.setHeader("Aibee-Auth-Sign", Sign);
        httpPost.setHeader("Aibee-Auth-Timestamp", Timestamp);
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        response = client.execute(httpPost);
        // 获得响应实体
        HttpEntity entity = response.getEntity();
        // 获取响应字符串
        return EntityUtils.toString(entity, Consts.UTF_8);
    }


    //饿了么加Header
    public static String elemePost(String url, String params, String consumerNo, String timeStamp, String sign) throws IOException {
        CloseableHttpClient client = getHttpClient();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("consumerNo", consumerNo);
        httpPost.setHeader("timeStamp", timeStamp);
        httpPost.setHeader("sign", sign);
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        response = client.execute(httpPost);
        // 获得响应实体
        HttpEntity entity = response.getEntity();
        // 获取响应字符串
        return EntityUtils.toString(entity, Consts.UTF_8);
    }

    /**
     * 初始化参数
     *
     * @param params
     * @return
     */
    private static List<NameValuePair> initParam(Map<String, String> params) {
        List<NameValuePair> paramList = new LinkedList<>();
        if (params != null) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                Object value = params.get(key);
                paramList.add(
                        new BasicNameValuePair(key, value == null ? "" : value.toString()));
            }
        }
        return paramList;
    }

    /**
     * 执行post请求
     *
     * @param params
     * @param httpPost
     * @return
     */
    private static String executePost(Map<String, String> params, HttpPost httpPost) {
        String result = null;
        CloseableHttpResponse response = null;
        try {
            // 获取连接客户端
            CloseableHttpClient httpClient = getHttpClient();
            // 发起请求
            response = httpClient.execute(httpPost);
            int respCode = response.getStatusLine().getStatusCode();
            /**
             * HttpClient打开重定向策略 不用再手动判断
             */
            // 如果是重定向301 302
//            if (HttpStatus.SC_MOVED_TEMPORARILY == respCode || HttpStatus.SC_MOVED_PERMANENTLY == respCode) {
//                String locationUrl = response.getLastHeader("Location").getValue();
//                return post(locationUrl, params, httpContext);
//            }
            //响应200
            if (HttpStatus.SC_OK == respCode) {
                // 获得响应实体
                HttpEntity entity = response.getEntity();
                // 获取响应字符串
                result = EntityUtils.toString(entity, Consts.UTF_8);
            } else {
                // 获得响应实体
                HttpEntity entity = response.getEntity();
                // 获取响应字符串
                result = EntityUtils.toString(entity, Consts.UTF_8);
                LOGGER.error("HttpUtils,http response code != 200 :{}", result);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }

        }
        return result;
    }


    /***
     * 执行get请求
     * @param httpGet
     * @param httpContext
     * @return
     */
    private static String executeGet(HttpGet httpGet, HttpClientContext httpContext) {
        String result = null;
        CloseableHttpResponse response = null;
        try {
            // 获取连接客户端
            CloseableHttpClient httpClient = getHttpClient();
            // 发起请求
            response = httpClient.execute(httpGet, httpContext);
            int respCode = response.getStatusLine().getStatusCode();
//            // 如果是重定向
//            if (HttpStatus.SC_MOVED_TEMPORARILY == respCode) {
//                String locationUrl = response.getLastHeader("Location").getValue();
//                return get(locationUrl, httpContext);
//            }
            //响应200
            if (HttpStatus.SC_OK == respCode) {
                // 获得响应实体
                HttpEntity entity = response.getEntity();
                // 获取响应字符串
                result = EntityUtils.toString(entity, Consts.UTF_8);
            } else {
                // 获得响应实体
                HttpEntity entity = response.getEntity();
                LOGGER.error("HttpUtils,http response:{}", entity.toString());
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }

        }
        return result;
    }

    /**
     * 解析验证码
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static byte[] downImgCode(String url, HttpClientContext httpClientContext) throws Exception {

        HttpEntity httpEntity = null;
        try {
            HttpGet httpGet = new HttpGet(url);

            CloseableHttpClient httpClient = getHttpClient();

            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:57.0) Gecko/20100101 Firefox/57.0");
            CloseableHttpResponse response = httpClient.execute(httpGet, httpClientContext);

            int respCode = response.getStatusLine().getStatusCode();
            //响应200
            if (HttpStatus.SC_OK == respCode) {
                // 获得响应实体
                httpEntity = response.getEntity();
                if (httpClient == null) {
                    return null;
                }
                InputStream is = httpEntity.getContent();
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                // 1K的数据缓冲
                byte[] bs = new byte[2048];
                // 读取到的数据长度
                int len;
                // 开始读取
                while ((len = is.read(bs)) != -1) {
                    swapStream.write(bs, 0, len);
                }
                return swapStream.toByteArray();
            }

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            EntityUtils.consumeQuietly(httpEntity);
        }

        return null;
    }

    /**
     * 获取HttpClient实例
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        try {

            SSLContext sslContext = SSLContext.getInstance("SSLv3");

            // 取消检测SSL
            X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
            };
            sslContext.init(null, new TrustManager[]{x509TrustManager}, null);

            //SSL Socket连接配置
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", sslConnectionSocketFactory)
                    .build();

            if (httpclient == null) {
                synchronized (HttpUtils.class) {
                    if (httpclient == null) {
                        //连接池配置
                        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
                        cm.setMaxTotal(MAX_TOTAL);
                        cm.setDefaultMaxPerRoute(cm.getMaxTotal());

                        //请求配置
                        RequestConfig requestConfig = RequestConfig.custom()
                                .setCookieSpec(CookieSpecs.STANDARD)
                                .setConnectTimeout(CONNECT_TIMEOUT)
                                .setSocketTimeout(SOCKET_TIMEOUT)
                                .build();

                        //创建HttpClient
                        httpclient = HttpClients.custom()
                                .setSSLSocketFactory(sslConnectionSocketFactory)
                                .setConnectionManager(cm)
                                .setDefaultRequestConfig(requestConfig)
                                .disableAutomaticRetries()
                                //打开post自动重定向策略
                                .setRedirectStrategy(new LaxRedirectStrategy())
                                .build();
                    }
                }
            }

            return httpclient;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return HttpClients.createDefault();
    }


    public static void main(String[] args) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Aibee-Auth-ApiKey", "AAAAA");
        headers.put("Aibee-Auth-Sign", "BBBBBB");
        headers.put("Aibee-Auth-Timestamp", "1234232");
        try {
            postHeader("https://www.baidu.com/", "ssssss", headers);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

