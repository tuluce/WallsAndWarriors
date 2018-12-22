package com.oops.wallsandwarriors.util;

import java.util.Iterator;
import java.util.List;

/**
 * A class for miscellaneous algorithm methods
 * @author Emin Bahadir Tuluce
 */
public class AlgorithmUtils {

    /**
     * A thread-safe method to remove an element from a list
     * @param list input list
     * @param element the element in the list that will be removed
     * @param <T> type of the element
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
