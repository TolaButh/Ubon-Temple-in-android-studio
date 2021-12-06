package tolabuth.ubontemple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tolabuth.ubontemple.adapter.TempleAdapter;
import tolabuth.ubontemple.model.Temple;

public class MainActivity extends AppCompatActivity implements TempleAdapter.OnItemClickListener {
    private RecyclerView rcvTemple;
    private List<Temple>temples;
    private TempleAdapter adapter;
    private RequestQueue queue;
    private FloatingActionButton flb_Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matchView();
        temples = new ArrayList<Temple>();
        parseJSON();
        flb_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTemple.class);
                startActivity(intent);
            }
        });


    }

    private void parseJSON() {
        String url = "http://s62122420127.cs.ubru.ac.th/ubontemple/api/get_temples.php";
        JsonArrayRequest request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject object = null;
                        for (int i = 0; i<response.length();i++){
                            try {
                                object = (JSONObject) response.get(i);
                                int id = object.getInt("id");
                                String name = object.getString("name");
                                String city = object.getString("city");
                                String imgName = object.getString("image");

                                Temple temple = new Temple(id,name, city, imgName);
                                temples.add(temple);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapter = new TempleAdapter(MainActivity.this,temples);
                        rcvTemple.setAdapter(adapter);
                        rcvTemple.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        adapter.setOnClickListener(MainActivity.this);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Message",error.getMessage());
                Toast.makeText(MainActivity.this,"The error",Toast.LENGTH_LONG).show();

            }
        }
        );
        queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);

    }
    //add data to service



    private void matchView() {
        rcvTemple = findViewById(R.id.rcv_temple);
        flb_Add = findViewById(R.id.fld_add_Temple);

    }

    @Override
    public void onItemClick(int position) {
        Log.d("position", String.valueOf(position));
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        intent.putExtra("name", temples.get(position).getName());
        intent.putExtra("id",temples.get(position).getId());
        startActivity(intent);

    }
}