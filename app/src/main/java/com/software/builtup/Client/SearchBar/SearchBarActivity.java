package com.software.builtup.Client.SearchBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.software.builtup.Client.ArchitectListDetailActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.repository.ArchitectRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchBarActivity extends AppCompatActivity {

    EditText searchItem;
    ImageButton searchButton;
    RecyclerView searchRecyclerView;
    List<Architect> architectList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        searchRecyclerView = findViewById(R.id.search_recycler_list);
        searchItem = findViewById(R.id.search_bar_edit_text);
        searchButton = findViewById(R.id.search_button_client);

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchRecyclerView.setAdapter(searchAdapter);
        setSearchAdapter();
        architectList.clear();
        //final ArchitectRepository architectRepository = new ArchitectRepository(SearchBarActivity.this);
        //architectList.addAll(architectRepository.getAllArchitect());
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = searchItem.getText().toString().trim();
                ArchitectRepository architectRepository = new ArchitectRepository(SearchBarActivity.this);
                architectList.clear();
                if (architectRepository.validateSearchedArchitect(search) == true){
                    architectList.addAll(architectRepository.getAllSearchArchitect(search));
                }else architectList.clear();
                searchAdapter.notifyDataSetChanged();
            }
        });

//        TextWatcher searchListener = new TextWatcher() {
//            ArchitectRepository architectRepository = new ArchitectRepository(SearchBarActivity.this);
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                architectList.clear();
//                if (architectRepository.validateSearchedArchitect(s.toString().trim()) == true){
//                    architectList.addAll(architectRepository.getAllSearchArchitect(s.toString().trim()));
//                }
//            }@Override
//            public void afterTextChanged(Editable s) { }
//        };
//        searchItem.addTextChangedListener(searchListener);

        searchItem.addTextChangedListener(new TextWatcher() {
//            ArchitectRepository architectRepository = new ArchitectRepository(SearchBarActivity.this);
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                architectList.clear();
                searchAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

    }

    public void setSearchAdapter() {

        ArchitectRepository architectRepository_ = new ArchitectRepository(this);
        List<Architect> fullData = architectRepository_.getAllArchitect();
        architectList = new ArrayList<>();
        searchAdapter = new SearchAdapter(fullData, new SearchAdapter.SearchAdapterClick() {
            @Override
            public void onClick(Architect architect) {
                Intent intent = new Intent(SearchBarActivity.this, ArchitectListDetailActivity.class);
                intent.putExtra("MsArchitect", architect);
                startActivity(intent);
            }
        });
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(SearchBarActivity.this));
        searchRecyclerView.setAdapter(searchAdapter);
    }
//
//    public void getAllData(){
//        ArchitectRepository architectRepository = new ArchitectRepository(SearchBarActivity.this);
//        architectList.clear();
//        architectList.addAll(architectRepository.getAllArchitect());
//        searchAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        getAllData();
//    }



}
