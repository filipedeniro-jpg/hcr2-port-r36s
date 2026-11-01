package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import com.inmobi.rendering.RenderView;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ClickableViewAccessibility"})
public final class MraidMediaProcessor {
    private static final String f = "MraidMediaProcessor";
    public RenderView a;
    public MediaRenderView b;
    public RingerModeChangeReceiver c;
    public a d;
    public HeadphonesPluggedChangeReceiver e;

    public final class RingerModeChangeReceiver extends BroadcastReceiver {
        private String b;

        public RingerModeChangeReceiver(String str) {
            this.b = str;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.media.RINGER_MODE_CHANGED".equals(intent.getAction())) {
                return;
            }
            int intExtra = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", 2);
            String unused = MraidMediaProcessor.f;
            MraidMediaProcessor.a(MraidMediaProcessor.this, this.b, 2 != intExtra);
        }
    }

    public final class HeadphonesPluggedChangeReceiver extends BroadcastReceiver {
        private String b;

        public HeadphonesPluggedChangeReceiver(String str) {
            this.b = str;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                return;
            }
            int intExtra = intent.getIntExtra("state", 0);
            String unused = MraidMediaProcessor.f;
            MraidMediaProcessor.b(MraidMediaProcessor.this, this.b, 1 == intExtra);
        }
    }

    public final class a extends ContentObserver {
        private Context b;
        private int c;
        private String d;

        public a(String str, Context context, Handler handler) {
            super(handler);
            this.d = str;
            this.b = context;
            this.c = -1;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            int streamVolume;
            super.onChange(z);
            Context context = this.b;
            if (context == null || (streamVolume = ((AudioManager) context.getSystemService("audio")).getStreamVolume(3)) == this.c) {
                return;
            }
            this.c = streamVolume;
            MraidMediaProcessor.a(MraidMediaProcessor.this, this.d, streamVolume);
        }
    }

    public MraidMediaProcessor(RenderView renderView) {
        this.a = renderView;
    }

    public static boolean a() {
        Context contextB = com.inmobi.commons.a.a.b();
        return (contextB == null || 2 == ((AudioManager) contextB.getSystemService("audio")).getRingerMode()) ? false : true;
    }

    public final void b() {
        RingerModeChangeReceiver ringerModeChangeReceiver;
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null || (ringerModeChangeReceiver = this.c) == null) {
            return;
        }
        contextB.unregisterReceiver(ringerModeChangeReceiver);
        this.c = null;
    }

    public final void c() {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null || this.d == null) {
            return;
        }
        contextB.getContentResolver().unregisterContentObserver(this.d);
        this.d = null;
    }

    public static boolean d() {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return false;
        }
        return ((AudioManager) contextB.getSystemService("audio")).isWiredHeadsetOn();
    }

    public final void e() {
        HeadphonesPluggedChangeReceiver headphonesPluggedChangeReceiver;
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null || (headphonesPluggedChangeReceiver = this.e) == null) {
            return;
        }
        contextB.unregisterReceiver(headphonesPluggedChangeReceiver);
        this.e = null;
    }

    static /* synthetic */ void a(MraidMediaProcessor mraidMediaProcessor, String str, boolean z) {
        RenderView renderView = mraidMediaProcessor.a;
        if (renderView != null) {
            renderView.a(str, "fireDeviceMuteChangeEvent(" + z + ");");
        }
    }

    static /* synthetic */ void b(MraidMediaProcessor mraidMediaProcessor, String str, boolean z) {
        RenderView renderView = mraidMediaProcessor.a;
        if (renderView != null) {
            renderView.a(str, "fireHeadphonePluggedEvent(" + z + ");");
        }
    }

    static /* synthetic */ void a(MraidMediaProcessor mraidMediaProcessor, String str, int i) {
        RenderView renderView = mraidMediaProcessor.a;
        if (renderView != null) {
            renderView.a(str, "fireDeviceVolumeChangeEvent(" + i + ");");
        }
    }
}
