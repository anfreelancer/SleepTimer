package com.luteapp.sleeptimer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.Q
import android.service.quicksettings.Tile.STATE_ACTIVE
import android.service.quicksettings.Tile.STATE_INACTIVE
import android.service.quicksettings.TileService
import com.luteapp.sleeptimer.SleepNotification.find
import com.luteapp.sleeptimer.SleepNotification.handle
import com.luteapp.sleeptimer.SleepNotification.toggle
import com.luteapp.sleeptimer.R
import java.text.DateFormat.SHORT
import java.text.DateFormat.getTimeInstance
import java.util.Date

class SleepTileService : TileService() {

    companion object {
        fun Context.requestTileUpdate() = requestListeningState(this, ComponentName(this, SleepTileService::class.java))
    }

    override fun onStartListening() = refreshTile()

    override fun onClick() = toggle().also { refreshTile() }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handle(intent)
        requestTileUpdate()
        stopSelfResult(startId)
        return START_NOT_STICKY
    }

    private fun refreshTile() = qsTile?.run {
        when (val notification = find()) {
            null -> {
                state = STATE_INACTIVE
                if (SDK_INT >= Q) subtitle = resources.getText(R.string.tile_subtitle)
            }
            else -> {
                state = STATE_ACTIVE
                if (SDK_INT >= Q) subtitle = getTimeInstance(SHORT).format(Date(notification.`when`))
            }
        }
        updateTile()
    } ?: Unit

}