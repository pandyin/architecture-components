package com.android.architecture_components.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.text.Editable;

import com.android.architecture_components.persistence.entity.Channel;
import com.android.architecture_components.repository.ChannelRepository;
import com.android.architecture_components.ui.ChannelRecyclerView;
import com.android.architecture_components.viewmodel.ChannelViewModel;

import androidx.work.WorkStatus;

public class ChannelPresenter extends Presenter<ChannelRecyclerView, ChannelRepository, ChannelViewModel> {

    public ChannelPresenter(LifecycleOwner lifecycleOwner, ChannelRecyclerView view, ChannelRepository repository, ChannelViewModel viewModel) {
        super(lifecycleOwner, view, repository, viewModel);
    }

    @Override
    protected void init() {
        androidViewModel.getAllLiveData().observe(lifecycleOwner, new Observer<PagedList<Channel>>() {
            @Override
            public void onChanged(@Nullable PagedList<Channel> channels) {
                view.submitList(channels);
            }
        });

        repository.getAllNetworkData().observe(lifecycleOwner, new Observer<WorkStatus>() {
            @Override
            public void onChanged(@Nullable WorkStatus workStatus) {

            }
        });

        view.displayLoggedInSnackbar();
    }

    public void onCreateChannelClicked(Editable editable) {
        repository.create(editable.toString());
    }
}
