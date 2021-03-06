package com.example.msaproj;

import java.time.Month;

public class IncomesExpenses {
    private String type;
    private String category;
    private String sum;
    private String month;
    private String user_uid;

    public IncomesExpenses(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
