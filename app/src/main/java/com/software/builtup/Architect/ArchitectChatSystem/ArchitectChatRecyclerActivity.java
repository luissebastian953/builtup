package com.software.builtup.Architect.ArchitectChatSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.software.builtup.R;
import com.software.builtup.model.ChatDetail;
import com.software.builtup.model.ChatSystem;
import com.software.builtup.repository.ChatDetailRepository;

import java.util.ArrayList;
import java.util.List;

public class ArchitectChatRecyclerActivity extends AppCompatActivity {

    TextView nameClient;
    EditText architectChatMessage;
    Button sendButton;
    RecyclerView architectRecyclerMessage;
    List<ChatDetail> chatDetails;
    ArchitectMessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_chat_recycler);

        final ChatSystem chatSystem = (ChatSystem) getIntent().getSerializableExtra("TrChat");

        architectChatMessage = findViewById(R.id.edit_text_chat_architect);
        architectRecyclerMessage = findViewById(R.id.chat_message_recycler_a);
        nameClient = findViewById(R.id.client_name_a);
        sendButton = findViewById(R.id.btn_send_chat_a);

        architectRecyclerMessage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        architectRecyclerMessage.setAdapter(messageAdapter);
        nameClient.setText(chatSystem.getClientName());
        setArchitectMessageAdapter();
        ChatDetailRepository chatDetailRepository = new ChatDetailRepository(ArchitectChatRecyclerActivity.this);
        chatDetails.clear();
        chatDetails.addAll(chatDetailRepository.getAllChatDetail(chatSystem.getChatID()));
        if(isVisible()) architectRecyclerMessage.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatDetailRepository chatDetailRepository = new ChatDetailRepository(ArchitectChatRecyclerActivity.this);
                String message = architectChatMessage.getText().toString().trim();
                if(message == "" || message.isEmpty()){

                }else{
                    ChatDetail chatDetail = new ChatDetail();
                    chatDetail.setChatID(chatSystem.getChatID());
                    chatDetail.setCurrentHolder("Architect");
                    chatDetail.setMessageText(message);
                    chatDetail.setChatDetailID(chatDetailRepository.IDEncoder(
                            chatSystem.getChatID(), message, "Architect"
                    ));
                    chatDetailRepository.insertChatDetail(chatDetail);
                    chatDetails.add(chatDetail);
                    messageAdapter.notifyDataSetChanged();
                    architectRecyclerMessage.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    architectChatMessage.setText("");
                }
            }
        });
    }
    public void setArchitectMessageAdapter(){
        chatDetails = new ArrayList<>();
        messageAdapter = new ArchitectMessageAdapter(chatDetails);
        architectRecyclerMessage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        architectRecyclerMessage.setAdapter(messageAdapter);
    }

    public boolean isVisible(){
        LinearLayoutManager LayoutManager = (LinearLayoutManager) architectRecyclerMessage.getLayoutManager();
        //int positionOfLastVisibleItem = LayoutManager.findLastCompletelyVisibleItemPosition();
        int count = architectRecyclerMessage.getAdapter().getItemCount();
        //return (positionOfLastVisibleItem >= count);
        return (10<=count);
    }
}
