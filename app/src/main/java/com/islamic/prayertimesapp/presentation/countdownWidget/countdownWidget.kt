package com.islamic.prayertimesapp.presentation.countdownWidget

import android.app.PendingIntent
import android.app.AlarmManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.widget.RemoteViews
import androidx.fragment.app.activityViewModels
import com.islamic.prayertimesapp.R
import com.islamic.prayertimesapp.common.util.Constants.Companion.COUNTDOWN_TIME_KEY
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import android.appwidget.AppWidgetManager

@AndroidEntryPoint
class countdownWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
) {

    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val timeMillis = preferences.getLong(COUNTDOWN_TIME_KEY, 0L)

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.countdown_widget)
    // Calculate the remaining time in milliseconds
    val currentTimeMillis = System.currentTimeMillis()
    val remainingTimeMillis = timeMillis - currentTimeMillis

    views.setChronometer(R.id.countDown,    (currentTimeMillis - timeMillis )/ 100000, "%02d hr %02d min %02d sec" , true)
    views.setChronometerCountDown(R.id.countDown, true)//if you wa
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}


