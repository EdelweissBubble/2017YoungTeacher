package com.bubble.youngteacher.app;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.bubble.youngteacher.R;
import com.bubble.youngteacher.utils.LogUtils;
import com.bubble.youngteacher.utils.NavigationBarUtils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScreenMusicPlayer extends BaseActivity implements View.OnClickListener {

	private View naviBar;
	private EditText logView;
	private Button playButton;
	private Button stopButton;

	// 音频播放
	private MediaPlayer musicPlayer = null;

	private Timer playTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen_music_player_main);
		init();
	}

	private void playMusic() {
		// musicPlayer = new MediaPlayer();
		try {
			if (musicPlayer != null) {
				if (musicPlayer.isPlaying()) {
					musicPlayer.stop();
				}
				musicPlayer.reset();
				musicPlayer.release();
				musicPlayer = null;
			}
			musicPlayer = MediaPlayer.create(this.getApplicationContext(), R.raw.butterflylove);
			// musicPlayer = new MediaPlayer();
			// musicPlayer.setDataSource("http://example.com/testmusic.mp3");
			musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			// musicPlayer.prepare();
			// musicPlayer.prepareAsync();
			musicPlayer.start();
			musicPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					// duration = mp.getDuration();
					// mp.start();
				}

			});
			musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					if (mp != null) {
						// mp.reset();
						mp.release();
						mp = null;
					}
					if (musicPlayer != null) {

						musicPlayer.release();
						musicPlayer = null;
					}
					if (playTimer != null) {
						playTimer.cancel();
						playTimer = null;
					}
				}
			});

			// 添加定时器，返回进度
			if (playTimer == null) {
				playTimer = new Timer();
			}
			TimerTask mTimerTask = new TimerTask() {
				@Override
				public void run() {
					LogUtils.e("音乐播放器", "进度：" + musicPlayer.getCurrentPosition());
				}
			};
			playTimer.schedule(mTimerTask, 0, 1000);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtils.e("音乐播放器", "文件播放错误，IllegalArgumentException！");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtils.e("音乐播放器", "文件播放错误，SecurityException！");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtils.e("音乐播放器", "文件播放错误，IllegalStateException！");
		}
	}

	private void stopMusic() {
		if (playTimer != null) {
			playTimer.cancel();
			playTimer = null;
		}
		if (musicPlayer != null) {
			if (musicPlayer.isPlaying()) {
				musicPlayer.stop();
			}
			musicPlayer.reset();
			musicPlayer.release();
			musicPlayer = null;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		naviBar = findViewById(R.id.navi_bar);
		logView = (EditText) findViewById(R.id.logview);
		playButton = (Button) findViewById(R.id.play);
		stopButton = (Button) findViewById(R.id.stop);
		initBase(logView);
		NavigationBarUtils.initNavigationBar(this, naviBar, true, ScreenContents.class, false, ScreenMusicPlayer.class,
				false, ScreenContents.class);
		playButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (musicPlayer != null) {
			if (musicPlayer.isPlaying()) {
				musicPlayer.start();
			}
			musicPlayer.reset();
			musicPlayer.release();
			musicPlayer = null;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		// this.finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.play:
			playMusic();
			break;
		case R.id.stop:
			stopMusic();
			break;
		default:
			break;
		}
	}
}