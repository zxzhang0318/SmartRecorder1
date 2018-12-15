package com.example.zhang.smartrecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //test request from wty
    // test by master zongxian33
    private final String TAG = "smartrecord";
    private Button btn_record;
    private Button btn_stop;
    File soundFile;
    MediaRecorder mediaRecorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        btn_record = (Button) findViewById(R.id.start_record);
        btn_stop = (Button) findViewById(R.id.stop_record);

        btn_record.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_record:
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(MainActivity.this, "Not found SD Card", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    // 创建保存录音的音频文件;
                    soundFile = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/sound.amr");

                    mediaRecorder = new MediaRecorder();
                    // 设置录音的声音来源
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    // 设置录制声音的输出格式（必须在设置声音编码格式之前设置）
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    // 设置声音编码格式//
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setOutputFile(soundFile.getAbsolutePath());
                    mediaRecorder.prepare();
                    // 开始录音
                    mediaRecorder.start();

                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stop_record:
                if (mediaRecorder != null && soundFile.exists()) {
                    // 停止录音
                    mediaRecorder.stop();
                    // 释放资源
                    mediaRecorder.release();
                    mediaRecorder = null;
                }
                break;
            default:
                break;
        }
    }


}
