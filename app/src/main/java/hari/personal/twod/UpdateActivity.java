package hari.personal.twod;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;

import java.util.ArrayList;

import hari.personal.twod.helpers.FirebaseHelper;
import hari.personal.twod.interfaces.Listener;
import hari.personal.twod.models.PostItemModel;

public class UpdateActivity extends AppCompatActivity implements Listener {

    EditText et_title, et_body;
    Button btn_update, btn_delete;
    PostItemModel item;
    FirebaseHelper helper;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_title = findViewById(R.id.et_title);
        et_body = findViewById(R.id.et_body);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        helper = new FirebaseHelper(this);
        toolbar = findViewById(R.id.toolbar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Post");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            item = getIntent().getParcelableExtra("postItem", PostItemModel.class);
        }

        et_title.setText(item.getTitle());
        et_body.setText(item.getBody());

        btn_delete.setOnClickListener(v->{
            progressDialog.show();
            helper.deletePost(item.getId());
        });

        btn_update.setOnClickListener(v->{
            progressDialog.show();
            item.setTitle(String.valueOf(et_title.getText()).trim());
            item.setBody(String.valueOf(et_body.getText()).trim());
            helper.updatePost(item);
        });
    }

    @Override
    public void onListFetchSuccessful(ArrayList<PostItemModel> list) {

    }

    @Override
    public void onError(String errorMessage) {
        progressDialog.dismiss();
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String successMessage) {
        progressDialog.dismiss();
        finish();
        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show();
    }
}