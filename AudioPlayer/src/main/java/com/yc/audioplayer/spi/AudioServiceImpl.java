package com.yc.audioplayer.spi;

import android.content.Context;

import com.yc.audioplayer.dispatch.AudioTaskDispatcher;
import com.yc.audioplayer.bean.AudioPlayData;
import com.yc.audioplayer.manager.AudioManager;
import com.yc.videotool.VideoLogUtils;

public class AudioServiceImpl implements AudioServiceProvider {

    private AudioManager mAudioManager;
    private final AudioTaskDispatcher mAudioTaskDispatcher = AudioTaskDispatcher.getInstance();
    private boolean mReady = false;
    private Context mContext;

    @Override
    public void init(Context context) {
        mAudioManager = new AudioManager(context);
        mAudioManager.init(mAudioTaskDispatcher, context);
        mAudioTaskDispatcher.initialize(mAudioManager);
        mContext = context;
        mReady = true;
    }

    @Override
    public boolean isInit() {
        return mReady;
    }

    @Override
    public void stop() {
        if (mReady) {
            mAudioManager.stop();
        }
    }

    @Override
    public void pause() {
        if (mReady) {
            mAudioManager.pause();
        }
    }

    @Override
    public void resume() {
        if (mReady) {
            mAudioManager.resumeSpeaking();
        }
    }

    @Override
    public void release() {
        mAudioManager.release();
        mAudioTaskDispatcher.release();
        mReady = false;
    }

    @Override
    public boolean isPlaying() {
        return mReady && mAudioManager.isPlaying();
    }

    @Override
    public void play(AudioPlayData data) {
        if (data == null) {
            return;
        }
        if (!mReady) {
            VideoLogUtils.d("audio not init!");
            return;
        }
        mAudioTaskDispatcher.addTask(data);
    }

    @Override
    public void playTts(String tts) {
        AudioPlayData data = new AudioPlayData
            .Builder()
            .tts(tts)
            .build();
        play(data);
    }

    @Override
    public void playAudioResource(int rawId) {
        AudioPlayData data = new AudioPlayData
            .Builder()
            .rawId(rawId)
            .build();
        play(data);
    }

    @Override
    public void setPlayStateListener(AudioManager.PlayStateListener playStateListener) {
        if (null != mAudioManager) {
            mAudioManager.setPlayStateListener(playStateListener);
        }
    }
}