package com.mycompany.labeller.h2.service;

import com.mycompany.labeller.commons.CachedLabelId;
import com.mycompany.labeller.h2.H2Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ador
 */
public class H2LabelRowMapper implements RowMapper<H2Label> {

    @Override
    public H2Label mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new H2Label(
                CachedLabelId.of(rs.getLong(H2Label.ID)),
                rs.getString(H2Label.NAME),
                rs.getString(H2Label.DESCRIPTION),
                rs.getString(H2Label.CLASSIFIER_DATA),
                rs.getBoolean(H2Label.TECHNICAL),
                CachedLabelId.of(rs.getLong(H2Label.PARENT)),
                rs.getTimestamp(H2Label.CREATION_DATE).toLocalDateTime(),
                rs.getTimestamp(H2Label.UPDATE_DATE).toLocalDateTime(),
                rs.getLong(H2Label.VERSION)
        );
    }

}
