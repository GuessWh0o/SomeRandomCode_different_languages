package manager.test.com.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ViewTabTemplete> arrayOfSubjects = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(this, arrayOfSubjects);
        ListView listView = (ListView) findViewById(R.id.lstAd);
        listView.setAdapter(adapter);

        ViewTabTemplete tab1 = new ViewTabTemplete("LMatematica", "4", "15", R.drawable.matematica_icon, ContextCompat.getColor(this, R.color.red));
        ViewTabTemplete tab2 = new ViewTabTemplete("Lengua Espanola", "0", "0", R.drawable.lengua_espanola_icon, ContextCompat.getColor(this, R.color.green));
        ViewTabTemplete tab3 = new ViewTabTemplete("Ciencias Naturales", "0", "0", R.drawable.ciencias_naturales_icon, ContextCompat.getColor(this, R.color.lightBlue));
        ViewTabTemplete tab4 = new ViewTabTemplete("Ciencias Sociales", "0", "0", R.drawable.ciencias_sociales_icon, ContextCompat.getColor(this, R.color.yellow));
        ViewTabTemplete tab5 = new ViewTabTemplete("Pruebas Mixtas", "0", "0", R.drawable.pruebas_mixtas_icon, ContextCompat.getColor(this, R.color.purple));
        adapter.add(tab1);
        adapter.add(tab2);
        adapter.add(tab3);
        adapter.add(tab3);
        adapter.add(tab4);
        adapter.add(tab5);
    }
}
