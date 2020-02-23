package tronku.project.zealicon.Activity

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_player.*
import tronku.project.zealicon.Fragment.InfoBottomSheetFragment
import tronku.project.zealicon.Model.EventTrack
import tronku.project.zealicon.Model.EventTrackDB
import tronku.project.zealicon.R
import tronku.project.zealicon.Utils.AnimUtils

class PlayerActivity : AppCompatActivity() {

    private var isAdded = false
    private var isMute = false
    private var isPlaying = true
    private var tracks = ArrayList<EventTrackDB>()
    private lateinit var currentTrack: EventTrackDB
    private var currentPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        tracks = intent.getParcelableArrayListExtra("tracks")
        currentPos = intent.getIntExtra("position", 0)
        inflateUI()
        addClickEvents()
    }

    fun inflateUI() {
        currentTrack = tracks[currentPos]
        eventName.text = currentTrack.name
        eventType.text = currentTrack.category
    }

    fun addClickEvents() {
        val rotator = ObjectAnimator.ofFloat(trackImage, View.ROTATION, 0f, 360f)
        rotator.duration = 6000
        rotator.repeatCount = ObjectAnimator.INFINITE
        rotator.interpolator = LinearInterpolator()
        rotator.start()

        buttonPlayPause.setOnClickListener {
            AnimUtils.setClickAnimation(buttonPlayPause)
           if (isPlaying) {
               isPlaying = false
               buttonPlayPause.setImageResource(R.drawable.play_button)
               rotator.pause()
               waveView.pause()
           } else {
               isPlaying = true
               buttonPlayPause.setImageResource(R.drawable.pause_icon)
               rotator.resume()
               waveView.play()
           }

        }

        buttonBack.setOnClickListener {
            AnimUtils.setClickAnimation(buttonBack)
            if (currentPos == 0) {
                Toast.makeText(this@PlayerActivity, "No tracks available", Toast.LENGTH_SHORT).show()
            } else {
                currentPos--
                inflateUI()
            }
        }

        buttonNext.setOnClickListener {
            AnimUtils.setClickAnimation(buttonNext)
            if (currentPos == tracks.size - 1) {
                Toast.makeText(this@PlayerActivity, "No tracks available", Toast.LENGTH_SHORT).show()
            } else {
                currentPos++
                inflateUI()
            }
        }

        backButton.setOnClickListener {
            AnimUtils.setClickAnimation(backButton)
            onBackPressed()
        }

        addRemoveButton.setOnClickListener {
            AnimUtils.setClickAnimation(addRemoveButton)
            if (isAdded) {
                isAdded = false
                addRemoveButton.setImageResource(R.drawable.ic_playlist_add)
            } else {
                isAdded = true
                addRemoveButton.setImageResource(R.drawable.ic_playlist_added)
            }
        }

        buttonMuteUnmute.setOnClickListener {
            AnimUtils.setClickAnimation(buttonMuteUnmute)
            if (isMute) {
                isMute = false
                buttonMuteUnmute.setImageResource(R.drawable.ic_mute)
            } else {
                isMute = true
                buttonMuteUnmute.setImageResource(R.drawable.ic_unmute)
            }
        }

        infoButton.setOnClickListener {
            AnimUtils.setClickAnimation(infoButton)
            val bottomSheet = InfoBottomSheetFragment.newInstance(currentTrack)
            bottomSheet.show(supportFragmentManager, InfoBottomSheetFragment.TAG)
        }

    }

}
