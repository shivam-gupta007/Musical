package com.shivamgupta.musical.feature_music.domain.use_cases

import androidx.lifecycle.MutableLiveData
import com.shivamgupta.musical.feature_music.data.repository.MusicRepository
import com.shivamgupta.musical.feature_music.domain.model.Music
import javax.inject.Inject

class GetLocalMusicList @Inject constructor(
    private val musicRepository: MusicRepository
) {
    private val _musicList = MutableLiveData<List<Music>>()
    val musicList get() = _musicList

    operator fun invoke() {

    }

}