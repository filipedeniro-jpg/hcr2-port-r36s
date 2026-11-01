package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class UnifiedNativeAdMapper {
    private Bundle extras = new Bundle();
    private VideoController zzceg;
    private String zzdkc;
    private View zzeic;
    private boolean zzeid;
    private String zzeie;
    private List<NativeAd.Image> zzeif;
    private String zzeih;
    private String zzeii;
    private NativeAd.Image zzeij;
    private String zzeil;
    private String zzeim;
    private Double zzeiq;
    private View zzeir;
    private Object zzeis;
    private boolean zzeit;
    private boolean zzeiu;
    private float zzeiv;

    public void handleClick(View view) {
    }

    public void recordImpression() {
    }

    public void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
    }

    public void untrackView(View view) {
    }

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

    public final void setAdvertiser(String str) {
        this.zzeii = str;
    }

    public final void setStarRating(Double d) {
        this.zzeiq = d;
    }

    public final void setStore(String str) {
        this.zzeil = str;
    }

    public final void setPrice(String str) {
        this.zzeim = str;
    }

    public final void zza(VideoController videoController) {
        this.zzceg = videoController;
    }

    public void setHasVideoContent(boolean z) {
        this.zzeid = z;
    }

    public void setAdChoicesContent(View view) {
        this.zzeir = view;
    }

    public void setMediaView(View view) {
        this.zzeic = view;
    }

    public void setMediaContentAspectRatio(float f) {
        this.zzeiv = f;
    }

    public final void zzn(Object obj) {
        this.zzeis = obj;
    }

    public final void setExtras(Bundle bundle) {
        this.extras = bundle;
    }

    public final void setOverrideImpressionRecording(boolean z) {
        this.zzeit = z;
    }

    public final void setOverrideClickHandling(boolean z) {
        this.zzeiu = z;
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

    public final String getAdvertiser() {
        return this.zzeii;
    }

    public final Double getStarRating() {
        return this.zzeiq;
    }

    public final String getStore() {
        return this.zzeil;
    }

    public final String getPrice() {
        return this.zzeim;
    }

    public final VideoController getVideoController() {
        return this.zzceg;
    }

    public boolean hasVideoContent() {
        return this.zzeid;
    }

    public View getAdChoicesContent() {
        return this.zzeir;
    }

    public final View zzaaw() {
        return this.zzeic;
    }

    public float getMediaContentAspectRatio() {
        return this.zzeiv;
    }

    public final Object zzjf() {
        return this.zzeis;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzeit;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzeiu;
    }
}
