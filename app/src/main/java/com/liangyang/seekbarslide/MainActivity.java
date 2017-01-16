package com.liangyang.seekbarslide;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 创建日期：2017/1/10 on 15:34
 * 作者:杨亮 liangyang
 * 描述:SeekBar的使用、仿淘宝滑动验证
 */
public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView mTextView;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        mTextView = (TextView) findViewById(R.id.tv);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        //SeekBar的滑动监听事件
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    /**
     * SeekBar进度变化时回调
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getProgress() == seekBar.getMax()){
            mTextView.setVisibility(View.VISIBLE);//设置TextView可见
            mTextView.setTextColor(Color.WHITE);
            mTextView.setText("完成验证");
            mSeekBar.setThumb(getResources().getDrawable(R.drawable.pay_success));
            createDialog();
        }else {
            mTextView.setVisibility(View.INVISIBLE);//设置TextView不可见
            mSeekBar.setThumb(getResources().getDrawable(R.drawable.thumb));
        }

    }

    /**
     * 创建Dialog
     */
    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.guide_home_on)
                .setTitle("提示")
                .setMessage("是否确认登录？")
                .setCancelable(false)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent intent = new Intent(MainActivity.this,LoginInActivity.class);
                        startActivity(intent);
                        restoreProgress();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        restoreProgress();
                    }
                });
        builder.create().show();
//        AlertDialog dialog = builder.create();
//        dialog.show();

    }

    /**
     * 还原ProgressBar
     */
    private void restoreProgress() {
        mSeekBar.setProgress(0);
        mTextView.setVisibility(View.VISIBLE);
        mTextView.setTextColor(Color.GRAY);
        mTextView.setText("请按住滑块拖动到最右边，完成验证！");
        mSeekBar.setThumb(getResources().getDrawable(R.drawable.thumb));
    }

    /**
     * SeekBar开始触摸时回调
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * SeekBar停止触摸时回调(开始滑动)
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != seekBar.getMax()){
            seekBar.setProgress(0);
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setTextColor(Color.GRAY);
            mTextView.setText("请按住滑块拖动到最右边，完成验证！");
        }

    }
}
