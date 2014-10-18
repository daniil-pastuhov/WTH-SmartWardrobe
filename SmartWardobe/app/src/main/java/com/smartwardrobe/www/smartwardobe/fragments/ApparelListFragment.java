package com.smartwardrobe.www.smartwardobe.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.smartwardrobe.www.smartwardobe.Activities.ApparelDetailsActivity;
import com.smartwardrobe.www.smartwardobe.Adapters.WardrobeAdapter;
import com.smartwardrobe.www.smartwardobe.R;
import com.smartwardrobe.www.smartwardobe.database.Apparel;

import java.util.ArrayList;

/**
 * Created by eugene on 18.10.14.
 */
public class ApparelListFragment extends Fragment implements WardrobeAdapter.AdapterClickCallbacks{

    ArrayList<Apparel> apparels = new ArrayList<Apparel>();
    ListView list ;
    BaseAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        apparels = new ArrayList<Apparel>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = (View) inflater.inflate(R.layout.wardrobe_list, null);

        init(v);

        return v;
    }


    public void init(View view){

        list = (ListView) view.findViewById(R.id.wardrobe_list);
        adapter = new WardrobeAdapter(apparels, getActivity(), this);
        list.setAdapter(adapter);
        //apparels.addAll(Apparel.getAll());

    }

    @Override
    public void onResume(){
        super.onResume();
        apparels.clear();
        apparels.addAll(Apparel.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getActionBar().setHomeButtonEnabled(true);
        menu.getItem(0).setVisible(false);
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onListItemSelected(int position) {
        Apparel tmp = apparels.get(position);

        Intent intent = new Intent(getActivity(), ApparelDetailsActivity.class);
        intent.putExtra("apparelId", tmp.getId());
        startActivity(intent);
    }
}
