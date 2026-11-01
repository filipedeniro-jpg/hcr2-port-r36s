package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Base64OutputStream;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.DeflaterOutputStream;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Il, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0599Il implements InterfaceC0596Ii {
    private final Context B;
    private final IL C;
    private static final AtomicBoolean F = new AtomicBoolean();
    private static final AtomicInteger D = new AtomicInteger(0);

    @Nullable
    private static String G = null;
    private static final MT E = MU.C();

    public C0599Il(Context context, boolean block) {
        this.B = context;
        this.C = new IL(context);
        G(context, block);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0596Ii
    public final Map<String, String> CC() {
        G(this.B, false);
        IK.H(this.B);
        HashMap map = new HashMap();
        C0591Id.C(this.B, map);
        map.put("LOCALE", Locale.getDefault().toString());
        float f = J6.B;
        int i = this.B.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.B.getResources().getDisplayMetrics().heightPixels;
        map.put("DENSITY", String.valueOf(f));
        int screenWidth = (int) (i / f);
        map.put("SCREEN_WIDTH", String.valueOf(screenWidth));
        int screenWidth2 = (int) (i2 / f);
        map.put("SCREEN_HEIGHT", String.valueOf(screenWidth2));
        map.put("ATTRIBUTION_ID", C0551Gp.C);
        map.put("ID_SOURCE", C0551Gp.D);
        map.put("BUNDLE", C0601In.E(this.B, this.C));
        map.put("CARRIER", this.C.F());
        map.put("MAKE", this.C.G());
        int screenWidth3 = E.B;
        map.put("ROOTED", String.valueOf(screenWidth3));
        map.put("INSTALLER", this.C.I());
        map.put("SDK_CAPABILITY", ML.B());
        int screenWidth4 = KN.B(this.B).B;
        map.put("NETWORK_TYPE", String.valueOf(screenWidth4));
        map.put("SESSION_TIME", C0622Jj.E(LX.D()));
        if (G != null) {
            map.put("AFP", G);
        }
        String strB = MU.B(this.B);
        if (strB != null) {
            map.put("ASHAS", strB);
        }
        map.put("UNITY", String.valueOf(MY.D(this.B)));
        String mediationService = AdInternalSettings.getMediationService();
        if (mediationService != null) {
            map.put("MEDIATION_SERVICE", mediationService);
        }
        String mediationService2 = String.valueOf(this.C.J());
        map.put("ACCESSIBILITY_ENABLED", mediationService2);
        if (this.C.A() != -1) {
            String mediationService3 = String.valueOf(this.C.A());
            map.put("APP_MIN_SDK_VERSION", mediationService3);
        }
        String mediationService4 = C0589Ib.B(this.B);
        map.put("VALPARAMS", mediationService4);
        String mediationService5 = MJ.C(IK.F());
        map.put("ANALOG", mediationService5);
        String mediationService6 = C0590Ic.C(this.B);
        map.put("PROCESS", mediationService6);
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CatchGeneralException"})
    @Nullable
    public static String F(Context context, String packageName) {
        try {
            return C0689Ma.B(context.getPackageManager().getApplicationInfo(packageName, 0).sourceDir);
        } catch (Exception e) {
            if (F.compareAndSet(false, true)) {
                C0585Hx.D(context.getApplicationContext(), MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.HB, new C0583Hv(e));
            }
            return null;
        }
    }

    @SuppressLint({"CatchGeneralException"})
    private static void G(Context context, boolean block) {
        if (!D.compareAndSet(0, 1)) {
            return;
        }
        try {
            LX.E();
            SharedPreferences sharedPreferences = context.getSharedPreferences(I6.C("FBAdPrefs", context), 0);
            String str = "AFP;" + new IL(context).C();
            G = sharedPreferences.getString(str, null);
            FutureTask<Boolean> initialiser = new FutureTask<>(new CallableC0598Ik(context, sharedPreferences, str));
            Executors.newSingleThreadExecutor().submit(initialiser);
            if (!block) {
                return;
            }
            initialiser.get();
        } catch (Exception e) {
            D.set(0);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0596Ii
    @WorkerThread
    public final String KC() {
        return LC(C0595Ih.B());
    }

    /* JADX WARN: Code duplicated, block: B:19:0x008e A[Catch: IOException -> 0x0097, TryCatch #0 {IOException -> 0x0097, blocks: (B:17:0x0089, B:19:0x008e, B:21:0x0093), top: B:48:0x0089 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0093 A[Catch: IOException -> 0x0097, TRY_LEAVE, TryCatch #0 {IOException -> 0x0097, blocks: (B:17:0x0089, B:19:0x008e, B:21:0x0093), top: B:48:0x0089 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00c2 A[Catch: IOException -> 0x00cb, TryCatch #9 {IOException -> 0x00cb, blocks: (B:41:0x00bd, B:43:0x00c2, B:45:0x00c7), top: B:53:0x00bd }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00c7 A[Catch: IOException -> 0x00cb, TRY_LEAVE, TryCatch #9 {IOException -> 0x00cb, blocks: (B:41:0x00bd, B:43:0x00c2, B:45:0x00c7), top: B:53:0x00bd }] */
    @Override // com.facebook.ads.redexgen.X.InterfaceC0596Ii
    @WorkerThread
    public final String LC(InterfaceC0592Ie filter) throws IOException {
        G(this.B, true);
        ByteArrayOutputStream byteArrayOutputStream = null;
        Base64OutputStream base64OutputStream = null;
        DeflaterOutputStream deflaterOutputStream = null;
        try {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    Base64OutputStream base64OutputStream2 = new Base64OutputStream(out, 0);
                    try {
                        DeflaterOutputStream deflaterOutputStream2 = new DeflaterOutputStream(base64OutputStream2);
                        try {
                            Map<String, String> mapCC = CC();
                            if (TextUtils.isEmpty(C0551Gp.B)) {
                                C0551Gp.B(this.B);
                                C0604Iq.B(this.B);
                            }
                            mapCC.put("IDFA", C0551Gp.B);
                            mapCC.put("USER_AGENT", C0601In.F(this.C, this.B, false));
                            Iterator<Map.Entry<String, String>> it = mapCC.entrySet().iterator();
                            while (it.hasNext()) {
                                if (!filter.RB(it.next().getKey())) {
                                    it.remove();
                                }
                            }
                            JSONObject envParameters = new JSONObject(mapCC);
                            deflaterOutputStream2.write(envParameters.toString().getBytes());
                            deflaterOutputStream2.close();
                            String strReplaceAll = out.toString().replaceAll("\n", "");
                            if (deflaterOutputStream2 != null) {
                                try {
                                    deflaterOutputStream2.close();
                                    if (base64OutputStream2 != null) {
                                        base64OutputStream2.close();
                                    }
                                    if (out != null) {
                                        out.close();
                                    }
                                } catch (IOException e) {
                                }
                            } else {
                                if (base64OutputStream2 != null) {
                                    base64OutputStream2.close();
                                }
                                if (out != null) {
                                    out.close();
                                }
                            }
                            return strReplaceAll;
                        } catch (IOException e2) {
                            e = e2;
                            deflaterOutputStream = deflaterOutputStream2;
                            base64OutputStream = base64OutputStream2;
                            byteArrayOutputStream = out;
                            throw new RuntimeException("Failed to build user token", e);
                        } catch (Throwable th) {
                            e = th;
                            deflaterOutputStream = deflaterOutputStream2;
                            base64OutputStream = base64OutputStream2;
                            byteArrayOutputStream = out;
                            if (deflaterOutputStream != null) {
                                try {
                                    deflaterOutputStream.close();
                                    if (base64OutputStream != null) {
                                        base64OutputStream.close();
                                    }
                                    if (byteArrayOutputStream != null) {
                                        byteArrayOutputStream.close();
                                    }
                                } catch (IOException e3) {
                                    throw e;
                                }
                            } else {
                                if (base64OutputStream != null) {
                                    base64OutputStream.close();
                                }
                                if (byteArrayOutputStream != null) {
                                    byteArrayOutputStream.close();
                                }
                            }
                            throw e;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        base64OutputStream = base64OutputStream2;
                        byteArrayOutputStream = out;
                    } catch (Throwable th2) {
                        e = th2;
                        base64OutputStream = base64OutputStream2;
                        byteArrayOutputStream = out;
                    }
                } catch (IOException e5) {
                    e = e5;
                    byteArrayOutputStream = out;
                } catch (Throwable th3) {
                    e = th3;
                    byteArrayOutputStream = out;
                }
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th4) {
            e = th4;
        }
    }
}
