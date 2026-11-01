package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.ironsource.sdk.constants.Constants;
import com.my.target.ah;
import com.my.target.common.models.AudioData;
import com.my.target.common.models.VideoData;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VastParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class bi<T extends ah> {
    private static final String[] fI = {MimeTypes.VIDEO_MP4, "application/vnd.apple.mpegurl", "application/x-mpegurl"};
    private static final String[] fJ = {bj.gn};

    @NonNull
    private final b adConfig;

    @Nullable
    private String ctaText;

    @NonNull
    private final af ey;

    @NonNull
    private final Context ez;
    private boolean fO;

    @Nullable
    private af fP;

    @NonNull
    private final ArrayList<aq> cf = new ArrayList<>();

    @NonNull
    private final ArrayList<ap> fK = new ArrayList<>();

    @NonNull
    private final ArrayList<aj> fL = new ArrayList<>();

    @NonNull
    private final ArrayList<aq> fM = new ArrayList<>();

    @NonNull
    private final ArrayList<ak<T>> fN = new ArrayList<>();

    @NonNull
    public static <T extends ah> bi<T> a(@NonNull b bVar, @NonNull af afVar, @NonNull Context context) {
        return new bi<>(bVar, afVar, context);
    }

    private static void a(@NonNull XmlPullParser xmlPullParser) {
        if (e(xmlPullParser) != 2) {
            return;
        }
        int i = 1;
        while (i != 0) {
            int iB = b(xmlPullParser);
            if (iB == 2) {
                i++;
            } else if (iB == 3) {
                i--;
            }
        }
    }

    private static int b(@NonNull XmlPullParser xmlPullParser) {
        try {
            return xmlPullParser.next();
        } catch (IOException e) {
            g.a(e.getMessage());
            return Integer.MIN_VALUE;
        } catch (XmlPullParserException e2) {
            g.a(e2.getMessage());
            return Integer.MIN_VALUE;
        }
    }

    @NonNull
    private static String c(@NonNull XmlPullParser xmlPullParser) {
        String text;
        if (b(xmlPullParser) == 4) {
            text = xmlPullParser.getText();
            d(xmlPullParser);
        } else {
            g.a("No text: " + xmlPullParser.getName());
            text = "";
        }
        return text.trim();
    }

    @Nullable
    private static String a(@NonNull String str, @NonNull XmlPullParser xmlPullParser) {
        return xmlPullParser.getAttributeValue(null, str);
    }

    @NonNull
    private static String F(@NonNull String str) {
        return str.replaceAll("&amp;", Constants.RequestParameters.AMPERSAND).replaceAll("&lt;", "<").replaceAll("&gt;", ">").trim();
    }

    private static int d(@NonNull XmlPullParser xmlPullParser) {
        try {
            return xmlPullParser.nextTag();
        } catch (IOException e) {
            g.a(e.getMessage());
            return Integer.MIN_VALUE;
        } catch (XmlPullParserException e2) {
            g.a(e2.getMessage());
            return Integer.MIN_VALUE;
        }
    }

    private static int e(@NonNull XmlPullParser xmlPullParser) {
        try {
            return xmlPullParser.getEventType();
        } catch (XmlPullParserException e) {
            g.a(e.getMessage());
            return Integer.MIN_VALUE;
        }
    }

    private bi(@NonNull b bVar, @NonNull af afVar, @NonNull Context context) {
        this.adConfig = bVar;
        this.ey = afVar;
        this.ez = context;
    }

    @NonNull
    public ArrayList<ak<T>> at() {
        return this.fN;
    }

    @NonNull
    public ArrayList<aq> F() {
        return this.cf;
    }

    public void G(@NonNull String str) {
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        try {
            xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            av();
            for (int iE = e(xmlPullParserNewPullParser); iE != 1 && iE != Integer.MIN_VALUE; iE = b(xmlPullParserNewPullParser)) {
                if (iE == 2 && bj.fS.equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                    f(xmlPullParserNewPullParser);
                }
            }
        } catch (XmlPullParserException e) {
            g.a("Unable to parse VAST: " + e.getMessage());
        }
    }

    @Nullable
    public af au() {
        return this.fP;
    }

    private void a(@Nullable String str, @NonNull String str2, @Nullable String str3) {
        az.z(str2).A(str3).g(this.adConfig.getSlotId()).C(str).B(this.ey.getUrl()).e(this.ez);
    }

    private void av() {
        ArrayList<aq> arrayListF = this.ey.F();
        if (arrayListF != null) {
            this.cf.addAll(arrayListF);
        }
        ArrayList<aj> companionBanners = this.ey.getCompanionBanners();
        if (companionBanners != null) {
            this.fL.addAll(companionBanners);
        }
    }

    private void aw() {
        for (ak<T> akVar : this.fN) {
            ar statHolder = akVar.getStatHolder();
            statHolder.a(this.ey.N(), akVar.getDuration());
            if (!TextUtils.isEmpty(this.ctaText)) {
                akVar.setCtaText(this.ctaText);
            } else if (!TextUtils.isEmpty(this.ey.getCtaText())) {
                akVar.setCtaText(this.ey.getCtaText());
            }
            for (ap apVar : this.fK) {
                a(apVar.Z(), apVar.getUrl(), akVar);
            }
            Iterator<aq> it = this.fM.iterator();
            while (it.hasNext()) {
                statHolder.b(it.next());
            }
            Iterator<aj> it2 = this.fL.iterator();
            while (it2.hasNext()) {
                akVar.addCompanion(it2.next());
            }
        }
    }

    private void f(@NonNull XmlPullParser xmlPullParser) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2 && bj.fU.equals(xmlPullParser.getName())) {
                g(xmlPullParser);
            }
        }
    }

    private void g(@NonNull XmlPullParser xmlPullParser) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                String name = xmlPullParser.getName();
                if (bj.fW.equals(name)) {
                    this.fO = true;
                    g.a("VAST file contains wrapped ad information.");
                    int iG = this.ey.G();
                    if (iG < 5) {
                        a(xmlPullParser, iG);
                    } else {
                        g.a("got VAST wrapper, but max redirects limit exceeded");
                        a(xmlPullParser);
                    }
                } else if (bj.fV.equals(name)) {
                    this.fO = false;
                    g.a("VAST file contains inline ad information.");
                    h(xmlPullParser);
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }

    private void a(@NonNull XmlPullParser xmlPullParser, int i) {
        String strC = null;
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                String name = xmlPullParser.getName();
                if (bj.fX.equals(name)) {
                    j(xmlPullParser);
                } else if (bj.fY.equals(name)) {
                    k(xmlPullParser);
                } else if (bj.gl.equals(name)) {
                    i(xmlPullParser);
                } else if (bj.fT.equals(name)) {
                    strC = c(xmlPullParser);
                } else {
                    a(xmlPullParser);
                }
            }
        }
        if (strC != null) {
            this.fP = af.n(strC);
            this.fP.a(i + 1);
            this.fP.c(this.cf);
            af afVar = this.fP;
            String ctaText = this.ctaText;
            if (ctaText == null) {
                ctaText = this.ey.getCtaText();
            }
            afVar.setCtaText(ctaText);
            this.fP.b(this.fL);
            ar arVarN = this.fP.N();
            arVarN.e(this.fM);
            arVarN.f(this.fK);
            arVarN.a(this.ey.N(), -1.0f);
            this.ey.b(this.fP);
            return;
        }
        g.a("got VAST wrapper, but no vastAdTagUri");
    }

    private void h(@NonNull XmlPullParser xmlPullParser) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                String name = xmlPullParser.getName();
                if (bj.fX.equals(name)) {
                    j(xmlPullParser);
                } else if (name != null && name.equals(bj.fY)) {
                    k(xmlPullParser);
                } else if (name != null && name.equals(bj.gl)) {
                    i(xmlPullParser);
                } else {
                    a(xmlPullParser);
                }
            }
        }
        aw();
    }

    private void i(@NonNull XmlPullParser xmlPullParser) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                if (bj.gm.equals(xmlPullParser.getName())) {
                    String strA = a("type", xmlPullParser);
                    for (String str : fJ) {
                        if (str.equals(strA)) {
                            a(xmlPullParser, strA);
                        } else {
                            a(xmlPullParser);
                        }
                    }
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }

    private void a(@NonNull XmlPullParser xmlPullParser, String str) {
        if (bj.gn.equals(str)) {
            String strC = c(xmlPullParser);
            this.ctaText = cl.decode(strC);
            g.a("VAST linkTxt raw text: " + strC);
        }
    }

    private void j(@NonNull XmlPullParser xmlPullParser) {
        String strC = c(xmlPullParser);
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        this.cf.add(aq.b(aq.a.dB, strC));
        g.a("Impression tracker url for wrapper: " + strC);
    }

    private void k(@NonNull XmlPullParser xmlPullParser) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                if (bj.fZ.equals(xmlPullParser.getName())) {
                    String strA = a("id", xmlPullParser);
                    ak<T> akVarNewBanner = null;
                    if (!this.fO) {
                        akVarNewBanner = ak.newBanner();
                        if (strA == null) {
                            strA = "";
                        }
                        akVarNewBanner.setId(strA);
                    }
                    a(xmlPullParser, (ak) akVarNewBanner);
                    if (akVarNewBanner != null) {
                        if (akVarNewBanner.getDuration() > 0.0f) {
                            if (akVarNewBanner.getMediaData() != null) {
                                this.fN.add(akVarNewBanner);
                            } else {
                                a(akVarNewBanner.getId(), az.a.ej, "VAST has no valid mediaData");
                            }
                        } else {
                            a(akVarNewBanner.getId(), az.a.ej, "VAST has no valid Duration");
                        }
                    }
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }

    private void a(@NonNull XmlPullParser xmlPullParser, @Nullable ak akVar) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                String name = xmlPullParser.getName();
                if (bj.ga.equals(name)) {
                    a(xmlPullParser, akVar, a(bj.go, xmlPullParser));
                } else if (name != null && name.equals(bj.gL)) {
                    String strA = a(bj.required, xmlPullParser);
                    if (strA != null && !bj.gK.equals(strA) && !bj.gJ.equals(strA) && !"none".equals(strA)) {
                        a(akVar != null ? akVar.getId() : null, az.a.ei, "Wrong companion required attribute:" + strA);
                        strA = null;
                    }
                    a(xmlPullParser, akVar != null ? akVar.getId() : null, strA);
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }

    private void a(@NonNull XmlPullParser xmlPullParser, @Nullable String str, @Nullable String str2) {
        while (d(xmlPullParser) == 2) {
            b(xmlPullParser, str, str2);
        }
    }

    private void b(@NonNull XmlPullParser xmlPullParser, @Nullable String str, @Nullable String str2) {
        if (e(xmlPullParser) != 2) {
            return;
        }
        String name = xmlPullParser.getName();
        if (name != null && name.equals(bj.gM)) {
            String strA = a("width", xmlPullParser);
            String strA2 = a("height", xmlPullParser);
            String strA3 = a("id", xmlPullParser);
            aj ajVarNewBanner = aj.newBanner();
            if (strA3 == null) {
                strA3 = "";
            }
            ajVarNewBanner.setId(strA3);
            try {
                ajVarNewBanner.setWidth(Integer.parseInt(strA));
                ajVarNewBanner.setHeight(Integer.parseInt(strA2));
            } catch (NumberFormatException unused) {
                a(str, az.a.ei, "Unable  to convert required companion attributes, width = " + strA + " height = " + strA2);
            }
            ajVarNewBanner.setRequired(str2);
            String strA4 = a(bj.gN, xmlPullParser);
            String strA5 = a(bj.gO, xmlPullParser);
            try {
                if (!TextUtils.isEmpty(strA4)) {
                    ajVarNewBanner.setAssetWidth(Integer.parseInt(strA4));
                }
                if (!TextUtils.isEmpty(strA5)) {
                    ajVarNewBanner.setAssetHeight(Integer.parseInt(strA5));
                }
            } catch (NumberFormatException e) {
                g.a("wrong VAST asset dimensions: " + e.getMessage());
            }
            String strA6 = a(bj.gP, xmlPullParser);
            String strA7 = a(bj.gQ, xmlPullParser);
            try {
                if (!TextUtils.isEmpty(strA6)) {
                    ajVarNewBanner.setExpandedWidth(Integer.parseInt(strA6));
                }
                if (!TextUtils.isEmpty(strA7)) {
                    ajVarNewBanner.setExpandedHeight(Integer.parseInt(strA7));
                }
            } catch (NumberFormatException e2) {
                g.a("wrong VAST expanded dimensions " + e2.getMessage());
            }
            ajVarNewBanner.setAdSlotID(a(bj.adSlotID, xmlPullParser));
            ajVarNewBanner.setApiFramework(a(bj.apiFramework, xmlPullParser));
            this.fL.add(ajVarNewBanner);
            while (d(xmlPullParser) == 2) {
                String name2 = xmlPullParser.getName();
                if (bj.gR.equals(name2)) {
                    ajVarNewBanner.setStaticResource(cl.decode(c(xmlPullParser)));
                } else if (bj.gT.equals(name2)) {
                    ajVarNewBanner.setHtmlResource(cl.decode(c(xmlPullParser)));
                } else if (bj.gS.equals(name2)) {
                    ajVarNewBanner.setIframeResource(cl.decode(c(xmlPullParser)));
                } else if (bj.gj.equals(name2)) {
                    String strC = c(xmlPullParser);
                    if (!TextUtils.isEmpty(strC)) {
                        ajVarNewBanner.setTrackingLink(F(strC));
                    }
                } else if (bj.gk.equals(name2)) {
                    String strC2 = c(xmlPullParser);
                    if (!TextUtils.isEmpty(strC2)) {
                        ajVarNewBanner.getStatHolder().b(aq.b(aq.a.dF, strC2));
                    }
                } else if (bj.gc.equals(name2)) {
                    a(xmlPullParser, ajVarNewBanner);
                } else {
                    a(xmlPullParser);
                }
            }
            return;
        }
        a(xmlPullParser);
    }

    @VisibleForTesting
    float H(@NonNull String str) {
        String strSubstring;
        long j = 0;
        try {
            if (str.contains(".")) {
                int iIndexOf = str.indexOf(".");
                j = Long.parseLong(str.substring(iIndexOf + 1));
                if (j > 1000) {
                    return -1.0f;
                }
                strSubstring = str.substring(0, iIndexOf);
            } else {
                strSubstring = str;
            }
            String[] strArrSplit = strSubstring.split(":", 3);
            long j2 = Integer.parseInt(strArrSplit[0]);
            long j3 = Integer.parseInt(strArrSplit[1]);
            long j4 = Integer.parseInt(strArrSplit[2]);
            if (j2 >= 24 || j3 >= 60 || j4 >= 60) {
                return -1.0f;
            }
            Long.signum(j4);
            return (((j + (j4 * 1000)) + (j3 * ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS)) + (j2 * 3600000)) / 1000.0f;
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    private boolean b(@NonNull XmlPullParser xmlPullParser, @NonNull ak akVar) {
        float fH;
        try {
            fH = H(c(xmlPullParser));
        } catch (Exception unused) {
            fH = 0.0f;
        }
        if (fH <= 0.0f) {
            return false;
        }
        akVar.setDuration(fH);
        return true;
    }

    private void c(@NonNull XmlPullParser xmlPullParser, @Nullable ak akVar) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                String name = xmlPullParser.getName();
                if (bj.gh.equals(name)) {
                    if (akVar != null) {
                        String strC = c(xmlPullParser);
                        if (!TextUtils.isEmpty(strC)) {
                            akVar.setTrackingLink(F(strC));
                        }
                    }
                } else if (bj.gi.equals(name)) {
                    String strC2 = c(xmlPullParser);
                    if (!TextUtils.isEmpty(strC2)) {
                        this.fM.add(aq.b(aq.a.dF, strC2));
                    }
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }

    private void d(@NonNull XmlPullParser xmlPullParser, @NonNull ak akVar) {
        if (b.a.h.equals(this.adConfig.getFormat()) || "fullscreen".equals(this.adConfig.getFormat())) {
            f(xmlPullParser, akVar);
        } else if (b.a.i.equals(this.adConfig.getFormat())) {
            e(xmlPullParser, akVar);
        }
    }

    private void e(@NonNull XmlPullParser xmlPullParser, @NonNull ak<AudioData> akVar) {
        AudioData audioDataNewAudioData;
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                if (bj.gf.equals(xmlPullParser.getName())) {
                    String strA = a("type", xmlPullParser);
                    String strA2 = a("bitrate", xmlPullParser);
                    String strF = F(c(xmlPullParser));
                    if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strF) || !strA.toLowerCase(Locale.ROOT).trim().startsWith("audio")) {
                        audioDataNewAudioData = null;
                    } else {
                        int i = 0;
                        if (strA2 != null) {
                            try {
                                i = Integer.parseInt(strA2);
                            } catch (NumberFormatException unused) {
                            }
                        }
                        audioDataNewAudioData = AudioData.newAudioData(strF);
                        audioDataNewAudioData.setBitrate(i);
                    }
                    if (audioDataNewAudioData == null) {
                        g.a("Skipping unsupported VAST file (mimetype=" + strA + ",url=" + strF);
                    } else {
                        akVar.setMediaData(audioDataNewAudioData);
                    }
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00b4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x0086 A[SYNTHETIC] */
    private void f(@NonNull XmlPullParser xmlPullParser, @NonNull ak<VideoData> akVar) {
        int i;
        int i2;
        ArrayList arrayList = new ArrayList();
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                if (bj.gf.equals(xmlPullParser.getName())) {
                    String strA = a("type", xmlPullParser);
                    String strA2 = a("bitrate", xmlPullParser);
                    String strA3 = a("width", xmlPullParser);
                    String strA4 = a("height", xmlPullParser);
                    String strF = F(c(xmlPullParser));
                    VideoData videoDataNewVideoData = null;
                    if (!TextUtils.isEmpty(strA) && !TextUtils.isEmpty(strF)) {
                        int i3 = 0;
                        for (String str : fI) {
                            if (str.equals(strA)) {
                                if (strA3 != null) {
                                    try {
                                        i = Integer.parseInt(strA3);
                                    } catch (NumberFormatException unused) {
                                        i = 0;
                                        i2 = 0;
                                        if (i <= 0) {
                                            break;
                                        }
                                        videoDataNewVideoData = VideoData.newVideoData(strF, i, i2);
                                        videoDataNewVideoData.setBitrate(i3);
                                        break;
                                        if (videoDataNewVideoData == null) {
                                            g.a("Skipping unsupported VAST file (mimeType=" + strA + ",width=" + strA3 + ",height=" + strA4 + ",url=" + strF);
                                        } else {
                                            arrayList.add(videoDataNewVideoData);
                                        }
                                    }
                                } else {
                                    i = 0;
                                }
                                if (strA4 != null) {
                                    try {
                                        i2 = Integer.parseInt(strA4);
                                    } catch (NumberFormatException unused2) {
                                        i2 = 0;
                                    }
                                } else {
                                    i2 = 0;
                                }
                                if (strA2 != null) {
                                    try {
                                        i3 = Integer.parseInt(strA2);
                                    } catch (NumberFormatException unused3) {
                                    }
                                }
                                if (i <= 0 && i2 > 0) {
                                    videoDataNewVideoData = VideoData.newVideoData(strF, i, i2);
                                    videoDataNewVideoData.setBitrate(i3);
                                    break;
                                } else {
                                    break;
                                    break;
                                }
                            }
                        }
                    }
                    if (videoDataNewVideoData == null) {
                        g.a("Skipping unsupported VAST file (mimeType=" + strA + ",width=" + strA3 + ",height=" + strA4 + ",url=" + strF);
                    } else {
                        arrayList.add(videoDataNewVideoData);
                    }
                } else {
                    a(xmlPullParser);
                }
            }
        }
        akVar.setMediaData(VideoData.chooseBest(arrayList, this.adConfig.getVideoQuality()));
    }

    private void a(@NonNull XmlPullParser xmlPullParser, @Nullable ai aiVar) {
        while (d(xmlPullParser) == 2) {
            if (e(xmlPullParser) == 2) {
                if (bj.gd.equals(xmlPullParser.getName())) {
                    String strA = a("event", xmlPullParser);
                    String strA2 = a(bj.gD, xmlPullParser);
                    if (strA != null) {
                        if ("progress".equals(strA) && !TextUtils.isEmpty(strA2)) {
                            if (strA2.endsWith("%")) {
                                try {
                                    a(Integer.parseInt(strA2.replace("%", "")), c(xmlPullParser), aiVar);
                                } catch (Exception unused) {
                                    g.a("Unable to parse progress stat with value " + strA2);
                                }
                            } else {
                                b(strA2, c(xmlPullParser), aiVar);
                            }
                        } else {
                            c(strA, c(xmlPullParser), aiVar);
                        }
                    }
                    g.a("Added VAST tracking \"" + strA + "\"");
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }

    private void a(@NonNull String str, @NonNull String str2, @Nullable ai aiVar) {
        if (aiVar != null) {
            aiVar.getStatHolder().b(aq.b(str, str2));
        } else {
            this.fM.add(aq.b(str, str2));
        }
    }

    private void b(@NonNull String str, @NonNull String str2, @Nullable ai aiVar) {
        float fH;
        try {
            fH = H(str);
        } catch (Exception unused) {
            fH = -1.0f;
        }
        if (fH >= 0.0f) {
            ap apVarW = ap.w(str2);
            apVarW.c(fH);
            if (aiVar != null) {
                aiVar.getStatHolder().b(apVarW);
                return;
            } else {
                this.fM.add(apVarW);
                return;
            }
        }
        g.a("Unable to parse progress stat with value " + str);
    }

    private void a(float f, @NonNull String str, @Nullable ai aiVar) {
        ap apVarW = ap.w(str);
        if (aiVar != null && aiVar.getDuration() > 0.0f) {
            apVarW.c(aiVar.getDuration() * (f / 100.0f));
            aiVar.getStatHolder().b(apVarW);
        } else {
            apVarW.d(f);
            this.fK.add(apVarW);
        }
    }

    private void c(@NonNull String str, @NonNull String str2, @Nullable ai aiVar) {
        if ("start".equalsIgnoreCase(str)) {
            a(aq.a.du, str2, aiVar);
            return;
        }
        if (bj.gq.equalsIgnoreCase(str)) {
            a(25.0f, str2, aiVar);
            return;
        }
        if (bj.gr.equalsIgnoreCase(str)) {
            a(50.0f, str2, aiVar);
            return;
        }
        if (bj.gs.equalsIgnoreCase(str)) {
            a(75.0f, str2, aiVar);
            return;
        }
        if (bj.gt.equalsIgnoreCase(str)) {
            a(100.0f, str2, aiVar);
            return;
        }
        if ("creativeView".equalsIgnoreCase(str)) {
            a(aq.a.du, str2, aiVar);
            return;
        }
        if (bj.gv.equalsIgnoreCase(str)) {
            a(aq.a.dD, str2, aiVar);
            return;
        }
        if (bj.gw.equalsIgnoreCase(str)) {
            a(aq.a.dC, str2, aiVar);
            return;
        }
        if ("pause".equalsIgnoreCase(str)) {
            a(aq.a.dv, str2, aiVar);
            return;
        }
        if ("resume".equalsIgnoreCase(str)) {
            a(aq.a.dw, str2, aiVar);
            return;
        }
        if ("fullscreen".equalsIgnoreCase(str)) {
            a(aq.a.dz, str2, aiVar);
            return;
        }
        if (bj.gA.equalsIgnoreCase(str)) {
            a(aq.a.dA, str2, aiVar);
            return;
        }
        if ("skip".equalsIgnoreCase(str)) {
            a(aq.a.dy, str2, aiVar);
            return;
        }
        if ("error".equalsIgnoreCase(str)) {
            a("error", str2, aiVar);
        } else if (bj.gi.equalsIgnoreCase(str)) {
            a(aq.a.dF, str2, aiVar);
        } else if ("close".equalsIgnoreCase(str)) {
            a(aq.a.dy, str2, aiVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0064  */
    @VisibleForTesting
    void a(@NonNull ak akVar, @Nullable String str) {
        float fH;
        if (str == null) {
            fH = -1.0f;
        } else if (str.contains("%")) {
            int i = Integer.parseInt(str.substring(0, str.length() - 1));
            g.a("Linear skipoffset is " + str + " [%]");
            fH = (akVar.getDuration() / 100.0f) * ((float) i);
        } else if (str.contains(":")) {
            try {
                fH = H(str);
            } catch (Exception unused) {
                a(akVar.getId(), az.a.ei, "Failed to convert ISO time skipoffset string " + str);
                fH = -1.0f;
            }
        } else {
            fH = -1.0f;
        }
        if (fH > 0.0f) {
            akVar.setAllowCloseDelay(fH);
        }
    }

    private void a(@NonNull XmlPullParser xmlPullParser, @Nullable ak akVar, @Nullable String str) {
        while (d(xmlPullParser) == 2) {
            String name = xmlPullParser.getName();
            if (e(xmlPullParser) == 2) {
                if (bj.gb.equals(name)) {
                    if (akVar == null) {
                        continue;
                    } else if (!b(xmlPullParser, akVar)) {
                        return;
                    } else {
                        a(akVar, str);
                    }
                } else if (bj.gc.equals(name)) {
                    a(xmlPullParser, (ai) akVar);
                } else if (bj.ge.equals(name)) {
                    if (akVar == null) {
                        continue;
                    } else {
                        d(xmlPullParser, akVar);
                        if (akVar.getMediaData() == null) {
                            g.a("Unable to find valid mediafile!");
                            return;
                        }
                    }
                } else if (bj.gg.equals(name)) {
                    c(xmlPullParser, akVar);
                } else {
                    a(xmlPullParser);
                }
            }
        }
    }
}
