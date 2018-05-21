package com.android.architecture_components.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.android.architecture_components.persistence.dao.ChannelDao;
import com.android.architecture_components.persistence.entity.Channel;
import com.android.architecture_components.work.CreateChannelWorker;
import com.android.architecture_components.work.GetAllChannelsWorker;

import androidx.work.WorkStatus;

public class ChannelRepository extends Repository<ChannelDao, Channel> {

    public ChannelRepository(ChannelDao channelDao) {
        super(channelDao);
    }

    @Override
    public LiveData<PagedList<Channel>> getAll() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .setEnablePlaceholders(true)
                .build();

        return new LivePagedListBuilder<>(dao.getAll(), config).build();
    }

    public LiveData<WorkStatus> getAllFromNetwork() {
        return enqueue(new GetAllChannelsWorker.Builder<GetAllChannelsWorker>()
                .build(GetAllChannelsWorker.class));
    }

    public LiveData<WorkStatus> create(String channelName) {
        return enqueue(new CreateChannelWorker.Builder<CreateChannelWorker>()
                .putData(CreateChannelWorker.CHANNEL_NAME, channelName)
                .build(CreateChannelWorker.class));
    }
}