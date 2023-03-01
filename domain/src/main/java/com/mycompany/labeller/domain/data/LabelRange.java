package com.mycompany.labeller.domain.data;

/**
 *
 * @author ador
 */
public class LabelRange {

    public final int from;
    public final int limit;

    public LabelRange(int from, int limit) {
        if (from < 0) {
            throw new IllegalArgumentException("from");
        }
        this.from = from;
        if (limit < 1) {
            throw new IllegalArgumentException("limit");
        }
        this.limit = limit;
    }

    public int getFrom() {
        return from;
    }

    public int getLimit() {
        return limit;
    }

}
