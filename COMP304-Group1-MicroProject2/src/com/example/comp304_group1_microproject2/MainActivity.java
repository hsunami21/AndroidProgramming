package com.example.comp304_group1_microproject2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mp = MediaPlayer.create(this, R.raw.frozen_letitgo);
		mp.start();
		
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
		if (view.getId() == R.id.gummy)
		{
			mp = MediaPlayer.create(this,  R.raw.gummybear);
		}
		else if (view.getId() == R.id.letitgo)
		{
			mp = MediaPlayer.create(this,  R.raw.frozen_letitgo);
		}
		mp.start();
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
