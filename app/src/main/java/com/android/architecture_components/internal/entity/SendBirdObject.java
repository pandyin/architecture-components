package com.android.architecture_components.internal.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

public abstract class SendBirdObject<T> {

    @NonNull
    @PrimaryKey
    public String id = UUID.randomUUID().toString();

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] data;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    abstract T get();

    protected void set(byte[] data) {
        this.data = data;
    }
}
