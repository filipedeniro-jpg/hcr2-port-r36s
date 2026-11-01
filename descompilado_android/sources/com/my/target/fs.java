package com.my.target;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ResearchViewabilityTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fs {

    @Nullable
    private WeakReference<View> f;
    private long h;
    private float g = -1.0f;

    @NonNull
    private final ArrayList<fw> e = new ArrayList<>();

    @NonNull
    public static fs b(@NonNull ar arVar) {
        return new fs(arVar);
    }

    public final void setView(@Nullable View view) {
        WeakReference<View> weakReference;
        if (view == null && (weakReference = this.f) != null) {
            weakReference.clear();
        } else {
            this.f = new WeakReference<>(view);
        }
    }

    private fs(@NonNull ar arVar) {
        for (an anVar : arVar.ac()) {
            if (anVar instanceof fw) {
                this.e.add((fw) anVar);
            }
        }
    }

    public final void c(int i) {
        View view;
        float f = i;
        float f2 = this.g;
        if (f == f2) {
            return;
        }
        if (!(f >= f2 && (this.h <= 0 || (((long) (f - f2)) * 1000) - (System.currentTimeMillis() - this.h) <= 1000))) {
            Iterator<fw> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().b(-1.0f);
            }
        }
        Context context = null;
        double dA = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        WeakReference<View> weakReference = this.f;
        if (weakReference != null && (view = weakReference.get()) != null) {
            dA = cn.a(view);
            context = view.getContext();
        }
        if (!this.e.isEmpty()) {
            if (context == null) {
                Iterator<fw> it2 = this.e.iterator();
                while (it2.hasNext()) {
                    it2.next().b(-1.0f);
                }
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator<fw> it3 = this.e.iterator();
                while (it3.hasNext()) {
                    fw next = it3.next();
                    int iG = next.g();
                    int iH = next.h();
                    if (!(iG <= i && (iH == 0 || iH >= i)) || next.ag() > dA) {
                        next.b(-1.0f);
                    } else if (next.W() < 0.0f || f <= next.W()) {
                        next.b(f);
                    } else if (f - next.W() >= next.getDuration()) {
                        arrayList.add(next);
                        it3.remove();
                    }
                }
                if (!arrayList.isEmpty()) {
                    cj.a(arrayList, context);
                }
            }
        }
        this.g = f;
        this.h = System.currentTimeMillis();
    }
}
