package com.example.practica11_atahuachi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ToDoAPI {
    @GET("/todos")
    Call<List<ToDo>> getAllTodo();

    @GET("/todos/{id}")
    Call<List<ToDo>> getTodo(@Path("id") String id);
}
