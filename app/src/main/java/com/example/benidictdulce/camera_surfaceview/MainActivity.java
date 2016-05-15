package com.example.benidictdulce.camera_surfaceview;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

   public CameraSurfaceView cameraSurfaceView = null;
   public   Camera camera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            camera = Camera.open();// user open(int) to use different camera
        }catch (Exception e){
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if (camera != null){
            cameraSurfaceView = new CameraSurfaceView(this, camera);//create surface view
            FrameLayout frame = (FrameLayout) findViewById(R.id.cam_camera);
            frame.addView(cameraSurfaceView);//add surface view to frame layout
        }

    }
}
