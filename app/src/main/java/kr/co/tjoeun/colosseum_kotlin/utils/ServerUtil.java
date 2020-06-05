package kr.co.tjoeun.colosseum_kotlin.utils;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerUtil {

    private static final String BASE_URL = "http://15.165.177.142";
//    private static final String BASE_URL = "http://10.0.2.2:5000";


    public interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }

    public static void postRequestLogin(Context context, String email, String pw, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드는 FormBody에 필요한 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build();

//        API에 접근하기 위한 정보가 적혀있는 Request 변수를 만들자.
//        /user + POST => http://아이피주소/user + POST

        Request request = new Request.Builder()
                .url(BASE_URL + "/user")
                .post(requestBody)
//                .header()  // 헤더가 필요하다면 이 시점에서 첨부.
                .build();


//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("서버연결실패", "로그인 기능 실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body 를 JSONObject 로 변환.

                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public static void postRequestVote(Context context, int sideId, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드는 FormBody에 필요한 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder()
                .add("side_id", sideId+"")
                .build();

//        API에 접근하기 위한 정보가 적혀있는 Request 변수를 만들자.
//        /user + POST => http://아이피주소/user + POST

        Request request = new Request.Builder()
                .url(BASE_URL + "/topic_vote")
                .post(requestBody)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))  // 헤더가 필요하다면 이 시점에서 첨부.
                .build();


//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("서버연결실패", "로그인 기능 실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body 를 JSONObject 로 변환.

                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }


    public static void postRequestReplyLike(Context context, int replyId, boolean isLike, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드는 FormBody에 필요한 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder()
                .add("reply_id", replyId+"")
                .add("is_like", isLike+"")
                .build();

//        API에 접근하기 위한 정보가 적혀있는 Request 변수를 만들자.
//        /user + POST => http://아이피주소/user + POST

        Request request = new Request.Builder()
                .url(BASE_URL + "/topic_reply_like")
                .post(requestBody)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))  // 헤더가 필요하다면 이 시점에서 첨부.
                .build();


//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("서버연결실패", "로그인 기능 실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body 를 JSONObject 로 변환.

                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public static void postRequestReply(Context context, int topicId, String inputContent, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드는 FormBody에 필요한 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder()
                .add("topic_id", topicId+"")
                .add("content", inputContent)
                .build();

//        API에 접근하기 위한 정보가 적혀있는 Request 변수를 만들자.
//        /user + POST => http://아이피주소/user + POST

        Request request = new Request.Builder()
                .url(BASE_URL + "/topic_reply")
                .post(requestBody)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))  // 헤더가 필요하다면 이 시점에서 첨부.
                .build();


//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("서버연결실패", "로그인 기능 실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body 를 JSONObject 로 변환.

                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public static void postRequestReReply(Context context, int replyId, String inputContent, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드는 FormBody에 필요한 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder()
                .add("parent_reply_id", replyId+"")
                .add("content", inputContent)
                .build();

//        API에 접근하기 위한 정보가 적혀있는 Request 변수를 만들자.
//        /user + POST => http://아이피주소/user + POST

        Request request = new Request.Builder()
                .url(BASE_URL + "/topic_reply")
                .post(requestBody)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))  // 헤더가 필요하다면 이 시점에서 첨부.
                .build();


//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("서버연결실패", "로그인 기능 실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body 를 JSONObject 로 변환.

                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }


    public static void putRequestReply(Context context, int replyId, String inputContent, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드는 FormBody에 필요한 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder()
                .add("reply_id", replyId+"")
                .add("content", inputContent)
                .build();

//        API에 접근하기 위한 정보가 적혀있는 Request 변수를 만들자.
//        /user + POST => http://아이피주소/user + POST

        Request request = new Request.Builder()
                .url(BASE_URL + "/topic_reply")
                .put(requestBody)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))  // 헤더가 필요하다면 이 시점에서 첨부.
                .build();


//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("서버연결실패", "로그인 기능 실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body 를 JSONObject 로 변환.

                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }


    public static void getRequestMainInfo(Context context, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        GET - 파라미터들이 모두 주소에 같이 적힌다.
//        요청할때 파라미터를 주소에 모두 적어줘야한다.

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/v2/main_info").newBuilder();
        urlBuilder.addEncodedQueryParameter("device_token", "임시토큰값");
        urlBuilder.addEncodedQueryParameter("os", "Android");

        String completeUrl = urlBuilder.build().toString();
        Log.d("완성된URL", completeUrl);


        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if (handler != null) {
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("body", body);

            }
        });
    }

    public static void deleteRequestReply(Context context, int replyId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        GET - 파라미터들이 모두 주소에 같이 적힌다.
//        요청할때 파라미터를 주소에 모두 적어줘야한다.

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/topic_reply").newBuilder();
        urlBuilder.addEncodedQueryParameter("reply_id", replyId+"");
//        urlBuilder.addEncodedQueryParameter("os", "Android");

        String completeUrl = urlBuilder.build().toString();
        Log.d("완성된URL", completeUrl);


        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .delete()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if (handler != null) {
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("body", body);

            }
        });
    }

    public static void getRequestUserInfo(Context context, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        GET - 파라미터들이 모두 주소에 같이 적힌다.
//        요청할때 파라미터를 주소에 모두 적어줘야한다.

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/user_info").newBuilder();
//        urlBuilder.addEncodedQueryParameter("device_token", "임시토큰값");
//        urlBuilder.addEncodedQueryParameter("os", "Android");

        String completeUrl = urlBuilder.build().toString();
        Log.d("완성된URL", completeUrl);


        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if (handler != null) {
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("body", body);

            }
        });
    }

    public static void getRequestNotifications(Context context, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        GET - 파라미터들이 모두 주소에 같이 적힌다.
//        요청할때 파라미터를 주소에 모두 적어줘야한다.

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/notification").newBuilder();
//        urlBuilder.addEncodedQueryParameter("device_token", "임시토큰값");
//        urlBuilder.addEncodedQueryParameter("os", "Android");

        String completeUrl = urlBuilder.build().toString();
        Log.d("완성된URL", completeUrl);


        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if (handler != null) {
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("body", body);

            }
        });
    }

    public static void getRequestTopicById(Context context, int topicId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        GET - 파라미터들이 모두 주소에 같이 적힌다.
//        요청할때 파라미터를 주소에 모두 적어줘야한다.

//        5번주제? => 15.165.177.142/topic/5
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/topic/" + topicId).newBuilder();
//        urlBuilder.addEncodedQueryParameter("device_token", "임시토큰값");
//        urlBuilder.addEncodedQueryParameter("os", "Android");

        String completeUrl = urlBuilder.build().toString();
        Log.d("완성된URL", completeUrl);


        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if (handler != null) {
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("body", body);

            }
        });
    }

    public static void getRequestTopicReplyById(Context context, int replyId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        GET - 파라미터들이 모두 주소에 같이 적힌다.
//        요청할때 파라미터를 주소에 모두 적어줘야한다.

//        5번주제? => 15.165.177.142/topic/5
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/topic_reply/" + replyId).newBuilder();
//        urlBuilder.addEncodedQueryParameter("device_token", "임시토큰값");
//        urlBuilder.addEncodedQueryParameter("os", "Android");

        String completeUrl = urlBuilder.build().toString();
        Log.d("완성된URL", completeUrl);


        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if (handler != null) {
                        handler.onResponse(json);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("body", body);

            }
        });
    }

}










