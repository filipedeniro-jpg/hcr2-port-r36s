package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection("GamesClientImpl.java")
@RetainForClient
@SafeParcelable.Class(creator = "ParticipantEntityCreator")
@SafeParcelable.Reserved({1000})
public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Parcelable.Creator<ParticipantEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getStatus", id = 5)
    private final int status;

    @Nullable
    @SafeParcelable.Field(getter = "getIconImageUrl", id = 11)
    private final String zzac;

    @Nullable
    @SafeParcelable.Field(getter = "getHiResImageUrl", id = 12)
    private final String zzad;

    @Nullable
    @SafeParcelable.Field(getter = "getPlayer", id = 8)
    private final PlayerEntity zzfj;

    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    private final String zzn;

    @SafeParcelable.Field(getter = "getParticipantId", id = 1)
    private final String zzpg;

    @SafeParcelable.Field(getter = "getClientAddress", id = 6)
    private final String zzph;

    @SafeParcelable.Field(getter = "isConnectedToRoom", id = 7)
    private final boolean zzpi;

    @SafeParcelable.Field(getter = "getCapabilities", id = 9)
    private final int zzpj;

    @Nullable
    @SafeParcelable.Field(getter = "getResult", id = 10)
    private final ParticipantResult zzpk;

    @Nullable
    @SafeParcelable.Field(getter = "getIconImageUri", id = 3)
    private final Uri zzr;

    @Nullable
    @SafeParcelable.Field(getter = "getHiResImageUri", id = 4)
    private final Uri zzs;

    static final class zza extends zzc {
        zza() {
        }

        @Override // com.google.android.gms.games.multiplayer.zzc
        /* JADX INFO: renamed from: zzf */
        public final ParticipantEntity createFromParcel(Parcel parcel) {
            if (ParticipantEntity.zzb(ParticipantEntity.getUnparcelClientVersion()) || ParticipantEntity.canUnparcelSafely(ParticipantEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            return new ParticipantEntity(string, string2, string3 == null ? null : Uri.parse(string3), string4 == null ? null : Uri.parse(string4), parcel.readInt(), parcel.readString(), parcel.readInt() > 0, parcel.readInt() > 0 ? PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, null, null, null);
        }

        @Override // com.google.android.gms.games.multiplayer.zzc, android.os.Parcelable.Creator
        public final /* synthetic */ ParticipantEntity createFromParcel(Parcel parcel) {
            return createFromParcel(parcel);
        }
    }

    public static ArrayList<ParticipantEntity> zza(@NonNull List<Participant> list) {
        ArrayList<ParticipantEntity> arrayList = new ArrayList<>(list.size());
        for (Participant participant : list) {
            arrayList.add(participant instanceof ParticipantEntity ? (ParticipantEntity) participant : new ParticipantEntity(participant));
        }
        return arrayList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Participant freeze() {
        return this;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ParticipantEntity(Participant participant) {
        Player player = participant.getPlayer();
        this(participant, player == null ? null : new PlayerEntity(player));
    }

    private ParticipantEntity(Participant participant, @Nullable PlayerEntity playerEntity) {
        this.zzpg = participant.getParticipantId();
        this.zzn = participant.getDisplayName();
        this.zzr = participant.getIconImageUri();
        this.zzs = participant.getHiResImageUri();
        this.status = participant.getStatus();
        this.zzph = participant.zzdn();
        this.zzpi = participant.isConnectedToRoom();
        this.zzfj = playerEntity;
        this.zzpj = participant.getCapabilities();
        this.zzpk = participant.getResult();
        this.zzac = participant.getIconImageUrl();
        this.zzad = participant.getHiResImageUrl();
    }

    @SafeParcelable.Constructor
    ParticipantEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) Uri uri, @Nullable @SafeParcelable.Param(id = 4) Uri uri2, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 7) boolean z, @Nullable @SafeParcelable.Param(id = 8) PlayerEntity playerEntity, @SafeParcelable.Param(id = 9) int i2, @Nullable @SafeParcelable.Param(id = 10) ParticipantResult participantResult, @Nullable @SafeParcelable.Param(id = 11) String str4, @Nullable @SafeParcelable.Param(id = 12) String str5) {
        this.zzpg = str;
        this.zzn = str2;
        this.zzr = uri;
        this.zzs = uri2;
        this.status = i;
        this.zzph = str3;
        this.zzpi = z;
        this.zzfj = playerEntity;
        this.zzpj = i2;
        this.zzpk = participantResult;
        this.zzac = str4;
        this.zzad = str5;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final int getStatus() {
        return this.status;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String zzdn() {
        return this.zzph;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final boolean isConnectedToRoom() {
        return this.zzpi;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getDisplayName() {
        PlayerEntity playerEntity = this.zzfj;
        if (playerEntity == null) {
            return this.zzn;
        }
        return playerEntity.getDisplayName();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        PlayerEntity playerEntity = this.zzfj;
        if (playerEntity == null) {
            String str = this.zzn;
            if (str == null) {
                charArrayBuffer.sizeCopied = 0;
                return;
            } else {
                DataUtils.copyStringToBuffer(str, charArrayBuffer);
                return;
            }
        }
        playerEntity.getDisplayName(charArrayBuffer);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    @Nullable
    public final Uri getIconImageUri() {
        PlayerEntity playerEntity = this.zzfj;
        if (playerEntity == null) {
            return this.zzr;
        }
        return playerEntity.getIconImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    @Nullable
    public final String getIconImageUrl() {
        PlayerEntity playerEntity = this.zzfj;
        if (playerEntity == null) {
            return this.zzac;
        }
        return playerEntity.getIconImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    @Nullable
    public final Uri getHiResImageUri() {
        PlayerEntity playerEntity = this.zzfj;
        if (playerEntity == null) {
            return this.zzs;
        }
        return playerEntity.getHiResImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getHiResImageUrl() {
        PlayerEntity playerEntity = this.zzfj;
        if (playerEntity == null) {
            return this.zzad;
        }
        return playerEntity.getHiResImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getParticipantId() {
        return this.zzpg;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final Player getPlayer() {
        return this.zzfj;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final ParticipantResult getResult() {
        return this.zzpk;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final int getCapabilities() {
        return this.zzpj;
    }

    @Override // com.google.android.gms.common.internal.DowngradeableSafeParcel
    public final void setShouldDowngrade(boolean z) {
        super.setShouldDowngrade(z);
        PlayerEntity playerEntity = this.zzfj;
        if (playerEntity != null) {
            playerEntity.setShouldDowngrade(z);
        }
    }

    public final int hashCode() {
        return zza(this);
    }

    static int zza(Participant participant) {
        return Objects.hashCode(participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.zzdn(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), Integer.valueOf(participant.getCapabilities()), participant.getResult(), participant.getParticipantId());
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    static boolean zza(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return Objects.equal(participant2.getPlayer(), participant.getPlayer()) && Objects.equal(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && Objects.equal(participant2.zzdn(), participant.zzdn()) && Objects.equal(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && Objects.equal(participant2.getDisplayName(), participant.getDisplayName()) && Objects.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && Objects.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && Objects.equal(Integer.valueOf(participant2.getCapabilities()), Integer.valueOf(participant.getCapabilities())) && Objects.equal(participant2.getResult(), participant.getResult()) && Objects.equal(participant2.getParticipantId(), participant.getParticipantId());
    }

    public final String toString() {
        return zzb(this);
    }

    static String zzb(Participant participant) {
        return Objects.toStringHelper(participant).add("ParticipantId", participant.getParticipantId()).add("Player", participant.getPlayer()).add("Status", Integer.valueOf(participant.getStatus())).add("ClientAddress", participant.zzdn()).add("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).add("DisplayName", participant.getDisplayName()).add("IconImage", participant.getIconImageUri()).add("IconImageUrl", participant.getIconImageUrl()).add("HiResImage", participant.getHiResImageUri()).add("HiResImageUrl", participant.getHiResImageUrl()).add("Capabilities", Integer.valueOf(participant.getCapabilities())).add("Result", participant.getResult()).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        if (!shouldDowngrade()) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, getParticipantId(), false);
            SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
            SafeParcelWriter.writeParcelable(parcel, 3, getIconImageUri(), i, false);
            SafeParcelWriter.writeParcelable(parcel, 4, getHiResImageUri(), i, false);
            SafeParcelWriter.writeInt(parcel, 5, getStatus());
            SafeParcelWriter.writeString(parcel, 6, this.zzph, false);
            SafeParcelWriter.writeBoolean(parcel, 7, isConnectedToRoom());
            SafeParcelWriter.writeParcelable(parcel, 8, getPlayer(), i, false);
            SafeParcelWriter.writeInt(parcel, 9, this.zzpj);
            SafeParcelWriter.writeParcelable(parcel, 10, getResult(), i, false);
            SafeParcelWriter.writeString(parcel, 11, getIconImageUrl(), false);
            SafeParcelWriter.writeString(parcel, 12, getHiResImageUrl(), false);
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
            return;
        }
        parcel.writeString(this.zzpg);
        parcel.writeString(this.zzn);
        Uri uri = this.zzr;
        parcel.writeString(uri == null ? null : uri.toString());
        Uri uri2 = this.zzs;
        parcel.writeString(uri2 != null ? uri2.toString() : null);
        parcel.writeInt(this.status);
        parcel.writeString(this.zzph);
        parcel.writeInt(this.zzpi ? 1 : 0);
        if (this.zzfj == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            this.zzfj.writeToParcel(parcel, i);
        }
    }
}
