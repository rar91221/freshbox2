package com.joseph.nibin.freshbox;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pramod Rajan on 6/3/2018.
 */

class GetTopVeggies extends AsyncTask<String,String,String>
{
    Context context;
    ProgressDialog loading;
    ArrayList<Custom_Class_VF1> obj2;
    GetTopVeggies(Context ctx){
        context = ctx;
    }
    //ProgressDialog loading;
    //StringBuilder sb;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(this.context, "Fetching Data","Please Wait...",true,true);
    }


    @Override
    protected String doInBackground(String... strings) {

        String topVeggies_url = strings[0];

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(topVeggies_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            StringBuilder sb = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json;
            while((json = bufferedReader.readLine())!= null){
                sb.append(json+"\n");
            }
            con.disconnect();
            bufferedReader.close();
            return sb.toString().trim();

        }catch(Exception e){
            //Toast.makeText(getContext(),"Connection not Available",Toast.LENGTH_SHORT).show();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            //ArrayList<Custom_Class_VF1> obj2 = new ArrayList<Custom_Class_VF1>();
            JSONArray objArray = new JSONArray(s);
            for(int i=0; i<objArray.length();i++){
                JSONObject obj = objArray.getJSONObject(i);
                String image = obj.getString("image");
                String product_name = obj.getString("v_name");
                String unit = obj.getString("unit");
                String price = obj.getString("price");
                //getPostRequest(image, product_name, unit, price);
                obj2.add(new Custom_Class_VF1(image,product_name,unit,price));

            }
            //loading.dismiss();
            //TopVeggiesAdapter obj3 = new TopVeggiesAdapter(getActivity(), obj2);
            //ListView listView = (ListView) rootView.findViewById(R.id.lv1);
            //listView.setAdapter(obj3);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}