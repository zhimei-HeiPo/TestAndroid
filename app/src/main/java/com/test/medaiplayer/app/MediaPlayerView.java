package com.test.medaiplayer.app;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HeiPo on 16/1/21.
 */
public class MediaPlayerView extends SurfaceView{

    private Context context;
    private MediaPlayer mp;
    private Uri uri;

    private int videoWidth;
    private int videoHeight;

    private ReentrantLock reentrantLock;
    private MediaPlayer.OnPreparedListener onPreparedListener = null;
    private MediaPlayer.OnCompletionListener onCompletionListener = null;
    private MediaPlayer.OnErrorListener onErrorListener = null;

    public MediaPlayerView(Context context) {
        super(context);
        this.context = context;
        reentrantLock = new ReentrantLock();
    }

    public MediaPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        reentrantLock = new ReentrantLock();
    }

    private MediaPlayer.OnPreparedListener preparedListener =
            new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            if(onPreparedListener != null){
                onPreparedListener.onPrepared(mediaPlayer);
            }
        }
    };

    private MediaPlayer.OnVideoSizeChangedListener videoSizeChangedListener =
            new MediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int w, int h) {
            videoWidth = mp.getVideoWidth();
            videoHeight = mp.getVideoHeight();

            if (videoWidth != 0 && videoHeight != 0) {
                getHolder().setFixedSize(videoWidth, videoHeight);
            }
        }
    };

    private MediaPlayer.OnCompletionListener completionListener =
            new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            if(onCompletionListener != null){
                onCompletionListener.onCompletion(mediaPlayer);
            }
        }
    };

    private MediaPlayer.OnErrorListener errorListener =
            new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            if(onErrorListener != null){
                onErrorListener.onError(mediaPlayer, i, i1);
            }
            return false;
        }
    };

    private MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener
            = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        }
    };

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener listener) {
        onPreparedListener = listener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        onCompletionListener = listener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener listener) {
        onErrorListener = listener;
    }

    public void SetDataSource(String path){
        uri = Uri.parse(path);
    }

    public void SetDataSource(Uri uri){
        this.uri = uri;
    }

    public boolean Play(){
        if(uri == null) {
            return false;
        }

        reentrantLock.lock();

        if(mp != null){
            mp.reset();
            mp.release();
            mp = null;
        }

        try {
            mp = new MediaPlayer();
            mp.setOnPreparedListener(preparedListener);
            mp.setOnVideoSizeChangedListener(videoSizeChangedListener);
            mp.setOnCompletionListener(completionListener);
            mp.setOnErrorListener(errorListener);
            mp.setOnBufferingUpdateListener(bufferingUpdateListener);
            mp.setDataSource(context, uri);
            mp.setDisplay(getHolder());
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setScreenOnWhilePlaying(true);
            mp.prepareAsync();
            reentrantLock.unlock();
            return true;
        } catch (IOException e) {
        }

        reentrantLock.unlock();
        return false;
    }


    public void Stop(){
        reentrantLock.lock();
        if (mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
        reentrantLock.unlock();
    }

    public void Pause(){
        reentrantLock.lock();
        if(mp != null && mp.isPlaying()){
            mp.pause();
        }
        reentrantLock.unlock();
    }

    public void SeekTo(int pos){
        reentrantLock.lock();
        if(mp != null){
            mp.seekTo(pos);
        }
        reentrantLock.unlock();
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public int getVideoHeight() {
        return videoHeight;
    }


}
