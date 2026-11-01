package com.vungle.warren.persistence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MemoryUtils {
    @Deprecated
    public static void writeString(@Nullable String str, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        if (str == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        byte[] bytes = str.getBytes();
        byteArrayOutputStream.write(toBytes(bytes.length));
        byteArrayOutputStream.write(bytes);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void write(@Nullable T t, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byte[] bytes;
        if (t == 0) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        if (t instanceof String) {
            bytes = ((String) t).getBytes();
        } else if (t instanceof Number) {
            if (t instanceof Integer) {
                bytes = toBytes(((Integer) t).intValue());
            } else if (t instanceof Long) {
                bytes = toBytes(((Long) t).longValue());
            } else {
                throw new IllegalArgumentException("Value type not supported!");
            }
        } else if (t instanceof Boolean) {
            bytes = new byte[]{((Boolean) t).booleanValue() ? (byte) 1 : (byte) 0};
        } else {
            throw new IllegalArgumentException("Value type not supported!");
        }
        byteArrayOutputStream.write(toBytes(bytes.length));
        byteArrayOutputStream.write(bytes);
    }

    @NonNull
    public static String extractString(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return new String(bArr);
    }

    @Nullable
    public static <T> T extract(ByteBuffer byteBuffer, Class<T> cls) {
        int i = byteBuffer.getInt();
        if (i == 0) {
            if (cls == String.class) {
                return "";
            }
            return null;
        }
        if (cls == String.class) {
            byte[] bArr = new byte[i];
            byteBuffer.get(bArr);
            return (T) new String(bArr);
        }
        if (cls == Integer.class) {
            return (T) Integer.valueOf(byteBuffer.getInt());
        }
        if (cls == Long.class) {
            return (T) Long.valueOf(byteBuffer.getLong());
        }
        if (cls == Boolean.class) {
            return (T) Boolean.valueOf(byteBuffer.get() == 1);
        }
        throw new IllegalArgumentException("Class type " + cls.getCanonicalName() + " not supported!");
    }

    public static void writeStringArray(@NonNull String[] strArr, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byteArrayOutputStream.write(toBytes(strArr.length));
        for (String str : strArr) {
            writeString(str, byteArrayOutputStream);
        }
    }

    public static <T> void writeArray(@NonNull T[] tArr, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byteArrayOutputStream.write(toBytes(tArr.length));
        for (T t : tArr) {
            write(t, byteArrayOutputStream);
        }
    }

    public static String[] extractStringArray(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = extractString(byteBuffer);
        }
        return strArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] extractArray(ByteBuffer byteBuffer, Class<T> cls) {
        int i = byteBuffer.getInt();
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
        for (int i2 = 0; i2 < i; i2++) {
            tArr[i2] = extract(byteBuffer, cls);
        }
        return tArr;
    }

    public static void writeStringMap(@NonNull Map<String, String> map, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        int size = map.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            strArr[i] = entry.getKey();
            strArr2[i] = entry.getValue();
            i++;
        }
        writeStringArray(strArr, byteArrayOutputStream);
        writeStringArray(strArr2, byteArrayOutputStream);
    }

    public static <T> void writeMap(@NonNull Map<String, T> map, @NonNull ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        int size = map.size();
        String[] strArr = new String[size];
        Object[] objArr = null;
        int i = 0;
        for (Map.Entry<String, T> entry : map.entrySet()) {
            strArr[i] = entry.getKey();
            if (objArr == null) {
                objArr = (Object[]) Array.newInstance(entry.getValue().getClass(), size);
            }
            objArr[i] = entry.getValue();
            i++;
        }
        writeArray(strArr, byteArrayOutputStream);
        if (objArr == null) {
            objArr = new Object[0];
        }
        writeArray(objArr, byteArrayOutputStream);
    }

    public static Map<String, String> extractStringMap(ByteBuffer byteBuffer) {
        String[] strArrExtractStringArray = extractStringArray(byteBuffer);
        String[] strArrExtractStringArray2 = extractStringArray(byteBuffer);
        HashMap map = new HashMap(strArrExtractStringArray.length);
        for (int i = 0; i < strArrExtractStringArray.length; i++) {
            map.put(strArrExtractStringArray[i], strArrExtractStringArray2[i]);
        }
        return map;
    }

    public static <T> Map<String, T> extractMap(ByteBuffer byteBuffer, Class<T> cls) {
        String[] strArr = (String[]) extractArray(byteBuffer, String.class);
        Object[] objArrExtractArray = extractArray(byteBuffer, cls);
        HashMap map = new HashMap(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            map.put(strArr[i], objArrExtractArray[i]);
        }
        return map;
    }

    public static <T extends Memorable> void writeMemorable(@Nullable T t, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        if (t == null) {
            byteArrayOutputStream.write(toBytes(0));
            return;
        }
        byte[] byteArray = t.toByteArray();
        byteArrayOutputStream.write(toBytes(byteArray.length));
        byteArrayOutputStream.write(byteArray);
    }

    @Nullable
    public static <T extends Memorable> T extractMemorable(ByteBuffer byteBuffer, Class<T> cls) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        int i = byteBuffer.getInt();
        if (i == 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return cls.getDeclaredConstructor(byte[].class).newInstance(bArr);
    }

    public static byte[] toBytes(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static byte[] toBytes(long j) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (255 & j);
            j >>= 8;
        }
        return bArr;
    }
}
