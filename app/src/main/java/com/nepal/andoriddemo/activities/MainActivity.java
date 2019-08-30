package com.nepal.andoriddemo.activities;import androidx.appcompat.app.AppCompatActivity;import android.content.Intent;import android.os.Bundle;import android.view.View;import android.widget.LinearLayout;import com.nepal.andoriddemo.R;import com.nepal.andoriddemo.helpers.AppActivity;public class MainActivity extends AppActivity implements View.OnClickListener {    private LinearLayout searchLayout;    private LinearLayout notificationLayout;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        initializeView();        initializeListeners();    }    @Override    protected void initializeView() {        searchLayout = findViewById(R.id.search_layout);        notificationLayout = findViewById(R.id.notification_layout);    }    @Override    protected void initializeListeners() {        searchLayout.setOnClickListener(this);        notificationLayout.setOnClickListener(this);    }    @Override    public void onClick(View view) {        switch (view.getId()){            case R.id.search_layout:                startActivity(new Intent(MainActivity.this, SearchActivity.class));                break;            case R.id.notification_layout:                startActivity(new Intent(MainActivity.this, NotificationActivity.class));                break;        }    }}