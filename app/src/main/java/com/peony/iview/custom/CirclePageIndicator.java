package com.peony.iview.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.peony.iview.app.R;

/**
 * Created by wdynetposa on 14-8-7.
 */
public class CirclePageIndicator extends View implements IPageIndicator {

    private ViewPager _viewPager;
    private Bitmap _greyBitmap;
    private Bitmap _greenBitmap;

    private OnPageListener _pageListener = null;

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode())
            return;
        _greyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.point_gray);
        _greenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.point_green);
        _radius = _greenBitmap.getWidth();
    }

    @Override
    public void setPageListener(OnPageListener listener) {
        _pageListener = listener;
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (_viewPager == view) {
            return;
        }
        if (_viewPager != null) {
            _viewPager.setOnPageChangeListener(null);
        }
        if (view.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        _viewPager = view;
        _viewPager.setOnPageChangeListener(this);
        invalidate();
    }

    @Override
    public void setCurrentItem(int item) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        _currentPosition = position;
        invalidate();
    }

    private boolean misScrolled;

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                misScrolled = false;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                misScrolled = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (_viewPager.getCurrentItem() == _viewPager.getAdapter()
                        .getCount() - 1 && !misScrolled) {
                    if (_pageListener != null)
                        _pageListener.OnBoundaryMoveEvent();
                }
                misScrolled = true;
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (_orientation == LinearLayout.HORIZONTAL) {
            setMeasuredDimension(measureLong(widthMeasureSpec), measureShort(heightMeasureSpec));
        } else {
            setMeasuredDimension(measureShort(widthMeasureSpec), measureLong(heightMeasureSpec));
        }
    }

    private int measureLong(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if ((specMode == MeasureSpec.EXACTLY) || (_viewPager == null)) {
            //We were told how big to be
            result = specSize;
        } else {
            //Calculate the width according the views count
            final int count = _viewPager.getAdapter().getCount();
            result = (int) (getPaddingLeft() + getPaddingRight()
                    + (count * 2 * _radius) + (count - 1) * _radius + 1);
            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureShort(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            //We were told how big to be
            result = specSize;
        } else {
            //Measure the height
            result = (int) (2 * _radius + getPaddingTop() + getPaddingBottom() + 1);
            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int _currentPosition;
    private final Paint _paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int _orientation = LinearLayout.HORIZONTAL;
    private boolean _isCentered = true;
    private float _radius;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (_viewPager == null) {
            return;
        }

        final int count = _viewPager.getAdapter().getCount();
        if (count == 0) {
            return;
        }

        int longSize;
        int longPaddingBefore;
        int longPaddingAfter;
        int shortPaddingBefore;
        if (_orientation == LinearLayout.HORIZONTAL) {
            longSize = getWidth();
            longPaddingBefore = getPaddingLeft();
            longPaddingAfter = getPaddingRight();
            shortPaddingBefore = getPaddingTop();
        } else {
            longSize = getHeight();
            longPaddingBefore = getPaddingTop();
            longPaddingAfter = getPaddingBottom();
            shortPaddingBefore = getPaddingLeft();
        }

        final float rangeRadius = _radius * 2;
        final float shortOffset = shortPaddingBefore + _radius;
        float longOffset = longPaddingBefore + _radius;
        if (_isCentered) {
            longOffset += ((longSize - longPaddingBefore - longPaddingAfter) / 2.0f) - ((count * rangeRadius) / 2.0f);
        }

        float dX;
        float dY;

        for (int iLoop = 0; iLoop < count; iLoop++) {
            float drawLong = longOffset + (iLoop * rangeRadius);
            if (_orientation == LinearLayout.HORIZONTAL) {
                dX = drawLong;
                dY = shortOffset;
            } else {
                dX = shortOffset;
                dY = drawLong;
            }

            if (iLoop == _currentPosition) {
                canvas.drawBitmap(_greenBitmap, dX, dY, _paintStroke);
            } else {
                canvas.drawBitmap(_greyBitmap, dX, dY, _paintStroke);
            }
        }
    }
}
