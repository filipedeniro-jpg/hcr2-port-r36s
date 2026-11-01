package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class NativeAppInstallAdMapper extends NativeAdMapper {
    private String zzdkc;
    private String zzeie;
    private List<NativeAd.Image> zzeif;
    private String zzeih;
    private NativeAd.Image zzeij;
    private double zzeik;
    private String zzeil;
    private String zzeim;

    public final void setHeadline(String str) {
        this.zzeie = str;
    }

    public final void setImages(List<NativeAd.Image> list) {
        this.zzeif = list;
    }

    public final void setBody(String str) {
        this.zzdkc = str;
    }

    public final void setIcon(NativeAd.Image image) {
        this.zzeij = image;
    }

    public final void setCallToAction(String str) {
        this.zzeih = str;
    }

    public final void setStarRating(double d) {
        this.zzeik = d;
    }

    public final void setStore(String str) {
        this.zzeil = str;
    }

    public final void setPrice(String str) {
        this.zzeim = str;
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

    public final NativeAd.Image getIcon() {
        return this.zzeij;
    }

    public final String getCallToAction() {
        return this.zzeih;
    }

    public final double getStarRating() {
        return this.zzeik;
    }

    public final String getStore() {
        return this.zzeil;
    }

    public final String getPrice() {
        return this.zzeim;
    }
}
