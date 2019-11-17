package com.airhacks.data;

public class Mutable {

    private int[] array = new int[10];

    {
        // fill the array
        array[0] = 1;
        array[1] = 3;
        array[2] = 5;
        array[3] = 6;
        array[4] = 3;
        array[5] = 2;
        array[6] = 1;
        array[7] = 4;
        array[8] = 2;
        array[9] = 9;

    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] i) {
        array = i;
    }
}

