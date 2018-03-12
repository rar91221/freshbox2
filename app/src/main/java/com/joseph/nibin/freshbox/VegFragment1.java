package com.joseph.nibin.freshbox;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class VegFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_veg_fragment1,container,false);
        ListView lv = (ListView) rootView.findViewById(R.id.lv1);
        TopVeggiesAdapter adapter = new TopVeggiesAdapter(this.getActivity(),getTopVegFragment());
        lv.setAdapter(adapter);
        return rootView;
    }

    private ArrayList<Custom_Class_VF1> getTopVegFragment(){
        /*ArrayList<Custom_Class_VF1> topVegFragmentArrayList = new ArrayList<>();
        Custom_Class_VF1 ccvf1Obj;
        GetTopVeggies getTopVeggies = new GetTopVeggies(this.getActivity());
        getTopVeggies.execute("http://192.168.2.101:8081/topVeggies_select.php");
        for (int i = 0; i<10; i++){
            ccvf1Obj = new Custom_Class_VF1("image","name","unit","price");
            topVegFragmentArrayList.add(ccvf1Obj);
        }*/

        //return topVegFragmentArrayList;
        final ArrayList<Custom_Class_VF1> obj2 = new ArrayList<>();

        class GetTopVeggies extends AsyncTask<String,String,String>
        {
            Context context;
            ProgressDialog loading;

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
                    loading.dismiss();
                    //TopVeggiesAdapter obj3 = new TopVeggiesAdapter(getActivity(), obj2);
                    //ListView listView = (ListView) rootView.findViewById(R.id.lv1);
                    //listView.setAdapter(obj3);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }
        GetTopVeggies getTopVeggies = new GetTopVeggies(this.getActivity());
        getTopVeggies.execute("http://192.168.43.226:8081/topVeggies_select.php");

        return obj2;

    }

    @Override
    public String toString() {
        String title = "Top Veggies";
        return title;
    }

    /*@Override
    public void onResume() {
        super.onResume();

    }*/
}