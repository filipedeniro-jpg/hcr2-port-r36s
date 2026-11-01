package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
public final class zzph implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long zzbnl = ((Long) zzuo.zzoj().zzd(zzyt.zzclr)).longValue();
    private final WindowManager zzbnm;

    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzbnn;
    private WeakReference<View> zzbno;
    private zzpk zzbnp;
    private final Rect zzbnt;
    private final DisplayMetrics zzwl;
    private Application zzxh;
    private final Context zzzc;
    private final PowerManager zzzd;
    private final KeyguardManager zzze;
    private WeakReference<ViewTreeObserver> zzzg;
    private zzavu zzbnq = new zzavu(zzbnl);
    private boolean zzbnr = false;
    private int zzzk = -1;
    private final HashSet<zzpl> zzbns = new HashSet<>();

    public zzph(Context context, View view) {
        this.zzzc = context.getApplicationContext();
        this.zzbnm = (WindowManager) context.getSystemService("window");
        this.zzzd = (PowerManager) this.zzzc.getSystemService("power");
        this.zzze = (KeyguardManager) context.getSystemService("keyguard");
        Context context2 = this.zzzc;
        if (context2 instanceof Application) {
            this.zzxh = (Application) context2;
            this.zzbnp = new zzpk((Application) context2, this);
        }
        this.zzwl = context.getResources().getDisplayMetrics();
        this.zzbnt = new Rect();
        this.zzbnt.right = this.zzbnm.getDefaultDisplay().getWidth();
        this.zzbnt.bottom = this.zzbnm.getDefaultDisplay().getHeight();
        WeakReference<View> weakReference = this.zzbno;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzbno = new WeakReference<>(view);
        if (view != null) {
            if (com.google.android.gms.ads.internal.zzp.zzka().isAttachedToWindow(view)) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }

    public final void zza(zzpl zzplVar) {
        this.zzbns.add(zzplVar);
        zzbm(3);
    }

    public final void zzb(zzpl zzplVar) {
        this.zzbns.remove(zzplVar);
    }

    private final void zzcq() {
        zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzpg
            private final zzph zzbnk;

            {
                this.zzbnk = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzbnk.zzlg();
            }
        });
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzzk = -1;
        zze(view);
        zzbm(3);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.zzzk = -1;
        zzbm(3);
        zzcq();
        zzf(view);
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzbno == null || (window = activity.getWindow()) == null) {
            return;
        }
        View viewPeekDecorView = window.peekDecorView();
        View view = this.zzbno.get();
        if (view == null || viewPeekDecorView == null || view.getRootView() != viewPeekDecorView.getRootView()) {
            return;
        }
        this.zzzk = i;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzbm(3);
        zzcq();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzbm(3);
        zzcq();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzbm(3);
        zzcq();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzbm(3);
        zzcq();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        zzbm(3);
        zzcq();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzbm(3);
        zzcq();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zzbm(3);
        zzcq();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zzbm(2);
        zzcq();
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zzbm(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbm(int i) {
        WeakReference<View> weakReference;
        boolean z;
        boolean z2;
        List<Rect> listEmptyList;
        if (this.zzbns.size() == 0 || (weakReference = this.zzbno) == null) {
            return;
        }
        View view = weakReference.get();
        boolean z3 = i == 1;
        boolean z4 = view == null;
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        Rect rect4 = new Rect();
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        if (view != null) {
            boolean globalVisibleRect = view.getGlobalVisibleRect(rect2);
            boolean localVisibleRect = view.getLocalVisibleRect(rect3);
            view.getHitRect(rect4);
            try {
                view.getLocationOnScreen(iArr);
                view.getLocationInWindow(iArr2);
            } catch (Exception e) {
                zzatm.zzc("Failure getting view location.", e);
            }
            rect.left = iArr[0];
            rect.top = iArr[1];
            rect.right = rect.left + view.getWidth();
            rect.bottom = rect.top + view.getHeight();
            z = globalVisibleRect;
            z2 = localVisibleRect;
        } else {
            z = false;
            z2 = false;
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclu)).booleanValue() && view != null) {
            listEmptyList = zzh(view);
        } else {
            listEmptyList = Collections.emptyList();
        }
        List<Rect> list = listEmptyList;
        int windowVisibility = view != null ? view.getWindowVisibility() : 8;
        int i2 = this.zzzk;
        if (i2 != -1) {
            windowVisibility = i2;
        }
        boolean z5 = !z4 && com.google.android.gms.ads.internal.zzp.zzjy().zza(view, this.zzzd, this.zzze) && z && z2 && windowVisibility == 0;
        if (z3 && !this.zzbnq.tryAcquire() && z5 == this.zzbnr) {
            return;
        }
        if (z5 || this.zzbnr || i != 1) {
            zzpi zzpiVar = new zzpi(com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime(), this.zzzd.isScreenOn(), view != null && com.google.android.gms.ads.internal.zzp.zzka().isAttachedToWindow(view), view != null ? view.getWindowVisibility() : 8, zza(this.zzbnt), zza(rect), zza(rect2), z, zza(rect3), z2, zza(rect4), this.zzwl.density, z5, list);
            Iterator<zzpl> it = this.zzbns.iterator();
            while (it.hasNext()) {
                it.next().zza(zzpiVar);
            }
            this.zzbnr = z5;
        }
    }

    private final Rect zza(Rect rect) {
        return new Rect(zzbn(rect.left), zzbn(rect.top), zzbn(rect.right), zzbn(rect.bottom));
    }

    private final int zzbn(int i) {
        return (int) (i / this.zzwl.density);
    }

    private final List<Rect> zzh(View view) {
        boolean zIsScrollContainer;
        try {
            ArrayList arrayList = new ArrayList();
            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                View view2 = (View) parent;
                Rect rect = new Rect();
                if (Build.VERSION.SDK_INT >= 16) {
                    zIsScrollContainer = view2.isScrollContainer();
                } else {
                    zIsScrollContainer = (view2 instanceof ScrollView) || (view2 instanceof ListView);
                }
                if (zIsScrollContainer && view2.getGlobalVisibleRect(rect)) {
                    arrayList.add(zza(rect));
                }
            }
            return arrayList;
        } catch (Exception e) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "PositionWatcher.getParentScrollViewRects");
            return Collections.emptyList();
        }
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzzg = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzbnn == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzbnn = new zzpj(this);
            com.google.android.gms.ads.internal.zzp.zzkt().zza(this.zzzc, this.zzbnn, intentFilter);
        }
        Application application = this.zzxh;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzbnp);
            } catch (Exception e) {
                zzatm.zzc("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private final void zzf(View view) {
        try {
            if (this.zzzg != null) {
                ViewTreeObserver viewTreeObserver = this.zzzg.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzzg = null;
            }
        } catch (Exception e) {
            zzatm.zzc("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
            zzatm.zzc("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        if (this.zzbnn != null) {
            try {
                com.google.android.gms.ads.internal.zzp.zzkt().zza(this.zzzc, this.zzbnn);
            } catch (IllegalStateException e3) {
                zzatm.zzc("Failed trying to unregister the receiver", e3);
            } catch (Exception e4) {
                com.google.android.gms.ads.internal.zzp.zzkc().zza(e4, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzbnn = null;
        }
        Application application = this.zzxh;
        if (application != null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zzbnp);
            } catch (Exception e5) {
                zzatm.zzc("Error registering activity lifecycle callbacks.", e5);
            }
        }
    }

    public final void zzeh(long j) {
        this.zzbnq.zzev(j);
    }

    public final void zzlf() {
        this.zzbnq.zzev(zzbnl);
    }

    final /* synthetic */ void zzlg() {
        zzbm(3);
    }
}
