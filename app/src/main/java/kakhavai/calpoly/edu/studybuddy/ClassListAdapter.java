package kakhavai.calpoly.edu.studybuddy;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Kian on 10/6/2016.
 * This code was slightly stolen credits go to the authoer at
 * https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
 * Simply a guide to override this class because I had no prior knowledge.
 * If i get put on academic dishonesty for this I will cry like a man and you will be sad
 * So you better not do that.
 */

public class ClassListAdapter extends ArrayAdapter<String> {
    public ArrayList<String> itemList;

    public ClassListAdapter(Context context, ArrayList<String>items) {
        super(context, 0, items);
    }

    //
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        String item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.classitem, parent, false);
        }

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLUE);
        } else {
            convertView.setBackgroundColor(Color.CYAN);
        }




        return convertView;
    }


}
