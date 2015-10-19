package edu.cascadia.brianb.mydrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Edited by Brian Bansenauer on 10/18/15.
 */
public class SimplyDrawnView extends View {

    private Paint mPaint;
    private Path mPath;
    private float mWidth, mHeight;

    public SimplyDrawnView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        mPaint = new Paint();
        mPath = new Path();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE); //draw background

        //Draw line
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(mWidth, 0, 200, mHeight, mPaint);

        //Draw red line
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(mWidth / 2, mHeight / 2, mWidth, mHeight, mPaint);

        //Draw green lines
        mPaint.setColor(Color.GREEN);
        final int SPACER = ((int) ((mHeight / 2) / 4) - 25); // int for spacing on lines
        for (int idx = 0; idx < 5; ++idx) {
            mPaint.setAlpha(255 - (idx * 60));
            canvas.drawLine(0, (idx * SPACER) + 25, mWidth, (idx * SPACER) + 25, mPaint);
        }

        //Draw Text
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(45);
        canvas.drawText(getResources().getString(R.string.drawingText), 0, mHeight / 2, mPaint);

        //Draw Text on a Path
        mPaint.setColor(Color.CYAN);
        RectF rect = new RectF(mWidth / 3, (mHeight / 2 ) - 185, mWidth - 25, (mHeight / 2) + 185);
        mPath.addOval(rect, Path.Direction.CW);
        canvas.drawTextOnPath(getResources().getString(R.string.pathText), mPath, 950, 20, mPaint);

        //Draw filled, opaque, and open ovals
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        canvas.drawCircle((mWidth / 2), (mHeight / 2), 15, mPaint);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setAlpha(185);
        canvas.drawCircle((mWidth / 2), (mHeight / 2), 155, mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle((mWidth / 2), (mHeight / 2), 160, mPaint);

        //Draw bee bitmap
        Bitmap bee = BitmapFactory.decodeResource(getResources(), R.drawable.bee);
        bee = bee.createScaledBitmap(bee, bee.getWidth() * 2, bee.getHeight() * 2, false);
        canvas.drawBitmap(bee, 0, 0, null);
        // Drawable b = getResources().getDrawable(R.drawable.bee,null);

    }
}
