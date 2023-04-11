package com.mycompany.labeller.domain.services;

import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ador
 */
public class LabelMatcherUtilTest {

    @Test
    public void expectMatchesReturnsFalseWhenParameterIsNull() {
        Assertions.assertThat(LabelMatcherUtil.matches("text", (LabelClassifierData) null)).isFalse();
    }

    @Test
    public void expectMatchesReturnsFalse() {
        Assertions.assertThat(LabelMatcherUtil.matches("text", new LabelClassifierData(null))).isFalse();
    }

    @Test
    public void expectMatchesThrowsExceptionWhenTextIsNull() {
        Assertions.assertThatThrownBy(
                () -> LabelMatcherUtil.matches(null, new LabelClassifierData("data"))
        ).isInstanceOf(LabellerException.class);
    }
    
    @Test
    public void expectMatchesThrowsExceptionWhenTextIsEmpty() {
        Assertions.assertThatThrownBy(
                () -> LabelMatcherUtil.matches(" ", new LabelClassifierData("data"))
        ).isInstanceOf(LabellerException.class);
    }

    @Test
    public void expectGetPatternsReturnsEmpty() {
        Assertions.assertThat(LabelMatcherUtil.getPatterns(null)).isEmpty();
    }

    @Test
    public void expectGetPatternsReturnsSinglePattern() {
        Assertions.assertThatStream(LabelMatcherUtil.getPatterns(new LabelClassifierData("test")))
                .containsExactly("test");
    }

    @Test
    public void expectGetPatternsReturnsMultipleSimplePatterns() {
        Assertions.assertThat(LabelMatcherUtil.getPatterns(new LabelClassifierData("test junit spring")))
                .containsExactly("test", "junit", "spring");
    }

    @Test
    public void expectGetPatternsReturnsMultipleSimplePatternsWhitespace() {
        Assertions.assertThat(LabelMatcherUtil.getPatterns(new LabelClassifierData("test  junit")))
                .containsExactly("test", "junit");
    }

    @Test
    public void expectGetPatternsReturnsComplexPatterns() {
        Assertions.assertThat(LabelMatcherUtil.getPatterns(new LabelClassifierData("test \"junit spring\"")))
                .containsExactly("test", "junit spring");
    }

}
