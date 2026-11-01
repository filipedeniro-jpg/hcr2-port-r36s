package net.nend.android.internal.utilities;

import java.util.Locale;

/* JADX INFO: compiled from: NendStatus.java */
/* JADX INFO: loaded from: classes2.dex */
public enum k {
    SUCCESS(200, "Success!"),
    ERR_EXCESSIVE_AD_CALLS(332, "Excessive ad calls."),
    INITIAL(800, "Initial state"),
    ERR_INVALID_ATTRIBUTE_SET(811, "AttributeSet is invalid."),
    ERR_INVALID_API_KEY(812, "Api key is invalid."),
    ERR_INVALID_SPOT_ID(813, "Spot id is invalid."),
    ERR_INVALID_CONTEXT(814, "Context is invalid."),
    ERR_INVALID_URL(815, "Url is invalid."),
    ERR_INVALID_RESPONSE(814, "Response is invalid."),
    ERR_INVALID_AD_STATUS(815, "Ad status is invalid."),
    ERR_INVALID_RESPONSE_TYPE(816, "Response type is invalid."),
    ERR_INVALID_ICON_COUNT(817, "Icon count is invalid."),
    ERR_NO_AD_IMAGE(818, "This Ad has no Ad image."),
    ERR_NO_LOGO_IMAGE(819, "This Ad has no logo image"),
    ERR_HTTP_REQUEST(820, "Failed to http request."),
    ERR_FAILED_TO_PARSE(821, "Failed to parse response."),
    ERR_OUT_OF_STOCK(830, "Ad is out of stock."),
    ERR_VALIDATION_BINDER_SETTING(849, "There is a problem with the set in NendAdNativeBinder."),
    ERR_UNEXPECTED(899, "Unexpected error.");

    private final int t;
    private final String u;

    k(int i, String str) {
        this.t = i;
        this.u = str;
    }

    public int a() {
        return this.t;
    }

    public String b() {
        return String.format(Locale.US, "[%s%d] %s", "AE", Integer.valueOf(this.t), this.u);
    }

    public String a(String str) {
        return String.format(Locale.US, "[%s%d] %s %s", "AE", Integer.valueOf(this.t), this.u, str);
    }
}
