package com.example.zloiy.marriage_agency;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ZloiY on 25-Sep-16.
 */
public class MailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_layout);
        setTitle("Введите текст сообщения:");
        final EditText mailText = (EditText)findViewById(R.id.mail_text);
        Button sendBtn = (Button)findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"norfolf1000@gmail.com"});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "app_test");
                mailIntent.putExtra(Intent.EXTRA_TEXT, mailText.getText().toString());
                try{
                    startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(MailActivity.this, "No email clients install.", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
