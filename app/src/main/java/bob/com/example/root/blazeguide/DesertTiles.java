package bob.com.example.root.blazeguide;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.TreeSet;

import bob.com.example.root.blazeguide.service.AlarmService;

public class DesertTiles extends AppCompatActivity {

    PendingIntent broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }
        setContentView(R.layout.activity_desert_tiles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.desertToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.desert_collapsing_toolbar);
        collapsingToolbar.setTitle("Defending Tricks");

        Switch desertSwitch = (Switch) findViewById(R.id.desertSwitch);
        SharedPreferences sharedPrefs = getSharedPreferences("bob.com.example.root.blazeguide", MODE_PRIVATE);
        desertSwitch.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", false));

        desertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("bob.com.example.root.blazeguide", MODE_PRIVATE).edit();
                    editor.putBoolean("NameOfThingToSave", true);
                    editor.commit();
                    //on
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
                    notificationIntent.addCategory("android.intent.category.DEFAULT");
                    broadcast = PendingIntent.getBroadcast(DesertTiles.this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    Long utcTimm = getCurrentUTCTIME();
                    Toast.makeText(DesertTiles.this, "Notify Me", Toast.LENGTH_SHORT).show();

                    System.out.print("current UTC time"+utcTimm);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getClosesDesertTime(utcTimm), 1000* 60 * 60 *4, broadcast);
                } else {

                    SharedPreferences.Editor editor = getSharedPreferences("bob.com.example.root.blazeguide", MODE_PRIVATE).edit();
                    editor.putBoolean("NameOfThingToSave", false);
                    editor.commit();
                    alarmManager.cancel(broadcast);
                    // Tell the user about what we did.
                    Toast.makeText(DesertTiles.this, "Dont Notify Me", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public static Long getCurrentUTCTIME(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return cal.getTimeInMillis();
    }

    public static Long getClosesDesertTime(Long currentTime){
        TreeSet<Long> times = new TreeSet<>();
        times.add(parseStringToLongDate(02,10));
        times.add(parseStringToLongDate(06,10));
        times.add(parseStringToLongDate(10,10));
        times.add(parseStringToLongDate(14,10));
        times.add(parseStringToLongDate(18,10));
        times.add(parseStringToLongDate(22,10));
        Long closesttime = times.ceiling(currentTime);
        return closesttime ;
    }

    public static Long parseStringToLongDate(int h,int m){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, h);
        cal.set(Calendar.MINUTE, m);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return cal.getTimeInMillis();
    }
}
