package com.wxj.mdnote.cpf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wxj.mdnote.R;
import com.wxj.mdnote.cpf.model.CPFCityModel;
import com.wxj.mdnote.cpf.net.CPFCityAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CitySupportActivity extends AppCompatActivity {

    private static final String TAG = CitySupportActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_support);

        // Grab your RecyclerView and the RecyclerViewFastScroller from the layout
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        VerticalRecyclerViewFastScroller fastScroller = (VerticalRecyclerViewFastScroller) findViewById(R.id.fast_scroller);
//
//        // Connect the recycler to the scroller (to let the scroller scroll the list)
//        fastScroller.setRecyclerView(recyclerView);
//
//        // Connect the scroller to the recycler (to let the recycler scroll the scroller's handle)
//        recyclerView.setOnScrollListener(fastScroller.getOnScrollListener());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        final CPFCityAPI cpfCityAPI = retrofit.create(CPFCityAPI.class);

        cpfCityAPI.getCities("json", "d0c922cd1973a37847f49d2d0ab3feaf")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CPFCityModel>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(CPFCityModel cpfCityModel) {
                        Log.d(TAG, "onNext: " + cpfCityModel.getCityCPF().size());
                    }
                });

    }
}
