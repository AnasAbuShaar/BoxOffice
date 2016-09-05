package com.volley.boxoffice.activites;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.volley.boxoffice.R;
import com.volley.boxoffice.util.Keys;

public class DetailActivity extends ActionBarActivity {

    private TextView tvDetail;

    private TextView tvyear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupViews();

        String desc = getIntent().getStringExtra(Keys.descKey);
        Integer yearMovies=getIntent().getIntExtra(Keys.years,0);

        tvyear.setText(String.valueOf(yearMovies));
        tvDetail.setText(desc);





    }

    private void setupViews() {

        tvDetail = (TextView) findViewById(R.id.tvDetail) ;
        tvyear=(TextView)findViewById(R.id.tvYear);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
