package com.example.wangdaopeng.myapplication;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CurrentUser {
    private  static  CurrentUser currentUser =  new CurrentUser();

    private CurrentUser(){

    }
    public  static CurrentUser getInstance(){
        return currentUser;
    }

    String usename="user1";
    String date="date1";
    Global_Data global_data = Global_Data.getInstance();


    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }



    public   String login(String username,String password) throws IOException {
        String url = global_data.getLogin_url();

        System.out.print("urlis ====="+url);

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params=new FormBody.Builder();
        params.add("username",username);
        params.add("password",password);
        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();
        System.out.print("________________++++++++++++++");
        String login = client.newCall(request).execute().body().string();
        System.out.println(login);
        if(login.equals("1")){

            //app client should set usename == username
            return "success";
        }
        else
            return "failed";
    }


    public String logout() throws  IOException{
        String url = global_data.getLogout_url();

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();

        Request request = new Request.Builder()
                .url(url)
                .build();

        String  login =  client.newCall(request).execute().body().string();

        if (login.equals("1")){
            return  "success";
        }
        else {
            return "failed";
        }
    }


    public  String  get_label() throws IOException {
        String url = global_data.getLabel_url();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();

        params.add("usename",currentUser.getUsename());

        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();
        String label = client.newCall(request).execute().body().string();
        return label;
    }



    public String get_trace(String date) throws IOException {
        String url = global_data.getTrace_url();

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();
        params.add("username",currentUser.getUsename());
        params.add("date",date);

        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();

        String trace =client.newCall(request).execute().body().string();

        return  trace;
    }

    public String get_welcome() throws IOException {
        String url =  global_data.getWelcome_url();

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params  = new FormBody.Builder();

        Request request = new Request.Builder()
                .url(url)
                .build();

        String welcome  = client.newCall(request).execute().body().string();

        return welcome;
    }

}