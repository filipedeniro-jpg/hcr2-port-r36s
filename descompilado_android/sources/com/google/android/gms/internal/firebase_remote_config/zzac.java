package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class zzac {
    private final int statusCode;
    private final zzy zzag;
    private int zzbf;
    private boolean zzbg;
    private InputStream zzbr;
    private final String zzbs;
    private final String zzbt;
    private zzai zzbu;
    private final String zzbv;
    private final zzab zzbw;
    private boolean zzbx;

    zzac(zzab zzabVar, zzai zzaiVar) throws IOException {
        StringBuilder sb;
        this.zzbw = zzabVar;
        this.zzbf = zzabVar.zzv();
        this.zzbg = zzabVar.zzw();
        this.zzbu = zzaiVar;
        this.zzbs = zzaiVar.getContentEncoding();
        int statusCode = zzaiVar.getStatusCode();
        boolean z = false;
        this.statusCode = statusCode < 0 ? 0 : statusCode;
        String reasonPhrase = zzaiVar.getReasonPhrase();
        this.zzbv = reasonPhrase;
        Logger logger = zzag.zzbz;
        if (this.zzbg && logger.isLoggable(Level.CONFIG)) {
            z = true;
        }
        if (z) {
            sb = new StringBuilder();
            sb.append("-------------- RESPONSE --------------");
            sb.append(zzcl.zzgg);
            String strZzag = zzaiVar.zzag();
            if (strZzag != null) {
                sb.append(strZzag);
            } else {
                sb.append(this.statusCode);
                if (reasonPhrase != null) {
                    sb.append(' ');
                    sb.append(reasonPhrase);
                }
            }
            sb.append(zzcl.zzgg);
        } else {
            sb = null;
        }
        zzabVar.zzy().zza(zzaiVar, z ? sb : null);
        String contentType = zzaiVar.getContentType();
        contentType = contentType == null ? zzabVar.zzy().getContentType() : contentType;
        this.zzbt = contentType;
        this.zzag = contentType != null ? new zzy(contentType) : null;
        if (z) {
            logger.logp(Level.CONFIG, "com.google.api.client.http.HttpResponse", "<init>", sb.toString());
        }
    }

    public final String getContentType() {
        return this.zzbt;
    }

    public final zzw zzx() {
        return this.zzbw.zzy();
    }

    public final boolean zzad() {
        int i = this.statusCode;
        return i >= 200 && i < 300;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final String getStatusMessage() {
        return this.zzbv;
    }

    public final InputStream getContent() throws IOException {
        if (!this.zzbx) {
            InputStream content = this.zzbu.getContent();
            if (content != null) {
                try {
                    String str = this.zzbs;
                    if (str != null && str.contains(HttpRequest.ENCODING_GZIP)) {
                        content = new GZIPInputStream(content);
                    }
                    Logger logger = zzag.zzbz;
                    if (this.zzbg && logger.isLoggable(Level.CONFIG)) {
                        content = new zzce(content, logger, Level.CONFIG, this.zzbf);
                    }
                    this.zzbr = content;
                } catch (EOFException unused) {
                    content.close();
                } catch (Throwable th) {
                    content.close();
                    throw th;
                }
            }
            this.zzbx = true;
        }
        return this.zzbr;
    }

    public final void ignore() throws IOException {
        InputStream content = getContent();
        if (content != null) {
            content.close();
        }
    }

    public final void disconnect() throws IOException {
        ignore();
        this.zzbu.disconnect();
    }

    public final <T> T zza(Class<T> cls) throws IOException {
        int i = this.statusCode;
        boolean z = true;
        if (this.zzbw.getRequestMethod().equals(HttpRequest.METHOD_HEAD) || i / 100 == 1 || i == 204 || i == 304) {
            ignore();
            z = false;
        }
        if (z) {
            return (T) this.zzbw.zzaa().zza(getContent(), zzaf(), cls);
        }
        return null;
    }

    public final String zzae() throws IOException {
        InputStream content = getContent();
        if (content == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzdt.checkNotNull(content);
            zzdt.checkNotNull(byteArrayOutputStream);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = content.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    content.close();
                    return byteArrayOutputStream.toString(zzaf().name());
                }
            }
        } catch (Throwable th) {
            content.close();
            throw th;
        }
    }

    private final Charset zzaf() {
        zzy zzyVar = this.zzag;
        return (zzyVar == null || zzyVar.zzr() == null) ? zzbo.ISO_8859_1 : this.zzag.zzr();
    }
}
