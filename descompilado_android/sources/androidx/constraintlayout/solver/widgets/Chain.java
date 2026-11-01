package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.ArrayRow;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        int i3;
        ChainHead[] chainHeadArr;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = i4;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (constraintWidgetContainer.optimizeFor(4)) {
                if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i2, chainHead)) {
                    applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
                }
            } else {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:155:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:29:0x004a A[PHI: r8 r14
  0x004a: PHI (r8v4 boolean) = (r8v2 boolean), (r8v43 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]
  0x004a: PHI (r14v4 boolean) = (r14v2 boolean), (r14v19 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:30:0x004c A[PHI: r8 r14
  0x004c: PHI (r8v40 boolean) = (r8v2 boolean), (r8v43 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]
  0x004c: PHI (r14v16 boolean) = (r14v2 boolean), (r14v19 boolean) binds: [B:28:0x0048, B:17:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:85:0x0158  */
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, int i2, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        int i4;
        ConstraintAnchor constraintAnchor;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        ConstraintAnchor constraintAnchor2;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        float f;
        int size;
        int i5;
        int i6;
        ConstraintWidget constraintWidget = chainHead.mFirst;
        ConstraintWidget constraintWidget2 = chainHead.mLast;
        ConstraintWidget constraintWidget3 = chainHead.mFirstVisibleWidget;
        ConstraintWidget constraintWidget4 = chainHead.mLastVisibleWidget;
        ConstraintWidget constraintWidget5 = chainHead.mHead;
        float f2 = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget6 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget7 = chainHead.mLastMatchConstraintWidget;
        boolean z4 = constraintWidgetContainer.mListDimensionBehaviors[i] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i == 0) {
            z = constraintWidget5.mHorizontalChainStyle == 0;
            z2 = constraintWidget5.mHorizontalChainStyle == 1;
            if (constraintWidget5.mHorizontalChainStyle == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
        } else {
            z = constraintWidget5.mVerticalChainStyle == 0;
            z2 = constraintWidget5.mVerticalChainStyle == 1;
            if (constraintWidget5.mVerticalChainStyle == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
        }
        boolean z5 = z;
        ConstraintWidget constraintWidget8 = constraintWidget;
        boolean z6 = z2;
        boolean z7 = z3;
        boolean z8 = false;
        while (true) {
            ConstraintWidget constraintWidget9 = null;
            if (z8) {
                break;
            }
            ConstraintAnchor constraintAnchor3 = constraintWidget8.mListAnchors[i2];
            int i7 = (z4 || z7) ? 1 : 4;
            int margin = constraintAnchor3.getMargin();
            if (constraintAnchor3.mTarget != null && constraintWidget8 != constraintWidget) {
                margin += constraintAnchor3.mTarget.getMargin();
            }
            int i8 = margin;
            if (!z7 || constraintWidget8 == constraintWidget || constraintWidget8 == constraintWidget3) {
                i5 = (z5 && z4) ? 4 : i7;
            } else {
                i5 = 6;
            }
            if (constraintAnchor3.mTarget != null) {
                if (constraintWidget8 == constraintWidget3) {
                    linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, i8, 5);
                } else {
                    linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, i8, 6);
                }
                linearSystem.addEquality(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, i8, i5);
            } else {
                constraintWidget5 = constraintWidget5;
                z5 = z5;
            }
            if (z4) {
                if (constraintWidget8.getVisibility() == 8 || constraintWidget8.mListDimensionBehaviors[i] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i6 = 0;
                } else {
                    i6 = 0;
                    linearSystem.addGreaterThan(constraintWidget8.mListAnchors[i2 + 1].mSolverVariable, constraintWidget8.mListAnchors[i2].mSolverVariable, 0, 5);
                }
                linearSystem.addGreaterThan(constraintWidget8.mListAnchors[i2].mSolverVariable, constraintWidgetContainer.mListAnchors[i2].mSolverVariable, i6, 6);
            }
            ConstraintAnchor constraintAnchor4 = constraintWidget8.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor4 != null) {
                ConstraintWidget constraintWidget10 = constraintAnchor4.mOwner;
                if (constraintWidget10.mListAnchors[i2].mTarget != null && constraintWidget10.mListAnchors[i2].mTarget.mOwner == constraintWidget8) {
                    constraintWidget9 = constraintWidget10;
                }
            }
            if (constraintWidget9 != null) {
                constraintWidget8 = constraintWidget9;
                z8 = z8;
            } else {
                z8 = true;
            }
            f2 = f2;
            z5 = z5;
            constraintWidget5 = constraintWidget5;
        }
        ConstraintWidget constraintWidget11 = constraintWidget5;
        float f3 = f2;
        boolean z9 = z5;
        if (constraintWidget4 != null) {
            int i9 = i2 + 1;
            if (constraintWidget2.mListAnchors[i9].mTarget != null) {
                ConstraintAnchor constraintAnchor5 = constraintWidget4.mListAnchors[i9];
                linearSystem.addLowerThan(constraintAnchor5.mSolverVariable, constraintWidget2.mListAnchors[i9].mTarget.mSolverVariable, -constraintAnchor5.getMargin(), 5);
            }
        }
        if (z4) {
            int i10 = i2 + 1;
            linearSystem.addGreaterThan(constraintWidgetContainer.mListAnchors[i10].mSolverVariable, constraintWidget2.mListAnchors[i10].mSolverVariable, constraintWidget2.mListAnchors[i10].getMargin(), 6);
        }
        ArrayList<ConstraintWidget> arrayList = chainHead.mWeightedMatchConstraintsWidgets;
        if (arrayList != null && (size = arrayList.size()) > 1) {
            float f4 = (!chainHead.mHasUndefinedWeights || chainHead.mHasComplexMatchWeights) ? f3 : chainHead.mWidgetsMatchCount;
            float f5 = 0.0f;
            ConstraintWidget constraintWidget12 = null;
            int i11 = 0;
            float f6 = 0.0f;
            while (i11 < size) {
                ConstraintWidget constraintWidget13 = arrayList.get(i11);
                float f7 = constraintWidget13.mWeight[i];
                if (f7 < f5) {
                    if (chainHead.mHasComplexMatchWeights) {
                        linearSystem.addEquality(constraintWidget13.mListAnchors[i2 + 1].mSolverVariable, constraintWidget13.mListAnchors[i2].mSolverVariable, 0, 4);
                    } else {
                        f7 = 1.0f;
                    }
                    arrayList = arrayList;
                    size = size;
                    i11++;
                    size = size;
                    arrayList = arrayList;
                    f5 = 0.0f;
                }
                if (f7 == 0.0f) {
                    linearSystem.addEquality(constraintWidget13.mListAnchors[i2 + 1].mSolverVariable, constraintWidget13.mListAnchors[i2].mSolverVariable, 0, 6);
                    arrayList = arrayList;
                    size = size;
                } else {
                    if (constraintWidget12 != null) {
                        SolverVariable solverVariable5 = constraintWidget12.mListAnchors[i2].mSolverVariable;
                        int i12 = i2 + 1;
                        SolverVariable solverVariable6 = constraintWidget12.mListAnchors[i12].mSolverVariable;
                        SolverVariable solverVariable7 = constraintWidget13.mListAnchors[i2].mSolverVariable;
                        SolverVariable solverVariable8 = constraintWidget13.mListAnchors[i12].mSolverVariable;
                        ArrayRow arrayRowCreateRow = linearSystem.createRow();
                        arrayRowCreateRow.createRowEqualMatchDimensions(f6, f4, f7, solverVariable5, solverVariable6, solverVariable7, solverVariable8);
                        linearSystem.addConstraint(arrayRowCreateRow);
                    }
                    f6 = f7;
                    constraintWidget12 = constraintWidget13;
                }
                i11++;
                size = size;
                arrayList = arrayList;
                f5 = 0.0f;
            }
        }
        if (constraintWidget3 != null && (constraintWidget3 == constraintWidget4 || z7)) {
            ConstraintAnchor constraintAnchor6 = constraintWidget.mListAnchors[i2];
            int i13 = i2 + 1;
            ConstraintAnchor constraintAnchor7 = constraintWidget2.mListAnchors[i13];
            SolverVariable solverVariable9 = constraintWidget.mListAnchors[i2].mTarget != null ? constraintWidget.mListAnchors[i2].mTarget.mSolverVariable : null;
            SolverVariable solverVariable10 = constraintWidget2.mListAnchors[i13].mTarget != null ? constraintWidget2.mListAnchors[i13].mTarget.mSolverVariable : null;
            if (constraintWidget3 == constraintWidget4) {
                constraintAnchor6 = constraintWidget3.mListAnchors[i2];
                constraintAnchor7 = constraintWidget3.mListAnchors[i13];
            }
            if (solverVariable9 != null && solverVariable10 != null) {
                if (i == 0) {
                    f = constraintWidget11.mHorizontalBiasPercent;
                } else {
                    f = constraintWidget11.mVerticalBiasPercent;
                }
                linearSystem.addCentering(constraintAnchor6.mSolverVariable, solverVariable9, constraintAnchor6.getMargin(), f, solverVariable10, constraintAnchor7.mSolverVariable, constraintAnchor7.getMargin(), 5);
            }
        } else if (!z9 || constraintWidget3 == null) {
            int i14 = 8;
            if (z6 && constraintWidget3 != null) {
                boolean z10 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                ConstraintWidget constraintWidget14 = constraintWidget3;
                ConstraintWidget constraintWidget15 = constraintWidget14;
                while (constraintWidget14 != null) {
                    ConstraintWidget constraintWidget16 = constraintWidget14.mNextChainWidget[i];
                    while (constraintWidget16 != null && constraintWidget16.getVisibility() == i14) {
                        constraintWidget16 = constraintWidget16.mNextChainWidget[i];
                    }
                    if (constraintWidget14 == constraintWidget3 || constraintWidget14 == constraintWidget4 || constraintWidget16 == null) {
                        constraintWidget15 = constraintWidget15;
                        i4 = 8;
                    } else {
                        ConstraintWidget constraintWidget17 = constraintWidget16 == constraintWidget4 ? null : constraintWidget16;
                        ConstraintAnchor constraintAnchor8 = constraintWidget14.mListAnchors[i2];
                        SolverVariable solverVariable11 = constraintAnchor8.mSolverVariable;
                        if (constraintAnchor8.mTarget != null) {
                            SolverVariable solverVariable12 = constraintAnchor8.mTarget.mSolverVariable;
                        }
                        int i15 = i2 + 1;
                        SolverVariable solverVariable13 = constraintWidget15.mListAnchors[i15].mSolverVariable;
                        int margin2 = constraintAnchor8.getMargin();
                        int margin3 = constraintWidget14.mListAnchors[i15].getMargin();
                        if (constraintWidget17 != null) {
                            constraintAnchor = constraintWidget17.mListAnchors[i2];
                            solverVariable = constraintAnchor.mSolverVariable;
                            solverVariable2 = constraintAnchor.mTarget != null ? constraintAnchor.mTarget.mSolverVariable : null;
                        } else {
                            constraintAnchor = constraintWidget14.mListAnchors[i15].mTarget;
                            solverVariable = constraintAnchor != null ? constraintAnchor.mSolverVariable : null;
                            solverVariable2 = constraintWidget14.mListAnchors[i15].mSolverVariable;
                        }
                        if (constraintAnchor != null) {
                            margin3 += constraintAnchor.getMargin();
                        }
                        int i16 = margin3;
                        if (constraintWidget15 != null) {
                            margin2 += constraintWidget15.mListAnchors[i15].getMargin();
                        }
                        int i17 = margin2;
                        int i18 = z10 ? 6 : 4;
                        if (solverVariable11 == null || solverVariable13 == null || solverVariable == null || solverVariable2 == null) {
                            i4 = 8;
                        } else {
                            i4 = 8;
                            linearSystem.addCentering(solverVariable11, solverVariable13, i17, 0.5f, solverVariable, solverVariable2, i16, i18);
                        }
                        constraintWidget16 = constraintWidget17;
                    }
                    if (constraintWidget14.getVisibility() == i4) {
                        constraintWidget14 = constraintWidget15;
                    }
                    constraintWidget15 = constraintWidget14;
                    i14 = 8;
                    constraintWidget14 = constraintWidget16;
                }
                ConstraintAnchor constraintAnchor9 = constraintWidget3.mListAnchors[i2];
                ConstraintAnchor constraintAnchor10 = constraintWidget.mListAnchors[i2].mTarget;
                int i19 = i2 + 1;
                ConstraintAnchor constraintAnchor11 = constraintWidget4.mListAnchors[i19];
                ConstraintAnchor constraintAnchor12 = constraintWidget2.mListAnchors[i19].mTarget;
                if (constraintAnchor10 == null) {
                    i3 = 5;
                } else if (constraintWidget3 != constraintWidget4) {
                    i3 = 5;
                    linearSystem.addEquality(constraintAnchor9.mSolverVariable, constraintAnchor10.mSolverVariable, constraintAnchor9.getMargin(), 5);
                } else {
                    i3 = 5;
                    if (constraintAnchor12 != null) {
                        linearSystem.addCentering(constraintAnchor9.mSolverVariable, constraintAnchor10.mSolverVariable, constraintAnchor9.getMargin(), 0.5f, constraintAnchor11.mSolverVariable, constraintAnchor12.mSolverVariable, constraintAnchor11.getMargin(), 5);
                    }
                }
                if (constraintAnchor12 != null && constraintWidget3 != constraintWidget4) {
                    linearSystem.addEquality(constraintAnchor11.mSolverVariable, constraintAnchor12.mSolverVariable, -constraintAnchor11.getMargin(), i3);
                }
            }
        } else {
            boolean z11 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
            ConstraintWidget constraintWidget18 = constraintWidget3;
            ConstraintWidget constraintWidget19 = constraintWidget18;
            while (constraintWidget18 != null) {
                ConstraintWidget constraintWidget20 = constraintWidget18.mNextChainWidget[i];
                while (constraintWidget20 != null && constraintWidget20.getVisibility() == 8) {
                    constraintWidget20 = constraintWidget20.mNextChainWidget[i];
                }
                if (constraintWidget20 != null || constraintWidget18 == constraintWidget4) {
                    ConstraintAnchor constraintAnchor13 = constraintWidget18.mListAnchors[i2];
                    SolverVariable solverVariable14 = constraintAnchor13.mSolverVariable;
                    SolverVariable solverVariable15 = constraintAnchor13.mTarget != null ? constraintAnchor13.mTarget.mSolverVariable : null;
                    if (constraintWidget19 != constraintWidget18) {
                        solverVariable15 = constraintWidget19.mListAnchors[i2 + 1].mSolverVariable;
                    } else if (constraintWidget18 == constraintWidget3 && constraintWidget19 == constraintWidget18) {
                        solverVariable15 = constraintWidget.mListAnchors[i2].mTarget != null ? constraintWidget.mListAnchors[i2].mTarget.mSolverVariable : null;
                    }
                    int margin4 = constraintAnchor13.getMargin();
                    int i20 = i2 + 1;
                    int margin5 = constraintWidget18.mListAnchors[i20].getMargin();
                    if (constraintWidget20 != null) {
                        constraintAnchor2 = constraintWidget20.mListAnchors[i2];
                        solverVariable3 = constraintAnchor2.mSolverVariable;
                        solverVariable4 = constraintWidget18.mListAnchors[i20].mSolverVariable;
                    } else {
                        constraintAnchor2 = constraintWidget2.mListAnchors[i20].mTarget;
                        solverVariable3 = constraintAnchor2 != null ? constraintAnchor2.mSolverVariable : null;
                        solverVariable4 = constraintWidget18.mListAnchors[i20].mSolverVariable;
                    }
                    if (constraintAnchor2 != null) {
                        margin5 += constraintAnchor2.getMargin();
                    }
                    if (constraintWidget19 != null) {
                        margin4 += constraintWidget19.mListAnchors[i20].getMargin();
                    }
                    if (solverVariable14 != null && solverVariable15 != null && solverVariable3 != null && solverVariable4 != null) {
                        if (constraintWidget18 == constraintWidget3) {
                            margin4 = constraintWidget3.mListAnchors[i2].getMargin();
                        }
                        linearSystem.addCentering(solverVariable14, solverVariable15, margin4, 0.5f, solverVariable3, solverVariable4, constraintWidget18 == constraintWidget4 ? constraintWidget4.mListAnchors[i20].getMargin() : margin5, z11 ? 6 : 4);
                    }
                }
                if (constraintWidget18.getVisibility() != 8) {
                    constraintWidget19 = constraintWidget18;
                }
                constraintWidget18 = constraintWidget20;
            }
        }
        if ((z9 || z6) && constraintWidget3 != null) {
            ConstraintAnchor constraintAnchor14 = constraintWidget3.mListAnchors[i2];
            int i21 = i2 + 1;
            ConstraintAnchor constraintAnchor15 = constraintWidget4.mListAnchors[i21];
            SolverVariable solverVariable16 = constraintAnchor14.mTarget != null ? constraintAnchor14.mTarget.mSolverVariable : null;
            SolverVariable solverVariable17 = constraintAnchor15.mTarget != null ? constraintAnchor15.mTarget.mSolverVariable : null;
            if (constraintWidget2 != constraintWidget4) {
                ConstraintAnchor constraintAnchor16 = constraintWidget2.mListAnchors[i21];
                solverVariable17 = constraintAnchor16.mTarget != null ? constraintAnchor16.mTarget.mSolverVariable : null;
            }
            SolverVariable solverVariable18 = solverVariable17;
            if (constraintWidget3 == constraintWidget4) {
                constraintAnchor14 = constraintWidget3.mListAnchors[i2];
                constraintAnchor15 = constraintWidget3.mListAnchors[i21];
            }
            if (solverVariable16 == null || solverVariable18 == null) {
                return;
            }
            int margin6 = constraintAnchor14.getMargin();
            if (constraintWidget4 != null) {
                constraintWidget2 = constraintWidget4;
            }
            linearSystem.addCentering(constraintAnchor14.mSolverVariable, solverVariable16, margin6, 0.5f, solverVariable18, constraintAnchor15.mSolverVariable, constraintWidget2.mListAnchors[i21].getMargin(), 5);
        }
    }
}
