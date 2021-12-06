package tolabuth.ubontemple;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tolabuth.ubontemple.adapter.ImageAdapter;
import tolabuth.ubontemple.model.Image;

public class ImageActivity extends AppCompatActivity {
    private RecyclerView rcvImage;
    private TextView tvTemple;
    private ImageAdapter adapter;
    private List<Image>images;
    private RequestQueue queue;
    private TextView AddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        images =new ArrayList<Image>();
        matchView();
        Intent intent = getIntent();
        tvTemple.setText(intent.getStringExtra("name"));
        int id = intent.getIntExtra("id", 1);
        parseJSON(id);
        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ImageActivity.this, AddImageTemple.class);
                startActivity(intent1);
            }
        });

    }

    private void parseJSON(int id) {
        String url ="http://s62122420127.cs.ubru.ac.th/ubontemple/api/get_images.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("images");
                            for (int i=0;i<jsonArray.length(); i++){
                                JSONObject image = jsonArray.getJSONObject(i);
                                String description = image.getString("description");
                                String imgName = image.getString("image_name");
                                images.add(new Image(description,imgName));
                            }
                            adapter = new ImageAdapter(ImageActivity.this,images);
                            rcvImage.setAdapter(adapter);
                            rcvImage.setLayoutManager(new LinearLayoutManager(ImageActivity.this));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Message", error.getMessage());

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("id", String.valueOf(id));

                return param;
            }
        };
        queue = Volley.newRequestQueue(ImageActivity.this);
        queue.add(request);

    }

    private void matchView() {
        rcvImage = findViewById(R.id.rcv_image);
        tvTemple = findViewById(R.id.tv_temple);
        AddImage = findViewById(R.id.txtAdd_image);
    }
}