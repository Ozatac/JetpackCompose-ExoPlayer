package com.tunahanozatac.exoplayerexample

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.tunahanozatac.exoplayerexample.ui.theme.ExoPlayerExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExoPlayerExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Scaffold(topBar = {
                        TopAppBar(title = {
                            Text(
                                "ExoPlayer Video", color = Color.White
                            )
                        })
                    }, content = { VideoPlay() })
                }
            }
        }
    }
}

@Composable
fun VideoPlay() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val mContext = LocalContext.current
        val mExoPlayer = remember(mContext) {
            ExoPlayer.Builder(mContext).build().apply {
                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
                    .build()
                setMediaItem(mediaItem)
                playWhenReady = true
                prepare()
            }
        }

        AndroidView(factory = { context ->
            StyledPlayerView(context).apply {
                player = mExoPlayer
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExoPlayerExampleTheme {
        VideoPlay()
    }
}