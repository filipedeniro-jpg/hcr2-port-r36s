package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.google.android.gms.games.request.Requests;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class l {
    private final Executor b;
    private final ah c;
    private final ai d;
    private final AtomicReference<com.chartboost.sdk.Model.e> e;
    private final com.chartboost.sdk.Libraries.i f;
    private final com.chartboost.sdk.Tracking.a g;
    private final com.chartboost.sdk.Libraries.f h;
    int a = 1;
    private k i = null;
    private final PriorityQueue<j> j = new PriorityQueue<>();

    public l(Executor executor, com.chartboost.sdk.Libraries.f fVar, ah ahVar, ai aiVar, AtomicReference<com.chartboost.sdk.Model.e> atomicReference, com.chartboost.sdk.Libraries.i iVar, com.chartboost.sdk.Tracking.a aVar) {
        this.b = executor;
        this.h = fVar;
        this.c = ahVar;
        this.d = aiVar;
        this.e = atomicReference;
        this.f = iVar;
        this.g = aVar;
    }

    public synchronized void a(int i, Map<String, com.chartboost.sdk.Model.b> map, AtomicInteger atomicInteger, h hVar) {
        long jB = this.f.b();
        AtomicInteger atomicInteger2 = new AtomicInteger();
        AtomicReference atomicReference = new AtomicReference(hVar);
        for (com.chartboost.sdk.Model.b bVar : map.values()) {
            this.j.add(new j(this.f, i, bVar.b, bVar.c, bVar.a, atomicInteger, atomicReference, jB, atomicInteger2));
            jB = jB;
        }
        if (this.a == 1 || this.a == 2) {
            d();
        }
    }

    public synchronized void a(AtomicInteger atomicInteger) {
        atomicInteger.set(-10000);
        int i = this.a;
        boolean z = true;
        if (i != 1 && i == 2) {
            if (this.i.a.e != atomicInteger) {
                z = false;
            }
            if (z && this.i.b()) {
                this.i = null;
                d();
            }
        }
    }

    public synchronized void a() {
        int i = this.a;
        if (i == 1) {
            CBLogging.a("Downloader", "Change state to PAUSED");
            this.a = 4;
        } else if (i == 2) {
            if (this.i.b()) {
                this.j.add(this.i.a);
                this.i = null;
                CBLogging.a("Downloader", "Change state to PAUSED");
                this.a = 4;
            } else {
                CBLogging.a("Downloader", "Change state to PAUSING");
                this.a = 3;
            }
        }
    }

    public synchronized void b() {
        int i = this.a;
        if (i != 1 && i != 2) {
            if (i == 3) {
                CBLogging.a("Downloader", "Change state to DOWNLOADING");
                this.a = 2;
            } else if (i == 4) {
                CBLogging.a("Downloader", "Change state to IDLE");
                this.a = 1;
                d();
            }
        }
    }

    synchronized void a(k kVar, CBError cBError, ag agVar) {
        String str;
        String str2;
        int i = this.a;
        boolean z = true;
        if (i != 1 && (i == 2 || i == 3)) {
            if (kVar != this.i) {
                return;
            }
            j jVar = kVar.a;
            this.i = null;
            long millis = TimeUnit.NANOSECONDS.toMillis(kVar.g);
            jVar.f.addAndGet((int) millis);
            Executor executor = this.b;
            if (cBError != null) {
                z = false;
            }
            jVar.a(executor, z);
            long millis2 = TimeUnit.NANOSECONDS.toMillis(kVar.h);
            long millis3 = TimeUnit.NANOSECONDS.toMillis(kVar.i);
            if (cBError == null) {
                this.g.a(jVar.c, millis, millis2, millis3);
                CBLogging.a("Downloader", "Downloaded " + jVar.c);
            } else {
                String strB = cBError.b();
                this.g.a(jVar.c, strB, millis, millis2, millis3);
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to download ");
                sb.append(jVar.c);
                if (agVar != null) {
                    str = " Status code=" + agVar.a;
                } else {
                    str = "";
                }
                sb.append(str);
                if (strB != null) {
                    str2 = " Error message=" + strB;
                } else {
                    str2 = "";
                }
                sb.append(str2);
                CBLogging.a("Downloader", sb.toString());
            }
            if (this.a == 3) {
                CBLogging.a("Downloader", "Change state to PAUSED");
                this.a = 4;
            } else {
                d();
            }
        }
    }

    private void d() throws Throwable {
        j jVarPoll;
        j jVarPeek;
        if (this.i != null && (jVarPeek = this.j.peek()) != null && this.i.a.a > jVarPeek.a && this.i.b()) {
            this.j.add(this.i.a);
            this.i = null;
        }
        while (this.i == null && (jVarPoll = this.j.poll()) != null) {
            if (jVarPoll.e.get() > 0) {
                File file = new File(this.h.d().a, jVarPoll.d);
                if (!file.exists() && !file.mkdirs() && !file.isDirectory()) {
                    CBLogging.b("Downloader", "Unable to create directory " + file.getPath());
                    jVarPoll.a(this.b, false);
                } else {
                    File file2 = new File(file, jVarPoll.b);
                    if (file2.exists()) {
                        this.h.c(file2);
                        jVarPoll.a(this.b, true);
                    } else {
                        this.i = new k(this, this.d, jVarPoll, file2);
                        this.c.a(this.i);
                        this.g.a(jVarPoll.c, jVarPoll.b);
                    }
                }
            }
        }
        if (this.i != null) {
            if (this.a != 2) {
                CBLogging.a("Downloader", "Change state to DOWNLOADING");
                this.a = 2;
                return;
            }
            return;
        }
        if (this.a != 1) {
            CBLogging.a("Downloader", "Change state to IDLE");
            this.a = 1;
        }
    }

    public synchronized void c() {
        if (this.a != 1) {
            return;
        }
        try {
            CBLogging.a("Downloader", "########### Trimming the disk cache");
            File file = this.h.d().a;
            ArrayList arrayList = new ArrayList();
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String str : list) {
                    if (!str.equalsIgnoreCase(Requests.EXTRA_REQUESTS) && !str.equalsIgnoreCase("track") && !str.equalsIgnoreCase(SettingsJsonConstants.SESSION_KEY) && !str.equalsIgnoreCase("videoCompletionEvents") && !str.contains(".")) {
                        arrayList.addAll(CBUtility.a(new File(file, str), true));
                    }
                }
            }
            File[] fileArr = new File[arrayList.size()];
            arrayList.toArray(fileArr);
            if (fileArr.length > 1) {
                Arrays.sort(fileArr, new Comparator<File>() { // from class: com.chartboost.sdk.impl.l.1
                    @Override // java.util.Comparator
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(File file2, File file3) {
                        return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file3.lastModified()));
                    }
                });
            }
            if (fileArr.length > 0) {
                com.chartboost.sdk.Model.e eVar = this.e.get();
                long j = eVar.u;
                long jB = this.h.b(this.h.d().g);
                long jA = this.f.a();
                List<String> list2 = eVar.d;
                CBLogging.a("Downloader", "Total local file count:" + fileArr.length);
                CBLogging.a("Downloader", "Video Folder Size in bytes :" + jB);
                CBLogging.a("Downloader", "Max Bytes allowed:" + j);
                int length = fileArr.length;
                long length2 = jB;
                int i = 0;
                while (i < length) {
                    File file2 = fileArr[i];
                    long j2 = j;
                    com.chartboost.sdk.Model.e eVar2 = eVar;
                    boolean z = TimeUnit.MILLISECONDS.toDays(jA - file2.lastModified()) >= ((long) eVar.w);
                    boolean zEndsWith = file2.getName().endsWith(".tmp");
                    File parentFile = file2.getParentFile();
                    boolean zContains = parentFile.getAbsolutePath().contains("/videos");
                    if (file2.length() == 0 || zEndsWith || z || list2.contains(parentFile.getName()) || ((length2 > j2 ? 1 : (length2 == j2 ? 0 : -1)) > 0 && zContains)) {
                        if (zContains) {
                            length2 -= file2.length();
                        }
                        CBLogging.a("Downloader", "Deleting file at path:" + file2.getPath());
                        if (!file2.delete()) {
                            CBLogging.b("Downloader", "Unable to delete " + file2.getPath());
                        }
                    }
                    i++;
                    eVar = eVar2;
                    j = j2;
                }
            }
            this.g.a(this.h.e());
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "reduceCacheSize", e);
        }
    }
}
