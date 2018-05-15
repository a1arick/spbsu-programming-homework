package com.a1arick.spbsu.control;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    private final int n;
    private List<Integer> values = new ArrayList<>();
    private int count = 0;
    private Integer chosenI = null, chosenJ = null;

    public Controller(int n) {
        this.n = n;
    }

    public Integer getValue(int i, int j) {
        return values.get(i * n + j);
    }

    public boolean choose(int i, int j) {
        count++;
        if (chosenI == null) {
            chosenI = i;
            chosenJ = j;
            return true;
        } else {
            boolean isEqual = getValue(chosenI, chosenJ).equals(getValue(i, j));
            if (!isEqual) {
                count -= 2;
            }
            chosenI = null;
            chosenJ = null;
            return isEqual;
        }
    }

    public boolean isWin() {
        return count == n * n;
    }

    public void clear() {
        count = 0;
        chosenI = null;
        chosenJ = null;
        int size = n * n;
        values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            values.add(i < size / 2 ? 0 : 1);
        }
        Collections.shuffle(values);
    }
}
