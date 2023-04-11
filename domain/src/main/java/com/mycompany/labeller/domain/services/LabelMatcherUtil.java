package com.mycompany.labeller.domain.services;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.NullableStringAttribute;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 *
 * @author ador
 */
public class LabelMatcherUtil {

    public static Stream<LabelClassifierData> getMatchingLabels(String text, Stream<LabelClassifierData> labels) {
        return labels.filter(label -> matches(text, label));
    }

    static boolean matches(String text, LabelClassifierData classifierData) {
        return matches(text, getPatterns(classifierData));
    }

    static boolean matches(String text, String pattern) {
        return text.toUpperCase().contains(pattern.toUpperCase());
    }

    private static boolean matches(String text, Stream<String> patterns) {
        if (text == null) {
            throw new LabellerException("text must be non null!");
        }
        
        String trimedText = text.trim();
        
        if (trimedText.isBlank()) {
            throw new LabellerException("text must be non empty!");
        }

        return patterns.anyMatch(pattern
                -> matches(trimedText, pattern)
        );
    }

    static Stream<String> getPatterns(LabelClassifierData classifierData) {
        String value = NullableStringAttribute.getValue(classifierData);
        if (value == null || value.isBlank()) {
            return Stream.empty();
        }

        return cut(value);
    }

    private static Stream<String> cut(String pattern) {
        boolean inQuots = false;
        int start = 0;

        LinkedList<String> patterns = new LinkedList<>();

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '"' && !inQuots) {
                inQuots = true;
                start = i + 1;
            } else if (pattern.charAt(i) == '"' && inQuots) {
                inQuots = false;
                patterns.add(pattern.substring(start, i));
                start = i + 1;
            } else if (Character.isWhitespace(pattern.charAt(i)) && !inQuots) {
                if (start == i) {
                    start = i;
                } else {
                    patterns.add(pattern.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start != pattern.length()) {
            patterns.add(pattern.substring(start, pattern.length()));
        }

        return patterns.stream();
    }

}
