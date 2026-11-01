package com.integralads.avid.library.adcolony;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.processing.AvidProcessorFactory;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.utils.AvidTimestamp;
import com.integralads.avid.library.adcolony.utils.AvidViewUtil;
import com.integralads.avid.library.adcolony.walking.AvidAdViewCache;
import com.integralads.avid.library.adcolony.walking.AvidStatePublisher;
import com.integralads.avid.library.adcolony.walking.ViewType;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTaskQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidTreeWalker implements IAvidNodeProcessor.IAvidViewWalker {
    private static final int a = 200;
    private static a c;
    private int e;
    private double i;
    private double j;
    private static AvidTreeWalker b = new AvidTreeWalker();
    private static final Runnable k = new Runnable() { // from class: com.integralads.avid.library.adcolony.AvidTreeWalker.1
        @Override // java.lang.Runnable
        public void run() {
            if (AvidTreeWalker.c != null) {
                AvidTreeWalker.c.sendEmptyMessage(0);
                AvidTreeWalker.c.postDelayed(AvidTreeWalker.k, 200L);
            }
        }
    };
    private List<AvidTreeWalkerTimeLogger> d = new ArrayList();
    private AvidAdViewCache g = new AvidAdViewCache(AvidAdSessionRegistry.getInstance());
    private AvidProcessorFactory f = new AvidProcessorFactory();
    private AvidStatePublisher h = new AvidStatePublisher(AvidAdSessionRegistry.getInstance(), new AvidAsyncTaskQueue());

    public interface AvidTreeWalkerTimeLogger {
        void onTreeProcessed(int i, long j);
    }

    public static AvidTreeWalker getInstance() {
        return b;
    }

    public void addTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (this.d.contains(avidTreeWalkerTimeLogger)) {
            return;
        }
        this.d.add(avidTreeWalkerTimeLogger);
    }

    public void removeTimeLogger(AvidTreeWalkerTimeLogger avidTreeWalkerTimeLogger) {
        if (this.d.contains(avidTreeWalkerTimeLogger)) {
            this.d.remove(avidTreeWalkerTimeLogger);
        }
    }

    public void start() {
        g();
        d();
    }

    public void stop() {
        pause();
        this.d.clear();
        this.h.cleanupCache();
    }

    public void pause() {
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        e();
        a();
        f();
    }

    private void e() {
        this.e = 0;
        this.i = AvidTimestamp.getCurrentTime();
    }

    private void f() {
        this.j = AvidTimestamp.getCurrentTime();
        a((long) (this.j - this.i));
    }

    @VisibleForTesting
    void a() {
        this.g.prepare();
        double currentTime = AvidTimestamp.getCurrentTime();
        IAvidNodeProcessor rootProcessor = this.f.getRootProcessor();
        if (this.g.getHiddenSessionIds().size() > 0) {
            this.h.publishEmptyState(rootProcessor.getState(null), this.g.getHiddenSessionIds(), currentTime);
        }
        if (this.g.getVisibleSessionIds().size() > 0) {
            JSONObject state = rootProcessor.getState(null);
            a(null, rootProcessor, state, ViewType.ROOT_VIEW);
            AvidJSONUtil.fixStateFrame(state);
            this.h.publishState(state, this.g.getVisibleSessionIds(), currentTime);
        } else {
            this.h.cleanupCache();
        }
        this.g.cleanup();
    }

    @Override // com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor.IAvidViewWalker
    public void walkView(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject) {
        ViewType viewType;
        if (AvidViewUtil.isViewVisible(view) && (viewType = this.g.getViewType(view)) != ViewType.UNDERLYING_VIEW) {
            JSONObject state = iAvidNodeProcessor.getState(view);
            AvidJSONUtil.addChildState(jSONObject, state);
            if (!a(view, state)) {
                b(view, state);
                a(view, iAvidNodeProcessor, state, viewType);
            }
            this.e++;
        }
    }

    private void a(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject, ViewType viewType) {
        iAvidNodeProcessor.iterateChildren(view, jSONObject, this, viewType == ViewType.ROOT_VIEW);
    }

    private boolean a(View view, JSONObject jSONObject) {
        String sessionId = this.g.getSessionId(view);
        if (sessionId == null) {
            return false;
        }
        AvidJSONUtil.addAvidId(jSONObject, sessionId);
        this.g.onAdViewProcessed();
        return true;
    }

    private void b(View view, JSONObject jSONObject) {
        ArrayList<String> friendlySessionIds = this.g.getFriendlySessionIds(view);
        if (friendlySessionIds != null) {
            AvidJSONUtil.addFriendlyObstruction(jSONObject, friendlySessionIds);
        }
    }

    private void a(long j) {
        if (this.d.size() > 0) {
            Iterator<AvidTreeWalkerTimeLogger> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().onTreeProcessed(this.e, j);
            }
        }
    }

    private void g() {
        if (c == null) {
            c = new a();
            c.postDelayed(k, 200L);
        }
    }

    private void h() {
        a aVar = c;
        if (aVar != null) {
            aVar.removeCallbacks(k);
            c = null;
        }
    }

    @VisibleForTesting
    void a(AvidAdViewCache avidAdViewCache) {
        this.g = avidAdViewCache;
    }

    @VisibleForTesting
    void a(AvidStatePublisher avidStatePublisher) {
        this.h = avidStatePublisher;
    }

    @VisibleForTesting
    void a(AvidProcessorFactory avidProcessorFactory) {
        this.f = avidProcessorFactory;
    }

    private static class a extends Handler {
        private a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            AvidTreeWalker.getInstance().d();
        }
    }
}
