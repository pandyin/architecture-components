package com.android.architecture_components.data;

import android.arch.lifecycle.MediatorLiveData;

import com.android.architecture_components.persistence.entity.SendBirdObject;

public class SendBirdLiveData<OBJ extends SendBirdObject> extends MediatorLiveData<OBJ> {

}