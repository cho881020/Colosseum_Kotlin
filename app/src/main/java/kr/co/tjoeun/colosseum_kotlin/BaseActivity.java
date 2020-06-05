package kr.co.tjoeun.colosseum_kotlin;

import android.content.Context;
import android.os.Bundle;
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

            activityTitleTxt = getSupportActionBar().getCustomView().findViewById(R.id.activityTitleTxt);
        }


    }

}
