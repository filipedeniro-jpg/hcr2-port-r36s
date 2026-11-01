package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.HevcConfig;
import com.google.android.gms.games.Notifications;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class AtomParsers {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 3;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_text = Util.getIntegerCodeForString(MimeTypes.BASE_TYPE_TEXT);
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int hdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (hdlr == -1) {
            return null;
        }
        TkhdData tkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long jScaleLargeTimestamp = C.TIME_UNSET;
        long j2 = j == C.TIME_UNSET ? tkhd.duration : j;
        long mvhd = parseMvhd(leafAtom.data);
        if (j2 != C.TIME_UNSET) {
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j2, 1000000L, mvhd);
        }
        long j3 = jScaleLargeTimestamp;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> mdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData stsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhd.id, tkhd.rotationDegrees, (String) mdhd.second, drmInitData, z2);
        if (z) {
            jArr = null;
            jArr2 = null;
        } else {
            Pair<long[], long[]> edts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
            long[] jArr3 = (long[]) edts.first;
            jArr2 = (long[]) edts.second;
            jArr = jArr3;
        }
        if (stsd.format == null) {
            return null;
        }
        return new Track(tkhd.id, hdlr, ((Long) mdhd.first).longValue(), mvhd, j3, stsd.format, stsd.requiredSampleTransformation, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    /* JADX WARN: Code duplicated, block: B:138:0x0329  */
    /* JADX WARN: Code duplicated, block: B:139:0x032b  */
    /* JADX WARN: Code duplicated, block: B:143:0x0337  */
    /* JADX WARN: Code duplicated, block: B:145:0x0341  */
    /* JADX WARN: Code duplicated, block: B:147:0x0365  */
    /* JADX WARN: Code duplicated, block: B:148:0x0367  */
    /* JADX WARN: Code duplicated, block: B:150:0x036b  */
    /* JADX WARN: Code duplicated, block: B:154:0x0382  */
    /* JADX WARN: Code duplicated, block: B:155:0x0384  */
    /* JADX WARN: Code duplicated, block: B:158:0x0388  */
    /* JADX WARN: Code duplicated, block: B:159:0x038b  */
    /* JADX WARN: Code duplicated, block: B:161:0x038e  */
    /* JADX WARN: Code duplicated, block: B:162:0x0391  */
    /* JADX WARN: Code duplicated, block: B:164:0x0395  */
    /* JADX WARN: Code duplicated, block: B:165:0x0397  */
    /* JADX WARN: Code duplicated, block: B:167:0x039b  */
    /* JADX WARN: Code duplicated, block: B:168:0x039e  */
    /* JADX WARN: Code duplicated, block: B:172:0x03aa  */
    /* JADX WARN: Code duplicated, block: B:174:0x03b8  */
    /* JADX WARN: Code duplicated, block: B:176:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:177:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:180:0x03fb  */
    /* JADX WARN: Code duplicated, block: B:187:0x043a  */
    /* JADX WARN: Code duplicated, block: B:195:0x047d  */
    /* JADX WARN: Code duplicated, block: B:196:0x047f  */
    /* JADX WARN: Code duplicated, block: B:199:0x0486  */
    /* JADX WARN: Code duplicated, block: B:201:0x04a4  */
    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox stz2SampleSizeBox;
        boolean z;
        int unsignedIntToInt;
        int unsignedIntToInt2;
        int i;
        String str;
        long[] jArr;
        int[] iArr;
        int i2;
        long[] jArr2;
        int[] iArr2;
        long j;
        String str2;
        long j2;
        boolean z2;
        int i3;
        boolean z3;
        int i4;
        int i5;
        int[] iArr3;
        int i6;
        long j3;
        boolean z4;
        boolean z5;
        long[] jArr3;
        int[] iArr4;
        int i7;
        int[] iArr5;
        long[] jArr4;
        int i8;
        int i9;
        int i10;
        long[] jArr5;
        long[] jArr6;
        int[] iArr6;
        int[] iArr7;
        int[] iArr8;
        long jScaleLargeTimestamp;
        int i11;
        boolean z6;
        boolean z7;
        long j4;
        long j5;
        long[] jArr7;
        int[] iArr9;
        int[] iArr10;
        int[] iArr11;
        int[] iArr12;
        int[] iArr13;
        int iBinarySearchCeil;
        int iBinarySearchCeil2;
        int[] iArr14;
        long j6;
        int iBinarySearchCeil3;
        boolean z8;
        int i12;
        int i13;
        Track track2 = track;
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            stz2SampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 == null) {
                throw new ParserException("Track has no sample table size information");
            }
            stz2SampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
        }
        int sampleCount = stz2SampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0], C.TIME_UNSET);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_co64);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray4 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray5 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int unsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt() - 1;
        int unsignedIntToInt4 = parsableByteArray3.readUnsignedIntToInt();
        int unsignedIntToInt5 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            unsignedIntToInt = parsableByteArray5.readUnsignedIntToInt();
        } else {
            unsignedIntToInt = 0;
        }
        int unsignedIntToInt6 = -1;
        if (parsableByteArray4 != null) {
            parsableByteArray4.setPosition(12);
            unsignedIntToInt2 = parsableByteArray4.readUnsignedIntToInt();
            if (unsignedIntToInt2 > 0) {
                unsignedIntToInt6 = parsableByteArray4.readUnsignedIntToInt() - 1;
            } else {
                parsableByteArray4 = null;
            }
        } else {
            unsignedIntToInt2 = 0;
        }
        long j7 = 0;
        if (!(stz2SampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track2.format.sampleMimeType) && unsignedIntToInt3 == 0 && unsignedIntToInt == 0 && unsignedIntToInt2 == 0)) {
            jArr = new long[sampleCount];
            iArr = new int[sampleCount];
            int i14 = unsignedIntToInt2;
            jArr2 = new long[sampleCount];
            iArr2 = new int[sampleCount];
            int i15 = i14;
            int i16 = unsignedIntToInt4;
            int i17 = unsignedIntToInt5;
            int i18 = unsignedIntToInt;
            int unsignedIntToInt7 = unsignedIntToInt6;
            long j8 = 0;
            int i19 = unsignedIntToInt3;
            int i20 = 0;
            int i21 = 0;
            int unsignedIntToInt8 = 0;
            int i22 = 0;
            int i23 = 0;
            long j9 = 0;
            while (i21 < sampleCount) {
                while (i22 == 0) {
                    Assertions.checkState(chunkIterator.moveNext());
                    j9 = chunkIterator.offset;
                    i22 = chunkIterator.numSamples;
                    sampleCount = sampleCount;
                    i15 = i15;
                }
                int i24 = i15;
                int i25 = sampleCount;
                if (parsableByteArray5 != null) {
                    while (unsignedIntToInt8 == 0 && i18 > 0) {
                        unsignedIntToInt8 = parsableByteArray5.readUnsignedIntToInt();
                        i23 = parsableByteArray5.readInt();
                        i18--;
                    }
                    unsignedIntToInt8--;
                }
                int i26 = i23;
                jArr[i21] = j9;
                iArr[i21] = stz2SampleSizeBox.readNextSampleSize();
                if (iArr[i21] > i20) {
                    i20 = iArr[i21];
                }
                SampleSizeBox sampleSizeBox = stz2SampleSizeBox;
                jArr2[i21] = ((long) i26) + j8;
                iArr2[i21] = parsableByteArray4 == null ? 1 : 0;
                if (i21 == unsignedIntToInt7) {
                    iArr2[i21] = 1;
                    i12 = i24 - 1;
                    if (i12 > 0) {
                        unsignedIntToInt7 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                } else {
                    i12 = i24;
                }
                j8 += (long) i17;
                i16--;
                if (i16 == 0) {
                    i13 = i19;
                    if (i13 > 0) {
                        int unsignedIntToInt9 = parsableByteArray3.readUnsignedIntToInt();
                        i17 = parsableByteArray3.readInt();
                        i19 = i13 - 1;
                        i16 = unsignedIntToInt9;
                    }
                    j9 += (long) iArr[i21];
                    i22--;
                    i21++;
                    i15 = i12;
                    stz2SampleSizeBox = sampleSizeBox;
                    sampleCount = i25;
                    i23 = i26;
                } else {
                    i13 = i19;
                }
                i19 = i13;
                j9 += (long) iArr[i21];
                i22--;
                i21++;
                i15 = i12;
                stz2SampleSizeBox = sampleSizeBox;
                sampleCount = i25;
                i23 = i26;
            }
            int i27 = i15;
            i = sampleCount;
            int i28 = i19;
            j = j8 + ((long) i23);
            Assertions.checkArgument(unsignedIntToInt8 == 0);
            while (i18 > 0) {
                Assertions.checkArgument(parsableByteArray5.readUnsignedIntToInt() == 0);
                parsableByteArray5.readInt();
                i18--;
            }
            if (i27 == 0 && i16 == 0 && i22 == 0 && i28 == 0) {
                str = TAG;
                i2 = i20;
                track2 = track;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistent stbl box for track ");
                i2 = i20;
                track2 = track;
                sb.append(track2.id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i27);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i16);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i22);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i28);
                String string = sb.toString();
                str = TAG;
                Log.w(str, string);
            }
        } else {
            i = sampleCount;
            str = TAG;
            long[] jArr8 = new long[chunkIterator.length];
            int[] iArr15 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr8[chunkIterator.index] = chunkIterator.offset;
                iArr15[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results resultsRechunk = FixedSampleSizeRechunker.rechunk(Util.getPcmFrameSize(track2.format.pcmEncoding, track2.format.channelCount), jArr8, iArr15, unsignedIntToInt5);
            jArr = resultsRechunk.offsets;
            iArr = resultsRechunk.sizes;
            i2 = resultsRechunk.maximumSize;
            jArr2 = resultsRechunk.timestamps;
            iArr2 = resultsRechunk.flags;
            j = resultsRechunk.duration;
        }
        long[] jArr9 = jArr;
        int[] iArr16 = iArr;
        long[] jArr10 = jArr2;
        int i29 = i2;
        long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(j, 1000000L, track2.timescale);
        if (track2.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
            Util.scaleLargeTimestampsInPlace(jArr10, 1000000L, track2.timescale);
            return new TrackSampleTable(jArr9, iArr16, i29, jArr10, iArr2, jScaleLargeTimestamp2);
        }
        if (track2.editListDurations.length == 1 && track2.type == 1 && jArr10.length >= 2) {
            long j10 = track2.editListMediaTimes[0];
            str2 = str;
            long jScaleLargeTimestamp3 = j10 + Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale);
            if (canApplyEditWithGaplessInfo(jArr10, j, j10, jScaleLargeTimestamp3)) {
                long j11 = j - jScaleLargeTimestamp3;
                long jScaleLargeTimestamp4 = Util.scaleLargeTimestamp(j10 - jArr10[0], track2.format.sampleRate, track2.timescale);
                j2 = j;
                long jScaleLargeTimestamp5 = Util.scaleLargeTimestamp(j11, track2.format.sampleRate, track2.timescale);
                if ((jScaleLargeTimestamp4 != 0 || jScaleLargeTimestamp5 != 0) && jScaleLargeTimestamp4 <= 2147483647L && jScaleLargeTimestamp5 <= 2147483647L) {
                    gaplessInfoHolder.encoderDelay = (int) jScaleLargeTimestamp4;
                    gaplessInfoHolder.encoderPadding = (int) jScaleLargeTimestamp5;
                    Util.scaleLargeTimestampsInPlace(jArr10, 1000000L, track2.timescale);
                    return new TrackSampleTable(jArr9, iArr16, i29, jArr10, iArr2, jScaleLargeTimestamp2);
                }
            }
            if (track2.editListDurations.length != 1 && track2.editListDurations[0] == 0) {
                long j12 = track2.editListMediaTimes[0];
                for (int i30 = 0; i30 < jArr10.length; i30++) {
                    jArr10[i30] = Util.scaleLargeTimestamp(jArr10[i30] - j12, 1000000L, track2.timescale);
                }
                return new TrackSampleTable(jArr9, iArr16, i29, jArr10, iArr2, Util.scaleLargeTimestamp(j2 - j12, 1000000L, track2.timescale));
            }
            if (track2.type == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            i3 = 0;
            z3 = false;
            i4 = 0;
            i5 = 0;
            while (i3 < track2.editListDurations.length) {
                long j13 = jScaleLargeTimestamp2;
                j6 = track2.editListMediaTimes[i3];
                if (j6 != -1) {
                    long jScaleLargeTimestamp6 = Util.scaleLargeTimestamp(track2.editListDurations[i3], track2.timescale, track2.movieTimescale);
                    iBinarySearchCeil3 = Util.binarySearchCeil(jArr10, j6, true, true);
                    int iBinarySearchCeil4 = Util.binarySearchCeil(jArr10, j6 + jScaleLargeTimestamp6, z2, false);
                    i4 += iBinarySearchCeil4 - iBinarySearchCeil3;
                    if (i5 != iBinarySearchCeil3) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    z3 |= z8;
                    i5 = iBinarySearchCeil4;
                }
                i3++;
                i29 = i29;
                jScaleLargeTimestamp2 = j13;
                iArr16 = iArr16;
            }
            iArr3 = iArr16;
            i6 = i29;
            j3 = jScaleLargeTimestamp2;
            if (i4 != i) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z4 | z3;
            if (z5) {
                jArr3 = new long[i4];
            } else {
                jArr3 = jArr9;
            }
            if (z5) {
                iArr4 = new int[i4];
            } else {
                iArr4 = iArr3;
            }
            if (z5) {
                i7 = 0;
            } else {
                i7 = i6;
            }
            if (z5) {
                iArr5 = new int[i4];
            } else {
                iArr5 = iArr2;
            }
            jArr4 = new long[i4];
            i8 = i7;
            i9 = 0;
            i10 = 0;
            while (i9 < track2.editListDurations.length) {
                j4 = track2.editListMediaTimes[i9];
                j5 = track2.editListDurations[i9];
                if (j4 != -1) {
                    iArr12 = iArr2;
                    iArr13 = iArr5;
                    long jScaleLargeTimestamp7 = Util.scaleLargeTimestamp(j5, track2.timescale, track2.movieTimescale) + j4;
                    iBinarySearchCeil = Util.binarySearchCeil(jArr10, j4, true, true);
                    iBinarySearchCeil2 = Util.binarySearchCeil(jArr10, jScaleLargeTimestamp7, z2, false);
                    if (z5) {
                        int i31 = iBinarySearchCeil2 - iBinarySearchCeil;
                        System.arraycopy(jArr9, iBinarySearchCeil, jArr3, i10, i31);
                        iArr10 = iArr3;
                        System.arraycopy(iArr10, iBinarySearchCeil, iArr4, i10, i31);
                        iArr11 = iArr12;
                        iArr14 = iArr13;
                        System.arraycopy(iArr11, iBinarySearchCeil, iArr14, i10, i31);
                    } else {
                        iArr10 = iArr3;
                        iArr11 = iArr12;
                        iArr14 = iArr13;
                    }
                    int i32 = i8;
                    while (iBinarySearchCeil < iBinarySearchCeil2) {
                        int[] iArr17 = iArr14;
                        long[] jArr11 = jArr9;
                        long j14 = j4;
                        jArr4[i10] = Util.scaleLargeTimestamp(j7, 1000000L, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr10[iBinarySearchCeil] - j4, 1000000L, track2.timescale);
                        if (!z5 && iArr4[i10] > i32) {
                            i32 = iArr10[iBinarySearchCeil];
                        }
                        i10++;
                        iBinarySearchCeil++;
                        j4 = j14;
                        jArr9 = jArr11;
                        iArr14 = iArr17;
                    }
                    iArr9 = iArr14;
                    jArr7 = jArr9;
                    i8 = i32;
                } else {
                    jArr3 = jArr3;
                    jArr7 = jArr9;
                    iArr9 = iArr5;
                    iArr10 = iArr3;
                    z2 = z2;
                    iArr11 = iArr2;
                }
                j7 += j5;
                i9++;
                iArr2 = iArr11;
                jArr3 = jArr3;
                z2 = z2;
                jArr9 = jArr7;
                iArr5 = iArr9;
                iArr3 = iArr10;
            }
            jArr5 = jArr3;
            jArr6 = jArr9;
            iArr6 = iArr2;
            iArr7 = iArr5;
            iArr8 = iArr3;
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j7, 1000000L, track2.timescale);
            z6 = false;
            for (i11 = 0; i11 < iArr7.length && !z6; i11++) {
                if ((iArr7[i11] & 1) != 0) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                z6 |= z7;
            }
            if (!z6) {
                Log.w(str2, "Ignoring edit list: Edited sample sequence does not contain a sync sample.");
                Util.scaleLargeTimestampsInPlace(jArr10, 1000000L, track2.timescale);
                return new TrackSampleTable(jArr6, iArr8, i6, jArr10, iArr6, j3);
            }
            return new TrackSampleTable(jArr5, iArr4, i8, jArr4, iArr7, jScaleLargeTimestamp);
        }
        str2 = str;
        j2 = j;
        if (track2.editListDurations.length != 1) {
        }
        if (track2.type == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        i3 = 0;
        z3 = false;
        i4 = 0;
        i5 = 0;
        while (i3 < track2.editListDurations.length) {
            long j15 = jScaleLargeTimestamp2;
            j6 = track2.editListMediaTimes[i3];
            if (j6 != -1) {
                long jScaleLargeTimestamp8 = Util.scaleLargeTimestamp(track2.editListDurations[i3], track2.timescale, track2.movieTimescale);
                iBinarySearchCeil3 = Util.binarySearchCeil(jArr10, j6, true, true);
                int iBinarySearchCeil5 = Util.binarySearchCeil(jArr10, j6 + jScaleLargeTimestamp8, z2, false);
                i4 += iBinarySearchCeil5 - iBinarySearchCeil3;
                if (i5 != iBinarySearchCeil3) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                z3 |= z8;
                i5 = iBinarySearchCeil5;
            }
            i3++;
            i29 = i29;
            jScaleLargeTimestamp2 = j15;
            iArr16 = iArr16;
        }
        iArr3 = iArr16;
        i6 = i29;
        j3 = jScaleLargeTimestamp2;
        if (i4 != i) {
            z4 = true;
        } else {
            z4 = false;
        }
        z5 = z4 | z3;
        if (z5) {
            jArr3 = new long[i4];
        } else {
            jArr3 = jArr9;
        }
        if (z5) {
            iArr4 = new int[i4];
        } else {
            iArr4 = iArr3;
        }
        if (z5) {
            i7 = 0;
        } else {
            i7 = i6;
        }
        if (z5) {
            iArr5 = new int[i4];
        } else {
            iArr5 = iArr2;
        }
        jArr4 = new long[i4];
        i8 = i7;
        i9 = 0;
        i10 = 0;
        while (i9 < track2.editListDurations.length) {
            j4 = track2.editListMediaTimes[i9];
            j5 = track2.editListDurations[i9];
            if (j4 != -1) {
                iArr12 = iArr2;
                iArr13 = iArr5;
                long jScaleLargeTimestamp9 = Util.scaleLargeTimestamp(j5, track2.timescale, track2.movieTimescale) + j4;
                iBinarySearchCeil = Util.binarySearchCeil(jArr10, j4, true, true);
                iBinarySearchCeil2 = Util.binarySearchCeil(jArr10, jScaleLargeTimestamp9, z2, false);
                if (z5) {
                    int i33 = iBinarySearchCeil2 - iBinarySearchCeil;
                    System.arraycopy(jArr9, iBinarySearchCeil, jArr3, i10, i33);
                    iArr10 = iArr3;
                    System.arraycopy(iArr10, iBinarySearchCeil, iArr4, i10, i33);
                    iArr11 = iArr12;
                    iArr14 = iArr13;
                    System.arraycopy(iArr11, iBinarySearchCeil, iArr14, i10, i33);
                } else {
                    iArr10 = iArr3;
                    iArr11 = iArr12;
                    iArr14 = iArr13;
                }
                int i34 = i8;
                while (iBinarySearchCeil < iBinarySearchCeil2) {
                    int[] iArr18 = iArr14;
                    long[] jArr12 = jArr9;
                    long j16 = j4;
                    jArr4[i10] = Util.scaleLargeTimestamp(j7, 1000000L, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr10[iBinarySearchCeil] - j4, 1000000L, track2.timescale);
                    if (!z5) {
                    }
                    i10++;
                    iBinarySearchCeil++;
                    j4 = j16;
                    jArr9 = jArr12;
                    iArr14 = iArr18;
                }
                iArr9 = iArr14;
                jArr7 = jArr9;
                i8 = i34;
            } else {
                jArr3 = jArr3;
                jArr7 = jArr9;
                iArr9 = iArr5;
                iArr10 = iArr3;
                z2 = z2;
                iArr11 = iArr2;
            }
            j7 += j5;
            i9++;
            iArr2 = iArr11;
            jArr3 = jArr3;
            z2 = z2;
            jArr9 = jArr7;
            iArr5 = iArr9;
            iArr3 = iArr10;
        }
        jArr5 = jArr3;
        jArr6 = jArr9;
        iArr6 = iArr2;
        iArr7 = iArr5;
        iArr8 = iArr3;
        jScaleLargeTimestamp = Util.scaleLargeTimestamp(j7, 1000000L, track2.timescale);
        z6 = false;
        while (i11 < iArr7.length) {
            if ((iArr7[i11] & 1) != 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            z6 |= z7;
        }
        if (!z6) {
            Log.w(str2, "Ignoring edit list: Edited sample sequence does not contain a sync sample.");
            Util.scaleLargeTimestampsInPlace(jArr10, 1000000L, track2.timescale);
            return new TrackSampleTable(jArr6, iArr8, i6, jArr10, iArr6, j3);
        }
        return new TrackSampleTable(jArr5, iArr4, i8, jArr4, iArr7, jScaleLargeTimestamp);
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int i = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseMetaAtom(parsableByteArray, position + i);
            }
            parsableByteArray.skipBytes(i - 8);
        }
        return null;
    }

    private static Metadata parseMetaAtom(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int i2 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + i2);
            }
            parsableByteArray.skipBytes(i2 - 8);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry ilstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (ilstElement != null) {
                arrayList.add(ilstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        parsableByteArray.skipBytes(Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0 ? 16 : 8);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        int i = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i2 = fullAtomVersion == 0 ? 4 : 8;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                z = true;
                break;
            }
            if (parsableByteArray.data[position + i4] != -1) {
                z = false;
                break;
            }
            i4++;
        }
        long j = C.TIME_UNSET;
        if (z) {
            parsableByteArray.skipBytes(i2);
        } else {
            long unsignedInt = fullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (unsignedInt != 0) {
                j = unsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int i5 = parsableByteArray.readInt();
        int i6 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i7 = parsableByteArray.readInt();
        int i8 = parsableByteArray.readInt();
        if (i5 == 0 && i6 == 65536 && i7 == -65536 && i8 == 0) {
            i3 = 90;
        } else if (i5 == 0 && i6 == -65536 && i7 == 65536 && i8 == 0) {
            i3 = 270;
        } else if (i5 == -65536 && i6 == 0 && i7 == 0 && i8 == -65536) {
            i3 = 180;
        }
        return new TkhdData(i, j, i3);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        int i = parsableByteArray.readInt();
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp) {
            return 3;
        }
        return i == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 4 : 8);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(unsignedInt), "" + ((char) (((unsignedShort >> 10) & 31) + 96)) + ((char) (((unsignedShort >> 5) & 31) + 96)) + ((char) ((unsignedShort & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int i3 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            int position = parsableByteArray.getPosition();
            int i5 = parsableByteArray.readInt();
            Assertions.checkArgument(i5 > 0, "childAtomSize should be positive");
            int i6 = parsableByteArray.readInt();
            if (i6 == Atom.TYPE_avc1 || i6 == Atom.TYPE_avc3 || i6 == Atom.TYPE_encv || i6 == Atom.TYPE_mp4v || i6 == Atom.TYPE_hvc1 || i6 == Atom.TYPE_hev1 || i6 == Atom.TYPE_s263 || i6 == Atom.TYPE_vp08 || i6 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, i6, position, i5, i, i2, drmInitData, stsdData, i4);
            } else if (i6 == Atom.TYPE_mp4a || i6 == Atom.TYPE_enca || i6 == Atom.TYPE_ac_3 || i6 == Atom.TYPE_ec_3 || i6 == Atom.TYPE_dtsc || i6 == Atom.TYPE_dtse || i6 == Atom.TYPE_dtsh || i6 == Atom.TYPE_dtsl || i6 == Atom.TYPE_samr || i6 == Atom.TYPE_sawb || i6 == Atom.TYPE_lpcm || i6 == Atom.TYPE_sowt || i6 == Atom.TYPE__mp3 || i6 == Atom.TYPE_alac) {
                parseAudioSampleEntry(parsableByteArray, i6, position, i5, i, str, z, drmInitData, stsdData, i4);
            } else if (i6 == Atom.TYPE_TTML || i6 == Atom.TYPE_tx3g || i6 == Atom.TYPE_wvtt || i6 == Atom.TYPE_stpp || i6 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray, i6, position, i5, i, str, stsdData);
            } else if (i6 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, null);
            }
            parsableByteArray.setPosition(position + i5);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        parsableByteArray.setPosition(i2 + 8 + 8);
        int i5 = Atom.TYPE_TTML;
        String str2 = MimeTypes.APPLICATION_TTML;
        List listSingletonList = null;
        long j = Long.MAX_VALUE;
        if (i != i5) {
            if (i == Atom.TYPE_tx3g) {
                int i6 = (i3 - 8) - 8;
                byte[] bArr = new byte[i6];
                parsableByteArray.readBytes(bArr, 0, i6);
                listSingletonList = Collections.singletonList(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i == Atom.TYPE_wvtt) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i == Atom.TYPE_stpp) {
                j = 0;
            } else if (i == Atom.TYPE_c608) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData.format = Format.createTextSampleFormat(Integer.toString(i4), str2, null, -1, 0, str, -1, null, j, listSingletonList);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i2 + 8 + 8);
        parsableByteArray.skipBytes(16);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        String str = null;
        int iIntValue = i;
        if (iIntValue == Atom.TYPE_encv) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i2, i3);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i6] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData2 = drmInitDataCopyWithSchemeType;
        List<byte[]> listSingletonList = null;
        byte[] projFromParent = null;
        boolean z = false;
        float paspFromParent = 1.0f;
        int i7 = -1;
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int i8 = parsableByteArray.readInt();
            if (i8 == 0 && parsableByteArray.getPosition() - i2 == i3) {
                break;
            }
            Assertions.checkArgument(i8 > 0, "childAtomSize should be positive");
            int i9 = parsableByteArray.readInt();
            if (i9 == Atom.TYPE_avcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig avcConfig = AvcConfig.parse(parsableByteArray);
                listSingletonList = avcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                if (!z) {
                    paspFromParent = avcConfig.pixelWidthAspectRatio;
                }
                str = MimeTypes.VIDEO_H264;
            } else if (i9 == Atom.TYPE_hvcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                HevcConfig hevcConfig = HevcConfig.parse(parsableByteArray);
                listSingletonList = hevcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                str = MimeTypes.VIDEO_H265;
            } else if (i9 == Atom.TYPE_vpcC) {
                Assertions.checkState(str == null);
                str = iIntValue == Atom.TYPE_vp08 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
            } else if (i9 == Atom.TYPE_d263) {
                Assertions.checkState(str == null);
                str = MimeTypes.VIDEO_H263;
            } else if (i9 == Atom.TYPE_esds) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                str = (String) esdsFromParent.first;
                listSingletonList = Collections.singletonList(esdsFromParent.second);
            } else if (i9 == Atom.TYPE_pasp) {
                paspFromParent = parsePaspFromParent(parsableByteArray, position2);
                z = true;
            } else if (i9 == Atom.TYPE_sv3d) {
                projFromParent = parseProjFromParent(parsableByteArray, position2, i8);
            } else if (i9 == Atom.TYPE_st3d) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (unsignedByte == 0) {
                    int unsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (unsignedByte2 == 0) {
                        i7 = 0;
                    } else if (unsignedByte2 == 1) {
                        i7 = 1;
                    } else if (unsignedByte2 == 2) {
                        i7 = 2;
                    } else if (unsignedByte2 == 3) {
                        i7 = 3;
                    }
                }
            }
            position += i8;
        }
        if (str == null) {
            return;
        }
        stsdData.format = Format.createVideoSampleFormat(Integer.toString(i4), str, null, -1, -1, unsignedShort, unsignedShort2, -1.0f, listSingletonList, i5, paspFromParent, projFromParent, i7, null, drmInitData2);
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i = 0; i < unsignedIntToInt; i++) {
            jArr[i] = fullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = fullAtomVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int unsignedShort;
        int unsignedShort2;
        int unsignedFixedPoint1616;
        String str2;
        int i6;
        String str3;
        int i7;
        String str4;
        DrmInitData drmInitData2;
        int i8;
        int i9;
        int i10 = i2;
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i10 + 8 + 8);
        if (z) {
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            unsignedShort = 0;
        }
        if (unsignedShort == 0 || unsignedShort == 1) {
            unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
            }
        } else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            int iRound = (int) Math.round(parsableByteArray.readDouble());
            int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
            unsignedShort2 = unsignedIntToInt;
            unsignedFixedPoint1616 = iRound;
        }
        int position = parsableByteArray.getPosition();
        int iIntValue = i;
        if (iIntValue == Atom.TYPE_enca) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i10, i3);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i5] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData3 = drmInitDataCopyWithSchemeType;
        int i11 = Atom.TYPE_ac_3;
        String str5 = MimeTypes.AUDIO_RAW;
        if (iIntValue == i11) {
            str2 = MimeTypes.AUDIO_AC3;
        } else if (iIntValue == Atom.TYPE_ec_3) {
            str2 = MimeTypes.AUDIO_E_AC3;
        } else if (iIntValue == Atom.TYPE_dtsc) {
            str2 = MimeTypes.AUDIO_DTS;
        } else if (iIntValue == Atom.TYPE_dtsh || iIntValue == Atom.TYPE_dtsl) {
            str2 = MimeTypes.AUDIO_DTS_HD;
        } else if (iIntValue == Atom.TYPE_dtse) {
            str2 = MimeTypes.AUDIO_DTS_EXPRESS;
        } else if (iIntValue == Atom.TYPE_samr) {
            str2 = MimeTypes.AUDIO_AMR_NB;
        } else if (iIntValue == Atom.TYPE_sawb) {
            str2 = MimeTypes.AUDIO_AMR_WB;
        } else if (iIntValue == Atom.TYPE_lpcm || iIntValue == Atom.TYPE_sowt) {
            str2 = MimeTypes.AUDIO_RAW;
        } else if (iIntValue == Atom.TYPE__mp3) {
            str2 = MimeTypes.AUDIO_MPEG;
        } else {
            str2 = iIntValue == Atom.TYPE_alac ? MimeTypes.AUDIO_ALAC : null;
        }
        int iIntValue2 = unsignedFixedPoint1616;
        int i12 = position;
        int iIntValue3 = unsignedShort2;
        byte[] bArr = null;
        String str6 = str2;
        while (i12 - i10 < i3) {
            parsableByteArray.setPosition(i12);
            int i13 = parsableByteArray.readInt();
            Assertions.checkArgument(i13 > 0, "childAtomSize should be positive");
            int i14 = parsableByteArray.readInt();
            if (i14 == Atom.TYPE_esds || (z && i14 == Atom.TYPE_wave)) {
                i6 = i13;
                str3 = str6;
                i7 = i12;
                str4 = str5;
                drmInitData2 = drmInitData3;
                int iFindEsdsPosition = i14 == Atom.TYPE_esds ? i7 : findEsdsPosition(parsableByteArray, i7, i6);
                if (iFindEsdsPosition != -1) {
                    Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindEsdsPosition);
                    str6 = (String) esdsFromParent.first;
                    bArr = (byte[]) esdsFromParent.second;
                    if (MimeTypes.AUDIO_AAC.equals(str6)) {
                        Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        iIntValue2 = ((Integer) aacAudioSpecificConfig.first).intValue();
                        iIntValue3 = ((Integer) aacAudioSpecificConfig.second).intValue();
                    }
                }
                i12 = i7 + i6;
                i10 = i2;
                drmInitData3 = drmInitData2;
                str5 = str4;
            } else {
                if (i14 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(i12 + 8);
                    stsdData.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData3);
                } else if (i14 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(i12 + 8);
                    stsdData.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData3);
                } else {
                    if (i14 == Atom.TYPE_ddts) {
                        i8 = i13;
                        str3 = str6;
                        i9 = i12;
                        str4 = str5;
                        drmInitData2 = drmInitData3;
                        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str6, null, -1, -1, iIntValue3, iIntValue2, null, drmInitData2, 0, str);
                    } else {
                        i8 = i13;
                        str3 = str6;
                        i9 = i12;
                        str4 = str5;
                        drmInitData2 = drmInitData3;
                        if (i14 == Atom.TYPE_alac) {
                            i6 = i8;
                            byte[] bArr2 = new byte[i6];
                            i7 = i9;
                            parsableByteArray.setPosition(i7);
                            parsableByteArray.readBytes(bArr2, 0, i6);
                            bArr = bArr2;
                        }
                    }
                    i6 = i8;
                    i7 = i9;
                }
                i6 = i13;
                str3 = str6;
                i7 = i12;
                str4 = str5;
                drmInitData2 = drmInitData3;
            }
            str6 = str3;
            i12 = i7 + i6;
            i10 = i2;
            drmInitData3 = drmInitData2;
            str5 = str4;
        }
        String str7 = str6;
        String str8 = str5;
        DrmInitData drmInitData4 = drmInitData3;
        if (stsdData.format != null || str7 == null) {
            return;
        }
        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str7, null, -1, -1, iIntValue3, iIntValue2, str8.equals(str7) ? 2 : -1, bArr == null ? null : Collections.singletonList(bArr), drmInitData4, 0, str);
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += i3;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr, 0, expandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair<Integer, TrackEncryptionBox> commonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf && (commonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, i3)) != null) {
                return commonEncryptionSinfFromParent;
            }
            position += i3;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        String string = null;
        Integer numValueOf = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i6 = parsableByteArray.readInt();
            int i7 = parsableByteArray.readInt();
            if (i7 == Atom.TYPE_frma) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i7 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                string = parsableByteArray.readString(4);
            } else if (i7 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = i6;
            }
            i3 += i6;
        }
        if (!C.CENC_TYPE_cenc.equals(string) && !C.CENC_TYPE_cbc1.equals(string) && !C.CENC_TYPE_cens.equals(string) && !C.CENC_TYPE_cbcs.equals(string)) {
            return null;
        }
        Assertions.checkArgument(numValueOf != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox schiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, string);
        Assertions.checkArgument(schiFromParent != null, "tenc atom is mandatory");
        return Pair.create(numValueOf, schiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int i6 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (fullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = unsignedByte & 15;
                    i4 = (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, bArr2.length);
                if (z && unsignedByte2 == 0) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[unsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, unsignedByte3);
                }
                return new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += i6;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, i4 + i3);
            }
            i3 += i4;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = unsignedByte & Notifications.NOTIFICATION_TYPES_ALL;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (unsignedByte & Notifications.NOTIFICATION_TYPES_ALL);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        return jArr[0] <= j2 && j2 < jArr[Util.constrainValue(3, 0, length)] && jArr[Util.constrainValue(jArr.length - 3, 0, length)] < j3 && j3 <= j;
    }

    private AtomParsers() {
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
            this.fixedSampleSize = this.data.readUnsignedIntToInt();
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
            this.fieldSize = this.data.readUnsignedIntToInt() & 255;
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 == 0) {
                this.currentByte = this.data.readUnsignedByte();
                return (this.currentByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
            }
            return this.currentByte & 15;
        }
    }
}
