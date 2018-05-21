package com.android.architecture_components.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.architecture_components.R;
import com.android.architecture_components.persistence.entity.User;
import com.android.architecture_components.presenter.AuthPresenter;
import com.android.architecture_components.ui.intent.ChannelIntent;
import com.android.architecture_components.viewmodel.UserViewModel;

public class AuthActivity extends BaseActivity implements AuthView {

    private AuthPresenter authPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        UserViewModel.Factory factory = new UserViewModel.Factory(getApplication());
        UserViewModel userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);

        authPresenter = new AuthPresenter(this, this, userViewModel);
    }

    @Override
    public void submitUser(User user) {
        Toast.makeText(this, "Hello " + user.get().getUserId(), Toast.LENGTH_SHORT).show();
        ChannelIntent intent = new ChannelIntent(this);
        startActivityForResult(intent, intent.getRequestCode());
        finish();
    }
}
