package com.google.android.gms.internal.ads;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public enum zzdpy {
    DOUBLE(0, zzdqa.SCALAR, zzdqp.DOUBLE),
    FLOAT(1, zzdqa.SCALAR, zzdqp.FLOAT),
    INT64(2, zzdqa.SCALAR, zzdqp.LONG),
    UINT64(3, zzdqa.SCALAR, zzdqp.LONG),
    INT32(4, zzdqa.SCALAR, zzdqp.INT),
    FIXED64(5, zzdqa.SCALAR, zzdqp.LONG),
    FIXED32(6, zzdqa.SCALAR, zzdqp.INT),
    BOOL(7, zzdqa.SCALAR, zzdqp.BOOLEAN),
    STRING(8, zzdqa.SCALAR, zzdqp.STRING),
    MESSAGE(9, zzdqa.SCALAR, zzdqp.MESSAGE),
    BYTES(10, zzdqa.SCALAR, zzdqp.BYTE_STRING),
    UINT32(11, zzdqa.SCALAR, zzdqp.INT),
    ENUM(12, zzdqa.SCALAR, zzdqp.ENUM),
    SFIXED32(13, zzdqa.SCALAR, zzdqp.INT),
    SFIXED64(14, zzdqa.SCALAR, zzdqp.LONG),
    SINT32(15, zzdqa.SCALAR, zzdqp.INT),
    SINT64(16, zzdqa.SCALAR, zzdqp.LONG),
    GROUP(17, zzdqa.SCALAR, zzdqp.MESSAGE),
    DOUBLE_LIST(18, zzdqa.VECTOR, zzdqp.DOUBLE),
    FLOAT_LIST(19, zzdqa.VECTOR, zzdqp.FLOAT),
    INT64_LIST(20, zzdqa.VECTOR, zzdqp.LONG),
    UINT64_LIST(21, zzdqa.VECTOR, zzdqp.LONG),
    INT32_LIST(22, zzdqa.VECTOR, zzdqp.INT),
    FIXED64_LIST(23, zzdqa.VECTOR, zzdqp.LONG),
    FIXED32_LIST(24, zzdqa.VECTOR, zzdqp.INT),
    BOOL_LIST(25, zzdqa.VECTOR, zzdqp.BOOLEAN),
    STRING_LIST(26, zzdqa.VECTOR, zzdqp.STRING),
    MESSAGE_LIST(27, zzdqa.VECTOR, zzdqp.MESSAGE),
    BYTES_LIST(28, zzdqa.VECTOR, zzdqp.BYTE_STRING),
    UINT32_LIST(29, zzdqa.VECTOR, zzdqp.INT),
    ENUM_LIST(30, zzdqa.VECTOR, zzdqp.ENUM),
    SFIXED32_LIST(31, zzdqa.VECTOR, zzdqp.INT),
    SFIXED64_LIST(32, zzdqa.VECTOR, zzdqp.LONG),
    SINT32_LIST(33, zzdqa.VECTOR, zzdqp.INT),
    SINT64_LIST(34, zzdqa.VECTOR, zzdqp.LONG),
    DOUBLE_LIST_PACKED(35, zzdqa.PACKED_VECTOR, zzdqp.DOUBLE),
    FLOAT_LIST_PACKED(36, zzdqa.PACKED_VECTOR, zzdqp.FLOAT),
    INT64_LIST_PACKED(37, zzdqa.PACKED_VECTOR, zzdqp.LONG),
    UINT64_LIST_PACKED(38, zzdqa.PACKED_VECTOR, zzdqp.LONG),
    INT32_LIST_PACKED(39, zzdqa.PACKED_VECTOR, zzdqp.INT),
    FIXED64_LIST_PACKED(40, zzdqa.PACKED_VECTOR, zzdqp.LONG),
    FIXED32_LIST_PACKED(41, zzdqa.PACKED_VECTOR, zzdqp.INT),
    BOOL_LIST_PACKED(42, zzdqa.PACKED_VECTOR, zzdqp.BOOLEAN),
    UINT32_LIST_PACKED(43, zzdqa.PACKED_VECTOR, zzdqp.INT),
    ENUM_LIST_PACKED(44, zzdqa.PACKED_VECTOR, zzdqp.ENUM),
    SFIXED32_LIST_PACKED(45, zzdqa.PACKED_VECTOR, zzdqp.INT),
    SFIXED64_LIST_PACKED(46, zzdqa.PACKED_VECTOR, zzdqp.LONG),
    SINT32_LIST_PACKED(47, zzdqa.PACKED_VECTOR, zzdqp.INT),
    SINT64_LIST_PACKED(48, zzdqa.PACKED_VECTOR, zzdqp.LONG),
    GROUP_LIST(49, zzdqa.VECTOR, zzdqp.MESSAGE),
    MAP(50, zzdqa.MAP, zzdqp.VOID);

    private static final zzdpy[] zzhjn;
    private static final Type[] zzhjo = new Type[0];
    private final int id;
    private final zzdqp zzhjj;
    private final zzdqa zzhjk;
    private final Class<?> zzhjl;
    private final boolean zzhjm;

    zzdpy(int i, zzdqa zzdqaVar, zzdqp zzdqpVar) {
        int i2;
        this.id = i;
        this.zzhjk = zzdqaVar;
        this.zzhjj = zzdqpVar;
        int i3 = zzdpx.zzhhi[zzdqaVar.ordinal()];
        if (i3 == 1 || i3 == 2) {
            this.zzhjl = zzdqpVar.zzbag();
        } else {
            this.zzhjl = null;
        }
        boolean z = false;
        if (zzdqaVar == zzdqa.SCALAR && (i2 = zzdpx.zzhhj[zzdqpVar.ordinal()]) != 1 && i2 != 2 && i2 != 3) {
            z = true;
        }
        this.zzhjm = z;
    }

    public final int id() {
        return this.id;
    }

    static {
        zzdpy[] zzdpyVarArrValues = values();
        zzhjn = new zzdpy[zzdpyVarArrValues.length];
        for (zzdpy zzdpyVar : zzdpyVarArrValues) {
            zzhjn[zzdpyVar.id] = zzdpyVar;
        }
    }
}
