package com.example.comp304_group1_microproject3;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayMusicProgressBar extends Activity {

	MediaPlayer mp;
	ProgressBar progress;
	TextView txtProgress;

	int currentPosition;
	int length;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_music_progress_bar);
		
		mp = MediaPlayer.create(this, R.raw.gummybear);
		progress = (ProgressBar) findViewById(R.id.progressBar);	

		mp.start();
		
		new Thread(new Runnable() {
            public void run() {
        	   	currentPosition = 0;
            	length = mp.getDuration();
            	
        		progress.setMax(length);
        		
            	while (mp != null && currentPosition < length) {
        			try {
        				Thread.sleep(1000);
        				currentPosition = mp.getCurrentPosition();
        			}
        			catch (Exception e) {
        				return;
        			}
        			progress.setProgress(currentPosition);
        		}
            }
        }).start();
		
		TextView tv = (TextView)findViewById(R.id.SongList);
		tv.setText("Song Length: " + mp.getDuration() + "ms");
	}
	
	@Override
	protected void onPause() { super.onPause(); mp.stop(); }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void songClick(View view)
	{
		mp.stop();
		progress = (ProgressBar) findViewById(R.id.progressBar);
		progress.setProgress(0);

		if (view.getId() == R.id.gummy)
		{
			mp = MediaPlayer.create(this,  R.raw.gummybear);
		}
		else if (view.getId() == R.id.letitgo)
		{
			mp = MediaPlayer.create(this,  R.raw.frozen_letitgo);
		}

		mp.start();
		
		new Thread(new Runnable() {
            public void run() {
            	currentPosition = 0;
            	length = mp.getDuration();
        		
        		progress.setMax(length);
        		
            	while (mp != null && currentPosition < length) {
        			try {
        				Thread.sleep(1000);
        				currentPosition = mp.getCurrentPosition();	
        			}
        			catch (Exception e) {
        				return;
        			}
		 			progress.setProgress(currentPosition);
        		}
            }
        }).start();
	
		TextView tv = (TextView)findViewById(R.id.SongList);
		tv.setText("Song Length: " + mp.getDuration() + "ms");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
