package net.nend.android;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdNativeViewBinder {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private String h;
    private int i;

    private NendAdNativeViewBinder(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
    }

    public NendAdNativeViewHolder createViewHolder(View view) {
        return new NendAdNativeViewHolder(view, this);
    }

    public int getAdImageId() {
        return this.a;
    }

    public int getLogoImageId() {
        return this.b;
    }

    public int getTitleId() {
        return this.c;
    }

    public int getContentId() {
        return this.d;
    }

    public int getPromotionNameId() {
        return this.e;
    }

    public int getPromotionUrId() {
        return this.f;
    }

    public int getPrId() {
        return this.g;
    }

    public String getPrText() {
        return this.h;
    }

    public int getActionId() {
        return this.i;
    }

    public static class Builder {
        private int a;
        private int b;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private String h;
        private int i;

        public Builder adImageId(int i) {
            this.a = i;
            return this;
        }

        public Builder logoImageId(int i) {
            this.b = i;
            return this;
        }

        public Builder titleId(int i) {
            this.c = i;
            return this;
        }

        public Builder contentId(int i) {
            this.d = i;
            return this;
        }

        public Builder promotionNameId(int i) {
            this.e = i;
            return this;
        }

        public Builder promotionUrlId(int i) {
            this.f = i;
            return this;
        }

        public Builder prId(int i, NendAdNative.AdvertisingExplicitly advertisingExplicitly) {
            this.g = i;
            this.h = advertisingExplicitly.getText();
            return this;
        }

        public Builder actionId(int i) {
            this.i = i;
            return this;
        }

        public NendAdNativeViewBinder build() {
            return new NendAdNativeViewBinder(this);
        }
    }
}
