package com.my.target.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.b;
import com.my.target.common.CustomParams;
import com.my.target.df;
import com.my.target.g;

/* JADX INFO: loaded from: classes2.dex */
public final class MyTargetView extends RelativeLayout {

    @Nullable
    private b adConfig;
    private boolean attached;

    @Nullable
    private com.my.target.core.engines.b engine;

    @Nullable
    private MyTargetViewListener listener;
    private boolean trackingEnvironmentEnabled;
    private boolean trackingLocationEnabled;

    public interface AdSize {
        public static final int BANNER_300x250 = 1;
        public static final int BANNER_320x50 = 0;
        public static final int BANNER_728x90 = 2;
    }

    public interface MyTargetViewListener {
        void onClick(@NonNull MyTargetView myTargetView);

        void onLoad(@NonNull MyTargetView myTargetView);

        void onNoAd(@NonNull String str, @NonNull MyTargetView myTargetView);
    }

    public static void setDebugMode(boolean z) {
        g.enabled = z;
        if (z) {
            g.a("Debug mode enabled");
        }
    }

    @Nullable
    public final MyTargetViewListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable MyTargetViewListener myTargetViewListener) {
        this.listener = myTargetViewListener;
    }

    public final boolean isTrackingEnvironmentEnabled() {
        return this.trackingEnvironmentEnabled;
    }

    public final void setTrackingEnvironmentEnabled(boolean z) {
        this.trackingEnvironmentEnabled = z;
        b bVar = this.adConfig;
        if (bVar != null) {
            bVar.setTrackingEnvironmentEnabled(z);
        }
    }

    public final void setTrackingLocationEnabled(boolean z) {
        this.trackingLocationEnabled = z;
        b bVar = this.adConfig;
        if (bVar != null) {
            bVar.setTrackingLocationEnabled(z);
        }
    }

    public final boolean isTrackingLocationEnabled() {
        return this.trackingLocationEnabled;
    }

    @Nullable
    public final CustomParams getCustomParams() {
        b bVar = this.adConfig;
        if (bVar != null) {
            return bVar.getCustomParams();
        }
        return null;
    }

    public MyTargetView(@NonNull Context context) {
        super(context);
        this.trackingEnvironmentEnabled = true;
        this.trackingLocationEnabled = true;
        g.c("MyTargetView created. Version: 5.3.9");
    }

    public MyTargetView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.trackingEnvironmentEnabled = true;
        this.trackingLocationEnabled = true;
        g.c("MyTargetView created. Version: 5.3.9");
    }

    public MyTargetView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.trackingEnvironmentEnabled = true;
        this.trackingLocationEnabled = true;
        g.c("MyTargetView created. Version: 5.3.9");
    }

    public final void init(int i) {
        init(i, true);
    }

    public final void init(int i, boolean z) {
        init(i, 0, z);
    }

    public final void init(int i, int i2) {
        init(i, i2, true);
    }

    public final void init(int i, int i2, boolean z) {
        String str;
        if (this.adConfig != null) {
            return;
        }
        if (i2 == 1) {
            str = b.a.b;
        } else {
            str = i2 == 2 ? b.a.c : b.a.a;
        }
        this.adConfig = b.newConfig(i, str);
        this.adConfig.setTrackingEnvironmentEnabled(this.trackingEnvironmentEnabled);
        this.adConfig.setTrackingLocationEnabled(this.trackingLocationEnabled);
        this.adConfig.setRefreshAd(z);
        g.a("MyTargetView initialized");
    }

    public final void load() {
        b bVar = this.adConfig;
        if (bVar != null) {
            df.newFactory(bVar).a(new df.a() { // from class: com.my.target.ads.MyTargetView.1
                @Override // com.my.target.c.b
                /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
                public void b(@Nullable com.my.target.core.models.sections.b bVar2, @Nullable String str) {
                    MyTargetView.this.handleResult(bVar2, str);
                }
            }).a(getContext());
        } else {
            g.a("MyTargetView not initialized");
        }
    }

    public final void loadFromBid(@NonNull String str) {
        b bVar = this.adConfig;
        if (bVar == null) {
            g.a("MyTargetView not initialized");
            return;
        }
        bVar.setBidId(str);
        this.adConfig.setRefreshAd(false);
        load();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable com.my.target.core.models.sections.b bVar, @Nullable String str) {
        MyTargetViewListener myTargetViewListener = this.listener;
        if (myTargetViewListener != null) {
            if (bVar == null) {
                if (str == null) {
                    str = "no ad";
                }
                myTargetViewListener.onNoAd(str, this);
            } else {
                b bVar2 = this.adConfig;
                if (bVar2 != null) {
                    this.engine = com.my.target.core.engines.b.a(this, bVar2);
                    this.engine.a(this.attached);
                    this.engine.a(bVar);
                } else {
                    myTargetViewListener.onNoAd("no ad", this);
                }
            }
        }
        b bVar3 = this.adConfig;
        if (bVar3 != null) {
            bVar3.setBidId(null);
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        com.my.target.core.engines.b bVar = this.engine;
        if (bVar != null) {
            bVar.onWindowFocusChanged(z);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        com.my.target.core.engines.b bVar = this.engine;
        if (bVar != null) {
            bVar.a(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = true;
        com.my.target.core.engines.b bVar = this.engine;
        if (bVar != null) {
            bVar.a(false);
        }
    }

    public final void destroy() {
        com.my.target.core.engines.b bVar = this.engine;
        if (bVar != null) {
            bVar.destroy();
            this.engine = null;
        }
        this.listener = null;
    }
}
