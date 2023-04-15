package com.mycompany.labeller.jmx;

import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.services.LabelService;
import com.mycompany.labeller.helper.CachedLabelId;
import com.mycompany.labeller.helper.roles.Roles;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 *
 * @author ador
 */
@ManagedResource(
        objectName="bean:name=JMXLabellerService",
        description="JMXLabellerService",
        log=true,
        logFile="jmx.log",
        currencyTimeLimit=15,
        persistPolicy="OnUpdate",
        persistPeriod=200,
        persistLocation="foo",
        persistName="bar")
public class JMXLabellerService implements LabellerServiceMBean {

    @Autowired
    private LabelService labelService;

    @ManagedOperation(description="Get a Label by id")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name = "id", description = "the id")
    })
    @Override
    public Optional<Label> getById(long id) {
        return labelService.getById(CachedLabelId.of(id), Roles.Admin);
    }
}
