package hari.personal.twod.interfaces;

import java.util.ArrayList;

import hari.personal.twod.models.PostItemModel;

public interface Listener {
    public void onApiFetchSuccessful(ArrayList<PostItemModel> list);
    void onApiFetchError(String errorMessage);
}