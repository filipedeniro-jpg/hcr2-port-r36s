package com.integralads.avid.library.inmobi;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.activity.AvidActivityStack;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistryListener;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AvidManager implements AvidLoader.AvidLoaderListener, AvidStateWatcher.AvidStateWatcherListener, AvidAdSessionRegistryListener {
    private static AvidManager avidManagerInstance = new AvidManager();
    private static Context context;

    public static AvidManager getInstance() {
        return avidManagerInstance;
    }

    public void init(Context context2) {
        if (context == null) {
            context = context2.getApplicationContext();
            AvidStateWatcher.getInstance().init(context);
            AvidAdSessionRegistry.getInstance().setListener(this);
            AvidJSONUtil.init(context);
        }
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistry.getInstance().registerAvidAdSession(abstractAvidAdSession, internalAvidAdSession);
    }

    public AbstractAvidAdSession findAvidAdSessionById(String str) {
        return AvidAdSessionRegistry.getInstance().findAvidAdSessionById(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(str);
    }

    public void registerActivity(Activity activity) {
        AvidActivityStack.getInstance().addActivity(activity);
    }

    private void start() {
        AvidStateWatcher.getInstance().setStateWatcherListener(this);
        AvidStateWatcher.getInstance().start();
        if (AvidStateWatcher.getInstance().isActive()) {
            AvidTreeWalker.getInstance().start();
        }
    }

    private void stop() {
        AvidActivityStack.getInstance().cleanup();
        AvidTreeWalker.getInstance().stop();
        AvidStateWatcher.getInstance().stop();
        AvidLoader.getInstance().unregisterAvidLoader();
        context = null;
    }

    private boolean isActive() {
        return !AvidAdSessionRegistry.getInstance().isEmpty();
    }

    private void notifyAvidReady() {
        AvidAdSessionRegistry.getInstance().setListener(null);
        Iterator<InternalAvidAdSession> it = AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions().iterator();
        while (it.hasNext()) {
            it.next().getAvidBridgeManager().onAvidJsReady();
        }
        AvidAdSessionRegistry.getInstance().setListener(this);
    }

    @Override // com.integralads.avid.library.inmobi.AvidLoader.AvidLoaderListener
    public void onAvidLoaded() {
        if (isActive()) {
            notifyAvidReady();
            if (AvidAdSessionRegistry.getInstance().hasActiveSessions()) {
                start();
            }
        }
    }

    @Override // com.integralads.avid.library.inmobi.AvidStateWatcher.AvidStateWatcherListener
    public void onAppStateChanged(boolean z) {
        if (z) {
            AvidTreeWalker.getInstance().start();
        } else {
            AvidTreeWalker.getInstance().pause();
        }
    }

    @Override // com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistryListener
    public void registryHasSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (avidAdSessionRegistry.isEmpty() || AvidBridge.isAvidJsReady()) {
            return;
        }
        AvidLoader.getInstance().setListener(this);
        AvidLoader.getInstance().registerAvidLoader(context);
    }

    @Override // com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistryListener
    public void registryHasActiveSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (avidAdSessionRegistry.hasActiveSessions() && AvidBridge.isAvidJsReady()) {
            start();
        } else {
            stop();
        }
    }

    @VisibleForTesting
    static void setAvidManagerInstance(AvidManager avidManager) {
        avidManagerInstance = avidManager;
    }
}
