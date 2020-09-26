package com.software.builtup.Client.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.ClientMainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Client;
import com.software.builtup.model.FeedBack;
import com.software.builtup.repository.FeedBackRepository;

public class FeedBackActivity extends AppCompatActivity {

    EditText feedbackClient;
    Button submitFeedBackClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        feedbackClient = findViewById(R.id.edit_text_feedback_to_bh_c);
        submitFeedBackClient = findViewById(R.id.button_submit_feedback_c);

        submitFeedBackClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBack feedBack = new FeedBack();
                final SharedPref sharedPref = new SharedPref(FeedBackActivity.this);
                final Client loggedClient = sharedPref.clientLogger();

                String feedbackMessage = feedbackClient.getText().toString().trim();
                if(feedbackMessage.isEmpty() || feedbackMessage == ""){
                    Toast.makeText(FeedBackActivity.this, "Please fill the feedback form", Toast.LENGTH_SHORT).show();
                }else{
                    FeedBackRepository feedBackRepository = new FeedBackRepository(FeedBackActivity.this);
                    feedBack.setFeedBackID(feedBackRepository.IDEncoder(loggedClient.getClientID(),"-",feedbackMessage));
                    feedBack.setArchitectID("-");
                    feedBack.setClientID(loggedClient.getClientID());
                    feedBack.setFeedBackMessage(feedbackMessage);
                    String IDdrawed = feedBackRepository.insertTransaction(feedBack);
                    Intent intent = new Intent(FeedBackActivity.this, ClientMainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
            }
        });
    }
}
