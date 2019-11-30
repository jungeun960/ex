package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CrawlingActivity extends AppCompatActivity {

    //private String htmlPageUrl = "http://www.yonhapnews.co.kr/"; //파싱할 홈페이지의 URL주소
    private String htmlPageUrl = "http://www.hanatour.com/"; //파싱할 홈페이지의 URL주소 하나 투어
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat="";
    private String[] list;
    private int i =0;

    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawling);
        textviewHtmlDocument = (TextView)findViewById(R.id.textView);
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod()); //스크롤 가능한 텍스트뷰로 만들기

        System.out.println( (cnt+1) +"번째 파싱");
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
        cnt++;

        (new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (!Thread.interrupted())
                    try
                    {
                        Thread.sleep(2000);
                        runOnUiThread(new Runnable() // start actions in UI thread
                        {
                            @Override
                            public void run()
                            {
                                textviewHtmlDocument.setText(getCurrentlist());
                            }
                        });
                    }
                    catch (InterruptedException e)
                    {
                        // ooops
                    }
            }
        })).start();

//        Button htmlTitleButton = (Button)findViewById(R.id.button);
//        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements titles;
                titles= doc.select("div.vote_keyword"); // 하나투어 한달간 인기 키워드 top5 view-source:http://www.hanatour.com/

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    //System.out.println("title: " + e.text() + "\n");
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }
                htmlContentInStringFormat = htmlContentInStringFormat.replace("인기키워드 한 달간 인기키워드","");
                System.out.println(htmlContentInStringFormat + "\n");
                list = htmlContentInStringFormat.split("\\s");

                for (String li : list){
                    System.out.println(li);
                }

//                //테스트1
//                Elements titles= doc.select("div.news-con h1.tit-news"); //연합뉴스 젤 위에 뉴스제목 가져오기
//
//                System.out.println("-------------------------------------------------------------");
//                for(Element e: titles){
//                    System.out.println("title: " + e.text());
//                    htmlContentInStringFormat += e.text().trim() + "\n";
//                }

                //테스트2
                //titles= doc.select("div.news-con h2.tit-news");
                //titles= doc.select("span.cont_txt_slide"); //요즘 인기있는 여행지 top4

//                //테스트3
//                titles= doc.select("li.section02 div.con h2.news-tl");
//
//                System.out.println("-------------------------------------------------------------");
//                for(Element e: titles){
//                    System.out.println("title: " + e.text());
//                    htmlContentInStringFormat += e.text().trim() + "\n";
//                }
//                System.out.println("-------------------------------------------------------------");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //textviewHtmlDocument.setText(htmlContentInStringFormat);
        }
    }

    // 현재 시간을 반환
    public String getCurrentlist(){
        if(i==5)
            i=0;
        i++;
        return list[i];
    }
}