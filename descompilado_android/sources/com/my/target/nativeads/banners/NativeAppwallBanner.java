package com.my.target.nativeads.banners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import com.my.target.core.models.banners.j;

/* JADX INFO: loaded from: classes2.dex */
public class NativeAppwallBanner {
    private final boolean appInstalled;

    @Nullable
    private final ImageData bubbleIcon;

    @Nullable
    private final String bubbleId;
    private final int coins;

    @Nullable
    private final ImageData coinsIcon;
    private final int coinsIconBgColor;
    private final int coinsIconTextColor;

    @Nullable
    private final ImageData crossNotifIcon;

    @NonNull
    private final String description;

    @Nullable
    private final ImageData gotoAppIcon;
    private boolean hasNotification;

    @Nullable
    private final ImageData icon;

    @NonNull
    private final String id;
    private final boolean isBanner;
    private final boolean isItemHighlight;
    private final boolean isMain;
    private final boolean isRequireCategoryHighlight;
    private final boolean isRequireWifi;
    private final boolean isSubItem;

    @Nullable
    private final ImageData itemHighlightIcon;

    @Nullable
    private final ImageData labelIcon;

    @Nullable
    private final String labelType;
    private final int mrgsId;

    @Nullable
    private final String paidType;
    private final float rating;

    @Nullable
    private final String status;

    @Nullable
    private final ImageData statusIcon;

    @NonNull
    private final String title;
    private final int votes;

    @NonNull
    public static NativeAppwallBanner newBanner(@NonNull j jVar) {
        return new NativeAppwallBanner(jVar);
    }

    private NativeAppwallBanner(@NonNull j jVar) {
        this.id = jVar.getId();
        this.description = jVar.getDescription();
        this.title = jVar.getTitle();
        this.bubbleId = jVar.getBubbleId();
        this.labelType = jVar.getLabelType();
        this.status = jVar.getStatus();
        this.paidType = jVar.getPaidType();
        this.mrgsId = jVar.getMrgsId();
        this.coins = jVar.getCoins();
        this.coinsIconBgColor = jVar.getCoinsIconBgColor();
        this.coinsIconTextColor = jVar.getCoinsIconTextColor();
        this.votes = jVar.getVotes();
        this.rating = jVar.getRating();
        this.hasNotification = jVar.isHasNotification();
        this.isMain = jVar.isMain();
        this.isRequireCategoryHighlight = jVar.isRequireCategoryHighlight();
        this.isItemHighlight = jVar.isItemHighlight();
        this.isBanner = jVar.isBanner();
        this.isRequireWifi = jVar.isRequireWifi();
        this.isSubItem = jVar.isSubItem();
        this.appInstalled = jVar.isAppInstalled();
        this.icon = jVar.getIcon();
        this.coinsIcon = jVar.getCoinsIcon();
        this.labelIcon = jVar.getLabelIcon();
        this.gotoAppIcon = jVar.getGotoAppIcon();
        this.statusIcon = jVar.getStatusIcon();
        this.bubbleIcon = jVar.getBubbleIcon();
        this.itemHighlightIcon = jVar.getItemHighlightIcon();
        this.crossNotifIcon = jVar.getCrossNotifIcon();
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public String getDescription() {
        return this.description;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }

    @Nullable
    public String getBubbleId() {
        return this.bubbleId;
    }

    @Nullable
    public String getLabelType() {
        return this.labelType;
    }

    @Nullable
    public String getStatus() {
        return this.status;
    }

    @Nullable
    public String getPaidType() {
        return this.paidType;
    }

    public int getMrgsId() {
        return this.mrgsId;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getCoinsIconBgColor() {
        return this.coinsIconBgColor;
    }

    public int getCoinsIconTextColor() {
        return this.coinsIconTextColor;
    }

    public int getVotes() {
        return this.votes;
    }

    public float getRating() {
        return this.rating;
    }

    public boolean isHasNotification() {
        return this.hasNotification;
    }

    public boolean isMain() {
        return this.isMain;
    }

    public boolean isRequireCategoryHighlight() {
        return this.isRequireCategoryHighlight;
    }

    public boolean isItemHighlight() {
        return this.isItemHighlight;
    }

    public boolean isBanner() {
        return this.isBanner;
    }

    public boolean isRequireWifi() {
        return this.isRequireWifi;
    }

    public boolean isSubItem() {
        return this.isSubItem;
    }

    public boolean isAppInstalled() {
        return this.appInstalled;
    }

    @Nullable
    public ImageData getIcon() {
        return this.icon;
    }

    @Nullable
    public ImageData getCoinsIcon() {
        return this.coinsIcon;
    }

    @Nullable
    public ImageData getLabelIcon() {
        return this.labelIcon;
    }

    @Nullable
    public ImageData getGotoAppIcon() {
        return this.gotoAppIcon;
    }

    @Nullable
    public ImageData getStatusIcon() {
        return this.statusIcon;
    }

    @Nullable
    public ImageData getBubbleIcon() {
        return this.bubbleIcon;
    }

    @Nullable
    public ImageData getItemHighlightIcon() {
        return this.itemHighlightIcon;
    }

    @Nullable
    public ImageData getCrossNotifIcon() {
        return this.crossNotifIcon;
    }

    public void setHasNotification(boolean z) {
        this.hasNotification = z;
    }
}
