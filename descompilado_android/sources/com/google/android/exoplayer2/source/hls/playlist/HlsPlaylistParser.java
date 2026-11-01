package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.util.Base64;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(,|$)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern(MessengerShareContentUtility.PREVIEW_DEFAULT);
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (!checkPlaylistHeader(bufferedReader)) {
                throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    String strTrim = line.trim();
                    if (!strTrim.isEmpty()) {
                        if (strTrim.startsWith(TAG_STREAM_INF)) {
                            arrayDeque.add(strTrim);
                            HlsMasterPlaylist masterPlaylist = parseMasterPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                            Util.closeQuietly(bufferedReader);
                            return masterPlaylist;
                        }
                        if (!strTrim.startsWith(TAG_TARGET_DURATION) && !strTrim.startsWith(TAG_MEDIA_SEQUENCE) && !strTrim.startsWith(TAG_MEDIA_DURATION) && !strTrim.startsWith(TAG_KEY) && !strTrim.startsWith(TAG_BYTERANGE) && !strTrim.equals(TAG_DISCONTINUITY) && !strTrim.equals(TAG_DISCONTINUITY_SEQUENCE) && !strTrim.equals(TAG_ENDLIST)) {
                            arrayDeque.add(strTrim);
                        }
                        arrayDeque.add(strTrim);
                        HlsMediaPlaylist mediaPlaylist = parseMediaPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                        Util.closeQuietly(bufferedReader);
                        return mediaPlaylist;
                    }
                } else {
                    Util.closeQuietly(bufferedReader);
                    throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                }
            }
        } catch (Throwable th) {
            Util.closeQuietly(bufferedReader);
            throw th;
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int i = bufferedReader.read();
        if (i == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            i = bufferedReader.read();
        }
        int iSkipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, i);
        for (int i2 = 0; i2 < 7; i2++) {
            if (iSkipIgnorableWhitespace != PLAYLIST_HEADER.charAt(i2)) {
                return false;
            }
            iSkipIgnorableWhitespace = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, iSkipIgnorableWhitespace));
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !Util.isLinebreak(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x0152  */
    private static HlsMasterPlaylist parseMasterPlaylist(LineIterator lineIterator, String str) throws IOException {
        byte b;
        int i;
        String str2;
        int i2;
        int i3;
        int i4;
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        boolean zContains = false;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            if (next.startsWith(TAG_PREFIX)) {
                arrayList5.add(next);
            }
            if (next.startsWith(TAG_MEDIA)) {
                arrayList4.add(next);
            } else if (next.startsWith(TAG_STREAM_INF)) {
                zContains |= next.contains(ATTR_CLOSED_CAPTIONS_NONE);
                int intAttr = parseIntAttr(next, REGEX_BANDWIDTH);
                String optionalStringAttr = parseOptionalStringAttr(next, REGEX_AVERAGE_BANDWIDTH);
                if (optionalStringAttr != null) {
                    intAttr = Integer.parseInt(optionalStringAttr);
                }
                int i5 = intAttr;
                String optionalStringAttr2 = parseOptionalStringAttr(next, REGEX_CODECS);
                String optionalStringAttr3 = parseOptionalStringAttr(next, REGEX_RESOLUTION);
                if (optionalStringAttr3 != null) {
                    String[] strArrSplit = optionalStringAttr3.split("x");
                    int i6 = Integer.parseInt(strArrSplit[0]);
                    int i7 = Integer.parseInt(strArrSplit[1]);
                    if (i6 <= 0 || i7 <= 0) {
                        i4 = -1;
                        i7 = -1;
                    } else {
                        i4 = i6;
                    }
                    i2 = i4;
                    i3 = i7;
                } else {
                    i2 = -1;
                    i3 = -1;
                }
                String optionalStringAttr4 = parseOptionalStringAttr(next, REGEX_FRAME_RATE);
                float f = optionalStringAttr4 != null ? Float.parseFloat(optionalStringAttr4) : -1.0f;
                String optionalStringAttr5 = parseOptionalStringAttr(next, REGEX_AUDIO);
                if (optionalStringAttr5 != null && optionalStringAttr2 != null) {
                    map.put(optionalStringAttr5, Util.getCodecsOfType(optionalStringAttr2, 1));
                }
                String next2 = lineIterator.next();
                if (hashSet.add(next2)) {
                    arrayList.add(new HlsMasterPlaylist.HlsUrl(next2, Format.createVideoContainerFormat(Integer.toString(arrayList.size()), MimeTypes.APPLICATION_M3U8, null, optionalStringAttr2, i5, i2, i3, f, null, 0)));
                }
            }
        }
        int i8 = 0;
        Format format = null;
        ArrayList arrayList6 = null;
        while (i8 < arrayList4.size()) {
            String str3 = (String) arrayList4.get(i8);
            int selectionFlags = parseSelectionFlags(str3);
            String optionalStringAttr6 = parseOptionalStringAttr(str3, REGEX_URI);
            String stringAttr = parseStringAttr(str3, REGEX_NAME);
            String optionalStringAttr7 = parseOptionalStringAttr(str3, REGEX_LANGUAGE);
            String optionalStringAttr8 = parseOptionalStringAttr(str3, REGEX_GROUP_ID);
            String stringAttr2 = parseStringAttr(str3, REGEX_TYPE);
            int iHashCode = stringAttr2.hashCode();
            ArrayList arrayList7 = arrayList4;
            if (iHashCode != -959297733) {
                if (iHashCode != -333210994) {
                    if (iHashCode == 62628790 && stringAttr2.equals(TYPE_AUDIO)) {
                        b = 0;
                    } else {
                        b = -1;
                    }
                } else if (stringAttr2.equals(TYPE_CLOSED_CAPTIONS)) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (stringAttr2.equals(TYPE_SUBTITLES)) {
                b = 1;
            } else {
                b = -1;
            }
            if (b == 0) {
                String str4 = (String) map.get(optionalStringAttr8);
                Format formatCreateAudioContainerFormat = Format.createAudioContainerFormat(stringAttr, MimeTypes.APPLICATION_M3U8, str4 != null ? MimeTypes.getMediaMimeType(str4) : null, str4, -1, -1, -1, null, selectionFlags, optionalStringAttr7);
                if (optionalStringAttr6 == null) {
                    format = formatCreateAudioContainerFormat;
                } else {
                    arrayList2.add(new HlsMasterPlaylist.HlsUrl(optionalStringAttr6, formatCreateAudioContainerFormat));
                }
            } else if (b == 1) {
                arrayList3.add(new HlsMasterPlaylist.HlsUrl(optionalStringAttr6, Format.createTextContainerFormat(stringAttr, MimeTypes.APPLICATION_M3U8, MimeTypes.TEXT_VTT, null, -1, selectionFlags, optionalStringAttr7)));
            } else if (b == 2) {
                String stringAttr3 = parseStringAttr(str3, REGEX_INSTREAM_ID);
                if (stringAttr3.startsWith("CC")) {
                    i = Integer.parseInt(stringAttr3.substring(2));
                    str2 = MimeTypes.APPLICATION_CEA608;
                } else {
                    i = Integer.parseInt(stringAttr3.substring(7));
                    str2 = MimeTypes.APPLICATION_CEA708;
                }
                int i9 = i;
                String str5 = str2;
                if (arrayList6 == null) {
                    arrayList6 = new ArrayList();
                }
                arrayList6.add(Format.createTextContainerFormat(stringAttr, null, str5, null, -1, selectionFlags, optionalStringAttr7, i9));
            }
            i8++;
            arrayList4 = arrayList7;
        }
        return new HlsMasterPlaylist(str, arrayList5, arrayList, arrayList2, arrayList3, format, zContains ? Collections.emptyList() : arrayList6);
    }

    private static int parseSelectionFlags(String str) {
        return (parseBooleanAttribute(str, REGEX_DEFAULT, false) ? 1 : 0) | (parseBooleanAttribute(str, REGEX_FORCED, false) ? 2 : 0) | (parseBooleanAttribute(str, REGEX_AUTOSELECT, false) ? 4 : 0);
    }

    private static HlsMediaPlaylist parseMediaPlaylist(LineIterator lineIterator, String str) throws IOException {
        DrmInitData.SchemeData widevineSchemeData;
        String hexString;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long doubleAttr = -9223372036854775807L;
        long intAttr = -9223372036854775807L;
        int i = 0;
        int i2 = 0;
        long jMsToUs = 0;
        boolean z = false;
        int i3 = 0;
        long j = 0;
        int intAttr2 = 1;
        boolean z2 = false;
        boolean z3 = false;
        DrmInitData drmInitData = null;
        long j2 = 0;
        long longAttr = 0;
        boolean z4 = false;
        long j3 = -1;
        long j4 = 0;
        String stringAttr = null;
        String str2 = null;
        long doubleAttr2 = 0;
        HlsMediaPlaylist.Segment segment = null;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            if (next.startsWith(TAG_PREFIX)) {
                arrayList2.add(next);
            }
            if (next.startsWith(TAG_PLAYLIST_TYPE)) {
                String stringAttr2 = parseStringAttr(next, REGEX_PLAYLIST_TYPE);
                if ("VOD".equals(stringAttr2)) {
                    i2 = 1;
                } else if ("EVENT".equals(stringAttr2)) {
                    i2 = 2;
                }
            } else if (next.startsWith(TAG_START)) {
                doubleAttr = (long) (parseDoubleAttr(next, REGEX_TIME_OFFSET) * 1000000.0d);
            } else if (next.startsWith(TAG_INIT_SEGMENT)) {
                String stringAttr3 = parseStringAttr(next, REGEX_URI);
                String optionalStringAttr = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE);
                if (optionalStringAttr != null) {
                    String[] strArrSplit = optionalStringAttr.split("@");
                    j3 = Long.parseLong(strArrSplit[0]);
                    if (strArrSplit.length > 1) {
                        j2 = Long.parseLong(strArrSplit[1]);
                    }
                }
                segment = new HlsMediaPlaylist.Segment(stringAttr3, j2, j3);
                j2 = 0;
                j3 = -1;
            } else if (next.startsWith(TAG_TARGET_DURATION)) {
                intAttr = 1000000 * ((long) parseIntAttr(next, REGEX_TARGET_DURATION));
            } else if (next.startsWith(TAG_MEDIA_SEQUENCE)) {
                longAttr = parseLongAttr(next, REGEX_MEDIA_SEQUENCE);
                j = longAttr;
            } else if (next.startsWith(TAG_VERSION)) {
                intAttr2 = parseIntAttr(next, REGEX_VERSION);
            } else if (next.startsWith(TAG_MEDIA_DURATION)) {
                doubleAttr2 = (long) (parseDoubleAttr(next, REGEX_MEDIA_DURATION) * 1000000.0d);
            } else if (next.startsWith(TAG_KEY)) {
                String optionalStringAttr2 = parseOptionalStringAttr(next, REGEX_METHOD);
                String optionalStringAttr3 = parseOptionalStringAttr(next, REGEX_KEYFORMAT);
                if (METHOD_NONE.equals(optionalStringAttr2)) {
                    stringAttr = null;
                    str2 = null;
                } else {
                    String optionalStringAttr4 = parseOptionalStringAttr(next, REGEX_IV);
                    if (KEYFORMAT_IDENTITY.equals(optionalStringAttr3) || optionalStringAttr3 == null) {
                        if (METHOD_AES_128.equals(optionalStringAttr2)) {
                            stringAttr = parseStringAttr(next, REGEX_URI);
                            str2 = optionalStringAttr4;
                        }
                    } else if (optionalStringAttr2 != null && (widevineSchemeData = parseWidevineSchemeData(next, optionalStringAttr3)) != null) {
                        drmInitData = new DrmInitData((METHOD_SAMPLE_AES_CENC.equals(optionalStringAttr2) || METHOD_SAMPLE_AES_CTR.equals(optionalStringAttr2)) ? C.CENC_TYPE_cenc : C.CENC_TYPE_cbcs, widevineSchemeData);
                    }
                    str2 = optionalStringAttr4;
                    stringAttr = null;
                }
            } else if (next.startsWith(TAG_BYTERANGE)) {
                String[] strArrSplit2 = parseStringAttr(next, REGEX_BYTERANGE).split("@");
                j3 = Long.parseLong(strArrSplit2[0]);
                if (strArrSplit2.length > 1) {
                    j2 = Long.parseLong(strArrSplit2[1]);
                }
            } else if (next.startsWith(TAG_DISCONTINUITY_SEQUENCE)) {
                i3 = Integer.parseInt(next.substring(next.indexOf(58) + 1));
                z = true;
            } else if (next.equals(TAG_DISCONTINUITY)) {
                i++;
            } else if (next.startsWith(TAG_PROGRAM_DATE_TIME)) {
                if (jMsToUs == 0) {
                    jMsToUs = C.msToUs(Util.parseXsDateTime(next.substring(next.indexOf(58) + 1))) - j4;
                }
            } else if (next.equals(TAG_GAP)) {
                z4 = true;
            } else if (next.equals(TAG_INDEPENDENT_SEGMENTS)) {
                z2 = true;
            } else if (next.equals(TAG_ENDLIST)) {
                z3 = true;
            } else if (!next.startsWith("#")) {
                if (stringAttr == null) {
                    hexString = null;
                } else {
                    hexString = str2 != null ? str2 : Long.toHexString(longAttr);
                }
                long j5 = longAttr + 1;
                if (j3 == -1) {
                    j2 = 0;
                }
                arrayList.add(new HlsMediaPlaylist.Segment(next, segment, doubleAttr2, i, j4, stringAttr, hexString, j2, j3, z4));
                j4 += doubleAttr2;
                if (j3 != -1) {
                    j2 += j3;
                }
                longAttr = j5;
                j3 = -1;
                z4 = false;
                doubleAttr2 = 0;
            }
        }
        return new HlsMediaPlaylist(i2, str, arrayList2, doubleAttr, jMsToUs, z, i3, j, intAttr2, intAttr, z2, z3, jMsToUs != 0, drmInitData, arrayList);
    }

    private static DrmInitData.SchemeData parseWidevineSchemeData(String str, String str2) throws ParserException {
        if (KEYFORMAT_WIDEVINE_PSSH_BINARY.equals(str2)) {
            String stringAttr = parseStringAttr(str, REGEX_URI);
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, MimeTypes.VIDEO_MP4, Base64.decode(stringAttr.substring(stringAttr.indexOf(44)), 0));
        }
        if (!KEYFORMAT_WIDEVINE_PSSH_JSON.equals(str2)) {
            return null;
        }
        try {
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new ParserException(e);
        }
    }

    private static int parseIntAttr(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern));
    }

    private static long parseLongAttr(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(parseStringAttr(str, pattern));
    }

    private static double parseDoubleAttr(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern));
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String parseStringAttr(String str, Pattern pattern) throws ParserException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        throw new ParserException("Couldn't match " + pattern.pattern() + " in " + str);
    }

    private static boolean parseBooleanAttribute(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals(BOOLEAN_TRUE) : z;
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(" + BOOLEAN_FALSE + "|" + BOOLEAN_TRUE + ")");
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        public boolean hasNext() throws IOException {
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = this.extraLines.poll();
                return true;
            }
            do {
                String line = this.reader.readLine();
                this.next = line;
                if (line == null) {
                    return false;
                }
                this.next = this.next.trim();
            } while (this.next.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (!hasNext()) {
                return null;
            }
            String str = this.next;
            this.next = null;
            return str;
        }
    }
}
