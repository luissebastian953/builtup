package com.software.builtup.Architect.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.ArchitectMainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.FeedBack;
import com.software.builtup.repository.FeedBackRepository;

public class ArchitectFeedbackActivity extends AppCompatActivity {

    EditText feedbackArchitect;
    Button submitFeedBackArchitect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_feedback);
        feedbackArchitect = findViewById(R.id.edit_text_feedback_a);
        submitFeedBackArchitect = findViewById(R.id.button_submit_feedback_a);

        submitFeedBackArchitect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBack feedBack = new FeedBack();
                final SharedPref sharedPref = new SharedPref(ArchitectFeedbackActivity.this);
                final Architect loggedArchitect = sharedPref.architectLogger();
                String feedbackMessage = feedbackArchitect.getText().toString().trim();
                if(feedbackMessage.isEmpty() || feedbackMessage == ""){
                    Toast.makeText(ArchitectFeedbackActivity.this, "Please fill the feedback form", Toast.LENGTH_SHORT).show();
                }else{
                    FeedBackRepository feedBackRepository = new FeedBackRepository(ArchitectFeedbackActivity.this);
                    feedBack.setFeedBackID(feedBackRepository.IDEncoder("-",loggedArchitect.getArchitectID(),feedbackMessage));
                    feedBack.setClientID("-");
                    feedBack.setArchitectID(loggedArchitect.getArchitectID());
                    feedBack.setFeedBackMessage(feedbackMessage);
                    String IDdrawed = feedBackRepository.insertTransaction(feedBack);
                    Intent intent = new Intent(ArchitectFeedbackActivity.this, ArchitectMainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
            }
        });
    }
}
