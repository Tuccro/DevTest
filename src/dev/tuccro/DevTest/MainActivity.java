package dev.tuccro.DevTest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<String> strings;
    private ScrollView scrollView;
    EditText editText, editText1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        scrollView = (ScrollView) findViewById(R.id.scrollView);

        strings = StringHelper.stringArrayMaker();

        printTable();

    }

    private void printTable(){

        TableMaker tableMaker = new TableMaker(strings,this);

        TableLayout tableLayout = new TableLayout(this);
        tableLayout = tableMaker.getTable();
        scrollView.removeAllViews();
        scrollView.addView(tableLayout);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 0, "Add new");
        menu.add(0, 2, 0, "Remove String");
        menu.add(0, 3, 0, "Sort");
        menu.add(0, 4, 0, "Exit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1: {
                StringHelper.addRandomString(strings);
                printTable();
                break;
            }
            case 2: {
                deleteDialog();
                break;
            }
            case 3: {
                sortDialog();
                break;
            }
            case 4: {
                System.exit(1);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteDialog() {

        editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Enter the number of string")
                .setView(editText)
                .setCancelable(true)
                .setNegativeButton("Remove",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                int number;

                                if (editText.getText().toString().trim().length() > 0){
                                    number = Integer.parseInt(editText.getText().toString());

                                    if (number>0&&number<=strings.size()) {
                                        strings.remove(number-1);
                                        printTable();
                                        dialog.cancel();
                                    }
                                } else {
                                    dialog.cancel();
                                }
                            }
                        }
                            );
                            AlertDialog alert = builder.create();
                            alert.show();
                        }

    private void sortDialog() {
        editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText1 = new EditText(this);
        editText1.setInputType(InputType.TYPE_CLASS_NUMBER);

        TableLayout tableLayout = new TableLayout(this);
        tableLayout.addView(editText);
        tableLayout.addView(editText1);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Enter the numbers of strings")
                .setView(tableLayout)
                .setCancelable(true)
                .setNeutralButton("Change",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int item1, item2;
                        int arraySize = strings.size();

                        if(editText.getText().toString().trim().length() > 0 &&
                                editText1.getText().toString().trim().length() > 0) {
                            item1 = Integer.parseInt(editText.getText().toString());
                            item2 = Integer.parseInt(editText1.getText().toString());

                            if (item1>0 && item1 <= arraySize &&
                                    item2 >0 && item2 <= arraySize &&
                                    item1 != item2) {
                                StringHelper.changeStrings(strings, item1-1, item2-1);
                                printTable();
                                dialogInterface.cancel();
                            }

                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    }
