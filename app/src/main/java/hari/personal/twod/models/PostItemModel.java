package hari.personal.twod.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PostItemModel implements Parcelable {

    private String title, body, id;

    public PostItemModel(String title, String body, String id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public PostItemModel() {}

    protected PostItemModel(Parcel in) {
        title = in.readString();
        body = in.readString();
        id = in.readString();
    }

    public static final Creator<PostItemModel> CREATOR = new Creator<PostItemModel>() {
        @Override
        public PostItemModel createFromParcel(Parcel in) {
            return new PostItemModel(in);
        }

        @Override
        public PostItemModel[] newArray(int size) {
            return new PostItemModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(id);
    }
}
