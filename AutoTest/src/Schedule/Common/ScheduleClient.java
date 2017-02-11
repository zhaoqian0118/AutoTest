package Schedule.Common;

import Schedule.Base.ScheduleConfig;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleClient {
    private static HttpClient client = null;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }

    //Get
    public static String doGet(String url,String token) throws Exception {
        return get(url,token, "UTF-8", null, null);
    }

    //Get_Param
    public static String doGetParam(String url,String param,String token) throws Exception {
        return get(url+"?"+param, token,"UTF-8", null, null);
    }

    //POST_Json
    public static String doPostJson(String url,String json,String token) throws Exception {
        return post(url, json, token, "application/json", "UTF-8", null, null);
    }

    //POST
    public static String doPost(String url,String token) throws Exception {
        return post(url, "", token, "application/json", "UTF-8", null, null);
    }

    //POST_Form
    public static String doPostForm(String url,Map map,String token) throws Exception {
        return postForm(url, map, token, null, null);
    }

    //PUT
    public static String doPut(String url,String json,String token) throws Exception {
        return put(url, json, token, "application/json", "UTF-8", null, null);
    }

    //DELETE
    public static String doDelete(String url,String token) throws Exception {
        return delete(url, token, "application/json", "UTF-8", null, null);
    }


    private static String post(String url, String body,String token, String mimeType,
                               String charset, Integer connTimeout, Integer readTimeout)
            throws ConnectTimeoutException, SocketTimeoutException, Exception {
        Security.addProvider(new BouncyCastleProvider());
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = "";
        try {
            if (StringUtils.isNotBlank(body)) {
                HttpEntity entity = new StringEntity(body, ContentType.create(
                        mimeType, charset));
                post.setEntity(entity);
            }
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setHeader("Authorization",token);
            post.setConfig(customReqConf.build());

            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(post);
            }
            else {
                // 执行 Http 请求.
                client = ScheduleClient.client;
                res = client.execute(post);
            }
            if(res.getStatusLine().getStatusCode()==204){
                result="HTTP/1.1 "+res.getStatusLine().getStatusCode()+" No Content";
            }else if(res.getStatusLine().getStatusCode()!=204) {
                result = IOUtils.toString(res.getEntity().getContent(), charset);
            }
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null
                    && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    private static String postForm(String url, Map<String, String> params,String token,
                                   Integer connTimeout,
                                   Integer readTimeout) throws ConnectTimeoutException,
            SocketTimeoutException, Exception {
        Security.addProvider(new BouncyCastleProvider());

        HttpClient client = null;

        HttpPost post = new HttpPost(url);
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                Set<Map.Entry<String, String>> entrySet = params.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry
                            .getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                        formParams, Consts.UTF_8);
                post.setEntity(entity);
            }
//            if (headers != null && !headers.isEmpty()) {
//                for (Map.Entry<String, String> entry : headers.entrySet()) {
//                    post.addHeader(entry.getKey(), entry.getValue());
//                }
//            }
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setHeader("Authorization", token);
            post.setConfig(customReqConf.build());
            HttpResponse res = null;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(post);
            } else {
                // 执行 Http 请求.
                client = ScheduleClient.client;
                res = client.execute(post);
            }
            return IOUtils.toString(res.getEntity().getContent(), "UTF-8");
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null
                    && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
    }

    private static String put(String url, String body, String token,String mimeType,
                              String charset, Integer connTimeout, Integer readTimeout)
            throws ConnectTimeoutException, SocketTimeoutException, Exception {
        Security.addProvider(new BouncyCastleProvider());
        HttpClient client = null;
        HttpPut put = new HttpPut(url);
        String result = "";
        try {
            if (StringUtils.isNotBlank(body)) {
                HttpEntity entity = new StringEntity(body, ContentType.create(
                        mimeType, charset));
                put.setEntity(entity);
            }
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            put.setHeader("Authorization",token);
            put.setConfig(customReqConf.build());

            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(put);
            }
            else {
                // 执行 Http 请求.
                client = ScheduleClient.client;
                res = client.execute(put);
            }
            result = IOUtils.toString(res.getEntity().getContent(), charset);
        } finally {
            put.releaseConnection();
            if (url.startsWith("https") && client != null
                    && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    private static String delete(String url, String token,String mimeType,
                                 String charset, Integer connTimeout, Integer readTimeout)
            throws ConnectTimeoutException, SocketTimeoutException, Exception {
        Security.addProvider(new BouncyCastleProvider());
        HttpClient client = null;
        HttpDelete delete = new HttpDelete(url);
        String result = "";
        try {

            HttpEntity entity = new StringEntity("", ContentType.create(
                    mimeType, charset));
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            delete.setHeader("Authorization", token);
            delete.setConfig(customReqConf.build());

            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(delete);
            } else {
                // 执行 Http 请求.
                client = ScheduleClient.client;
                res = client.execute(delete);
            }
            result = IOUtils.toString(res.getEntity().getContent(), charset);
        } finally {
            delete.releaseConnection();
            if (url.startsWith("https") && client != null
                    && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    private static String get(String url, String token,String charset, Integer connTimeout,
                              Integer readTimeout) throws ConnectTimeoutException,
            SocketTimeoutException, Exception {
        Security.addProvider(new BouncyCastleProvider());
        HttpClient client = null;

        HttpGet get = new HttpGet(url);
        String result = "";
        try {
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            get.setHeader("Authorization",token);
            get.setConfig(customReqConf.build());

            HttpResponse res = null;

            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(get);
            } else {
                // 执行 Http 请求.
                client = ScheduleClient.client;
                res = client.execute(get);
            }
            result = IOUtils.toString(res.getEntity().getContent(), charset);
        } finally {
            get.releaseConnection();
            if (url.startsWith("https") && client != null
                    && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 从 response 里获取 charset
     *
     * @param ressponse
     * @return
     */
    @SuppressWarnings("unused")
    private static String getCharsetFromResponse(HttpResponse ressponse) {
        // Content-Type:text/html; charset=UTF-8
        if (ressponse.getEntity() != null
                && ressponse.getEntity().getContentType() != null
                && ressponse.getEntity().getContentType().getValue() != null) {
            String contentType = ressponse.getEntity().getContentType()
                    .getValue();
            if (contentType.contains("charset=")) {
                return contentType
                        .substring(contentType.indexOf("charset=") + 8);
            }
        }
        return null;
    }

    private static CloseableHttpClient createSSLInsecureClient()
            throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl)
                        throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert)
                        throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns,
                                   String[] subjectAlts) throws SSLException {
                }

            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException e) {
            throw e;
        }
    }
}
