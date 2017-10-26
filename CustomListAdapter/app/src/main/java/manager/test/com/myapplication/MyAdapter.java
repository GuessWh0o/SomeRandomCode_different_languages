package manager.test.com.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Maks on 9/8/2017.
 */

public class MyAdapter extends ArrayAdapter<ViewTabTemplete> {

    public MyAdapter (Context context, ArrayList<ViewTabTemplete> tab) {
        super(context, 0, tab);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewTabTemplete tab = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tab, parent, false);
        }
        TextView subjectName = (TextView) convertView.findViewById(R.id.subjectName);
        TextView resputasValue = (TextView) convertView.findViewById(R.id.resputas_value);
        TextView pruebasCompletas = (TextView) convertView.findViewById(R.id.pruebas_value);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        subjectName.setText(tab.subjectName);
        pruebasCompletas.setText(tab.tasksCompleted);
        resputasValue.setText(tab.correctAnswers);
        imageView.setImageResource(tab.imageView);
        convertView.setBackgroundColor(tab.color);

        return convertView;
    }
}
