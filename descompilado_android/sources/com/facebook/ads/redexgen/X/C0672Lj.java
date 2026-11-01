package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0672Lj extends C0671Li {
    private final M1 B;
    private InterfaceC0665Lc C;
    private final C0682Lt D;

    public C0672Lj(C0682Lt source, M1 cache) {
        super(source, cache);
        this.B = cache;
        this.D = source;
    }

    @Override // com.facebook.ads.redexgen.X.C0671Li
    public final void A(int percents) {
        if (this.C != null) {
            this.C.cD(this.B.B, this.D.B, percents);
        }
    }

    private boolean C(C0670Lh request) throws C0666Ld {
        int length = this.D.length();
        return ((length > 0) && request.B && ((float) request.C) > ((float) this.B.available()) + (((float) length) * 0.2f)) ? false : true;
    }

    private String D(C0670Lh request) throws C0666Ld, IOException {
        String str;
        String str2;
        String str3;
        String strA = this.D.A();
        boolean z = !TextUtils.isEmpty(strA);
        boolean mimeKnown = this.B.RC();
        int iAvailable = mimeKnown ? this.B.available() : this.D.length();
        boolean z2 = iAvailable >= 0;
        boolean lengthKnown = request.B;
        long j = lengthKnown ? ((long) iAvailable) - request.C : iAvailable;
        boolean z3 = z2 && request.B;
        StringBuilder sbAppend = new StringBuilder().append(request.B ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n").append("Accept-Ranges: bytes\n");
        if (z2) {
            str = String.format(Locale.US, "Content-Length: %d\n", Long.valueOf(j));
        } else {
            str = "";
        }
        StringBuilder sbAppend2 = sbAppend.append(str);
        if (z3) {
            Locale locale = Locale.US;
            long contentLength = request.C;
            str2 = String.format(locale, "Content-Range: bytes %d-%d/%d\n", Long.valueOf(contentLength), Integer.valueOf(iAvailable - 1), Integer.valueOf(iAvailable));
        } else {
            str2 = "";
        }
        StringBuilder sbAppend3 = sbAppend2.append(str2);
        if (z) {
            str3 = String.format(Locale.US, "Content-Type: %s\n", strA);
        } else {
            str3 = "";
        }
        return sbAppend3.append(str3).append("\n").toString();
    }

    private void E(OutputStream out, long offset) throws C0666Ld, IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int iB = B(bArr, offset, bArr.length);
            if (iB != -1) {
                out.write(bArr, 0, iB);
                offset += (long) iB;
            } else {
                out.flush();
                return;
            }
        }
    }

    public final void F(C0670Lh request, Socket socket) throws Exception {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(D(request).getBytes("UTF-8"));
        long j = request.C;
        HA haF = C0679Lq.F();
        boolean zBG = haF.BG(this.D.m262B());
        boolean z = true;
        Integer numValueOf = null;
        Long lValueOf = null;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            if (C(request)) {
                if (this.D.length() > 0 && this.D.length() == this.B.available()) {
                    haF.dB(this.D.m262B(), zBG);
                    z = false;
                } else {
                    haF.eB(this.D.m262B(), zBG);
                }
                E(bufferedOutputStream, j);
                numValueOf = Integer.valueOf(this.B.available());
                lValueOf = Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis);
            } else {
                haF.eB(this.D.m262B(), zBG);
                F(bufferedOutputStream, j);
            }
            if (z) {
                haF.cB(this.D.m262B(), zBG, 2112, null, numValueOf, lValueOf);
            }
        } catch (C0667Le e) {
            haF.cB(this.D.m262B(), zBG, 2113, e.toString(), null, null);
            throw e;
        } catch (C0669Lg e2) {
            haF.cB(this.D.m262B(), zBG, 2119, e2.toString(), null, null);
            throw e2;
        } catch (C0666Ld e3) {
            e = e3;
            haF.cB(this.D.m262B(), zBG, 2119, e.toString(), null, null);
            throw e;
        } catch (IOException e4) {
            e = e4;
            haF.cB(this.D.m262B(), zBG, 2119, e.toString(), null, null);
            throw e;
        }
    }

    private void F(OutputStream out, long offset) throws C0666Ld, IOException {
        try {
            C0682Lt c0682Lt = new C0682Lt(this.D);
            c0682Lt.SF((int) offset);
            byte[] bArr = new byte[8192];
            while (true) {
                int i = c0682Lt.read(bArr);
                if (i != -1) {
                    out.write(bArr, 0, i);
                    offset += (long) i;
                } else {
                    out.flush();
                    return;
                }
            }
        } finally {
            this.D.close();
        }
    }

    public final void G(InterfaceC0665Lc cacheListener) {
        this.C = cacheListener;
    }
}
