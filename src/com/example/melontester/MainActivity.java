package com.example.melontester;

import java.util.ArrayList;

import com.simple.melon.Album;
import com.simple.melon.Artist;
import com.simple.melon.Genre;
import com.simple.melon.MelonChart;
import com.simple.melon.MelonLatest;
import com.simple.melon.MelonSearch;
import com.simple.melon.Song;

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
			String appkey = getString(R.string.api_app_key);
			
			Log.e("test", "아래는 테스트 코드입니다.");
			MelonSearch melonsearch = new MelonSearch(appkey);
			ArrayList<Album> SearchAlbum = melonsearch.getSearchAlbum(1, 5, "hi");
			ArrayList<Song> SearchSong = melonsearch.getSearchSong(1, 10, "hi");
			ArrayList<Artist> SearchArtist = melonsearch.getSearchArtist(1, 10, "버스커");
			
//			MelonLatest melonlatest = new MelonLatest(appkey);
//			ArrayList<Album> LatestAlbumList = melonlatest.getLatestAlbum(1,5);
//			ArrayList<Album> LatestAlbumByGenre = melonlatest.getLatestAlbumByGenre("DP0500", 1, 5);
//			ArrayList<Song> LatestSong = melonlatest.getLatestSong(1, 10);
//			ArrayList<Song> LastetSongByGenre = melonlatest.getLastetSongByGenre("DP0800", 1, 10);
//			
//			MelonChart melonchart = new MelonChart(appkey);
//			ArrayList<Genre> GenreList = melonchart.getGenreList();
//			ArrayList<Song> GenreChartList = melonchart.getGenreChart("DP0800",1,5);
//			ArrayList<Song> nonGenreChartList = melonchart.getnonGenreChart(1,5);
//			ArrayList<Song> dailyChartList = melonchart.getdailyChart(1,5);
//			ArrayList<Album> AlbumChartList = melonchart.getalbumChart(1,5);
//			ArrayList<Song> realTimeChartList = melonchart.getRealTimeChart(1,5);
//			
			for(Song song : SearchSong) {
				  Log.i("test",song.getSongId());
				  Log.i("test",song.getSongName());
				  Log.i("test",song.getArtistName());
				  Log.i("test",String.valueOf(song.isHitSong()));
			}
			for(Album album : SearchAlbum) {
				  Log.i("test",album.getRepSongName());
				  Log.i("test",album.getAlbumName());
				  Log.i("test",album.getTotalSongCount());
				  Log.i("test",album.getIssueDate());
				  Log.i("test",album.getAverageScore());
				  Log.i("test",album.getArtistName());
			}
//			for(Genre genre : GenreList) {
//				Log.i("test",genre.getGenreId());
//				Log.i("test",genre.getGenreName());
//				
//			}
			for(Artist artist : SearchArtist) {
				Log.i("test",artist.getGenreNames());
				Log.i("test",artist.getActTypeName());
				Log.i("test",artist.getSex());
				Log.i("test",artist.getArtistId());
				Log.i("test",artist.getArtistName());
				Log.i("test",artist.getNationalityName());
			}
		}
	}
}
