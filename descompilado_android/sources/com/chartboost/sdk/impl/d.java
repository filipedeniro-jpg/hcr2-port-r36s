package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.c.RunnableC0032c;
import com.chartboost.sdk.impl.e.a;

/* JADX INFO: loaded from: classes.dex */
class d implements com.chartboost.sdk.Model.d {
    private final e a;
    private final f b;

    d(e eVar, f fVar) {
        this.a = eVar;
        this.b = fVar;
    }

    @Override // com.chartboost.sdk.Model.d
    public void a(com.chartboost.sdk.Model.c cVar) {
        cVar.l = 1;
        if (this.a.f.f(this.b.b)) {
            this.a.e.a(cVar);
            if (this.a.f.a != 0 || cVar.n == 1 || cVar.p.b == 1) {
                return;
            }
            c(cVar);
            return;
        }
        e eVar = this.a;
        eVar.getClass();
        this.a.a.execute(eVar.new a(7, this.b.b, this.b, null));
    }

    @Override // com.chartboost.sdk.Model.d
    public void b(com.chartboost.sdk.Model.c cVar) {
        e eVar = this.a;
        eVar.getClass();
        this.a.a.execute(eVar.new a(7, this.b.b, this.b, null));
    }

    @Override // com.chartboost.sdk.Model.d
    public void c(com.chartboost.sdk.Model.c cVar) {
        cVar.r = true;
        e eVar = this.a;
        eVar.getClass();
        this.a.a.execute(eVar.new a(5, this.b.b, this.b, null));
    }

    @Override // com.chartboost.sdk.Model.d
    public void a(com.chartboost.sdk.Model.c cVar, CBError.CBImpressionError cBImpressionError) {
        com.chartboost.sdk.c cVar2 = this.a.e;
        cVar2.getClass();
        com.chartboost.sdk.c.RunnableC0032c runnableC0032c = cVar2.new RunnableC0032c(11);
        runnableC0032c.d = cVar;
        com.chartboost.sdk.h.b(runnableC0032c);
        e eVar = this.a;
        eVar.getClass();
        this.a.a.execute(eVar.new a(6, this.b.b, this.b, cBImpressionError));
    }
}
