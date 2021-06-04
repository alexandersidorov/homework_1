package ru.mos.etp.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@ApiModel(description = "Fail response")
public class ErrorResponse {

    @ApiModelProperty(value = "Error message", required = true, position = 1)
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
}
