package com.mycompany.labeller.domain.attribute;

import com.mycompany.labeller.domain.data.attributes.GetLabelsForStringText;
import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelRangeFrom;
import com.mycompany.labeller.domain.data.attributes.LabelRangeLimit;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ador
 */
public class AttributeTests {

    @Test
    public void expectInvalidClassifierDataThrowsException() {
        Assertions.assertThatThrownBy(
                () -> new LabelClassifierData("some invalid \" string")
        ).isInstanceOf(LabellerException.class);
    }
    
    @Test
    public void expectEmptyGetLabelsForStringTextThrowsException() {
        Assertions.assertThatThrownBy(
                () -> new GetLabelsForStringText("")
        ).isInstanceOf(LabellerException.class);
    }
    
    @Test
    public void expectNullGetLabelsForStringTextThrowsException() {
        Assertions.assertThatThrownBy(
                () -> new GetLabelsForStringText(null)
        ).isInstanceOf(LabellerException.class);
    }
    
    @Test
    public void expect0VersionThrowsException() {
        Assertions.assertThatThrownBy(
                () -> new LabelVersion(0)
        ).isInstanceOf(LabellerException.class);
    }
    
    @Test
    public void expectNegativeFromThrowsException() {
        Assertions.assertThatThrownBy(
                () -> new LabelRangeFrom(-6)
        ).isInstanceOf(LabellerException.class);
    }
    
    @Test
    public void expectZeroLimitThrowsException() {
        Assertions.assertThatThrownBy(
                () -> new LabelRangeLimit(0)
        ).isInstanceOf(LabellerException.class);
    }
    
}
