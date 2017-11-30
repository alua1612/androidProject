package com.example.aluakosamanova.newsapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aluakosamanova.newsapp.ApiService;
import com.example.aluakosamanova.newsapp.Contact;
import com.example.aluakosamanova.newsapp.ContactsAdapter;
import com.example.aluakosamanova.newsapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentOne extends Fragment{

    private RecyclerView recyclerview;
    private ContactsAdapter contactsAdapter;
    private List<Contact> contactList;
    private boolean add = false;
    View view;
    View createView;
    public FragmentOne() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactList = new ArrayList<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonblob.com/api/jsonBlob/fff380b2-b1d8-11e7-8d30-fba08838ca15/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<List<Contact>> postCall = service.getPosts();

        postCall.enqueue(new Callback<List<Contact>>() {

            @Override
            public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    //Log.d("ADAPTER", "onResponse: " + response.body());
                    setAdapter(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.rvContacts);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactsAdapter = new ContactsAdapter(getContext(), contactList);
        recyclerview.setAdapter(contactsAdapter);
        return view;
    }

    public void setAdapter(List<Contact> newsList) {
        contactList.clear();
        contactList.addAll(newsList);
        contactsAdapter.notifyDataSetChanged();
    }

    void setToRecyclerView(List <Contact> myList) {
        contactList = myList;
        contactsAdapter = new ContactsAdapter(this.getContext(), contactList);
        recyclerview.setLayoutManager(new GridLayoutManager(this.getContext(), 1));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(contactsAdapter);
    }
}
