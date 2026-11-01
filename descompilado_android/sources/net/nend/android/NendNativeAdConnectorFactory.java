package net.nend.android;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NendNativeAdConnectorFactory {
    private static final List<String> a = Arrays.asList("net.nend.unity.plugin.NendUnityNativeAdClient", "net.nend.NendModule.NendNativeAdClient");

    public static NendNativeAdConnector createNativeAdConnector(NendAdNative nendAdNative) {
        boolean z = false;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            Iterator<String> it = a.iterator();
            while (it.hasNext()) {
                if (stackTraceElement.getClassName().contains(it.next())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                break;
            }
        }
        if (z) {
            return new net.nend.android.internal.b.b.a(nendAdNative);
        }
        throw new UnsupportedOperationException("Not allow to use this feature.");
    }
}
