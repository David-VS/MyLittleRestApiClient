package be.ehb.myrestapi.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import be.ehb.myrestapi.model.PostData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OurViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<PostData>> posts;

    public OurViewModel(@NonNull Application application) {
        super(application);
        posts = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<PostData>> getPosts() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();

                    Request request = new Request.Builder()
                            .url("https://jsonplaceholder.typicode.com/posts")
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseRaw = response.body().string();

                    ArrayList<PostData> justGotThese = new ArrayList<>();

                    JSONArray postsRaw = new JSONArray(responseRaw);
                    int length = postsRaw.length();
                    for(int i = 0; i < length; i++){
                        JSONObject postRaw = postsRaw.getJSONObject(i);

                        PostData postParsed = new PostData(
                                postRaw.getInt("userId"),
                                postRaw.getInt("id"),
                                postRaw.getString("title"),
                                postRaw.getString("body")
                        );
                        justGotThese.add(postParsed);
                    }
                    posts.postValue(justGotThese);

                }catch (IOException ioe){
                    Log.e("TEST", "Verbinding is kapot");
                } catch (JSONException e) {
                    Log.e("TEST", "JSON WRONG FORMAT");
                }
            }
        });
        thread.start();

        return posts;
    }
}
