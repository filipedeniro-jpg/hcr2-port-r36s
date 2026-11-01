package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class DK implements DJ {
    public final DH B;
    public final boolean C;
    public Runnable D;
    public final EC E;
    public final ArrayList<DI> F;
    public final ArrayList<DI> G;
    private int H;
    private AnonymousClass86<DI> I;

    public DK(DH callback) {
        this(callback, false);
    }

    private DK(DH callback, boolean disableRecycler) {
        this.I = new AnonymousClass87(30);
        this.F = new ArrayList<>();
        this.G = new ArrayList<>();
        this.H = 0;
        this.B = callback;
        this.C = disableRecycler;
        this.E = new EC(this);
    }

    public final int A(int i) {
        int size = this.F.size();
        for (int i2 = 0; i2 < size; i2++) {
            DI di = this.F.get(i2);
            switch (di.B) {
                case 1:
                    if (di.E <= i) {
                        i += di.C;
                    }
                    break;
                case 2:
                    if (di.E > i) {
                        continue;
                    } else {
                        if (di.E + di.C > i) {
                            return -1;
                        }
                        i -= di.C;
                    }
                    break;
                case 8:
                    if (di.E == i) {
                        i = di.C;
                    } else {
                        if (di.E < i) {
                            i--;
                        }
                        if (di.C <= i) {
                            i++;
                        }
                    }
                    break;
            }
        }
        return i;
    }

    private int B(int end, int cmd) {
        int i;
        int i2;
        for (int size = this.G.size() - 1; size >= 0; size--) {
            DI di = this.G.get(size);
            if (di.B == 8) {
                if (di.E < di.C) {
                    i = di.E;
                    i2 = di.C;
                } else {
                    i = di.C;
                    i2 = di.E;
                }
                if (end >= i && end <= i2) {
                    if (i == di.E) {
                        if (cmd == 1) {
                            di.C++;
                        } else if (cmd == 2) {
                            di.C--;
                        }
                        end++;
                    } else {
                        if (cmd == 1) {
                            di.E++;
                        } else if (cmd == 2) {
                            di.E--;
                        }
                        end--;
                    }
                } else if (end < di.E) {
                    if (cmd == 1) {
                        di.E++;
                        di.C++;
                    } else if (cmd == 2) {
                        di.E--;
                        di.C--;
                    }
                }
            } else if (di.E <= end) {
                if (di.B == 1) {
                    end -= di.C;
                } else if (di.B == 2) {
                    end += di.C;
                }
            } else if (cmd == 1) {
                di.E++;
            } else if (cmd == 2) {
                di.E--;
            }
        }
        for (int size2 = this.G.size() - 1; size2 >= 0; size2--) {
            DI di2 = this.G.get(size2);
            if (di2.B == 8) {
                if (di2.C == di2.E || di2.C < 0) {
                    this.G.remove(size2);
                    jF(di2);
                }
            } else if (di2.C <= 0) {
                this.G.remove(size2);
                jF(di2);
            }
        }
        return end;
    }

    public final void B() {
        int size = this.G.size();
        for (int i = 0; i < size; i++) {
            this.B.tD(this.G.get(i));
        }
        L(this.G);
        this.H = 0;
    }

    public final void C() {
        B();
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            DI di = this.F.get(i);
            int i2 = di.B;
            switch (i2) {
                case 1:
                    this.B.tD(di);
                    DH dh = this.B;
                    int i3 = di.E;
                    int i4 = di.C;
                    dh.ID(i3, i4);
                    break;
                case 2:
                    this.B.tD(di);
                    DH dh2 = this.B;
                    int i5 = di.E;
                    int i6 = di.C;
                    dh2.KD(i5, i6);
                    break;
                case 4:
                    this.B.tD(di);
                    this.B.FD(di.E, di.C, di.D);
                    break;
                case 8:
                    this.B.tD(di);
                    DH dh3 = this.B;
                    int i7 = di.E;
                    int i8 = di.C;
                    dh3.JD(i7, i8);
                    break;
            }
            if (this.D != null) {
                this.D.run();
            }
        }
        L(this.F);
        this.H = 0;
    }

    private void C(DI op) {
        K(op);
    }

    public final int D(int position) {
        return J(position, 0);
    }

    private void D(DI op) {
        K(op);
    }

    private void E(DI op) {
        int tmpStart = op.E;
        int type = 0;
        int i = op.E + op.C;
        byte b = -1;
        int i2 = op.E;
        while (i2 < i) {
            boolean z = false;
            AbstractC0502Er vh = this.B.rB(i2);
            if (vh != null || G(i2)) {
                if (b == 0) {
                    H(HD(2, tmpStart, type, null));
                    z = true;
                }
                b = 1;
            } else {
                if (b == 1) {
                    K(HD(2, tmpStart, type, null));
                    z = true;
                }
                b = 0;
            }
            if (z) {
                i2 -= type;
                i -= type;
                type = 1;
            } else {
                type++;
            }
            i2++;
        }
        if (type != op.C) {
            jF(op);
            op = HD(2, tmpStart, type, null);
        }
        if (b == 0) {
            H(op);
        } else {
            K(op);
        }
    }

    public final boolean E(int updateTypes) {
        return (this.H & updateTypes) != 0;
    }

    private void F(DI op) {
        int tmpCount = op.E;
        int tmpEnd = 0;
        int tmpStart = op.E;
        int tmpStart2 = tmpStart + op.C;
        byte b = -1;
        for (int i = op.E; i < tmpStart2; i++) {
            if (this.B.rB(i) != null || G(i)) {
                if (b == 0) {
                    H(HD(4, tmpCount, tmpEnd, op.D));
                    tmpEnd = 0;
                    tmpCount = i;
                }
                b = 1;
            } else {
                if (b == 1) {
                    K(HD(4, tmpCount, tmpEnd, op.D));
                    tmpEnd = 0;
                    tmpCount = i;
                }
                b = 0;
            }
            tmpEnd++;
        }
        int type = op.C;
        if (tmpEnd != type) {
            Object obj = op.D;
            jF(op);
            op = HD(4, tmpCount, tmpEnd, obj);
        }
        if (b == 0) {
            H(op);
        } else {
            K(op);
        }
    }

    public final boolean F() {
        return this.F.size() > 0;
    }

    public final boolean G() {
        return (this.G.isEmpty() || this.F.isEmpty()) ? false : true;
    }

    private boolean G(int position) {
        int size = this.G.size();
        for (int i = 0; i < size; i++) {
            DI di = this.G.get(i);
            if (di.B == 8) {
                if (J(di.C, i + 1) == position) {
                    return true;
                }
            } else if (di.B == 1) {
                int i2 = di.E + di.C;
                for (int i3 = di.E; i3 < i2; i3++) {
                    if (J(i3, i + 1) == position) {
                        return true;
                    }
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public final void H() {
        L(this.F);
        L(this.G);
        this.H = 0;
    }

    private void H(DI op) {
        int i;
        if (op.B == 1 || op.B == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iB = B(op.E, op.B);
        int i2 = 1;
        int i3 = op.E;
        int tmpStart = op.B;
        switch (tmpStart) {
            case 2:
                i = 0;
                break;
            case 3:
            default:
                throw new IllegalArgumentException("op should be remove or update." + op);
            case 4:
                i = 1;
                break;
        }
        for (int i4 = 1; i4 < op.C; i4++) {
            int positionMultiplier = op.E;
            int pos = op.B;
            int iB2 = B(positionMultiplier + (i * i4), pos);
            int updatedPos = 0;
            int pos2 = op.B;
            switch (pos2) {
                case 2:
                    updatedPos = iB2 == iB ? 1 : 0;
                    break;
                case 4:
                    updatedPos = iB2 == iB + 1 ? 1 : 0;
                    break;
            }
            if (updatedPos != 0) {
                i2++;
            } else {
                int updatedPos2 = op.B;
                DI tmp = HD(updatedPos2, iB, i2, op.D);
                I(tmp, i3);
                jF(tmp);
                int updatedPos3 = op.B;
                if (updatedPos3 == 4) {
                    i3 += i2;
                }
                iB = iB2;
                i2 = 1;
            }
        }
        Object obj = op.D;
        jF(op);
        if (i2 > 0) {
            DI tmp2 = HD(op.B, iB, i2, obj);
            I(tmp2, i3);
            jF(tmp2);
        }
    }

    @Override // com.facebook.ads.redexgen.X.DJ
    public final DI HD(int cmd, int positionStart, int itemCount, Object payload) {
        DI diSB = this.I.SB();
        if (diSB == null) {
            return new DI(cmd, positionStart, itemCount, payload);
        }
        diSB.B = cmd;
        diSB.E = positionStart;
        diSB.C = itemCount;
        diSB.D = payload;
        return diSB;
    }

    public final void I() {
        this.E.A(this.F);
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            DI di = this.F.get(i);
            switch (di.B) {
                case 1:
                    C(di);
                    break;
                case 2:
                    E(di);
                    break;
                case 4:
                    F(di);
                    break;
                case 8:
                    D(di);
                    break;
            }
            if (this.D != null) {
                this.D.run();
            }
        }
        this.F.clear();
    }

    private final void I(DI op, int offsetStart) {
        this.B.rD(op);
        switch (op.B) {
            case 2:
                this.B.KD(offsetStart, op.C);
                return;
            case 3:
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            case 4:
                this.B.FD(offsetStart, op.C, op.D);
                return;
        }
    }

    private final int J(int i, int firstPostponedItem) {
        int size = this.G.size();
        while (firstPostponedItem < size) {
            DI di = this.G.get(firstPostponedItem);
            if (di.B == 8) {
                if (di.E == i) {
                    i = di.C;
                } else {
                    if (di.E < i) {
                        i--;
                    }
                    if (di.C <= i) {
                        i++;
                    }
                }
            } else if (di.E > i) {
                continue;
            } else if (di.B == 2) {
                if (i < di.E + di.C) {
                    return -1;
                }
                i -= di.C;
            } else if (di.B == 1) {
                i += di.C;
            }
            firstPostponedItem++;
        }
        return i;
    }

    private void K(DI op) {
        this.G.add(op);
        switch (op.B) {
            case 1:
                this.B.ID(op.E, op.C);
                return;
            case 2:
                this.B.LD(op.E, op.C);
                return;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
                throw new IllegalArgumentException("Unknown update op type for " + op);
            case 4:
                this.B.FD(op.E, op.C, op.D);
                return;
            case 8:
                this.B.JD(op.E, op.C);
                return;
        }
    }

    private final void L(List<DI> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            jF(list.get(i));
        }
        list.clear();
    }

    @Override // com.facebook.ads.redexgen.X.DJ
    public final void jF(DI op) {
        if (!this.C) {
            op.D = null;
            this.I.lF(op);
        }
    }
}
