package hari.personal.twod.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import hari.personal.twod.R;
import hari.personal.twod.helpers.FirebaseHelper;
import hari.personal.twod.interfaces.Listener;
import hari.personal.twod.models.PostItemModel;

public class AddFragment extends Fragment implements Listener {

    EditText et_title, et_body;
    Button btn_add;
    FirebaseHelper helper;
    Context context;
    AlertDialog.Builder builder;
    Dialog d;

    ProgressDialog progressDialog;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        et_body = view.findViewById(R.id.et_body);
        et_title = view.findViewById(R.id.et_title);
        btn_add = view.findViewById(R.id.btn_add);
        helper = new FirebaseHelper(this);

        builder = new AlertDialog.Builder(context);
        builder.setTitle("Inserted").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                d.dismiss();
            }
        }).setCancelable(false);
        d = builder.create();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please wait");
        progressDialog.setCancelable(false);

        btn_add.setOnClickListener(v -> {
            progressDialog.show();
            helper.addPost(
                    String.valueOf(et_title.getText()).trim(),
                    String.valueOf(et_body.getText()).trim()
            );
        });
    }

    @Override
    public void onListFetchSuccessful(ArrayList<PostItemModel> list) {
        //progressDialog.dismiss();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onSuccess(String successMessage) {
        Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        d.show();
    }
}