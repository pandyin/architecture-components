package com.android.architecture_components.data.repository;

import android.arch.lifecycle.LiveData;

import com.android.architecture_components.data.SendBirdApi;
import com.android.architecture_components.data.event.LiveEvent;
import com.android.architecture_components.persistence.dao.Dao;
import com.android.architecture_components.persistence.entity.SendBirdObject;
import com.android.architecture_components.worker.SendBirdWorkManager;

import java.util.UUID;

import androidx.work.WorkRequest;
import androidx.work.WorkStatus;

public abstract class BaseRepository<OBJ extends SendBirdObject, DAO extends Dao, LE extends LiveEvent>
        implements SendBirdApi<OBJ> {

    protected DAO dao;

    BaseRepository(DAO dao) {
        this.dao = dao;
    }

    public abstract LE getLiveEvent();

    final LiveData<WorkStatus> enqueue(WorkRequest request) {
        SendBirdWorkManager.getInstance().enqueue(request);
        return getWorkStatusById(request.getId());
    }

    final void enqueue(WorkRequest... requests) {
        SendBirdWorkManager.getInstance().enqueue(requests);
    }

    final LiveData<WorkStatus> getWorkStatusById(UUID workId) {
        return SendBirdWorkManager.getInstance().getStatusById(workId);
    }
}
