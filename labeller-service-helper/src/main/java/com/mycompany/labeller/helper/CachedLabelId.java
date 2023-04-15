package com.mycompany.labeller.helper;

import com.mycompany.labeller.domain.data.attributes.LabelId;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ador
 */
public class CachedLabelId extends LabelId {

    private CachedLabelId(long id) {
        super(id);
    }

    private static final Map<Long, CachedLabelId> CACHE = new HashMap<>();

    public static LabelId of(Long id) {
        if (id == null) {
            return null;
        }

        synchronized (CACHE) {
            if (CACHE.containsKey(id)) {
                return CACHE.get(id);
            } else {
                CachedLabelId cachedLabelId = new CachedLabelId(id);

                CACHE.put(id, cachedLabelId);
                return cachedLabelId;
            }
        }
    }
}
