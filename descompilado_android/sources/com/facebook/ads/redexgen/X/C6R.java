package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.TextView;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6R, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C6R extends AnonymousClass79 {
    private final TextView B;
    private final String C;
    private final AbstractC01210a<C03026z> D;

    public C6R(Context context, String format) {
        super(context);
        this.D = new AbstractC01210a<C03026z>() { // from class: com.facebook.ads.redexgen.X.6S
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03026z> A() {
                return C03026z.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03026z event) {
                if (this.B.getVideoView() == null) {
                    return;
                }
                this.B.B.setText(this.B.G(this.B.getVideoView().getDuration() - this.B.getVideoView().getCurrentPositionInMillis()));
            }
        };
        this.B = new TextView(context);
        this.C = format;
        addView(this.B);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        if (getVideoView() != null) {
            getVideoView().getEventBus().C(this.D);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().E(this.D);
        }
        super.B();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String G(long remainingMs) {
        if (remainingMs <= 0) {
            return "00:00";
        }
        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMs);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingMs % ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
        return this.C.isEmpty() ? String.format(Locale.US, "%02d:%02d", Long.valueOf(minutes), Long.valueOf(seconds)) : this.C.replace("{{REMAINING_TIME}}", String.format(Locale.US, "%02d:%02d", Long.valueOf(minutes), Long.valueOf(seconds)));
    }

    public void setCountdownTextColor(int color) {
        this.B.setTextColor(color);
    }
}
