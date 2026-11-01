package com.adcolony.sdk;

import android.app.Activity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ADCVMModule implements ai {
    static boolean a = false;
    int b;
    ExecutorService c;
    private boolean d;
    private JSONArray e = y.b();
    private Runnable f;
    private Runnable g;
    private ADCJSVirtualMachine h;

    /* JADX WARN: Code duplicated, block: B:16:0x0055 A[Catch: Exception -> 0x00e3, IOException -> 0x0119, TryCatch #2 {IOException -> 0x0119, Exception -> 0x00e3, blocks: (B:13:0x0048, B:15:0x004c, B:17:0x0064, B:23:0x007e, B:24:0x0082, B:26:0x008a, B:28:0x008e, B:30:0x009c, B:32:0x00a2, B:37:0x00c6, B:38:0x00ce, B:34:0x00a5, B:16:0x0055), top: B:47:0x0048 }] */
    /* JADX WARN: Code duplicated, block: B:19:0x0077  */
    /* JADX WARN: Code duplicated, block: B:21:0x007a  */
    /* JADX WARN: Code duplicated, block: B:23:0x007e A[Catch: Exception -> 0x00e3, IOException -> 0x0119, TryCatch #2 {IOException -> 0x0119, Exception -> 0x00e3, blocks: (B:13:0x0048, B:15:0x004c, B:17:0x0064, B:23:0x007e, B:24:0x0082, B:26:0x008a, B:28:0x008e, B:30:0x009c, B:32:0x00a2, B:37:0x00c6, B:38:0x00ce, B:34:0x00a5, B:16:0x0055), top: B:47:0x0048 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c6 A[Catch: Exception -> 0x00e3, IOException -> 0x0119, TryCatch #2 {IOException -> 0x0119, Exception -> 0x00e3, blocks: (B:13:0x0048, B:15:0x004c, B:17:0x0064, B:23:0x007e, B:24:0x0082, B:26:0x008a, B:28:0x008e, B:30:0x009c, B:32:0x00a2, B:37:0x00c6, B:38:0x00ce, B:34:0x00a5, B:16:0x0055), top: B:47:0x0048 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00ce A[Catch: Exception -> 0x00e3, IOException -> 0x0119, TRY_LEAVE, TryCatch #2 {IOException -> 0x0119, Exception -> 0x00e3, blocks: (B:13:0x0048, B:15:0x004c, B:17:0x0064, B:23:0x007e, B:24:0x0082, B:26:0x008a, B:28:0x008e, B:30:0x009c, B:32:0x00a2, B:37:0x00c6, B:38:0x00ce, B:34:0x00a5, B:16:0x0055), top: B:47:0x0048 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:21:0x007a, please report this as an issue */
    ADCVMModule(Activity activity, int i, String str, JSONObject jSONObject, ExecutorService executorService) {
        InputStream inputStreamOpen;
        byte[] bArr;
        byte[] bytes;
        byte[] bArrDecrypt;
        this.b = i;
        this.c = executorService;
        l lVarA = a.a();
        this.d = i == 1;
        if (i == 1 && a) {
            str = "ADCController.js";
        }
        new aa.a().a("----------------------------------------------------------------------").a(aa.b);
        new aa.a().a("CREATING VM ").a(str).a(aa.b);
        if (i == 1) {
            try {
                if (a) {
                    inputStreamOpen = activity.getAssets().open(str);
                } else {
                    inputStreamOpen = new FileInputStream(new File(str).getAbsolutePath());
                }
                int iAvailable = inputStreamOpen.available();
                bArr = new byte[iAvailable];
                inputStreamOpen.read(bArr, 0, iAvailable);
                inputStreamOpen.close();
                bArr = lVarA.h() ? null : bArr;
                if (bArr != null) {
                    bytes = (jSONObject != null ? jSONObject.toString() : "").getBytes("UTF-8");
                    if (i == 1 || a || y.a(lVarA.c(), "item", 0) % 2 != 1) {
                        bArrDecrypt = bArr;
                    } else {
                        bArrDecrypt = ADCGeneratedCrypto.decrypt(bArr);
                        if (bArrDecrypt == null || bArrDecrypt.length <= 0) {
                            new aa.a().a("Can't instantiate controller VM. Deleting ").a("controller, launch response, and disabling AdColony.").a(aa.h);
                            aw.a();
                            lVarA.a(true);
                            bArrDecrypt = null;
                        }
                    }
                    if (bArrDecrypt != null) {
                        this.h = new ADCJSVirtualMachine(i, bArrDecrypt, bytes);
                    } else {
                        new aa.a().a("Couldn't create virtual machine for: ").a(str).a(aa.b);
                    }
                }
            } catch (IOException e) {
                new aa.a().a("IOException while loading controller JS: ").a(e.toString()).a(aa.h);
            } catch (Exception unused) {
                new aa.a().a("Unable to create virtual machine for: ").a(str).a(aa.h);
                if (this.d) {
                    new aa.a().a("Can't instantiate controller VM. Deleting controller, launch ").a("response, and disabling AdColony.").a(aa.h);
                    aw.a();
                    lVarA.a(true);
                }
            }
        } else {
            inputStreamOpen = new FileInputStream(new File(str).getAbsolutePath());
            int iAvailable2 = inputStreamOpen.available();
            bArr = new byte[iAvailable2];
            inputStreamOpen.read(bArr, 0, iAvailable2);
            inputStreamOpen.close();
            if (lVarA.h()) {
            }
            if (bArr != null) {
                bytes = (jSONObject != null ? jSONObject.toString() : "").getBytes("UTF-8");
                if (i == 1) {
                    bArrDecrypt = bArr;
                } else {
                    bArrDecrypt = bArr;
                }
                if (bArrDecrypt != null) {
                    this.h = new ADCJSVirtualMachine(i, bArrDecrypt, bytes);
                } else {
                    new aa.a().a("Couldn't create virtual machine for: ").a(str).a(aa.b);
                }
            }
        }
        new aa.a().a("----------------------------------------------------------------------").a(aa.b);
    }

    @Override // com.adcolony.sdk.ai
    public int a() {
        return this.b;
    }

    @Override // com.adcolony.sdk.ai
    public void a(JSONObject jSONObject) {
        synchronized (this.e) {
            this.e.put(jSONObject);
        }
    }

    @Override // com.adcolony.sdk.ai
    public void b() {
        if (this.g == null) {
            this.g = new Runnable() { // from class: com.adcolony.sdk.ADCVMModule.1
                @Override // java.lang.Runnable
                public void run() {
                    if (a.a().h()) {
                        return;
                    }
                    ADCVMModule.this.h.a();
                }
            };
        }
        this.c.submit(this.g);
        this.c.shutdown();
    }

    @Override // com.adcolony.sdk.ai
    public void c() {
        if (this.f == null) {
            this.f = new Runnable() { // from class: com.adcolony.sdk.ADCVMModule.2
                @Override // java.lang.Runnable
                public void run() {
                    String string;
                    JSONArray jSONArrayB;
                    synchronized (ADCVMModule.this.e) {
                        if (ADCVMModule.this.e.length() > 0) {
                            string = ADCVMModule.this.e.toString();
                            ADCVMModule.this.e = y.b();
                        } else {
                            string = "";
                        }
                    }
                    String str = null;
                    if (!a.a().h()) {
                        try {
                            str = new String(ADCVMModule.this.h.a(string.getBytes("UTF-8")), "UTF-8");
                        } catch (Exception e) {
                            new aa.a().a("VM update failed: ").a(aw.a(e)).a(aa.h);
                            a.a().q().a(ADCVMModule.this.b);
                        }
                    }
                    if (str == null || str.length() <= 2 || (jSONArrayB = y.b(str)) == null) {
                        return;
                    }
                    for (int i = 0; i < jSONArrayB.length(); i++) {
                        JSONObject jSONObjectA = y.a(jSONArrayB, i);
                        if (jSONObjectA != null) {
                            a.a().q().a(jSONObjectA);
                        }
                    }
                }
            };
        }
        this.c.submit(this.f);
    }

    public long d() {
        return this.h.b;
    }

    ExecutorService e() {
        return this.c;
    }

    private class ADCJSVirtualMachine {
        private long b;
        private int c;

        private native long create(int i, byte[] bArr, byte[] bArr2);

        private native void delete(long j, int i);

        private native byte[] update(long j, int i, byte[] bArr);

        class a extends Exception {
            a(String str) {
                super(str);
            }
        }

        ADCJSVirtualMachine(int i, byte[] bArr, byte[] bArr2) throws a {
            this.b = create(i, bArr, bArr2);
            if (this.b < 0) {
                throw new a("Virtual machine could not be created.");
            }
            this.c = i;
        }

        void a() {
            delete(this.b, this.c);
        }

        byte[] a(byte[] bArr) throws a {
            byte[] bArrUpdate = update(this.b, this.c, bArr);
            if (bArrUpdate != null) {
                return bArrUpdate;
            }
            final Activity activityC = com.adcolony.sdk.a.c();
            if (activityC != null && (activityC instanceof b)) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.ADCVMModule.ADCJSVirtualMachine.1
                    @Override // java.lang.Runnable
                    public void run() {
                        af afVar = new af("AdSession.finish_fullscreen_ad", 0);
                        y.b(afVar.c(), "status", 1);
                        ((b) activityC).a(afVar);
                    }
                });
            }
            if (ADCVMModule.this.d) {
                try {
                    new File(com.adcolony.sdk.a.a().o().g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5").delete();
                } catch (Exception unused) {
                }
            }
            throw new a("Virtual machine error.");
        }
    }
}
