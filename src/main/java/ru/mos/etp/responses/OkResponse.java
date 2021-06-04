package ru.mos.etp.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.mos.etp.dto.Record;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@ApiModel(description = "Success response")
public class OkResponse {

    public OkResponse(String count, List<Record> records){
        this.count = count;
        this.records = records;
    }

    @ApiModelProperty(value = "Count of records", example = "\"42\"", required = true, position = 1)
    private String count;

    @ApiModelProperty(value = "Records",  required = true, position = 2)
    private List<Record> records;

    public String getCount() {return count;}
    public void setCount(String count) {this.count = count;}

    public List<Record> getRecords() {return records;}
    public void setRecords(List<Record> records) {this.records = records;}
}
