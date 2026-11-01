package com.google.android.gms.internal.firebase_remote_config;

import com.ironsource.sdk.constants.Constants;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzan extends zzp {
    private Object data;

    public zzan(Object obj) {
        super(zzam.MEDIA_TYPE);
        this.data = zzdt.checkNotNull(obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzcm
    public final void writeTo(OutputStream outputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, zzo()));
        boolean zZza = true;
        for (Map.Entry<String, Object> entry : zzbt.zzf(this.data).entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                String strZzah = zzcs.zzah(entry.getKey());
                Class<?> cls = value.getClass();
                if ((value instanceof Iterable) || cls.isArray()) {
                    Iterator it = zzco.zzi(value).iterator();
                    while (it.hasNext()) {
                        zZza = zza(zZza, bufferedWriter, strZzah, it.next());
                    }
                } else {
                    zZza = zza(zZza, bufferedWriter, strZzah, value);
                }
            }
        }
        bufferedWriter.flush();
    }

    private static boolean zza(boolean z, Writer writer, String str, Object obj) throws IOException {
        if (obj != null && !zzbt.isNull(obj)) {
            if (z) {
                z = false;
            } else {
                writer.write(Constants.RequestParameters.AMPERSAND);
            }
            writer.write(str);
            String strZzah = zzcs.zzah(obj instanceof Enum ? zzbz.zza((Enum<?>) obj).getName() : obj.toString());
            if (strZzah.length() != 0) {
                writer.write(Constants.RequestParameters.EQUAL);
                writer.write(strZzah);
            }
        }
        return z;
    }
}
