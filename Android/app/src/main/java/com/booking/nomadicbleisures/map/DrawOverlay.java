package com.booking.nomadicbleisures.map;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawOverlay extends View {

    private Paint paint;
    private Path path;
    private final String TAG = "DrawOverlay";
    private ArrayList<Point> coordinates;
    private MapDrawComplete interfaceDrawComplete;
    private boolean polygon = false;

    private final double MAX_DISPLACEMENT_FOR_POLYGON = 200;
    private final double MIN_SLOP_DIFF = 0.57735;

    private final int MIN_POINTS_THRESHOLD = 15;
    private final int MIN_FACTOR = 140;

    private int MIN_X;
    private int MAX_X;
    private int MIN_Y;
    private int MAX_Y;

    boolean firstRun = true;

    // Circle Draw Variables
    private ValueAnimator mAnimator;
    private Path circularPath;
    private float mCenterX;
    private float mCenterY;
    private boolean circleAnimationRunning = false;
    private int width;

    public DrawOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        coordinates = new ArrayList<Point>();
        initPaint(context);
    }

    public DrawOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        path = new Path();
        coordinates = new ArrayList<Point>();
        initPaint(context);
    }

    public DrawOverlay(Context context) {
        super(context);
        path = new Path();
        coordinates = new ArrayList<Point>();
        initPaint(context);
    }

    public void setMapDrawComplete(MapDrawComplete mapDrawComplete) {
        interfaceDrawComplete = mapDrawComplete;
    }

    private void initPaint(Context context) {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12f);
        paint.setAlpha(255);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (circularPath != null && circleAnimationRunning)
            canvas.drawPath(circularPath, paint);
        else if (!circleAnimationRunning) {
            canvas.drawPath(path, paint);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        float eventX = event.getX();
        float eventY = event.getY();

        if (event.getPointerCount() > 1 && !circleAnimationRunning) {
            path = new Path();
            coordinates.clear();
            invalidate();
            return true;
        }

        switch (action) {

            case MotionEvent.ACTION_DOWN:

                if (!circleAnimationRunning) {
                    path = new Path();
                    path.moveTo(eventX, eventY);
                    coordinates.clear();
                    coordinates.add(new Point((int) eventX, (int) (eventY - this.getY())));
                    MIN_X = (int) eventX;
                    MAX_X = (int) eventX;
                    MIN_Y = (int) eventY;
                    MAX_Y = (int) eventY;
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (!circleAnimationRunning) {
                    int x = (int) eventX;
                    int y = (int) eventY;

                    if (x < MIN_X)
                        MIN_X = x;
                    else if (x > MAX_X)
                        MAX_X = x;


                    if (y < MIN_Y)
                        MIN_Y = y;
                    else if (y < MAX_Y)
                        MAX_Y = y;


                    path.lineTo(eventX, eventY);
                    coordinates.add(new Point((int) eventX, (int) (eventY - this.getY())));
                }
                break;

            case MotionEvent.ACTION_UP:
                if (!circleAnimationRunning) {
                    int size = coordinates.size();
                    if (size > MIN_POINTS_THRESHOLD) {

                        Point a = coordinates.get(0);

                        Point b = coordinates.get(size - 1);
                        Point c = coordinates.get(size - 1 - 10);

                        Point X = new Point((b.x + a.x) / 2, (b.y + a.y) / 2);

                        double slopeAtX = getSlope(c, b);
                        double slopeForLineAX = getSlope(X, a);

                        double angleBetweenSlopeAndDisplacement = Math.PI / 2;
                        angleBetweenSlopeAndDisplacement = Math.atan2((slopeAtX - slopeForLineAX), (1 + (slopeAtX * slopeForLineAX)));

                        double displacement = Math.sqrt(distanceSquared(a, X));
                        double factor = Math.sin(angleBetweenSlopeAndDisplacement) * displacement;


                        if (Math.abs(factor) < MIN_FACTOR && displacement <= MAX_DISPLACEMENT_FOR_POLYGON) {
                            interfaceDrawComplete.onDrawComplete(coordinates, true, new Point((MIN_X + MAX_X) / 2, (MIN_Y + MAX_Y) / 2));
                        } else {
                            interfaceDrawComplete.onDrawComplete(coordinates, false, new Point((MIN_X + MAX_X) / 2, (MIN_Y + MAX_Y) / 2));
                        }


                    }
                    path = new Path();
                    coordinates.clear();
                }
                break;
        }

        invalidate();
        return true;
    }

    private double distanceSquared(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private double getSlope(Point a, Point b) {
        if (b.x != a.x)
            return (b.y - a.y) / (b.x - a.x);
        else
            return Double.MAX_VALUE;
    }

}
