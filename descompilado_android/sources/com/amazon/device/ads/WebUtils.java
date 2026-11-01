package com.amazon.device.ads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.drive.DriveFile;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class WebUtils {
    private static final String LOGTAG = "WebUtils";
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    WebUtils() {
    }

    public static boolean launchActivityForIntentLink(String str, Context context) {
        if (str == null || str.equals("")) {
            str = "about:blank";
        }
        logger.d("Launch Intent: " + str);
        Intent intent = new Intent();
        if (str.startsWith("intent:")) {
            try {
                intent = Intent.parseUri(str, 1);
            } catch (URISyntaxException unused) {
                return false;
            }
        } else {
            intent.setData(Uri.parse(str));
        }
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(DriveFile.MODE_READ_ONLY);
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException unused2) {
            String action = intent.getAction();
            MobileAdsLogger mobileAdsLogger = logger;
            StringBuilder sb = new StringBuilder();
            sb.append("Could not handle ");
            sb.append(action.startsWith("market://") ? "market" : "intent");
            sb.append(" action: ");
            sb.append(action);
            mobileAdsLogger.w(sb.toString());
            return false;
        }
    }

    public static final String getURLEncodedString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLEncodedString threw: %s", e);
            return str;
        } catch (IllegalCharsetNameException e2) {
            logger.d("getURLEncodedString threw: %s", e2);
            return str;
        }
    }

    public static final String getURLDecodedString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLDecodedString threw: %s", e);
            return str;
        }
    }

    public static final String getScheme(String str) {
        String scheme = Uri.parse(str).getScheme();
        return scheme != null ? scheme.toLowerCase(Locale.US) : scheme;
    }

    public static final String encloseHtml(String str, boolean z) {
        if (str == null) {
            return str;
        }
        if (str.indexOf("<html>") == -1) {
            str = "<html>" + str + "</html>";
        }
        if (!z || str.indexOf("<!DOCTYPE html>") != -1) {
            return str;
        }
        return "<!DOCTYPE html>" + str;
    }

    public static final void executeWebRequestInThread(final String str, final boolean z) {
        ThreadUtils.scheduleRunnable(new Runnable() { // from class: com.amazon.device.ads.WebUtils.1
            @Override // java.lang.Runnable
            public void run() {
                WebRequest webRequestCreateWebRequest = new WebRequest.WebRequestFactory().createWebRequest();
                webRequestCreateWebRequest.enableLog(true);
                webRequestCreateWebRequest.setUrlString(str);
                webRequestCreateWebRequest.setDisconnectEnabled(z);
                try {
                    webRequestCreateWebRequest.makeCall();
                } catch (WebRequest.WebRequestException unused) {
                }
            }
        });
    }
}
