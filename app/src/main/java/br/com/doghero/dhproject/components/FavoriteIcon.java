package br.com.doghero.dhproject.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import br.com.doghero.dhproject.R;
import br.com.doghero.dhproject.util.ImageHelper;

public class FavoriteIcon extends AppCompatImageView implements View.OnClickListener {

    private Context mContext;

    private boolean mIsActive = false;

    public FavoriteIcon(Context context) {
        super(context);
        this.init(context, null);
    }

    public FavoriteIcon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public FavoriteIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        this.setOnClickListener(this);
        this.setImageDrawable(ImageHelper.getDrawableFromId(mContext, R.drawable.icon_like_border_vector_gray_battleship));
    }

    @Override
    public void onClick(View view) {
        Drawable drawable;

        this.mIsActive = !this.mIsActive;
        this.setActive(this.mIsActive);
    }

    public void setActive(boolean isActive) {
        Drawable drawable;
        this.mIsActive = isActive;

        if (isActive) {
            drawable = ImageHelper.getDrawableFromId(mContext, R.drawable.icon_like_border_vector_gray_battleship);
        } else {
            drawable = ImageHelper.getDrawableFromId(mContext, R.drawable.icon_like_filled_vector_red);
        }

        this.setImageDrawable(drawable);
    }
}
