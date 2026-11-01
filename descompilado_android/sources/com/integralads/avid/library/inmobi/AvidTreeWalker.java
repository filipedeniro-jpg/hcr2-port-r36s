package com.integralads.avid.library.inmobi;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.processing.AvidProcessorFactory;
import com.integralads.avid.library.inmobi.processing.IAvidNodeProcessor;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.integralads.avid.library.inmobi.utils.AvidTimestamp;
import com.integralads.avid.library.inmobi.utils.AvidViewUtil;
import com.integralads.avid.library.inmobi.walking.AvidAdViewCache;
import com.integralads.avid.library.inmobi.walking.AvidStatePublisher;
import com.integralads.avid.library.inmobi.walking.ViewType;
import com.integralads.avid.library.inmobi.walking.async.AvidAsyncTaskQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidTreeWalker implements IAvidNodeProcessor.IAvidViewWalker {
    private static final int UPDATE_TREE_BY_SCHEDULE_PERIOD = 200;
    private static TreeWalkerHandler handler;
    private double endTime;
    private int objectsCount;
    private double startTime;
    private static AvidTreeWalker avidTreeWalker = new AvidTreeWalker();
    private static final Runnable viewTreeUpdaterRunnable = new Runnable() { // from class: com.integralads.avid.library.inmobi.AvidTreeWalker.1
        @Override // java.lang.Runnable
        public final void run() {
            if (AvidTreeWalker.handler != null) {
                AvidTreeWalker.handler.sendEmptyMessage(0);
                AvidTreeWalker.handler.postDelayed(AvidTreeWalker.viewTreeUpdaterRunnable, 200L);
            }
        }
    };
    private List<AvidTreeWalkerTimeLogger> timeLoggers = new ArrayList();
    private AvidAdViewCache adViewCache = new AvidAdViewCache(AvidAdSessionRegistry.getInstance());
    private AvidProcessorFactory processorFactory = new AvidProcessorFactory();
    private AvidStatePublisher statePublisher = new AvidStatePublisher(AvidAdSessionRegistry.getInstance(), new AvidAsyncTaskQueue());

    public interface AvidTreeWalkerTimeLogger {
        void onTreeProcessed(int i, long j);
    }

    public static AvidTreeWalker getInstance() {
        return avidTreeWalker;
    }

    public void addTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (this.timeLoggers.contains(avidTreeWalkerTimeLogger)) {
            return;
        }
        this.timeLoggers.add(avidTreeWalkerTimeLogger);
    }

    public void removeTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (this.timeLoggers.contains(avidTreeWalkerTimeLogger)) {
            this.timeLoggers.remove(avidTreeWalkerTimeLogger);
        }
    }

    public void start() {
        startTreeWalkerUpdater();
        updateTreeState();
    }

    public void stop() {
        pause();
        this.timeLoggers.clear();
        this.statePublisher.cleanupCache();
    }

    public void pause() {
        stopTreeWalkerUpdater();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTreeState() {
        beforeWalk();
        doWalk();
        afterWalk();
    }

    private void beforeWalk() {
        this.objectsCount = 0;
        this.startTime = AvidTimestamp.getCurrentTime();
    }

    private void afterWalk() {
        this.endTime = AvidTimestamp.getCurrentTime();
        notifyTimeLogger((long) (this.endTime - this.startTime));
    }

    @VisibleForTesting
    void doWalk() {
        this.adViewCache.prepare();
        double currentTime = AvidTimestamp.getCurrentTime();
        IAvidNodeProcessor rootProcessor = this.processorFactory.getRootProcessor();
        if (this.adViewCache.getHiddenSessionIds().size() > 0) {
            this.statePublisher.publishEmptyState(rootProcessor.getState(null), this.adViewCache.getHiddenSessionIds(), currentTime);
        }
        if (this.adViewCache.getVisibleSessionIds().size() > 0) {
            JSONObject state = rootProcessor.getState(null);
            walkViewChildren(null, rootProcessor, state, ViewType.ROOT_VIEW);
            AvidJSONUtil.fixStateFrame(state);
            this.statePublisher.publishState(state, this.adViewCache.getVisibleSessionIds(), currentTime);
        } else {
            this.statePublisher.cleanupCache();
        }
        this.adViewCache.cleanup();
    }

    @Override // com.integralads.avid.library.inmobi.processing.IAvidNodeProcessor.IAvidViewWalker
    public void walkView(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject) {
        ViewType viewType;
        if (AvidViewUtil.isViewVisible(view) && (viewType = this.adViewCache.getViewType(view)) != ViewType.UNDERLYING_VIEW) {
            JSONObject state = iAvidNodeProcessor.getState(view);
            AvidJSONUtil.addChildState(jSONObject, state);
            if (!handleAdView(view, state)) {
                checkFriendlyObstruction(view, state);
                walkViewChildren(view, iAvidNodeProcessor, state, viewType);
            }
            this.objectsCount++;
        }
    }

    private void walkViewChildren(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject, ViewType viewType) {
        iAvidNodeProcessor.iterateChildren(view, jSONObject, this, viewType == ViewType.ROOT_VIEW);
    }

    private boolean handleAdView(View view, JSONObject jSONObject) {
        String sessionId = this.adViewCache.getSessionId(view);
        if (sessionId == null) {
            return false;
        }
        AvidJSONUtil.addAvidId(jSONObject, sessionId);
        this.adViewCache.onAdViewProcessed();
        return true;
    }

    private void checkFriendlyObstruction(View view, JSONObject jSONObject) {
        ArrayList<String> friendlySessionIds = this.adViewCache.getFriendlySessionIds(view);
        if (friendlySessionIds != null) {
            AvidJSONUtil.addFriendlyObstruction(jSONObject, friendlySessionIds);
        }
    }

    private void notifyTimeLogger(long j) {
        if (this.timeLoggers.size() > 0) {
            Iterator<AvidTreeWalkerTimeLogger> it = this.timeLoggers.iterator();
            while (it.hasNext()) {
                it.next().onTreeProcessed(this.objectsCount, j);
            }
        }
    }

    private void startTreeWalkerUpdater() {
        if (handler == null) {
            TreeWalkerHandler treeWalkerHandler = new TreeWalkerHandler();
            handler = treeWalkerHandler;
            treeWalkerHandler.postDelayed(viewTreeUpdaterRunnable, 200L);
        }
    }

    private void stopTreeWalkerUpdater() {
        TreeWalkerHandler treeWalkerHandler = handler;
        if (treeWalkerHandler != null) {
            treeWalkerHandler.removeCallbacks(viewTreeUpdaterRunnable);
            handler = null;
        }
    }

    @VisibleForTesting
    void setAdViewCache(AvidAdViewCache avidAdViewCache) {
        this.adViewCache = avidAdViewCache;
    }

    @VisibleForTesting
    void setStatePublisher(AvidStatePublisher avidStatePublisher) {
        this.statePublisher = avidStatePublisher;
    }

    @VisibleForTesting
    void setProcessorFactory(AvidProcessorFactory avidProcessorFactory) {
        this.processorFactory = avidProcessorFactory;
    }

    private static class TreeWalkerHandler extends Handler {
        private TreeWalkerHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            AvidTreeWalker.getInstance().updateTreeState();
        }
    }
}
