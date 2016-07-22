package blackbaud.advocacymobileapp;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewConstitActivity extends AppCompatActivity implements View.OnClickListener{


    Constituent new_constituent = new Constituent();

    private Button submit, donate, petition;
    private TextView first_name, last_name, phone_number, email, county, comment, street_view, city_view, state_view, zipcode_view, country_view;
    private CheckBox issue1, issue2, issue3;
    private DoorData myData;
    private ImageButton backarrow;
    private String street, city, state, zipcode, country;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_constit);
        myData = DoorData.getInstance();

        //backarrow = (ImageButton) findViewById(R.id.imageButton);
        //backarrow.setOnClickListener(this);

        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(this);

        donate = (Button) findViewById(R.id.button3);
        donate.setOnClickListener(this);

        petition = (Button) findViewById(R.id.button1);
        petition.setOnClickListener(this);


        first_name = (EditText) findViewById(R.id.editText3);
        last_name = (EditText) findViewById(R.id.editText);
        phone_number = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText4);
        county = (EditText) findViewById(R.id.editText9);
        country_view = (EditText) findViewById(R.id.editText11);
        comment = (EditText) findViewById(R.id.editText12);
        street_view = (EditText) findViewById(R.id.editText5);
        city_view = (EditText) findViewById(R.id.editText6);
        state_view = (EditText) findViewById(R.id.editText7);
        zipcode_view = (EditText) findViewById(R.id.editText8);
        issue1 = (CheckBox) findViewById(R.id.checkBox1);
        issue2 = (CheckBox) findViewById(R.id.checkBox2);
        issue3 = (CheckBox) findViewById(R.id.checkBox3);

        Intent intent = getIntent();
        street = intent.getStringExtra("street");
        city = intent.getStringExtra("city");
        zipcode = intent.getStringExtra("zipcode");
        state = intent.getStringExtra("state");
        country = intent.getStringExtra("country");

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Regular.ttf");
        state_view.setText(state);
        street_view.setText(street);
        city_view.setText(city);
        zipcode_view.setText(zipcode);
        country_view.setText(country);

        state_view.setTypeface(tf);
        street_view.setTypeface(tf);
        city_view.setTypeface(tf);
        zipcode_view.setTypeface(tf);
        country_view.setTypeface(tf);
    }



    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.button:
                InterestItem item1 = new InterestItem();
                item1.is_interested = issue2.isChecked();
                item1.name = "dsd";
                if(item1.is_interested){
                    new_constituent.interest_items.add(item1);
                }
                InterestItem item = new InterestItem();
                item.is_interested = issue1.isChecked();
                item.name = "dsd";
                if(item.is_interested){
                    new_constituent.interest_items.add(item);
                }
                InterestItem item2 = new InterestItem();
                item2.is_interested = issue2.isChecked();
                item2.name = "dsd";
                if(item2.is_interested){
                    new_constituent.interest_items.add(item2);
                }

                new_constituent.first_name = first_name.toString();
                new_constituent.last_name = last_name.toString();
                new_constituent.phone = phone_number.toString();
                new_constituent.address = new Address(street.toString(), city.toString(), state.toString(),
                        zipcode.toString(), country.toString(), county.toString());
                new_constituent.email = email.toString();
                new_constituent.notes = comment.toString();
                myData.addConstituient(new_constituent);
                finish();
                // submit
                break;

            case R.id.button1:
                // petition
                intent = new Intent(this, PetitionActivity.class);
                intent.putExtra("first_name", first_name.toString());
                intent.putExtra("last_name", last_name.toString());
                break;

            case R.id.button3:
                intent = new Intent(NewConstitActivity.this, DonateActivity.class);
                startActivity(intent);
                break;

            case R.id.imageButton:
                finish();
                break;

            default:
                break;
        }

        if(intent != null) {
            startActivity(intent);
            finish();
        }

    }
}
