package com.software.builtup.Client.buildHouse;

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

public class BuildHouseRecycler extends AppCompatActivity {

    private BuildHouseAdapter buildHouseAdapter;
    private RecyclerView recyclerBuildHouse;
    private List<Architect> architectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_house_recycler);
        recyclerBuildHouse = findViewById(R.id.build_house_recycler_c);
        setBuildHouseAdapter();
    }

    public void setBuildHouseAdapter(){
        architectList = new ArrayList<>();
        buildHouseAdapter = new BuildHouseAdapter(architectList, new BuildHouseAdapter.BuildHouseClick() {
            @Override
            public void onClick(Architect architect) {
                Intent intent = new Intent(BuildHouseRecycler.this, ArchitectListDetailActivity.class);
                intent.putExtra("MsArchitect", architect);
                startActivity(intent);
            }
        });
        recyclerBuildHouse.setLayoutManager(new LinearLayoutManager(this));
        recyclerBuildHouse.setAdapter(buildHouseAdapter);
    }

    private void getData(){
        ArchitectRepository architectRepository = new ArchitectRepository(BuildHouseRecycler.this);
        architectList.clear();
        architectList.addAll(architectRepository.getAllBuildHouseArchitect());
        buildHouseAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
