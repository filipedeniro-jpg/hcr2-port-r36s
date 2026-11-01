package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: CompanionBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public class aj extends ai {

    @Nullable
    private String adSlotID;

    @Nullable
    private String apiFramework;
    private int assetHeight;
    private int assetWidth;
    private int expandedHeight;
    private int expandedWidth;

    @Nullable
    private String htmlResource;

    @Nullable
    private String iframeResource;

    @Nullable
    private String required;

    @Nullable
    private String staticResource;

    @NonNull
    public static aj newBanner() {
        return new aj();
    }

    private aj() {
        this.type = ai.a.db;
    }

    public int getAssetWidth() {
        return this.assetWidth;
    }

    public void setAssetWidth(int i) {
        this.assetWidth = i;
    }

    public int getAssetHeight() {
        return this.assetHeight;
    }

    public void setAssetHeight(int i) {
        this.assetHeight = i;
    }

    public int getExpandedWidth() {
        return this.expandedWidth;
    }

    public void setExpandedWidth(int i) {
        this.expandedWidth = i;
    }

    public int getExpandedHeight() {
        return this.expandedHeight;
    }

    public void setExpandedHeight(int i) {
        this.expandedHeight = i;
    }

    @Nullable
    public String getStaticResource() {
        return this.staticResource;
    }

    public void setStaticResource(@Nullable String str) {
        this.staticResource = str;
    }

    @Nullable
    public String getIframeResource() {
        return this.iframeResource;
    }

    public void setIframeResource(@Nullable String str) {
        this.iframeResource = str;
    }

    @Nullable
    public String getHtmlResource() {
        return this.htmlResource;
    }

    public void setHtmlResource(@Nullable String str) {
        this.htmlResource = str;
    }

    @Nullable
    public String getApiFramework() {
        return this.apiFramework;
    }

    public void setApiFramework(@Nullable String str) {
        this.apiFramework = str;
    }

    @Nullable
    public String getAdSlotID() {
        return this.adSlotID;
    }

    public void setAdSlotID(@Nullable String str) {
        this.adSlotID = str;
    }

    @Nullable
    public String getRequired() {
        return this.required;
    }

    public void setRequired(@Nullable String str) {
        this.required = str;
    }
}
