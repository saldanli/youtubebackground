package sirac.com.youtubebackground

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import java.io.BufferedReader
import android.webkit.ValueCallback
import android.widget.Button
import sirac.com.youtubebackground.model.ItemType
import sirac.com.youtubebackground.model.YouTubeVideo
import sirac.com.youtubebackground.utils.Config
import sirac.com.youtubebackground.utils.NetworkConf
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private val TAG="YOUTUBEBACKGROUND"
    lateinit  var networkConf:NetworkConf
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        networkConf = NetworkConf(this)

       // val webView=findViewById(R.id.webViewId) as WebView
       // webView.settings.javaScriptEnabled=true

        testBackGroundService("4LbVOCTKs18&t=2936s")



//        webView.webViewClient=object:WebViewClient(){
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                injectJS()
//            }
//
//
//            private fun injectJS() {
//                try {
//                    val inputStream = assets.open("style.js")
//                    inputStream.bufferedReader().use(BufferedReader::readText)
//                } catch (e: Exception) {
//                    null
//                }?.let { webView.loadUrl("javascript:($it)()")  }
//            }
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.evaluateJavascript("(function() { ytplayer = document.getElementById(\"movie_player\");" +
//                    "return ytplayer.getCurrentTime();; })();") { s ->
//                Log.d(TAG, s) // Prints "" (Two double quotes)
//            }
//        };
     //    webView.loadUrl("https://www.youtube.com")
    testBackGroundService("TSWUEaoqcq8")
    }

    fun testBackGroundService(videoId:String){
        val video= YouTubeVideo(videoId);
        onVideoSelected(video)
    }

    fun onVideoSelected(video: YouTubeVideo) {
        if (!networkConf.isNetworkAvailable()) {
            networkConf.createNetErrorDialog()
            return
        }
        val serviceIntent = Intent(this, BackgroundAudioService::class.java)
        serviceIntent.setAction(BackgroundAudioService.ACTION_PLAY)
        serviceIntent.putExtra(Config.YOUTUBE_TYPE, ItemType.YOUTUBE_MEDIA_TYPE_VIDEO)
        serviceIntent.putExtra(Config.YOUTUBE_TYPE_VIDEO, video)
        startService(serviceIntent)
    }

    fun onPlaylistSelected(playlist: List<YouTubeVideo>, position: Int) {
        if (!networkConf.isNetworkAvailable()) {
            networkConf.createNetErrorDialog()
            return
        }
        val serviceIntent = Intent(this, BackgroundAudioService::class.java)
        serviceIntent.setAction(BackgroundAudioService.ACTION_PLAY)
        serviceIntent.putExtra(Config.YOUTUBE_TYPE, ItemType.YOUTUBE_MEDIA_TYPE_PLAYLIST)
        serviceIntent.putExtra(Config.YOUTUBE_TYPE_PLAYLIST, playlist as ArrayList<*>)
        serviceIntent.putExtra(Config.YOUTUBE_TYPE_PLAYLIST_VIDEO_POS, position)
        startService(serviceIntent)
    }
}
