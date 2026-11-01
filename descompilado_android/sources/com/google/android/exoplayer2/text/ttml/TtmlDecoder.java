package com.google.android.exoplayer2.text.ttml;

import android.text.Layout;
import android.util.Log;
import com.facebook.appevents.UserDataStore;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ColorParser;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.my.target.i;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final CellResolution DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);

    public TtmlDecoder() {
        super(TAG);
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            this.xmlParserFactory.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public TtmlSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            TtmlSubtitle ttmlSubtitle = null;
            map2.put("", new TtmlRegion(null));
            int i2 = 0;
            xmlPullParserNewPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), null);
            LinkedList linkedList = new LinkedList();
            FrameAndTickRate frameAndTickRates = DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution cellResolution = DEFAULT_CELL_RESOLUTION;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) linkedList.peekLast();
                if (i2 == 0) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            frameAndTickRates = parseFrameAndTickRates(xmlPullParserNewPullParser);
                            cellResolution = parseCellResolution(xmlPullParserNewPullParser, DEFAULT_CELL_RESOLUTION);
                        }
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName());
                        } else if (TtmlNode.TAG_HEAD.equals(name)) {
                            parseHeader(xmlPullParserNewPullParser, map, map2, cellResolution);
                        } else {
                            try {
                                TtmlNode node = parseNode(xmlPullParserNewPullParser, ttmlNode, map2, frameAndTickRates);
                                linkedList.addLast(node);
                                if (ttmlNode != null) {
                                    ttmlNode.addChild(node);
                                }
                            } catch (SubtitleDecoderException e) {
                                Log.w(TAG, "Suppressing parser error", e);
                                i2++;
                            }
                        }
                        i2++;
                    } else if (eventType == 4) {
                        ttmlNode.addChild(TtmlNode.buildTextNode(xmlPullParserNewPullParser.getText()));
                    } else if (eventType == 3) {
                        if (xmlPullParserNewPullParser.getName().equals(TtmlNode.TAG_TT)) {
                            ttmlSubtitle = new TtmlSubtitle((TtmlNode) linkedList.getLast(), map, map2);
                        }
                        linkedList.removeLast();
                    }
                } else if (eventType == 2) {
                    i2++;
                } else if (eventType == 3) {
                    i2--;
                }
                xmlPullParserNewPullParser.next();
            }
            return ttmlSubtitle;
        } catch (IOException e2) {
            throw new IllegalStateException("Unexpected error when reading input.", e2);
        } catch (XmlPullParserException e3) {
            throw new SubtitleDecoderException("Unable to decode source", e3);
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int i = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split(" ");
            if (strArrSplit.length != 2) {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
            f = Integer.parseInt(strArrSplit[0]) / Integer.parseInt(strArrSplit[1]);
        }
        int i2 = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i2 = Integer.parseInt(attributeValue3);
        }
        int i3 = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i3 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(i * f, i2, i3);
    }

    private CellResolution parseCellResolution(XmlPullParser xmlPullParser, CellResolution cellResolution) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return cellResolution;
        }
        try {
            int i = Integer.parseInt(matcher.group(1));
            int i2 = Integer.parseInt(matcher.group(2));
            if (i == 0 || i2 == 0) {
                throw new SubtitleDecoderException("Invalid cell resolution " + i + " " + i2);
            }
            return new CellResolution(i, i2);
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return cellResolution;
        }
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, CellResolution cellResolution) throws XmlPullParserException, IOException {
        TtmlRegion regionAttributes;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        styleAttributes.chain(map.get(str));
                    }
                }
                if (styleAttributes.getId() != null) {
                    map.put(styleAttributes.getId(), styleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region") && (regionAttributes = parseRegionAttributes(xmlPullParser, cellResolution)) != null) {
                map2.put(regionAttributes.id, regionAttributes);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0099  */
    private TtmlRegion parseRegionAttributes(XmlPullParser xmlPullParser, CellResolution cellResolution) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id");
        if (attributeValue == null) {
            return null;
        }
        String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, "origin");
        if (attributeValue2 != null) {
            Matcher matcher = PERCENTAGE_COORDINATES.matcher(attributeValue2);
            if (matcher.matches()) {
                try {
                    float f = Float.parseFloat(matcher.group(1)) / 100.0f;
                    int i = 2;
                    float f2 = Float.parseFloat(matcher.group(2)) / 100.0f;
                    String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
                    if (attributeValue3 != null) {
                        Matcher matcher2 = PERCENTAGE_COORDINATES.matcher(attributeValue3);
                        if (matcher2.matches()) {
                            try {
                                float f3 = Float.parseFloat(matcher2.group(1)) / 100.0f;
                                float f4 = Float.parseFloat(matcher2.group(2)) / 100.0f;
                                String attributeValue4 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_DISPLAY_ALIGN);
                                if (attributeValue4 != null) {
                                    String lowerInvariant = Util.toLowerInvariant(attributeValue4);
                                    byte b = -1;
                                    int iHashCode = lowerInvariant.hashCode();
                                    if (iHashCode != -1364013995) {
                                        if (iHashCode == 92734940 && lowerInvariant.equals("after")) {
                                            b = 1;
                                        }
                                    } else if (lowerInvariant.equals(TtmlNode.CENTER)) {
                                        b = 0;
                                    }
                                    if (b == 0) {
                                        f2 += f4 / 2.0f;
                                        i = 1;
                                    } else if (b != 1) {
                                        i = 0;
                                    } else {
                                        f2 += f4;
                                    }
                                } else {
                                    i = 0;
                                }
                                return new TtmlRegion(attributeValue, f, f2, 0, i, f3, 1, 1.0f / cellResolution.rows);
                            } catch (NumberFormatException unused) {
                                Log.w(TAG, "Ignoring region with malformed extent: " + attributeValue2);
                                return null;
                            }
                        }
                        Log.w(TAG, "Ignoring region with unsupported extent: " + attributeValue2);
                        return null;
                    }
                    Log.w(TAG, "Ignoring region without an extent");
                    return null;
                } catch (NumberFormatException unused2) {
                    Log.w(TAG, "Ignoring region with malformed origin: " + attributeValue2);
                    return null;
                }
            }
            Log.w(TAG, "Ignoring region with unsupported origin: " + attributeValue2);
            return null;
        }
        Log.w(TAG, "Ignoring region without an origin");
        return null;
    }

    private String[] parseStyleIds(String str) {
        return str.split("\\s+");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:34:0x0079  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private TtmlStyle parseStyleAttributes(XmlPullParser xmlPullParser, TtmlStyle ttmlStyle) {
        int attributeCount = xmlPullParser.getAttributeCount();
        TtmlStyle ttmlStyleCreateIfNull = ttmlStyle;
        for (int i = 0; i < attributeCount; i++) {
            String attributeValue = xmlPullParser.getAttributeValue(i);
            byte b = -1;
            switch (xmlPullParser.getAttributeName(i)) {
                case "id":
                    if (!"style".equals(xmlPullParser.getName())) {
                        break;
                    } else {
                        ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setId(attributeValue);
                        break;
                    }
                    break;
                case "backgroundColor":
                    ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull);
                    try {
                        ttmlStyleCreateIfNull.setBackgroundColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused) {
                        Log.w(TAG, "Failed parsing background value: " + attributeValue);
                        break;
                    }
                    break;
                case "color":
                    ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull);
                    try {
                        ttmlStyleCreateIfNull.setFontColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        Log.w(TAG, "Failed parsing color value: " + attributeValue);
                        break;
                    }
                    break;
                case "fontFamily":
                    ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setFontFamily(attributeValue);
                    break;
                case "fontSize":
                    try {
                        ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull);
                        parseFontSize(attributeValue, ttmlStyleCreateIfNull);
                        break;
                    } catch (SubtitleDecoderException unused3) {
                        Log.w(TAG, "Failed parsing fontSize value: " + attributeValue);
                        break;
                    }
                    break;
                case "fontWeight":
                    ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setBold(TtmlNode.BOLD.equalsIgnoreCase(attributeValue));
                    break;
                case "fontStyle":
                    ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setItalic(TtmlNode.ITALIC.equalsIgnoreCase(attributeValue));
                    break;
                case "textAlign":
                    String lowerInvariant = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant.hashCode()) {
                        case -1364013995:
                            if (lowerInvariant.equals(TtmlNode.CENTER)) {
                                b = 4;
                            }
                            break;
                        case 100571:
                            if (lowerInvariant.equals("end")) {
                                b = 3;
                            }
                            break;
                        case 3317767:
                            if (lowerInvariant.equals(TtmlNode.LEFT)) {
                                b = 0;
                            }
                            break;
                        case 108511772:
                            if (lowerInvariant.equals(TtmlNode.RIGHT)) {
                                b = 2;
                            }
                            break;
                        case 109757538:
                            if (lowerInvariant.equals("start")) {
                                b = 1;
                            }
                            break;
                    }
                    if (b == 0) {
                        ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                        break;
                    } else {
                        if (b == 1) {
                            ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                        } else if (b == 2) {
                            ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                        } else if (b == 3) {
                            ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                        } else if (b == 4) {
                            ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setTextAlign(Layout.Alignment.ALIGN_CENTER);
                        }
                        break;
                    }
                    break;
                case "textDecoration":
                    String lowerInvariant2 = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant2.hashCode()) {
                        case -1461280213:
                            if (lowerInvariant2.equals(TtmlNode.NO_UNDERLINE)) {
                                b = 3;
                            }
                            break;
                        case -1026963764:
                            if (lowerInvariant2.equals(TtmlNode.UNDERLINE)) {
                                b = 2;
                            }
                            break;
                        case 913457136:
                            if (lowerInvariant2.equals(TtmlNode.NO_LINETHROUGH)) {
                                b = 1;
                            }
                            break;
                        case 1679736913:
                            if (lowerInvariant2.equals(TtmlNode.LINETHROUGH)) {
                                b = 0;
                            }
                            break;
                    }
                    if (b == 0) {
                        ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setLinethrough(true);
                        break;
                    } else {
                        if (b == 1) {
                            ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setLinethrough(false);
                        } else if (b == 2) {
                            ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setUnderline(true);
                        } else if (b == 3) {
                            ttmlStyleCreateIfNull = createIfNull(ttmlStyleCreateIfNull).setUnderline(false);
                        }
                        break;
                    }
                    break;
            }
        }
        return ttmlStyleCreateIfNull;
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:22:0x006b  */
    /* JADX WARN: Code duplicated, block: B:62:0x00e8  */
    private TtmlNode parseNode(XmlPullParser xmlPullParser, TtmlNode ttmlNode, Map<String, TtmlRegion> map, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
        long j;
        long j2;
        byte b;
        int attributeCount = xmlPullParser.getAttributeCount();
        TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, null);
        String[] strArr = null;
        String str = "";
        long timeExpression = C.TIME_UNSET;
        long timeExpression2 = C.TIME_UNSET;
        long timeExpression3 = C.TIME_UNSET;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            switch (attributeName) {
                case "region":
                    b = 4;
                    break;
                case "dur":
                    b = 2;
                    break;
                case "end":
                    b = 1;
                    break;
                case "begin":
                    b = 0;
                    break;
                case "style":
                    b = 3;
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                timeExpression = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (b == 1) {
                timeExpression2 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (b == 2) {
                timeExpression3 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (b == 3) {
                String[] styleIds = parseStyleIds(attributeValue);
                if (styleIds.length > 0) {
                    strArr = styleIds;
                }
            } else if (b == 4 && map.containsKey(attributeValue)) {
                str = attributeValue;
            }
        }
        if (ttmlNode != null) {
            long j3 = ttmlNode.startTimeUs;
            j = C.TIME_UNSET;
            if (j3 != C.TIME_UNSET) {
                if (timeExpression != C.TIME_UNSET) {
                    timeExpression += ttmlNode.startTimeUs;
                }
                if (timeExpression2 != C.TIME_UNSET) {
                    timeExpression2 += ttmlNode.startTimeUs;
                }
            }
        } else {
            j = C.TIME_UNSET;
        }
        long j4 = timeExpression;
        if (timeExpression2 != j) {
            j2 = timeExpression2;
        } else if (timeExpression3 != j) {
            j2 = timeExpression3 + j4;
        } else if (ttmlNode == null || ttmlNode.endTimeUs == j) {
            j2 = timeExpression2;
        } else {
            j2 = ttmlNode.endTimeUs;
        }
        return TtmlNode.buildNode(xmlPullParser.getName(), j4, j2, styleAttributes, strArr, str);
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals(TtmlNode.TAG_BODY) || str.equals(TtmlNode.TAG_DIV) || str.equals(TtmlNode.TAG_P) || str.equals(TtmlNode.TAG_SPAN) || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals(TtmlNode.TAG_SMPTE_IMAGE) || str.equals(TtmlNode.TAG_SMPTE_DATA) || str.equals(TtmlNode.TAG_SMPTE_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] strArrSplit = str.split("\\s+");
        if (strArrSplit.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (strArrSplit.length == 2) {
            matcher = FONT_SIZE.matcher(strArrSplit[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + strArrSplit.length + ".");
        }
        if (matcher.matches()) {
            String strGroup = matcher.group(3);
            byte b = -1;
            int iHashCode = strGroup.hashCode();
            if (iHashCode != 37) {
                if (iHashCode != 3240) {
                    if (iHashCode == 3592 && strGroup.equals("px")) {
                        b = 0;
                    }
                } else if (strGroup.equals(UserDataStore.EMAIL)) {
                    b = 1;
                }
            } else if (strGroup.equals("%")) {
                b = 2;
            }
            if (b == 0) {
                ttmlStyle.setFontSizeUnit(1);
            } else if (b == 1) {
                ttmlStyle.setFontSizeUnit(2);
            } else if (b == 2) {
                ttmlStyle.setFontSizeUnit(3);
            } else {
                throw new SubtitleDecoderException("Invalid unit for fontSize: '" + strGroup + "'.");
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + str + "'.");
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00fa  */
    private static long parseTimeExpression(String str, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
        byte b;
        double d;
        double d2;
        Matcher matcher = CLOCK_TIME.matcher(str);
        if (matcher.matches()) {
            double d3 = Long.parseLong(matcher.group(1)) * 3600;
            double d4 = Long.parseLong(matcher.group(2)) * 60;
            Double.isNaN(d3);
            Double.isNaN(d4);
            double d5 = Long.parseLong(matcher.group(3));
            Double.isNaN(d5);
            double d6 = d3 + d4 + d5;
            String strGroup = matcher.group(4);
            double d7 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            double d8 = d6 + (strGroup != null ? Double.parseDouble(strGroup) : 0.0d);
            String strGroup2 = matcher.group(5);
            double d9 = d8 + (strGroup2 != null ? Long.parseLong(strGroup2) / frameAndTickRate.effectiveFrameRate : 0.0d);
            String strGroup3 = matcher.group(6);
            if (strGroup3 != null) {
                double d10 = Long.parseLong(strGroup3);
                double d11 = frameAndTickRate.subFrameRate;
                Double.isNaN(d10);
                Double.isNaN(d11);
                double d12 = frameAndTickRate.effectiveFrameRate;
                Double.isNaN(d12);
                d7 = (d10 / d11) / d12;
            }
            return (long) ((d9 + d7) * 1000000.0d);
        }
        Matcher matcher2 = OFFSET_TIME.matcher(str);
        if (matcher2.matches()) {
            double d13 = Double.parseDouble(matcher2.group(1));
            String strGroup4 = matcher2.group(2);
            int iHashCode = strGroup4.hashCode();
            if (iHashCode != 102) {
                if (iHashCode != 104) {
                    if (iHashCode != 109) {
                        if (iHashCode != 3494) {
                            if (iHashCode != 115) {
                                if (iHashCode == 116 && strGroup4.equals("t")) {
                                    b = 5;
                                } else {
                                    b = -1;
                                }
                            } else if (strGroup4.equals("s")) {
                                b = 2;
                            } else {
                                b = -1;
                            }
                        } else if (strGroup4.equals("ms")) {
                            b = 3;
                        } else {
                            b = -1;
                        }
                    } else if (strGroup4.equals("m")) {
                        b = 1;
                    } else {
                        b = -1;
                    }
                } else if (strGroup4.equals(i.HEIGHT)) {
                    b = 0;
                } else {
                    b = -1;
                }
            } else if (strGroup4.equals("f")) {
                b = 4;
            } else {
                b = -1;
            }
            if (b != 0) {
                if (b == 1) {
                    d = 60.0d;
                } else if (b != 2) {
                    if (b == 3) {
                        d2 = 1000.0d;
                    } else if (b == 4) {
                        d2 = frameAndTickRate.effectiveFrameRate;
                        Double.isNaN(d2);
                    } else if (b == 5) {
                        d2 = frameAndTickRate.tickRate;
                        Double.isNaN(d2);
                    }
                    d13 /= d2;
                }
                return (long) (d13 * 1000000.0d);
            }
            d = 3600.0d;
            d13 *= d;
            return (long) (d13 * 1000000.0d);
        }
        throw new SubtitleDecoderException("Malformed time expression: " + str);
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }

    private static final class CellResolution {
        final int columns;
        final int rows;

        CellResolution(int i, int i2) {
            this.columns = i;
            this.rows = i2;
        }
    }
}
