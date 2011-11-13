package com.mycompany.mywebapp.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyWebAppModel {
    private final List<Integer> listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));

    public List<Integer> getListOfNumbers() {
        return listOfNumbers;
    }
}
