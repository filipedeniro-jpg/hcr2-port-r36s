package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class NativeContentAdMapper extends NativeAdMapper {
    private String zzdkc;
    private String zzeie;
    private List<NativeAd.Image> zzeif;
    private NativeAd.Image zzeig;
    private String zzeih;
    private String zzeii;

    public final void setHeadline(String str) {
        this.zzeie = str;
    }

    public final void setImages(List<NativeAd.Image> list) {
        this.zzeif = list;
    }

    public final void setBody(String str) {
        this.zzdkc = str;
    }

    public final void setLogo(NativeAd.Image image) {
        this.zzeig = image;
    }

    public final void setCallToAction(String str) {
        this.zzeih = str;
    }

    public final void setAdvertiser(String str) {
        this.zzeii = str;
    }

    public final String getHeadline() {
        return this.zzeie;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzeif;
    }

    public final String getBody() {
        return this.zzdkc;
    }

    public final NativeAd.Image getLogo() {
        return this.zzeig;
    }

    public final String getCallToAction() {
        return this.zzeih;
    }

    public final String getAdvertiser() {
        return this.zzeii;
    }
}
