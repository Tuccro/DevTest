package dev.tuccro.DevTest;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableMaker {

    final private int arraySize;
    final private TableLayout tableLayout;


    private TableRow[] tableRows;
    private TextView textView;
    private ImageView imageView;

    public TableMaker(ArrayList<String> strings, Context context){

        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.image);

        tableLayout = new TableLayout(context);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        tableLayout.setStretchAllColumns(true);

        arraySize = strings.size();
        tableRows = new TableRow[arraySize];

        RowsMaker(strings, context);

        //tableLayout.addView(imageView);

        for (int k=0; k<arraySize; k++){

            tableLayout.addView(tableRows[k]);

        }
    }

    private void RowsMaker(ArrayList<String> strings, Context context){



        for (int k=0; k<arraySize; k++){
            tableRows[k] = new TableRow(context);
            //tableRows[k].addView(imageView);
            tableRows[k].addView(textViewMaker(Integer.toString(k+1), context));
            tableRows[k].addView(textViewMaker(strings.get(k), context));

        }
    }

    private TextView textViewMaker(String string, Context context){
        textView = new TextView(context);
        textView.setText(string);
        //textView.setTextColor(Color.RED);
        return textView;
    }

    public TableLayout getTable(){
        return tableLayout;
    }
}
