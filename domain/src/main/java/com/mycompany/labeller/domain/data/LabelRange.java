package com.mycompany.labeller.domain.data;

import com.mycompany.labeller.domain.data.attributes.LabelRangeFrom;
import com.mycompany.labeller.domain.data.attributes.LabelRangeLimit;

/**
 *
 * @author ador
 */
public class LabelRange extends DomainObject {
    
    private final LabelRangeFrom from;
    private final LabelRangeLimit limit;
    
    public LabelRange(LabelRangeFrom from, LabelRangeLimit limit) {
        notNull(from, limit);
        this.from = from;
        this.limit = limit;
    }
    
    public LabelRangeFrom getFrom() {
        return from;
    }
    
    public LabelRangeLimit getLimit() {
        return limit;
    }
    
}
