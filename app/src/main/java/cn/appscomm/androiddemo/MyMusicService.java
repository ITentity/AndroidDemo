package cn.appscomm.androiddemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

public class MyMusicService extends Service {
    private static final String TAG = "MainService";
    private AudioManager mAm;
    private MediaPlayer player;
    private MyOnAudioFocusChangeListener mListener;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.test);  // 在res目录下新建raw目录，复制一个test.mp3文件到此目录下。
        player.setLooping(false);
        mAm = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        mListener = new MyOnAudioFocusChangeListener();
    }

    public MyMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "My Service Start", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onStart");

        // 申请音频焦点
        int result = mAm.requestAudioFocus(mListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
        Log.i(TAG, "onStart"+result);
        Log.i(TAG, "onStart"+AudioManager.AUDIOFOCUS_REQUEST_GRANTED);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
        {
            Log.i(TAG, "requestAudioFocus successfully.");

            // Start playback.
            player.start();
        }
        else
        {
            Log.e(TAG, "requestAudioFocus failed.");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "My Service Stoped", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onDestroy");
        // 放弃音频焦点
        mAm.abandonAudioFocus(mListener);
    }

    private class MyOnAudioFocusChangeListener implements AudioManager.OnAudioFocusChangeListener
    {
        @Override
        public void onAudioFocusChange(int focusChange)
        {
            Log.i(TAG, "focusChange=" + focusChange);
        }
    }
}
