package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzuo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class AdActivity extends Activity {

    @KeepForSdk
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";

    @KeepForSdk
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzamu zzaaw;

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzaaw = zzuo.zzog().zzb(this);
        zzamu zzamuVar = this.zzaaw;
        if (zzamuVar == null) {
            zzawo.zze("#007 Could not call remote method.", null);
            finish();
            return;
        }
        try {
            zzamuVar.onCreate(bundle);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onRestart() {
        super.onRestart();
        try {
            if (this.zzaaw != null) {
                this.zzaaw.onRestart();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onStart() {
        super.onStart();
        try {
            if (this.zzaaw != null) {
                this.zzaaw.onStart();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onResume() {
        super.onResume();
        try {
            if (this.zzaaw != null) {
                this.zzaaw.onResume();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onPause() {
        try {
            if (this.zzaaw != null) {
                this.zzaaw.onPause();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            finish();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.zzaaw != null) {
                this.zzaaw.onSaveInstanceState(bundle);
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected final void onStop() {
        try {
            if (this.zzaaw != null) {
                this.zzaaw.onStop();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            finish();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        try {
            if (this.zzaaw != null) {
                this.zzaaw.onDestroy();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
        super.onDestroy();
    }

    private final void zzcz() {
        zzamu zzamuVar = this.zzaaw;
        if (zzamuVar != null) {
            try {
                zzamuVar.zzcz();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i) {
        super.setContentView(i);
        zzcz();
    }

    @Override // android.app.Activity
    public final void setContentView(View view) {
        super.setContentView(view);
        zzcz();
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zzcz();
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        boolean zZzsl = true;
        try {
            if (this.zzaaw != null) {
                zZzsl = this.zzaaw.zzsl();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
        if (zZzsl) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected final void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zzaaw.onActivityResult(i, i2, intent);
        } catch (Exception e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzaaw.zzag(ObjectWrapper.wrap(configuration));
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }
}
