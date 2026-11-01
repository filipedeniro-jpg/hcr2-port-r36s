package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ai;
import com.my.target.common.models.ImageData;

/* JADX INFO: compiled from: NativeAppwallAdBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j extends ai {
    private boolean appInstalled;

    @Nullable
    private ImageData bubbleIcon;

    @Nullable
    private String bubbleId;
    private int coins;

    @Nullable
    private ImageData coinsIcon;
    private int coinsIconBgColor = -552418;
    private int coinsIconTextColor = -1;

    @Nullable
    private ImageData crossNotifIcon;

    @Nullable
    private ImageData gotoAppIcon;
    private boolean hasNotification;
    private boolean isBanner;
    private boolean isItemHighlight;
    private boolean isMain;
    private boolean isRequireCategoryHighlight;
    private boolean isRequireWifi;
    private boolean isSubItem;

    @Nullable
    private ImageData itemHighlightIcon;

    @Nullable
    private ImageData labelIcon;

    @Nullable
    private String labelType;
    private int mrgsId;

    @Nullable
    private String paidType;

    @Nullable
    private String status;

    @Nullable
    private ImageData statusIcon;

    @NonNull
    public static j newBanner() {
        return new j();
    }

    private j() {
    }

    public final void setAppInstalled(boolean z) {
        this.appInstalled = z;
    }

    public final boolean isAppInstalled() {
        return this.appInstalled;
    }

    public final void setHasNotification(boolean z) {
        this.hasNotification = z;
    }

    public final boolean isHasNotification() {
        return this.hasNotification;
    }

    @Nullable
    public final ImageData getCoinsIcon() {
        return this.coinsIcon;
    }

    public final void setCoinsIcon(@Nullable ImageData imageData) {
        this.coinsIcon = imageData;
    }

    @Nullable
    public final ImageData getLabelIcon() {
        return this.labelIcon;
    }

    public final void setLabelIcon(@Nullable ImageData imageData) {
        this.labelIcon = imageData;
    }

    @Nullable
    public final ImageData getGotoAppIcon() {
        return this.gotoAppIcon;
    }

    public final void setGotoAppIcon(@Nullable ImageData imageData) {
        this.gotoAppIcon = imageData;
    }

    @Nullable
    public final ImageData getStatusIcon() {
        return this.statusIcon;
    }

    public final void setStatusIcon(@Nullable ImageData imageData) {
        this.statusIcon = imageData;
    }

    @Nullable
    public final ImageData getBubbleIcon() {
        return this.bubbleIcon;
    }

    public final void setBubbleIcon(@Nullable ImageData imageData) {
        this.bubbleIcon = imageData;
    }

    @Nullable
    public final ImageData getItemHighlightIcon() {
        return this.itemHighlightIcon;
    }

    public final void setItemHighlightIcon(@Nullable ImageData imageData) {
        this.itemHighlightIcon = imageData;
    }

    @Nullable
    public final ImageData getCrossNotifIcon() {
        return this.crossNotifIcon;
    }

    public final void setCrossNotifIcon(@Nullable ImageData imageData) {
        this.crossNotifIcon = imageData;
    }

    @Nullable
    public final String getBubbleId() {
        return this.bubbleId;
    }

    public final void setBubbleId(@Nullable String str) {
        this.bubbleId = str;
    }

    @Nullable
    public final String getLabelType() {
        return this.labelType;
    }

    public final void setLabelType(@Nullable String str) {
        this.labelType = str;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }

    @Nullable
    public final String getPaidType() {
        return this.paidType;
    }

    public final void setPaidType(@Nullable String str) {
        this.paidType = str;
    }

    public final int getMrgsId() {
        return this.mrgsId;
    }

    public final void setMrgsId(int i) {
        this.mrgsId = i;
    }

    public final int getCoins() {
        return this.coins;
    }

    public final void setCoins(int i) {
        this.coins = i;
    }

    public final int getCoinsIconBgColor() {
        return this.coinsIconBgColor;
    }

    public final void setCoinsIconBgColor(int i) {
        this.coinsIconBgColor = i;
    }

    public final int getCoinsIconTextColor() {
        return this.coinsIconTextColor;
    }

    public final void setCoinsIconTextColor(int i) {
        this.coinsIconTextColor = i;
    }

    public final boolean isMain() {
        return this.isMain;
    }

    public final void setMain(boolean z) {
        this.isMain = z;
    }

    public final boolean isRequireCategoryHighlight() {
        return this.isRequireCategoryHighlight;
    }

    public final void setRequireCategoryHighlight(boolean z) {
        this.isRequireCategoryHighlight = z;
    }

    public final boolean isItemHighlight() {
        return this.isItemHighlight;
    }

    public final void setItemHighlight(boolean z) {
        this.isItemHighlight = z;
    }

    public final boolean isBanner() {
        return this.isBanner;
    }

    public final void setBanner(boolean z) {
        this.isBanner = z;
    }

    public final boolean isRequireWifi() {
        return this.isRequireWifi;
    }

    public final void setRequireWifi(boolean z) {
        this.isRequireWifi = z;
    }

    public final boolean isSubItem() {
        return this.isSubItem;
    }

    public final void setSubItem(boolean z) {
        this.isSubItem = z;
    }
}
