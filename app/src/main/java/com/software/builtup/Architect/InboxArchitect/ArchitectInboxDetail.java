package com.software.builtup.Architect.InboxArchitect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Architect.ArchitectMainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.ChatSystem;
import com.software.builtup.model.TransactionModel;
import com.software.builtup.repository.ChatSystemRepository;
import com.software.builtup.repository.TransactionRepository;

public class ArchitectInboxDetail extends AppCompatActivity {

    TextView clientNameArchitectInboxDetail, clientMessageArchitectInboxDetail, clientConstructionTypeInboxDetail, clientBudgetInboxDetail;
    Button btnAccept, btnDecline;

    public ArchitectInboxDetail(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_inbox_detail);

        clientNameArchitectInboxDetail = findViewById(R.id.text_view_name_architect_inbox_a);
        clientMessageArchitectInboxDetail = findViewById(R.id.text_view_info_architect_inbox_request_message_inbox_a);
        clientConstructionTypeInboxDetail = findViewById(R.id.text_view_info_architect_inbox_construction_type_a);
        clientBudgetInboxDetail = findViewById(R.id.text_view_info_architect_budget_inbox_detail_a);

        btnAccept = findViewById(R.id.inbox_button_accepted_a);
        btnDecline = findViewById(R.id.inbox_button_decline_a);


        Intent intent = this.getIntent();
        final SharedPref sharedPref = new SharedPref(ArchitectInboxDetail.this);
        final Architect loggedArchitect = sharedPref.architectLogger();
        final Bundle bundle = intent.getExtras();
        final TransactionModel transactionModel = (TransactionModel) bundle.getSerializable("TrTransaction");

        clientNameArchitectInboxDetail.setText(transactionModel.getClientName());
        clientMessageArchitectInboxDetail.setText(transactionModel.getRequestMessage());
        clientConstructionTypeInboxDetail.setText(transactionModel.getConstructionType());
        clientBudgetInboxDetail.setText("Rp. "+transactionModel.getBudget()+".00 ");

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accepted = "Accepted";
                transactionModel.setTransactionStatus(accepted);

                ChatSystemRepository chatSystemRepository = new ChatSystemRepository(ArchitectInboxDetail.this);
                if (chatSystemRepository.valideChatExisted(loggedArchitect.getArchitectID(), transactionModel.getClientID()) == true){
                    ChatSystem chatSystem = new ChatSystem();
                    chatSystem.setArchitectID(loggedArchitect.getArchitectID());
                    chatSystem.setClientID(transactionModel.getClientID());
                    chatSystem.setArchitectFieldFocus(loggedArchitect.getArchitectFieldFocus());
                    chatSystem.setArchitectName(loggedArchitect.getArchitectName());
                    chatSystem.setChatID(
                            chatSystemRepository.IDEncoder(
                                    loggedArchitect.getArchitectName(),
                                    transactionModel.getClientName()
                            )
                    );
                    chatSystemRepository.insertChatSystem(chatSystem);
                }
                TransactionRepository transactionRepository = new TransactionRepository(ArchitectInboxDetail.this);
                transactionRepository.UpdateTransaction(transactionModel);
                transactionRepository.RemovePendingTransaction(transactionModel.getClientID());
                Intent intent = new Intent(ArchitectInboxDetail.this, ArchitectMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String declined = "Declined";
                transactionModel.setTransactionStatus(declined);
                TransactionRepository transactionRepository = new TransactionRepository(ArchitectInboxDetail.this);
                transactionRepository.UpdateTransaction(transactionModel);
                Intent intent = new Intent(ArchitectInboxDetail.this, ArchitectMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
