package com.thedeveloperworldisyours.jurassicpad.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thedeveloperworldisyours.jurassicpad.R;
import com.thedeveloperworldisyours.jurassicpad.data.SearchItem;
import com.thedeveloperworldisyours.jurassicpad.vertical.DividerVerticalItemDecoration;
import com.thedeveloperworldisyours.jurassicpad.vertical.VerticalData;
import com.thedeveloperworldisyours.jurassicpad.vertical.VerticalRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment implements VerticalRecyclerViewAdapter.MyClickListener, MainContract.View {

    @BindView(R.id.main_fragment_recycler_view)
    RecyclerView mRecyclerView;
    private VerticalRecyclerViewAdapter mAdapter;

    private MainContract.Presenter mPresenter;

    public MainFragment() {
        // Required empty public constructor
    }



    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);


        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new VerticalRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerVerticalItemDecoration(getActivity());
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter.setOnItemClickListener(this);


        return view;
    }


    private ArrayList<VerticalData> getDataSet() {
        ArrayList results = new ArrayList<>();
        for (int index = 0; index < 20; index++) {
            VerticalData obj = new VerticalData("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }
        return results;
    }

    @Override
    public void onItemClick(final int position, View v) {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public void showUser(List<SearchItem>) {
        mAdapter = new VerticalRecyclerViewAdapter(
    }

    @Override
    public void showError() {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

}
