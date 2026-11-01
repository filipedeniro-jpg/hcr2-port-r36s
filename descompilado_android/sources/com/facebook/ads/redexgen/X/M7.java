package com.facebook.ads.redexgen.X;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class M7 implements M0 {
    private final ExecutorService B = Executors.newSingleThreadExecutor();

    public abstract boolean A(File file, long j, int i);

    private long C(List<File> list) {
        long totalSize = 0;
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            totalSize += it.next().length();
        }
        return totalSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(File file) throws IOException {
        M5.D(file);
        E(M5.B(file.getParentFile()));
    }

    private void E(List<File> list) {
        long jC = C(list);
        int size = list.size();
        for (File file : list) {
            if (!A(file, jC, size)) {
                long length = file.length();
                if (file.delete()) {
                    size--;
                    jC -= length;
                    Log.i("ProxyCache", "Cache file " + file + " is deleted because it exceeds cache limit");
                } else {
                    Log.e("ProxyCache", "Error deleting file " + file + " for trimming cache");
                }
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.M0
    public void vF(File file) throws IOException {
        this.B.submit(new M6(this, file));
    }
}
