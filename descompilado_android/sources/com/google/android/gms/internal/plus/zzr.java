package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.adcolony.sdk.AdColonyUserMetadata;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
@SafeParcelable.Class(creator = "PersonEntityCreator")
public final class zzr extends FastSafeParcelableJsonResponse implements Person {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

    @SafeParcelable.Indicator
    private final Set<Integer> zzap;

    @SafeParcelable.Field(id = 2)
    private String zzaq;

    @SafeParcelable.Field(id = 3)
    private zza zzar;

    @SafeParcelable.Field(id = 4)
    private String zzas;

    @SafeParcelable.Field(id = 5)
    private String zzat;

    @SafeParcelable.Field(id = 6)
    private int zzau;

    @SafeParcelable.Field(id = 7)
    private zzb zzav;

    @SafeParcelable.Field(id = 8)
    private String zzaw;

    @SafeParcelable.Field(id = 9)
    private String zzax;

    @SafeParcelable.Field(id = 12)
    private int zzay;

    @SafeParcelable.Field(id = 14)
    private String zzaz;

    @SafeParcelable.Field(id = 15)
    private zzc zzba;

    @SafeParcelable.Field(id = 16)
    private boolean zzbb;

    @SafeParcelable.Field(id = 18)
    private String zzbc;

    @SafeParcelable.Field(id = 19)
    private zzd zzbd;

    @SafeParcelable.Field(id = 20)
    private String zzbe;

    @SafeParcelable.Field(id = 21)
    private int zzbf;

    @SafeParcelable.Field(id = 22)
    private List<zze> zzbg;

    @SafeParcelable.Field(id = 23)
    private List<zzf> zzbh;

    @SafeParcelable.Field(id = 24)
    private int zzbi;

    @SafeParcelable.Field(id = 25)
    private int zzbj;

    @SafeParcelable.Field(id = 26)
    private String zzbk;

    @SafeParcelable.Field(id = 28)
    private List<zzg> zzbl;

    @SafeParcelable.Field(id = 29)
    private boolean zzbm;

    @SafeParcelable.Field(id = 27)
    private String zzk;

    @SafeParcelable.VersionField(id = 1)
    private final int zzw;

    @VisibleForTesting
    @SafeParcelable.Class(creator = "PersonEntity_AgeRangeEntityCreator")
    public static final class zza extends FastSafeParcelableJsonResponse implements Person.AgeRange {
        public static final Parcelable.Creator<zza> CREATOR = new zzt();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

        @SafeParcelable.Indicator
        private final Set<Integer> zzap;

        @SafeParcelable.Field(id = 2)
        private int zzbn;

        @SafeParcelable.Field(id = 3)
        private int zzbo;

        @SafeParcelable.VersionField(id = 1)
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
            zzao = map;
            map.put("max", FastJsonResponse.Field.forInteger("max", 2));
            zzao.put("min", FastJsonResponse.Field.forInteger("min", 3));
        }

