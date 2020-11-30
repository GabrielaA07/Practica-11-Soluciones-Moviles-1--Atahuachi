package com.example.practica11_atahuachi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ListView lvTodos;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        //lvTodos = (ListView) findViewById(R.id.lvTodos);
        loadToDos();
    }

    protected void loadToDos() {
        Retrofit retrofit = API.getRetrofitClient();
        ToDoAPI api = retrofit.create(ToDoAPI.class);

        Call<List<ToDo>> listCall = api.getAllTodo();
        listCall.enqueue(new Callback<List<ToDo>>() {
            @Override
            public void onResponse(Call<List<ToDo>> call, Response<List<ToDo>> response) {
                ToDoList list = new ToDoList(getParent(), response.body());
                lvTodos.setAdapter(list);
                lvTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        Toast.makeText(getApplicationContext(), response.body().get(i).getTitle(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ToDo>> call, Throwable t) {

            }
        });
    }
}