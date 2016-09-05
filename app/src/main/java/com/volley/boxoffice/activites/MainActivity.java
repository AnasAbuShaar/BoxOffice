package com.volley.boxoffice.activites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.volley.boxoffice.R;
import com.volley.boxoffice.adapters.CustomListAdapter;
import com.volley.boxoffice.model.Movie;
import com.volley.boxoffice.util.AppController;
import com.volley.boxoffice.util.Keys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=4b658vdmeb4d2zrdrfn9c8c9";
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        fetchTopMovies();
    }

    private void setupViews() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie movie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this , DetailActivity.class);
                intent.putExtra(Keys.descKey,movie.getSynopsis());
                intent.putExtra(Keys.years,movie.getYear());



                startActivity(intent);
            }
        });
    }

    private void fetchTopMovies() {

        pDialog.show();

        // Creating volley request obj
        JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            JSONArray jsonArray = response.getJSONArray(Keys.moviesKey);
                            // Parsing json
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString(Keys.titleKey));
                                movie.setThumbnailUrl(obj.getJSONObject(Keys.posterKey).getString(Keys.thumbnailKey));
                                movie.setSynopsis(obj.getString(Keys.synopsisKey));
                                movie.setYear(obj.getInt(Keys.years));


                                movieList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "error message: " + e.getMessage());
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                        hidePDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}