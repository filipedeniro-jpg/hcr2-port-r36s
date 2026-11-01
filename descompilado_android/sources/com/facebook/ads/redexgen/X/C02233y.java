package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3y, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02233y extends C4K {
    private static final String B = C02233y.class.getSimpleName();

    public C02233y(Context context, EnumC02053g bdIntegrationSite) {
        super(context, bdIntegrationSite);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"Nullable Dereference"})
    public AbstractC02043f E(HashMap<String, C02243z> map) {
        int iA = 0;
        Set<String> keySet = map.keySet();
        for (String str : keySet) {
            iA += map.get(str).A() + str.getBytes().length;
        }
        return new C3Y(SystemClock.elapsedRealtime(), K(), map, C3S.MAP, iA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @InterfaceC01641r
    @SuppressLint({"CatchGeneralException", "BadMethodUse-android.util.Log.e"})
    public static File F(String fileNameWithPath) {
        try {
            String strSubstring = fileNameWithPath.substring(0, fileNameWithPath.lastIndexOf(File.separator));
            String filePath = File.separator;
            return new File(strSubstring, fileNameWithPath.substring(fileNameWithPath.lastIndexOf(filePath) + 1));
        } catch (Exception e) {
            Log.e(B, "Error building file Object", e);
            return null;
        }
    }

    private static String G(String matchedPattern) {
        switch (matchedPattern) {
            case "<EXT_SD>":
                return Environment.getExternalStorageDirectory().toString();
            default:
                return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    public static String H(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        Pattern pattern = Pattern.compile("<.*?\\>");
        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()) {
            String matchedPattern = matcher.group();
            matcher.appendReplacement(stringBuffer, G(matchedPattern));
        }
        matcher.appendTail(stringBuffer);
        String fileName2 = stringBuffer.toString();
        try {
            fileName2 = new File(fileName2).getCanonicalPath();
            return fileName2;
        } catch (IOException e) {
            String regex = B;
            Log.e(regex, "Parse Env Variable", e);
            return fileName2;
        }
    }

    public final C4S L(final List<C02093k> list) {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.40
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                HashMap map = new HashMap();
                for (C02093k c02093k : list) {
                    if (c02093k.B()) {
                        String strH = C02233y.H((String) c02093k.A());
                        map.put(strH, new C02243z(C02233y.F(strH)));
                    }
                }
                return this.B.E((HashMap<String, C02243z>) map);
            }
        };
    }
}
