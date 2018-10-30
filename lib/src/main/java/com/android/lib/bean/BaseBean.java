package com.android.lib.bean;

//by summer on 2017-06-20.

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

//import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
//import com.raizlabs.android.dbflow.config.FlowManager;
//import com.raizlabs.android.dbflow.structure.AsyncModel;
//import com.raizlabs.android.dbflow.structure.InvalidDBConfiguration;
//import com.raizlabs.android.dbflow.structure.Model;
//import com.raizlabs.android.dbflow.structure.ModelAdapter;
//import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.io.Serializable;


public class BaseBean extends BaseObservable implements Serializable  {



    public String getUniqueID(){
        return toString();
    }
}
