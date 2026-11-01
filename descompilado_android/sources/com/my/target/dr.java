package com.my.target;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: InterstitialAdResultProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dr extends e<com.my.target.core.models.sections.c> {
    @Override // com.my.target.e
    @Nullable
    public final /* synthetic */ al a(@NonNull al alVar, @NonNull b bVar, @NonNull Context context) {
        com.my.target.core.models.sections.c cVar = (com.my.target.core.models.sections.c) alVar;
        com.my.target.core.models.banners.e eVarM = cVar.m();
        if (eVarM != null) {
            boolean zA = true;
            if (eVarM instanceof com.my.target.core.models.banners.i) {
                com.my.target.core.models.banners.i iVar = (com.my.target.core.models.banners.i) eVarM;
                ArrayList arrayList = new ArrayList();
                ak<VideoData> videoBanner = iVar.getVideoBanner();
                if (videoBanner != null) {
                    if (videoBanner.getPreview() != null) {
                        arrayList.add(videoBanner.getPreview());
                    }
                    VideoData videoData = (VideoData) videoBanner.getMediaData();
                    if (videoData != null && videoData.isCacheable()) {
                        videoData.setData(ay.ao().f(videoData.getUrl(), context));
                    }
                }
                if (iVar.getImage() != null) {
                    arrayList.add(iVar.getImage());
                }
                if (iVar.getIcon() != null) {
                    arrayList.add(iVar.getIcon());
                }
                if (iVar.getCloseIcon() != null) {
                    arrayList.add(iVar.getCloseIcon());
                }
                if (iVar.getPlayIcon() != null) {
                    arrayList.add(iVar.getPlayIcon());
                }
                if (iVar.getStoreIcon() != null) {
                    arrayList.add(iVar.getStoreIcon());
                }
                List<com.my.target.core.models.banners.f> interstitialAdCards = iVar.getInterstitialAdCards();
                if (!interstitialAdCards.isEmpty()) {
                    Iterator<com.my.target.core.models.banners.f> it = interstitialAdCards.iterator();
                    while (it.hasNext()) {
                        ImageData image = it.next().getImage();
                        if (image != null) {
                            arrayList.add(image);
                        }
                    }
                }
                com.my.target.core.models.banners.e endCard = iVar.getEndCard();
                if (endCard != null && (endCard instanceof com.my.target.core.models.banners.h) && !a((com.my.target.core.models.banners.h) endCard, context)) {
                    iVar.setEndCard(null);
                }
                if (arrayList.size() > 0) {
                    cf.b(arrayList).v(context);
                }
            } else if (eVarM instanceof com.my.target.core.models.banners.h) {
                zA = a((com.my.target.core.models.banners.h) eVarM, context);
            } else if (eVarM instanceof com.my.target.core.models.banners.g) {
                com.my.target.core.models.banners.g gVar = (com.my.target.core.models.banners.g) eVarM;
                if (gVar.getCloseIcon() != null) {
                    cf.a(gVar.getCloseIcon()).v(context);
                }
            } else {
                zA = false;
            }
            if (zA) {
                return cVar;
            }
        }
        return null;
    }

    @NonNull
    public static dr e() {
        return new dr();
    }

    private dr() {
    }

    private static boolean a(@NonNull com.my.target.core.models.banners.h hVar, @NonNull Context context) {
        ImageData closeIcon;
        ArrayList arrayList = new ArrayList();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        Point point = new Point();
        if (defaultDisplay != null) {
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else {
                defaultDisplay.getSize(point);
            }
        }
        int i = point.x;
        int i2 = point.y;
        ImageData imageDataA = a(hVar.getPortraitImages(), Math.min(i, i2), Math.max(i, i2));
        if (imageDataA != null) {
            arrayList.add(imageDataA);
            hVar.setOptimalPortraitImage(imageDataA);
        }
        ImageData imageDataA2 = a(hVar.getLandscapeImages(), Math.max(i, i2), Math.min(i, i2));
        if (imageDataA2 != null) {
            arrayList.add(imageDataA2);
            hVar.setOptimalLandscapeImage(imageDataA2);
        }
        if ((imageDataA != null || imageDataA2 != null) && (closeIcon = hVar.getCloseIcon()) != null) {
            arrayList.add(closeIcon);
        }
        if (arrayList.size() <= 0) {
            return false;
        }
        cf.b(arrayList).v(context);
        if (imageDataA == null || imageDataA.getBitmap() == null) {
            return (imageDataA2 == null || imageDataA2.getBitmap() == null) ? false : true;
        }
        return true;
    }

    @Nullable
    private static ImageData a(@NonNull List<ImageData> list, int i, int i2) {
        float height;
        float f;
        ImageData imageData = null;
        if (list.size() == 0) {
            return null;
        }
        if (i2 == 0 || i == 0) {
            g.a("[InterstitialAdResultProcessor] display size is zero");
            return null;
        }
        float f2 = i;
        float f3 = i2;
        float f4 = f2 / f3;
        float f5 = 0.0f;
        for (ImageData imageData2 : list) {
            if (imageData2.getWidth() > 0 && imageData2.getHeight() > 0) {
                float width = imageData2.getWidth() / imageData2.getHeight();
                if (f4 < width) {
                    float width2 = imageData2.getWidth();
                    if (width2 > f2) {
                        width2 = f2;
                    }
                    float f6 = width2;
                    height = width2 / width;
                    f = f6;
                } else {
                    height = imageData2.getHeight();
                    if (height > f3) {
                        height = f3;
                    }
                    f = width * height;
                }
                float f7 = f * height;
                if (f7 <= f5) {
                    break;
                }
                imageData = imageData2;
                f5 = f7;
            }
        }
        return imageData;
    }
}
