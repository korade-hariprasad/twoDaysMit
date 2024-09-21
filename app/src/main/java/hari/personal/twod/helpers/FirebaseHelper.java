package hari.personal.twod.helpers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import hari.personal.twod.interfaces.Listener;
import hari.personal.twod.models.PostItemModel;

public class FirebaseHelper {

    FirebaseFirestore db;
    private static final String COLLECTION = "posts";
    Listener listener;

    public FirebaseHelper(Listener listener) {
        db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public void addPost(String title, String body) {
        String id = db.collection(COLLECTION).document().getId();

        db.collection(COLLECTION).document(id).set(new PostItemModel(title, body, id)
                ).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            listener.onSuccess("Inserted");
                        } else {
                            listener.onError("Insertion Failed");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onError("Something went wrong");
                    }
                });
    }

    public void getAllPosts() {
        ArrayList<PostItemModel> list = new ArrayList<>();
        db.collection(COLLECTION).get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            QuerySnapshot querySnapshot = task.getResult();
                            for(DocumentSnapshot doc : querySnapshot){
                                PostItemModel item = doc.toObject(PostItemModel.class);
                                list.add(item);
                            }
                            listener.onListFetchSuccessful(list);
                        }
                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onError("Something went wrong");
            }
        });
    }

    public void deletePost(String id) {
        db.collection(COLLECTION).document(id).delete().addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) listener.onSuccess("Post Deleted");
                        else listener.onError("Cannot delete Post");
                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onError("Something went wrong");
            }
        });
    }

    public void updatePost(PostItemModel item) {
        String id = item.getId();

        db.collection(COLLECTION).document(id).set(item)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            listener.onSuccess("Updated");
                        } else {
                            listener.onError("Updating Failed");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onError("Something went wrong");
                    }
                });
    }
}
