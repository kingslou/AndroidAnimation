package com.example.androidanimation;

import android.content.ClipDescription;
import android.text.TextUtils;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

public class DragUtils {

    private static Object currentData;

    //用这一接口方便 两个view之间所在的调用
    public interface DragStatus {
        void complete(String obj);
    }

    public static void bindDragInZone(View view, final DragStatus impl) {
        final int[] dragPoint = new int[2];
        final boolean[] isIn = {false};
        final String[] localState = {""};
        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();

                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED://拖拽开始
                        localState[0] = (String) event.getLocalState();
                        Log.i("rex", "localState-----" + localState[0]);
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//                            shadowView.setBackgroundResource(R.drawable.xml_rect_line_bg);
                            return true;
                        }

                        return false;
                    case DragEvent.ACTION_DRAG_ENTERED://拖拽进入目标区域

                        Log.i("rex", "拖拽进入目标区域" + event.getX() + "----" + event.getY());
                        //ULog.e("拖拽进入目标区域");
                        isIn[0] = true;

                        if(event.getClipDescription()!=null &&event.getClipDescription().getLabel()!=null){
                            Log.i("rex", "拖拽进入目标区域" + event.getClipDescription().getLabel()+"");
                            currentData = event.getClipDescription().getLabel();
                        }

                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION://拖拽位置
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED://拖拽到目标区域外
                        isIn[0] = false;
                        currentData = "";
                        //ULog.e("拖拽到目标区域外");
                        return true;
                    case DragEvent.ACTION_DROP: //拖拽完成之后松开手指

                        //ULog.e("拖拽完成之后松开手指" + event.getX() + "----" + event.getY());
                        dragPoint[0] = (int) event.getX();
                        dragPoint[1] = (int) event.getY();

                        return true;
                    case DragEvent.ACTION_DRAG_ENDED://拖拽完成
                        if (isIn[0] && impl != null) {
                            impl.complete(currentData+"");
                        }
                        isIn[0] = false;
//                        shadowView.setBackgroundResource(0);
//                        if (!isIn) {
//                            return true;
//                        }

//                        findDragPostion(dragPoint[0], dragPoint[1]);
//                        isIn = false;

                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
