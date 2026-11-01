package com.google.android.gms.internal.drive;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.DriveEventService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaw extends GmsClient<zzeo> {
    private final String zzeb;
    protected final boolean zzec;
    private volatile DriveId zzed;
    private volatile DriveId zzee;
    private volatile boolean zzef;

    @VisibleForTesting
    @GuardedBy("changeEventCallbackMap")
    private final Map<DriveId, Map<ChangeListener, zzee>> zzeg;

    @VisibleForTesting
    @GuardedBy("changesAvailableEventCallbackMap")
    private final Map<com.google.android.gms.drive.events.zzd, zzee> zzeh;

    @VisibleForTesting
    @GuardedBy("uploadProgressEventCallbackMap")
    private final Map<DriveId, Map<com.google.android.gms.drive.events.zzl, zzee>> zzei;

    @VisibleForTesting
    @GuardedBy("pinnedDownloadProgressEventCallbackMap")
    private final Map<DriveId, Map<com.google.android.gms.drive.events.zzl, zzee>> zzej;
    private final Bundle zzz;

    public zzaw(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, Bundle bundle) {
        super(context, looper, 11, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzef = false;
        this.zzeg = new HashMap();
        this.zzeh = new HashMap();
        this.zzei = new HashMap();
        this.zzej = new HashMap();
        this.zzeb = clientSettings.getRealClientPackageName();
        this.zzz = bundle;
        Intent intent = new Intent(DriveEventService.ACTION_HANDLE_EVENT);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        int size = listQueryIntentServices.size();
        if (size == 0) {
            this.zzec = false;
            return;
        }
        if (size != 1) {
            String action = intent.getAction();
            StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 72);
            sb.append("AndroidManifest.xml can only define one service that handles the ");
            sb.append(action);
            sb.append(" action");
            throw new IllegalStateException(sb.toString());
        }
        ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
        if (serviceInfo.exported) {
            this.zzec = true;
            return;
        }
        String str = serviceInfo.name;
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 60);
        sb2.append("Drive event service ");
        sb2.append(str);
        sb2.append(" must be exported in AndroidManifest.xml");
        throw new IllegalStateException(sb2.toString());
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.drive.ApiService.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresAccount() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresSignIn() {
        return (getContext().getPackageName().equals(this.zzeb) && UidVerifier.isGooglePlayServicesUid(getContext(), Process.myUid())) ? false : true;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        String packageName = getContext().getPackageName();
        Preconditions.checkNotNull(packageName);
        Preconditions.checkState(!getClientSettings().getAllRequestedScopes().isEmpty());
        Bundle bundle = new Bundle();
        if (!packageName.equals(this.zzeb)) {
            bundle.putString("proxy_package_name", this.zzeb);
        }
        bundle.putAll(this.zzz);
        return bundle;
    }

    public final DriveId zzae() {
        return this.zzed;
    }

    public final DriveId zzaf() {
        return this.zzee;
    }

    public final boolean zzag() {
        return this.zzef;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        if (isConnected()) {
            try {
                ((zzeo) getService()).zza(new zzad());
            } catch (RemoteException unused) {
            }
        }
        super.disconnect();
        synchronized (this.zzeg) {
            this.zzeg.clear();
        }
        synchronized (this.zzeh) {
            this.zzeh.clear();
        }
        synchronized (this.zzei) {
            this.zzei.clear();
        }
        synchronized (this.zzej) {
            this.zzej.clear();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.zzed = (DriveId) bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.zzee = (DriveId) bundle.getParcelable("com.google.android.gms.drive.appdata_id");
            this.zzef = true;
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    public final boolean zzah() {
        return this.zzec;
    }

    final PendingResult<Status> zza(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(1, driveId));
        Preconditions.checkNotNull(changeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkState(isConnected(), "Client must be connected");
        synchronized (this.zzeg) {
            Map<ChangeListener, zzee> map = this.zzeg.get(driveId);
            if (map == null) {
                map = new HashMap<>();
                this.zzeg.put(driveId, map);
            }
            zzee zzeeVar = map.get(changeListener);
            if (zzeeVar == null) {
                zzeeVar = new zzee(getLooper(), getContext(), 1, changeListener);
                map.put(changeListener, zzeeVar);
            } else if (zzeeVar.zzg(1)) {
                return new zzat(googleApiClient, Status.RESULT_SUCCESS);
            }
            zzeeVar.zzf(1);
            return googleApiClient.execute(new zzax(this, googleApiClient, new zzj(1, driveId), zzeeVar));
        }
    }

    final PendingResult<Status> zzb(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(1, driveId));
        Preconditions.checkState(isConnected(), "Client must be connected");
        Preconditions.checkNotNull(changeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.zzeg) {
            Map<ChangeListener, zzee> map = this.zzeg.get(driveId);
            if (map == null) {
                return new zzat(googleApiClient, Status.RESULT_SUCCESS);
            }
            zzee zzeeVarRemove = map.remove(changeListener);
            if (zzeeVarRemove == null) {
                return new zzat(googleApiClient, Status.RESULT_SUCCESS);
            }
            if (map.isEmpty()) {
                this.zzeg.remove(driveId);
            }
            return googleApiClient.execute(new zzay(this, googleApiClient, new zzgs(driveId, 1), zzeeVarRemove));
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
        if (iInterfaceQueryLocalInterface instanceof zzeo) {
            return (zzeo) iInterfaceQueryLocalInterface;
        }
        return new zzep(iBinder);
    }
}
