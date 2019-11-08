package com.depromeet.android.main.view;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.depromeet.android.R;

import androidx.core.content.ContextCompat;

public class DragDropOnTouchListener implements View.OnTouchListener {

    private Context context;

    public DragDropOnTouchListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // Get view object tag value.
        String tag = (String)view.getTag();

        // Create clip data.
        ClipData clipData = ClipData.newPlainText("", tag);

        // Create drag shadow builder object.
        View.DragShadowBuilder dragShadowBuilder = new CustomShadowBuilder(view);


        /* Invoke view object's startDrag method to start the drag action.
           clipData : to be dragged data.
           dragShadowBuilder : the shadow of the dragged view.
        */
        view.startDrag(clipData, dragShadowBuilder, view, 0);


        // Hide the view object because we are dragging it.
        //view.setVisibility(View.INVISIBLE);

        return true;
    }

    class CustomShadowBuilder extends View.DragShadowBuilder{
        int mWidth, mHeight;

        public CustomShadowBuilder(View v){
            super(v);
            mWidth = v.getWidth();//좌표를 가져와서 멤버 변수에 넣어둠.
            mHeight = v.getHeight();//좌표를 가져와서 멤버 변수에 넣어둠.
        }

        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint){
            //섀도우의 크기와 중심점의 좌표를 지정하는 메소드
            shadowSize.set(mWidth, mHeight);//사이즈 지정
            shadowTouchPoint.set(mWidth/2, mHeight/2);//중심점 지정
        }

        //섀도우 이미지를 그릴때 이 메소드가 호출됨
        public void onDrawShadow(Canvas canvas){
            Paint paint = new Paint();
            Resources res = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.baby_face_clicked);
            canvas.drawBitmap(bitmap, 0, 0, paint);
        }
    }
}
