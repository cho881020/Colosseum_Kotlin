package kr.co.tjoeun.colosseum_kotlin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext = this;

    public abstract void setupEvents();
    public abstract void setValues();

    public TextView activityTitleTxt;
    public ImageView notificaionImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomActionBar();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

        activityTitleTxt.setText(title);

    }

    public void setCustomActionBar() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_action_bar);
            getSupportActionBar().setBackgroundDrawable(null);

            Toolbar toolbar = (Toolbar) getSupportActionBar().getCustomView().getParent();
            toolbar.setContentInsetsAbsolute(0,0);

            View customActionView = getSupportActionBar().getCustomView();

            activityTitleTxt = customActionView.findViewById(R.id.activityTitleTxt);
            notificaionImg = customActionView.findViewById(R.id.notificaionImg);

            notificaionImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(mContext, NotificationListActivity.class);
                    startActivity(myIntent);
                }
            });

//            기본 화면 이름을 Colosseum으로 세팅.
            setTitle("Colosseum");
        }


    }

}
