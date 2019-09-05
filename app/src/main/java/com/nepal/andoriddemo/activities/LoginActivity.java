package com.nepal.andoriddemo.activities;import android.content.Intent;import android.os.Bundle;import android.util.Patterns;import android.view.View;import android.widget.EditText;import android.widget.ImageView;import android.widget.TextView;import android.widget.Toast;import com.google.gson.GsonBuilder;import com.nepal.andoriddemo.R;import com.nepal.andoriddemo.helpers.AppActivity;import com.nepal.andoriddemo.helpers.ShowToast;import com.nepal.andoriddemo.models.UserLogin;import com.nepal.andoriddemo.presenters.LoginPresenter;import com.nepal.andoriddemo.utils.Utilities;import com.nepal.andoriddemo.utils.UtilitiesFunctions;import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;public class LoginActivity extends AppActivity implements LoginPresenter.View, View.OnClickListener {    private ImageView imageView;    private TextView buttonLogin;    private EditText editTextEmail;    private EditText editTextPassword;    private SmoothProgressBar progressBar;    private LoginPresenter loginPresenter;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_login);        initializeView();        initializeListeners();        checkIfUserIsLogin();    }    private void checkIfUserIsLogin() {        if (Utilities.isLogin()){            doLoginAfterSuccessWork();        }    }    @Override    protected void initializeView() {        buttonLogin = findViewById(R.id.btn_login);        editTextEmail = findViewById(R.id.et_username);        editTextPassword = findViewById(R.id.et_password);        progressBar = findViewById(R.id.progress_bar);    }    @Override    protected void initializeListeners() {        loginPresenter = new LoginPresenter(this);        buttonLogin.setOnClickListener(this);        editTextEmail.setText("android@gmail.com");        editTextPassword.setText("android");    }    @Override    public void onLoginResponseSuccess(UserLogin userLogin) {        hideProgressBar();        ShowToast.withLongMessage("Success" + new GsonBuilder().create().toJson(userLogin));        doLoginAfterSuccessWork();    }    private void doLoginAfterSuccessWork() {        startActivity(new Intent(LoginActivity.this, MainActivity.class));        LoginActivity.this.finish();    }    @Override    public void onResponseFailure(String message) {        hideProgressBar();        ShowToast.withLongMessage("Failure");    }    private void showProgressBar() {        if (progressBar != null) {            buttonLogin.setEnabled(false);            progressBar.setVisibility(View.VISIBLE);        }    }    private void hideProgressBar() {        if (progressBar != null) {            buttonLogin.setEnabled(true);            progressBar.setVisibility(View.GONE);        }    }    @Override    public void onClick(View view) {        switch (view.getId()) {            case R.id.btn_login:                doLoginWork(editTextEmail, editTextPassword);                break;        }    }    private void doLoginWork(EditText editTextEmail, EditText editTextPassword) {        if (editTextEmail.getText().toString().isEmpty() && editTextPassword.getText().toString().isEmpty()) {            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();        } else {            if (Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText()).matches()) {                if (UtilitiesFunctions.isNetworkAvailable(LoginActivity.this)) {                    showProgressBar();                    loginPresenter.userLogin(editTextEmail.getText().toString(), editTextPassword.getText().toString());                } else {                    ShowToast.withLongMessage("Please connect to internet..");                }            } else {                Toast.makeText(this, "Valid email is required", Toast.LENGTH_SHORT).show();            }        }    }}