package net.hockeyapp.android.utils;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.hockeyapp.android.objects.Feedback;
import net.hockeyapp.android.objects.FeedbackAttachment;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.objects.FeedbackResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class FeedbackParser {
    private FeedbackParser() {
    }

    private static class FeedbackParserHolder {
        public static final FeedbackParser INSTANCE = new FeedbackParser();

        private FeedbackParserHolder() {
        }
    }

    public static FeedbackParser getInstance() {
        return FeedbackParserHolder.INSTANCE;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    public FeedbackResponse parseFeedbackResponse(String str) {
        FeedbackResponse feedbackResponse;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        String str2;
        String str3;
        String str4;
        String str5;
        Feedback feedback;
        ArrayList<FeedbackMessage> arrayList;
        String str6;
        String str7;
        String str8;
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject3 = new JSONObject(str);
            JSONObject jSONObject4 = jSONObject3.getJSONObject("feedback");
            Feedback feedback2 = new Feedback();
            JSONArray jSONArray = jSONObject4.getJSONArray("messages");
            String str9 = "token";
            String str10 = "name";
            String str11 = "created_at";
            String str12 = "id";
            if (jSONArray.length() > 0) {
                ArrayList<FeedbackMessage> arrayList2 = new ArrayList<>();
                int i = 0;
                while (i < jSONArray.length()) {
                    String string = jSONArray.getJSONObject(i).getString("subject").toString();
                    String string2 = jSONArray.getJSONObject(i).getString(MimeTypes.BASE_TYPE_TEXT).toString();
                    String string3 = jSONArray.getJSONObject(i).getString("oem").toString();
                    String string4 = jSONArray.getJSONObject(i).getString("model").toString();
                    String string5 = jSONArray.getJSONObject(i).getString("os_version").toString();
                    String string6 = jSONArray.getJSONObject(i).getString(str11).toString();
                    JSONObject jSONObject5 = jSONObject3;
                    int i2 = jSONArray.getJSONObject(i).getInt(str12);
                    JSONObject jSONObject6 = jSONObject4;
                    String string7 = jSONArray.getJSONObject(i).getString(str9).toString();
                    String str13 = str9;
                    Feedback feedback3 = feedback2;
                    int i3 = jSONArray.getJSONObject(i).getInt("via");
                    ArrayList<FeedbackMessage> arrayList3 = arrayList2;
                    String string8 = jSONArray.getJSONObject(i).getString("user_string").toString();
                    String string9 = jSONArray.getJSONObject(i).getString("clean_text").toString();
                    String string10 = jSONArray.getJSONObject(i).getString(str10).toString();
                    String str14 = str10;
                    String string11 = jSONArray.getJSONObject(i).getString("app_id").toString();
                    JSONArray jSONArray2 = jSONArray;
                    JSONArray jSONArrayOptJSONArray = jSONArray.getJSONObject(i).optJSONArray("attachments");
                    List<FeedbackAttachment> listEmptyList = Collections.emptyList();
                    if (jSONArrayOptJSONArray != null) {
                        listEmptyList = new ArrayList<>();
                        int i4 = 0;
                        while (i4 < jSONArrayOptJSONArray.length()) {
                            int i5 = jSONArrayOptJSONArray.getJSONObject(i4).getInt(str12);
                            String str15 = str12;
                            String str16 = string2;
                            int i6 = jSONArrayOptJSONArray.getJSONObject(i4).getInt("feedback_message_id");
                            String str17 = string;
                            String string12 = jSONArrayOptJSONArray.getJSONObject(i4).getString("file_name");
                            String str18 = string5;
                            String string13 = jSONArrayOptJSONArray.getJSONObject(i4).getString("url");
                            String string14 = jSONArrayOptJSONArray.getJSONObject(i4).getString(str11);
                            String str19 = str11;
                            JSONArray jSONArray3 = jSONArrayOptJSONArray;
                            String string15 = jSONArrayOptJSONArray.getJSONObject(i4).getString("updated_at");
                            FeedbackAttachment feedbackAttachment = new FeedbackAttachment();
                            feedbackAttachment.setId(i5);
                            feedbackAttachment.setMessageId(i6);
                            feedbackAttachment.setFilename(string12);
                            feedbackAttachment.setUrl(string13);
                            feedbackAttachment.setCreatedAt(string14);
                            feedbackAttachment.setUpdatedAt(string15);
                            listEmptyList.add(feedbackAttachment);
                            i4++;
                            str12 = str15;
                            string2 = str16;
                            string = str17;
                            string5 = str18;
                            str11 = str19;
                            jSONArrayOptJSONArray = jSONArray3;
                        }
                        str6 = str11;
                        str7 = str12;
                        str8 = string5;
                    } else {
                        str6 = str11;
                        str7 = str12;
                        str8 = string5;
                    }
                    FeedbackMessage feedbackMessage = new FeedbackMessage();
                    feedbackMessage.setAppId(string11);
                    feedbackMessage.setCleanText(string9);
                    feedbackMessage.setCreatedAt(string6);
                    feedbackMessage.setId(i2);
                    feedbackMessage.setModel(string4);
                    feedbackMessage.setName(string10);
                    feedbackMessage.setOem(string3);
                    feedbackMessage.setOsVersion(str8);
                    feedbackMessage.setSubjec(string);
                    feedbackMessage.setText(string2);
                    feedbackMessage.setToken(string7);
                    feedbackMessage.setUserString(string8);
                    feedbackMessage.setVia(i3);
                    feedbackMessage.setFeedbackAttachments(listEmptyList);
                    arrayList3.add(feedbackMessage);
                    i++;
                    arrayList2 = arrayList3;
                    jSONObject3 = jSONObject5;
                    jSONObject4 = jSONObject6;
                    str9 = str13;
                    feedback2 = feedback3;
                    str10 = str14;
                    jSONArray = jSONArray2;
                    str12 = str7;
                    str11 = str6;
                }
                jSONObject = jSONObject3;
                jSONObject2 = jSONObject4;
                str2 = str9;
                str3 = str10;
                str4 = str11;
                str5 = str12;
                arrayList = arrayList2;
                feedback = feedback2;
            } else {
                jSONObject = jSONObject3;
                jSONObject2 = jSONObject4;
                str2 = "token";
                str3 = "name";
                str4 = "created_at";
                str5 = "id";
                feedback = feedback2;
                arrayList = null;
            }
            feedback.setMessages(arrayList);
            JSONObject jSONObject7 = jSONObject2;
            try {
                feedback.setName(jSONObject7.getString(str3).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                feedback.setEmail(jSONObject7.getString("email").toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            try {
                feedback.setId(jSONObject7.getInt(str5));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            try {
                feedback.setCreatedAt(jSONObject7.getString(str4).toString());
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
            FeedbackResponse feedbackResponse2 = new FeedbackResponse();
            try {
                feedbackResponse2.setFeedback(feedback);
                JSONObject jSONObject8 = jSONObject;
                try {
                    feedbackResponse2.setStatus(jSONObject8.getString("status").toString());
                } catch (JSONException e5) {
                    e5.printStackTrace();
                }
                try {
                    feedbackResponse2.setToken(jSONObject8.getString(str2).toString());
                } catch (JSONException e6) {
                    e6.printStackTrace();
                }
                return feedbackResponse2;
            } catch (JSONException e7) {
                e = e7;
                feedbackResponse = feedbackResponse2;
                e.printStackTrace();
                return feedbackResponse;
            }
        } catch (JSONException e8) {
            e = e8;
            feedbackResponse = null;
        }
    }
}
