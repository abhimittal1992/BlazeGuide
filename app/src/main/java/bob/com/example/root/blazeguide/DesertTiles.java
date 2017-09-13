package bob.com.example.root.blazeguide;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.TreeSet;

import bob.com.example.root.blazeguide.service.AlarmService;

import static bob.com.example.root.blazeguide.R.drawable.e;

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
        collapsingToolbar.setTitle("Desert Tiles Refresh");

        TextView time1 = (TextView) findViewById(R.id.time1);
        TextView time2 = (TextView) findViewById(R.id.time2);

        TextView time3 = (TextView) findViewById(R.id.time3);

        TextView time4 = (TextView) findViewById(R.id.time4);

        TextView time5 = (TextView) findViewById(R.id.time5);

        TextView time6 = (TextView) findViewById(R.id.time6);
        Button clock = (Button) findViewById(R.id.clock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openClockIntent);
            }
        });


        SharedPreferences sharedPrefs = getSharedPreferences("bob.com.example.root.blazeguide", MODE_PRIVATE);
    //    desertSwitch.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", false));


        DateFormat df7 = new SimpleDateFormat("HH:mm");

        try {
            time1.setText(df7.format(gmttoLocalDate(getDateFromString("02:10"))));
            time2.setText(df7.format(gmttoLocalDate(getDateFromString("06:10"))));

            time3.setText(df7.format(gmttoLocalDate(getDateFromString("10:10"))));

            time4.setText(df7.format(gmttoLocalDate(getDateFromString("14:10"))));

            time5.setText(df7.format(gmttoLocalDate(getDateFromString("18:10"))));

            time6.setText(df7.format(gmttoLocalDate(getDateFromString("22:10"))));


        }   catch(Exception e){

        }
       /* desertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });*/
    }

    public static Long getCurrentUTCTIME(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return cal.getTimeInMillis();
    }

    public Date getDateFromString(String dateStr){
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            Date startDate = (Date)formatter.parse(dateStr);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date gmttoLocalDate(Date date) {

        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date local = new Date(date.getTime() + TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
        return local;
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
