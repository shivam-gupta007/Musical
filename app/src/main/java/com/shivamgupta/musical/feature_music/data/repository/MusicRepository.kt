package com.shivamgupta.musical.feature_music.data.repository

import androidx.lifecycle.MutableLiveData
import com.shivamgupta.musical.feature_music.data.local.MusicLocalDataSource
import com.shivamgupta.musical.feature_music.domain.model.Music
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val musicLocalDataSource: MusicLocalDataSource
){
    private val _musicList = MutableLiveData<List<Music>>()
    val musicList get() = _musicList

    suspend fun getLocalMusicList(){
        musicLocalDataSource.getMusicList()
    }
}