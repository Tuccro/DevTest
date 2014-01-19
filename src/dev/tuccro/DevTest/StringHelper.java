package dev.tuccro.DevTest;

import android.util.Log;

import java.util.ArrayList;

public class StringHelper {

    public static ArrayList<String> stringArrayMaker(){

        Log.d("MyLog","Начинаю создавать массив...");

        ArrayList<String> strings = new ArrayList<String>();

        int arrayLength = (int) (1+Math.random()*100);


        for (int k=0; k<arrayLength; k++){

            addRandomString(strings);
        }

        return strings;
    }

    public static void addRandomString(ArrayList<String> strings){

        int stringLength = (int) (1+Math.random()*10); //количество символов от 1 до 11
        char chars[] = new char[stringLength];

        for(int k=0; k<stringLength; k++){
            chars[k] =(char) (33+Math.random()*93); //часть Unicode таблицы, содержащая цифры, латинские буквы и знаки препинания
        }

        strings.add(String.valueOf(chars));
    }

    public static void changeStrings(ArrayList<String> strings, int num1, int num2) {
        String temp = strings.get(num1);

        strings.set(num1, strings.get(num2));
        strings.set(num2, temp);

    }



}
