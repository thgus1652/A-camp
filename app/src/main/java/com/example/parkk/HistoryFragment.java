package com.example.parkk;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class HistoryFragment extends Fragment {
    private Context mContext;

    private MaterialCalendarView materialCalendarView;
    private ImageButton callButton;
    private ImageButton mailButton;
    private Toolbar toolbar;


    public HistoryFragment(Context context){
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_history, null);

        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        callButton = (ImageButton) view.findViewById(R.id.callButton);
        mailButton = (ImageButton) view.findViewById(R.id.mailButton);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        //Set Toolbar
        if(!isHidden()) {
            TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbar_title.setText("HISTORY");

        }
        //Text color of Date is changed to white
        materialCalendarView.setDateTextAppearance(R.style.CustomTextAppearance);

        //Set the call feature
        callButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                1000);
                        return;
                    }
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2737-2598")));

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        return view;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
