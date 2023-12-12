package com.example.bluescreen;
import android.content.Context;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLES32; // Version 3.2 being used hence GLES32 -> OpenGL 3.0 + Android Extension Packet


import android.view.MotionEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.OnDoubleTapListener;



public class GLESView extends GLSurfaceView implements GLSurfaceView.Renderer, OnGestureListener, OnDoubleTapListener
{
    private final Context context;
    // Inside the class - but global compared to others
    // CLASS VARIABLES or CLASS FIELDS
    private GestureDetector gestureDetector;

    GLESView(Context drawingContext){
        super(drawingContext);

        context = drawingContext;

        setEGLContextClientVersion(3);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        // Object = giving memory
        gestureDetector = new GestureDetector(context, this, null, false);
        gestureDetector.setOnDoubleTapListener(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        String version = gl.glGetString(GL10.GL_VERSION);
        System.out.println("VVM:GL Version -"+version);

        String glslVersion = gl.glGetString(GLES32.GL_SHADING_LANGUAGE_VERSION);
        System.out.println("VVM:GLSL Version -"+glslVersion);
        initialize(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height){
        resize(width, height);
    }

    // we don't require GL10 but we need to write to respect signature of the method
    @Override
    public void onDrawFrame(GL10 unused){
        draw();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //
        // IMP in EVENT DRIVEN ARCHITECTURE, in Graphics we might not use it often
        int eventaction = event.getAction();
        if(!gestureDetector.onTouchEvent(event)){
            super.onTouchEvent(event);
        }
        return(true);
    }

    // Complete Override as we are implementing this class
    @Override
    public boolean onDoubleTap(MotionEvent e){
        System.out.println("VVM:Double Tap Pressed");

        // We are handling hence return true
        return(true);

        // return false if implement but dont handle it, body is needed anyways
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e){
        return(true);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e){
        System.out.println("VVM:Single Tap Pressed");
        return(true);
    }


    // on GESTURE LISTENER
    @Override
    public boolean onDown(MotionEvent e){
        return(true);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        return(true);
    }

    @Override
    public void onLongPress(MotionEvent e){
        System.out.println("VVM:Long Press");
        // No Return as VOID : haha remember this
    }

    @Override
    public void onShowPress(MotionEvent e){

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){

        uninitialize();

        // Place from on scoll to another scroll movement then 1st scroll point is made x -> 0, y-> 0
        System.out.println("VVM:On Scroll ::::: Exiting");
        System.exit(0);
        return(true);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e){
        return(true);
    }

    private void initialize(GL10 gl){
        GLES32.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
    }

    private void resize(int width, int height){
        GLES32.glViewport(0, 0, width, height);
    }

    private void draw(){
        GLES32.glClear(
                GLES32.GL_COLOR_BUFFER_BIT |
                        GLES32.GL_DEPTH_BUFFER_BIT
        );

        // SWAP BUFFER equivalent
        requestRender();
    }

    private void uninitialize(){

    }
}


