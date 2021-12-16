package com.yc.audioplayer.service;

import android.content.Context;

import com.yc.audioplayer.bean.AudioPlayData;
import com.yc.audioplayer.manager.AudioManager;

public interface AudioServiceProvider {

    /**
     * 初始化语音服务
     *
     * @param context {@link Context}
     */
    void init(Context context);

    /**
     * 是否已经初始化
     * true 是
     */
    boolean isInit();

    /**
     * 停止播放
     */
    void stop();

    /**
     * 暂停播放
     */
    void pause();

    /**
     * 恢复播放
     */
    void resume();

    /**
     * 恢复播放
     */
    void release();

    /**
     * 是否正在播放
     *
     * @return true 正在播放
     */
    boolean isPlaying();

    /**
     * 播放数据
     *
     * @param data {@link AudioPlayData}
     */
    void play(AudioPlayData data);

    /**
     * 播放tts
     *
     * @param tts tts文本
     */
    void playTts(String tts);

    /**
     * 播放音频资源
     *
     * @param rawId 资源文件
     */
    void playAudioResource(int rawId);

    /**
     * 监听单条PlayData播放状态
     * @param playStateListener 监听器
     */
    void setPlayStateListener(AudioManager.PlayStateListener playStateListener);

}
