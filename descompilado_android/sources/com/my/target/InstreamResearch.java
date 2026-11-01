package com.my.target;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ironsource.sdk.constants.Constants;
import com.my.target.common.BaseAd;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class InstreamResearch extends BaseAd {

    @Nullable
    private ft banner;

    @NonNull
    private final Context context;
    private final int duration;
    private int lastPosition;

    @Nullable
    private InstreamResearchListener listener;

    @Nullable
    private fr researchProgressTracker;

    @Nullable
    private fs researchViewabilityTracker;
    private int state;

    public interface InstreamResearchListener {
        void onLoad(@NonNull InstreamResearch instreamResearch);

        void onNoData(@NonNull InstreamResearch instreamResearch, @Nullable String str);
    }

    private String getReadableState(int i) {
        if (i == 0) {
            return "idle";
        }
        if (i == 1) {
            return Constants.ParametersKeys.VIDEO_STATUS_STARTED;
        }
        if (i != 2) {
            return i != 3 ? "unknown" : "completed";
        }
        return Constants.ParametersKeys.VIDEO_STATUS_PAUSED;
    }

    @NonNull
    public static InstreamResearch newResearch(int i, int i2, @NonNull Context context) {
        return new InstreamResearch(i, i2, context);
    }

    private InstreamResearch(int i, int i2, @NonNull Context context) {
        super(i, b.a.m);
        this.state = 0;
        this.lastPosition = -1;
        this.duration = i2;
        this.context = context;
        g.c("InstreamResearch created. Version: 5.3.9");
    }

    public void load() {
        fo.newFactory(this.adConfig, this.duration).a(new fo.b() { // from class: com.my.target.InstreamResearch.1
            @Override // com.my.target.c.b
            public final /* synthetic */ void b(@Nullable al alVar, @Nullable String str) {
                InstreamResearch.this.handleResult((fu) alVar, str);
            }
        }).a(this.context);
    }

    public void registerPlayerView(@NonNull View view) {
        fs fsVar = this.researchViewabilityTracker;
        if (fsVar != null) {
            fsVar.setView(view);
        }
    }

    public void unregisterPlayerView() {
        fs fsVar = this.researchViewabilityTracker;
        if (fsVar != null) {
            fsVar.setView(null);
        }
    }

    public void trackPause() {
        if (this.state != 1) {
            g.b("Unable to track pause, wrong state " + getReadableState(this.state));
        } else {
            trackEvent(aq.a.dv);
            this.state = 2;
        }
    }

    public void trackResume() {
        if (this.state != 2) {
            g.b("VideoAdTracker error: unable to track resume, wrong state " + getReadableState(this.state));
        } else {
            trackEvent(aq.a.dw);
            this.state = 1;
        }
    }

    public void trackProgress(float f) {
        if (this.state <= 0) {
            trackEvent(aq.a.du);
            this.state = 1;
        }
        if (this.state > 1) {
            g.a("Unable to track progress while state is: " + getReadableState(this.state));
            return;
        }
        int iRound = Math.round(f);
        int i = this.lastPosition;
        if (iRound < i) {
            trackEvent(aq.a.dE);
        } else if (iRound == i) {
            return;
        }
        this.lastPosition = iRound;
        fs fsVar = this.researchViewabilityTracker;
        if (fsVar != null) {
            fsVar.c(iRound);
        }
        fr frVar = this.researchProgressTracker;
        if (frVar != null) {
            frVar.a(iRound, this.duration, this.context);
        }
    }

    public void trackMute(boolean z) {
        trackEvent(z ? aq.a.dD : aq.a.dC);
    }

    public void trackFullscreen(boolean z) {
        trackEvent(z ? aq.a.dz : aq.a.dA);
    }

    public void setListener(@Nullable InstreamResearchListener instreamResearchListener) {
        this.listener = instreamResearchListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable fu fuVar, @Nullable String str) {
        if (fuVar != null) {
            this.banner = fuVar.f();
            ft ftVar = this.banner;
            if (ftVar != null) {
                this.researchProgressTracker = fr.a(ftVar.getStatHolder());
                this.researchViewabilityTracker = fs.b(this.banner.getStatHolder());
                InstreamResearchListener instreamResearchListener = this.listener;
                if (instreamResearchListener != null) {
                    instreamResearchListener.onLoad(this);
                    return;
                }
                return;
            }
        }
        InstreamResearchListener instreamResearchListener2 = this.listener;
        if (instreamResearchListener2 != null) {
            instreamResearchListener2.onNoData(this, str);
        }
    }

    private void trackEvent(@NonNull String str) {
        ft ftVar = this.banner;
        if (ftVar != null) {
            ArrayList<aq> arrayListX = ftVar.getStatHolder().x(str);
            if (arrayListX.isEmpty()) {
                return;
            }
            cj.a(arrayListX, this.context);
        }
    }
}
