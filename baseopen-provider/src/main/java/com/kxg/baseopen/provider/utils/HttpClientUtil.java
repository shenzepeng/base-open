package com.kxg.baseopen.provider.utils;

import com.alibaba.fastjson.JSON;

import com.kxg.baseopen.provider.exception.KxgException;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final String JSON_DATA_KEY = "json_data";
    private static final String XML_DATA_KEY = "xml_data";

    private static final Integer SOCKET_TIMEOUT = 30000;
    private static final Integer CONN_TIMEOUT = 30000;
    private static final Integer CONN_REQUEST_TIMEOUT = 30000;

    public static final Integer DEFAULT_MAX_CONN_PER_ROUTE = 50;
    public static final Integer DEFAULT_MAX_CONN_TOTAL = 500;

    public static final Integer DEFAULT_KEEP_ALIVE = 5000;
    public static final Integer DEFAULT_RETRY_COUNT = 3;

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private static CloseableHttpClient httpClient;

    static {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONN_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setConnectionRequestTimeout(CONN_REQUEST_TIMEOUT).build();
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoTimeout(SOCKET_TIMEOUT).setSoKeepAlive(true).build();

        ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1L) {

                    keepAlive = DEFAULT_KEEP_ALIVE;
                }
                return keepAlive;
            }

        };

        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= DEFAULT_RETRY_COUNT) {
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);

                HttpRequest request = clientContext.getRequest();
                return !(request instanceof HttpEntityEnclosingRequest);
            }
        };

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONN_PER_ROUTE);
        connectionManager.setMaxTotal(DEFAULT_MAX_CONN_TOTAL);
        connectionManager.setDefaultSocketConfig(socketConfig);

        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).setRetryHandler(retryHandler)
                .setKeepAliveStrategy(keepAliveStrat).build();

    }

    /**
     * GET请求
     *
     * @param url 请求路径
     * @return 请求返回数据
     */
    public static String get(String url) {
        return doRequest(HttpMethod.GET, url, null, null);
    }

    /**
     * GET请求
     *
     * @param url      请求路径
     * @param paramMap 请求参数
     * @return 请求返回数据
     */
    public static String get(String url, Map<String, String> paramMap) {
        return doRequest(HttpMethod.GET, url, paramMap, null);
    }

    /**
     * GET请求
     *
     * @param url       请求路径
     * @param paramMap  请求参数
     * @param headerMap 自定义头信息
     * @return 请求返回数据
     */
    public static String get(String url, Map<String, String> paramMap, Map<String, String> headerMap) {
        return doRequest(HttpMethod.GET, url, paramMap, headerMap);
    }

    /**
     * POST请求
     *
     * @param url 请求路径
     * @return 请求返回数据
     */
    public static String post(String url) {
        return doRequest(HttpMethod.POST, url, null, null);
    }

    /**
     * POST请求
     *
     * @param url      请求路径
     * @param paramMap 请求参数
     * @return 请求返回数据
     */
    public static String post(String url, Map<String, String> paramMap) {
        return doRequest(HttpMethod.POST, url, paramMap, null);
    }

    /**
     * POST请求
     *
     * @param url       请求路径
     * @param paramMap  请求参数
     * @param headerMap 自定义头信息
     * @return 请求返回数据
     */
    public static String post(String url, Map<String, String> paramMap, Map<String, String> headerMap) {
        return doRequest(HttpMethod.POST, url, paramMap, headerMap);
    }

    /**
     * 发起JSON Post请求
     *
     * @param url      请求地址
     * @param jsonData JSON数据
     * @return 请求返回数据
     */
    public static String postJson(String url, HashMap<String, String> jsonData) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(JSON_DATA_KEY, JSON.toJSONString(jsonData));
        return doRequest(HttpMethod.POST_JSON, url, jsonMap, null);
    }

    /**
     * 发起JSON Post请求
     *
     * @param url       请求地址
     * @param jsonData  JSON数据
     * @param headerMap 头信息
     * @return 请求返回数据
     */
    public static String postJson(String url, Object jsonData, Map<String, String> headerMap) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(JSON_DATA_KEY, JSON.toJSONString(jsonData));
        return doRequest(HttpMethod.POST_JSON, url, jsonMap, headerMap);
    }

    /**
     * 发起JSON Post请求
     *
     * @param url       请求地址
     * @param jsonData  JSON数据
     * @param headerMap 头信息
     * @return 请求返回数据
     */
    public static String postJson(String url, Map<String, Object> jsonData, Map<String, String> headerMap) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(JSON_DATA_KEY, JSON.toJSONString(jsonData));
        return doRequest(HttpMethod.POST_JSON, url, jsonMap, headerMap);
    }

    // =======================SSL========================

    /**
     * 发起JSON Post请求
     *
     * @param url      请求地址
     * @param jsonData JSON数据
     * @return 请求返回数据
     */
    public static String postJsonSSL(String url, Map<String, Object> jsonData, SSLEnvBuilder sslEnvBuilder) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(JSON_DATA_KEY, JSON.toJSONString(jsonData));
        return doRequestSSL(HttpMethod.POST_JSON, url, jsonMap, null, sslEnvBuilder);
    }

    /**
     * 发起JSON Post请求
     *
     * @param url       请求地址
     * @param jsonData  JSON数据
     * @param headerMap 头信息
     * @return 请求返回数据
     */
    public static String postJsonSSL(String url, Map<String, Object> jsonData, Map<String, String> headerMap, SSLEnvBuilder sslEnvBuilder) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(JSON_DATA_KEY, JSON.toJSONString(jsonData));
        return doRequestSSL(HttpMethod.POST_JSON, url, jsonMap, headerMap, sslEnvBuilder);
    }

    public interface SSLEnvBuilder {
        LayeredConnectionSocketFactory createSSLSocketFactory();
    }

    private static String doRequest(HttpMethod method, String url, Map<String, String> paramMap, Map<String, String> headerMap) {
        return doRequestSSL(method, url, paramMap, headerMap, null);
    }

    private static String doRequestSSL(HttpMethod method, String url, Map<String, String> paramMap, Map<String, String> headerMap, SSLEnvBuilder sslEnvBuilder) {

        if (url == null || "".equals(url)) {
            throw new IllegalArgumentException("url cannot be empty");
        }

        HttpUriRequest request = null;
        CloseableHttpResponse response = null;

        try {
            switch (method) {
                case GET:
                    URI uri = new URIBuilder(url).addParameters(convertToNameValuePairs(paramMap)).build();
                    request = new HttpGet(uri);
                    break;
                case POST:
                    HttpEntity formEntity = new UrlEncodedFormEntity(convertToNameValuePairs(paramMap), DEFAULT_CHARSET);
                    request = new HttpPost(url);
                    ((HttpPost) request).setEntity(formEntity);
                    break;
                case POST_JSON:
                    String jsonData = paramMap.get(JSON_DATA_KEY);
                    LOG.debug("jsonData:{}", jsonData);
                    HttpEntity jsonEntity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
                    request = new HttpPost(url);
                    ((HttpPost) request).setEntity(jsonEntity);
                    break;
                case POST_XML:
                    String xmlData = paramMap.get(XML_DATA_KEY);
                    HttpEntity xmlEntity = new StringEntity(xmlData, DEFAULT_CHARSET);
                    request = new HttpPost(url);
                    ((HttpPost) request).setEntity(xmlEntity);
                    break;

                default:
                    break;
            }
            LOG.info("request:{}", request);

            response = httpClient.execute(request, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            String resultData = EntityUtils.toString(entity, DEFAULT_CHARSET);

            LOG.info("statusCode：{}", statusCode);
            LOG.info("resultData：{}", resultData);

            return resultData;
        } catch (IOException | URISyntaxException e) {
            LOG.error("execute [{}] request error", method.name());
            throw new KxgException(
                    "", String.format("execute [%s] request error", method.name())
            );
        } finally {
            if (null != request && !request.isAborted()) {
                request.abort();
            }
            HttpClientUtils.closeQuietly(response);
        }
    }

    private static List<NameValuePair> convertToNameValuePairs(Map<String, String> paramMap) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramMap != null && paramMap.size() > 0) {
            for (Map.Entry<String, String> e : paramMap.entrySet()) {
                String value = e.getValue();
                if (value != null && !"".equals(value)) {
                    nvps.add(new BasicNameValuePair(e.getKey(), value));
                }
            }
        }
        return nvps;
    }

    public static String wormPost(String url,String appKey) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("appKey",appKey);
        CloseableHttpResponse response = null;
        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();

                String jsonString = EntityUtils.toString(responseEntity);
                LOG.error("请求返回:("+jsonString+")");
                return jsonString;
            }
            else{
                LOG.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *  本地文件http上传  form表单上传
     * @param targetUrl 目标url
     * @param fileKey  文件key
     * @param filePath 本地文件路径
     * @return
     */
    public static String uploadForm(String targetUrl, String fileKey, String filePath) {
        String end=null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(targetUrl);
            // 把文件转换成流对象FileBody
            FileBody bin = new FileBody(new File(filePath));
//            StringBody appKey = new StringBody("2D82A760C145BC93", ContentType.create(
//                    "text/plain", Consts.UTF_8));
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    // 相当于<input type="file" name="file"/>
                    .addPart(fileKey, bin)
                    // 相当于<input type="text" name="userName" value=userName>
//                    .addPart("appKey", appKey)
//                    .addPart("pass", password)
                    .build();

            httpPost.setEntity(reqEntity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            System.out.println("The response value of token:" + response.getFirstHeader("token"));

            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应长度
                System.out.println("Response content length: " + resEntity.getContentLength());
                // 打印响应内容
                end= EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
            }
            // 销毁
            EntityUtils.consume(resEntity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return end;
    }
    /**
     * 标识HTTP请求类型枚举
     */
    static enum HttpMethod {
        GET, POST, PUT, DELETE, POST_JSON, POST_XML
    }
}
