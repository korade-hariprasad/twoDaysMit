package hari.personal.twod.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hari.personal.twod.R;
import hari.personal.twod.adapters.FetchListAdapter;
import hari.personal.twod.helpers.FirebaseHelper;
import hari.personal.twod.interfaces.Listener;
import hari.personal.twod.models.PostItemModel;

public class HomeFragment extends Fragment implements Listener {

    RecyclerView rv_home_list;
    FirebaseHelper helper;
    ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_home_list = view.findViewById(R.id.rv_home_list);
        rv_home_list.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        helper = new FirebaseHelper(this);
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.show();
        helper.getAllPosts();
    }

    @Override
    public void onListFetchSuccessful(ArrayList<PostItemModel> list) {
        progressDialog.dismiss();
        rv_home_list.setAdapter(new FetchListAdapter(list, false));
    }

    @Override
    public void onError(String errorMessage) {
        progressDialog.dismiss();
    }

    @Override
    public void onSuccess(String successMessage) {
        progressDialog.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        helper.getAllPosts();
    }
}