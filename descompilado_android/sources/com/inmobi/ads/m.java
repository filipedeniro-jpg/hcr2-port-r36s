package com.inmobi.ads;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* JADX INFO: compiled from: AnimationController.java */
/* JADX INFO: loaded from: classes2.dex */
final class m {

    /* JADX INFO: compiled from: AnimationController.java */
    static class a extends Animation {
        private final float c;
        private final float d;
        private Camera g;
        private final float a = 0.0f;
        private final float b = 90.0f;
        private final float e = 0.0f;
        private final boolean f = true;

        public a(float f, float f2) {
            this.c = f;
            this.d = f2;
        }

        @Override // android.view.animation.Animation
        public final void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        @Override // android.view.animation.Animation
        protected final void applyTransformation(float f, Transformation transformation) {
            float f2 = this.a;
            float f3 = f2 + ((this.b - f2) * f);
            float f4 = this.c;
            float f5 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(0.0f, 0.0f, this.e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.e * (1.0f - f));
            }
            camera.rotateX(f3);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f4, -f5);
            matrix.postTranslate(f4, f5);
        }
    }

    /* JADX INFO: compiled from: AnimationController.java */
    static class b extends Animation {
        private final float c;
        private final float d;
        private Camera g;
        private final float a = 0.0f;
        private final float b = 90.0f;
        private final float e = 0.0f;
        private final boolean f = true;

        public b(float f, float f2) {
            this.c = f;
            this.d = f2;
        }

        @Override // android.view.animation.Animation
        public final void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        @Override // android.view.animation.Animation
        protected final void applyTransformation(float f, Transformation transformation) {
            float f2 = this.a;
            float f3 = f2 + ((this.b - f2) * f);
            float f4 = this.c;
            float f5 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(0.0f, 0.0f, this.e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.e * (1.0f - f));
            }
            camera.rotateY(f3);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f4, -f5);
            matrix.postTranslate(f4, f5);
        }
    }
}
