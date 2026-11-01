package com.integralads.avid.library.adcolony;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AvidStateWatcher {
    public static final String CONTEXT_AVID_AD_SESSION_ID = "avidAdSessionId";
    public static final String CONTEXT_AVID_LIBRARY_VERSION = "avidLibraryVersion";
    public static final String CONTEXT_BUNDLE_IDENTIFIER = "bundleIdentifier";
    public static final String CONTEXT_PARTNER = "partner";
    public static final String CONTEXT_PARTNER_VERSION = "partnerVersion";
    private static AvidStateWatcher a = new AvidStateWatcher();
    private Context b;
    private BroadcastReceiver c;
    private boolean d;
    private boolean e;
    private AvidStateWatcherListener f;

    public interface AvidStateWatcherListener {
        void onAppStateChanged(boolean z);
    }

    public static AvidStateWatcher getInstance() {
        return a;
    }

    public void init(Context context) {
        c();
        this.b = context;
        b();
    }

    public void start() {
        this.d = true;
        d();
    }

    public void stop() {
        c();
        this.b = null;
        this.d = false;
        this.e = false;
        this.f = null;
    }

    public boolean isActive() {
        return !this.e;
    }

    public AvidStateWatcherListener getStateWatcherListener() {
        return this.f;
    }

    public void setStateWatcherListener(AvidStateWatcherListener avidStateWatcherListener) {
        this.f = avidStateWatcherListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.e != z) {
            this.e = z;
            if (this.d) {
                d();
                AvidStateWatcherListener avidStateWatcherListener = this.f;
                if (avidStateWatcherListener != null) {
                    avidStateWatcherListener.onAppStateChanged(isActive());
                }
            }
        }
    }

    private void b() {
        this.c = new BroadcastReceiver() { // from class: com.integralads.avid.library.adcolony.AvidStateWatcher.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                KeyguardManager keyguardManager;
                if (intent == null) {
                    return;
                }
                if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    AvidStateWatcher.this.b(true);
                    return;
                }
                if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                    AvidStateWatcher.this.b(false);
                } else {
                    if (!"android.intent.action.SCREEN_ON".equals(intent.getAction()) || (keyguardManager = (KeyguardManager) context.getSystemService("keyguard")) == null || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return;
                    }
                    AvidStateWatcher.this.b(false);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.b.registerReceiver(this.c, intentFilter);
    }

    private void c() {
        BroadcastReceiver broadcastReceiver;
        Context context = this.b;
        if (context == null || (broadcastReceiver = this.c) == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
        this.c = null;
    }

    private void d() {
        boolean z = !this.e;
        Iterator<InternalAvidAdSession> it = AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions().iterator();
        while (it.hasNext()) {
            it.next().setScreenMode(z);
        }
    }

    @VisibleForTesting
    BroadcastReceiver a() {
        return this.c;
    }

    @VisibleForTesting
    void a(boolean z) {
        this.e = z;
    }
}
