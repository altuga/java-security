package com.kodcu.question32;

import java.util.Iterator;
import java.util.List;

/*
TODO
 1 - Run the LordOfTheLoops.java
 2 - Did you spot the bug?
*/
public class LordOfTheLoops {

    public static void exec(List<String> n, List<Integer> a) {

        for (String names : n) {
            System.out.println(" --> " + names );
        }


        for (Integer ages : a) {
            System.out.println( "--> "  + ages );
        }


    }

    public static void main(String[] args) {

        List<String> names = List.of("Lurtz", "Saruman" , "Gollum" , "Boromir" , "Legolas");
        List<Integer> ages  = List.of(40, 50, 160, 65, 1040);

        exec(names, ages);

    }

}
