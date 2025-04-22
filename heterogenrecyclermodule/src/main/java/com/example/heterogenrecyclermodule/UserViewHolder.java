package com.example.heterogenrecyclermodule;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder  extends RecyclerView.ViewHolder {

    private TextView userNameLabel, userHometownLabel;

    public UserViewHolder(View v) {
        super(v);
        userNameLabel = (TextView) v.findViewById(R.id.userItemTextName);
        userHometownLabel = (TextView) v.findViewById(R.id.userItemTextHometown);
    }

    public TextView getUserNameLabel() {
        return userNameLabel;
    }

    public void setUserNameLabel(TextView userNameLabel) {
        this.userNameLabel = userNameLabel;
    }

    public TextView getUserHometownLabel() {
        return userHometownLabel;
    }

    public void setUserHometownLabel(TextView userHometownLabel) {
        this.userHometownLabel = userHometownLabel;
    }
}