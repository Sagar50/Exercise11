//Sagar patel CSCI1660 3/29/2020
package com.company.Patel;

abstract class WeatherData {
    private String dataType;
    private Double measuredValue;

    WeatherData(String dataType, Double measuredValue) {
        this.dataType = dataType;
        this.measuredValue = measuredValue;
    }

    public String getDataType() {
        return dataType;
    }

    public Double getMeasuredValue() {
        return measuredValue;
    }

    abstract public String getUpdateMessage();
}
