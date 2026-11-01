package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.jni.AbiArchitecture;
import com.facebook.ads.internal.jni.NativeExecutionResult;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JM {

    @Nullable
    private final Throwable B;
    private final Map<JE, JK> C;
    private final List<JE> D;

    private JM(@Nullable Throwable failure, List<JE> list, Map<JE, JK> map) {
        this.B = failure;
        List<AbiArchitecture> supported = new ArrayList<>(list);
        this.D = supported;
        Map<AbiArchitecture, NativeExecutionResult.AbiLoadResult> result = new TreeMap<>(new JJ(this));
        this.C = result;
        this.C.putAll(map);
    }

    public /* synthetic */ JM(Throwable x0, List x1, Map x2, JJ x3) {
        this(x0, x1, x2);
    }

    public static JL B() {
        return new JL(null);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("failure=");
        boolean first = this.B != null;
        sb.append(first);
        if (this.B != null) {
            return this.B.toString();
        }
        sb.append(", supported={");
        boolean z = true;
        Iterator<JE> it = this.D.iterator();
        while (first) {
            JE next = it.next();
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            sb.append(next.name());
        }
        sb.append("}, result={");
        boolean z2 = true;
        Iterator<Map.Entry<JE, JK>> it2 = this.C.entrySet().iterator();
        while (first) {
            Map.Entry<JE, JK> next2 = it2.next();
            if (z2) {
                z2 = false;
            } else {
                sb.append(",");
            }
            sb.append(next2.getKey().name());
            sb.append(Constants.RequestParameters.EQUAL);
            boolean first2 = next2.getValue().B;
            sb.append(first2);
        }
        sb.append("}");
        sb.append("\n\n");
        return sb.toString();
    }
}
