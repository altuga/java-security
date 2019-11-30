package com.kodcu.question37;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *  1 - How do you transform current Exception in to IndexOutOfBoundsException -
 *  Exception Translation :
 *  Convert LowerLevelException to HigherLevelException
 *
 * @param <E> Type of the Element
 */
public class Sorting<E> {

    private static List myList = new ArrayList();

    /**
    * Returns the element at the specified position in this list.
    *
    * @throws IndexOutOfBoundsException if the index is out of range
    *                                   ({@code index < 0 || index >= size()}).
    */
    public E get(int index) throws Throwable{
        ListIterator<E> i = myList.listIterator();
        try {
            return i.next();
        } catch (NoSuchElementException e) {
            //throw new IndexOutOfBoundsException("Index: " + index); // 3
            throw new IndexOutOfBoundsException("Index: " + index).initCause(e);
        }
    }



    public static void main(String[] args) throws Throwable{
        Sorting<String> sorting = new Sorting<String>();
        System.out.println(sorting.get(9));
    }


}
