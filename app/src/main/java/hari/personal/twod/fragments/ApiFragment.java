package hari.personal.twod.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hari.personal.twod.R;
import hari.personal.twod.adapters.FetchListAdapter;
import hari.personal.twod.helpers.ApiHelper;
import hari.personal.twod.interfaces.Listener;
import hari.personal.twod.models.PostItemModel;

public class ApiFragment extends Fragment implements Listener {

    //declaration
    RecyclerView rv;
    ApiHelper helper;
    ArrayList<PostItemModel> list;
    ProgressDialog progressDialog;

    public ApiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv_web_list);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle("Please wait");
        progressDialog.setCancelable(false);

        helper = new ApiHelper(view.getContext(), this);
        helper.fetchData();
    }

    @Override
    public void onListFetchSuccessful(ArrayList<PostItemModel> list) {
        this.list = list;
        rv.setAdapter(new FetchListAdapter(list, true));
    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onSuccess(String successMessage) {

    }
}