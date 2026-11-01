package com.amazon.device.ads;

import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class WebRequest {
    private static final String CHARSET_KEY = "charset";
    public static final String CHARSET_UTF_16 = "UTF-16";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE_CSS = "text/css";
    public static final String CONTENT_TYPE_HTML = "text/html";
    public static final String CONTENT_TYPE_JAVASCRIPT = "application/javascript";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_PLAIN_TEXT = "text/plain";
    public static final int DEFAULT_PORT = -1;
    public static final int DEFAULT_TIMEOUT = 20000;
    private static final String HEADER_ACCEPT_KEY = "Accept";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String LOGTAG = "WebRequest";
    private MetricsCollector metricsCollector;
    protected boolean secure;
    protected Metrics.MetricType serviceCallLatencyMetric;
    String requestBody = null;
    String acceptContentType = null;
    String contentType = null;
    String charset = null;
    private String urlString = null;
    private String secureHost = null;
    private String nonSecureHost = null;
    private String path = null;
    private int port = -1;
    private HttpMethod httpMethod = HttpMethod.GET;
    private int timeout = 20000;
    boolean logRequestBodyEnabled = false;
    boolean logResponseEnabled = false;
    protected boolean logUrlEnabled = false;
    boolean logSessionIdEnabled = false;
    private String logTag = LOGTAG;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(this.logTag);
    protected QueryStringParameters queryStringParameters = new QueryStringParameters();
    protected final HashMap<String, String> headers = new HashMap<>();
    protected HashMap<String, String> postParameters = new HashMap<>();
    private boolean disconnectEnabled = true;

    public enum WebRequestStatus {
        NETWORK_FAILURE,
        NETWORK_TIMEOUT,
        MALFORMED_URL,
        INVALID_CLIENT_PROTOCOL,
        UNSUPPORTED_ENCODING
    }

    protected abstract void closeConnection();

    protected abstract WebResponse doHttpNetworkCall(URL url) throws WebRequestException;

    protected abstract String getSubLogTag();

    WebRequest() {
        this.secure = false;
        this.secure = Settings.getInstance().getBoolean("tlsEnabled", false);
    }

    public void convertToJSONPostRequest() {
        setHttpMethod(HttpMethod.POST);
        putHeader("Accept", "application/json");
        putHeader("Content-Type", "application/json; charset=UTF-8");
    }

    public WebResponse makeCall() throws WebRequestException {
        if (ThreadUtils.isOnMainThread()) {
            this.logger.e("The network request should not be performed on the main thread.");
        }
        setContentTypeHeaders();
        String url = getUrl();
        try {
            URL urlCreateURL = createURL(url);
            writeMetricStart(this.serviceCallLatencyMetric);
            try {
                try {
                    WebResponse webResponseDoHttpNetworkCall = doHttpNetworkCall(urlCreateURL);
                    writeMetricStop(this.serviceCallLatencyMetric);
                    if (this.logResponseEnabled) {
                        this.logger.d("Response: %s %s", Integer.valueOf(webResponseDoHttpNetworkCall.getHttpStatusCode()), webResponseDoHttpNetworkCall.getHttpStatus());
                    }
                    return webResponseDoHttpNetworkCall;
                } catch (Throwable th) {
                    writeMetricStop(this.serviceCallLatencyMetric);
                    throw th;
                }
            } catch (WebRequestException e) {
                throw e;
            }
        } catch (MalformedURLException e2) {
            this.logger.e("Problem with URI syntax: %s", e2.getMessage());
            throw new WebRequestException(WebRequestStatus.MALFORMED_URL, "Could not construct URL from String " + url, e2);
        }
    }

    public void enableLogUrl(boolean z) {
        this.logUrlEnabled = z;
    }

    public void enableLogRequestBody(boolean z) {
        this.logRequestBodyEnabled = z;
    }

    public void enableLogResponse(boolean z) {
        this.logResponseEnabled = z;
    }

    public void enableLogSessionID(boolean z) {
        this.logSessionIdEnabled = z;
    }

    public void enableLog(boolean z) {
        enableLogUrl(z);
        enableLogRequestBody(z);
        enableLogResponse(z);
    }

    protected void logUrl(String str) {
        if (this.logUrlEnabled) {
            this.logger.d("%s %s", getHttpMethod(), str);
        }
    }

    public String getQueryParameter(String str) {
        return this.queryStringParameters.get(str);
    }

    public void putUrlEncodedQueryParameter(String str, String str2) {
        this.queryStringParameters.putUrlEncoded(str, str2);
    }

    public String putUnencodedQueryParameter(String str, String str2) {
        return this.queryStringParameters.putUnencoded(str, str2);
    }

    public void setQueryStringParameters(QueryStringParameters queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public String getPostParameter(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }
        return this.postParameters.get(str);
    }

    public void putPostParameter(String str, String str2) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }
        if (str2 == null) {
            this.postParameters.remove(str);
        } else {
            this.postParameters.put(str, str2);
        }
    }

    public String getHeader(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }
        return this.headers.get(str);
    }

    public void putHeader(String str, String str2) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The name must not be null or empty string.");
        }
        this.headers.put(str, str2);
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        if (httpMethod == null) {
            throw new IllegalArgumentException("The httpMethod must not be null.");
        }
        this.httpMethod = httpMethod;
    }

    public String getHost() {
        return getUseSecure() ? this.secureHost : this.nonSecureHost;
    }

    public void setHost(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.secureHost = str;
        this.nonSecureHost = str;
    }

    public void setSecureHost(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.secureHost = str;
    }

    public void setNonSecureHost(String str) {
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("The host must not be null.");
        }
        this.nonSecureHost = str;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        if (str.charAt(0) != '/') {
            this.path = '/' + str;
            return;
        }
        this.path = str;
    }

    public boolean getUseSecure() {
        return DebugProperties.getInstance().getDebugPropertyAsBoolean(DebugProperties.DEBUG_USESECURE, Boolean.valueOf(this.secure)).booleanValue();
    }

    public void setUseSecure(boolean z) {
        this.secure = z;
    }

    public void setUrlString(String str) {
        if (str != null && getUseSecure() && str.startsWith("http:")) {
            str = str.replaceFirst("http", "https");
        }
        this.urlString = str;
    }

    public String getUrlString() {
        return this.urlString;
    }

    public void setRequestBodyString(String str) {
        this.requestBody = str;
    }

    public String getRequestBodyString() {
        return this.requestBody;
    }

    public String getRequestBody() {
        if (getRequestBodyString() != null) {
            return getRequestBodyString();
        }
        if (this.postParameters.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.postParameters.entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            sb.append(";\n");
        }
        return sb.toString();
    }

    public void setAcceptContentType(String str) {
        this.acceptContentType = this.contentType;
    }

    public String getAcceptContentType() {
        return this.acceptContentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public String getCharset() {
        return this.charset;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public void setMetricsCollector(MetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
    }

    public void setServiceCallLatencyMetric(Metrics.MetricType metricType) {
        this.serviceCallLatencyMetric = metricType;
    }

    public void setAdditionalQueryParamsString(String str) {
        this.queryStringParameters.setRawAppendage(str);
    }

    public void setExternalLogTag(String str) {
        if (str == null) {
            this.logTag = LOGTAG + " " + getSubLogTag();
        } else {
            this.logTag = str + " " + LOGTAG + " " + getSubLogTag();
        }
        this.logger.withLogTag(this.logTag);
    }

    public boolean getDisconnectEnabled() {
        return this.disconnectEnabled;
    }

    public void setDisconnectEnabled(boolean z) {
        this.disconnectEnabled = z;
    }

    protected MobileAdsLogger getLogger() {
        return this.logger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLogTag() {
        return this.logTag;
    }

    protected void setContentTypeHeaders() {
        if (this.acceptContentType != null) {
            putHeader("Accept", this.contentType);
        }
        String str = this.contentType;
        if (str != null) {
            if (this.charset != null) {
                str = str + "; charset=" + this.charset;
            }
            putHeader("Content-Type", str);
        }
    }

    protected void writeMetricStart(Metrics.MetricType metricType) {
        MetricsCollector metricsCollector;
        if (metricType == null || (metricsCollector = this.metricsCollector) == null) {
            return;
        }
        metricsCollector.startMetric(metricType);
    }

    protected void writeMetricStop(Metrics.MetricType metricType) {
        MetricsCollector metricsCollector;
        if (metricType == null || (metricsCollector = this.metricsCollector) == null) {
            return;
        }
        metricsCollector.stopMetric(metricType);
    }

    protected URI createUri() throws MalformedURLException, URISyntaxException {
        return new URL(getUrlString()).toURI();
    }

    protected URI createURI(String str) throws MalformedURLException, URISyntaxException {
        return createURI(createURL(str));
    }

    protected URI createURI(URL url) throws URISyntaxException {
        return url.toURI();
    }

    protected URL createURL(String str) throws MalformedURLException {
        return new URL(str);
    }

    protected void appendQuery(StringBuilder sb) {
        this.queryStringParameters.append(sb);
    }

    protected String getScheme() {
        return getUseSecure() ? "https" : "http";
    }

    public String toString() {
        return getUrl();
    }

    protected String getUrl() {
        String str = this.urlString;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(getScheme());
        sb.append("://");
        sb.append(getHost());
        if (getPort() != -1) {
            sb.append(":");
            sb.append(getPort());
        }
        sb.append(getPath());
        appendQuery(sb);
        return sb.toString();
    }

    public enum HttpMethod {
        GET(HttpRequest.METHOD_GET),
        POST(HttpRequest.METHOD_POST);

        private final String methodString;

        HttpMethod(String str) {
            this.methodString = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.methodString;
        }
    }

    public class WebResponse {
        private String httpStatus;
        private int httpStatusCode;
        private WebRequestInputStream inputStream;

        protected WebResponse() {
        }

        protected void setInputStream(InputStream inputStream) {
            this.inputStream = WebRequest.this.new WebRequestInputStream(inputStream);
        }

        public ResponseReader getResponseReader() {
            ResponseReader responseReader = new ResponseReader(this.inputStream);
            responseReader.enableLog(WebRequest.this.logResponseEnabled);
            responseReader.setExternalLogTag(WebRequest.this.getLogTag());
            return responseReader;
        }

        public int getHttpStatusCode() {
            return this.httpStatusCode;
        }

        protected void setHttpStatusCode(int i) {
            this.httpStatusCode = i;
        }

        public boolean isHttpStatusCodeOK() {
            return getHttpStatusCode() == 200;
        }

        public String getHttpStatus() {
            return this.httpStatus;
        }

        protected void setHttpStatus(String str) {
            this.httpStatus = str;
        }
    }

    public class WebRequestException extends Exception {
        private static final long serialVersionUID = -4980265484926465548L;
        private final WebRequestStatus status;

        protected WebRequestException(WebRequestStatus webRequestStatus, String str, Throwable th) {
            super(str, th);
            this.status = webRequestStatus;
        }

        protected WebRequestException(WebRequest webRequest, WebRequestStatus webRequestStatus, String str) {
            this(webRequestStatus, str, null);
        }

        public WebRequestStatus getStatus() {
            return this.status;
        }
    }

    public static class WebRequestFactory {
        public WebRequest createWebRequest() {
            return new HttpURLConnectionWebRequest();
        }

        public WebRequest createJSONGetWebRequest() {
            WebRequest webRequestCreateWebRequest = createWebRequest();
            webRequestCreateWebRequest.setHttpMethod(HttpMethod.GET);
            webRequestCreateWebRequest.putHeader("Accept", "application/json");
            return webRequestCreateWebRequest;
        }

        public WebRequest createJSONPostWebRequest() {
            WebRequest webRequestCreateWebRequest = createWebRequest();
            webRequestCreateWebRequest.convertToJSONPostRequest();
            return webRequestCreateWebRequest;
        }
    }

    static class QueryStringParameters {
        private final HashMap<String, String> params = new HashMap<>();
        private String rawAppendage;

        QueryStringParameters() {
        }

        int size() {
            return this.params.size();
        }

        void setRawAppendage(String str) {
            this.rawAppendage = str;
        }

        void putUrlEncoded(String str, boolean z) {
            putUrlEncoded(str, Boolean.toString(z));
        }

        void putUrlEncodedIfNotNullOrEmpty(String str, String str2) {
            putUrlEncodedIfTrue(str, str2, !StringUtils.isNullOrEmpty(str2));
        }

        void putUrlEncodedIfTrue(String str, String str2, boolean z) {
            if (z) {
                putUrlEncoded(str, str2);
            }
        }

        String get(String str) {
            if (StringUtils.isNullOrWhiteSpace(str)) {
                throw new IllegalArgumentException("The name must not be null or empty string.");
            }
            return this.params.get(str);
        }

        void putUrlEncoded(String str, String str2) {
            if (StringUtils.isNullOrWhiteSpace(str)) {
                throw new IllegalArgumentException("The name must not be null or empty string.");
            }
            if (str2 == null) {
                this.params.remove(str);
            } else {
                this.params.put(str, str2);
            }
        }

        String putUnencoded(String str, String str2) {
            WebUtils2 webUtils2 = new WebUtils2();
            String uRLEncodedString = webUtils2.getURLEncodedString(str);
            putUrlEncoded(uRLEncodedString, webUtils2.getURLEncodedString(str2));
            return uRLEncodedString;
        }

        void append(StringBuilder sb) {
            if (size() == 0 && StringUtils.isNullOrEmpty(this.rawAppendage)) {
                return;
            }
            sb.append("?");
            boolean z = true;
            for (Map.Entry<String, String> entry : this.params.entrySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(Constants.RequestParameters.AMPERSAND);
                }
                sb.append(entry.getKey());
                sb.append(Constants.RequestParameters.EQUAL);
                sb.append(entry.getValue());
            }
            String str = this.rawAppendage;
            if (str == null || str.equals("")) {
                return;
            }
            if (size() != 0) {
                sb.append(Constants.RequestParameters.AMPERSAND);
            }
            sb.append(this.rawAppendage);
        }
    }

    class WebRequestInputStream extends InputStream {
        private final InputStream decoratedStream;

        public WebRequestInputStream(InputStream inputStream) {
            this.decoratedStream = inputStream;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            return this.decoratedStream.read();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.decoratedStream.close();
            if (WebRequest.this.disconnectEnabled) {
                WebRequest.this.closeConnection();
            }
        }
    }
}
