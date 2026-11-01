package com.applovin.impl.sdk.ad;

import android.content.Context;
import android.net.Uri;
import com.applovin.impl.sdk.utils.n;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class NativeAdImpl implements j, AppLovinNativeAd {
    public static final String QUERY_PARAM_IS_FIRST_PLAY = "fp";
    public static final String QUERY_PARAM_VIDEO_PERCENT_VIEWED = "pv";
    private final com.applovin.impl.sdk.h a;
    private final d b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final List<com.applovin.impl.sdk.c.a> p;
    private final List<com.applovin.impl.sdk.c.a> q;
    private final String r;
    private final long s;
    private final List<String> t;
    private String u;
    private String v;
    private float w;
    private String x;
    private AtomicBoolean y;

    public static class a {
        private d a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private float l;
        private String m;
        private String n;
        private String o;
        private String p;
        private String q;
        private List<com.applovin.impl.sdk.c.a> r;
        private List<com.applovin.impl.sdk.c.a> s;
        private String t;
        private String u;
        private long v;
        private List<String> w;
        private com.applovin.impl.sdk.h x;

        public a a(float f) {
            this.l = f;
            return this;
        }

        public a a(long j) {
            this.v = j;
            return this;
        }

        public a a(d dVar) {
            this.a = dVar;
            return this;
        }

        public a a(com.applovin.impl.sdk.h hVar) {
            this.x = hVar;
            return this;
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public a a(List<com.applovin.impl.sdk.c.a> list) {
            this.r = list;
            return this;
        }

        public NativeAdImpl a() {
            return new NativeAdImpl(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x);
        }

        public a b(String str) {
            this.d = str;
            return this;
        }

        public a b(List<com.applovin.impl.sdk.c.a> list) {
            this.s = list;
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }

        public a c(List<String> list) {
            this.w = list;
            return this;
        }

        public a d(String str) {
            this.f = str;
            return this;
        }

        public a e(String str) {
            this.b = str;
            return this;
        }

        public a f(String str) {
            this.g = str;
            return this;
        }

        public a g(String str) {
            this.h = str;
            return this;
        }

        public a h(String str) {
            this.i = str;
            return this;
        }

        public a i(String str) {
            this.j = str;
            return this;
        }

        public a j(String str) {
            this.k = str;
            return this;
        }

        public a k(String str) {
            this.m = str;
            return this;
        }

        public a l(String str) {
            this.n = str;
            return this;
        }

        public a m(String str) {
            this.o = str;
            return this;
        }

        public a n(String str) {
            this.p = str;
            return this;
        }

        public a o(String str) {
            this.q = str;
            return this;
        }

        public a p(String str) {
            this.t = str;
            return this;
        }

        public a q(String str) {
            this.u = str;
            return this;
        }
    }

    private NativeAdImpl(d dVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, float f, String str11, String str12, String str13, String str14, String str15, List<com.applovin.impl.sdk.c.a> list, List<com.applovin.impl.sdk.c.a> list2, String str16, String str17, long j, List<String> list3, com.applovin.impl.sdk.h hVar) {
        this.y = new AtomicBoolean();
        this.b = dVar;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.u = str9;
        this.v = str10;
        this.w = f;
        this.x = str11;
        this.l = str12;
        this.m = str13;
        this.n = str14;
        this.o = str15;
        this.p = list;
        this.q = list2;
        this.r = str16;
        this.k = str17;
        this.s = j;
        this.t = list3;
        this.a = hVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NativeAdImpl nativeAdImpl = (NativeAdImpl) obj;
        d dVar = this.b;
        if (dVar == null ? nativeAdImpl.b != null : !dVar.equals(nativeAdImpl.b)) {
            return false;
        }
        String str = this.j;
        if (str == null ? nativeAdImpl.j != null : !str.equals(nativeAdImpl.j)) {
            return false;
        }
        String str2 = this.r;
        if (str2 == null ? nativeAdImpl.r != null : !str2.equals(nativeAdImpl.r)) {
            return false;
        }
        String str3 = this.l;
        if (str3 == null ? nativeAdImpl.l != null : !str3.equals(nativeAdImpl.l)) {
            return false;
        }
        String str4 = this.k;
        if (str4 == null ? nativeAdImpl.k != null : !str4.equals(nativeAdImpl.k)) {
            return false;
        }
        String str5 = this.i;
        if (str5 == null ? nativeAdImpl.i != null : !str5.equals(nativeAdImpl.i)) {
            return false;
        }
        String str6 = this.m;
        if (str6 == null ? nativeAdImpl.m != null : !str6.equals(nativeAdImpl.m)) {
            return false;
        }
        String str7 = this.d;
        if (str7 == null ? nativeAdImpl.d != null : !str7.equals(nativeAdImpl.d)) {
            return false;
        }
        String str8 = this.e;
        if (str8 == null ? nativeAdImpl.e != null : !str8.equals(nativeAdImpl.e)) {
            return false;
        }
        String str9 = this.f;
        if (str9 == null ? nativeAdImpl.f != null : !str9.equals(nativeAdImpl.f)) {
            return false;
        }
        String str10 = this.g;
        if (str10 == null ? nativeAdImpl.g != null : !str10.equals(nativeAdImpl.g)) {
            return false;
        }
        String str11 = this.h;
        if (str11 == null ? nativeAdImpl.h != null : !str11.equals(nativeAdImpl.h)) {
            return false;
        }
        String str12 = this.o;
        if (str12 == null ? nativeAdImpl.o != null : !str12.equals(nativeAdImpl.o)) {
            return false;
        }
        String str13 = this.n;
        if (str13 == null ? nativeAdImpl.n != null : !str13.equals(nativeAdImpl.n)) {
            return false;
        }
        List<com.applovin.impl.sdk.c.a> list = this.p;
        if (list == null ? nativeAdImpl.p != null : !list.equals(nativeAdImpl.p)) {
            return false;
        }
        List<com.applovin.impl.sdk.c.a> list2 = this.q;
        if (list2 == null ? nativeAdImpl.q != null : !list2.equals(nativeAdImpl.q)) {
            return false;
        }
        List<String> list3 = this.t;
        return list3 == null ? nativeAdImpl.t == null : list3.equals(nativeAdImpl.t);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public long getAdId() {
        return this.s;
    }

    public d getAdZone() {
        return this.b;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getCaptionText() {
        return this.j;
    }

    public String getClCode() {
        return this.r;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getClickUrl() {
        return this.l;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getCtaText() {
        return this.k;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getDescriptionText() {
        return this.i;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getIconUrl() {
        return this.u;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getImageUrl() {
        return this.v;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getImpressionTrackingUrl() {
        return this.m;
    }

    public List<String> getResourcePrefixes() {
        return this.t;
    }

    public String getSourceIconUrl() {
        return this.d;
    }

    public String getSourceImageUrl() {
        return this.e;
    }

    public String getSourceStarRatingImageUrl() {
        return this.f;
    }

    public String getSourceVideoUrl() {
        return this.g;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public float getStarRating() {
        return this.w;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getTitle() {
        return this.h;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getVideoEndTrackingUrl(int i, boolean z) {
        Uri uriBuild;
        if (this.o == null) {
            uriBuild = Uri.EMPTY;
        } else {
            if (i < 0 || i > 100) {
                this.a.w().c("AppLovinNativeAd", "Invalid percent viewed supplied.", new IllegalArgumentException("Percent viewed must be an integer between 0 and 100."));
            }
            uriBuild = Uri.parse(this.o).buildUpon().appendQueryParameter(QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).appendQueryParameter(QUERY_PARAM_IS_FIRST_PLAY, Boolean.toString(z)).build();
        }
        return uriBuild.toString();
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getVideoStartTrackingUrl() {
        return this.n;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getVideoUrl() {
        return this.x;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public String getZoneId() {
        return this.c;
    }

    public int hashCode() {
        String str = this.d;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.e;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.g;
        int iHashCode4 = (iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.h;
        int iHashCode5 = (iHashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.i;
        int iHashCode6 = (iHashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.j;
        int iHashCode7 = (iHashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.k;
        int iHashCode8 = (iHashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.l;
        int iHashCode9 = (iHashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.m;
        int iHashCode10 = (iHashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.n;
        int iHashCode11 = (iHashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.o;
        int iHashCode12 = (iHashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        List<com.applovin.impl.sdk.c.a> list = this.p;
        int iHashCode13 = (iHashCode12 + (list != null ? list.hashCode() : 0)) * 31;
        List<com.applovin.impl.sdk.c.a> list2 = this.q;
        int iHashCode14 = (iHashCode13 + (list2 != null ? list2.hashCode() : 0)) * 31;
        String str13 = this.r;
        int iHashCode15 = (iHashCode14 + (str13 != null ? str13.hashCode() : 0)) * 31;
        d dVar = this.b;
        int iHashCode16 = (iHashCode15 + (dVar != null ? dVar.hashCode() : 0)) * 31;
        List<String> list3 = this.t;
        return iHashCode16 + (list3 != null ? list3.hashCode() : 0);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public boolean isImagePrecached() {
        String str = this.u;
        boolean z = (str == null || str.equals(this.d)) ? false : true;
        String str2 = this.v;
        return z && (str2 != null && !str2.equals(this.e));
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public boolean isVideoPrecached() {
        String str = this.x;
        return (str == null || str.equals(this.g)) ? false : true;
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public void launchClickTarget(Context context) {
        for (com.applovin.impl.sdk.c.a aVar : this.q) {
            this.a.I().a(com.applovin.impl.sdk.network.e.j().a(aVar.a()).b(aVar.b()).a(false).a());
        }
        n.a(context, Uri.parse(this.l), this.a);
    }

    public void setIconUrl(String str) {
        this.u = str;
    }

    public void setImageUrl(String str) {
        this.v = str;
    }

    public void setStarRating(float f) {
        this.w = f;
    }

    public void setVideoUrl(String str) {
        this.x = str;
    }

    public String toString() {
        return "AppLovinNativeAd{clCode='" + this.r + "', adZone='" + this.b + "', sourceIconUrl='" + this.d + "', sourceImageUrl='" + this.e + "', sourceStarRatingImageUrl='" + this.f + "', sourceVideoUrl='" + this.g + "', title='" + this.h + "', descriptionText='" + this.i + "', captionText='" + this.j + "', ctaText='" + this.k + "', iconUrl='" + this.u + "', imageUrl='" + this.v + "', starRating='" + this.w + "', videoUrl='" + this.x + "', clickUrl='" + this.l + "', impressionTrackingUrl='" + this.m + "', videoStartTrackingUrl='" + this.n + "', videoEndTrackingUrl='" + this.o + "', impressionPostbacks=" + this.p + "', clickTrackingPostbacks=" + this.q + "', resourcePrefixes=" + this.t + '}';
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public void trackImpression() {
        trackImpression(null);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAd
    public void trackImpression(AppLovinPostbackListener appLovinPostbackListener) {
        if (this.y.getAndSet(true)) {
            if (appLovinPostbackListener != null) {
                appLovinPostbackListener.onPostbackFailure(this.m, AppLovinErrorCodes.NATIVE_AD_IMPRESSION_ALREADY_TRACKED);
            }
        } else {
            this.a.w().a("AppLovinNativeAd", "Tracking impression...");
            for (com.applovin.impl.sdk.c.a aVar : this.p) {
                this.a.M().dispatchPostbackRequest(com.applovin.impl.sdk.network.f.b(this.a).a(aVar.a()).c(aVar.b()).a(false).a(), appLovinPostbackListener);
            }
        }
    }
}
