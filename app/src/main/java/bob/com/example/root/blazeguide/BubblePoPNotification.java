package bob.com.example.root.blazeguide;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class BubblePoPNotification extends AppCompatActivity {

    PendingIntent broadcast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_bubble_po_pnotification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.bubblePoptoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.bubblePopCollapsableToolbar);
        collapsingToolbar.setTitle("Notify me when Bubble Pops");

        Button twoHourSheild =  (Button) findViewById(R.id.twohour);
        Button eigthHourSheild =  (Button) findViewById(R.id.eightHour);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");
        broadcast = PendingIntent.getBroadcast(BubblePoPNotification.this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        twoHourSheild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+  1000* 7200, broadcast);
                Toast.makeText(BubblePoPNotification.this, "You will get notify after 2 hours from now", Toast.LENGTH_SHORT).show();

            }
        });
        eigthHourSheild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+  1000* 28800, broadcast);
                Toast.makeText(BubblePoPNotification.this, "You will get notify after 8 hours from now", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
