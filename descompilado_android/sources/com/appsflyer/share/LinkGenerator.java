package com.appsflyer.share;

import android.content.Context;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.CreateOneLinkHttpTask;
import com.appsflyer.ServerConfigHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LinkGenerator {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private String f242;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private String f243;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private String f244;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private String f245;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private String f246;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private String f248;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private String f249;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private String f251;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private String f252;

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private String f253;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private String f254;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private Map<String, String> f247 = new HashMap();

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private Map<String, String> f250 = new HashMap();

    public LinkGenerator(String str) {
        this.f245 = str;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final LinkGenerator m206(String str) {
        this.f243 = str;
        return this;
    }

    public LinkGenerator setDeeplinkPath(String str) {
        this.f244 = str;
        return this;
    }

    public LinkGenerator setBaseDeeplink(String str) {
        this.f252 = str;
        return this;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final LinkGenerator m207(String str) {
        this.f242 = str;
        return this;
    }

    public LinkGenerator setChannel(String str) {
        this.f251 = str;
        return this;
    }

    public String getChannel() {
        return this.f251;
    }

    public LinkGenerator setReferrerCustomerId(String str) {
        this.f249 = str;
        return this;
    }

    public String getMediaSource() {
        return this.f245;
    }

    public Map<String, String> getParameters() {
        return this.f247;
    }

    public LinkGenerator setCampaign(String str) {
        this.f248 = str;
        return this;
    }

    public String getCampaign() {
        return this.f248;
    }

    public LinkGenerator addParameter(String str, String str2) {
        this.f247.put(str, str2);
        return this;
    }

    public LinkGenerator addParameters(Map<String, String> map) {
        if (map != null) {
            this.f247.putAll(map);
        }
        return this;
    }

    public LinkGenerator setReferrerUID(String str) {
        this.f246 = str;
        return this;
    }

    public LinkGenerator setReferrerName(String str) {
        this.f253 = str;
        return this;
    }

    public LinkGenerator setReferrerImageURL(String str) {
        this.f254 = str;
        return this;
    }

    public LinkGenerator setBaseURL(String str, String str2, String str3) {
        if (str == null || str.length() <= 0) {
            this.f242 = String.format(Constants.AF_BASE_URL_FORMAT, ServerConfigHandler.getUrl(Constants.APPSFLYER_DEFAULT_APP_DOMAIN), str3);
        } else {
            if (str2 == null || str2.length() < 5) {
                str2 = Constants.ONELINK_DEFAULT_DOMAIN;
            }
            this.f242 = String.format(Constants.AF_BASE_URL_FORMAT, str2, str);
        }
        return this;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private StringBuilder m204() {
        StringBuilder sb = new StringBuilder();
        String str = this.f242;
        if (str != null && str.startsWith("http")) {
            sb.append(this.f242);
        } else {
            sb.append(ServerConfigHandler.getUrl(Constants.BASE_URL_APP_APPSFLYER_COM));
        }
        if (this.f243 != null) {
            sb.append('/');
            sb.append(this.f243);
        }
        this.f250.put(Constants.URL_MEDIA_SOURCE, this.f245);
        sb.append('?');
        sb.append("pid=");
        sb.append(m205(this.f245, "media source"));
        String str2 = this.f246;
        if (str2 != null) {
            this.f250.put(Constants.URL_REFERRER_UID, str2);
            sb.append('&');
            sb.append("af_referrer_uid=");
            sb.append(m205(this.f246, "referrerUID"));
        }
        String str3 = this.f251;
        if (str3 != null) {
            this.f250.put("af_channel", str3);
            sb.append('&');
            sb.append("af_channel=");
            sb.append(m205(this.f251, AppsFlyerProperties.CHANNEL));
        }
        String str4 = this.f249;
        if (str4 != null) {
            this.f250.put(Constants.URL_REFERRER_CUSTOMER_ID, str4);
            sb.append('&');
            sb.append("af_referrer_customer_id=");
            sb.append(m205(this.f249, "referrerCustomerId"));
        }
        String str5 = this.f248;
        if (str5 != null) {
            this.f250.put(Constants.URL_CAMPAIGN, str5);
            sb.append('&');
            sb.append("c=");
            sb.append(m205(this.f248, FirebaseAnalytics.Param.CAMPAIGN));
        }
        String str6 = this.f253;
        if (str6 != null) {
            this.f250.put(Constants.URL_REFERRER_NAME, str6);
            sb.append('&');
            sb.append("af_referrer_name=");
            sb.append(m205(this.f253, "referrerName"));
        }
        String str7 = this.f254;
        if (str7 != null) {
            this.f250.put(Constants.URL_REFERRER_IMAGE_URL, str7);
            sb.append('&');
            sb.append("af_referrer_image_url=");
            sb.append(m205(this.f254, "referrerImageURL"));
        }
        if (this.f252 != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.f252);
            sb2.append(this.f252.endsWith(Constants.URL_PATH_DELIMITER) ? "" : Constants.URL_PATH_DELIMITER);
            String str8 = this.f244;
            if (str8 != null) {
                sb2.append(str8);
            }
            this.f250.put(Constants.URL_BASE_DEEPLINK, sb2.toString());
            sb.append('&');
            sb.append("af_dp=");
            sb.append(m205(this.f252, "baseDeeplink"));
            if (this.f244 != null) {
                sb.append(this.f252.endsWith(Constants.URL_PATH_DELIMITER) ? "" : "%2F");
                sb.append(m205(this.f244, "deeplinkPath"));
            }
        }
        for (String str9 : this.f247.keySet()) {
            String string = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str9);
            sb3.append(com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL);
            sb3.append(m205(this.f247.get(str9), str9));
            if (!string.contains(sb3.toString())) {
                sb.append('&');
                sb.append(str9);
                sb.append('=');
                sb.append(m205(this.f247.get(str9), str9));
            }
        }
        return sb;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static String m205(String str, String str2) {
        try {
            return URLEncoder.encode(str, "utf8");
        } catch (UnsupportedEncodingException unused) {
            StringBuilder sb = new StringBuilder("Illegal ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            AFLogger.afInfoLog(sb.toString());
            return "";
        } catch (Throwable unused2) {
            return "";
        }
    }

    public String generateLink() {
        return m204().toString();
    }

    public void generateLink(Context context, CreateOneLinkHttpTask.ResponseListener responseListener) {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
        if (!this.f247.isEmpty()) {
            for (Map.Entry<String, String> entry : this.f247.entrySet()) {
                this.f250.put(entry.getKey(), entry.getValue());
            }
        }
        m204();
        ShareInviteHelper.generateUserInviteLink(context, string, this.f250, responseListener);
    }
}
