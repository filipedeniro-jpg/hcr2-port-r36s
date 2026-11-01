package com.integralads.avid.library.inmobi;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AvidStateWatcher {
    public static final String CONTEXT_AVID_AD_SESSION_ID = "avidAdSessionId";
    public static final String CONTEXT_AVID_LIBRARY_VERSION = "avidLibraryVersion";
    public static final String CONTEXT_BUNDLE_IDENTIFIER = "bundleIdentifier";
    public static final String CONTEXT_PARTNER = "partner";
    public static final String CONTEXT_PARTNER_VERSION = "partnerVersion";
    private static AvidStateWatcher avidStateWatcher = new AvidStateWatcher();
    private Context context;
    private boolean isScreenOff;
    private boolean isStarted;
    private BroadcastReceiver receiver;
    private AvidStateWatcherListener stateWatcherListener;

    public interface AvidStateWatcherListener {
        void onAppStateChanged(boolean z);
    }

    public static AvidStateWatcher getInstance() {
        return avidStateWatcher;
    }

    public void init(Context context) {
        unregisterReceiver();
        this.context = context;
        registerReceiver();
    }

    public void start() {
        this.isStarted = true;
        notifyScreenModeChanged();
    }

    public void stop() {
        unregisterReceiver();
        this.context = null;
        this.isStarted = false;
        this.isScreenOff = false;
        this.stateWatcherListener = null;
    }

    public boolean isActive() {
        return !this.isScreenOff;
    }

    public AvidStateWatcherListener getStateWatcherListener() {
        return this.stateWatcherListener;
    }

    public void setStateWatcherListener(AvidStateWatcherListener avidStateWatcherListener) {
        this.stateWatcherListener = avidStateWatcherListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScreenModeChanged(boolean z) {
        if (this.isScreenOff != z) {
            this.isScreenOff = z;
            if (this.isStarted) {
                notifyScreenModeChanged();
                AvidStateWatcherListener avidStateWatcherListener = this.stateWatcherListener;
                if (avidStateWatcherListener != null) {
                    avidStateWatcherListener.onAppStateChanged(isActive());
                }
            }
        }
    }

    private void registerReceiver() {
        this.receiver = new BroadcastReceiver() { // from class: com.integralads.avid.library.inmobi.AvidStateWatcher.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                KeyguardManager keyguardManager;
                if (intent == null) {
                    return;
                }
                if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    AvidStateWatcher.this.onScreenModeChanged(true);
                    return;
                }
                if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                    AvidStateWatcher.this.onScreenModeChanged(false);
                } else {
                    if (!"android.intent.action.SCREEN_ON".equals(intent.getAction()) || (keyguardManager = (KeyguardManager) context.getSystemService("keyguard")) == null || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return;
                    }
                    AvidStateWatcher.this.onScreenModeChanged(false);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.context.registerReceiver(this.receiver, intentFilter);
    }

    private void unregisterReceiver() {
        BroadcastReceiver broadcastReceiver;
        Context context = this.context;
        if (context == null || (broadcastReceiver = this.receiver) == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
        this.receiver = null;
    }

    private void notifyScreenModeChanged() {
        boolean z = !this.isScreenOff;
        Iterator<InternalAvidAdSession> it = AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions().iterator();
        while (it.hasNext()) {
            it.next().setScreenMode(z);
        }
    }

    @VisibleForTesting
    BroadcastReceiver getReceiver() {
        return this.receiver;
    }

    @VisibleForTesting
    void setScreenOff(boolean z) {
        this.isScreenOff = z;
    }
}
