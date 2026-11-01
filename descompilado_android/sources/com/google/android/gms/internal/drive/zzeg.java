package com.google.android.gms.internal.drive;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.DriveEvent;

/* JADX INFO: loaded from: classes2.dex */
final class zzeg extends zzir {
    private final Context zzgw;

    private zzeg(Looper looper, Context context) {
        super(looper);
        this.zzgw = context;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.what == 1) {
            Pair pair = (Pair) message.obj;
            com.google.android.gms.drive.events.zzi zziVar = (com.google.android.gms.drive.events.zzi) pair.first;
            DriveEvent driveEvent = (DriveEvent) pair.second;
            int type = driveEvent.getType();
            if (type == 1) {
                ((ChangeListener) zziVar).onChange((ChangeEvent) driveEvent);
                return;
            }
            if (type == 2) {
                ((CompletionListener) zziVar).onCompletion((CompletionEvent) driveEvent);
                return;
            }
            if (type != 3) {
                if (type == 4) {
                    ((com.google.android.gms.drive.events.zzd) zziVar).zza((com.google.android.gms.drive.events.zzb) driveEvent);
                    return;
                } else if (type != 8) {
                    zzee.zzbz.wfmt("EventCallback", "Unexpected event: %s", driveEvent);
                    return;
                } else {
                    ((com.google.android.gms.drive.events.zzl) zziVar).zza(new zze(((com.google.android.gms.drive.events.zzr) driveEvent).zzac()));
                    return;
                }
            }
            com.google.android.gms.drive.events.zzq zzqVar = (com.google.android.gms.drive.events.zzq) zziVar;
            com.google.android.gms.drive.events.zzo zzoVar = (com.google.android.gms.drive.events.zzo) driveEvent;
            DataHolder dataHolderZzz = zzoVar.zzz();
            if (dataHolderZzz != null) {
                zzqVar.zza(new zzeh(new MetadataBuffer(dataHolderZzz)));
            }
            if (zzoVar.zzaa()) {
                zzqVar.zzc(zzoVar.zzab());
                return;
            }
            return;
        }
        zzee.zzbz.efmt("EventCallback", "Don't know how to handle this event in context %s", this.zzgw);
    }
}
