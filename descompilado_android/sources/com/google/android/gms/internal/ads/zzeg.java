package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeg implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final Handler zzzb = new Handler(Looper.getMainLooper());
    private final zzdx zzvo;
    private Application zzxh;
    private final Context zzzc;

    @Nullable
    private final PowerManager zzzd;

    @Nullable
    private final KeyguardManager zzze;
    private BroadcastReceiver zzzf;
    private WeakReference<ViewTreeObserver> zzzg;
    private WeakReference<View> zzzh;
    private zzdm zzzi;
    private byte zzzj = -1;
    private int zzzk = -1;
    private long zzzl = -3;

    public zzeg(zzdx zzdxVar, View view) {
        this.zzvo = zzdxVar;
        this.zzzc = zzdxVar.zzlk;
        this.zzzd = (PowerManager) this.zzzc.getSystemService("power");
        this.zzze = (KeyguardManager) this.zzzc.getSystemService("keyguard");
        Context context = this.zzzc;
        if (context instanceof Application) {
            this.zzxh = (Application) context;
            this.zzzi = new zzdm((Application) context, this);
        }
        zzd(view);
    }

    final void zzd(View view) {
        WeakReference<View> weakReference = this.zzzh;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzzh = new WeakReference<>(view);
        if (view != null) {
            if ((view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
            this.zzzl = -2L;
            return;
        }
        this.zzzl = -3L;
    }

    private final void zzcq() {
        zzzb.post(new zzej(this));
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzzk = -1;
        zze(view);
        zzcs();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.zzzk = -1;
        zzcs();
        zzcq();
        zzf(view);
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzzh == null || (window = activity.getWindow()) == null) {
            return;
        }
        View viewPeekDecorView = window.peekDecorView();
        View view = this.zzzh.get();
        if (view == null || viewPeekDecorView == null || view.getRootView() != viewPeekDecorView.getRootView()) {
            return;
        }
        this.zzzk = i;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzcs();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzcs();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzcs();
        zzcq();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzcs();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        zzcs();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzcs();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zzcs();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zzcs();
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zzcs();
    }

    public final long zzcr() {
        if (this.zzzl <= -2 && this.zzzh.get() == null) {
            this.zzzl = -3L;
        }
        return this.zzzl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:40:0x0069  */
    public final void zzcs() {
        boolean z;
        WeakReference<View> weakReference = this.zzzh;
        if (weakReference == null) {
            return;
        }
        View view = weakReference.get();
        if (view == null) {
            this.zzzl = -3L;
            this.zzzj = (byte) -1;
            return;
        }
        boolean z2 = true;
        byte b = view.getVisibility() != 0 ? (byte) 1 : (byte) 0;
        if (!view.isShown()) {
            b = (byte) (b | 2);
        }
        PowerManager powerManager = this.zzzd;
        if (powerManager != null && !powerManager.isScreenOn()) {
            b = (byte) (b | 4);
        }
        if (!this.zzvo.zzck()) {
            KeyguardManager keyguardManager = this.zzze;
            if (keyguardManager == null || !keyguardManager.inKeyguardRestrictedInputMode()) {
                z2 = false;
            } else {
                Activity activityZzc = zzee.zzc(view);
                if (activityZzc == null) {
                    z = false;
                } else {
                    Window window = activityZzc.getWindow();
                    WindowManager.LayoutParams attributes = window == null ? null : window.getAttributes();
                    if (attributes == null || (attributes.flags & 524288) == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                }
                if (!z) {
                    z2 = false;
                }
            }
        }
        if (!z2) {
            b = (byte) (b | 8);
        }
        if (!view.getGlobalVisibleRect(new Rect())) {
            b = (byte) (b | 16);
        }
        if (!view.getLocalVisibleRect(new Rect())) {
            b = (byte) (b | 32);
        }
        int windowVisibility = view.getWindowVisibility();
        int i = this.zzzk;
        if (i != -1) {
            windowVisibility = i;
        }
        if (windowVisibility != 0) {
            b = (byte) (b | 64);
        }
        if (this.zzzj != b) {
            this.zzzj = b;
            byte b2 = this.zzzj;
            this.zzzl = b2 == 0 ? SystemClock.elapsedRealtime() : (-3) - ((long) b2);
        }
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzzg = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzzf == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzzf = new zzei(this);
            this.zzzc.registerReceiver(this.zzzf, intentFilter);
        }
        Application application = this.zzxh;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzzi);
            } catch (Exception unused) {
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
        } catch (Exception unused) {
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception unused2) {
        }
        BroadcastReceiver broadcastReceiver = this.zzzf;
        if (broadcastReceiver != null) {
            try {
                this.zzzc.unregisterReceiver(broadcastReceiver);
            } catch (Exception unused3) {
            }
            this.zzzf = null;
        }
        Application application = this.zzxh;
        if (application != null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zzzi);
            } catch (Exception unused4) {
            }
        }
    }
}
