package com.wxj.mdnote.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.wxj.mdnote.R;

/**
 * 具有 宽高自动适配的 控件
 *
 * @author wxj
 */
public class Ratiolayout extends FrameLayout {
    // 宽高比例
    float ratio = 2.43f; // 宽 / 高

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public Ratiolayout(Context context) {
        super(context);
    }

    public Ratiolayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Ratiolayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //获取   解析自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Ratiolayout);

//	    <!-- 声明新的属性 -->
//	    <declare-styleable name="Ratiolayout">  Ratiolayout _ratio
//	        <attr name="ratio" format="float" />
//	    </declare-styleable>

//		array.getFloat(0, defValue)
        ratio = array.getFloat(R.styleable.Ratiolayout_ratio, ratio);
        //  回收资源
        array.recycle();
    }

    /**
     * 父控件 是有责任 测量子控件
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // widthMeasureSpec // 宽度的测量规则 模式+ size
        // heightMeasureSpec 高度的测量规则
        // 模式
        // MeasureSpec.AT_MOST ;// wrap_content
        // MeasureSpec.EXACTLY;// 40dp match_parent;
        // MeasureSpec.UNSPECIFIED ;// 未指定
        // 重新 指定测量规则 ----- 尺子
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec)
                - getPaddingLeft() - getPaddingRight();
        int heightSize = MeasureSpec.getSize(heightMeasureSpec)
                - getPaddingTop() - getPaddingBottom();

        // 谁的模式是精确的 就用谁
        // 宽是精确的 高不是精确的
        if (widthMode == MeasureSpec.EXACTLY
                && heightMode != MeasureSpec.EXACTLY) {
            heightSize = (int) (widthSize / ratio + 0.5); // 3.4 ---- 3 3.2 +
            // 0.5 --->3 3.6
            // +0.5 --->4 +0.5
            // 四舍五入更接近
        } else if (widthMode != MeasureSpec.EXACTLY
                && heightMode == MeasureSpec.EXACTLY) { // 如果 宽不是精确的 高是精确的
            widthSize = (int) (heightSize * ratio + 0.5);
        }

        // 指定测量规则
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                widthSize + getPaddingLeft() + getPaddingRight(), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                heightSize + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
