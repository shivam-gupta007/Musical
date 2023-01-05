package com.shivamgupta.musical.feature_music.data.local

import android.app.Application
import android.os.Build
import android.provider.MediaStore
import com.shivamgupta.musical.feature_music.domain.model.Music
import javax.inject.Inject

class MusicLocalDataSource @Inject constructor(
   private val application: Application
){
    suspend fun getMusicList(): ArrayList<Music> {
        val selection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaStore.Audio.Media.IS_MUSIC + " AND " + MediaStore.Audio.Media.IS_RECORDING + " == 0"
        } else {
            MediaStore.Audio.Media.IS_MUSIC
        }
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED
        )

        val musicList = ArrayList<Music>()
        val musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val musicCursor = application.contentResolver.query(
            musicUri,
            projection,
            selection,
            null,
            "${MediaStore.Audio.Media.DATE_ADDED} DESC"
        )

        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            val titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val durationColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            val dateAddedColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED)
            //add songs to list
            do {
                val thisId = musicCursor.getLong(idColumn)
                val thisTitle = musicCursor.getString(titleColumn)
                val thisArtist = musicCursor.getString(artistColumn)
                val thisDuration = musicCursor.getString(durationColumn)
                val thisDateAdded = musicCursor.getString(dateAddedColumn)
                musicList.add(
                    Music(
                        thisId,
                        thisTitle,
                        thisArtist,
                        thisDuration,
                        thisDateAdded
                    )
                )
            } while (musicCursor.moveToNext())
        }
        musicCursor?.close()
        return musicList
    }
}