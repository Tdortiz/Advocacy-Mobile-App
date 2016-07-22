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
    private TextView first_name, last_name, phone_number, email, comment, street_view, city_view, state_view, zipcode_view, county_view;
    private CheckBox issue1, issue2, issue3;
    private DoorData myData;
    private String first, last, street, city, state, zipcode, county;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_constit);
        myData = DoorData.getInstance();

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
        county_view = (EditText) findViewById(R.id.editText9);
        comment = (EditText) findViewById(R.id.editText12);
        street_view = (EditText) findViewById(R.id.editText5);
        city_view = (EditText) findViewById(R.id.editText6);
        state_view = (EditText) findViewById(R.id.editText7);
        zipcode_view = (EditText) findViewById(R.id.editText8);
        issue1 = (CheckBox) findViewById(R.id.checkBox1);
        issue2 = (CheckBox) findViewById(R.id.checkBox2);
        issue3 = (CheckBox) findViewById(R.id.checkBox3);

        Intent intent = getIntent();
        first = intent.getStringExtra("first");
        last = intent.getStringExtra("last");
        street = intent.getStringExtra("street");
        city = intent.getStringExtra("city");
        zipcode = intent.getStringExtra("zipcode");
        state = intent.getStringExtra("state");
        county = intent.getStringExtra("county");

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Regular.ttf");
        //first_name.setText(first);
        if(myData.getCurrentConstituent() != null && myData.getCurrentConstituent().first_name != null) {
            first_name.setText(myData.getCurrentConstituent().first_name);
        }
        else {
            first_name.setText(first);
        }
        if(myData.getCurrentConstituent() != null && myData.getCurrentConstituent().last_name != null) {
            last_name.setText(myData.getCurrentConstituent().last_name);
        }
        else {
            last_name.setText(last);
        }
        state_view.setText(state);
        street_view.setText(street);
        city_view.setText(city);
        zipcode_view.setText(zipcode);
        county_view.setText(county);

        first_name.setTypeface(tf);
        state_view.setTypeface(tf);
        street_view.setTypeface(tf);
        city_view.setTypeface(tf);
        zipcode_view.setTypeface(tf);
        county_view.setTypeface(tf);
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
                new_constituent.address = new Address(street, city, state, zipcode, county);
                new_constituent.email = email.toString();
                new_constituent.notes = comment.toString();
                myData.addConstituient(new_constituent);
                finish();
                // submit
                break;

            // Petition
            case R.id.button1:
                intent = new Intent(this, PetitionActivity.class);
                intent.putExtra("first_name", first_name.toString());
                intent.putExtra("last_name", last_name.toString());
                Constituent c = new Constituent();
                c.first_name = first_name.getText().toString();
                c.last_name = last_name.getText().toString();
                //c.phone = phone_number.toString();
                //c.address = new Address(street, city, state, zipcode, county);
                //c.email = email.toString();
                //c.notes = comment.toString();
                myData.setCurrentConstituent(c);
                break;

            // Make a donation
            case R.id.button3:
                intent = new Intent(this, DonateActivity.class);
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

    public void back(View view) {
        Intent nextScreen = new Intent(getApplicationContext(), AddProspectActivity.class);
        nextScreen.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(nextScreen, 0);
        overridePendingTransition(0, 0); //0 for no animation

        finish();
    }

}
