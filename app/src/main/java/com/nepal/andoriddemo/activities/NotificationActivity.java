package com.nepal.andoriddemo.activities;import android.os.Bundle;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.TextView;import androidx.annotation.NonNull;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import com.google.gson.GsonBuilder;import com.nepal.andoriddemo.R;import com.nepal.andoriddemo.helpers.AppActivity;import com.nepal.andoriddemo.helpers.AppRecyclerViewAdapter;import com.nepal.andoriddemo.helpers.DefineClassType;import com.nepal.andoriddemo.models.Notifications;import com.nepal.andoriddemo.presenters.NotificationPresenter;public class NotificationActivity extends AppActivity implements NotificationPresenter.View, View.OnClickListener {    private NotificationPresenter notificationPresenter;    private RecyclerView recyclerView;    private NotificationRecyclerViewAdapter notificationRecyclerViewAdapter;    private ImageView backArrow;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_notification);        initializeView();        initializeListeners();        prepareRecyclerView();    }    @Override    protected void initializeView() {        recyclerView = findViewById(R.id.recycler_view);        backArrow = findViewById(R.id.back_arrow);    }    @Override    protected void initializeListeners() {        backArrow.setOnClickListener(this);        notificationPresenter = new NotificationPresenter(this);        notificationPresenter.getNotificationData();    }    private void prepareRecyclerView() {        // setup initalise adapter for recyclerview        // layout manager        // adapter set        notificationRecyclerViewAdapter = new NotificationRecyclerViewAdapter();        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this);        recyclerView.setLayoutManager(linearLayoutManager);        recyclerView.setAdapter(notificationRecyclerViewAdapter);    }    @Override    public void onNotificationResponseSuccess(Notifications notifications) {        notificationRecyclerViewAdapter.add(notifications);        notificationRecyclerViewAdapter.notifyDataSetChanged();    }    @Override    public void onNotificationResponseFailure(String message) {    }    @Override    public void onClick(View view) {        switch (view.getId()){            case R.id.back_arrow:                finish();                break;        }    }    private class NotificationRecyclerViewAdapter extends AppRecyclerViewAdapter {        private Notifications notificationsData;        @Override        public void add(Object object) {            notificationsData = DefineClassType.getType(object, Notifications.class);            Log.e( "add: ", new GsonBuilder().create().toJson(notificationsData));        }        @Override        public void clear() {        }        @NonNull        @Override        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {            //views            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification_content, parent, false);            return new VHItem(itemView);        }        @Override        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {            // data holding            VHItem vhItem = (VHItem) holder;            Notifications.Datum data = notificationsData.getData().get(position);            vhItem.notificationTitle.setText(data.getName());        }        @Override        public int getItemCount() {            if (notificationsData != null){                return notificationsData.getData().size();            }            return 0;        }        private class VHItem extends RecyclerView.ViewHolder {            private TextView notificationTitle;            private TextView notificationDescription;            public VHItem(View itemView) {                super(itemView);                notificationTitle = itemView.findViewById(R.id.notification_title);                notificationDescription = itemView.findViewById(R.id.notification_description);            }        }    }}