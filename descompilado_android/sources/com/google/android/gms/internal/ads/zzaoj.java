package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "LargeParcelTeleporterCreator")
@SafeParcelable.Reserved({1})
public final class zzaoj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaoj> CREATOR = new zzaol();

    @SafeParcelable.Field(id = 2)
    private ParcelFileDescriptor zzdll;
    private Parcelable zzdlm = null;
    private boolean zzdln = true;

    @SafeParcelable.Constructor
    public zzaoj(@SafeParcelable.Param(id = 2) ParcelFileDescriptor parcelFileDescriptor) {
        this.zzdll = parcelFileDescriptor;
    }

    public final <T extends SafeParcelable> T zza(Parcelable.Creator<T> creator) {
        if (this.zzdln) {
            ParcelFileDescriptor parcelFileDescriptor = this.zzdll;
            if (parcelFileDescriptor == null) {
                zzatm.zzes("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor));
            try {
                try {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(bArr, 0, bArr.length);
                    IOUtils.closeQuietly(dataInputStream);
                    Parcel parcelObtain = Parcel.obtain();
                    try {
                        parcelObtain.unmarshall(bArr, 0, bArr.length);
                        parcelObtain.setDataPosition(0);
                        this.zzdlm = creator.createFromParcel(parcelObtain);
                        parcelObtain.recycle();
                        this.zzdln = false;
                    } catch (Throwable th) {
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (IOException e) {
                    zzatm.zzc("Could not read from parcel file descriptor", e);
                    IOUtils.closeQuietly(dataInputStream);
                    return null;
                }
            } catch (Throwable th2) {
                IOUtils.closeQuietly(dataInputStream);
                throw th2;
            }
        }
        return (T) this.zzdlm;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        zzsx();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdll, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private final ParcelFileDescriptor zzsx() {
        if (this.zzdll == null) {
            Parcel parcelObtain = Parcel.obtain();
            try {
                this.zzdlm.writeToParcel(parcelObtain, 0);
                byte[] bArrMarshall = parcelObtain.marshall();
                parcelObtain.recycle();
                this.zzdll = zzh(bArrMarshall);
            } catch (Throwable th) {
                parcelObtain.recycle();
                throw th;
            }
        }
        return this.zzdll;
    }

    private static <T> ParcelFileDescriptor zzh(final byte[] bArr) {
        final ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptorArrCreatePipe[1]);
            try {
                zzawx.zzdvx.execute(new Runnable(autoCloseOutputStream, bArr) { // from class: com.google.android.gms.internal.ads.zzaoi
                    private final OutputStream zzdlj;
                    private final byte[] zzdlk;

                    {
                        this.zzdlj = autoCloseOutputStream;
                        this.zzdlk = bArr;
                    }

                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        zzaoj.zza(this.zzdlj, this.zzdlk);
                    }
                });
                return parcelFileDescriptorArrCreatePipe[0];
            } catch (IOException e) {
                e = e;
                zzatm.zzc("Error transporting the ad response", e);
                com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "LargeParcelTeleporter.pipeData.2");
                IOUtils.closeQuietly(autoCloseOutputStream);
                return null;
            }
        } catch (IOException e2) {
            e = e2;
            autoCloseOutputStream = null;
        }
    }

    static final /* synthetic */ void zza(OutputStream outputStream, byte[] bArr) throws Throwable {
        DataOutputStream dataOutputStream = null;
        try {
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(outputStream);
                try {
                    dataOutputStream2.writeInt(bArr.length);
                    dataOutputStream2.write(bArr);
                    IOUtils.closeQuietly(dataOutputStream2);
                } catch (IOException e) {
                    e = e;
                    dataOutputStream = dataOutputStream2;
                    zzatm.zzc("Error transporting the ad response", e);
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "LargeParcelTeleporter.pipeData.1");
                    if (dataOutputStream == null) {
                        IOUtils.closeQuietly(outputStream);
                    } else {
                        IOUtils.closeQuietly(dataOutputStream);
                    }
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream == null) {
                        IOUtils.closeQuietly(outputStream);
                    } else {
                        IOUtils.closeQuietly(dataOutputStream);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
