package com.google.android.gms.internal.ads;

import com.google.android.gms.games.GamesActivityResultCodes;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzsb {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd<zza, C0053zza> implements zzdrp {
        private static final zza zzbru;
        private static volatile zzdrw<zza> zzdv;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzb implements zzdqh {
            UNKNOWN_EVENT_TYPE(0),
            AD_REQUEST(1),
            AD_LOADED(2),
            AD_IMPRESSION(5),
            AD_FIRST_CLICK(6),
            AD_SUBSEQUENT_CLICK(7),
            REQUEST_WILL_START(8),
            REQUEST_DID_END(9),
            REQUEST_WILL_UPDATE_SIGNALS(10),
            REQUEST_DID_UPDATE_SIGNALS(11),
            REQUEST_WILL_BUILD_URL(12),
            REQUEST_DID_BUILD_URL(13),
            REQUEST_WILL_MAKE_NETWORK_REQUEST(14),
            REQUEST_DID_RECEIVE_NETWORK_RESPONSE(15),
            REQUEST_WILL_PROCESS_RESPONSE(16),
            REQUEST_DID_PROCESS_RESPONSE(17),
            REQUEST_WILL_RENDER(18),
            REQUEST_DID_RENDER(19),
            AD_FAILED_TO_LOAD(3),
            AD_FAILED_TO_LOAD_NO_FILL(4),
            AD_FAILED_TO_LOAD_INVALID_REQUEST(100),
            AD_FAILED_TO_LOAD_NETWORK_ERROR(101),
            AD_FAILED_TO_LOAD_TIMEOUT(102),
            AD_FAILED_TO_LOAD_CANCELLED(103),
            AD_FAILED_TO_LOAD_NO_ERROR(104),
            AD_FAILED_TO_LOAD_NOT_FOUND(105),
            REQUEST_WILL_UPDATE_GMS_SIGNALS(1000),
            REQUEST_DID_UPDATE_GMS_SIGNALS(1001),
            REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS(1002),
            REQUEST_FAILED_TO_BUILD_URL(1003),
            REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST(1004),
            REQUEST_FAILED_TO_PROCESS_RESPONSE(1005),
            REQUEST_FAILED_TO_UPDATE_SIGNALS(1006),
            REQUEST_FAILED_TO_RENDER(1007),
            REQUEST_IS_PREFETCH(IronSourceConstants.RV_API_SHOW_CALLED),
            REQUEST_SAVED_TO_CACHE(IronSourceConstants.RV_API_HAS_AVAILABILITY_TRUE),
            REQUEST_LOADED_FROM_CACHE(IronSourceConstants.RV_API_HAS_AVAILABILITY_FALSE),
            REQUEST_PREFETCH_INTERCEPTED(1103),
            BANNER_SIZE_INVALID(10000),
            BANNER_SIZE_VALID(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED),
            ANDROID_WEBVIEW_CRASH(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED),
            OFFLINE_UPLOAD(GamesActivityResultCodes.RESULT_LICENSE_FAILED),
            DELAY_PAGE_LOAD_CANCELLED_AD(GamesActivityResultCodes.RESULT_APP_MISCONFIGURED);

            private static final zzdqg<zzb> zzeg = new zzsc();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzb(int i) {
                this.value = i;
            }
        }

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzsb$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class C0053zza extends zzdqd.zza<zza, C0053zza> implements zzdrp {
            private C0053zza() {
                super(zza.zzbru);
            }

            /* synthetic */ C0053zza(zzsa zzsaVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsa zzsaVar = null;
            switch (zzsa.zzdi[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0053zza(zzsaVar);
                case 3:
                    return zza(zzbru, "\u0001\u0000", (Object[]) null);
                case 4:
                    return zzbru;
                case 5:
                    zzdrw<zza> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbru);
                                zzdv = zzcVar;
                            }
                            break;
                        }
                    }
                    return zzcVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zzaVar = new zza();
            zzbru = zzaVar;
            zzdqd.zza((Class<zza>) zza.class, zzaVar);
        }
    }
}
