package com.example.parkk;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainFragment extends Fragment {
    private Context mContext;
    private View rootView;
    private Toolbar toolbar;
    private int mProgressStatus = 0;
    private ProgressBar progressBar;
    private Handler mHandler = new Handler();

    public MainFragment(Context context){
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        //Set Toolbar
        if(!isHidden()) {
            TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbar_title.setText("MAIN");
        }

        //
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        new Thread(new Runnable(){
            public void run(){

                while (mProgressStatus < 20 ) {
                    mProgressStatus += 1;
                    mHandler.post(new Runnable(){
                        public void run() {
                            progressBar.setProgress(mProgressStatus);
                        }
                    });
                    try {
                        Thread.sleep(50);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return rootView;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
