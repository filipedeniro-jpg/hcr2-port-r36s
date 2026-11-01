package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zzbd;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class LoadMatchesResponse {
    private final InvitationBuffer zzqh;
    private final TurnBasedMatchBuffer zzqi;
    private final TurnBasedMatchBuffer zzqj;
    private final TurnBasedMatchBuffer zzqk;

    public LoadMatchesResponse(Bundle bundle) {
        DataHolder dataHolderZza = zza(bundle, 0);
        if (dataHolderZza != null) {
            this.zzqh = new InvitationBuffer(dataHolderZza);
        } else {
            this.zzqh = null;
        }
        DataHolder dataHolderZza2 = zza(bundle, 1);
        if (dataHolderZza2 != null) {
            this.zzqi = new TurnBasedMatchBuffer(dataHolderZza2);
        } else {
            this.zzqi = null;
        }
        DataHolder dataHolderZza3 = zza(bundle, 2);
        if (dataHolderZza3 != null) {
            this.zzqj = new TurnBasedMatchBuffer(dataHolderZza3);
        } else {
            this.zzqj = null;
        }
        DataHolder dataHolderZza4 = zza(bundle, 3);
        if (dataHolderZza4 != null) {
            this.zzqk = new TurnBasedMatchBuffer(dataHolderZza4);
        } else {
            this.zzqk = null;
        }
    }

    private static DataHolder zza(Bundle bundle, int i) {
        String str;
        if (i == 0) {
            str = "TURN_STATUS_INVITED";
        } else if (i == 1) {
            str = "TURN_STATUS_MY_TURN";
        } else if (i == 2) {
            str = "TURN_STATUS_THEIR_TURN";
        } else if (i != 3) {
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown match turn status: ");
            sb.append(i);
            zzbd.e("MatchTurnStatus", sb.toString());
            str = "TURN_STATUS_UNKNOWN";
        } else {
            str = "TURN_STATUS_COMPLETE";
        }
        if (bundle.containsKey(str)) {
            return (DataHolder) bundle.getParcelable(str);
        }
        return null;
    }

    public final InvitationBuffer getInvitations() {
        return this.zzqh;
    }

    public final TurnBasedMatchBuffer getMyTurnMatches() {
        return this.zzqi;
    }

    public final TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.zzqj;
    }

    public final TurnBasedMatchBuffer getCompletedMatches() {
        return this.zzqk;
    }

    @Deprecated
    public final void close() {
        release();
    }

    public final void release() {
        InvitationBuffer invitationBuffer = this.zzqh;
        if (invitationBuffer != null) {
            invitationBuffer.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer = this.zzqi;
        if (turnBasedMatchBuffer != null) {
            turnBasedMatchBuffer.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer2 = this.zzqj;
        if (turnBasedMatchBuffer2 != null) {
            turnBasedMatchBuffer2.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer3 = this.zzqk;
        if (turnBasedMatchBuffer3 != null) {
            turnBasedMatchBuffer3.release();
        }
    }

    public final boolean hasData() {
        InvitationBuffer invitationBuffer = this.zzqh;
        if (invitationBuffer != null && invitationBuffer.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer = this.zzqi;
        if (turnBasedMatchBuffer != null && turnBasedMatchBuffer.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer2 = this.zzqj;
        if (turnBasedMatchBuffer2 != null && turnBasedMatchBuffer2.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer3 = this.zzqk;
        return turnBasedMatchBuffer3 != null && turnBasedMatchBuffer3.getCount() > 0;
    }
}
