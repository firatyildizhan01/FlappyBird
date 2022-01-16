package com.sport.game.sunsetlacross.game


import android.content.Context
import android.media.SoundPool

import android.media.MediaPlayer


class VoiceUtils {
    private var soundPool: SoundPool? = null
    private var soundID //创建某个声音对应的音频ID
            = 0

    /**
     * start SoundPool
     */
    fun playVoice(mContext: Context) {
//        if (soundPool == null) {
//            soundPool = SoundPool(1, AudioManager.STREAM_ALARM, 0)
//            soundID = soundPool!!.load(mContext, com.sport.game.sunsetlacross.R.raw.menugamesong, 1)
//        }
//        if (enableVoice) {
//            soundPool!!.play(
//                soundID,
//                0.1f,
//                0.5f,
//                0,
//                1, 1f
//            )
//        }
        val music: MediaPlayer = MediaPlayer.create(mContext, com.sport.game.sunsetlacross.R.raw.hit)
        music.start()
    }

    /**
     * release SoundPool
     */
    fun release() {
        if (soundPool != null) {
            soundPool!!.stop(soundID)
        }
        soundPool = null
    }
}