package com.mrhi.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
    }

    public void clickBtn(View view) {

        //res폴더 창고관리자 객체 소환하기
        Resources res= getResources();


        XmlResourceParser xrp= res.getXml(R.xml.members);

        try {
            xrp.next();
            int eventType= xrp.getEventType();

            String tagName;
            StringBuffer buffer= new StringBuffer();

            while( eventType!= XmlResourceParser.END_DOCUMENT){

                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("파싱시작..\n\n");
                        break;

                    case XmlResourceParser.START_TAG:
                        tagName= xrp.getName();
                        if(tagName.equals("nm")){
                            buffer.append("이름:");
                        }else if(tagName.equals("age")){
                            buffer.append("나이:");
                        }else if(tagName.equals("tel")){
                            buffer.append("전화번호:");
                        }

                        break;

                    case XmlResourceParser.TEXT:
                        buffer.append( xrp.getText() +"\n");
                        break;

                    case XmlResourceParser.END_TAG:
                        tagName= xrp.getName();
                        if(tagName.equals("person")){
                            buffer.append("\n-----------------\n");
                        }


                        break;

                    case XmlResourceParser.END_DOCUMENT:
                        break;

                }
                eventType= xrp.next();
            }//while

            tv.setText(buffer.toString());




        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


    }
}
