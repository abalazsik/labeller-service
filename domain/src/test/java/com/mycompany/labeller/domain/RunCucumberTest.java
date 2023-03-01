
package com.mycompany.labeller.domain;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


/**
 *
 * @author ador
 */

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("domain")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber.html")
public class RunCucumberTest {
    
}
