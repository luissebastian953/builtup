package com.software.builtup.AppStart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.software.builtup.Client.LoginClientActivity;
import com.software.builtup.R;
import com.software.builtup.model.Architect;
import com.software.builtup.repository.ArchitectRepository;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //summonDummyData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginClientActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

//    public void summonDummyData(){
//
//        ArchitectRepository dummyA = new ArchitectRepository(SplashActivity.this);
//        String ID, Username, Password, Name, Phone, Organization, FocusField, Address;
//        if(dummyA.ValidArchitectUser("andreasArchitect@gmail.com") == true){
//            Username = "andreasArchitect@gmail.com";
//            Password = "andreas45";
//            Name = "Andreas Aditya";
//            Phone = "0855446546";
//            Organization = "Architect Div Binus University";
//            FocusField = "Build House";
//            Address = "Jakarta";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("wilo@gmail.com") == true){
//            Username = "wilo@gmail.com";
//            Password = "wilowilo45";
//            Name = "William Loo";
//            Phone = "0843443434";
//            Organization = "Architect Div Batam Center";
//            FocusField = "Renovation";
//            Address = "Batam";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("wibunagano@gmail.com") == true){
//            Username = "wibunagano@gmail.com";
//            Password = "rionagano45";
//            Name = "Rio Nagano";
//            Phone = "089473843839";
//            Organization = "Freelancer";
//            FocusField = "Build House";
//            Address = "Bekasi";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("hanif@gmail.com") == true){
//            Username = "hanif@gmail.com";
//            Password = "musyaffa45";
//            Name = "Hanif Musyaffa";
//            Phone = "08463488235";
//            Organization = "Underpaid Architect at Bisanara.com";
//            FocusField = "Renovation";
//            Address = "Padang";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("andreasharyanto@gmail.com") == true){
//            Username = "andreasharyanto@gmail.com";
//            Password = "andreasharryanto45";
//            Name = "Andreas Aditya";
//            Phone = "0855446546";
//            Organization = "Architect Div Binus University";
//            FocusField = "Build House";
//            Address = "Jakarta";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("prismaakeh@gmail.com") == true){
//            Username = "prismaakeh@gmail.com";
//            Password = "prismaakeh45";
//            Name = "Prisma Jaya Akeh";
//            Phone = "08548734873";
//            Organization = "Freelancer";
//            FocusField = "Renovation";
//            Address = "Batam";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("kubusakeh@gmail.com") == true){
//            Username = "kubusakeh@gmail.com";
//            Password = "kubusakeh45";
//            Name = "Kubus Jaya Akeh";
//            Phone = "0855446546";
//            Organization = "Freelancer";
//            FocusField = "Build House";
//            Address = "Sumedang";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("bolaakeh@gmail.com") == true){
//            Username = "bolaakeh@gmail.com";
//            Password = "bolaakeh45";
//            Name = "Bola Jaya Akeh";
//            Phone = "08954059495";
//            Organization = "Freelancer";
//            FocusField = "Renovation";
//            Address = "Jakarta";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//
//        if(dummyA.ValidArchitectUser("tegarakeh@gmail.com") == true){
//            Username = "tegarakeh@gmail.com";
//            Password = "tegarakeh45";
//            Name = "Muhammad Rafi Tegar Jaya Akeh";
//            Phone = "08467326373";
//            Organization = "Freelancer";
//            FocusField = "Build House";
//            Address = "Riau";
//
//            dummyA.insertRegisterArchitect(
//                    dummyA.IDEncoder(Username, Password, Phone, Organization),
//                    Username, Password, Name, Phone, Organization, FocusField, Address);
//        }
//    }
}
