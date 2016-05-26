package me.moreshare.gamea;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class TBSurfaceView extends SurfaceView implements Callback, Runnable {

    private static final String TAG = TBSurfaceView.class.getSimpleName();
    private SurfaceHolder sfh;
    private Thread thread;
    private Canvas canvas;
    private Paint paint;

    public TBSurfaceView(Context context) {
        super(context);
        thread = new Thread(this);
        sfh = getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }

    private void draw() {
        try {
            canvas = sfh.lockCanvas();
            if (null != canvas) {
                canvas.drawColor(Color.WHITE);
                canvas.drawText("Time:" + System.currentTimeMillis(), 100, 100, paint);
            }
        } catch (Exception e) {
            Log.e(TAG, "exception in draw", e);
        } finally {
            if (null != canvas) {
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            draw();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                Log.e(TAG, "exception in Thread.sleep", e);
            }
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
