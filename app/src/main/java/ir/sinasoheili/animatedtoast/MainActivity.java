package ir.sinasoheili.animatedtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;

import ir.sinasoheili.animated_toast.AnimatedToast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create an animated toast and show that
        AnimatedToast t = new AnimatedToast(this , "Animated Toast" , R.drawable.home);
        t.setGravity(Gravity.CENTER_VERTICAL , 0 , 0 );
        t.show();
    }
}
