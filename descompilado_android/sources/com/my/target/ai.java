package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;

/* JADX INFO: compiled from: AdBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ai {
    public static final String DEFAULT_CTA_TEXT_STORE = "Install";
    public static final String DEFAULT_CTA_TEXT_WEB = "Visit";
    public static final boolean DEFAULT_OPEN_IN_BROWSER = false;
    public static final boolean DEFAULT_USE_DIRECT_LINK = false;
    public static final boolean DEFAULT_USE_PLAY_STORE_ACTION = false;

    @Nullable
    protected ae adChoices;

    @Nullable
    protected String bundleId;

    @Nullable
    protected String ctaText;

    @Nullable
    protected String deeplink;
    protected float duration;
    protected int height;

    @Nullable
    protected ImageData icon;

    @Nullable
    protected ImageData image;
    protected float rating;

    @Nullable
    protected String trackingLink;

    @Nullable
    protected String urlscheme;
    protected boolean usePlayStoreAction;
    protected int votes;
    protected int width;

    @NonNull
    private final ar statHolder = ar.aa();

    @NonNull
    protected String description = "";

    @NonNull
    protected String title = "";

    @NonNull
    protected String disclaimer = "";

    @NonNull
    protected String ageRestrictions = "";

    @NonNull
    protected String category = "";

    @NonNull
    protected String subCategory = "";

    @NonNull
    protected String domain = "";

    @NonNull
    protected String navigationType = "web";

    @NonNull
    protected String advertisingLabel = "";

    @NonNull
    protected ag clickArea = ag.cs;
    protected boolean openInBrowser = false;
    protected boolean directLink = false;

    @NonNull
    protected String type = "";

    @NonNull
    protected String id = "";

    /* JADX INFO: compiled from: AdBanner.java */
    public interface a {
        public static final String bw = "mraid";
        public static final String cV = "teaser";
        public static final String cW = "banner";
        public static final String cX = "promo";
        public static final String cY = "video";
        public static final String cZ = "audio";
        public static final String d = "fullscreen";
        public static final String da = "html";
        public static final String db = "companion";
        public static final String dc = "statistics";
        public static final String dd = "additionalData";
    }

    protected ai() {
    }

    public void setIcon(@Nullable ImageData imageData) {
        this.icon = imageData;
    }

    public void setImage(@Nullable ImageData imageData) {
        this.image = imageData;
    }

    public void setType(@NonNull String str) {
        this.type = str;
    }

    @NonNull
    public String getAgeRestrictions() {
        return this.ageRestrictions;
    }

    public void setAgeRestrictions(@NonNull String str) {
        this.ageRestrictions = str;
    }

    @Nullable
    public String getBundleId() {
        return this.bundleId;
    }

    public void setBundleId(@Nullable String str) {
        this.bundleId = str;
    }

    @NonNull
    public String getCategory() {
        return this.category;
    }

    public void setCategory(@NonNull String str) {
        this.category = str;
    }

    @NonNull
    public String getCtaText() {
        String str = this.ctaText;
        if (str == null) {
            return "store".equals(this.navigationType) ? DEFAULT_CTA_TEXT_STORE : DEFAULT_CTA_TEXT_WEB;
        }
        return str;
    }

    public void setCtaText(@NonNull String str) {
        this.ctaText = str;
    }

    @Nullable
    public String getDeeplink() {
        return this.deeplink;
    }

    public void setDeeplink(@Nullable String str) {
        this.deeplink = str;
    }

    @NonNull
    public String getDescription() {
        return this.description;
    }

    public void setDescription(@NonNull String str) {
        this.description = str;
    }

    @NonNull
    public String getDisclaimer() {
        return this.disclaimer;
    }

    public void setDisclaimer(@NonNull String str) {
        this.disclaimer = str;
    }

    @NonNull
    public String getDomain() {
        return this.domain;
    }

    public void setDomain(@NonNull String str) {
        this.domain = str;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    @Nullable
    public ImageData getIcon() {
        return this.icon;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    public void setId(@NonNull String str) {
        this.id = str;
    }

    @Nullable
    public ImageData getImage() {
        return this.image;
    }

    @NonNull
    public String getNavigationType() {
        return this.navigationType;
    }

    public void setNavigationType(@NonNull String str) {
        this.navigationType = str;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float f) {
        this.rating = f;
    }

    @NonNull
    public String getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(@NonNull String str) {
        this.subCategory = str;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }

    public void setTitle(@NonNull String str) {
        this.title = str;
    }

    @Nullable
    public String getTrackingLink() {
        return this.trackingLink;
    }

    public void setTrackingLink(@Nullable String str) {
        this.trackingLink = str;
    }

    @NonNull
    public String getType() {
        return this.type;
    }

    @Nullable
    public String getUrlscheme() {
        return this.urlscheme;
    }

    public void setUrlscheme(@Nullable String str) {
        this.urlscheme = str;
    }

    public int getVotes() {
        return this.votes;
    }

    public void setVotes(int i) {
        this.votes = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public boolean isDirectLink() {
        return this.directLink;
    }

    public void setDirectLink(boolean z) {
        this.directLink = z;
    }

    public boolean isOpenInBrowser() {
        return this.openInBrowser;
    }

    public void setOpenInBrowser(boolean z) {
        this.openInBrowser = z;
    }

    public boolean isUsePlayStoreAction() {
        return this.usePlayStoreAction;
    }

    public void setUsePlayStoreAction(boolean z) {
        this.usePlayStoreAction = z;
    }

    @NonNull
    public ar getStatHolder() {
        return this.statHolder;
    }

    public void setAdvertisingLabel(@NonNull String str) {
        this.advertisingLabel = str;
    }

    @NonNull
    public String getAdvertisingLabel() {
        return this.advertisingLabel;
    }

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public void setClickArea(@NonNull ag agVar) {
        this.clickArea = agVar;
    }

    @NonNull
    public ag getClickArea() {
        return this.clickArea;
    }

    public void setAdChoices(@Nullable ae aeVar) {
        this.adChoices = aeVar;
    }

    @Nullable
    public ae getAdChoices() {
        return this.adChoices;
    }
}
