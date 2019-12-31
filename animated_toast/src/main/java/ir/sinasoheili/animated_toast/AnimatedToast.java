package ir.sinasoheili.animated_toast;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimatedToast extends Toast
{
    private Context context;
    private View main_view;
    private int view_resource = R.layout.toast_view;
    private String text = null;
    private int image_id = -1;
    private int duration_animation_textview = 1000;

    //CONSTRUCTOR ----------------------------------------------------------------------------------
    public AnimatedToast(Context context)
    {
        super(context);
        this.context = context;

        //set view
        init_view(context);
        this.setView(main_view);

        //set text
        this.setText(null);

        //set image
        this.set_image(-1);

        //set duration
        this.setDuration(Toast.LENGTH_LONG);
    }

    public AnimatedToast(Context context , String text)
    {
        super(context);
        this.context = context;

        //set view
        init_view(context);
        this.setView(main_view);

        //set text
        this.setText(text);

        //set image
        this.set_image(-1);

        //set duration
        this.setDuration(Toast.LENGTH_LONG);
    }

    public AnimatedToast(Context context , int text_id)
    {
        super(context);
        this.context = context;

        //set view
        init_view(context);
        this.setView(main_view);

        //set text
        this.setText(text_id);

        //set image
        this.set_image(-1);

        //set duration
        this.setDuration(Toast.LENGTH_LONG);
    }

    public AnimatedToast(Context context , String text , int image_src_id)
    {
        super(context);
        this.context = context;

        //set view
        init_view(context);
        this.setView(main_view);

        //set text
        this.setText(text);

        //set image view
        this.set_image(image_src_id);

        //set duration
        this.setDuration(Toast.LENGTH_LONG);
    }

    public AnimatedToast(Context context , int text_id , int image_src_id)
    {
        super(context);
        this.context = context;

        //set view
        init_view(context);
        this.setView(main_view);

        //set text
        this.setText(text_id);

        //set image view
        this.set_image(image_src_id);

        //set duration
        this.setDuration(Toast.LENGTH_LONG);
    }

    //INIT VIEW ------------------------------------------------------------------------------------
    private void init_view(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        main_view = inflater.inflate(view_resource , null , false);
    }

    //SET TEXT -------------------------------------------------------------------------------------
    @Override
    public void setText(CharSequence s)
    {
        this.text = (String) s;
    }

    @Override
    public void setText(int resId)
    {
        try
        {
            this.text = context.getString(resId);
        }
        catch (Exception e)
        {
            this.text = "";
        }
    }


    //SET IMAGE ------------------------------------------------------------------------------------
    public void set_image(int image_res_id)
    {
        this.image_id = image_res_id;
    }

    //GETTER ---------------------------------------------------------------------------------------
    @Override
    public View getView()
    {
        return main_view;
    }

    //ANIMATION ------------------------------------------------------------------------------------
    private void anim_scale_imageView()
    {
        if(main_view != null)
        {
            ImageView iv = main_view.findViewById(R.id.toast_image_view);
            if(image_id == -1)
            {
                iv.setVisibility(View.GONE);
            }
            else
            {
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(image_id);

                Animation anim = AnimationUtils.loadAnimation( context , R.anim.scale_image);
                iv.startAnimation(anim);
            }
        }
    }

    private void anim_textView()
    {
        if(main_view != null)
        {
            final TextView tv = main_view.findViewById(R.id.toast_text_view);
            if((this.text == null) || (this.text.isEmpty() == true))
            {
                tv.setVisibility(View.GONE);
            }
            else
            {
                tv.setVisibility(View.VISIBLE);

                Rect rect = new Rect();
                tv.getPaint().getTextBounds(text , 0 , text.length() , rect);

                int min_width = 0;
                int max_width = (int) (rect.width()+tv.getTextSize());

                tv.getLayoutParams().width = min_width ;

                ValueAnimator vanimator = ValueAnimator.ofInt(0 , max_width);
                vanimator.setDuration(duration_animation_textview);
                vanimator.setStartDelay(500+200); //500 is duration of scale animation for ImageView
                vanimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        tv.getLayoutParams().width = (int) animation.getAnimatedValue();
                        tv.requestLayout();
                    }
                });
                vanimator.start();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tv.setText(text);
                    }
                } , duration_animation_textview+500+200); //duration_animation_textview+500+200 are all durartion and delay of TextView animation
            }
        }
    }

    // SET DURATION --------------------------------------------------------------------------------
    @Override
    public void setDuration(int duration)
    {
        super.setDuration(Toast.LENGTH_LONG);
    }

    //SHOW AND CANCEL ------------------------------------------------------------------------------
    @Override
    public void cancel()
    {
        super.cancel();
    }


    @Override
    public void show()
    {
        anim_scale_imageView();
        anim_textView();

        super.show();
    }

}
