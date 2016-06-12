package com.wxj.mdnote.cpf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wxj.mdnote.R;
import com.wxj.mdnote.cpf.model.Result;
import com.wxj.mdnote.cpf.view.ICityListView;
import com.wxj.mdnote.view.FancyIndexer;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class CitySupportActivity extends AppCompatActivity implements ICityListView, FancyIndexer.OnTouchLetterChangedListener {

    private static final String TAG = CitySupportActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private Button btnAgain;
    private CitySuppoertPresenter presenter;
    private RecyclerView recyclerView;
    private FancyIndexer fancyIndexer;
    private List<Result> cities;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_support);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnAgain = (Button) findViewById(R.id.btn_again);
        fancyIndexer = (FancyIndexer) findViewById(R.id.fancyindex);
        fancyIndexer.setOnTouchLetterChangedListener(this);
        presenter = new CitySuppoertPresenter(this);
        presenter.getCities();
    }


    @Override
    public void showPrompt() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrompt() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showErrorView() {
        btnAgain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorVIew() {
        btnAgain.setVisibility(View.GONE);
    }

    @Override
    public void showCities(final List<Result> cities) {
        this.cities = cities;
        Log.d(TAG, "showCities: 显示数据");
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecyclerView.Adapter<CityViewHolder>() {
            @Override
            public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = View.inflate(CitySupportActivity.this, android.R.layout.simple_list_item_1, null);
                return new CityViewHolder(view);
            }

            @Override
            public void onBindViewHolder(final CityViewHolder holder, int position) {
                Observable.just(cities.get(position))
                        .subscribe(new Action1<Result>() {
                            @Override
                            public void call(Result result) {
                                holder.textView.setText(result.getCname());
                            }
                        });
            }

            @Override
            public int getItemCount() {
                return cities.size();
            }
        });
    }

    @Override
    public void onTouchLetterChanged(String s) {
        Observable.just(s)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return cities != null && (cities.size() != 0);
                    }
                })
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        int index = 0;
                        for (int i = 0; i < cities.size(); i++) {
                            Result result = cities.get(i);
                            if (s.equalsIgnoreCase(result.getCity().charAt(0) + "")) {
                                index = i;
                                break;
                            }
                        }
                        Log.d(TAG, "call: index = " + index);
                        return index;
                    }
                })
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer index) {
                        // index ---->position
                        View view = linearLayoutManager.findViewByPosition(index);
                        int top = view.getTop();
                        return top;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer position) {

                        linearLayoutManager.scrollToPosition(position);
                    }
                });
    }
}

class CityViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public CityViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(android.R.id.text1);
    }
}