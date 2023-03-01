package com.mycompany.labeller.domain.services;

import java.util.LinkedList;

/**
 *
 * @author ador
 */
public class LabelMatcherUtil {

    public static boolean matches(String text, String pattern) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("text must be non empty");
        }
        if (pattern == null || pattern.isBlank()) {
            throw new IllegalArgumentException("pattern must be non empty");
        }

        return text.toUpperCase().contains(pattern.toUpperCase());
    }

    public static boolean matches(String text, String[] patterns) {
        for (String pattern : patterns) {
            if (matches(text, pattern)) {
                return true;
            }
        }
        return false;
    }
    
    /*
    public static String[] getPatterns(LabelClassifierData classifierData) {
        if (classifierData == null || classifierData.getValue() == null) {
            throw new IllegalArgumentException("classifiercata must be non null");
        }
        
        return cut(pattern);
    }

    private static String[] cut(String pattern) {
        boolean inQuots = false;
        int start = 0;
        
        LinkedList<String> patterns = new LinkedList<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '"' && !inQuots) {
                inQuots = true;
            } else if (pattern.charAt(i) == '"' && inQuots) {
                inQuots = false;
                patterns.add(pattern.substring(start, i));
                start = i;
            }
        }
    }
*/
    
}
