package com.oops.wallsandwarriors.util;

import java.util.Iterator;
import java.util.List;

public class AlgorithmUtils {
    
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
