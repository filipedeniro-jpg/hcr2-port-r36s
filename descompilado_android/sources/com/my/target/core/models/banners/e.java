package com.my.target.core.models.banners;

import androidx.annotation.Nullable;
import com.my.target.ag;
import com.my.target.ai;
import com.my.target.common.models.ImageData;

/* JADX INFO: compiled from: InterstitialAdBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class e extends ai {
    private float allowCloseDelay;

    @Nullable
    private ImageData closeIcon;
    private boolean allowClose = true;
    private boolean closeOnClick = true;

    e() {
        this.clickArea = ag.ct;
    }

    @Nullable
    public ImageData getCloseIcon() {
        return this.closeIcon;
    }

    public void setCloseIcon(@Nullable ImageData imageData) {
        this.closeIcon = imageData;
    }

    public float getAllowCloseDelay() {
        return this.allowCloseDelay;
    }

    public void setAllowCloseDelay(float f) {
        this.allowCloseDelay = f;
    }

    public void setAllowClose(boolean z) {
        this.allowClose = z;
    }

    public boolean isAllowClose() {
        return this.allowClose;
    }

    public void setCloseOnClick(boolean z) {
        this.closeOnClick = z;
    }

    public boolean isCloseOnClick() {
        return this.closeOnClick;
    }
}
