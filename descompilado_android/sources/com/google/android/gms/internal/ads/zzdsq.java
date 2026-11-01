package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsq {
    static String zzdc(zzdot zzdotVar) {
        zzdst zzdstVar = new zzdst(zzdotVar);
        StringBuilder sb = new StringBuilder(zzdstVar.size());
        for (int i = 0; i < zzdstVar.size(); i++) {
            byte bZzfn = zzdstVar.zzfn(i);
            if (bZzfn == 34) {
                sb.append("\\\"");
            } else if (bZzfn == 39) {
                sb.append("\\'");
            } else if (bZzfn != 92) {
                switch (bZzfn) {
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
                        if (bZzfn < 32 || bZzfn > 126) {
                            sb.append('\\');
                            sb.append((char) (((bZzfn >>> 6) & 3) + 48));
                            sb.append((char) (((bZzfn >>> 3) & 7) + 48));
                            sb.append((char) ((bZzfn & 7) + 48));
                        } else {
                            sb.append((char) bZzfn);
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
