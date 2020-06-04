package kr.co.tjoeun.colosseum_kotlin.datas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class TopicReply {

    private int id;
    private int side_id;
    private String content;
    private User writer;
    private Calendar createdAt = Calendar.getInstance(); // 작성 일시 기록
    private int likeCount;
    private int dislikeCount;
    private boolean isMyLike;
    private boolean isMyDislike;
    private int replyCount;

//    대댓글 목록 저장
    private List<TopicReply> replyList = new ArrayList<>();


    public static TopicReply getTopicReplyFromJson(JSONObject jsonObject) {
        TopicReply tr = new TopicReply();

        try {
            tr.id = jsonObject.getInt("id");
            tr.side_id = jsonObject.getInt("side_id");
            tr.content = jsonObject.getString("content");

            JSONObject user = jsonObject.getJSONObject("user");
            tr.writer = User.getUserFromJson(user);

            String createdAtStr = jsonObject.getString("created_at");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tr.createdAt.setTime(sdf.parse(createdAtStr));
            TimeZone myPhoneTimeZone = tr.createdAt.getTimeZone();
            int gmtOffset = myPhoneTimeZone.getRawOffset() / 60 / 60 / 1000;
            tr.createdAt.add(Calendar.HOUR_OF_DAY, gmtOffset);

            tr.likeCount = jsonObject.getInt("like_count");
            tr.dislikeCount = jsonObject.getInt("dislike_count");
            tr.isMyLike = jsonObject.getBoolean("my_like");
            tr.isMyDislike = jsonObject.getBoolean("my_dislike");

            tr.replyCount = jsonObject.getInt("reply_count");

            if (!jsonObject.isNull("replies")) {
                JSONArray replies = jsonObject.getJSONArray("replies");

                for (int i=0 ; i < replies.length() ; i++) {

                    JSONObject re_reply = replies.getJSONObject(i);
                    TopicReply reReply = TopicReply.getTopicReplyFromJson(re_reply);
                    tr.replyList.add(reReply);
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return tr;
    }

    public TopicReply() {

    }

    public TopicReply(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public int getSide_id() {
        return side_id;
    }

    public void setSide_id(int side_id) {
        this.side_id = side_id;
    }


    //    현재 시간 대비 작성시간이 얼마나 오래되었나를 체크해서, 다른 양식으로 출력

    public String getFormattedTimeAgo() {
//        1. 작성한 시간(!)으로부터 현재시간(!)이 얼마나 흘렀나? => 그 둘의 차이

        long writeTime = this.createdAt.getTimeInMillis(); // 작성 시간
        long now = System.currentTimeMillis(); // 현재 시간을 long으로 바로 리턴.

        long diff = now - writeTime;

        if (diff < 1 * 60 * 1000) {
            return "방금 전";
        }
        else if (diff < 1 * 60 * 60 * 1000) {

            long minute = diff / 1000 / 60;
            return String.format("%d분 전", minute);
        }
        else if (diff < 1 * 24 * 60 * 60 * 1000) {
            long hour = diff / 1000 / 60 / 60;
            return String.format("%d시간 전", hour);
        }
        else {
//            하루가 넘어가면 그 날짜만 출력. 2020년 5월 5일
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");
            return sdf.format(this.createdAt.getTime());
        }


    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public boolean isMyLike() {
        return isMyLike;
    }

    public void setMyLike(boolean myLike) {
        isMyLike = myLike;
    }

    public boolean isMyDislike() {
        return isMyDislike;
    }

    public void setMyDislike(boolean myDislike) {
        isMyDislike = myDislike;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public List<TopicReply> getReplyList() {
        return replyList;
    }
}
