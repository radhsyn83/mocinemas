package com.example.fathurradhy.mocinemas.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.services.StackWidgetService;

public class ImagesBannerWidget extends AppWidgetProvider {

    public static final String TOAST_ACTION = "com.example.dicoding.TOAST_ACTION";
    public static final String EXTRA_MOVIE_ID = "com.example.dicoding.EXTRA_ID";
    public static final String EXTRA_MOVIE_JUDUL = "com.example.dicoding.EXTRA_JUDUL";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.image_banner_widget);

        views.setRemoteAdapter( R.id.stack_view, intent);
        views.setEmptyView(R.id.stack_view, R.id.empty_view);

        Intent toastIntent = new Intent(context, ImagesBannerWidget.class);

        toastIntent.setAction(ImagesBannerWidget.TOAST_ACTION);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
// Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
// Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)) {
            String title = intent.getStringExtra(EXTRA_MOVIE_JUDUL);

            Toast.makeText(context, "Touched view " + title, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }
}

