package com.example.mylab.fbtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Azinova on 1/16/2018.
 */

public class AdapterTest extends RecyclerView.Adapter<AdapterTest.VData>{
    @Override
    public VData onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VData holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VData extends RecyclerView.ViewHolder {

        public VData(View itemView) {
            super(itemView);
        }
    }
}
