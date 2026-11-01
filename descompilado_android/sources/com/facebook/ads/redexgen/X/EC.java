package com.facebook.ads.redexgen.X;

import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class EC {
    public final DJ B;

    public EC(DJ callback) {
        this.B = callback;
    }

    public final void A(List<DI> list) {
        while (true) {
            int iB = B(list);
            if (iB != -1) {
                D(list, iB, iB + 1);
            } else {
                return;
            }
        }
    }

    private int B(List<DI> list) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).B == 8) {
                if (z) {
                    return size;
                }
            } else {
                z = true;
            }
        }
        return -1;
    }

    private void C(List<DI> list, int move, DI moveOp, int add, DI addOp) {
        int i = 0;
        if (moveOp.C < addOp.E) {
            i = 0 - 1;
        }
        if (moveOp.E < addOp.E) {
            i++;
        }
        if (addOp.E <= moveOp.E) {
            moveOp.E += addOp.C;
        }
        if (addOp.E <= moveOp.C) {
            moveOp.C += addOp.C;
        }
        addOp.E += i;
        list.set(move, addOp);
        list.set(add, moveOp);
    }

    private void D(List<DI> list, int badMove, int next) {
        DI nextOp = list.get(badMove);
        DI di = list.get(next);
        switch (di.B) {
            case 1:
                C(list, badMove, nextOp, next, di);
                break;
            case 2:
                E(list, badMove, nextOp, next, di);
                break;
            case 4:
                F(list, badMove, nextOp, next, di);
                break;
        }
    }

    private final void E(List<DI> list, int movePos, DI moveOp, int removePos, DI removeOp) {
        boolean z;
        DI diHD = null;
        boolean z2 = false;
        if (moveOp.E < moveOp.C) {
            z = false;
            if (removeOp.E == moveOp.E && removeOp.C == moveOp.C - moveOp.E) {
                z2 = true;
            }
        } else {
            z = true;
            if (removeOp.E == moveOp.C + 1 && removeOp.C == moveOp.E - moveOp.C) {
                z2 = true;
            }
        }
        if (moveOp.C < removeOp.E) {
            removeOp.E--;
        } else if (moveOp.C < removeOp.E + removeOp.C) {
            removeOp.C--;
            moveOp.B = 2;
            moveOp.C = 1;
            if (removeOp.C != 0) {
                return;
            }
            list.remove(removePos);
            this.B.jF(removeOp);
            return;
        }
        if (moveOp.E <= removeOp.E) {
            removeOp.E++;
        } else if (moveOp.E < removeOp.E + removeOp.C) {
            diHD = this.B.HD(2, moveOp.E + 1, (removeOp.E + removeOp.C) - moveOp.E, null);
            removeOp.C = moveOp.E - removeOp.E;
        }
        if (z2) {
            list.set(movePos, removeOp);
            list.remove(removePos);
            this.B.jF(moveOp);
            return;
        }
        if (z) {
            if (diHD != null) {
                if (moveOp.E > diHD.E) {
                    moveOp.E -= diHD.C;
                }
                if (moveOp.C > diHD.E) {
                    moveOp.C -= diHD.C;
                }
            }
            if (moveOp.E > removeOp.E) {
                moveOp.E -= removeOp.C;
            }
            if (moveOp.C > removeOp.E) {
                moveOp.C -= removeOp.C;
            }
        } else {
            if (diHD != null) {
                if (moveOp.E >= diHD.E) {
                    moveOp.E -= diHD.C;
                }
                if (moveOp.C >= diHD.E) {
                    moveOp.C -= diHD.C;
                }
            }
            if (moveOp.E >= removeOp.E) {
                moveOp.E -= removeOp.C;
            }
            if (moveOp.C >= removeOp.E) {
                moveOp.C -= removeOp.C;
            }
        }
        list.set(movePos, removeOp);
        if (moveOp.E != moveOp.C) {
            list.set(removePos, moveOp);
        } else {
            list.remove(removePos);
        }
        if (diHD == null) {
            return;
        }
        list.add(movePos, diHD);
    }

    private final void F(List<DI> list, int move, DI moveOp, int update, DI updateOp) {
        DI diHD = null;
        DI diHD2 = null;
        if (moveOp.C < updateOp.E) {
            updateOp.E--;
        } else if (moveOp.C < updateOp.E + updateOp.C) {
            updateOp.C--;
            diHD = this.B.HD(4, moveOp.E, 1, updateOp.D);
        }
        if (moveOp.E <= updateOp.E) {
            updateOp.E++;
        } else if (moveOp.E < updateOp.E + updateOp.C) {
            int i = (updateOp.E + updateOp.C) - moveOp.E;
            diHD2 = this.B.HD(4, moveOp.E + 1, i, updateOp.D);
            updateOp.C -= i;
        }
        list.set(update, moveOp);
        if (updateOp.C > 0) {
            list.set(move, updateOp);
        } else {
            list.remove(move);
            this.B.jF(updateOp);
        }
        if (diHD != null) {
            list.add(move, diHD);
        }
        if (diHD2 != null) {
            list.add(move, diHD2);
        }
    }
}
