package com.example.ex;

public class MainData {
    // 아이템 데이터 클래스 정의
    private int iv_profile; //imageView int 임
    private String tv_name;
    private String tv_cotent;
    private String tv_title;



    //생성자
    public MainData(int iv_profile, String tv_name, String tv_cotent, String tv_title) {
        this.iv_profile = iv_profile;
        this.tv_name = tv_name;
        this.tv_cotent = tv_cotent;
        this.tv_title = tv_title;
    }


    //getter, setter 생성
    public int getIv_profile() {
        return iv_profile;
    }

    public void setIv_profile(int iv_profile) {
        this.iv_profile = iv_profile;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_cotent() {
        return tv_cotent;
    }

    public void setTv_cotent(String tv_cotent) {
        this.tv_cotent = tv_cotent;
    }

    public String getTv_title(){
        return tv_title;
    }


}
