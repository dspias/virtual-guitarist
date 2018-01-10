package com.example.pdspe.virtualguitarist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class developers extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        setTitle("Developer's");

        ImageView hasanF = (ImageView) findViewById(R.id.hasan_fb);
        hasanF.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/HasanFahim.it"));
                startActivity(intent);
            }
        });
        ImageView hasanG = (ImageView) findViewById(R.id.hasan_email);

        hasanG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "HasanFahim.it@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
                startActivity(Intent.createChooser(emailIntent, null));
            }
        });


        ImageView piasF = (ImageView) findViewById(R.id.pias_fb);
        piasF.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/sperrowRAAZ"));
                startActivity(intent);
            }
        });
        ImageView piasG = (ImageView) findViewById(R.id.pias_email);

        piasG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "pdsperrow95@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
                startActivity(Intent.createChooser(emailIntent, null));
            }
        });

        ImageView sirF = (ImageView) findViewById(R.id.sir_fb);

        sirF.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/sas2505.lu"));
                startActivity(intent);
            }
        });

        ImageView sirG = (ImageView) findViewById(R.id.sir_email);

        sirG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "sas2505@lus.ac.bd", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
                startActivity(Intent.createChooser(emailIntent, null));
            }
        });
    }
}
