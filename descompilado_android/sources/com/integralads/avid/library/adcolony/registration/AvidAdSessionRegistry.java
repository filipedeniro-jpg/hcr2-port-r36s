package com.integralads.avid.library.adcolony.registration;

import android.view.View;
import com.integralads.avid.library.adcolony.session.AbstractAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionListener;
import java.util.Collection;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class AvidAdSessionRegistry implements InternalAvidAdSessionListener {
    private static AvidAdSessionRegistry a = new AvidAdSessionRegistry();
    private AvidAdSessionRegistryListener d;
    private final HashMap<String, InternalAvidAdSession> b = new HashMap<>();
    private final HashMap<String, AbstractAvidAdSession> c = new HashMap<>();
    private int e = 0;

    public static AvidAdSessionRegistry getInstance() {
        return a;
    }

    public AvidAdSessionRegistryListener getListener() {
        return this.d;
    }

    public void setListener(AvidAdSessionRegistryListener avidAdSessionRegistryListener) {
        this.d = avidAdSessionRegistryListener;
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        this.c.put(abstractAvidAdSession.getAvidAdSessionId(), abstractAvidAdSession);
        this.b.put(abstractAvidAdSession.getAvidAdSessionId(), internalAvidAdSession);
        internalAvidAdSession.setListener(this);
        if (this.c.size() != 1 || (avidAdSessionRegistryListener = this.d) == null) {
            return;
        }
        avidAdSessionRegistryListener.registryHasSessionsChanged(this);
    }

    public Collection<InternalAvidAdSession> getInternalAvidAdSessions() {
        return this.b.values();
    }

    public Collection<AbstractAvidAdSession> getAvidAdSessions() {
        return this.c.values();
    }

    public boolean isEmpty() {
        return this.c.isEmpty();
    }

    public boolean hasActiveSessions() {
        return this.e > 0;
    }

    public AbstractAvidAdSession findAvidAdSessionById(String str) {
        return this.c.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return this.b.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionByView(View view) {
        for (InternalAvidAdSession internalAvidAdSession : this.b.values()) {
            if (internalAvidAdSession.doesManageView(view)) {
                return internalAvidAdSession;
            }
        }
        return null;
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionListener
    public void sessionDidEnd(InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        this.c.remove(internalAvidAdSession.getAvidAdSessionId());
        this.b.remove(internalAvidAdSession.getAvidAdSessionId());
        internalAvidAdSession.setListener(null);
        if (this.c.size() != 0 || (avidAdSessionRegistryListener = this.d) == null) {
            return;
        }
        avidAdSessionRegistryListener.registryHasSessionsChanged(this);
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionListener
    public void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        this.e++;
        if (this.e != 1 || (avidAdSessionRegistryListener = this.d) == null) {
            return;
        }
        avidAdSessionRegistryListener.registryHasActiveSessionsChanged(this);
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionListener
    public void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        this.e--;
        if (this.e != 0 || (avidAdSessionRegistryListener = this.d) == null) {
            return;
        }
        avidAdSessionRegistryListener.registryHasActiveSessionsChanged(this);
    }
}
