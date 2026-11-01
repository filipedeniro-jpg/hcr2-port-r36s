package com.vungle.warren.network;

import com.google.gson.JsonObject;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/* JADX INFO: loaded from: classes2.dex */
public interface VungleApi {
    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    @POST("{ads}")
    Call<JsonObject> ads(@Header("User-Agent") String str, @Path(encoded = true, value = "ads") String str2, @Body JsonObject jsonObject);

    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    @POST("config")
    Call<JsonObject> config(@Header("User-Agent") String str, @Body JsonObject jsonObject);

    @GET
    Call<ResponseBody> pingTPAT(@Header("User-Agent") String str, @Url String str2);

    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    @POST("{report_ad}")
    Call<JsonObject> reportAd(@Header("User-Agent") String str, @Path(encoded = true, value = "report_ad") String str2, @Body JsonObject jsonObject);

    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    @GET("{new}")
    Call<JsonObject> reportNew(@Header("User-Agent") String str, @Path(encoded = true, value = AppSettingsData.STATUS_NEW) String str2, @QueryMap Map<String, String> map);

    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    @POST("{ri}")
    Call<JsonObject> ri(@Header("User-Agent") String str, @Path(encoded = true, value = "ri") String str2, @Body JsonObject jsonObject);

    @Headers({"Content-Type: application/json", "Vungle-Version: 5.2.0"})
    @POST("{will_play_ad}")
    Call<JsonObject> willPlayAd(@Header("User-Agent") String str, @Path(encoded = true, value = "will_play_ad") String str2, @Body JsonObject jsonObject);
}
