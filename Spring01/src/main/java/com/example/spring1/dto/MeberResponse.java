package com.example.spring1.dto;

import org.springframework.stereotype.Component;

/*
{
    "error":0,
    "message":"",
    "insertId":15,
    "member":member
}
*/
@Component
public class MeberResponse {
    private int error;
    private String message;
    private int insertId;
    private Member member;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getInsertId() {
        return insertId;
    }

    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
