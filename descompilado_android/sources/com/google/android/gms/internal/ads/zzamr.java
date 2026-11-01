package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzamr {

    @VisibleForTesting
    private zzcyb zzdgh;
    private static final Object lock = new Object();

    @VisibleForTesting
    @GuardedBy("lock")
    private static boolean zzye = false;

    @VisibleForTesting
    @GuardedBy("lock")
    private static boolean zzdgg = false;

    public final boolean zzo(Context context) {
        synchronized (lock) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue()) {
                return false;
            }
            if (zzye) {
                return true;
            }
            try {
                zzp(context);
                boolean zZzau = this.zzdgh.zzau(ObjectWrapper.wrap(context));
                zzye = zZzau;
                return zZzau;
            } catch (RemoteException e) {
                e = e;
                zzawo.zze("#007 Could not call remote method.", e);
                return false;
            } catch (NullPointerException e2) {
                e = e2;
                zzawo.zze("#007 Could not call remote method.", e);
                return false;
            }
        }
    }

    @VisibleForTesting
    private final void zzp(Context context) {
        synchronized (lock) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue() && !zzdgg) {
                try {
                    zzdgg = true;
                    this.zzdgh = (zzcyb) zzawr.zza(context, "com.google.android.gms.ads.omid.DynamiteOmid", zzamt.zzbtn);
                } catch (zzawt e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Nullable
    public final String getVersion(Context context) {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue()) {
            return null;
        }
        try {
            zzp(context);
            String strValueOf = String.valueOf(this.zzdgh.getVersion());
            return strValueOf.length() != 0 ? "a.".concat(strValueOf) : new String("a.");
        } catch (RemoteException | NullPointerException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    @Nullable
    public final IObjectWrapper zza(String str, WebView webView, String str2, String str3, String str4) {
        return zza(str, webView, str2, str3, str4, "Google");
    }

    @Nullable
    public final IObjectWrapper zza(String str, WebView webView, String str2, String str3, String str4, String str5) {
        synchronized (lock) {
            try {
                try {
                    if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue() && zzye) {
                        try {
                            return this.zzdgh.zza(str, ObjectWrapper.wrap(webView), str2, str3, str4, str5);
                        } catch (RemoteException | NullPointerException e) {
                            zzawo.zze("#007 Could not call remote method.", e);
                            return null;
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final void zzae(IObjectWrapper iObjectWrapper) {
        synchronized (lock) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue() && zzye) {
                try {
                    this.zzdgh.zzae(iObjectWrapper);
                } catch (RemoteException | NullPointerException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void zzaf(IObjectWrapper iObjectWrapper) {
        synchronized (lock) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue() && zzye) {
                try {
                    this.zzdgh.zzaf(iObjectWrapper);
                } catch (RemoteException | NullPointerException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, View view) {
        synchronized (lock) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue() && zzye) {
                try {
                    this.zzdgh.zzd(iObjectWrapper, ObjectWrapper.wrap(view));
                } catch (RemoteException | NullPointerException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, View view) {
        synchronized (lock) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue() && zzye) {
                try {
                    this.zzdgh.zze(iObjectWrapper, ObjectWrapper.wrap(view));
                } catch (RemoteException | NullPointerException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }
}
