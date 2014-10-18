package com.smartwardrobe.www.smartwardobe.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smartwardrobe.www.smartwardobe.Adapters.WardrobeAdapter;
import com.smartwardrobe.www.smartwardobe.R;

/**
 * Created by eugene on 18.10.14.
 */
public class FittingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = (View) inflater.inflate(R.layout.wardrobe_list, null);

        init(v);

        return v;
    }


    public void init(View view){

//        list = (ListView) view.findViewById(R.id.wardrobe_list);
//        adapter = new WardrobeAdapter(apparels, getActivity(), this);
//        list.setAdapter(adapter);
        //apparels.addAll(Apparel.getAll());

    }
}
