package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.LocationConst;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class y {

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private static int f686 = 0;

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private static int f687 = 1;

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private Location f688;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private JSONObject f690;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private Rect f692;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private JSONObject f694;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private Rect f695;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private JSONObject f696;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private Map<String, Object> f689 = new HashMap();

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    String f693 = "{}";

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private a f691 = new a();

    static class a {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        Rect f697 = new Rect(0, 0, 0, 0);

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        double f698 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        double f699 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

        a() {
        }
    }

    static class b {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        final View f700;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final boolean f701;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        final int f702;

        b(View view, int i, boolean z) {
            this.f700 = view;
            this.f702 = i;
            this.f701 = z;
        }
    }

    y() {
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011f  */
    /* JADX WARN: Code duplicated, block: B:102:0x0120 A[Catch: Exception -> 0x04f3, TRY_LEAVE, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:104:0x0126  */
    /* JADX WARN: Code duplicated, block: B:105:0x0129  */
    /* JADX WARN: Code duplicated, block: B:108:0x012f  */
    /* JADX WARN: Code duplicated, block: B:109:0x0131  */
    /* JADX WARN: Code duplicated, block: B:111:0x0134  */
    /* JADX WARN: Code duplicated, block: B:112:0x0137  */
    /* JADX WARN: Code duplicated, block: B:115:0x013d  */
    /* JADX WARN: Code duplicated, block: B:116:0x0149 A[Catch: Exception -> 0x04f3, TRY_ENTER, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x0170  */
    /* JADX WARN: Code duplicated, block: B:120:0x0172  */
    /* JADX WARN: Code duplicated, block: B:122:0x0175  */
    /* JADX WARN: Code duplicated, block: B:123:0x0177  */
    /* JADX WARN: Code duplicated, block: B:126:0x018d  */
    /* JADX WARN: Code duplicated, block: B:127:0x018f  */
    /* JADX WARN: Code duplicated, block: B:129:0x0192  */
    /* JADX WARN: Code duplicated, block: B:130:0x0194  */
    /* JADX WARN: Code duplicated, block: B:133:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:134:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:136:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:137:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:140:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:141:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:143:0x01c8 A[Catch: Exception -> 0x04f3, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:144:0x01cd A[Catch: Exception -> 0x04f3, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:149:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:152:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:154:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:155:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:157:0x0202 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:158:0x0204  */
    /* JADX WARN: Code duplicated, block: B:159:0x0206  */
    /* JADX WARN: Code duplicated, block: B:162:0x020a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:163:0x020c  */
    /* JADX WARN: Code duplicated, block: B:164:0x020f  */
    /* JADX WARN: Code duplicated, block: B:167:0x0215 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:168:0x0217  */
    /* JADX WARN: Code duplicated, block: B:169:0x0219  */
    /* JADX WARN: Code duplicated, block: B:171:0x021c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:172:0x021e  */
    /* JADX WARN: Code duplicated, block: B:173:0x0221  */
    /* JADX WARN: Code duplicated, block: B:176:0x0227  */
    /* JADX WARN: Code duplicated, block: B:178:0x0234  */
    /* JADX WARN: Code duplicated, block: B:179:0x0236  */
    /* JADX WARN: Code duplicated, block: B:181:0x0239  */
    /* JADX WARN: Code duplicated, block: B:184:0x024f  */
    /* JADX WARN: Code duplicated, block: B:185:0x0251  */
    /* JADX WARN: Code duplicated, block: B:187:0x0254  */
    /* JADX WARN: Code duplicated, block: B:188:0x0256 A[Catch: Exception -> 0x04f3, TRY_LEAVE, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:191:0x0262  */
    /* JADX WARN: Code duplicated, block: B:192:0x0264  */
    /* JADX WARN: Code duplicated, block: B:195:0x0268  */
    /* JADX WARN: Code duplicated, block: B:199:0x0286  */
    /* JADX WARN: Code duplicated, block: B:200:0x0289  */
    /* JADX WARN: Code duplicated, block: B:204:0x0290  */
    /* JADX WARN: Code duplicated, block: B:207:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:208:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:211:0x02ab A[Catch: Exception -> 0x04f3, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:213:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:214:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:217:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:218:0x02bb A[Catch: Exception -> 0x04f3, TRY_LEAVE, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:220:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:221:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:224:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:225:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:229:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:232:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:233:0x02f6  */
    /* JADX WARN: Code duplicated, block: B:236:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:238:0x0306  */
    /* JADX WARN: Code duplicated, block: B:239:0x0309  */
    /* JADX WARN: Code duplicated, block: B:242:0x030f A[Catch: Exception -> 0x04f1, TRY_ENTER, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:244:0x0319  */
    /* JADX WARN: Code duplicated, block: B:245:0x031b  */
    /* JADX WARN: Code duplicated, block: B:247:0x031e  */
    /* JADX WARN: Code duplicated, block: B:248:0x031f A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:250:0x0329  */
    /* JADX WARN: Code duplicated, block: B:251:0x032c  */
    /* JADX WARN: Code duplicated, block: B:254:0x0331  */
    /* JADX WARN: Code duplicated, block: B:255:0x0332 A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:257:0x033e  */
    /* JADX WARN: Code duplicated, block: B:258:0x0341  */
    /* JADX WARN: Code duplicated, block: B:261:0x0347 A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:265:0x0354 A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:268:0x0379  */
    /* JADX WARN: Code duplicated, block: B:269:0x037b  */
    /* JADX WARN: Code duplicated, block: B:271:0x037e  */
    /* JADX WARN: Code duplicated, block: B:273:0x038a  */
    /* JADX WARN: Code duplicated, block: B:274:0x038e  */
    /* JADX WARN: Code duplicated, block: B:276:0x0394 A[Catch: Exception -> 0x04f1, TRY_ENTER, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:278:0x039c  */
    /* JADX WARN: Code duplicated, block: B:279:0x039d A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:281:0x03a5 A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:284:0x03bb  */
    /* JADX WARN: Code duplicated, block: B:285:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:293:0x03df  */
    /* JADX WARN: Code duplicated, block: B:294:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:297:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:299:0x03f1 A[Catch: Exception -> 0x04f1, TRY_ENTER, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:301:0x03f9  */
    /* JADX WARN: Code duplicated, block: B:302:0x03fa A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:304:0x0402  */
    /* JADX WARN: Code duplicated, block: B:305:0x0405  */
    /* JADX WARN: Code duplicated, block: B:307:0x0409  */
    /* JADX WARN: Code duplicated, block: B:308:0x040a A[Catch: Exception -> 0x04f1, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:311:0x041d  */
    /* JADX WARN: Code duplicated, block: B:312:0x041f  */
    /* JADX WARN: Code duplicated, block: B:314:0x0422  */
    /* JADX WARN: Code duplicated, block: B:318:0x0431  */
    /* JADX WARN: Code duplicated, block: B:319:0x0433  */
    /* JADX WARN: Code duplicated, block: B:322:0x0438  */
    /* JADX WARN: Code duplicated, block: B:325:0x0474  */
    /* JADX WARN: Code duplicated, block: B:326:0x0477  */
    /* JADX WARN: Code duplicated, block: B:328:0x047b  */
    /* JADX WARN: Code duplicated, block: B:329:0x047c  */
    /* JADX WARN: Code duplicated, block: B:331:0x0480  */
    /* JADX WARN: Code duplicated, block: B:332:0x0483  */
    /* JADX WARN: Code duplicated, block: B:335:0x0489  */
    /* JADX WARN: Code duplicated, block: B:336:0x0495 A[Catch: Exception -> 0x04f1, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:338:0x04d0  */
    /* JADX WARN: Code duplicated, block: B:339:0x04d3  */
    /* JADX WARN: Code duplicated, block: B:341:0x04d7  */
    /* JADX WARN: Code duplicated, block: B:342:0x04e2 A[Catch: Exception -> 0x04f1, TRY_ENTER, TryCatch #1 {Exception -> 0x04f1, blocks: (B:228:0x02ec, B:230:0x02f0, B:242:0x030f, B:255:0x0332, B:261:0x0347, B:266:0x036a, B:276:0x0394, B:282:0x03b7, B:290:0x03c9, B:291:0x03db, B:299:0x03f1, B:309:0x040d, B:315:0x042c, B:323:0x0442, B:344:0x04ea, B:343:0x04e7, B:342:0x04e2, B:336:0x0495, B:302:0x03fa, B:308:0x040a, B:288:0x03c1, B:279:0x039d, B:281:0x03a5, B:248:0x031f, B:265:0x0354, B:226:0x02e0), top: B:355:0x02e0 }] */
    /* JADX WARN: Code duplicated, block: B:358:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x008d A[Catch: Exception -> 0x04f3, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:88:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:93:0x010b  */
    /* JADX WARN: Code duplicated, block: B:94:0x010e  */
    /* JADX WARN: Code duplicated, block: B:96:0x0112 A[Catch: Exception -> 0x04f3, TRY_ENTER, TryCatch #0 {Exception -> 0x04f3, blocks: (B:9:0x0022, B:18:0x003a, B:28:0x0054, B:42:0x007c, B:44:0x0099, B:54:0x00b1, B:96:0x0112, B:117:0x014d, B:124:0x0182, B:131:0x0195, B:138:0x01a8, B:143:0x01c8, B:145:0x01d2, B:182:0x0243, B:188:0x0256, B:196:0x0272, B:197:0x0279, B:205:0x029a, B:211:0x02ab, B:218:0x02bb, B:345:0x04ee, B:144:0x01cd, B:116:0x0149, B:102:0x0120, B:79:0x00e8, B:67:0x00d0, B:35:0x0068, B:43:0x008d), top: B:353:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:98:0x0118  */
    /* JADX WARN: Code duplicated, block: B:99:0x011b  */
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m622(String str, View view) {
        String str2;
        DisplayMetrics displayMetrics;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        char c;
        float fM614;
        boolean z5;
        int i;
        boolean z6;
        int i2;
        boolean z7;
        int i3;
        Rect rect;
        boolean z8;
        Rect rect2;
        a aVar;
        int iWidth;
        char c2;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        Location locationM563;
        boolean z14;
        boolean z15;
        JSONObject jSONObject;
        boolean z16;
        boolean z17;
        char c3;
        HashMap map;
        int i4;
        int i5;
        JSONObject jSONObject2;
        int i6;
        boolean z18;
        boolean z19;
        int i7;
        char c4;
        char c5;
        int i8;
        char c6;
        char c7;
        char c8;
        boolean z20;
        int i9;
        boolean z21;
        char c9;
        boolean z22;
        char c10;
        boolean z23;
        Rect rect3;
        boolean z24;
        int iWidth2;
        boolean z25;
        HashSet hashSet;
        char c11;
        boolean zM619;
        char c12;
        char c13;
        int iM615;
        char c14;
        boolean z26;
        int i10;
        char c15;
        char c16;
        char c17;
        Activity activity;
        HashMap map2 = new HashMap();
        String string = "{}";
        if ((view != null ? 'N' : (char) 0) != 'N') {
            return;
        }
        int i11 = f687 + 31;
        f686 = i11 % 128;
        int i12 = i11 % 2;
        try {
            if (Build.VERSION.SDK_INT < 17) {
                displayMetrics = view.getContext().getResources().getDisplayMetrics();
            } else {
                int i13 = f686 + 93;
                f687 = i13 % 128;
                int i14 = i13 % 2;
                if (!(com.moat.analytics.mobile.iro.a.f497 == null)) {
                    int i15 = f687 + 85;
                    f686 = i15 % 128;
                    if (!(i15 % 2 != 0)) {
                        activity = com.moat.analytics.mobile.iro.a.f497.get();
                        if ((activity != null ? '`' : ';') != ';') {
                            displayMetrics = new DisplayMetrics();
                            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                        } else {
                            displayMetrics = view.getContext().getResources().getDisplayMetrics();
                        }
                    } else {
                        activity = com.moat.analytics.mobile.iro.a.f497.get();
                        if ((activity != null ? 'V' : '[') != 'V') {
                            displayMetrics = view.getContext().getResources().getDisplayMetrics();
                        } else {
                            displayMetrics = new DisplayMetrics();
                            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                        }
                    }
                } else {
                    displayMetrics = view.getContext().getResources().getDisplayMetrics();
                }
            }
            if (Build.VERSION.SDK_INT < 19) {
                if ((view != null ? (char) 25 : (char) 26) != 26) {
                    z = (view.getWindowToken() != null ? 'B' : '&') != '&';
                }
            } else {
                if ((view != null ? '\f' : '+') == '\f') {
                    if ((view.isAttachedToWindow() ? '!' : '2') != '!') {
                    }
                }
            }
            if (view != null) {
                if (view.hasWindowFocus()) {
                    z2 = true;
                }
                if (view != null) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z3) {
                    z4 = true;
                } else {
                    i10 = f686 + 69;
                    f687 = i10 % 128;
                    if (i10 % 2 == 0) {
                        c15 = 'B';
                    } else {
                        c15 = '.';
                    }
                    if (c15 != 'B') {
                        if (view.isShown()) {
                            c17 = 'Y';
                        } else {
                            c17 = 'E';
                        }
                        if (c17 != 'E') {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                    } else {
                        if (view.isShown()) {
                            c16 = '4';
                        } else {
                            c16 = 'W';
                        }
                        if (c16 != '4') {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                    }
                }
                if (view == null) {
                    c = '0';
                } else {
                    c = '%';
                }
                if (c != '%') {
                    int i16 = f687 + 67;
                    f686 = i16 % 128;
                    int i17 = i16 % 2;
                    fM614 = 0.0f;
                } else {
                    fM614 = m614(view);
                }
                map2.put("dr", Float.valueOf(displayMetrics.density));
                map2.put("dv", Double.valueOf(p.m580()));
                map2.put("adKey", str);
                if (z) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (!z5) {
                    i = 0;
                } else {
                    int i18 = f686 + 77;
                    f687 = i18 % 128;
                    int i19 = i18 % 2;
                    i = 1;
                }
                map2.put("isAttached", Integer.valueOf(i));
                if (z2) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (!z6) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                map2.put("inFocus", Integer.valueOf(i2));
                if (z4) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (!z7) {
                    i3 = 0;
                } else {
                    i3 = 1;
                }
                map2.put("isHidden", Integer.valueOf(i3));
                map2.put("opacity", Float.valueOf(fM614));
                rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
                if (view != null) {
                    z8 = false;
                } else {
                    z8 = true;
                }
                if (!z8) {
                    rect2 = m620(view);
                } else {
                    rect2 = new Rect(0, 0, 0, 0);
                }
                aVar = new a();
                iWidth = rect2.width() * rect2.height();
                if (view != null) {
                    c2 = '\"';
                } else {
                    c2 = '7';
                }
                if (c2 != '7') {
                    i9 = f686 + 45;
                    f687 = i9 % 128;
                    if (i9 % 2 == 0) {
                        z21 = true;
                    } else {
                        z21 = false;
                    }
                    if (z21) {
                        if (z) {
                            z26 = false;
                        } else {
                            z26 = true;
                        }
                        if (!z26) {
                            if (z2) {
                                z22 = false;
                            } else {
                                z22 = true;
                            }
                            if (z22) {
                                if (z4) {
                                    c10 = '[';
                                } else {
                                    c10 = 18;
                                }
                                if (c10 != '[') {
                                    int i20 = f687 + 17;
                                    f686 = i20 % 128;
                                    int i21 = i20 % 2;
                                    if (iWidth > 0) {
                                        z23 = false;
                                    } else {
                                        z23 = true;
                                    }
                                    if (!z23) {
                                        int i22 = f687 + 121;
                                        f686 = i22 % 128;
                                        int i23 = i22 % 2;
                                        rect3 = new Rect(0, 0, 0, 0);
                                        if (view.getGlobalVisibleRect(rect3)) {
                                            z24 = true;
                                        } else {
                                            z24 = false;
                                        }
                                        if (z24) {
                                            iWidth2 = rect3.width() * rect3.height();
                                            if (iWidth2 < iWidth) {
                                                z25 = false;
                                            } else {
                                                z25 = true;
                                            }
                                            if (!z25) {
                                                int i24 = f687 + 39;
                                                f686 = i24 % 128;
                                                int i25 = i24 % 2;
                                                com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", null, "Ad is clipped");
                                            }
                                            hashSet = new HashSet();
                                            if (view.getRootView() instanceof ViewGroup) {
                                                c11 = ')';
                                            } else {
                                                c11 = 'T';
                                            }
                                            if (c11 != ')') {
                                                int i26 = f686 + 49;
                                                f687 = i26 % 128;
                                                int i27 = i26 % 2;
                                                aVar.f697 = rect3;
                                                zM619 = m619(rect3, view, hashSet);
                                                if (zM619) {
                                                    c12 = 'Y';
                                                } else {
                                                    c12 = '\\';
                                                }
                                                if (c12 != '\\') {
                                                    aVar.f699 = 1.0d;
                                                }
                                                if (zM619) {
                                                    c13 = 18;
                                                } else {
                                                    c13 = 21;
                                                }
                                                if (c13 != 21) {
                                                    iM615 = m615(rect3, hashSet);
                                                    if (iM615 > 0) {
                                                        c14 = ']';
                                                    } else {
                                                        c14 = 26;
                                                    }
                                                    if (c14 != ']') {
                                                        str2 = "{}";
                                                    } else {
                                                        int i28 = f686 + 87;
                                                        f687 = i28 % 128;
                                                        int i29 = i28 % 2;
                                                        double d = iM615;
                                                        str2 = "{}";
                                                        double d2 = iWidth2;
                                                        Double.isNaN(d);
                                                        Double.isNaN(d2);
                                                        try {
                                                            aVar.f699 = d / d2;
                                                        } catch (Exception e) {
                                                            e = e;
                                                        }
                                                    }
                                                    double d3 = iWidth2 - iM615;
                                                    double d4 = iWidth;
                                                    Double.isNaN(d3);
                                                    Double.isNaN(d4);
                                                    aVar.f698 = d3 / d4;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (z) {
                            c9 = 'H';
                        } else {
                            c9 = 'Z';
                        }
                        if (c9 != 'Z') {
                            if (z2) {
                                z22 = false;
                            } else {
                                z22 = true;
                            }
                            if (z22) {
                                if (z4) {
                                    c10 = 18;
                                } else {
                                    c10 = '[';
                                }
                                if (c10 != '[') {
                                    int i210 = f687 + 17;
                                    f686 = i210 % 128;
                                    int i211 = i210 % 2;
                                    if (iWidth > 0) {
                                        z23 = false;
                                    } else {
                                        z23 = true;
                                    }
                                    if (!z23) {
                                        int i212 = f687 + 121;
                                        f686 = i212 % 128;
                                        int i213 = i212 % 2;
                                        rect3 = new Rect(0, 0, 0, 0);
                                        if (view.getGlobalVisibleRect(rect3)) {
                                            z24 = true;
                                        } else {
                                            z24 = false;
                                        }
                                        if (z24) {
                                            iWidth2 = rect3.width() * rect3.height();
                                            if (iWidth2 < iWidth) {
                                                z25 = false;
                                            } else {
                                                z25 = true;
                                            }
                                            if (!z25) {
                                                int i214 = f687 + 39;
                                                f686 = i214 % 128;
                                                int i215 = i214 % 2;
                                                com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", null, "Ad is clipped");
                                            }
                                            hashSet = new HashSet();
                                            if (view.getRootView() instanceof ViewGroup) {
                                                c11 = ')';
                                            } else {
                                                c11 = 'T';
                                            }
                                            if (c11 != ')') {
                                                int i216 = f686 + 49;
                                                f687 = i216 % 128;
                                                int i217 = i216 % 2;
                                                aVar.f697 = rect3;
                                                zM619 = m619(rect3, view, hashSet);
                                                if (zM619) {
                                                    c12 = 'Y';
                                                } else {
                                                    c12 = '\\';
                                                }
                                                if (c12 != '\\') {
                                                    aVar.f699 = 1.0d;
                                                }
                                                if (zM619) {
                                                    c13 = 21;
                                                } else {
                                                    c13 = 18;
                                                }
                                                if (c13 != 21) {
                                                    iM615 = m615(rect3, hashSet);
                                                    if (iM615 > 0) {
                                                        c14 = ']';
                                                    } else {
                                                        c14 = 26;
                                                    }
                                                    if (c14 != ']') {
                                                        str2 = "{}";
                                                    } else {
                                                        int i218 = f686 + 87;
                                                        f687 = i218 % 128;
                                                        int i219 = i218 % 2;
                                                        double d5 = iM615;
                                                        str2 = "{}";
                                                        double d6 = iWidth2;
                                                        Double.isNaN(d5);
                                                        Double.isNaN(d6);
                                                        aVar.f699 = d5 / d6;
                                                    }
                                                    double d7 = iWidth2 - iM615;
                                                    double d8 = iWidth;
                                                    Double.isNaN(d7);
                                                    Double.isNaN(d8);
                                                    aVar.f698 = d7 / d8;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    str2 = "{}";
                } else {
                    str2 = "{}";
                }
                if (this.f694 != null) {
                    z9 = false;
                } else {
                    z9 = true;
                }
                if (z9) {
                    this.f691 = aVar;
                    this.f694 = new JSONObject(m621(m616(this.f691.f697, displayMetrics)));
                    z10 = true;
                } else {
                    i8 = f686 + 99;
                    f687 = i8 % 128;
                    if (i8 % 2 == 0) {
                        c6 = 'B';
                    } else {
                        c6 = 16;
                    }
                    if (c6 != 'B') {
                        if (aVar.f698 == this.f691.f698) {
                            z20 = true;
                        } else {
                            z20 = false;
                        }
                        if (z20) {
                            if (aVar.f697.equals(this.f691.f697)) {
                                c8 = 24;
                            } else {
                                c8 = '*';
                            }
                            if (c8 == '*' && aVar.f699 == this.f691.f699) {
                                z10 = false;
                            }
                        }
                    } else {
                        if (aVar.f698 == this.f691.f698) {
                            c7 = 'R';
                        } else {
                            c7 = 3;
                        }
                        if (c7 != 'R') {
                            if (aVar.f697.equals(this.f691.f697)) {
                                c8 = 24;
                            } else {
                                c8 = '*';
                            }
                            if (c8 == '*') {
                            }
                        }
                    }
                    this.f691 = aVar;
                    this.f694 = new JSONObject(m621(m616(this.f691.f697, displayMetrics)));
                    z10 = true;
                }
                map2.put("coveredPercent", Double.valueOf(aVar.f699));
                if (this.f690 != null) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                if (z11) {
                    i7 = f686 + 9;
                    f687 = i7 % 128;
                    if (i7 % 2 == 0) {
                        c5 = 7;
                        c4 = 'Y';
                    } else {
                        c4 = 'Y';
                        c5 = 'Y';
                    }
                    if (c5 != c4) {
                        if (!rect.equals(this.f695)) {
                            this.f695 = rect;
                            this.f690 = new JSONObject(m621(m616(rect, displayMetrics)));
                            z10 = true;
                        }
                    } else if (!rect.equals(this.f695)) {
                        this.f695 = rect;
                        this.f690 = new JSONObject(m621(m616(rect, displayMetrics)));
                        z10 = true;
                    }
                } else {
                    this.f695 = rect;
                    this.f690 = new JSONObject(m621(m616(rect, displayMetrics)));
                    z10 = true;
                }
                if (this.f696 != null) {
                    z12 = false;
                } else {
                    z12 = true;
                }
                if (!z12 || !rect2.equals(this.f692)) {
                    this.f692 = rect2;
                    this.f696 = new JSONObject(m621(m616(rect2, displayMetrics)));
                    z10 = true;
                }
                if (this.f689 != null) {
                    z13 = false;
                } else {
                    z13 = true;
                }
                if (z13) {
                    this.f689 = map2;
                    z10 = true;
                } else {
                    i6 = f687 + 99;
                    f686 = i6 % 128;
                    if (i6 % 2 != 0) {
                        if (map2.equals(this.f689)) {
                            z18 = true;
                            z19 = false;
                        } else {
                            z18 = true;
                            z19 = true;
                        }
                        if (z19 != z18) {
                            this.f689 = map2;
                            z10 = true;
                        }
                    } else if (!map2.equals(this.f689)) {
                        this.f689 = map2;
                        z10 = true;
                    }
                }
                locationM563 = k.m554().m563();
                if (k.m555(locationM563, this.f688)) {
                    z14 = false;
                } else {
                    z14 = true;
                }
                if (z14) {
                    int i30 = f687 + 103;
                    f686 = i30 % 128;
                    int i31 = i30 % 2;
                    this.f688 = locationM563;
                    z10 = true;
                }
                if (z10) {
                    z15 = false;
                } else {
                    z15 = true;
                }
                if (z15) {
                    return;
                }
                int i32 = f686 + 57;
                f687 = i32 % 128;
                int i33 = i32 % 2;
                jSONObject = new JSONObject(this.f689);
                jSONObject.accumulate("screen", this.f690);
                jSONObject.accumulate(Constants.ParametersKeys.VIEW, this.f696);
                jSONObject.accumulate("visible", this.f694);
                jSONObject.accumulate("maybe", this.f694);
                jSONObject.accumulate("visiblePercent", Double.valueOf(this.f691.f698));
                if (locationM563 != null) {
                    z16 = true;
                    z17 = true;
                } else {
                    z16 = true;
                    z17 = false;
                }
                if (z17 == z16) {
                    if (locationM563 == null) {
                        c3 = 'B';
                    } else {
                        c3 = 'X';
                    }
                    if (c3 != 'X') {
                        int i34 = f687 + 95;
                        f686 = i34 % 128;
                        int i35 = i34 % 2;
                        map = null;
                    } else {
                        map = new HashMap();
                        map.put(LocationConst.LATITUDE, Double.toString(locationM563.getLatitude()));
                        map.put(LocationConst.LONGITUDE, Double.toString(locationM563.getLongitude()));
                        map.put("timestamp", Long.toString(locationM563.getTime()));
                        map.put("horizontalAccuracy", Float.toString(locationM563.getAccuracy()));
                    }
                    if (map == null) {
                        i4 = 1;
                        i5 = 0;
                    } else {
                        i4 = 1;
                        i5 = 1;
                    }
                    if (i5 != i4) {
                        int i36 = f686 + i4;
                        f687 = i36 % 128;
                        int i37 = i36 % 2;
                        jSONObject2 = null;
                    } else {
                        jSONObject2 = new JSONObject(map);
                    }
                    jSONObject.accumulate("location", jSONObject2);
                }
                string = jSONObject.toString();
                this.f693 = string;
                return;
                o.m569(e);
                this.f693 = str2;
            }
            z2 = false;
            if (view != null) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3) {
                z4 = true;
            } else {
                i10 = f686 + 69;
                f687 = i10 % 128;
                if (i10 % 2 == 0) {
                    c15 = 'B';
                } else {
                    c15 = '.';
                }
                if (c15 != 'B') {
                    if (view.isShown()) {
                        c17 = 'E';
                    } else {
                        c17 = 'Y';
                    }
                    if (c17 != 'E') {
                        z4 = false;
                    } else {
                        z4 = true;
                    }
                } else {
                    if (view.isShown()) {
                        c16 = 'W';
                    } else {
                        c16 = '4';
                    }
                    if (c16 != '4') {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                }
            }
            if (view == null) {
                c = '0';
            } else {
                c = '%';
            }
            if (c != '%') {
                int i110 = f687 + 67;
                f686 = i110 % 128;
                int i111 = i110 % 2;
                fM614 = 0.0f;
            } else {
                fM614 = m614(view);
            }
            map2.put("dr", Float.valueOf(displayMetrics.density));
            map2.put("dv", Double.valueOf(p.m580()));
            map2.put("adKey", str);
            if (z) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (!z5) {
                i = 0;
            } else {
                int i112 = f686 + 77;
                f687 = i112 % 128;
                int i113 = i112 % 2;
                i = 1;
            }
            map2.put("isAttached", Integer.valueOf(i));
            if (z2) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (!z6) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            map2.put("inFocus", Integer.valueOf(i2));
            if (z4) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (!z7) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            map2.put("isHidden", Integer.valueOf(i3));
            map2.put("opacity", Float.valueOf(fM614));
            rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
            if (view != null) {
                z8 = false;
            } else {
                z8 = true;
            }
            if (!z8) {
                rect2 = m620(view);
            } else {
                rect2 = new Rect(0, 0, 0, 0);
            }
            aVar = new a();
            iWidth = rect2.width() * rect2.height();
            if (view != null) {
                c2 = '\"';
            } else {
                c2 = '7';
            }
            if (c2 != '7') {
                i9 = f686 + 45;
                f687 = i9 % 128;
                if (i9 % 2 == 0) {
                    z21 = true;
                } else {
                    z21 = false;
                }
                if (z21) {
                    if (z) {
                        z26 = false;
                    } else {
                        z26 = true;
                    }
                    if (!z26) {
                        if (z2) {
                            z22 = false;
                        } else {
                            z22 = true;
                        }
                        if (z22) {
                            if (z4) {
                                c10 = 18;
                            } else {
                                c10 = '[';
                            }
                            if (c10 != '[') {
                                int i2110 = f687 + 17;
                                f686 = i2110 % 128;
                                int i2111 = i2110 % 2;
                                if (iWidth > 0) {
                                    z23 = false;
                                } else {
                                    z23 = true;
                                }
                                if (!z23) {
                                    int i2112 = f687 + 121;
                                    f686 = i2112 % 128;
                                    int i2113 = i2112 % 2;
                                    rect3 = new Rect(0, 0, 0, 0);
                                    if (view.getGlobalVisibleRect(rect3)) {
                                        z24 = true;
                                    } else {
                                        z24 = false;
                                    }
                                    if (z24) {
                                        iWidth2 = rect3.width() * rect3.height();
                                        if (iWidth2 < iWidth) {
                                            z25 = false;
                                        } else {
                                            z25 = true;
                                        }
                                        if (!z25) {
                                            int i2114 = f687 + 39;
                                            f686 = i2114 % 128;
                                            int i2115 = i2114 % 2;
                                            com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", null, "Ad is clipped");
                                        }
                                        hashSet = new HashSet();
                                        if (view.getRootView() instanceof ViewGroup) {
                                            c11 = ')';
                                        } else {
                                            c11 = 'T';
                                        }
                                        if (c11 != ')') {
                                            int i2116 = f686 + 49;
                                            f687 = i2116 % 128;
                                            int i2117 = i2116 % 2;
                                            aVar.f697 = rect3;
                                            zM619 = m619(rect3, view, hashSet);
                                            if (zM619) {
                                                c12 = 'Y';
                                            } else {
                                                c12 = '\\';
                                            }
                                            if (c12 != '\\') {
                                                aVar.f699 = 1.0d;
                                            }
                                            if (zM619) {
                                                c13 = 21;
                                            } else {
                                                c13 = 18;
                                            }
                                            if (c13 != 21) {
                                                iM615 = m615(rect3, hashSet);
                                                if (iM615 > 0) {
                                                    c14 = ']';
                                                } else {
                                                    c14 = 26;
                                                }
                                                if (c14 != ']') {
                                                    str2 = "{}";
                                                } else {
                                                    int i2118 = f686 + 87;
                                                    f687 = i2118 % 128;
                                                    int i2119 = i2118 % 2;
                                                    double d9 = iM615;
                                                    str2 = "{}";
                                                    double d10 = iWidth2;
                                                    Double.isNaN(d9);
                                                    Double.isNaN(d10);
                                                    aVar.f699 = d9 / d10;
                                                }
                                                double d11 = iWidth2 - iM615;
                                                double d12 = iWidth;
                                                Double.isNaN(d11);
                                                Double.isNaN(d12);
                                                aVar.f698 = d11 / d12;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (z) {
                        c9 = 'H';
                    } else {
                        c9 = 'Z';
                    }
                    if (c9 != 'Z') {
                        if (z2) {
                            z22 = false;
                        } else {
                            z22 = true;
                        }
                        if (z22) {
                            if (z4) {
                                c10 = 18;
                            } else {
                                c10 = '[';
                            }
                            if (c10 != '[') {
                                int i21110 = f687 + 17;
                                f686 = i21110 % 128;
                                int i21111 = i21110 % 2;
                                if (iWidth > 0) {
                                    z23 = false;
                                } else {
                                    z23 = true;
                                }
                                if (!z23) {
                                    int i21112 = f687 + 121;
                                    f686 = i21112 % 128;
                                    int i21113 = i21112 % 2;
                                    rect3 = new Rect(0, 0, 0, 0);
                                    if (view.getGlobalVisibleRect(rect3)) {
                                        z24 = true;
                                    } else {
                                        z24 = false;
                                    }
                                    if (z24) {
                                        iWidth2 = rect3.width() * rect3.height();
                                        if (iWidth2 < iWidth) {
                                            z25 = false;
                                        } else {
                                            z25 = true;
                                        }
                                        if (!z25) {
                                            int i21114 = f687 + 39;
                                            f686 = i21114 % 128;
                                            int i21115 = i21114 % 2;
                                            com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", null, "Ad is clipped");
                                        }
                                        hashSet = new HashSet();
                                        if (view.getRootView() instanceof ViewGroup) {
                                            c11 = ')';
                                        } else {
                                            c11 = 'T';
                                        }
                                        if (c11 != ')') {
                                            int i21116 = f686 + 49;
                                            f687 = i21116 % 128;
                                            int i21117 = i21116 % 2;
                                            aVar.f697 = rect3;
                                            zM619 = m619(rect3, view, hashSet);
                                            if (zM619) {
                                                c12 = 'Y';
                                            } else {
                                                c12 = '\\';
                                            }
                                            if (c12 != '\\') {
                                                aVar.f699 = 1.0d;
                                            }
                                            if (zM619) {
                                                c13 = 21;
                                            } else {
                                                c13 = 18;
                                            }
                                            if (c13 != 21) {
                                                iM615 = m615(rect3, hashSet);
                                                if (iM615 > 0) {
                                                    c14 = ']';
                                                } else {
                                                    c14 = 26;
                                                }
                                                if (c14 != ']') {
                                                    str2 = "{}";
                                                } else {
                                                    int i21118 = f686 + 87;
                                                    f687 = i21118 % 128;
                                                    int i21119 = i21118 % 2;
                                                    double d13 = iM615;
                                                    str2 = "{}";
                                                    double d14 = iWidth2;
                                                    Double.isNaN(d13);
                                                    Double.isNaN(d14);
                                                    aVar.f699 = d13 / d14;
                                                }
                                                double d15 = iWidth2 - iM615;
                                                double d16 = iWidth;
                                                Double.isNaN(d15);
                                                Double.isNaN(d16);
                                                aVar.f698 = d15 / d16;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                str2 = "{}";
            } else {
                str2 = "{}";
            }
            if (this.f694 != null) {
                z9 = false;
            } else {
                z9 = true;
            }
            if (z9) {
                this.f691 = aVar;
                this.f694 = new JSONObject(m621(m616(this.f691.f697, displayMetrics)));
                z10 = true;
            } else {
                i8 = f686 + 99;
                f687 = i8 % 128;
                if (i8 % 2 == 0) {
                    c6 = 'B';
                } else {
                    c6 = 16;
                }
                if (c6 != 'B') {
                    if (aVar.f698 == this.f691.f698) {
                        z20 = true;
                    } else {
                        z20 = false;
                    }
                    if (z20) {
                        if (aVar.f697.equals(this.f691.f697)) {
                            c8 = 24;
                        } else {
                            c8 = '*';
                        }
                        if (c8 == '*') {
                        }
                    }
                } else {
                    if (aVar.f698 == this.f691.f698) {
                        c7 = 'R';
                    } else {
                        c7 = 3;
                    }
                    if (c7 != 'R') {
                        if (aVar.f697.equals(this.f691.f697)) {
                            c8 = 24;
                        } else {
                            c8 = '*';
                        }
                        if (c8 == '*') {
                        }
                    }
                }
                this.f691 = aVar;
                this.f694 = new JSONObject(m621(m616(this.f691.f697, displayMetrics)));
                z10 = true;
            }
            map2.put("coveredPercent", Double.valueOf(aVar.f699));
            if (this.f690 != null) {
                z11 = true;
            } else {
                z11 = false;
            }
            if (z11) {
                i7 = f686 + 9;
                f687 = i7 % 128;
                if (i7 % 2 == 0) {
                    c5 = 7;
                    c4 = 'Y';
                } else {
                    c4 = 'Y';
                    c5 = 'Y';
                }
                if (c5 != c4) {
                    if (!rect.equals(this.f695)) {
                        this.f695 = rect;
                        this.f690 = new JSONObject(m621(m616(rect, displayMetrics)));
                        z10 = true;
                    }
                } else if (!rect.equals(this.f695)) {
                    this.f695 = rect;
                    this.f690 = new JSONObject(m621(m616(rect, displayMetrics)));
                    z10 = true;
                }
            } else {
                this.f695 = rect;
                this.f690 = new JSONObject(m621(m616(rect, displayMetrics)));
                z10 = true;
            }
            if (this.f696 != null) {
                z12 = false;
            } else {
                z12 = true;
            }
            if (!z12) {
            }
            this.f692 = rect2;
            this.f696 = new JSONObject(m621(m616(rect2, displayMetrics)));
            z10 = true;
            if (this.f689 != null) {
                z13 = false;
            } else {
                z13 = true;
            }
            if (z13) {
                this.f689 = map2;
                z10 = true;
            } else {
                i6 = f687 + 99;
                f686 = i6 % 128;
                if (i6 % 2 != 0) {
                    if (map2.equals(this.f689)) {
                        z18 = true;
                        z19 = true;
                    } else {
                        z18 = true;
                        z19 = false;
                    }
                    if (z19 != z18) {
                        this.f689 = map2;
                        z10 = true;
                    }
                } else if (!map2.equals(this.f689)) {
                    this.f689 = map2;
                    z10 = true;
                }
            }
            locationM563 = k.m554().m563();
            if (k.m555(locationM563, this.f688)) {
                z14 = true;
            } else {
                z14 = false;
            }
            if (z14) {
                int i38 = f687 + 103;
                f686 = i38 % 128;
                int i39 = i38 % 2;
                this.f688 = locationM563;
                z10 = true;
            }
            if (z10) {
                z15 = false;
            } else {
                z15 = true;
            }
            if (z15) {
                return;
            }
            int i310 = f686 + 57;
            f687 = i310 % 128;
            int i311 = i310 % 2;
            jSONObject = new JSONObject(this.f689);
            jSONObject.accumulate("screen", this.f690);
            jSONObject.accumulate(Constants.ParametersKeys.VIEW, this.f696);
            jSONObject.accumulate("visible", this.f694);
            jSONObject.accumulate("maybe", this.f694);
            jSONObject.accumulate("visiblePercent", Double.valueOf(this.f691.f698));
            if (locationM563 != null) {
                z16 = true;
                z17 = true;
            } else {
                z16 = true;
                z17 = false;
            }
            if (z17 == z16) {
                if (locationM563 == null) {
                    c3 = 'B';
                } else {
                    c3 = 'X';
                }
                if (c3 != 'X') {
                    int i312 = f687 + 95;
                    f686 = i312 % 128;
                    int i313 = i312 % 2;
                    map = null;
                } else {
                    map = new HashMap();
                    map.put(LocationConst.LATITUDE, Double.toString(locationM563.getLatitude()));
                    map.put(LocationConst.LONGITUDE, Double.toString(locationM563.getLongitude()));
                    map.put("timestamp", Long.toString(locationM563.getTime()));
                    map.put("horizontalAccuracy", Float.toString(locationM563.getAccuracy()));
                }
                if (map == null) {
                    i4 = 1;
                    i5 = 0;
                } else {
                    i4 = 1;
                    i5 = 1;
                }
                if (i5 != i4) {
                    int i314 = f686 + i4;
                    f687 = i314 % 128;
                    int i315 = i314 % 2;
                    jSONObject2 = null;
                } else {
                    jSONObject2 = new JSONObject(map);
                }
                jSONObject.accumulate("location", jSONObject2);
            }
            string = jSONObject.toString();
            this.f693 = string;
            return;
        } catch (Exception e2) {
            e = e2;
            str2 = string;
        }
        o.m569(e);
        this.f693 = str2;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static float m614(View view) {
        float alpha = view.getAlpha();
        while (true) {
            if ((view != null ? 'S' : 'c') != 'S') {
                break;
            }
            if ((view.getParent() != null ? '\r' : (char) 18) != 18) {
                if ((((double) alpha) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 'D' : (char) 5) == 5) {
                    break;
                }
                int i = f686 + 121;
                f687 = i % 128;
                int i2 = i % 2;
                if ((view.getParent() instanceof View ? 'H' : (char) 1) != 'H') {
                    break;
                }
                int i3 = f686 + 117;
                f687 = i3 % 128;
                int i4 = i3 % 2;
                alpha *= ((View) view.getParent()).getAlpha();
                view = (View) view.getParent();
            } else {
                break;
            }
        }
        return alpha;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static Rect m617(View view) {
        return (view != null ? '\n' : 'Z') != 'Z' ? m620(view) : new Rect(0, 0, 0, 0);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static void m618(ArrayDeque<b> arrayDeque, b bVar, boolean z) {
        if ((bVar.f700 instanceof ViewGroup ? '?' : '/') != '/') {
            int i = f687 + 107;
            f686 = i % 128;
            int i2 = i % 2;
            if (!(!(bVar.f700 instanceof ListView))) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) bVar.f700;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                arrayDeque.add(new b(viewGroup.getChildAt(childCount), bVar.f702 + 1, z));
            }
        }
    }

    @VisibleForTesting
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static int m615(Rect rect, Set<Rect> set) {
        if (set.isEmpty()) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() { // from class: com.moat.analytics.mobile.iro.y.2
            @Override // java.util.Comparator
            public final /* synthetic */ int compare(Rect rect2, Rect rect3) {
                return Integer.valueOf(rect2.top).compareTo(Integer.valueOf(rect3.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            if (!(it.hasNext())) {
                break;
            }
            int i = f686 + 101;
            f687 = i % 128;
            int i2 = i % 2;
            Rect rect2 = (Rect) it.next();
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i3 = 0;
        int iWidth = 0;
        while (true) {
            if (i3 >= arrayList2.size() - 1) {
                return iWidth;
            }
            int i4 = f687 + 99;
            f686 = i4 % 128;
            int i5 = i4 % 2;
            int i6 = i3 + 1;
            if ((!((Integer) arrayList2.get(i3)).equals(arrayList2.get(i6)) ? 'a' : '1') != '1') {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i3)).intValue(), rect.top, ((Integer) arrayList2.get(i6)).intValue(), rect.bottom);
                int i7 = rect.top;
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if ((it2.hasNext() ? '^' : (char) 17) == 17) {
                        break;
                    }
                    Rect rect4 = (Rect) it2.next();
                    if (Rect.intersects(rect4, rect3)) {
                        if ((rect4.bottom > i7 ? (char) 3 : '/') == 3) {
                            iWidth += rect3.width() * (rect4.bottom - Math.max(i7, rect4.top));
                            i7 = rect4.bottom;
                        }
                        if (rect4.bottom == rect3.bottom) {
                            break;
                        }
                    }
                }
            }
            i3 = i6;
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static Map<String, String> m621(Rect rect) {
        HashMap map = new HashMap();
        map.put("x", String.valueOf(rect.left));
        map.put("y", String.valueOf(rect.top));
        map.put(com.my.target.i.WIDTH, String.valueOf(rect.right - rect.left));
        map.put(com.my.target.i.HEIGHT, String.valueOf(rect.bottom - rect.top));
        return map;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static Rect m616(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        if (!(f != 0.0f)) {
            return rect;
        }
        return new Rect(Math.round(rect.left / f), Math.round(rect.top / f), Math.round(rect.right / f), Math.round(rect.bottom / f));
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static Rect m620(View view) {
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    /* JADX WARN: Code duplicated, block: B:141:0x01b7 A[PHI: r15
  0x01b7: PHI (r15v5 java.util.ArrayList) = (r15v4 java.util.ArrayList), (r15v6 java.util.ArrayList) binds: [B:139:0x01b3, B:131:0x019d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:143:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:144:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:148:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:150:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:151:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:154:0x01db  */
    /* JADX WARN: Code duplicated, block: B:156:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:157:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:159:0x01eb A[Catch: Exception -> 0x036f, TRY_ENTER, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:161:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:162:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:165:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:166:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:167:0x01fb A[Catch: Exception -> 0x036f, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:169:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:170:0x0201  */
    /* JADX WARN: Code duplicated, block: B:172:0x0204 A[Catch: Exception -> 0x036f, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:174:0x0212  */
    /* JADX WARN: Code duplicated, block: B:175:0x0214 A[Catch: Exception -> 0x036f, TRY_LEAVE, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:177:0x0218  */
    /* JADX WARN: Code duplicated, block: B:178:0x021a  */
    /* JADX WARN: Code duplicated, block: B:180:0x021d  */
    /* JADX WARN: Code duplicated, block: B:182:0x0228  */
    /* JADX WARN: Code duplicated, block: B:183:0x022b  */
    /* JADX WARN: Code duplicated, block: B:186:0x0231 A[Catch: Exception -> 0x036f, TRY_ENTER, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:188:0x023f  */
    /* JADX WARN: Code duplicated, block: B:189:0x0241  */
    /* JADX WARN: Code duplicated, block: B:191:0x0244  */
    /* JADX WARN: Code duplicated, block: B:192:0x0245 A[Catch: Exception -> 0x036f, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:194:0x0253  */
    /* JADX WARN: Code duplicated, block: B:195:0x0255  */
    /* JADX WARN: Code duplicated, block: B:197:0x0258  */
    /* JADX WARN: Code duplicated, block: B:222:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:223:0x02aa  */
    /* JADX WARN: Code duplicated, block: B:225:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:227:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:228:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:231:0x02c2 A[Catch: Exception -> 0x036f, TRY_ENTER, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:233:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:234:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:236:0x02d3  */
    /* JADX WARN: Code duplicated, block: B:237:0x02d5 A[Catch: Exception -> 0x036f, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:239:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:240:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:243:0x02ea  */
    /* JADX WARN: Code duplicated, block: B:244:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:245:0x02ee A[Catch: Exception -> 0x036f, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:247:0x02fa A[Catch: Exception -> 0x036f, TRY_LEAVE, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:249:0x032e  */
    /* JADX WARN: Code duplicated, block: B:250:0x0331  */
    /* JADX WARN: Code duplicated, block: B:256:0x0344  */
    /* JADX WARN: Code duplicated, block: B:257:0x0347  */
    /* JADX WARN: Code duplicated, block: B:276:0x005b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:277:0x009e A[EDGE_INSN: B:277:0x009e->B:52:0x009e BREAK  A[LOOP:0: B:4:0x000e->B:49:0x0095], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:282:0x0338 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:32:0x0057  */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    /* JADX WARN: Code duplicated, block: B:37:0x006a  */
    /* JADX WARN: Code duplicated, block: B:39:0x0070 A[Catch: Exception -> 0x036f, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x007b  */
    /* JADX WARN: Code duplicated, block: B:42:0x007e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0082  */
    /* JADX WARN: Code duplicated, block: B:46:0x008e  */
    /* JADX WARN: Code duplicated, block: B:48:0x0091 A[Catch: Exception -> 0x036f, TRY_ENTER, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x0099 A[Catch: Exception -> 0x036f, TRY_LEAVE, TryCatch #0 {Exception -> 0x036f, blocks: (B:3:0x0007, B:4:0x000e, B:17:0x0037, B:62:0x00bc, B:73:0x00d4, B:74:0x00ec, B:88:0x0116, B:94:0x012d, B:198:0x025a, B:208:0x0285, B:220:0x02a0, B:231:0x02c2, B:237:0x02d5, B:245:0x02ee, B:247:0x02fa, B:215:0x0295, B:100:0x0139, B:105:0x0146, B:107:0x0151, B:112:0x015a, B:114:0x0162, B:159:0x01eb, B:172:0x0204, B:167:0x01fb, B:175:0x0214, B:186:0x0231, B:192:0x0245, B:127:0x018e, B:133:0x01a0, B:263:0x035f, B:68:0x00c9, B:23:0x0043, B:39:0x0070, B:48:0x0091, B:49:0x0095, B:50:0x0099), top: B:272:0x0007 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:247:0x02fa, please report this as an issue */
    @VisibleForTesting
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static boolean m619(Rect rect, @NonNull View view, Set<Rect> set) {
        char c;
        int i;
        char c2;
        int i2;
        Object parent;
        ArrayList arrayList;
        boolean z;
        char c3;
        char c4;
        boolean z2;
        int i3;
        char c5;
        boolean z3;
        boolean z4;
        int i4;
        boolean z5;
        boolean z6;
        char c6;
        Rect rectM620;
        char c7;
        int i5;
        char c8;
        boolean z7;
        int i6;
        char c9;
        char c10;
        boolean z8;
        boolean z9 = false;
        try {
            ArrayList arrayList2 = new ArrayList();
            View view2 = view;
            int i7 = 0;
            while (true) {
                if ((view2.getParent() == null ? ':' : (char) 30) == ':') {
                    int i8 = f687 + 9;
                    f686 = i8 % 128;
                    if (i8 % 2 == 0) {
                        if (view2 == view.getRootView()) {
                            i7++;
                            if (i7 > 50) {
                                c = 'a';
                            } else {
                                c = ']';
                            }
                            if (c == ']') {
                                arrayList2.add(0, view2);
                                if (view2.getParent() instanceof View) {
                                    c2 = '\'';
                                } else {
                                    c2 = '\r';
                                }
                                if (c2 == '\r') {
                                    break;
                                    break;
                                }
                                i2 = f687 + 67;
                                f686 = i2 % 128;
                                if (i2 % 2 == 0) {
                                    parent = view2.getParent();
                                } else {
                                    parent = view2.getParent();
                                }
                                view2 = (View) parent;
                            } else {
                                i = f687 + 115;
                                f686 = i % 128;
                                if (i % 2 != 0) {
                                }
                                arrayList2 = null;
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (view2 == view.getRootView()) {
                            i7++;
                            if (i7 > 50) {
                                c = 'a';
                            } else {
                                c = ']';
                            }
                            if (c == ']') {
                                arrayList2.add(0, view2);
                                if (view2.getParent() instanceof View) {
                                    c2 = '\'';
                                } else {
                                    c2 = '\r';
                                }
                                if (c2 == '\r') {
                                    break;
                                    break;
                                }
                                i2 = f687 + 67;
                                f686 = i2 % 128;
                                if (i2 % 2 == 0) {
                                    parent = view2.getParent();
                                } else {
                                    parent = view2.getParent();
                                }
                                view2 = (View) parent;
                            } else {
                                i = f687 + 115;
                                f686 = i % 128;
                                if (i % 2 != 0) {
                                }
                                arrayList2 = null;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    i7++;
                    if (i7 > 50) {
                        c = 'a';
                    } else {
                        c = ']';
                    }
                    if (c == ']') {
                        arrayList2.add(0, view2);
                        if (view2.getParent() instanceof View) {
                            c2 = '\'';
                        } else {
                            c2 = '\r';
                        }
                        if (c2 == '\r') {
                            break;
                        }
                        i2 = f687 + 67;
                        f686 = i2 % 128;
                        if (i2 % 2 == 0) {
                            parent = view2.getParent();
                        } else {
                            parent = view2.getParent();
                        }
                        view2 = (View) parent;
                    } else {
                        i = f687 + 115;
                        f686 = i % 128;
                        if (i % 2 != 0) {
                        }
                        arrayList2 = null;
                        break;
                    }
                }
            }
            if ((arrayList2 != null ? '\t' : 'M') == 'M') {
                return false;
            }
            int i9 = f686 + 81;
            f687 = i9 % 128;
            if ((i9 % 2 == 0 ? '^' : '%') != '%') {
                if (arrayList2.isEmpty()) {
                    return false;
                }
            } else {
                if (!(!arrayList2.isEmpty())) {
                    return false;
                }
            }
            View rootView = view.getRootView();
            ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.add(new b(rootView, 0, true));
            com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", view, "starting covering rect search");
            int i10 = 0;
            boolean z10 = false;
            while (true) {
                if ((!arrayDeque.isEmpty() ? '(' : '5') == '5') {
                    return false;
                }
                int i11 = f687 + 25;
                f686 = i11 % 128;
                int i12 = i11 % 2;
                if ((i10 < 250 ? (char) 22 : '2') != 22) {
                    return false;
                }
                i10++;
                b bVar = (b) arrayDeque.pollLast();
                if ((bVar.f700.equals(view) ? '@' : 'A') != '@') {
                    if (!bVar.f701) {
                        arrayList = arrayList2;
                    } else {
                        View view3 = arrayList2.size() > bVar.f702 ? (View) arrayList2.get(bVar.f702) : null;
                        if (!(bVar.f700 != view3)) {
                            m618((ArrayDeque<b>) arrayDeque, bVar, true);
                            arrayList = arrayList2;
                        } else {
                            View view4 = bVar.f700;
                            if ((view4.isShown() ? (char) 16 : '/') != 16) {
                                arrayList = arrayList2;
                            } else {
                                int i13 = f687 + 121;
                                f686 = i13 % 128;
                                if ((i13 % 2 != 0 ? (char) 18 : '\"') != '\"') {
                                    arrayList = arrayList2;
                                    if (!(((double) view4.getAlpha()) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
                                        z = true;
                                    }
                                } else {
                                    arrayList = arrayList2;
                                    if ((((double) view4.getAlpha()) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? '>' : 'D') == '>') {
                                        z = true;
                                    }
                                }
                                if (z) {
                                    c3 = 'T';
                                } else {
                                    c3 = '$';
                                }
                                if (c3 == 'T') {
                                    int i14 = f687 + 43;
                                    f686 = i14 % 128;
                                    int i15 = i14 % 2;
                                    if (z10) {
                                        c4 = 'R';
                                    } else {
                                        c4 = 23;
                                    }
                                    if (c4 != 23) {
                                        i4 = f687 + 9;
                                        f686 = i4 % 128;
                                        if (i4 % 2 != 0) {
                                            z5 = false;
                                        } else {
                                            z5 = true;
                                        }
                                        if (z5) {
                                            if (Build.VERSION.SDK_INT >= 21) {
                                                c6 = 'I';
                                            } else {
                                                c6 = 29;
                                            }
                                            if (c6 != 29) {
                                                if (bVar.f700.getElevation() < view3.getElevation()) {
                                                }
                                            }
                                        } else {
                                            if (Build.VERSION.SDK_INT >= 21) {
                                                z6 = true;
                                            } else {
                                                z6 = false;
                                            }
                                            if (z6) {
                                                if (bVar.f700.getElevation() < view3.getElevation()) {
                                                }
                                            }
                                        }
                                        z9 = false;
                                    } else {
                                        if (Build.VERSION.SDK_INT >= 21) {
                                            z2 = false;
                                        } else {
                                            z2 = true;
                                        }
                                        if (!z2) {
                                            i3 = f686 + 13;
                                            f687 = i3 % 128;
                                            if (i3 % 2 == 0) {
                                                c5 = 26;
                                            } else {
                                                c5 = '\\';
                                            }
                                            if (c5 != '\\') {
                                                if (bVar.f700.getElevation() > view3.getElevation()) {
                                                    z4 = true;
                                                } else {
                                                    z4 = false;
                                                }
                                                if (z4) {
                                                    z9 = false;
                                                }
                                                arrayList2 = arrayList;
                                                z9 = false;
                                            } else {
                                                if (bVar.f700.getElevation() > view3.getElevation()) {
                                                    z3 = false;
                                                } else {
                                                    z3 = true;
                                                }
                                                if (z3) {
                                                    z9 = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            z = false;
                            if (z) {
                                c3 = 'T';
                            } else {
                                c3 = '$';
                            }
                            if (c3 == 'T') {
                                int i16 = f687 + 43;
                                f686 = i16 % 128;
                                int i17 = i16 % 2;
                                if (z10) {
                                    c4 = 'R';
                                } else {
                                    c4 = 23;
                                }
                                if (c4 != 23) {
                                    i4 = f687 + 9;
                                    f686 = i4 % 128;
                                    if (i4 % 2 != 0) {
                                        z5 = false;
                                    } else {
                                        z5 = true;
                                    }
                                    if (z5) {
                                        if (Build.VERSION.SDK_INT >= 21) {
                                            c6 = 'I';
                                        } else {
                                            c6 = 29;
                                        }
                                        if (c6 != 29) {
                                            if (bVar.f700.getElevation() < view3.getElevation()) {
                                            }
                                        }
                                    } else {
                                        if (Build.VERSION.SDK_INT >= 21) {
                                            z6 = true;
                                        } else {
                                            z6 = false;
                                        }
                                        if (z6) {
                                            if (bVar.f700.getElevation() < view3.getElevation()) {
                                            }
                                        }
                                    }
                                    z9 = false;
                                } else {
                                    if (Build.VERSION.SDK_INT >= 21) {
                                        z2 = false;
                                    } else {
                                        z2 = true;
                                    }
                                    if (!z2) {
                                        i3 = f686 + 13;
                                        f687 = i3 % 128;
                                        if (i3 % 2 == 0) {
                                            c5 = 26;
                                        } else {
                                            c5 = '\\';
                                        }
                                        if (c5 != '\\') {
                                            if (bVar.f700.getElevation() > view3.getElevation()) {
                                                z4 = true;
                                            } else {
                                                z4 = false;
                                            }
                                            if (z4) {
                                                z9 = false;
                                            }
                                            arrayList2 = arrayList;
                                            z9 = false;
                                        } else {
                                            if (bVar.f700.getElevation() > view3.getElevation()) {
                                                z3 = false;
                                            } else {
                                                z3 = true;
                                            }
                                            if (z3) {
                                                z9 = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        arrayList2 = arrayList;
                        z9 = false;
                    }
                    m618((ArrayDeque<b>) arrayDeque, bVar, z9);
                    if (ViewGroup.class.equals(bVar.f700.getClass().getSuperclass())) {
                        int i18 = f686 + 17;
                        f687 = i18 % 128;
                        if (i18 % 2 == 0) {
                            if ((Build.VERSION.SDK_INT >= 19 ? '*' : 'I') != 'I') {
                                if (bVar.f700.getBackground() != null) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                if (z7) {
                                    i6 = f687 + 71;
                                    f686 = i6 % 128;
                                    if (i6 % 2 != 0) {
                                        c9 = 11;
                                    } else {
                                        c9 = '%';
                                    }
                                    if (c9 != 11) {
                                        if (bVar.f700.getBackground().getAlpha() != 0) {
                                            z8 = true;
                                        } else {
                                            z8 = false;
                                        }
                                        if (!z8) {
                                            rectM620 = m620(bVar.f700);
                                            if (rectM620.setIntersect(rect, rectM620)) {
                                                com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", bVar.f700, "Covered by " + bVar.f700.getClass().getSimpleName() + "-" + rectM620.toString());
                                                set.add(rectM620);
                                                if (rectM620.contains(rect)) {
                                                    c7 = '(';
                                                } else {
                                                    c7 = 'D';
                                                }
                                                if (c7 == '(') {
                                                    i5 = f687 + 85;
                                                    f686 = i5 % 128;
                                                    if (i5 % 2 != 0) {
                                                        c8 = '$';
                                                    } else {
                                                        c8 = 'E';
                                                    }
                                                    if (c8 != 'E') {
                                                    }
                                                    return true;
                                                }
                                            }
                                        }
                                    } else {
                                        if (bVar.f700.getBackground().getAlpha() != 0) {
                                            c10 = '%';
                                        } else {
                                            c10 = 'Y';
                                        }
                                        if (c10 != '%') {
                                            rectM620 = m620(bVar.f700);
                                            if (rectM620.setIntersect(rect, rectM620)) {
                                                com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", bVar.f700, "Covered by " + bVar.f700.getClass().getSimpleName() + "-" + rectM620.toString());
                                                set.add(rectM620);
                                                if (rectM620.contains(rect)) {
                                                    c7 = '(';
                                                } else {
                                                    c7 = 'D';
                                                }
                                                if (c7 == '(') {
                                                    i5 = f687 + 85;
                                                    f686 = i5 % 128;
                                                    if (i5 % 2 != 0) {
                                                        c8 = '$';
                                                    } else {
                                                        c8 = 'E';
                                                    }
                                                    if (c8 != 'E') {
                                                    }
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            arrayList2 = arrayList;
                            z9 = false;
                        } else {
                            if (!(Build.VERSION.SDK_INT < 19)) {
                                if (bVar.f700.getBackground() != null) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                if (z7) {
                                    i6 = f687 + 71;
                                    f686 = i6 % 128;
                                    if (i6 % 2 != 0) {
                                        c9 = 11;
                                    } else {
                                        c9 = '%';
                                    }
                                    if (c9 != 11) {
                                        if (bVar.f700.getBackground().getAlpha() != 0) {
                                            z8 = true;
                                        } else {
                                            z8 = false;
                                        }
                                        if (!z8) {
                                            rectM620 = m620(bVar.f700);
                                            if (rectM620.setIntersect(rect, rectM620)) {
                                                com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", bVar.f700, "Covered by " + bVar.f700.getClass().getSimpleName() + "-" + rectM620.toString());
                                                set.add(rectM620);
                                                if (rectM620.contains(rect)) {
                                                    c7 = '(';
                                                } else {
                                                    c7 = 'D';
                                                }
                                                if (c7 == '(') {
                                                    i5 = f687 + 85;
                                                    f686 = i5 % 128;
                                                    if (i5 % 2 != 0) {
                                                        c8 = '$';
                                                    } else {
                                                        c8 = 'E';
                                                    }
                                                    if (c8 != 'E') {
                                                    }
                                                    return true;
                                                }
                                            }
                                            arrayList2 = arrayList;
                                            z9 = false;
                                        }
                                    } else {
                                        if (bVar.f700.getBackground().getAlpha() != 0) {
                                            c10 = '%';
                                        } else {
                                            c10 = 'Y';
                                        }
                                        if (c10 != '%') {
                                            rectM620 = m620(bVar.f700);
                                            if (rectM620.setIntersect(rect, rectM620)) {
                                                com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", bVar.f700, "Covered by " + bVar.f700.getClass().getSimpleName() + "-" + rectM620.toString());
                                                set.add(rectM620);
                                                if (rectM620.contains(rect)) {
                                                    c7 = '(';
                                                } else {
                                                    c7 = 'D';
                                                }
                                                if (c7 == '(') {
                                                    i5 = f687 + 85;
                                                    f686 = i5 % 128;
                                                    if (i5 % 2 != 0) {
                                                        c8 = '$';
                                                    } else {
                                                        c8 = 'E';
                                                    }
                                                    if (c8 != 'E') {
                                                    }
                                                    return true;
                                                }
                                            }
                                            arrayList2 = arrayList;
                                            z9 = false;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        rectM620 = m620(bVar.f700);
                        if (rectM620.setIntersect(rect, rectM620)) {
                            com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", bVar.f700, "Covered by " + bVar.f700.getClass().getSimpleName() + "-" + rectM620.toString());
                            set.add(rectM620);
                            if (rectM620.contains(rect)) {
                                c7 = '(';
                            } else {
                                c7 = 'D';
                            }
                            if (c7 == '(') {
                                i5 = f687 + 85;
                                f686 = i5 % 128;
                                if (i5 % 2 != 0) {
                                    c8 = '$';
                                } else {
                                    c8 = 'E';
                                }
                                if (c8 != 'E') {
                                }
                                return true;
                            }
                        }
                        arrayList2 = arrayList;
                        z9 = false;
                    }
                    arrayList2 = arrayList;
                    z9 = false;
                } else {
                    ArrayList arrayList3 = arrayList2;
                    int i19 = f687 + 73;
                    f686 = i19 % 128;
                    int i20 = i19 % 2;
                    com.moat.analytics.mobile.iro.b.m462("VisibilityInfo", rect, "found target");
                    arrayList2 = arrayList3;
                    z9 = false;
                    z10 = true;
                }
            }
        } catch (Exception e) {
            o.m569(e);
            return false;
        }
    }
}
