package com.example.fathurradhy.mocinemas.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.fathurradhy.mocinemas.widget.StackRemoteViewsFactory;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
