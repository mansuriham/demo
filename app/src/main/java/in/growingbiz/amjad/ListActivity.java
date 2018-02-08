package in.growingbiz.amjad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import java.util.List;

public class ListActivity extends AppCompatActivity {

    //the URL having the json data
    private static final String TAG = "ListActivity";

    private static final String JSON_URL = "http://192.168.0.107:80/rest/listview.php";

    //listview object
    private ListView listView;

    //the hero list where we will store all the hero objects after parsing json
    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //initializing listview and hero list
        listView = (ListView) findViewById(R.id.listView);
        heroList = new ArrayList<>();

        //this method will fetch and parse the data
        loadHeroList();
    }

    private void loadHeroList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            final JSONArray heroArray = obj.getJSONArray("queue");
                            //System.out.println(heroArray.length());
                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                final JSONObject heroObject = heroArray.getJSONObject(i);
                                final Simplerow adapter = new Simplerow(heroList, getApplicationContext());

                                //adding the adapter to listview
                                listView.setAdapter(adapter);
                                //creating a hero object and giving them the values from json object
                                final Hero hero = new Hero(heroObject.getString("CUS_FNAME"), heroObject.getString("CUS_TEL"));
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        if (position!=-1) {

                                            Intent myIntent = new Intent(ListActivity.this, CustInfoActivity.class);
                                            //String hero = adapter.getItem(position).getCUS_TEL();
                                            myIntent.putExtra("CUS_TEL",adapter.getItem(position).getCUS_TEL());




                                            startActivity(myIntent);
                                        }

                                    }

                                });
                                //adding the hero to herolist
                                heroList.add(hero);
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }


}
