package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdus implements zzbe, Closeable, Iterator<zzbb> {
    protected zzduu zzhwf;
    protected zzba zzhwi;
    private static final zzbb zzhwh = new zzduv("eof ");
    private static zzdva zzcp = zzdva.zzn(zzdus.class);
    private zzbb zzhwj = null;
    long zzhwk = 0;
    long zzbcr = 0;
    long zzaui = 0;
    private List<zzbb> zzhwl = new ArrayList();

    public final List<zzbb> zzbcx() {
        if (this.zzhwf != null && this.zzhwj != zzhwh) {
            return new zzduy(this.zzhwl, this);
        }
        return this.zzhwl;
    }

    public void zza(zzduu zzduuVar, long j, zzba zzbaVar) throws IOException {
        this.zzhwf = zzduuVar;
        long jPosition = zzduuVar.position();
        this.zzbcr = jPosition;
        this.zzhwk = jPosition;
        zzduuVar.zzew(zzduuVar.position() + j);
        this.zzaui = zzduuVar.position();
        this.zzhwi = zzbaVar;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        zzbb zzbbVar = this.zzhwj;
        if (zzbbVar == zzhwh) {
            return false;
        }
        if (zzbbVar != null) {
            return true;
        }
        try {
            this.zzhwj = (zzbb) next();
            return true;
        } catch (NoSuchElementException unused) {
            this.zzhwj = zzhwh;
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Iterator
    /* JADX INFO: renamed from: zzbcy, reason: merged with bridge method [inline-methods] */
    public final zzbb next() {
        zzbb zzbbVarZza;
        zzbb zzbbVar = this.zzhwj;
        if (zzbbVar != null && zzbbVar != zzhwh) {
            this.zzhwj = null;
            return zzbbVar;
        }
        zzduu zzduuVar = this.zzhwf;
        if (zzduuVar == null || this.zzhwk >= this.zzaui) {
            this.zzhwj = zzhwh;
            throw new NoSuchElementException();
        }
        try {
            synchronized (zzduuVar) {
                this.zzhwf.zzew(this.zzhwk);
                zzbbVarZza = this.zzhwi.zza(this.zzhwf, this);
                this.zzhwk = this.zzhwf.position();
            }
            return zzbbVarZza;
        } catch (EOFException unused) {
            throw new NoSuchElementException();
        } catch (IOException unused2) {
            throw new NoSuchElementException();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Constants.RequestParameters.LEFT_BRACKETS);
        for (int i = 0; i < this.zzhwl.size(); i++) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(this.zzhwl.get(i).toString());
        }
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb.toString();
    }

    public void close() throws IOException {
        this.zzhwf.close();
    }
}
