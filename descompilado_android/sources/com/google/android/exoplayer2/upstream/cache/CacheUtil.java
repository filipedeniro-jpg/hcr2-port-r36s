package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class CacheUtil {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;

    public static class CachingCounters {
        public volatile long alreadyCachedBytes;
        public volatile long contentLength = -1;
        public volatile long newlyCachedBytes;

        public long totalCachedBytes() {
            return this.alreadyCachedBytes + this.newlyCachedBytes;
        }
    }

    public static String generateKey(Uri uri) {
        return uri.toString();
    }

    public static String getKey(DataSpec dataSpec) {
        return dataSpec.key != null ? dataSpec.key : generateKey(dataSpec.uri);
    }

    public static void getCached(DataSpec dataSpec, Cache cache, CachingCounters cachingCounters) {
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length != -1 ? dataSpec.length : cache.getContentLength(key);
        cachingCounters.contentLength = contentLength;
        cachingCounters.alreadyCachedBytes = 0L;
        cachingCounters.newlyCachedBytes = 0L;
        long j2 = j;
        long j3 = contentLength;
        while (j3 != 0) {
            long cachedLength = cache.getCachedLength(key, j2, j3 != -1 ? j3 : Long.MAX_VALUE);
            if (cachedLength > 0) {
                cachingCounters.alreadyCachedBytes += cachedLength;
            } else {
                cachedLength = -cachedLength;
                if (cachedLength == Long.MAX_VALUE) {
                    return;
                }
            }
            j2 += cachedLength;
            if (j3 == -1) {
                cachedLength = 0;
            }
            j3 -= cachedLength;
        }
    }

    public static void cache(DataSpec dataSpec, Cache cache, DataSource dataSource, @Nullable CachingCounters cachingCounters, @Nullable AtomicBoolean atomicBoolean) throws InterruptedException, IOException {
        cache(dataSpec, cache, new CacheDataSource(cache, dataSource), new byte[131072], null, 0, cachingCounters, null, false);
    }

    public static void cache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, @Nullable CachingCounters cachingCounters, @Nullable AtomicBoolean atomicBoolean, boolean z) throws InterruptedException, IOException {
        CachingCounters cachingCounters2 = cachingCounters;
        Assertions.checkNotNull(cacheDataSource);
        Assertions.checkNotNull(bArr);
        if (cachingCounters2 != null) {
            getCached(dataSpec, cache, cachingCounters2);
        } else {
            cachingCounters2 = new CachingCounters();
        }
        CachingCounters cachingCounters3 = cachingCounters2;
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length != -1 ? dataSpec.length : cache.getContentLength(key);
        while (contentLength != 0) {
            if (atomicBoolean != null && atomicBoolean.get()) {
                throw new InterruptedException();
            }
            long cachedLength = cache.getCachedLength(key, j, contentLength != -1 ? contentLength : Long.MAX_VALUE);
            if (cachedLength <= 0) {
                long j2 = -cachedLength;
                if (readAndDiscard(dataSpec, j, j2, cacheDataSource, bArr, priorityTaskManager, i, cachingCounters3) < j2) {
                    if (z && contentLength != -1) {
                        throw new EOFException();
                    }
                    return;
                }
                cachedLength = j2;
            }
            j += cachedLength;
            if (contentLength == -1) {
                cachedLength = 0;
            }
            contentLength -= cachedLength;
        }
    }

    private static long readAndDiscard(DataSpec dataSpec, long j, long j2, DataSource dataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, CachingCounters cachingCounters) throws InterruptedException, IOException {
        DataSpec dataSpec2 = dataSpec;
        while (true) {
            if (priorityTaskManager != null) {
                priorityTaskManager.proceed(i);
            }
            try {
                try {
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    DataSpec dataSpec3 = new DataSpec(dataSpec2.uri, dataSpec2.postBody, j, (dataSpec2.position + j) - dataSpec2.absoluteStreamPosition, -1L, dataSpec2.key, dataSpec2.flags | 2);
                    try {
                        long jOpen = dataSource.open(dataSpec3);
                        if (cachingCounters.contentLength == -1 && jOpen != -1) {
                            cachingCounters.contentLength = dataSpec3.absoluteStreamPosition + jOpen;
                        }
                        long j3 = 0;
                        while (j3 != j2) {
                            if (Thread.interrupted()) {
                                throw new InterruptedException();
                            }
                            int i2 = dataSource.read(bArr, 0, j2 != -1 ? (int) Math.min(bArr.length, j2 - j3) : bArr.length);
                            if (i2 == -1) {
                                if (cachingCounters.contentLength != -1) {
                                    break;
                                }
                                cachingCounters.contentLength = dataSpec3.absoluteStreamPosition + j3;
                                break;
                            }
                            long j4 = i2;
                            j3 += j4;
                            cachingCounters.newlyCachedBytes += j4;
                        }
                        Util.closeQuietly(dataSource);
                        return j3;
                    } catch (PriorityTaskManager.PriorityTooLowException unused) {
                        dataSpec2 = dataSpec3;
                    }
                } catch (PriorityTaskManager.PriorityTooLowException unused2) {
                }
            } catch (Throwable th) {
                Util.closeQuietly(dataSource);
                throw th;
            }
            Util.closeQuietly(dataSource);
        }
    }

    public static void remove(Cache cache, String str) {
        Iterator<CacheSpan> it = cache.getCachedSpans(str).iterator();
        while (it.hasNext()) {
            try {
                cache.removeSpan(it.next());
            } catch (Cache.CacheException unused) {
            }
        }
    }

    private CacheUtil() {
    }
}
