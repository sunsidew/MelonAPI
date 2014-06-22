package com.example.melontester;

import java.util.ArrayList;

import org.nhnnext.MelonAPI.Album;
import org.nhnnext.MelonAPI.MelonChart;
import org.nhnnext.MelonAPI.Song;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	Button btn;
	TextView tv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    
    public void initUI() {
		btn = (Button) findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.textView1);
		btn.setOnClickListener(this);
	}
    
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			MelonChart melonchart = new MelonChart("a857db0d-38f7-3375-a5ad-4d4b306a8945");
			//ArrayList<Song> GenreList
			//ArrayList<Song> GenreChartList = melonchart.getGenreChart("DP0800",1,5);
			//ArrayList<Song> nonGenreChartList = melonchart.getnonGenreChart(1,5);
			//ArrayList<Song> dailyChartList = melonchart.getdailyChart(1,5);
			ArrayList<Album> AlbumChartList = melonchart.getalbumChart(1,5);
			//ArrayList<Song> realTimeChartList = melonchart.getRealTimeChart(1,5);
			Log.i("test","start!");
			/*for(Song song : GenreChartList) {
				  Log.i("test",song.getSongId());
				  Log.i("test",song.getSongName());
				  Log.i("test",song.getArtistName());
			}*/
			for(Album album : AlbumChartList) {
				  Log.i("test",album.getRepSongName());
				  Log.i("test",album.getAlbumName());
				  Log.i("test",album.getAlbumType());
				  Log.i("test",album.getArtistName());
			}
			Log.i("test","end!");
		}
	}
}
