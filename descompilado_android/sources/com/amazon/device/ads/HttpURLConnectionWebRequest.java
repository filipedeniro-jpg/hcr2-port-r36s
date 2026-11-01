package com.amazon.device.ads;

import com.ironsource.sdk.constants.Constants;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class HttpURLConnectionWebRequest extends WebRequest {
    private static final String LOGTAG = "HttpURLConnectionWebRequest";
    private HttpURLConnection connection;

    HttpURLConnectionWebRequest() {
    }

    @Override // com.amazon.device.ads.WebRequest
    protected WebRequest.WebResponse doHttpNetworkCall(URL url) throws Throwable {
        if (this.connection != null) {
            closeConnection();
        }
        try {
            this.connection = openConnection(url);
            setupRequestProperties(this.connection);
            try {
                this.connection.connect();
                return prepareResponse(this.connection);
            } catch (SocketTimeoutException e) {
                getLogger().e("Socket timed out while connecting to URL: %s", e.getMessage());
                throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_TIMEOUT, "Socket timed out while connecting to URL", e);
            } catch (Exception e2) {
                getLogger().e("Problem while connecting to URL: %s", e2.getMessage());
                throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Probem while connecting to URL", e2);
            }
        } catch (IOException e3) {
            getLogger().e("Problem while opening the URL connection: %s", e3.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while opening the URL connection", e3);
        }
    }

    protected HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    @Override // com.amazon.device.ads.WebRequest
    protected void closeConnection() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            this.connection = null;
        }
    }

    protected void setupRequestProperties(HttpURLConnection httpURLConnection) throws Throwable {
        try {
            httpURLConnection.setRequestMethod(getHttpMethod().name());
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().equals("")) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpURLConnection.setConnectTimeout(getTimeout());
            httpURLConnection.setReadTimeout(getTimeout());
            logUrl(httpURLConnection.getURL().toString());
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod[getHttpMethod().ordinal()];
            if (i == 1) {
                httpURLConnection.setDoOutput(false);
            } else {
                if (i != 2) {
                    return;
                }
                httpURLConnection.setDoOutput(true);
                writePostBody(httpURLConnection);
            }
        } catch (ProtocolException e) {
            getLogger().e("Invalid client protocol: %s", e.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.INVALID_CLIENT_PROTOCOL, "Invalid client protocol", e);
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.HttpURLConnectionWebRequest$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod = new int[WebRequest.HttpMethod.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod[WebRequest.HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod[WebRequest.HttpMethod.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void writePostBody(HttpURLConnection httpURLConnection) throws Throwable {
        StringBuilder sb = new StringBuilder();
        if (this.requestBody != null) {
            sb.append(this.requestBody);
        } else if (this.postParameters != null && !this.postParameters.isEmpty()) {
            for (Map.Entry<String, String> entry : this.postParameters.entrySet()) {
                sb.append(entry.getKey());
                sb.append(Constants.RequestParameters.EQUAL);
                sb.append(WebUtils.getURLEncodedString(entry.getValue()));
                sb.append(Constants.RequestParameters.AMPERSAND);
            }
            sb.deleteCharAt(sb.lastIndexOf(Constants.RequestParameters.AMPERSAND));
        }
        if (this.logRequestBodyEnabled && getRequestBody() != null) {
            getLogger().d("Request Body: %s", !this.logSessionIdEnabled ? getRequestBody().replaceAll(",\\s*\"\\s*sessionId\\s*\"\\s*:\\s*\".*?\"|\\s*\"\\s*sessionId\\s*\"\\s*:\\s*\".*?\"\\s*,*", "") : getRequestBody());
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                try {
                    outputStreamWriter2.write(sb.toString());
                    try {
                        outputStreamWriter2.close();
                    } catch (IOException e) {
                        getLogger().e("Problem while closing output stream writer for request body: %s", e.getMessage());
                        throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while closing output stream writer for request body", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    getLogger().e("Problem while creating output steam for request body: %s", e.getMessage());
                    throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while creating output steam for request body", e);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e3) {
                            getLogger().e("Problem while closing output stream writer for request body: %s", e3.getMessage());
                            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while closing output stream writer for request body", e3);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    protected WebRequest.WebResponse prepareResponse(HttpURLConnection httpURLConnection) throws WebRequest.WebRequestException {
        WebRequest.WebResponse webResponse = new WebRequest.WebResponse();
        try {
            webResponse.setHttpStatusCode(httpURLConnection.getResponseCode());
            webResponse.setHttpStatus(httpURLConnection.getResponseMessage());
            if (webResponse.getHttpStatusCode() == 200) {
                try {
                    webResponse.setInputStream(httpURLConnection.getInputStream());
                } catch (IOException e) {
                    getLogger().e("IOException while reading the input stream from response: %s", e.getMessage());
                    throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException while reading the input stream from response", e);
                }
            }
            return webResponse;
        } catch (IOException e2) {
            getLogger().e("IOException while getting the response status code: %s", e2.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException while getting the response status code", e2);
        } catch (IndexOutOfBoundsException e3) {
            getLogger().e("IndexOutOfBoundsException while getting the response status code: %s", e3.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.MALFORMED_URL, "IndexOutOfBoundsException while getting the response status code", e3);
        } catch (SocketTimeoutException e4) {
            getLogger().e("Socket Timeout while getting the response status code: %s", e4.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_TIMEOUT, "Socket Timeout while getting the response status code", e4);
        }
    }

    @Override // com.amazon.device.ads.WebRequest
    protected String getSubLogTag() {
        return LOGTAG;
    }
}
