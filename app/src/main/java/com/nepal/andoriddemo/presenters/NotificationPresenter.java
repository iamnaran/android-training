package com.nepal.andoriddemo.presenters;


import android.util.Log;

import com.nepal.andoriddemo.apiservices.ApiClient;
import com.nepal.andoriddemo.apiservices.LoginApiService;
import com.nepal.andoriddemo.apiservices.NotificationApiService;
import com.nepal.andoriddemo.models.Notifications;
import com.nepal.andoriddemo.models.UserLogin;
import com.nepal.andoriddemo.utils.Utilities;
import com.nepal.andoriddemo.utils.UtilitiesFunctions;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationPresenter {


    private WeakReference<View> view;

    public NotificationPresenter(NotificationPresenter.View view) {
        this.view = new WeakReference<>(view);

    }

    private NotificationPresenter.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {

        void onNotificationResponseSuccess(Notifications notifications);

        void onNotificationResponseFailure(String message);


    }

    public void getNotificationData() {

        NotificationApiService notificationApiService = ApiClient.getClient().create(NotificationApiService.class);

        notificationApiService.getNotificaitons().enqueue(new Callback<Notifications>() {
            @Override
            public void onResponse(Call<Notifications> call, Response<Notifications> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        if (getView() != null){

                            Utilities.saveNotificationData(response.body());

                            getView().onNotificationResponseSuccess(response.body());

                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<Notifications> call, Throwable t) {

                if (getView() != null){

                    getView().onNotificationResponseFailure(UtilitiesFunctions.handleApiError(t));

                }

            }
        });

    }


}
