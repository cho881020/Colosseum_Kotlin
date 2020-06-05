package kr.co.tjoeun.colosseum_kotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.colosseum_kotlin.adapters.NotificationAdapter;
import kr.co.tjoeun.colosseum_kotlin.databinding.ActivityNotificationListBinding;
import kr.co.tjoeun.colosseum_kotlin.datas.Notification;
import kr.co.tjoeun.colosseum_kotlin.utils.ServerUtil;

public class NotificationListActivity extends BaseActivity {

    List<Notification> notificationList = new ArrayList<>();
    NotificationAdapter myNotiAdapter;

    ActivityNotificationListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification_list);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        notificaionImg.setVisibility(View.INVISIBLE);

        myNotiAdapter = new NotificationAdapter(mContext, R.layout.notification_list_item, notificationList);
        binding.notiListView.setAdapter(myNotiAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotiFromServer();
    }

    void getNotiFromServer() {

        ServerUtil.getRequestNotifications(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("알림목록응답", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONArray notis = data.getJSONArray("notifications");

                    notificationList.clear();

                    for (int i=0 ; i < notis.length() ; i++) {
                        JSONObject notiObj = notis.getJSONObject(i);
                        notificationList.add(Notification.getNotiFromJson(notiObj));
                    }

//                    notifyDataSetChange 필요

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myNotiAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
