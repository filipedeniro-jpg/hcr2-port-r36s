package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzyo {
    private final Collection<zzyi<?>> zzcfn = new ArrayList();
    private final Collection<zzyi<String>> zzcfo = new ArrayList();
    private final Collection<zzyi<String>> zzcfp = new ArrayList();

    public final void zza(zzyi zzyiVar) {
        this.zzcfn.add(zzyiVar);
    }

    public final void zzb(zzyi<String> zzyiVar) {
        this.zzcfo.add(zzyiVar);
    }

    public final void zzc(zzyi<String> zzyiVar) {
        this.zzcfp.add(zzyiVar);
    }

    public final void zza(SharedPreferences.Editor editor, int i, JSONObject jSONObject) {
        for (zzyi<?> zzyiVar : this.zzcfn) {
            if (zzyiVar.getSource() == 1) {
                zzyiVar.zza(editor, zzyiVar.zza(jSONObject));
            }
        }
        if (jSONObject != null) {
            editor.putString("flag_configuration", jSONObject.toString());
        } else {
            zzawo.zzes("Flag Json is null.");
        }
    }

    public final List<String> zzpn() {
        ArrayList arrayList = new ArrayList();
        Iterator<zzyi<String>> it = this.zzcfo.iterator();
        while (it.hasNext()) {
            String str = (String) zzuo.zzoj().zzd(it.next());
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final List<String> zzpo() {
        List<String> listZzpn = zzpn();
        Iterator<zzyi<String>> it = this.zzcfp.iterator();
        while (it.hasNext()) {
            String str = (String) zzuo.zzoj().zzd(it.next());
            if (str != null) {
                listZzpn.add(str);
            }
        }
        return listZzpn;
    }
}
