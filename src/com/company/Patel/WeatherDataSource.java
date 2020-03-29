//Sagar patel CSCI1660 3/29/2020
package com.company.Patel;

interface WeatherDataSource {
    public void addListener(WeatherDataListener listener);
    public void removeListener(WeatherDataListener listener);
    public void update();
}
