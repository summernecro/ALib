package com.android.lib.base.interf;

//by summer on 2018-01-10.

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public interface FragI {

    public void onCreate(Bundle savedInstanceState);

    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    public void onDestroyView() ;

    public void onDestroy();
}
