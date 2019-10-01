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
@JsonDeserialize(builder = RequestDO.Builder.class)
public class RequestDO {

  private final String input1;
  private final String input2;

  protected RequestDO(final String input1,
      final String input2) {
    this.input1 = input1;
    this.input2 = input2;
  }

  public static RequestDO.Builder builder() {
    return new RequestDO.Builder();
  }

  @JsonProperty("input_1")
  public String getInput1() {
    return input1;
  }

  @JsonProperty("input_2")
  public String getInput2() {
    return input2;
  }


  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RequestDO)) {
      return false;
    }
    final RequestDO requestDO = (RequestDO) o;
    return Objects.equals(getInput1(), requestDO.getInput1()) &&
        Objects.equals(getInput2(), requestDO.getInput2());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getInput1(), getInput2());
  }

  @Override
  public String toString() {
    return "RequestDO{" +
        "input1='" + input1 + '\'' +
        ", input2='" + input2 + '\'' +
        '}';
  }

  @JsonPOJOBuilder(withPrefix = "")
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Builder {

    private String input1;
    private String input2;

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

    public RequestDO build() {
      return new RequestDO(input1,
          input2);
    }

    private String defaultValue(final String value) {
      if (StringUtils.isNotBlank(value)) {
        return value.trim();
      }
      return StringUtils.EMPTY;
    }
  }
}