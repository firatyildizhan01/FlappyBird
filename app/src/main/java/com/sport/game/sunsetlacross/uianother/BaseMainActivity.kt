package com.sport.game.sunsetlacross.uianother

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import com.sport.game.sunsetlacross.game.VoiceUtils
import android.R




private lateinit var soundPool: SoundPool
var sound1 = 0
lateinit var mediaPlayer: MediaPlayer


open class baseMainActivity : AppCompatActivity() {

    var continueBGMusic = false
    override fun onCreate(savedInstanceState: Bundle?) {
        mediaPlayer = MediaPlayer.create(this, com.sport.game.sunsetlacross.R.raw.menugamesong)

        super.onCreate(savedInstanceState)
        continueBGMusic=true;
        setContentView(com.sport.game.sunsetlacross.R.layout.activity_base_main)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        sound1 = soundPool.load(this, com.sport.game.sunsetlacross.R.raw.menugamesong, 1)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        actionBar?.hide()
        setupMediaPlayer()

        val navHostFragment =
            supportFragmentManager.findFragmentById(com.sport.game.sunsetlacross.R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


    }
    open fun setupMediaPlayer() {
        mediaPlayer.start()
    }

    open fun stopMediaPlayer(){
        mediaPlayer.stop()
    }
    open fun startMediaPlayer(){
        mediaPlayer.start()
    }
    override fun onResume() {
        super.onResume()
        continueBGMusic=false;
        mediaPlayer.start();    }

     override fun onPause() {
        super.onPause()
        if(!continueBGMusic)
            mediaPlayer.pause();
    }


}
