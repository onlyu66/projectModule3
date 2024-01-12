package com.ra.model;

public interface IProduct {
    final float MIN_INTEREST_RATE = 0.2F;
    void inputData();
    void displayData();
    double calProfit();
}
