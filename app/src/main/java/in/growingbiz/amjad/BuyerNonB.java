package in.growingbiz.amjad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BuyerNonB extends AppCompatActivity {
    Button buyer,nonbuyer;
    EditText mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_non_b);
        buyer= (Button)findViewById(R.id.buyer);
        nonbuyer=(Button)findViewById(R.id.nonbuyer);
        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mobile.getText().toString();

                Intent i=new Intent(BuyerNonB.this,newregcust.class);
                //i.putExtra("mobile", "value");
                i.putExtra("mytext",text);

                startActivity(i);

            }
        });
        nonbuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mobile.getText().toString();
                Intent i=new Intent(BuyerNonB.this,newregcust.class);
                i.putExtra("mytext",text);

                startActivity(i);
            }
        });
        mobile=(EditText)findViewById(R.id.mobile);

    }
}
