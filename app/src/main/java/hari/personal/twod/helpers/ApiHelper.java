package hari.personal.twod.helpers;

import android.content.Context;
import android.util.Log;

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

import hari.personal.twod.interfaces.Listener;
import hari.personal.twod.models.PostItemModel;

public class ApiHelper {

    Context context;
    Listener listener;

    public ApiHelper(Context context, Listener listener){
        this.context = context;
        this.listener = listener;
    }

    String url = "https://jsonplaceholder.typicode.com/posts";

    public void fetchData(){
        ArrayList<PostItemModel> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                PostItemModel item = new PostItemModel();
                                item.setId(String.valueOf(jsonObject.getInt("id")));
                                item.setTitle(jsonObject.getString("title"));
                                item.setBody(jsonObject.getString("body"));
                                list.add(item);
                                //Log.d("myTag", "Title\n"+item.getTitle());
                            }
                        } catch (JSONException e) {
                            Log.d("myTag", e.getMessage(), e);
                            listener.onError("Something went wrong");
                        }
                        listener.onListFetchSuccessful(list);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError("Something went wrong");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
