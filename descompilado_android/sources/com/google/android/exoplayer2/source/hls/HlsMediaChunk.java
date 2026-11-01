package com.google.android.exoplayer2.source.hls;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class HlsMediaChunk extends MediaChunk {
    private static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    private static final AtomicInteger uidSource = new AtomicInteger();
    private int bytesLoaded;
    public final int discontinuitySequenceNumber;
    private final Extractor extractor;
    private final boolean hasGapTag;
    public final HlsMasterPlaylist.HlsUrl hlsUrl;
    private final ParsableByteArray id3Data;
    private final Id3Decoder id3Decoder;
    private boolean id3TimestampPeeked;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private boolean initLoadCompleted;
    private int initSegmentBytesLoaded;
    private final boolean isEncrypted;
    private final boolean isMasterTimestampSource;
    private final boolean isPackedAudioExtractor;
    private volatile boolean loadCanceled;
    private volatile boolean loadCompleted;
    private HlsSampleStreamWrapper output;
    private final boolean reusingExtractor;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    public HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, DataSpec dataSpec2, HlsMasterPlaylist.HlsUrl hlsUrl, List<Format> list, int i, Object obj, long j, long j2, long j3, int i2, boolean z, boolean z2, TimestampAdjuster timestampAdjuster, HlsMediaChunk hlsMediaChunk, DrmInitData drmInitData, byte[] bArr, byte[] bArr2) {
        Extractor extractor;
        ParsableByteArray parsableByteArray;
        super(buildDataSource(dataSource, bArr, bArr2), dataSpec, hlsUrl.format, i, obj, j, j2, j3);
        this.discontinuitySequenceNumber = i2;
        this.initDataSpec = dataSpec2;
        this.hlsUrl = hlsUrl;
        this.isMasterTimestampSource = z2;
        this.timestampAdjuster = timestampAdjuster;
        this.isEncrypted = this.dataSource instanceof Aes128DataSource;
        this.hasGapTag = z;
        if (hlsMediaChunk != null) {
            this.shouldSpliceIn = hlsMediaChunk.hlsUrl != hlsUrl;
            extractor = (hlsMediaChunk.discontinuitySequenceNumber != i2 || this.shouldSpliceIn) ? null : hlsMediaChunk.extractor;
        } else {
            this.shouldSpliceIn = false;
            extractor = null;
        }
        Pair<Extractor, Boolean> pairCreateExtractor = hlsExtractorFactory.createExtractor(extractor, dataSpec.uri, this.trackFormat, list, drmInitData, timestampAdjuster);
        this.extractor = (Extractor) pairCreateExtractor.first;
        this.isPackedAudioExtractor = ((Boolean) pairCreateExtractor.second).booleanValue();
        this.reusingExtractor = this.extractor == extractor;
        this.initLoadCompleted = this.reusingExtractor && dataSpec2 != null;
        if (!this.isPackedAudioExtractor) {
            this.id3Decoder = null;
            this.id3Data = null;
        } else if (hlsMediaChunk != null && (parsableByteArray = hlsMediaChunk.id3Data) != null) {
            this.id3Decoder = hlsMediaChunk.id3Decoder;
            this.id3Data = parsableByteArray;
        } else {
            this.id3Decoder = new Id3Decoder();
            this.id3Data = new ParsableByteArray(10);
        }
        this.initDataSource = dataSource;
        this.uid = uidSource.getAndIncrement();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.output = hlsSampleStreamWrapper;
        hlsSampleStreamWrapper.init(this.uid, this.shouldSpliceIn, this.reusingExtractor);
        if (this.reusingExtractor) {
            return;
        }
        this.extractor.init(hlsSampleStreamWrapper);
    }

    @Override // com.google.android.exoplayer2.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // com.google.android.exoplayer2.source.chunk.Chunk
    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        maybeLoadInitData();
        if (this.loadCanceled) {
            return;
        }
        if (!this.hasGapTag) {
            loadMedia();
        }
        this.loadCompleted = true;
    }

    private void maybeLoadInitData() throws InterruptedException, IOException {
        DataSpec dataSpec;
        if (this.initLoadCompleted || (dataSpec = this.initDataSpec) == null) {
            return;
        }
        DataSpec dataSpecSubrange = dataSpec.subrange(this.initSegmentBytesLoaded);
        try {
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.initDataSource, dataSpecSubrange.absoluteStreamPosition, this.initDataSource.open(dataSpecSubrange));
            int i = 0;
            while (i == 0) {
                try {
                    if (this.loadCanceled) {
                        break;
                    } else {
                        i = this.extractor.read(defaultExtractorInput, null);
                    }
                } catch (Throwable th) {
                    this.initSegmentBytesLoaded = (int) (defaultExtractorInput.getPosition() - this.initDataSpec.absoluteStreamPosition);
                    throw th;
                }
            }
            this.initSegmentBytesLoaded = (int) (defaultExtractorInput.getPosition() - this.initDataSpec.absoluteStreamPosition);
            Util.closeQuietly(this.dataSource);
            this.initLoadCompleted = true;
        } catch (Throwable th2) {
            Util.closeQuietly(this.dataSource);
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001c  */
    /* JADX WARN: Code duplicated, block: B:12:0x0022  */
    /* JADX WARN: Code duplicated, block: B:14:0x0031  */
    /* JADX WARN: Code duplicated, block: B:21:0x0061 A[Catch: all -> 0x00a2, TryCatch #1 {all -> 0x00a2, blocks: (B:15:0x0038, B:17:0x004c, B:19:0x0050, B:21:0x0061, B:23:0x006a, B:22:0x0068, B:25:0x006f, B:34:0x0090, B:32:0x0083, B:33:0x008f, B:27:0x0076, B:29:0x007a), top: B:43:0x0038, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x0068 A[Catch: all -> 0x00a2, TryCatch #1 {all -> 0x00a2, blocks: (B:15:0x0038, B:17:0x004c, B:19:0x0050, B:21:0x0061, B:23:0x006a, B:22:0x0068, B:25:0x006f, B:34:0x0090, B:32:0x0083, B:33:0x008f, B:27:0x0076, B:29:0x007a), top: B:43:0x0038, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x006f A[Catch: all -> 0x00a2, TRY_LEAVE, TryCatch #1 {all -> 0x00a2, blocks: (B:15:0x0038, B:17:0x004c, B:19:0x0050, B:21:0x0061, B:23:0x006a, B:22:0x0068, B:25:0x006f, B:34:0x0090, B:32:0x0083, B:33:0x008f, B:27:0x0076, B:29:0x007a), top: B:43:0x0038, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x007a A[Catch: all -> 0x0082, TRY_LEAVE, TryCatch #0 {all -> 0x0082, blocks: (B:27:0x0076, B:29:0x007a), top: B:41:0x0076, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:45:0x0090 A[EDGE_INSN: B:45:0x0090->B:34:0x0090 BREAK  A[LOOP:0: B:26:0x0074->B:47:?], SYNTHETIC] */
    private void loadMedia() throws InterruptedException, IOException {
        DataSpec dataSpecSubrange;
        boolean z;
        DefaultExtractorInput defaultExtractorInput;
        long jPeekId3PrivTimestamp;
        long jAdjustTsTimestamp;
        int i = 0;
        try {
            if (this.isEncrypted) {
                dataSpecSubrange = this.dataSpec;
                z = this.bytesLoaded != 0;
                if (!this.isMasterTimestampSource) {
                    this.timestampAdjuster.waitUntilInitialized();
                } else if (this.timestampAdjuster.getFirstSampleTimestampUs() == Long.MAX_VALUE) {
                    this.timestampAdjuster.setFirstSampleTimestampUs(this.startTimeUs);
                }
                defaultExtractorInput = new DefaultExtractorInput(this.dataSource, dataSpecSubrange.absoluteStreamPosition, this.dataSource.open(dataSpecSubrange));
                if (this.isPackedAudioExtractor && !this.id3TimestampPeeked) {
                    jPeekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput);
                    this.id3TimestampPeeked = true;
                    HlsSampleStreamWrapper hlsSampleStreamWrapper = this.output;
                    if (jPeekId3PrivTimestamp != C.TIME_UNSET) {
                        jAdjustTsTimestamp = this.timestampAdjuster.adjustTsTimestamp(jPeekId3PrivTimestamp);
                    } else {
                        jAdjustTsTimestamp = this.startTimeUs;
                    }
                    hlsSampleStreamWrapper.setSampleOffsetUs(jAdjustTsTimestamp);
                }
                if (z) {
                    defaultExtractorInput.skipFully(this.bytesLoaded);
                }
                while (i == 0) {
                    try {
                        if (!this.loadCanceled) {
                            break;
                        } else {
                            i = this.extractor.read(defaultExtractorInput, null);
                        }
                    } catch (Throwable th) {
                        this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                        throw th;
                    }
                }
                this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                Util.closeQuietly(this.dataSource);
                return;
            }
            dataSpecSubrange = this.dataSpec.subrange(this.bytesLoaded);
            defaultExtractorInput = new DefaultExtractorInput(this.dataSource, dataSpecSubrange.absoluteStreamPosition, this.dataSource.open(dataSpecSubrange));
            if (this.isPackedAudioExtractor) {
                jPeekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput);
                this.id3TimestampPeeked = true;
                HlsSampleStreamWrapper hlsSampleStreamWrapper2 = this.output;
                if (jPeekId3PrivTimestamp != C.TIME_UNSET) {
                    jAdjustTsTimestamp = this.timestampAdjuster.adjustTsTimestamp(jPeekId3PrivTimestamp);
                } else {
                    jAdjustTsTimestamp = this.startTimeUs;
                }
                hlsSampleStreamWrapper2.setSampleOffsetUs(jAdjustTsTimestamp);
            }
            if (z) {
                defaultExtractorInput.skipFully(this.bytesLoaded);
            }
            while (i == 0) {
                if (!this.loadCanceled) {
                    break;
                    break;
                }
                i = this.extractor.read(defaultExtractorInput, null);
            }
            this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
            Util.closeQuietly(this.dataSource);
            return;
        } catch (Throwable th2) {
            Util.closeQuietly(this.dataSource);
            throw th2;
        }
        if (!this.isMasterTimestampSource) {
            this.timestampAdjuster.waitUntilInitialized();
        } else if (this.timestampAdjuster.getFirstSampleTimestampUs() == Long.MAX_VALUE) {
            this.timestampAdjuster.setFirstSampleTimestampUs(this.startTimeUs);
        }
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws InterruptedException, IOException {
        Metadata metadataDecode;
        extractorInput.resetPeekPosition();
        if (!extractorInput.peekFully(this.id3Data.data, 0, 10, true)) {
            return C.TIME_UNSET;
        }
        this.id3Data.reset(10);
        if (this.id3Data.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
            return C.TIME_UNSET;
        }
        this.id3Data.skipBytes(3);
        int synchSafeInt = this.id3Data.readSynchSafeInt();
        int i = synchSafeInt + 10;
        if (i > this.id3Data.capacity()) {
            byte[] bArr = this.id3Data.data;
            this.id3Data.reset(i);
            System.arraycopy(bArr, 0, this.id3Data.data, 0, 10);
        }
        if (!extractorInput.peekFully(this.id3Data.data, 10, synchSafeInt, true) || (metadataDecode = this.id3Decoder.decode(this.id3Data.data, synchSafeInt)) == null) {
            return C.TIME_UNSET;
        }
        int length = metadataDecode.length();
        for (int i2 = 0; i2 < length; i2++) {
            Metadata.Entry entry = metadataDecode.get(i2);
            if (entry instanceof PrivFrame) {
                PrivFrame privFrame = (PrivFrame) entry;
                if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
                    System.arraycopy(privFrame.privateData, 0, this.id3Data.data, 0, 8);
                    this.id3Data.reset(8);
                    return this.id3Data.readLong() & 8589934591L;
                }
            }
        }
        return C.TIME_UNSET;
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new Aes128DataSource(dataSource, bArr, bArr2) : dataSource;
    }
}
