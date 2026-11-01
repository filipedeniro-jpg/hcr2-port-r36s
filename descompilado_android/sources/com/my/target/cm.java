package com.my.target;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewabilityTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public class cm {
    private static final int kw = 1;

    @NonNull
    private final ArrayList<ao> dS;

    @NonNull
    private final ArrayList<an> dT;

    @Nullable
    private WeakReference<View> kx;
    private float ky = -1.0f;

    public static cm a(@NonNull ar arVar) {
        return new cm(arVar.ab(), arVar.ac());
    }

    public void setView(@Nullable View view) {
        WeakReference<View> weakReference;
        if (view == null && (weakReference = this.kx) != null) {
            weakReference.clear();
        } else {
            this.kx = new WeakReference<>(view);
        }
    }

    protected cm(@NonNull ArrayList<ao> arrayList, @NonNull ArrayList<an> arrayList2) {
        this.dT = arrayList2;
        this.dS = arrayList;
    }

    public void e(float f) {
        View view;
        if (Math.abs(f - this.ky) < 1.0f) {
            return;
        }
        Context context = null;
        double dA = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        WeakReference<View> weakReference = this.kx;
        if (weakReference != null && (view = weakReference.get()) != null) {
            dA = cn.a(view);
            context = view.getContext();
        }
        a(dA, f, context);
        this.ky = f;
    }

    protected void a(double d, float f, @Nullable Context context) {
        if (this.dS.isEmpty() && this.dT.isEmpty()) {
            return;
        }
        if (context == null) {
            Iterator<an> it = this.dT.iterator();
            while (it.hasNext()) {
                it.next().b(-1.0f);
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        while (!this.dS.isEmpty()) {
            ArrayList<ao> arrayList2 = this.dS;
            if (arrayList2.get(arrayList2.size() - 1).Y() > f) {
                break;
            }
            ArrayList<ao> arrayList3 = this.dS;
            ao aoVarRemove = arrayList3.remove(arrayList3.size() - 1);
            int iAg = aoVarRemove.ag();
            boolean zX = aoVarRemove.X();
            double d2 = iAg;
            if ((d2 <= d && zX) || (d2 > d && !zX)) {
                arrayList.add(aoVarRemove);
            }
        }
        Iterator<an> it2 = this.dT.iterator();
        while (it2.hasNext()) {
            an next = it2.next();
            if (next.ag() > d) {
                next.b(-1.0f);
            } else if (next.W() < 0.0f || f <= next.W()) {
                next.b(f);
            } else if (f - next.W() >= next.getDuration()) {
                arrayList.add(next);
                it2.remove();
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        cj.a(arrayList, context);
    }

    public void destroy() {
        WeakReference<View> weakReference = this.kx;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.dT.clear();
        this.dS.clear();
        this.kx = null;
    }
}
