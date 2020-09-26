package com.software.builtup.Client.Renovation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.software.builtup.Client.ArchitectListDetailActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.repository.ArchitectRepository;

import java.util.ArrayList;
import java.util.List;

public class RenovationRecycler extends AppCompatActivity {

    private RenovationAdapter RenovationAdapter;
    private RecyclerView recyclerRenovation;
    private List<Architect> architectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renovation_recycler);
        recyclerRenovation = findViewById(R.id.renovation_recycler_c);
        setRenovationAdapter();
    }

    public void setRenovationAdapter(){
        architectList = new ArrayList<>();
        RenovationAdapter = new RenovationAdapter(architectList, new RenovationAdapter.RenovationClick() {
            @Override
            public void onClick(Architect architect) {
                Intent intent = new Intent(RenovationRecycler.this, ArchitectListDetailActivity.class);
                intent.putExtra("MsArchitect", architect);
                startActivity(intent);
            }
        });
        recyclerRenovation.setLayoutManager(new LinearLayoutManager(this));
        recyclerRenovation.setAdapter(RenovationAdapter);
    }

    private void getData(){
        ArchitectRepository architectRepository = new ArchitectRepository(RenovationRecycler.this);
        architectList.clear();
        architectList.addAll(architectRepository.getAllRenovationArchitect());
        RenovationAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

}
