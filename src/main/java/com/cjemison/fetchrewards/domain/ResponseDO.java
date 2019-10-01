package com.cjemison.fetchrewards.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = ResponseDO.Builder.class)
public class ResponseDO {

  private final String input1;
  private final String input2;
  private final String answer;
  private final String error;

  protected ResponseDO(final String input1,
      final String input2,
      final String answer,
      final String error) {

    this.input1 = input1;
    this.input2 = input2;
    this.answer = answer;
    this.error = error;
  }

  public static ResponseDO.Builder builder() {
    return new ResponseDO.Builder();
  }

  @JsonProperty("input_1")
  public String getInput1() {
    return input1;
  }

  @JsonProperty("input_2")
  public String getInput2() {
    return input2;
  }

  @JsonProperty("answer")
  public String getAnswer() {
    return answer;
  }

  @JsonProperty("error")
  public String getError() {
    return error;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ResponseDO)) {
      return false;
    }
    final ResponseDO that = (ResponseDO) o;
    return Objects.equals(getInput1(), that.getInput1()) &&
        Objects.equals(getInput2(), that.getInput2()) &&
        Objects.equals(getAnswer(), that.getAnswer()) &&
        Objects.equals(getError(), that.getError());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getInput1(), getInput2(), getAnswer(), getError());
  }

  @Override
  public String toString() {
    return "ResponseDO{" +
        "input1='" + input1 + '\'' +
        ", input2='" + input2 + '\'' +
        ", answer='" + answer + '\'' +
        ", error='" + error + '\'' +
        '}';
  }

  @JsonPOJOBuilder(withPrefix = "")
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Builder {

    private String input1;
    private String input2;
    private String answer;
    private String error;

    public Builder() {
      this.input1 = StringUtils.EMPTY;
      this.input2 = StringUtils.EMPTY;
      this.answer = StringUtils.EMPTY;
      this.error = StringUtils.EMPTY;
    }

    @JsonProperty("input_1")
    public Builder input1(final String value) {
      this.input1 = defaultValue(value);
      return this;
    }

    @JsonProperty("input_2")
    public Builder input2(final String value) {
      this.input2 = defaultValue(value);
      return this;
    }

    @JsonProperty("answer")
    public Builder answer(final String value) {
      this.answer = defaultValue(value);
      return this;
    }

    @JsonProperty("error")
    public Builder error(final String value) {
      this.error = defaultValue(value);
      return this;
    }

    public ResponseDO build() {
      return new ResponseDO(input1,
          input2,
          answer,
          error);
    }

    private String defaultValue(final String value) {
      if (StringUtils.isNotBlank(value)) {
        return value.trim();
      }
      return StringUtils.EMPTY;
    }
  }
}
