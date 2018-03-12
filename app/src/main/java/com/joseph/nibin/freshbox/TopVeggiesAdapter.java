package com.joseph.nibin.freshbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TopVeggiesAdapter extends BaseAdapter{

    Context con;
    ArrayList<Custom_Class_VF1> ccvf1;
    LayoutInflater inflater;

    public TopVeggiesAdapter(Context con, ArrayList<Custom_Class_VF1> ccvf1){
        this.con = con;
        this.ccvf1 = ccvf1;
    }

    @Override
    public int getCount() {
        return ccvf1.size();
    }

    @Override
    public Object getItem(int position) {
        return ccvf1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater ==null){
            inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listviewitem,parent,false);
        }
        final TextView productNameTV = (TextView) convertView.findViewById(R.id.productNameTV);
        final TextView priceTV = (TextView) convertView.findViewById(R.id.priceTV);
        final TextView quantityTV = (TextView) convertView.findViewById(R.id.quantityTV);
        final ImageView productImageIV = (ImageView) convertView.findViewById(R.id.productIV);
        Button addButton = (Button) convertView.findViewById(R.id.addButton);

        final String name = ccvf1.get(position).getProduct_name();
        final String unit = ccvf1.get(position).getUnit();
        final String price = ccvf1.get(position).getPrice();
        final String imageURL = ccvf1.get(position).getImage();

        class GetImage extends AsyncTask<String,Void,Bitmap>{

            URL url = null;
            Bitmap image;
            @Override
            protected Bitmap doInBackground(String... strings) {
                try{
                    Log.i("IMAGE URL: ASSSS ",strings[0]);
                    url = new URL(strings[0]);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }catch (MalformedURLException e){
                    Log.i("IMAGE URL: ",strings[0]);
                    e.printStackTrace();
                }catch (IOException e){
                    Log.i("IMAGE URL: ",strings[0]);
                    e.printStackTrace();
                }
                return image;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                productImageIV.setImageBitmap(bitmap);
                productNameTV.setText(name);
                priceTV.setText(price);
                quantityTV.setText(unit);
            }
        }
        GetImage getImage = new GetImage();
        getImage.execute(imageURL);
        //productNameTV.setText(name);
        //priceTV.setText(price);
        //quantityTV.setText(unit);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(con,name,Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }
}