package kakhavai.calpoly.edu.studybuddy;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kakhavai.calpoly.edu.studybuddy.MainActivity.Class;

/**
 * The main classlist adapter
 * @Author: Kian, Ani
 *
 */

public class ClassListAdapter extends ArrayAdapter<Class> {
    public ArrayList<Class> itemList;

    public ClassListAdapter(Context context, ArrayList<Class>items) {
        super(context, 0, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Class item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.classitem, parent, false);
        }

        TextView classText = (TextView)convertView.findViewById(R.id.classText);
        classText.setText(item.getClassName());

        //Assign a varying color based on position.. To be updated soon.
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLUE);
        } else {
            convertView.setBackgroundColor(Color.CYAN);
        }

        return convertView;
    }
}
