package in.growingbiz.amjad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustInfoActivity extends AppCompatActivity {

    TextView name, type,mobile,source,lname,dob,ann;
    private static final String TAG = "CustInfoActivity";
    String c_mobile,c_name, c_email,c_lname,c_dob,c_ann;
    private static final String URL_FOR_LOGIN = "http://192.168.0.107:80/rest/listcust.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_info);


        mobile = (TextView) findViewById(R.id.Mobile);

        //name.setText(jRealObject.getString("CUS_TEL"));
        mobile.setText("PHONE NUMBER " + getIntent().getExtras().getString("CUS_TEL"));
        name  = (TextView) findViewById(R.id.Name);
        source  = (TextView) findViewById(R.id.Sorce);
        type = (TextView) findViewById(R.id.Type);
        lname = (TextView) findViewById(R.id.Lname);
        dob = (TextView) findViewById(R.id.DOB);
        ann = (TextView) findViewById(R.id.ANN);
        loginuser(getIntent().getExtras().getString("CUS_TEL"));
        // de(getIntent().getExtras().getString("CUS_TEL"));

    }

    private void loginuser(final String mobile) {
        String cancel_req_tag = "login";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "CUSTOMER Response: " + response.toString());


                try {
                    //JSONArray obj = new JSONArray(response);
                    String error="";
                    //getting the whole json object from the response

                    //we have the array named hero inside the object
                    //so here we are getting that json array

                    JSONArray jsonarray = new JSONArray(response);

                    System.out.println(jsonarray);
                    for(int i=0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        c_name       = jsonobject.getString("CUS_FNAME");
                        c_mobile    = jsonobject.getString("CUS_OCCU");
                        c_email  = jsonobject.getString("CUS_EMAIL");
                        c_lname = jsonobject.getString("CUS_LNAME");
                        c_dob = jsonobject.getString("CUS_DOB");
                        c_ann = jsonobject.getString("CUS_ANNDATE");

                        error =     jsonobject.getString("error");



                    //JSONArray error = obj.getJSONArray("error");
                    System.out.println("In JSON"+error);
                    if (error.equals("false")) {
                    //    if (!error) {

                         c_name = jsonobject.getString("CUS_FNAME");
                         name.setText(c_name);

                         c_email = jsonobject.getString("CUS_EMAIL");
                        source.setText(c_email);

                        c_mobile = jsonobject.getString("CUS_OCCU");
                        type.setText(c_mobile);

                        c_lname = jsonobject.getString("CUS_LNAME");
                        lname.setText(c_lname);

                        c_dob = jsonobject.getString("CUS_DOB");
                        dob.setText(c_dob);

                        c_ann = jsonobject.getString("CUS_ANNDATE");
                        ann.setText(c_ann);
                        //final String src = obj.getJSONObject("queue").getString("CUS_OCCU");
                            //final String ty = obj.getJSONObject("queue").getString("CUS_EMAIL");
                            //System.out.println("fname"+fname+"src"+src+"Type"+ty);
                            //name.setText(fname);
                            //source.setText(src);
                            //Type.setText(ty);



                    }else {

                        String errorMsg = jsonobject.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "CUSTOMER Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                })


        {
            public Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("CUS_TEL", mobile);
                return params;

            }
        };

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }

}
