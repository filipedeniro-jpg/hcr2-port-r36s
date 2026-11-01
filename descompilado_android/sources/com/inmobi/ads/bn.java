package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: RenderTimeoutHandler.java */
/* JADX INFO: loaded from: classes2.dex */
final class bn extends Handler {
    private WeakReference<i> a;

    bn(i iVar) {
        this.a = new WeakReference<>(iVar);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        i iVar = this.a.get();
        if (iVar == null) {
            return;
        }
        try {
            if (message.what != 0) {
                return;
            }
            if (iVar instanceof ac) {
                ac acVar = (ac) iVar;
                if (acVar.u != null) {
                    acVar.u.stopLoading();
                    return;
                }
            }
            RenderView renderView = (RenderView) iVar.j();
            if (renderView == null) {
                return;
            }
            renderView.stopLoading();
        } catch (Exception e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        } finally {
            iVar.D();
        }
    }
}
