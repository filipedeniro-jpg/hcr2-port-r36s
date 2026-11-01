package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzjt {
    static String zze(zzfx zzfxVar) {
        zzjw zzjwVar = new zzjw(zzfxVar);
        StringBuilder sb = new StringBuilder(zzjwVar.size());
        for (int i = 0; i < zzjwVar.size(); i++) {
            byte bZzv = zzjwVar.zzv(i);
            if (bZzv == 34) {
                sb.append("\\\"");
            } else if (bZzv == 39) {
                sb.append("\\'");
            } else if (bZzv != 92) {
                switch (bZzv) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (bZzv < 32 || bZzv > 126) {
                            sb.append('\\');
                            sb.append((char) (((bZzv >>> 6) & 3) + 48));
                            sb.append((char) (((bZzv >>> 3) & 7) + 48));
                            sb.append((char) ((bZzv & 7) + 48));
                        } else {
                            sb.append((char) bZzv);
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
