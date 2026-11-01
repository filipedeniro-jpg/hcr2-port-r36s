package com.google.android.gms.ads.mediation;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public interface MediationAdapter extends MediationExtrasReceiver {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static class zza {
        private int zzeia;

        public final zza zzdd(int i) {
            this.zzeia = 1;
            return this;
        }

        public final Bundle zzaav() {
            Bundle bundle = new Bundle();
            bundle.putInt("capabilities", this.zzeia);
            return bundle;
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}
