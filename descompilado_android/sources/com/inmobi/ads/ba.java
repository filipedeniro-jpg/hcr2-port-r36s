package com.inmobi.ads;

import android.media.MediaMetadataRetriever;
import java.io.IOException;

/* JADX INFO: compiled from: NativeTimer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ba {
    a a;
    a b;

    public ba(a aVar, a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    /* JADX INFO: compiled from: NativeTimer.java */
    public static class a {
        private long a;
        private long b;
        private String c;
        private ao d;

        public a(long j, long j2, String str, ao aoVar) {
            this.a = j;
            this.b = j2;
            this.c = str;
            this.d = aoVar;
        }

        public final long a() throws IOException {
            be beVar;
            String strB;
            long j = this.a;
            ak akVarB = this.d.b(this.c);
            if (akVarB == null || !(akVarB instanceof be) || (beVar = (be) akVarB) == null || (strB = beVar.b().b()) == null) {
                return j;
            }
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(strB);
            long jIntValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue();
            double d = j;
            double d2 = this.b;
            Double.isNaN(d2);
            double d3 = jIntValue / 1000;
            Double.isNaN(d3);
            Double.isNaN(d);
            long j2 = (long) (d + (((d2 * 1.0d) / 100.0d) * d3));
            mediaMetadataRetriever.release();
            return j2;
        }
    }
}
