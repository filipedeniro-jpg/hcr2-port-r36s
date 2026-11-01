package com.inmobi.ads;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: GifMovieObject.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u implements t {
    Movie a;
    private long c;
    private t.a e;
    private ExecutorService f;
    private Runnable g;
    int b = 0;
    private volatile boolean d = false;

    public u(String str) throws IOException {
        File file = new File(str);
        byte[] bArr = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        int i = fileInputStream.read(bArr);
        fileInputStream.close();
        this.a = Movie.decodeByteArray(bArr, 0, i);
    }

    @Override // com.inmobi.ads.t
    public final void a() {
        this.f = Executors.newSingleThreadExecutor();
        this.g = new Runnable() { // from class: com.inmobi.ads.u.1
            @Override // java.lang.Runnable
            public final void run() {
                if (u.this.b + 20 >= u.this.a.duration()) {
                    final u uVar = u.this;
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.u.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            u uVar2 = u.this;
                            uVar2.b = 0;
                            uVar2.a(false);
                        }
                    });
                }
            }
        };
    }

    @Override // com.inmobi.ads.t
    public final void a(boolean z) {
        this.d = z;
        if (!this.d) {
            this.c = SystemClock.uptimeMillis() - ((long) this.b);
        }
        t.a aVar = this.e;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // com.inmobi.ads.t
    public final int b() {
        return this.a.width();
    }

    @Override // com.inmobi.ads.t
    public final int c() {
        return this.a.height();
    }

    @Override // com.inmobi.ads.t
    public final void a(Canvas canvas, float f, float f2) {
        this.a.draw(canvas, f, f2);
        this.f.execute(this.g);
    }

    @Override // com.inmobi.ads.t
    public final boolean d() {
        return !this.d;
    }

    @Override // com.inmobi.ads.t
    public final void e() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.c == 0) {
            this.c = jUptimeMillis;
        }
        int iDuration = this.a.duration();
        if (iDuration == 0) {
            iDuration = 1000;
        }
        this.b = (int) ((jUptimeMillis - this.c) % ((long) iDuration));
        this.a.setTime(this.b);
    }

    @Override // com.inmobi.ads.t
    public final void a(t.a aVar) {
        this.e = aVar;
    }
}
