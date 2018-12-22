package com.oops.wallsandwarriors.util;

import java.util.Iterator;
import java.util.List;

public class AlgorithmUtils {

    /**
     * Method to remove an element from a list
     * @param list, input list
     * @param element, the element in the list that will be removed
     * @param <T>, type of the element
     */
    public static <T> void concurrentRemove(List list, T element) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                iterator.remove();
                return;
            }
        }
    }
    
}
