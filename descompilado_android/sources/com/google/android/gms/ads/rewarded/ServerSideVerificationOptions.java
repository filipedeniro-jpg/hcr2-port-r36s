package com.google.android.gms.ads.rewarded;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class ServerSideVerificationOptions {
    private final String zzdnn;
    private final String zzdno;

    private ServerSideVerificationOptions(Builder builder) {
        this.zzdnn = builder.zzdnn;
        this.zzdno = builder.zzdno;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static final class Builder {
        private String zzdnn = "";
        private String zzdno = "";

        public final Builder setUserId(String str) {
            this.zzdnn = str;
            return this;
        }

        public final Builder setCustomData(String str) {
            this.zzdno = str;
            return this;
        }

        public final ServerSideVerificationOptions build() {
            return new ServerSideVerificationOptions(this);
        }
    }

    public String getUserId() {
        return this.zzdnn;
    }

    public String getCustomData() {
        return this.zzdno;
    }
}
