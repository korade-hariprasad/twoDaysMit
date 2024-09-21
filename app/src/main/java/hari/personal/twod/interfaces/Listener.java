package hari.personal.twod.interfaces;

import java.util.ArrayList;

import hari.personal.twod.models.PostItemModel;

public interface Listener {
    void onListFetchSuccessful(ArrayList<PostItemModel> list);
    void onError(String errorMessage);
    void onSuccess(String successMessage);
}