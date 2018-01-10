package com.example.pdspe.virtualguitarist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UsingInformation extends AppCompatActivity {

    TextView demoLink, detaisView;
    boolean isAlive;
    LinearLayout detailsLayout;
    Button back,details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_information);

        setTitle("How to use our App");

        TextView tv=(TextView)findViewById(R.id.shortInfo);
        tv.setText(Html.fromHtml(getString(R.string.using_play_guitar)));

        demoLink = (TextView) findViewById(R.id.youtubelink);
        Spanned text = Html.fromHtml("<a href='https://www.youtube.com/watch?v=_TDJQ3c_Wl4'>Demo Video</a>");

        demoLink.setMovementMethod(LinkMovementMethod.getInstance());
        demoLink.setText(text);
        isAlive = true;
        detailsLayout = (LinearLayout) findViewById(R.id.detalis_Layout);



        details = (Button) findViewById(R.id.detailsButton);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive == true){
                    detailsLayout.setVisibility(View.VISIBLE);
                    details.setText("<< close");
                } else {
                    detailsLayout.setVisibility(View.GONE);
                    details.setText("Details >>");
                }
                isAlive =! isAlive;
            }
        });

        detaisView = (TextView) findViewById(R.id.Detalis);
        detaisView.setText(Html.fromHtml(getString(R.string.details_using_info)));
        back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsLayout.setVisibility(View.GONE);
                details.setText("Details >>");
            }
        });




    }
}
