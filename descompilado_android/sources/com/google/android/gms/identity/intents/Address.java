package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.identity.zze;

/* JADX INFO: loaded from: classes.dex */
public final class Address {
    private static final Api.ClientKey<zze> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zze, AddressOptions> CLIENT_BUILDER = new com.google.android.gms.identity.intents.zza();
    public static final Api<AddressOptions> API = new Api<>("Address.API", CLIENT_BUILDER, CLIENT_KEY);

    public static final class AddressOptions implements Api.ApiOptions.HasOptions {
        public final int theme;

        public AddressOptions() {
            this.theme = 0;
        }

        public AddressOptions(int i) {
            this.theme = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class zza extends BaseImplementation.ApiMethodImpl<Status, zze> {
        public zza(GoogleApiClient googleApiClient) {
            super(Address.API, googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    public static void requestUserAddress(GoogleApiClient googleApiClient, UserAddressRequest userAddressRequest, int i) {
        googleApiClient.enqueue(new zzb(googleApiClient, userAddressRequest, i));
    }
}
