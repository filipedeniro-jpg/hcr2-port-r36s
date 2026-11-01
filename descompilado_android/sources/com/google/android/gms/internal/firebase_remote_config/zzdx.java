package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzdx extends zzdi<String> {
    private int limit;
    private final zzdj zzhg;
    final CharSequence zzhl;
    private int offset = 0;
    private final boolean zzhh = false;

    protected zzdx(zzds zzdsVar, CharSequence charSequence) {
        this.zzhg = zzdsVar.zzhg;
        this.limit = zzdsVar.limit;
        this.zzhl = charSequence;
    }

    abstract int zzk(int i);

    abstract int zzl(int i);

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdi
    protected final /* synthetic */ String zzcg() {
        int i = this.offset;
        while (true) {
            int i2 = this.offset;
            if (i2 != -1) {
                int iZzk = zzk(i2);
                if (iZzk == -1) {
                    iZzk = this.zzhl.length();
                    this.offset = -1;
                } else {
                    this.offset = zzl(iZzk);
                }
                int i3 = this.offset;
                if (i3 == i) {
                    this.offset = i3 + 1;
                    if (this.offset > this.zzhl.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (i < iZzk && this.zzhg.zzb(this.zzhl.charAt(i))) {
                        i++;
                    }
                    while (iZzk > i && this.zzhg.zzb(this.zzhl.charAt(iZzk - 1))) {
                        iZzk--;
                    }
                    if (this.zzhh && i == iZzk) {
                        i = this.offset;
                    } else {
                        int i4 = this.limit;
                        if (i4 == 1) {
                            iZzk = this.zzhl.length();
                            this.offset = -1;
                            while (iZzk > i && this.zzhg.zzb(this.zzhl.charAt(iZzk - 1))) {
                                iZzk--;
                            }
                        } else {
                            this.limit = i4 - 1;
                        }
                        return this.zzhl.subSequence(i, iZzk).toString();
                    }
                }
            } else {
                zzch();
                return null;
            }
        }
    }
}
