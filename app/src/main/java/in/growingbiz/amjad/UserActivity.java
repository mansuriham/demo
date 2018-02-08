package in.growingbiz.amjad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

TextView txt,noofcust,registered,newreg,noofsms,loyalty;

Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


       // String user = getIntent().getStringExtra("user");
       // final String cust_data = getIntent().getStringExtra("cust_data");
        txt=(TextView)findViewById(R.id.txt);
        txt.setText("Welcome "+ getIntent().getExtras().getString("username"));
        noofcust=(TextView)findViewById(R.id.noofcust);
        noofcust.setText(getIntent().getExtras().getString("cust_data"));
        noofsms=(TextView)findViewById(R.id.noofsms);
        noofsms.setText(getIntent().getExtras().getString("user_sms"));
        registered=(TextView)findViewById(R.id.registered);
        newreg=(TextView)findViewById(R.id.newreg);
        loyalty=(TextView)findViewById(R.id.loyalty);
        loyalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                Intent i=new Intent(UserActivity.this,LoyaltyActivity.class);
                startActivity(i);
            }
        });
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(UserActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent o=new Intent(UserActivity.this,ListActivity.class);
                startActivity(o);
            }
        });
        newreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(UserActivity.this, BuyerNonB.class);
                startActivity(c);
           }
        });

    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

}
