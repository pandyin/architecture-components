package com.android.architecture_components.internal.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.android.architecture_components.internal.entity.Message;

@Dao
public interface MessageDao extends com.android.architecture_components.internal.dao.Dao {

    @Query("SELECT * FROM message")
    DataSource.Factory<Integer, Message> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Message... messages);
}
