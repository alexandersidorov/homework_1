package ru.mos.etp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

@ApiModel(description = "Record response")
public class Record {

    @ApiModelProperty(value = "id", required = true, position = 1)
    int id;

    @ApiModelProperty(value = "service number", required = true, position = 2)
    String serviceNumber;

    @ApiModelProperty(value = "status code", required = true, position = 3)
    Integer statusCode;

    @ApiModelProperty(value = "created", required = true, position = 4)
    LocalDateTime created;

    @ApiModelProperty(value = "msg id", required = true, position = 5)
    String msgId;

    @ApiModelProperty(value = "put", required = true, position = 6)
    LocalDateTime put;

    @ApiModelProperty(value = "reason code", required = true, position = 7)
    Integer reasonCode;

    @ApiModelProperty(value = "route", required = true, position = 8)
    boolean route;

    @ApiModelProperty(value = "direction", required = true, position = 9)
    String direction;

    public Record(int id, String serviceNumber, Integer statusCode, LocalDateTime created, String msgId, LocalDateTime put, Integer reasonCode, boolean route, String direction) {
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.statusCode = statusCode;
        this.created = created;
        this.msgId = msgId;
        this.put = put;
        this.reasonCode = reasonCode;
        this.route = route;
        this.direction = direction;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getServiceNumber() {return serviceNumber;}
    public void setServiceNumber(String serviceNumber) {this.serviceNumber = serviceNumber;}

    public Integer getStatusCode() {return statusCode;}
    public void setStatusCode(Integer statusCode) {this.statusCode = statusCode;}

    public LocalDateTime getCreated() {return created;}
    public void setCreated(LocalDateTime created) {this.created = created;}

    public String getMsgId() {return msgId;}
    public void setMsgId(String msgId) {this.msgId = msgId;}

    public LocalDateTime getPut() {return put;}
    public void setPut(LocalDateTime put) {this.put = put;}

    public Integer getReasonCode() {return reasonCode;}
    public void setReasonCode(Integer reasonCode) {this.reasonCode = reasonCode;}

    public boolean isRoute() {return route;}
    public void setRoute(boolean route) {this.route = route;}

    public String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction = direction;}
}
