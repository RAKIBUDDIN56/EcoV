package com.example.eco_v;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends ListFragment {

    ItemSelected activity;
    public interface ItemSelected{
        void onItemSeleted(int  index);
    }


    public ListFrag() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(ItemSelected) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> data= new ArrayList<>();
        data.add("Aerosol Cans");
        data.add("Aluminium packing and foil");
        data.add("Asbestos");
        data.add("Batteries(Household");
        data.add("Batteries (Vehicle");
        data.add("Concrete");
        data.add("Corrugated Cardboard");
        data.add("Steel");
        data.add("Plastics (HDPE)");
        data.add("Glass");
        data.add("Plastic (PET)");
        data.add("Mixed papers");
        data.add("Newspapers");
        data.add("Used motor oil");
        data.add("Soiled Food ");


        setListAdapter(new ArrayAdapter< >(getActivity(), android.R.layout.simple_list_item_1, data));
        //activity.onItemSeleted(0);
        //phone is in landscape mode
        if(this.getActivity().findViewById(R.id.layout_land) !=null){
            activity.onItemSeleted(0);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        activity.onItemSeleted(position);
    }
}
