package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.util.CollectionUtils;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ab;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzamf extends zzamp {
    private static final Set<String> zzdfc = CollectionUtils.setOf(Constants.ForceClosePosition.TOP_LEFT, Constants.ForceClosePosition.TOP_RIGHT, "top-center", TtmlNode.CENTER, Constants.ForceClosePosition.BOTTOM_LEFT, Constants.ForceClosePosition.BOTTOM_RIGHT, "bottom-center");
    private int height;
    private final Object lock;
    private int width;
    private zzams zzcxp;
    private final zzbbc zzcyx;
    private final Activity zzdes;
    private String zzdfd;
    private boolean zzdfe;
    private int zzdff;
    private int zzdfg;
    private int zzdfh;
    private int zzdfi;
    private zzbct zzdfj;
    private ImageView zzdfk;
    private LinearLayout zzdfl;
    private PopupWindow zzdfm;
    private RelativeLayout zzdfn;
    private ViewGroup zzdfo;

    public zzamf(zzbbc zzbbcVar, zzams zzamsVar) {
        super(zzbbcVar, ab.bA);
        this.zzdfd = Constants.ForceClosePosition.TOP_RIGHT;
        this.zzdfe = true;
        this.zzdff = 0;
        this.zzdfg = 0;
        this.height = -1;
        this.zzdfh = 0;
        this.zzdfi = 0;
        this.width = -1;
        this.lock = new Object();
        this.zzcyx = zzbbcVar;
        this.zzdes = zzbbcVar.zzxl();
        this.zzcxp = zzamsVar;
    }

    /* JADX WARN: Code duplicated, block: B:124:0x023b  */
    /* JADX WARN: Code duplicated, block: B:125:0x023d A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:127:0x0241 A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:128:0x0252 A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:130:0x0274  */
    /* JADX WARN: Code duplicated, block: B:131:0x0276 A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:133:0x027b A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x027f  */
    /* JADX WARN: Code duplicated, block: B:137:0x0284 A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:138:0x0287 A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:140:0x028e A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:143:0x029d A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x02a4 A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x02c6 A[Catch: all -> 0x04c3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0134, B:62:0x013a, B:64:0x013e, B:67:0x0144, B:69:0x0148, B:71:0x014c, B:143:0x029d, B:144:0x02a2, B:146:0x02a4, B:148:0x02c6, B:150:0x02ca, B:152:0x02da, B:154:0x030e, B:158:0x0344, B:159:0x0378, B:189:0x03ce, B:196:0x03fd, B:197:0x0415, B:198:0x0434, B:200:0x043c, B:201:0x0445, B:202:0x046b, B:205:0x046e, B:207:0x047e, B:209:0x0488, B:211:0x049a, B:212:0x04b3, B:208:0x0483, B:190:0x03d5, B:191:0x03dc, B:192:0x03e3, B:193:0x03ea, B:194:0x03f0, B:195:0x03f7, B:161:0x037c, B:164:0x0386, B:167:0x0390, B:170:0x039a, B:173:0x03a4, B:176:0x03ae, B:153:0x0309, B:214:0x04b5, B:215:0x04ba, B:125:0x023d, B:127:0x0241, B:128:0x0252, B:135:0x0280, B:137:0x0284, B:141:0x0294, B:138:0x0287, B:140:0x028e, B:131:0x0276, B:133:0x027b, B:72:0x0153, B:74:0x0157, B:75:0x015d, B:102:0x01aa, B:110:0x021b, B:112:0x021e, B:114:0x0221, B:116:0x0225, B:103:0x01b8, B:106:0x01e8, B:104:0x01c9, B:105:0x01dc, B:107:0x01eb, B:108:0x0202, B:109:0x0212, B:77:0x0161, B:80:0x016b, B:83:0x0175, B:86:0x017f, B:89:0x0189, B:92:0x0193, B:120:0x022d, B:121:0x0233, B:217:0x04bc, B:218:0x04c1), top: B:223:0x0007, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x019d  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void zzg(Map<String, String> map) {
        boolean z;
        int[] iArrZzf;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        ViewParent parent;
        byte b;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        synchronized (this.lock) {
            if (this.zzdes == null) {
                zzdn("Not an activity context. Cannot resize.");
                return;
            }
            if (this.zzcyx.zzyw() == null) {
                zzdn("Webview is not yet available, size is not set.");
                return;
            }
            if (this.zzcyx.zzyw().zzaaq()) {
                zzdn("Is interstitial. Cannot resize an interstitial.");
                return;
            }
            if (this.zzcyx.zzzd()) {
                zzdn("Cannot resize an expanded banner.");
                return;
            }
            if (!TextUtils.isEmpty(map.get("width"))) {
                com.google.android.gms.ads.internal.zzp.zzjy();
                this.width = zzatv.zzed(map.get("width"));
            }
            if (!TextUtils.isEmpty(map.get("height"))) {
                com.google.android.gms.ads.internal.zzp.zzjy();
                this.height = zzatv.zzed(map.get("height"));
            }
            if (!TextUtils.isEmpty(map.get(aa.d.bl))) {
                com.google.android.gms.ads.internal.zzp.zzjy();
                this.zzdfh = zzatv.zzed(map.get(aa.d.bl));
            }
            if (!TextUtils.isEmpty(map.get(aa.d.bm))) {
                com.google.android.gms.ads.internal.zzp.zzjy();
                this.zzdfi = zzatv.zzed(map.get(aa.d.bm));
            }
            if (!TextUtils.isEmpty(map.get(aa.d.bk))) {
                this.zzdfe = Boolean.parseBoolean(map.get(aa.d.bk));
            }
            String str = map.get(aa.d.bj);
            if (!TextUtils.isEmpty(str)) {
                this.zzdfd = str;
            }
            if (!(this.width >= 0 && this.height >= 0)) {
                zzdn("Invalid width and height options. Cannot resize.");
                return;
            }
            Window window = this.zzdes.getWindow();
            if (window != null && window.getDecorView() != null) {
                int[] iArrZze = com.google.android.gms.ads.internal.zzp.zzjy().zze(this.zzdes);
                int[] iArrZzf2 = com.google.android.gms.ads.internal.zzp.zzjy().zzf(this.zzdes);
                int i11 = iArrZze[0];
                int i12 = iArrZze[1];
                byte b2 = -1;
                if (this.width < 50 || this.width > i11) {
                    zzatm.zzeu("Width is too small or too large.");
                } else if (this.height < 50 || this.height > i12) {
                    zzatm.zzeu("Height is too small or too large.");
                } else if (this.height == i12 && this.width == i11) {
                    zzatm.zzeu("Cannot resize to a full-screen ad.");
                } else {
                    if (this.zzdfe) {
                        String str2 = this.zzdfd;
                        switch (str2.hashCode()) {
                            case -1364013995:
                                if (!str2.equals(TtmlNode.CENTER)) {
                                    b = -1;
                                } else {
                                    b = 2;
                                }
                                break;
                            case -1012429441:
                                if (!str2.equals(Constants.ForceClosePosition.TOP_LEFT)) {
                                    b = -1;
                                } else {
                                    b = 0;
                                }
                                break;
                            case -655373719:
                                if (!str2.equals(Constants.ForceClosePosition.BOTTOM_LEFT)) {
                                    b = -1;
                                } else {
                                    b = 3;
                                }
                                break;
                            case 1163912186:
                                if (!str2.equals(Constants.ForceClosePosition.BOTTOM_RIGHT)) {
                                    b = -1;
                                } else {
                                    b = 5;
                                }
                                break;
                            case 1288627767:
                                if (!str2.equals("bottom-center")) {
                                    b = -1;
                                } else {
                                    b = 4;
                                }
                                break;
                            case 1755462605:
                                if (!str2.equals("top-center")) {
                                    b = -1;
                                } else {
                                    b = 1;
                                }
                                break;
                            default:
                                b = -1;
                                break;
                        }
                        if (b == 0) {
                            i5 = this.zzdff + this.zzdfh;
                            i6 = this.zzdfg;
                            i7 = this.zzdfi;
                        } else if (b == 1) {
                            i5 = ((this.zzdff + this.zzdfh) + (this.width / 2)) - 25;
                            i6 = this.zzdfg;
                            i7 = this.zzdfi;
                        } else {
                            if (b != 2) {
                                if (b == 3) {
                                    i5 = this.zzdff + this.zzdfh;
                                    i9 = this.zzdfg + this.zzdfi;
                                    i10 = this.height;
                                } else if (b == 4) {
                                    i5 = ((this.zzdff + this.zzdfh) + (this.width / 2)) - 25;
                                    i9 = this.zzdfg + this.zzdfi;
                                    i10 = this.height;
                                } else if (b == 5) {
                                    i5 = ((this.zzdff + this.zzdfh) + this.width) - 50;
                                    i9 = this.zzdfg + this.zzdfi;
                                    i10 = this.height;
                                } else {
                                    i5 = ((this.zzdff + this.zzdfh) + this.width) - 50;
                                    i6 = this.zzdfg;
                                    i7 = this.zzdfi;
                                }
                                i8 = (i9 + i10) - 50;
                            } else {
                                i5 = ((this.zzdff + this.zzdfh) + (this.width / 2)) - 25;
                                i8 = ((this.zzdfg + this.zzdfi) + (this.height / 2)) - 25;
                            }
                            if (i5 >= 0 || i5 + 50 > i11 || i8 < iArrZzf2[0] || i8 + 50 > iArrZzf2[1]) {
                            }
                            if (!z) {
                                iArr = null;
                            } else if (this.zzdfe) {
                                iArr = new int[]{this.zzdff + this.zzdfh, this.zzdfg + this.zzdfi};
                            } else {
                                int[] iArrZze2 = com.google.android.gms.ads.internal.zzp.zzjy().zze(this.zzdes);
                                iArrZzf = com.google.android.gms.ads.internal.zzp.zzjy().zzf(this.zzdes);
                                i = iArrZze2[0];
                                i2 = this.zzdff + this.zzdfh;
                                i3 = this.zzdfg + this.zzdfi;
                                if (i2 < 0) {
                                    i4 = 0;
                                } else if (this.width + i2 > i) {
                                    i4 = i - this.width;
                                } else {
                                    i4 = i2;
                                }
                                if (i3 < iArrZzf[0]) {
                                    i3 = iArrZzf[0];
                                } else if (this.height + i3 > iArrZzf[1]) {
                                    i3 = iArrZzf[1] - this.height;
                                }
                                iArr = new int[]{i4, i3};
                            }
                            if (iArr == null) {
                                zzdn("Resize location out of screen or close button is not visible.");
                                return;
                            }
                            zzuo.zzof();
                            int iZza = zzawe.zza(this.zzdes, this.width);
                            zzuo.zzof();
                            int iZza2 = zzawe.zza(this.zzdes, this.height);
                            parent = this.zzcyx.getView().getParent();
                            if (parent == null && (parent instanceof ViewGroup)) {
                                ((ViewGroup) parent).removeView(this.zzcyx.getView());
                                if (this.zzdfm == null) {
                                    this.zzdfo = (ViewGroup) parent;
                                    com.google.android.gms.ads.internal.zzp.zzjy();
                                    Bitmap bitmapZzk = zzatv.zzk(this.zzcyx.getView());
                                    this.zzdfk = new ImageView(this.zzdes);
                                    this.zzdfk.setImageBitmap(bitmapZzk);
                                    this.zzdfj = this.zzcyx.zzyw();
                                    this.zzdfo.addView(this.zzdfk);
                                } else {
                                    this.zzdfm.dismiss();
                                }
                                this.zzdfn = new RelativeLayout(this.zzdes);
                                this.zzdfn.setBackgroundColor(0);
                                this.zzdfn.setLayoutParams(new ViewGroup.LayoutParams(iZza, iZza2));
                                com.google.android.gms.ads.internal.zzp.zzjy();
                                this.zzdfm = zzatv.zza((View) this.zzdfn, iZza, iZza2, false);
                                this.zzdfm.setOutsideTouchable(true);
                                this.zzdfm.setTouchable(true);
                                this.zzdfm.setClippingEnabled(!this.zzdfe);
                                this.zzdfn.addView(this.zzcyx.getView(), -1, -1);
                                this.zzdfl = new LinearLayout(this.zzdes);
                                zzuo.zzof();
                                int iZza3 = zzawe.zza(this.zzdes, 50);
                                zzuo.zzof();
                                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iZza3, zzawe.zza(this.zzdes, 50));
                                String str3 = this.zzdfd;
                                switch (str3.hashCode()) {
                                    case -1364013995:
                                        if (str3.equals(TtmlNode.CENTER)) {
                                            b2 = 2;
                                        }
                                        break;
                                    case -1012429441:
                                        if (str3.equals(Constants.ForceClosePosition.TOP_LEFT)) {
                                            b2 = 0;
                                        }
                                        break;
                                    case -655373719:
                                        if (str3.equals(Constants.ForceClosePosition.BOTTOM_LEFT)) {
                                            b2 = 3;
                                        }
                                        break;
                                    case 1163912186:
                                        if (str3.equals(Constants.ForceClosePosition.BOTTOM_RIGHT)) {
                                            b2 = 5;
                                        }
                                        break;
                                    case 1288627767:
                                        if (str3.equals("bottom-center")) {
                                            b2 = 4;
                                        }
                                        break;
                                    case 1755462605:
                                        if (str3.equals("top-center")) {
                                            b2 = 1;
                                        }
                                        break;
                                }
                                if (b2 == 0) {
                                    layoutParams.addRule(10);
                                    layoutParams.addRule(9);
                                } else if (b2 == 1) {
                                    layoutParams.addRule(10);
                                    layoutParams.addRule(14);
                                } else if (b2 == 2) {
                                    layoutParams.addRule(13);
                                } else if (b2 == 3) {
                                    layoutParams.addRule(12);
                                    layoutParams.addRule(9);
                                } else if (b2 == 4) {
                                    layoutParams.addRule(12);
                                    layoutParams.addRule(14);
                                } else if (b2 == 5) {
                                    layoutParams.addRule(12);
                                    layoutParams.addRule(11);
                                } else {
                                    layoutParams.addRule(10);
                                    layoutParams.addRule(11);
                                }
                                this.zzdfl.setOnClickListener(new zzami(this));
                                this.zzdfl.setContentDescription("Close button");
                                this.zzdfn.addView(this.zzdfl, layoutParams);
                                try {
                                    PopupWindow popupWindow = this.zzdfm;
                                    View decorView = window.getDecorView();
                                    zzuo.zzof();
                                    int iZza4 = zzawe.zza(this.zzdes, iArr[0]);
                                    zzuo.zzof();
                                    popupWindow.showAtLocation(decorView, 0, iZza4, zzawe.zza(this.zzdes, iArr[1]));
                                    int i13 = iArr[0];
                                    int i14 = iArr[1];
                                    if (this.zzcxp != null) {
                                        this.zzcxp.zzc(i13, i14, this.width, this.height);
                                    }
                                    this.zzcyx.zza(zzbct.zzp(iZza, iZza2));
                                    zza(iArr[0], iArr[1] - com.google.android.gms.ads.internal.zzp.zzjy().zzf(this.zzdes)[0], this.width, this.height);
                                    zzdp(aa.f.bs);
                                    return;
                                } catch (RuntimeException e) {
                                    String strValueOf = String.valueOf(e.getMessage());
                                    zzdn(strValueOf.length() != 0 ? "Cannot show popup window: ".concat(strValueOf) : new String("Cannot show popup window: "));
                                    this.zzdfn.removeView(this.zzcyx.getView());
                                    if (this.zzdfo != null) {
                                        this.zzdfo.removeView(this.zzdfk);
                                        this.zzdfo.addView(this.zzcyx.getView());
                                        this.zzcyx.zza(this.zzdfj);
                                    }
                                    return;
                                }
                            }
                            zzdn("Webview is detached, probably in the middle of a resize or expand.");
                            return;
                        }
                        i8 = i6 + i7;
                        if (i5 >= 0) {
                        }
                    }
                    z = true;
                    if (!z) {
                        iArr = null;
                    } else if (this.zzdfe) {
                        iArr = new int[]{this.zzdff + this.zzdfh, this.zzdfg + this.zzdfi};
                    } else {
                        int[] iArrZze3 = com.google.android.gms.ads.internal.zzp.zzjy().zze(this.zzdes);
                        iArrZzf = com.google.android.gms.ads.internal.zzp.zzjy().zzf(this.zzdes);
                        i = iArrZze3[0];
                        i2 = this.zzdff + this.zzdfh;
                        i3 = this.zzdfg + this.zzdfi;
                        if (i2 < 0) {
                            i4 = 0;
                        } else if (this.width + i2 > i) {
                            i4 = i - this.width;
                        } else {
                            i4 = i2;
                        }
                        if (i3 < iArrZzf[0]) {
                            i3 = iArrZzf[0];
                        } else if (this.height + i3 > iArrZzf[1]) {
                            i3 = iArrZzf[1] - this.height;
                        }
                        iArr = new int[]{i4, i3};
                    }
                    if (iArr == null) {
                        zzdn("Resize location out of screen or close button is not visible.");
                        return;
                    }
                    zzuo.zzof();
                    int iZza5 = zzawe.zza(this.zzdes, this.width);
                    zzuo.zzof();
                    int iZza6 = zzawe.zza(this.zzdes, this.height);
                    parent = this.zzcyx.getView().getParent();
                    if (parent == null) {
                    }
                    zzdn("Webview is detached, probably in the middle of a resize or expand.");
                    return;
                }
                z = false;
                if (!z) {
                    iArr = null;
                } else if (this.zzdfe) {
                    iArr = new int[]{this.zzdff + this.zzdfh, this.zzdfg + this.zzdfi};
                } else {
                    int[] iArrZze4 = com.google.android.gms.ads.internal.zzp.zzjy().zze(this.zzdes);
                    iArrZzf = com.google.android.gms.ads.internal.zzp.zzjy().zzf(this.zzdes);
                    i = iArrZze4[0];
                    i2 = this.zzdff + this.zzdfh;
                    i3 = this.zzdfg + this.zzdfi;
                    if (i2 < 0) {
                        i4 = 0;
                    } else if (this.width + i2 > i) {
                        i4 = i - this.width;
                    } else {
                        i4 = i2;
                    }
                    if (i3 < iArrZzf[0]) {
                        i3 = iArrZzf[0];
                    } else if (this.height + i3 > iArrZzf[1]) {
                        i3 = iArrZzf[1] - this.height;
                    }
                    iArr = new int[]{i4, i3};
                }
                if (iArr == null) {
                    zzdn("Resize location out of screen or close button is not visible.");
                    return;
                }
                zzuo.zzof();
                int iZza7 = zzawe.zza(this.zzdes, this.width);
                zzuo.zzof();
                int iZza8 = zzawe.zza(this.zzdes, this.height);
                parent = this.zzcyx.getView().getParent();
                if (parent == null) {
                }
                zzdn("Webview is detached, probably in the middle of a resize or expand.");
                return;
            }
            zzdn("Activity context is not ready, cannot get window or decor view.");
        }
    }

    public final void zzv(boolean z) {
        synchronized (this.lock) {
            if (this.zzdfm != null) {
                this.zzdfm.dismiss();
                this.zzdfn.removeView(this.zzcyx.getView());
                if (this.zzdfo != null) {
                    this.zzdfo.removeView(this.zzdfk);
                    this.zzdfo.addView(this.zzcyx.getView());
                    this.zzcyx.zza(this.zzdfj);
                }
                if (z) {
                    zzdp(aa.f.bq);
                    if (this.zzcxp != null) {
                        this.zzcxp.zzsh();
                    }
                }
                this.zzdfm = null;
                this.zzdfn = null;
                this.zzdfo = null;
                this.zzdfl = null;
            }
        }
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.lock) {
            this.zzdff = i;
            this.zzdfg = i2;
            PopupWindow popupWindow = this.zzdfm;
        }
    }

    public final boolean zzsg() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzdfm != null;
        }
        return z;
    }

    public final void zzh(int i, int i2) {
        this.zzdff = i;
        this.zzdfg = i2;
    }
}
