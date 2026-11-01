package com.integralads.avid.library.inmobi.walking;

import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.utils.AvidViewUtil;
import com.integralads.avid.library.inmobi.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class AvidAdViewCache {
    private final AvidAdSessionRegistry adSessionRegistry;
    private boolean isAdViewProcessed;
    private final HashMap<View, String> adViews = new HashMap<>();
    private final HashMap<View, ArrayList<String>> friendlyObstructions = new HashMap<>();
    private final HashSet<View> rootViews = new HashSet<>();
    private final HashSet<String> visibleSessionIds = new HashSet<>();
    private final HashSet<String> hiddenSessionIds = new HashSet<>();

    public AvidAdViewCache(AvidAdSessionRegistry avidAdSessionRegistry) {
        this.adSessionRegistry = avidAdSessionRegistry;
    }

    public HashSet<String> getVisibleSessionIds() {
        return this.visibleSessionIds;
    }

    public HashSet<String> getHiddenSessionIds() {
        return this.hiddenSessionIds;
    }

    public void prepare() {
        for (InternalAvidAdSession internalAvidAdSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            View view = internalAvidAdSession.getView();
            if (internalAvidAdSession.isActive() && view != null) {
                if (buildRootViews(view)) {
                    this.visibleSessionIds.add(internalAvidAdSession.getAvidAdSessionId());
                    this.adViews.put(view, internalAvidAdSession.getAvidAdSessionId());
                    prepareFriendlyObstructions(internalAvidAdSession);
                } else {
                    this.hiddenSessionIds.add(internalAvidAdSession.getAvidAdSessionId());
                }
            }
        }
    }

    private boolean buildRootViews(View view) {
        if (!view.hasWindowFocus()) {
            return false;
        }
        HashSet hashSet = new HashSet();
        while (view != null) {
            if (!AvidViewUtil.isViewVisible(view)) {
                return false;
            }
            hashSet.add(view);
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.rootViews.addAll(hashSet);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void prepareFriendlyObstructions(InternalAvidAdSession internalAvidAdSession) {
        for (AvidView avidView : internalAvidAdSession.getObstructionsWhiteList().getWhiteList()) {
            if (!avidView.isEmpty()) {
                addFriendlyObstruction((View) avidView.get(), internalAvidAdSession);
            }
        }
    }

    private void addFriendlyObstruction(View view, InternalAvidAdSession internalAvidAdSession) {
        ArrayList<String> arrayList = this.friendlyObstructions.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.friendlyObstructions.put(view, arrayList);
        }
        arrayList.add(internalAvidAdSession.getAvidAdSessionId());
    }

    public void cleanup() {
        this.adViews.clear();
        this.friendlyObstructions.clear();
        this.rootViews.clear();
        this.visibleSessionIds.clear();
        this.hiddenSessionIds.clear();
        this.isAdViewProcessed = false;
    }

    public void onAdViewProcessed() {
        this.isAdViewProcessed = true;
    }

    public String getSessionId(View view) {
        if (this.adViews.size() == 0) {
            return null;
        }
        String str = this.adViews.get(view);
        if (str != null) {
            this.adViews.remove(view);
        }
        return str;
    }

    public ArrayList<String> getFriendlySessionIds(View view) {
        if (this.friendlyObstructions.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = this.friendlyObstructions.get(view);
        if (arrayList != null) {
            this.friendlyObstructions.remove(view);
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public ViewType getViewType(View view) {
        if (this.rootViews.contains(view)) {
            return ViewType.ROOT_VIEW;
        }
        return this.isAdViewProcessed ? ViewType.OBSTRUCTION_VIEW : ViewType.UNDERLYING_VIEW;
    }

    @VisibleForTesting
    HashMap<View, String> getAdViews() {
        return this.adViews;
    }

    @VisibleForTesting
    HashMap<View, ArrayList<String>> getFriendlyObstructions() {
        return this.friendlyObstructions;
    }

    @VisibleForTesting
    HashSet<View> getRootViews() {
        return this.rootViews;
    }
}
