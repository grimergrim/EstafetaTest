package com.avtologistika.test.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avtologistika.test.GlobalApplication;
import com.avtologistika.test.R;
import com.avtologistika.test.api.Constants;
import com.avtologistika.test.screens.main.MainActivity;
import com.avtologistika.test.utils.NetworkChecker;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private EditText mLoginView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mLoginButton;
    private ActionBar mToolbar;

    @Inject
    protected LoginContract.LoginPresenter mLoginPresenter;

    @Inject
    protected NetworkChecker mNetworkChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GlobalApplication) this.getApplication()).getMainComponent().inject(this);
        setContentView(R.layout.activity_login);
        findViews();
        setListeners();
        if (null != mToolbar) {
            mToolbar.setTitle(R.string.toolbar_title);
            mToolbar.setSubtitle(R.string.toolbar_subtitle);
        }
    }

    private boolean isLoginValid(String login) {
        return login.length() >= Constants.MIN_LOGIN_LENGTH;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= Constants.MIN_PASSWORD_LENGTH;
    }

    private void findViews() {
        mToolbar = getSupportActionBar();
        mLoginView = (EditText) findViewById(R.id.login);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form_scroll_view);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void setListeners() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = mLoginView.getText().toString();
                String password = mPasswordView.getText().toString();
                boolean isLoginValid = isLoginValid(login);
                boolean isPasswordValid = isPasswordValid(password);
                boolean isNetworkConnected = mNetworkChecker.isNetworkConnected();
                if (!isLoginValid) {
                    showToast(getResources().getString(R.string.error_wrong_login));
                }
                if (!isPasswordValid) {
                    showToast(getResources().getString(R.string.error_invalid_password));
                }
                if (!isNetworkConnected) {
                    showToast(getResources().getString(R.string.error_no_internet));
                }
                if (isLoginValid && isPasswordValid && isNetworkConnected) {
                    showProgress(true);
                    mLoginPresenter.getTasksAndLogin(login, password);
                }
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            mLoginFormView.setVisibility(View.GONE);
            mProgressView.setVisibility(View.VISIBLE);
        } else {
            mLoginFormView.setVisibility(View.VISIBLE);
            mProgressView.setVisibility(View.GONE);
        }
    }

    @Override
    public void goToTaskList() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError() {
        showToast(getResources().getString(R.string.error_connection));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoginPresenter.setVIew(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress(false);
        mLoginPresenter.setVIew(this);
    }
}

