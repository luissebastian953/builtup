package com.software.builtup.Client.ClientChatSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.software.builtup.AppStart.SharedPref;
import com.software.builtup.Client.ArchitectListDetailActivity;
import com.software.builtup.Client.ClientMainActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.model.ChatDetail;
import com.software.builtup.model.ChatSystem;
import com.software.builtup.model.Client;
import com.software.builtup.repository.ChatDetailRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientChatRecyclerActivity extends AppCompatActivity {

    TextView nameArchitect;
    EditText clientChatMessage;
    Button sendButton;
    RecyclerView clientRecyclerMessage;
    List<ChatDetail> chatDetails;
    ClientMessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_chat_recycler);
        final ChatSystem chatSystem = (ChatSystem) getIntent().getSerializableExtra("TrChat");

        clientChatMessage = findViewById(R.id.edit_text_chat_client);
        clientRecyclerMessage = findViewById(R.id.chat_message_recycler_c);
        sendButton = findViewById(R.id.btn_send_chat_c);
        nameArchitect = findViewById(R.id.architect_name_c);

        clientRecyclerMessage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        clientRecyclerMessage.setAdapter(messageAdapter);
        nameArchitect.setText(chatSystem.getArchitectName());
        setClientMessageAdapter();
        ChatDetailRepository chatDetailRepository = new ChatDetailRepository(ClientChatRecyclerActivity.this);
        chatDetails.clear();
        chatDetails.addAll(chatDetailRepository.getAllChatDetail(chatSystem.getChatID()));
        if(isVisible()) clientRecyclerMessage.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatDetailRepository chatDetailRepository = new ChatDetailRepository(ClientChatRecyclerActivity.this);
                String message = clientChatMessage.getText().toString().trim();
                if(message == "" || message.isEmpty()){

                }else{
                    ChatDetail chatDetail = new ChatDetail();
                    chatDetail.setChatID(chatSystem.getChatID());
                    chatDetail.setCurrentHolder("Client");
                    chatDetail.setMessageText(message);
                    chatDetail.setChatDetailID(chatDetailRepository.IDEncoder(
                            chatSystem.getChatID(), message, "Client"
                    ));
                    chatDetailRepository.insertChatDetail(chatDetail);
                    chatDetails.add(chatDetail);
                    messageAdapter.notifyDataSetChanged();
                    clientRecyclerMessage.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    clientChatMessage.setText("");
                }
            }
        });
    }

    public void setClientMessageAdapter(){
        chatDetails = new ArrayList<>();
        messageAdapter = new ClientMessageAdapter(chatDetails);
        clientRecyclerMessage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        clientRecyclerMessage.setAdapter(messageAdapter);
    }

    public boolean isVisible(){
        LinearLayoutManager LayoutManager = (LinearLayoutManager) clientRecyclerMessage.getLayoutManager();
        //int positionOfLastVisibleItem = LayoutManager.findLastCompletelyVisibleItemPosition();
        int count = clientRecyclerMessage.getAdapter().getItemCount();
        //return (positionOfLastVisibleItem >= count);
        return (10<=count);
    }
}
