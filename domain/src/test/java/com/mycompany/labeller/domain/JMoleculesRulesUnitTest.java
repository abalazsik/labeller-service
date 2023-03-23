package com.mycompany.labeller.domain;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import static org.assertj.core.api.Assertions.assertThat;
import org.jmolecules.archunit.JMoleculesDddRules;

/**
 *
 * @author ador
 */
@AnalyzeClasses(packages = {
    "com.mycompany.labeller.domain.services",
    "com.mycompany.labeller.domain.repository",
    "com.mycompany.labeller.domain.data",
    "com.mycompany.labeller.domain.data.attributes"})
public class JMoleculesRulesUnitTest {

    @ArchTest
    public ArchRule dddRules = JMoleculesDddRules.all();

    @ArchTest
    public void detectsViolations(JavaClasses classes) {
        EvaluationResult result = JMoleculesDddRules.all().evaluate(classes);

        assertThat(result.hasViolation()).isFalse();
    }
}