        public zza() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @SafeParcelable.Constructor
        zza(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3) {
            this.zzap = set;
            this.zzw = i;
            this.zzbn = i2;
            this.zzbo = i3;
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zza zzaVar = (zza) obj;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!zzaVar.isFieldSet(field) || !getFieldValue(field).equals(zzaVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (zzaVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final /* bridge */ /* synthetic */ Person.AgeRange freeze() {
            return this;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final Object getFieldValue(FastJsonResponse.Field field) {
            int i;
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                i = this.zzbn;
            } else {
                if (safeParcelableFieldId != 3) {
                    int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown safe parcelable id=");
                    sb.append(safeParcelableFieldId2);
                    throw new IllegalStateException(sb.toString());
                }
                i = this.zzbo;
            }
            return Integer.valueOf(i);
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public final int getMax() {
            return this.zzbn;
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public final int getMin() {
            return this.zzbo;
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public final boolean hasMax() {
            return this.zzap.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public final boolean hasMin() {
            return this.zzap.contains(3);
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final int hashCode() {
            int safeParcelableFieldId = 0;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                }
            }
            return safeParcelableFieldId;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final boolean isDataValid() {
            return true;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzbn = i;
            } else {
                if (safeParcelableFieldId != 3) {
                    StringBuilder sb = new StringBuilder(52);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be an int.");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zzbo = i;
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeInt(parcel, 2, this.zzbn);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeInt(parcel, 3, this.zzbo);
            }
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    @VisibleForTesting
    @SafeParcelable.Class(creator = "PersonEntity_CoverEntityCreator")
    public static final class zzb extends FastSafeParcelableJsonResponse implements Person.Cover {
        public static final Parcelable.Creator<zzb> CREATOR = new zzu();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

        @SafeParcelable.Indicator
        private final Set<Integer> zzap;

        @SafeParcelable.Field(id = 2)
        private zza zzbp;

        @SafeParcelable.Field(id = 3)
        private C0068zzb zzbq;

        @SafeParcelable.Field(id = 4)
        private int zzbr;

        @SafeParcelable.VersionField(id = 1)
        private final int zzw;

        @VisibleForTesting
        @SafeParcelable.Class(creator = "PersonEntity_CoverEntity_CoverInfoEntityCreator")
        public static final class zza extends FastSafeParcelableJsonResponse implements Person.Cover.CoverInfo {
            public static final Parcelable.Creator<zza> CREATOR = new zzv();
            private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

            @SafeParcelable.Indicator
            private final Set<Integer> zzap;

            @SafeParcelable.Field(id = 2)
            private int zzbs;

            @SafeParcelable.Field(id = 3)
            private int zzbt;

            @SafeParcelable.VersionField(id = 1)
            private final int zzw;

            static {
                HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
                zzao = map;
                map.put("leftImageOffset", FastJsonResponse.Field.forInteger("leftImageOffset", 2));
                zzao.put("topImageOffset", FastJsonResponse.Field.forInteger("topImageOffset", 3));
            }

            public zza() {
                this.zzw = 1;
                this.zzap = new HashSet();
            }

            @SafeParcelable.Constructor
            zza(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3) {
                this.zzap = set;
                this.zzw = i;
                this.zzbs = i2;
                this.zzbt = i3;
            }

            @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
            public final boolean equals(Object obj) {
                if (!(obj instanceof zza)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                zza zzaVar = (zza) obj;
                for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                    if (isFieldSet(field)) {
                        if (!zzaVar.isFieldSet(field) || !getFieldValue(field).equals(zzaVar.getFieldValue(field))) {
                            return false;
                        }
                    } else if (zzaVar.isFieldSet(field)) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.google.android.gms.common.data.Freezable
            public final /* bridge */ /* synthetic */ Person.Cover.CoverInfo freeze() {
                return this;
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            public final /* synthetic */ Map getFieldMappings() {
                return zzao;
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            protected final Object getFieldValue(FastJsonResponse.Field field) {
                int i;
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    i = this.zzbs;
                } else {
                    if (safeParcelableFieldId != 3) {
                        int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                        StringBuilder sb = new StringBuilder(38);
                        sb.append("Unknown safe parcelable id=");
                        sb.append(safeParcelableFieldId2);
                        throw new IllegalStateException(sb.toString());
                    }
                    i = this.zzbt;
                }
                return Integer.valueOf(i);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public final int getLeftImageOffset() {
                return this.zzbs;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public final int getTopImageOffset() {
                return this.zzbt;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public final boolean hasLeftImageOffset() {
                return this.zzap.contains(2);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public final boolean hasTopImageOffset() {
                return this.zzap.contains(3);
            }

            @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
            public final int hashCode() {
                int safeParcelableFieldId = 0;
                for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                    if (isFieldSet(field)) {
                        safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                    }
                }
                return safeParcelableFieldId;
            }

            @Override // com.google.android.gms.common.data.Freezable
            public final boolean isDataValid() {
                return true;
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            protected final boolean isFieldSet(FastJsonResponse.Field field) {
                return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            protected final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    this.zzbs = i;
                } else {
                    if (safeParcelableFieldId != 3) {
                        StringBuilder sb = new StringBuilder(52);
                        sb.append("Field with id=");
                        sb.append(safeParcelableFieldId);
                        sb.append(" is not known to be an int.");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.zzbt = i;
                }
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
                Set<Integer> set = this.zzap;
                if (set.contains(1)) {
                    SafeParcelWriter.writeInt(parcel, 1, this.zzw);
                }
                if (set.contains(2)) {
                    SafeParcelWriter.writeInt(parcel, 2, this.zzbs);
                }
                if (set.contains(3)) {
                    SafeParcelWriter.writeInt(parcel, 3, this.zzbt);
                }
                SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
            }
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.plus.zzr$zzb$zzb, reason: collision with other inner class name */
        @VisibleForTesting
        @SafeParcelable.Class(creator = "PersonEntity_CoverEntity_CoverPhotoEntityCreator")
        public static final class C0068zzb extends FastSafeParcelableJsonResponse implements Person.Cover.CoverPhoto {
            public static final Parcelable.Creator<C0068zzb> CREATOR = new zzw();
            private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

            @SafeParcelable.Indicator
            private final Set<Integer> zzap;

            @SafeParcelable.Field(id = 2)
            private int zzbu;

            @SafeParcelable.Field(id = 4)
            private int zzbv;

            @SafeParcelable.Field(id = 3)
            private String zzk;

            @SafeParcelable.VersionField(id = 1)
            private final int zzw;

            static {
                HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
                zzao = map;
                map.put("height", FastJsonResponse.Field.forInteger("height", 2));
                zzao.put("url", FastJsonResponse.Field.forString("url", 3));
                zzao.put("width", FastJsonResponse.Field.forInteger("width", 4));
            }

            public C0068zzb() {
                this.zzw = 1;
                this.zzap = new HashSet();
            }

            @SafeParcelable.Constructor
            C0068zzb(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) int i3) {
                this.zzap = set;
                this.zzw = i;
                this.zzbu = i2;
                this.zzk = str;
                this.zzbv = i3;
            }

            @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
            public final boolean equals(Object obj) {
                if (!(obj instanceof C0068zzb)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                C0068zzb c0068zzb = (C0068zzb) obj;
                for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                    if (isFieldSet(field)) {
                        if (!c0068zzb.isFieldSet(field) || !getFieldValue(field).equals(c0068zzb.getFieldValue(field))) {
                            return false;
                        }
                    } else if (c0068zzb.isFieldSet(field)) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.google.android.gms.common.data.Freezable
            public final /* bridge */ /* synthetic */ Person.Cover.CoverPhoto freeze() {
                return this;
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            public final /* synthetic */ Map getFieldMappings() {
                return zzao;
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            protected final Object getFieldValue(FastJsonResponse.Field field) {
                int i;
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    i = this.zzbu;
                } else {
                    if (safeParcelableFieldId == 3) {
                        return this.zzk;
                    }
                    if (safeParcelableFieldId != 4) {
                        int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                        StringBuilder sb = new StringBuilder(38);
                        sb.append("Unknown safe parcelable id=");
                        sb.append(safeParcelableFieldId2);
                        throw new IllegalStateException(sb.toString());
                    }
                    i = this.zzbv;
                }
                return Integer.valueOf(i);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public final int getHeight() {
                return this.zzbu;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public final String getUrl() {
                return this.zzk;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public final int getWidth() {
                return this.zzbv;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public final boolean hasHeight() {
                return this.zzap.contains(2);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public final boolean hasUrl() {
                return this.zzap.contains(3);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public final boolean hasWidth() {
                return this.zzap.contains(4);
            }

            @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
            public final int hashCode() {
                int safeParcelableFieldId = 0;
                for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                    if (isFieldSet(field)) {
                        safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                    }
                }
                return safeParcelableFieldId;
            }

            @Override // com.google.android.gms.common.data.Freezable
            public final boolean isDataValid() {
                return true;
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            protected final boolean isFieldSet(FastJsonResponse.Field field) {
                return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            protected final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 2) {
                    this.zzbu = i;
                } else {
                    if (safeParcelableFieldId != 4) {
                        StringBuilder sb = new StringBuilder(52);
                        sb.append("Field with id=");
                        sb.append(safeParcelableFieldId);
                        sb.append(" is not known to be an int.");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.zzbv = i;
                }
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            }

            @Override // com.google.android.gms.common.server.response.FastJsonResponse
            protected final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                if (safeParcelableFieldId == 3) {
                    this.zzk = str2;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                } else {
                    StringBuilder sb = new StringBuilder(54);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be a String.");
                    throw new IllegalArgumentException(sb.toString());
                }
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
                Set<Integer> set = this.zzap;
                if (set.contains(1)) {
                    SafeParcelWriter.writeInt(parcel, 1, this.zzw);
                }
                if (set.contains(2)) {
                    SafeParcelWriter.writeInt(parcel, 2, this.zzbu);
                }
                if (set.contains(3)) {
                    SafeParcelWriter.writeString(parcel, 3, this.zzk, true);
                }
                if (set.contains(4)) {
                    SafeParcelWriter.writeInt(parcel, 4, this.zzbv);
                }
                SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
            }
        }

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
            zzao = map;
            map.put("coverInfo", FastJsonResponse.Field.forConcreteType("coverInfo", 2, zza.class));
            zzao.put("coverPhoto", FastJsonResponse.Field.forConcreteType("coverPhoto", 3, C0068zzb.class));
            zzao.put(TtmlNode.TAG_LAYOUT, FastJsonResponse.Field.withConverter(TtmlNode.TAG_LAYOUT, 4, new StringToIntConverter().add(ai.a.cW, 0), false));
        }

        public zzb() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @SafeParcelable.Constructor
        zzb(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) zza zzaVar, @SafeParcelable.Param(id = 3) C0068zzb c0068zzb, @SafeParcelable.Param(id = 4) int i2) {
            this.zzap = set;
            this.zzw = i;
            this.zzbp = zzaVar;
            this.zzbq = c0068zzb;
            this.zzbr = i2;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzbp = (zza) t;
            } else {
                if (safeParcelableFieldId != 3) {
                    String canonicalName = t.getClass().getCanonicalName();
                    StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 62);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not a known custom type.  Found ");
                    sb.append(canonicalName);
                    sb.append(".");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zzbq = (C0068zzb) t;
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzb zzbVar = (zzb) obj;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!zzbVar.isFieldSet(field) || !getFieldValue(field).equals(zzbVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (zzbVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final /* bridge */ /* synthetic */ Person.Cover freeze() {
            return this;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public final Person.Cover.CoverInfo getCoverInfo() {
            return this.zzbp;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public final Person.Cover.CoverPhoto getCoverPhoto() {
            return this.zzbq;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final Object getFieldValue(FastJsonResponse.Field field) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                return this.zzbp;
            }
            if (safeParcelableFieldId == 3) {
                return this.zzbq;
            }
            if (safeParcelableFieldId == 4) {
                return Integer.valueOf(this.zzbr);
            }
            int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId2);
            throw new IllegalStateException(sb.toString());
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public final int getLayout() {
            return this.zzbr;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public final boolean hasCoverInfo() {
            return this.zzap.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public final boolean hasCoverPhoto() {
            return this.zzap.contains(3);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public final boolean hasLayout() {
            return this.zzap.contains(4);
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final int hashCode() {
            int safeParcelableFieldId = 0;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                }
            }
            return safeParcelableFieldId;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final boolean isDataValid() {
            return true;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 4) {
                this.zzbr = i;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            } else {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be an int.");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeParcelable(parcel, 2, this.zzbp, i, true);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeParcelable(parcel, 3, this.zzbq, i, true);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeInt(parcel, 4, this.zzbr);
            }
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    @VisibleForTesting
    @SafeParcelable.Class(creator = "PersonEntity_ImageEntityCreator")
    public static final class zzc extends FastSafeParcelableJsonResponse implements Person.Image {
        public static final Parcelable.Creator<zzc> CREATOR = new zzx();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

        @SafeParcelable.Indicator
        private final Set<Integer> zzap;

        @SafeParcelable.Field(id = 2)
        private String zzk;

        @SafeParcelable.VersionField(id = 1)
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
            zzao = map;
            map.put("url", FastJsonResponse.Field.forString("url", 2));
        }

        public zzc() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        public zzc(String str) {
            this.zzap = new HashSet();
            this.zzw = 1;
            this.zzk = str;
            this.zzap.add(2);
        }

        @SafeParcelable.Constructor
        zzc(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str) {
            this.zzap = set;
            this.zzw = i;
            this.zzk = str;
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzcVar = (zzc) obj;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!zzcVar.isFieldSet(field) || !getFieldValue(field).equals(zzcVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (zzcVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final /* bridge */ /* synthetic */ Person.Image freeze() {
            return this;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final Object getFieldValue(FastJsonResponse.Field field) {
            if (field.getSafeParcelableFieldId() == 2) {
                return this.zzk;
            }
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId);
            throw new IllegalStateException(sb.toString());
        }

        @Override // com.google.android.gms.plus.model.people.Person.Image
        public final String getUrl() {
            return this.zzk;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Image
        public final boolean hasUrl() {
            return this.zzap.contains(2);
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final int hashCode() {
            int safeParcelableFieldId = 0;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                }
            }
            return safeParcelableFieldId;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final boolean isDataValid() {
            return true;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzk = str2;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            } else {
                StringBuilder sb = new StringBuilder(54);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a String.");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeString(parcel, 2, this.zzk, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    @VisibleForTesting
    @SafeParcelable.Class(creator = "PersonEntity_NameEntityCreator")
    public static final class zzd extends FastSafeParcelableJsonResponse implements Person.Name {
        public static final Parcelable.Creator<zzd> CREATOR = new zzy();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

        @SafeParcelable.Indicator
        private final Set<Integer> zzap;

        @SafeParcelable.Field(id = 2)
        private String zzbw;

        @SafeParcelable.Field(id = 3)
        private String zzbx;

        @SafeParcelable.Field(id = 4)
        private String zzby;

        @SafeParcelable.Field(id = 5)
        private String zzbz;

        @SafeParcelable.Field(id = 6)
        private String zzca;

        @SafeParcelable.Field(id = 7)
        private String zzcb;

        @SafeParcelable.VersionField(id = 1)
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
            zzao = map;
            map.put("familyName", FastJsonResponse.Field.forString("familyName", 2));
            zzao.put("formatted", FastJsonResponse.Field.forString("formatted", 3));
            zzao.put("givenName", FastJsonResponse.Field.forString("givenName", 4));
            zzao.put("honorificPrefix", FastJsonResponse.Field.forString("honorificPrefix", 5));
            zzao.put("honorificSuffix", FastJsonResponse.Field.forString("honorificSuffix", 6));
            zzao.put("middleName", FastJsonResponse.Field.forString("middleName", 7));
        }

        public zzd() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @SafeParcelable.Constructor
        zzd(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6) {
            this.zzap = set;
            this.zzw = i;
            this.zzbw = str;
            this.zzbx = str2;
            this.zzby = str3;
            this.zzbz = str4;
            this.zzca = str5;
            this.zzcb = str6;
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final boolean equals(Object obj) {
            if (!(obj instanceof zzd)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzd zzdVar = (zzd) obj;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!zzdVar.isFieldSet(field) || !getFieldValue(field).equals(zzdVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (zzdVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final /* bridge */ /* synthetic */ Person.Name freeze() {
            return this;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final String getFamilyName() {
            return this.zzbw;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final Object getFieldValue(FastJsonResponse.Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzbw;
                case 3:
                    return this.zzbx;
                case 4:
                    return this.zzby;
                case 5:
                    return this.zzbz;
                case 6:
                    return this.zzca;
                case 7:
                    return this.zzcb;
                default:
                    int safeParcelableFieldId = field.getSafeParcelableFieldId();
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown safe parcelable id=");
                    sb.append(safeParcelableFieldId);
                    throw new IllegalStateException(sb.toString());
            }
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final String getFormatted() {
            return this.zzbx;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final String getGivenName() {
            return this.zzby;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final String getHonorificPrefix() {
            return this.zzbz;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final String getHonorificSuffix() {
            return this.zzca;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final String getMiddleName() {
            return this.zzcb;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final boolean hasFamilyName() {
            return this.zzap.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final boolean hasFormatted() {
            return this.zzap.contains(3);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final boolean hasGivenName() {
            return this.zzap.contains(4);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final boolean hasHonorificPrefix() {
            return this.zzap.contains(5);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final boolean hasHonorificSuffix() {
            return this.zzap.contains(6);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public final boolean hasMiddleName() {
            return this.zzap.contains(7);
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final int hashCode() {
            int safeParcelableFieldId = 0;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                }
            }
            return safeParcelableFieldId;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final boolean isDataValid() {
            return true;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzbw = str2;
                    break;
                case 3:
                    this.zzbx = str2;
                    break;
                case 4:
                    this.zzby = str2;
                    break;
                case 5:
                    this.zzbz = str2;
                    break;
                case 6:
                    this.zzca = str2;
                    break;
                case 7:
                    this.zzcb = str2;
                    break;
                default:
                    StringBuilder sb = new StringBuilder(54);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be a String.");
                    throw new IllegalArgumentException(sb.toString());
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeString(parcel, 2, this.zzbw, true);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeString(parcel, 3, this.zzbx, true);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeString(parcel, 4, this.zzby, true);
            }
            if (set.contains(5)) {
                SafeParcelWriter.writeString(parcel, 5, this.zzbz, true);
            }
            if (set.contains(6)) {
                SafeParcelWriter.writeString(parcel, 6, this.zzca, true);
            }
            if (set.contains(7)) {
                SafeParcelWriter.writeString(parcel, 7, this.zzcb, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    @VisibleForTesting
    @SafeParcelable.Class(creator = "PersonEntity_OrganizationsEntityCreator")
    public static final class zze extends FastSafeParcelableJsonResponse implements Person.Organizations {
        public static final Parcelable.Creator<zze> CREATOR = new zzz();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

        @SafeParcelable.Field(id = 6)
        private String mName;

        @SafeParcelable.Indicator
        private final Set<Integer> zzap;

        @SafeParcelable.Field(id = 2)
        private String zzcc;

        @SafeParcelable.Field(id = 3)
        private String zzcd;

        @SafeParcelable.Field(id = 4)
        private String zzce;

        @SafeParcelable.Field(id = 5)
        private String zzcf;

        @SafeParcelable.Field(id = 7)
        private boolean zzcg;

        @SafeParcelable.Field(id = 8)
        private String zzch;

        @SafeParcelable.Field(id = 9)
        private String zzci;

        @SafeParcelable.Field(id = 10)
        private int zzcj;

        @SafeParcelable.VersionField(id = 1)
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
            zzao = map;
            map.put("department", FastJsonResponse.Field.forString("department", 2));
            zzao.put("description", FastJsonResponse.Field.forString("description", 3));
            zzao.put("endDate", FastJsonResponse.Field.forString("endDate", 4));
            zzao.put("location", FastJsonResponse.Field.forString("location", 5));
            zzao.put("name", FastJsonResponse.Field.forString("name", 6));
            zzao.put("primary", FastJsonResponse.Field.forBoolean("primary", 7));
            zzao.put("startDate", FastJsonResponse.Field.forString("startDate", 8));
            zzao.put("title", FastJsonResponse.Field.forString("title", 9));
            zzao.put("type", FastJsonResponse.Field.withConverter("type", 10, new StringToIntConverter().add("work", 0).add("school", 1), false));
        }

        public zze() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @SafeParcelable.Constructor
        zze(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) boolean z, @SafeParcelable.Param(id = 8) String str6, @SafeParcelable.Param(id = 9) String str7, @SafeParcelable.Param(id = 10) int i2) {
            this.zzap = set;
            this.zzw = i;
            this.zzcc = str;
            this.zzcd = str2;
            this.zzce = str3;
            this.zzcf = str4;
            this.mName = str5;
            this.zzcg = z;
            this.zzch = str6;
            this.zzci = str7;
            this.zzcj = i2;
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final boolean equals(Object obj) {
            if (!(obj instanceof zze)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zze zzeVar = (zze) obj;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!zzeVar.isFieldSet(field) || !getFieldValue(field).equals(zzeVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (zzeVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final /* bridge */ /* synthetic */ Person.Organizations freeze() {
            return this;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final String getDepartment() {
            return this.zzcc;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final String getDescription() {
            return this.zzcd;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final String getEndDate() {
            return this.zzce;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final Object getFieldValue(FastJsonResponse.Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzcc;
                case 3:
                    return this.zzcd;
                case 4:
                    return this.zzce;
                case 5:
                    return this.zzcf;
                case 6:
                    return this.mName;
                case 7:
                    return Boolean.valueOf(this.zzcg);
                case 8:
                    return this.zzch;
                case 9:
                    return this.zzci;
                case 10:
                    return Integer.valueOf(this.zzcj);
                default:
                    int safeParcelableFieldId = field.getSafeParcelableFieldId();
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unknown safe parcelable id=");
                    sb.append(safeParcelableFieldId);
                    throw new IllegalStateException(sb.toString());
            }
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final String getLocation() {
            return this.zzcf;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final String getName() {
            return this.mName;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final String getStartDate() {
            return this.zzch;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final String getTitle() {
            return this.zzci;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final int getType() {
            return this.zzcj;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasDepartment() {
            return this.zzap.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasDescription() {
            return this.zzap.contains(3);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasEndDate() {
            return this.zzap.contains(4);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasLocation() {
            return this.zzap.contains(5);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasName() {
            return this.zzap.contains(6);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasPrimary() {
            return this.zzap.contains(7);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasStartDate() {
            return this.zzap.contains(8);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasTitle() {
            return this.zzap.contains(9);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean hasType() {
            return this.zzap.contains(10);
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final int hashCode() {
            int safeParcelableFieldId = 0;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                }
            }
            return safeParcelableFieldId;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final boolean isDataValid() {
            return true;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public final boolean isPrimary() {
            return this.zzcg;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 7) {
                this.zzcg = z;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            } else {
                StringBuilder sb = new StringBuilder(55);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a boolean.");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 10) {
                this.zzcj = i;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            } else {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be an int.");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzcc = str2;
                    break;
                case 3:
                    this.zzcd = str2;
                    break;
                case 4:
                    this.zzce = str2;
                    break;
                case 5:
                    this.zzcf = str2;
                    break;
                case 6:
                    this.mName = str2;
                    break;
                case 7:
                default:
                    StringBuilder sb = new StringBuilder(54);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be a String.");
                    throw new IllegalArgumentException(sb.toString());
                case 8:
                    this.zzch = str2;
                    break;
                case 9:
                    this.zzci = str2;
                    break;
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeString(parcel, 2, this.zzcc, true);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeString(parcel, 3, this.zzcd, true);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeString(parcel, 4, this.zzce, true);
            }
            if (set.contains(5)) {
                SafeParcelWriter.writeString(parcel, 5, this.zzcf, true);
            }
            if (set.contains(6)) {
                SafeParcelWriter.writeString(parcel, 6, this.mName, true);
            }
            if (set.contains(7)) {
                SafeParcelWriter.writeBoolean(parcel, 7, this.zzcg);
            }
            if (set.contains(8)) {
                SafeParcelWriter.writeString(parcel, 8, this.zzch, true);
            }
            if (set.contains(9)) {
                SafeParcelWriter.writeString(parcel, 9, this.zzci, true);
            }
            if (set.contains(10)) {
                SafeParcelWriter.writeInt(parcel, 10, this.zzcj);
            }
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    @VisibleForTesting
    @SafeParcelable.Class(creator = "PersonEntity_PlacesLivedEntityCreator")
    public static final class zzf extends FastSafeParcelableJsonResponse implements Person.PlacesLived {
        public static final Parcelable.Creator<zzf> CREATOR = new zzaa();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

        @SafeParcelable.Field(id = 3)
        private String mValue;

        @SafeParcelable.Indicator
        private final Set<Integer> zzap;

        @SafeParcelable.Field(id = 2)
        private boolean zzcg;

        @SafeParcelable.VersionField(id = 1)
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
            zzao = map;
            map.put("primary", FastJsonResponse.Field.forBoolean("primary", 2));
            zzao.put("value", FastJsonResponse.Field.forString("value", 3));
        }

        public zzf() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @SafeParcelable.Constructor
        zzf(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) String str) {
            this.zzap = set;
            this.zzw = i;
            this.zzcg = z;
            this.mValue = str;
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final boolean equals(Object obj) {
            if (!(obj instanceof zzf)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzf zzfVar = (zzf) obj;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!zzfVar.isFieldSet(field) || !getFieldValue(field).equals(zzfVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (zzfVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final /* bridge */ /* synthetic */ Person.PlacesLived freeze() {
            return this;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final Object getFieldValue(FastJsonResponse.Field field) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                return Boolean.valueOf(this.zzcg);
            }
            if (safeParcelableFieldId == 3) {
                return this.mValue;
            }
            int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId2);
            throw new IllegalStateException(sb.toString());
        }

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public final String getValue() {
            return this.mValue;
        }

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public final boolean hasPrimary() {
            return this.zzap.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public final boolean hasValue() {
            return this.zzap.contains(3);
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final int hashCode() {
            int safeParcelableFieldId = 0;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                }
            }
            return safeParcelableFieldId;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final boolean isDataValid() {
            return true;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public final boolean isPrimary() {
            return this.zzcg;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 2) {
                this.zzcg = z;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            } else {
                StringBuilder sb = new StringBuilder(55);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a boolean.");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 3) {
                this.mValue = str2;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            } else {
                StringBuilder sb = new StringBuilder(54);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a String.");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(2)) {
                SafeParcelWriter.writeBoolean(parcel, 2, this.zzcg);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeString(parcel, 3, this.mValue, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    @VisibleForTesting
    @SafeParcelable.Class(creator = "PersonEntity_UrlsEntityCreator")
    public static final class zzg extends FastSafeParcelableJsonResponse implements Person.Urls {
        public static final Parcelable.Creator<zzg> CREATOR = new zzab();
        private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzao;

        @SafeParcelable.Field(id = 4)
        private String mValue;

        @SafeParcelable.Indicator
        private final Set<Integer> zzap;

        @SafeParcelable.Field(id = 6)
        private int zzcj;

        @SafeParcelable.Field(id = 5)
        private String zzck;

        @SafeParcelable.Field(getter = "getType_DEPRECATED_FENACHO", id = 3)
        private final int zzcl;

        @SafeParcelable.VersionField(id = 1)
        private final int zzw;

        static {
            HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
            zzao = map;
            map.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, FastJsonResponse.Field.forString(PlusShare.KEY_CALL_TO_ACTION_LABEL, 5));
            zzao.put("type", FastJsonResponse.Field.withConverter("type", 6, new StringToIntConverter().add("home", 0).add("work", 1).add("blog", 2).add(Scopes.PROFILE, 3).add(FacebookRequestErrorClassification.KEY_OTHER, 4).add("otherProfile", 5).add("contributor", 6).add(PlaceFields.WEBSITE, 7), false));
            zzao.put("value", FastJsonResponse.Field.forString("value", 4));
        }

        public zzg() {
            this.zzcl = 4;
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @SafeParcelable.Constructor
        zzg(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 5) String str, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 3) int i3) {
            this.zzcl = 4;
            this.zzap = set;
            this.zzw = i;
            this.zzck = str;
            this.zzcj = i2;
            this.mValue = str2;
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final boolean equals(Object obj) {
            if (!(obj instanceof zzg)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzg zzgVar = (zzg) obj;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!zzgVar.isFieldSet(field) || !getFieldValue(field).equals(zzgVar.getFieldValue(field))) {
                        return false;
                    }
                } else if (zzgVar.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final /* bridge */ /* synthetic */ Person.Urls freeze() {
            return this;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final Object getFieldValue(FastJsonResponse.Field field) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 4) {
                return this.mValue;
            }
            if (safeParcelableFieldId == 5) {
                return this.zzck;
            }
            if (safeParcelableFieldId == 6) {
                return Integer.valueOf(this.zzcj);
            }
            int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown safe parcelable id=");
            sb.append(safeParcelableFieldId2);
            throw new IllegalStateException(sb.toString());
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public final String getLabel() {
            return this.zzck;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public final int getType() {
            return this.zzcj;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public final String getValue() {
            return this.mValue;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public final boolean hasLabel() {
            return this.zzap.contains(5);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public final boolean hasType() {
            return this.zzap.contains(6);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public final boolean hasValue() {
            return this.zzap.contains(4);
        }

        @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
        public final int hashCode() {
            int safeParcelableFieldId = 0;
            for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
                if (isFieldSet(field)) {
                    safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
                }
            }
            return safeParcelableFieldId;
        }

        @Override // com.google.android.gms.common.data.Freezable
        public final boolean isDataValid() {
            return true;
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final boolean isFieldSet(FastJsonResponse.Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 6) {
                this.zzcj = i;
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            } else {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be an int.");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        @Override // com.google.android.gms.common.server.response.FastJsonResponse
        protected final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            if (safeParcelableFieldId == 4) {
                this.mValue = str2;
            } else {
                if (safeParcelableFieldId != 5) {
                    StringBuilder sb = new StringBuilder(54);
                    sb.append("Field with id=");
                    sb.append(safeParcelableFieldId);
                    sb.append(" is not known to be a String.");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zzck = str2;
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set<Integer> set = this.zzap;
            if (set.contains(1)) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(3)) {
                SafeParcelWriter.writeInt(parcel, 3, 4);
            }
            if (set.contains(4)) {
                SafeParcelWriter.writeString(parcel, 4, this.mValue, true);
            }
            if (set.contains(5)) {
                SafeParcelWriter.writeString(parcel, 5, this.zzck, true);
            }
            if (set.contains(6)) {
                SafeParcelWriter.writeInt(parcel, 6, this.zzcj);
            }
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    static {
        HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
        zzao = map;
        map.put("aboutMe", FastJsonResponse.Field.forString("aboutMe", 2));
        zzao.put("ageRange", FastJsonResponse.Field.forConcreteType("ageRange", 3, zza.class));
        zzao.put("birthday", FastJsonResponse.Field.forString("birthday", 4));
        zzao.put("braggingRights", FastJsonResponse.Field.forString("braggingRights", 5));
        zzao.put("circledByCount", FastJsonResponse.Field.forInteger("circledByCount", 6));
        zzao.put(PlaceFields.COVER, FastJsonResponse.Field.forConcreteType(PlaceFields.COVER, 7, zzb.class));
        zzao.put("currentLocation", FastJsonResponse.Field.forString("currentLocation", 8));
        zzao.put("displayName", FastJsonResponse.Field.forString("displayName", 9));
        zzao.put("gender", FastJsonResponse.Field.withConverter("gender", 12, new StringToIntConverter().add("male", 0).add("female", 1).add(FacebookRequestErrorClassification.KEY_OTHER, 2), false));
        zzao.put("id", FastJsonResponse.Field.forString("id", 14));
        zzao.put(MessengerShareContentUtility.MEDIA_IMAGE, FastJsonResponse.Field.forConcreteType(MessengerShareContentUtility.MEDIA_IMAGE, 15, zzc.class));
        zzao.put("isPlusUser", FastJsonResponse.Field.forBoolean("isPlusUser", 16));
        zzao.put("language", FastJsonResponse.Field.forString("language", 18));
        zzao.put("name", FastJsonResponse.Field.forConcreteType("name", 19, zzd.class));
        zzao.put("nickname", FastJsonResponse.Field.forString("nickname", 20));
        zzao.put("objectType", FastJsonResponse.Field.withConverter("objectType", 21, new StringToIntConverter().add("person", 0).add(PlaceFields.PAGE, 1), false));
        zzao.put("organizations", FastJsonResponse.Field.forConcreteTypeArray("organizations", 22, zze.class));
        zzao.put("placesLived", FastJsonResponse.Field.forConcreteTypeArray("placesLived", 23, zzf.class));
        zzao.put("plusOneCount", FastJsonResponse.Field.forInteger("plusOneCount", 24));
        zzao.put("relationshipStatus", FastJsonResponse.Field.withConverter("relationshipStatus", 25, new StringToIntConverter().add(AdColonyUserMetadata.USER_SINGLE, 0).add("in_a_relationship", 1).add("engaged", 2).add(AdColonyUserMetadata.USER_MARRIED, 3).add("its_complicated", 4).add("open_relationship", 5).add("widowed", 6).add("in_domestic_partnership", 7).add("in_civil_union", 8), false));
        zzao.put("tagline", FastJsonResponse.Field.forString("tagline", 26));
        zzao.put("url", FastJsonResponse.Field.forString("url", 27));
        zzao.put("urls", FastJsonResponse.Field.forConcreteTypeArray("urls", 28, zzg.class));
        zzao.put("verified", FastJsonResponse.Field.forBoolean("verified", 29));
    }

    public zzr() {
        this.zzw = 1;
        this.zzap = new HashSet();
    }

    public zzr(String str, String str2, zzc zzcVar, int i, String str3) {
        this.zzw = 1;
        this.zzap = new HashSet();
        this.zzax = str;
        this.zzap.add(9);
        this.zzaz = str2;
        this.zzap.add(14);
        this.zzba = zzcVar;
        this.zzap.add(15);
        this.zzbf = i;
        this.zzap.add(21);
        this.zzk = str3;
        this.zzap.add(27);
    }

    @SafeParcelable.Constructor
    zzr(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) zza zzaVar, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) zzb zzbVar, @SafeParcelable.Param(id = 8) String str4, @SafeParcelable.Param(id = 9) String str5, @SafeParcelable.Param(id = 12) int i3, @SafeParcelable.Param(id = 14) String str6, @SafeParcelable.Param(id = 15) zzc zzcVar, @SafeParcelable.Param(id = 16) boolean z, @SafeParcelable.Param(id = 18) String str7, @SafeParcelable.Param(id = 19) zzd zzdVar, @SafeParcelable.Param(id = 20) String str8, @SafeParcelable.Param(id = 21) int i4, @SafeParcelable.Param(id = 22) List<zze> list, @SafeParcelable.Param(id = 23) List<zzf> list2, @SafeParcelable.Param(id = 24) int i5, @SafeParcelable.Param(id = 25) int i6, @SafeParcelable.Param(id = 26) String str9, @SafeParcelable.Param(id = 27) String str10, @SafeParcelable.Param(id = 28) List<zzg> list3, @SafeParcelable.Param(id = 29) boolean z2) {
        this.zzap = set;
        this.zzw = i;
        this.zzaq = str;
        this.zzar = zzaVar;
        this.zzas = str2;
        this.zzat = str3;
        this.zzau = i2;
        this.zzav = zzbVar;
        this.zzaw = str4;
        this.zzax = str5;
        this.zzay = i3;
        this.zzaz = str6;
        this.zzba = zzcVar;
        this.zzbb = z;
        this.zzbc = str7;
        this.zzbd = zzdVar;
        this.zzbe = str8;
        this.zzbf = i4;
        this.zzbg = list;
        this.zzbh = list2;
        this.zzbi = i5;
        this.zzbj = i6;
        this.zzbk = str9;
        this.zzk = str10;
        this.zzbl = list3;
        this.zzbm = z2;
    }

    public static zzr zza(byte[] bArr) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        zzr zzrVarCreateFromParcel = CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return zzrVarCreateFromParcel;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final <T extends FastJsonResponse> void addConcreteTypeArrayInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<T> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 22) {
            this.zzbg = arrayList;
        } else if (safeParcelableFieldId == 23) {
            this.zzbh = arrayList;
        } else {
            if (safeParcelableFieldId != 28) {
                String canonicalName = arrayList.getClass().getCanonicalName();
                StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 71);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not a known array of custom type.  Found ");
                sb.append(canonicalName);
                sb.append(".");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzbl = arrayList;
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.zzar = (zza) t;
        } else if (safeParcelableFieldId == 7) {
            this.zzav = (zzb) t;
        } else if (safeParcelableFieldId == 15) {
            this.zzba = (zzc) t;
        } else {
            if (safeParcelableFieldId != 19) {
                String canonicalName = t.getClass().getCanonicalName();
                StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 62);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not a known custom type.  Found ");
                sb.append(canonicalName);
                sb.append(".");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzbd = (zzd) t;
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
    public final boolean equals(Object obj) {
        if (!(obj instanceof zzr)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzr zzrVar = (zzr) obj;
        for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
            if (isFieldSet(field)) {
                if (!zzrVar.isFieldSet(field) || !getFieldValue(field).equals(zzrVar.getFieldValue(field))) {
                    return false;
                }
            } else if (zzrVar.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ Person freeze() {
        return this;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getAboutMe() {
        return this.zzaq;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final Person.AgeRange getAgeRange() {
        return this.zzar;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getBirthday() {
        return this.zzas;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getBraggingRights() {
        return this.zzat;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final int getCircledByCount() {
        return this.zzau;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final Person.Cover getCover() {
        return this.zzav;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getCurrentLocation() {
        return this.zzaw;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getDisplayName() {
        return this.zzax;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final /* synthetic */ Map getFieldMappings() {
        return zzao;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final Object getFieldValue(FastJsonResponse.Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 2:
                return this.zzaq;
            case 3:
                return this.zzar;
            case 4:
                return this.zzas;
            case 5:
                return this.zzat;
            case 6:
                return Integer.valueOf(this.zzau);
            case 7:
                return this.zzav;
            case 8:
                return this.zzaw;
            case 9:
                return this.zzax;
            case 10:
            case 11:
            case 13:
            case 17:
            default:
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("Unknown safe parcelable id=");
                sb.append(safeParcelableFieldId);
                throw new IllegalStateException(sb.toString());
            case 12:
                return Integer.valueOf(this.zzay);
            case 14:
                return this.zzaz;
            case 15:
                return this.zzba;
            case 16:
                return Boolean.valueOf(this.zzbb);
            case 18:
                return this.zzbc;
            case 19:
                return this.zzbd;
            case 20:
                return this.zzbe;
            case 21:
                return Integer.valueOf(this.zzbf);
            case 22:
                return this.zzbg;
            case 23:
                return this.zzbh;
            case 24:
                return Integer.valueOf(this.zzbi);
            case 25:
                return Integer.valueOf(this.zzbj);
            case 26:
                return this.zzbk;
            case 27:
                return this.zzk;
            case 28:
                return this.zzbl;
            case 29:
                return Boolean.valueOf(this.zzbm);
        }
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final int getGender() {
        return this.zzay;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getId() {
        return this.zzaz;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final Person.Image getImage() {
        return this.zzba;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getLanguage() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final Person.Name getName() {
        return this.zzbd;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getNickname() {
        return this.zzbe;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final int getObjectType() {
        return this.zzbf;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final List<Person.Organizations> getOrganizations() {
        return (ArrayList) this.zzbg;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final List<Person.PlacesLived> getPlacesLived() {
        return (ArrayList) this.zzbh;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final int getPlusOneCount() {
        return this.zzbi;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final int getRelationshipStatus() {
        return this.zzbj;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getTagline() {
        return this.zzbk;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final String getUrl() {
        return this.zzk;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final List<Person.Urls> getUrls() {
        return (ArrayList) this.zzbl;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasAboutMe() {
        return this.zzap.contains(2);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasAgeRange() {
        return this.zzap.contains(3);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasBirthday() {
        return this.zzap.contains(4);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasBraggingRights() {
        return this.zzap.contains(5);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasCircledByCount() {
        return this.zzap.contains(6);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasCover() {
        return this.zzap.contains(7);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasCurrentLocation() {
        return this.zzap.contains(8);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasDisplayName() {
        return this.zzap.contains(9);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasGender() {
        return this.zzap.contains(12);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasId() {
        return this.zzap.contains(14);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasImage() {
        return this.zzap.contains(15);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasIsPlusUser() {
        return this.zzap.contains(16);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasLanguage() {
        return this.zzap.contains(18);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasName() {
        return this.zzap.contains(19);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasNickname() {
        return this.zzap.contains(20);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasObjectType() {
        return this.zzap.contains(21);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasOrganizations() {
        return this.zzap.contains(22);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasPlacesLived() {
        return this.zzap.contains(23);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasPlusOneCount() {
        return this.zzap.contains(24);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasRelationshipStatus() {
        return this.zzap.contains(25);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasTagline() {
        return this.zzap.contains(26);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasUrl() {
        return this.zzap.contains(27);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasUrls() {
        return this.zzap.contains(28);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean hasVerified() {
        return this.zzap.contains(29);
    }

    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
    public final int hashCode() {
        int safeParcelableFieldId = 0;
        for (FastJsonResponse.Field<?, ?> field : zzao.values()) {
            if (isFieldSet(field)) {
                safeParcelableFieldId = safeParcelableFieldId + field.getSafeParcelableFieldId() + getFieldValue(field).hashCode();
            }
        }
        return safeParcelableFieldId;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean isPlusUser() {
        return this.zzbb;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public final boolean isVerified() {
        return this.zzbm;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 16) {
            this.zzbb = z;
        } else {
            if (safeParcelableFieldId != 29) {
                StringBuilder sb = new StringBuilder(55);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a boolean.");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzbm = z;
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 6) {
            this.zzau = i;
        } else if (safeParcelableFieldId == 12) {
            this.zzay = i;
        } else if (safeParcelableFieldId == 21) {
            this.zzbf = i;
        } else if (safeParcelableFieldId == 24) {
            this.zzbi = i;
        } else {
            if (safeParcelableFieldId != 25) {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be an int.");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzbj = i;
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.zzaq = str2;
        } else if (safeParcelableFieldId == 14) {
            this.zzaz = str2;
        } else if (safeParcelableFieldId == 18) {
            this.zzbc = str2;
        } else if (safeParcelableFieldId == 20) {
            this.zzbe = str2;
        } else if (safeParcelableFieldId == 4) {
            this.zzas = str2;
        } else if (safeParcelableFieldId == 5) {
            this.zzat = str2;
        } else if (safeParcelableFieldId == 8) {
            this.zzaw = str2;
        } else if (safeParcelableFieldId == 9) {
            this.zzax = str2;
        } else if (safeParcelableFieldId == 26) {
            this.zzbk = str2;
        } else {
            if (safeParcelableFieldId != 27) {
                StringBuilder sb = new StringBuilder(54);
                sb.append("Field with id=");
                sb.append(safeParcelableFieldId);
                sb.append(" is not known to be a String.");
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzk = str2;
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzap;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzw);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeString(parcel, 2, this.zzaq, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeParcelable(parcel, 3, this.zzar, i, true);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeString(parcel, 4, this.zzas, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeString(parcel, 5, this.zzat, true);
        }
        if (set.contains(6)) {
            SafeParcelWriter.writeInt(parcel, 6, this.zzau);
        }
        if (set.contains(7)) {
            SafeParcelWriter.writeParcelable(parcel, 7, this.zzav, i, true);
        }
        if (set.contains(8)) {
            SafeParcelWriter.writeString(parcel, 8, this.zzaw, true);
        }
        if (set.contains(9)) {
            SafeParcelWriter.writeString(parcel, 9, this.zzax, true);
        }
        if (set.contains(12)) {
            SafeParcelWriter.writeInt(parcel, 12, this.zzay);
        }
        if (set.contains(14)) {
            SafeParcelWriter.writeString(parcel, 14, this.zzaz, true);
        }
        if (set.contains(15)) {
            SafeParcelWriter.writeParcelable(parcel, 15, this.zzba, i, true);
        }
        if (set.contains(16)) {
            SafeParcelWriter.writeBoolean(parcel, 16, this.zzbb);
        }
        if (set.contains(18)) {
            SafeParcelWriter.writeString(parcel, 18, this.zzbc, true);
        }
        if (set.contains(19)) {
            SafeParcelWriter.writeParcelable(parcel, 19, this.zzbd, i, true);
        }
        if (set.contains(20)) {
            SafeParcelWriter.writeString(parcel, 20, this.zzbe, true);
        }
        if (set.contains(21)) {
            SafeParcelWriter.writeInt(parcel, 21, this.zzbf);
        }
        if (set.contains(22)) {
            SafeParcelWriter.writeTypedList(parcel, 22, this.zzbg, true);
        }
        if (set.contains(23)) {
            SafeParcelWriter.writeTypedList(parcel, 23, this.zzbh, true);
        }
        if (set.contains(24)) {
            SafeParcelWriter.writeInt(parcel, 24, this.zzbi);
        }
        if (set.contains(25)) {
            SafeParcelWriter.writeInt(parcel, 25, this.zzbj);
        }
        if (set.contains(26)) {
            SafeParcelWriter.writeString(parcel, 26, this.zzbk, true);
        }
        if (set.contains(27)) {
            SafeParcelWriter.writeString(parcel, 27, this.zzk, true);
        }
        if (set.contains(28)) {
            SafeParcelWriter.writeTypedList(parcel, 28, this.zzbl, true);
        }
        if (set.contains(29)) {
            SafeParcelWriter.writeBoolean(parcel, 29, this.zzbm);
        }
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
