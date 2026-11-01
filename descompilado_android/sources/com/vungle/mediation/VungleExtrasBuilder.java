package com.vungle.mediation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import com.vungle.warren.AdConfig;

/* JADX INFO: loaded from: classes2.dex */
public final class VungleExtrasBuilder {
    static final String EXTRA_ALL_PLACEMENTS = "allPlacements";
    private static final String EXTRA_FLEXVIEW_CLOSE_TIME = "flexViewCloseTimeInSec";
    private static final String EXTRA_ORDINAL_VIEW_COUNT = "ordinalViewCount";
    static final String EXTRA_PLAY_PLACEMENT = "playPlacement";
    private static final String EXTRA_SOUND_ENABLED = "soundEnabled";
    static final String EXTRA_USER_ID = "userId";
    private final Bundle mBundle = new Bundle();

    public VungleExtrasBuilder(@NonNull @Size(min = 1) String[] strArr) {
        this.mBundle.putStringArray(EXTRA_ALL_PLACEMENTS, strArr);
    }

    public VungleExtrasBuilder setPlayingPlacement(@NonNull String str) {
        this.mBundle.putString(EXTRA_PLAY_PLACEMENT, str);
        return this;
    }

    public VungleExtrasBuilder setSoundEnabled(boolean z) {
        this.mBundle.putBoolean(EXTRA_SOUND_ENABLED, z);
        return this;
    }

    public VungleExtrasBuilder setUserId(String str) {
        this.mBundle.putString("userId", str);
        return this;
    }

    public VungleExtrasBuilder setFlexViewCloseTimeInSec(int i) {
        this.mBundle.putInt(EXTRA_FLEXVIEW_CLOSE_TIME, i);
        return this;
    }

    public VungleExtrasBuilder setOrdinalViewCount(int i) {
        this.mBundle.putInt(EXTRA_ORDINAL_VIEW_COUNT, i);
        return this;
    }

    public Bundle build() {
        return this.mBundle;
    }

    static AdConfig adConfigWithNetworkExtras(Bundle bundle) {
        AdConfig adConfig = new AdConfig();
        if (bundle != null) {
            adConfig.setMuted(true ^ bundle.getBoolean(EXTRA_SOUND_ENABLED, true));
            adConfig.setFlexViewCloseTime(bundle.getInt(EXTRA_FLEXVIEW_CLOSE_TIME, 0));
            adConfig.setOrdinal(bundle.getInt(EXTRA_ORDINAL_VIEW_COUNT, 0));
        }
        return adConfig;
    }
}
