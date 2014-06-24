package com.example.melontester;

import java.util.ArrayList;

import org.nhnnext.MelonAPI.Album;
import org.nhnnext.MelonAPI.Artist;
import org.nhnnext.MelonAPI.Genre;
import org.nhnnext.MelonAPI.MelonChart;
import org.nhnnext.MelonAPI.MelonLatest;
import org.nhnnext.MelonAPI.MelonSearch;
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
			Log.e("test", "아래는 테스트 코드입니다.");
//			MelonSearch melonsearch = new MelonSearch("a857db0d-38f7-3375-a5ad-4d4b306a8945");
//			ArrayList<Album> SearchAlbum = melonsearch.getSearchAlbum(1, 5, "hi");
//			ArrayList<Song> SearchSong = melonsearch.getSearchSong(1, 10, "hi");
//			ArrayList<Artist> SearchArtist = melonsearch.getSearchArtist(1, 10, "버스커");
//			
//			MelonLatest melonlatest = new MelonLatest("a857db0d-38f7-3375-a5ad-4d4b306a8945");
//			ArrayList<Album> LatestAlbumList = melonlatest.getLatestAlbum(1,5);
//			ArrayList<Album> LatestAlbumByGenre = melonlatest.getLatestAlbumByGenre("DP0500", 1, 5);
//			ArrayList<Song> LatestSong = melonlatest.getLatestSong(1, 10);
//			ArrayList<Song> LastetSongByGenre = melonlatest.getLastetSongByGenre("DP0800", 1, 10);
//			
//			MelonChart melonchart = new MelonChart("a857db0d-38f7-3375-a5ad-4d4b306a8945");
//			ArrayList<Genre> GenreList = melonchart.getGenreList();
			ArrayList<Song> GenreChartList = melonchart.getGenreChart("DP0800",1,5);
//			ArrayList<Song> nonGenreChartList = melonchart.getnonGenreChart(1,5);
//			ArrayList<Song> dailyChartList = melonchart.getdailyChart(1,5);
//			ArrayList<Album> AlbumChartList = melonchart.getalbumChart(1,5);
//			ArrayList<Song> realTimeChartList = melonchart.getRealTimeChart(1,5);
//			
//			for(Song song : SearchSong) {
//				  Log.i("test",song.getSongId());
//				  Log.i("test",song.getSongName());
//				  Log.i("test",song.getArtistName());
//				  Log.i("test",String.valueOf(song.isHitSong()));
//			}
//			for(Album album : SearchAlbum) {
//				  Log.i("test",album.getRepSongName());
//				  Log.i("test",album.getAlbumName());
//				  Log.i("test",album.getTotalSongCount());
//				  Log.i("test",album.getIssueDate());
//				  Log.i("test",album.getAverageScore());
//				  Log.i("test",album.getArtistName());
//			}
//			for(Genre genre : GenreList) {
//				Log.i("test",genre.getGenreId());
//				Log.i("test",genre.getGenreName());
//				
//			}
//			for(Artist artist : SearchArtist) {
//				Log.i("test",artist.getGenreNames());
//				Log.i("test",artist.getActTypeName());
//				Log.i("test",artist.getSex());
//				Log.i("test",artist.getArtistId());
//				Log.i("test",artist.getArtistName());
//				Log.i("test",artist.getNationalityName());
//			}
		}
	}
}
