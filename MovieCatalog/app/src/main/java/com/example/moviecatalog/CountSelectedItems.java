package com.example.moviecatalog;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CountSelectedItems {

    List<Integer> lst;

    public void setLst(List<Integer> lst) {
        this.lst = lst;
    }

    public HashMap<Integer, Integer> countOccurrence() {

        for(int i = 0; i < lst.size(); i++) {

            int occurrence = Collections.frequency(lst, lst.get(i));

            MovieAdapter.mapItems.put(lst.get(i), occurrence);

        }

        return MovieAdapter.mapItems;

    }
}
