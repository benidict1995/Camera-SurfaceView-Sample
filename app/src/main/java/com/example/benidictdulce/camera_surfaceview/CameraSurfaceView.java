package com.example.benidictdulce.camera_surfaceview;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Benidict Dulce on 5/15/2016.
 */
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    Camera cam;
  private  SurfaceHolder surfaceHolder;

    public CameraSurfaceView(Context context, Camera camera){
        super(context);

        cam = camera;
        cam.setDisplayOrientation(90); // integer, orientation set on 90 degrees

        surfaceHolder = getHolder(); //get holder
        surfaceHolder.addCallback(this);//set this class as callback
        //surfaceHolder.setFormat(SurfaceHolder.SURFACE_TYPE_NORMAL);//you can use also the .setType
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            try {
                cam.setPreviewDisplay(holder);//set camera in surface holder
                cam.startPreview(); // start
            }catch (IOException e){
                Log.d("ERROR!", "Camera error on surfaceCreated " + e.getMessage());
            }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (holder.getSurface() == null)//check if surface is ready
                return;
        try {//if the orientation change the preview need to stop, after rotate we can start it again
            cam.stopPreview(); //camera stop
        }catch (Exception e){
            // e.printStackTrace();
        }

        try {//after the rotation
            //recreate cam
            cam.setPreviewDisplay(holder); //camera in surface holder again
            cam.startPreview(); // start
        }catch (IOException e){
            Log.d("ERROR!", "Camera error on surfaceChanged " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        cam.stopPreview();
        cam.release();
    }
}
