package com.mycompany.samplehospital.model;

import javax.xml.bind.annotation.XmlRootElement;
//handles all the errors and maps to specific type of error to json format

@XmlRootElement
public class Error {

    private String errorName;
    private int errorId;
    private String detail;

    public Error() {

    }

    public Error(String errorName, Integer errorId, String detail) {

        this.errorName = errorName;
        this.errorId = errorId;
        this.detail = detail;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
