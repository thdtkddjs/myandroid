package com.example.step17example;

public class TodoDto {
    public int num;
    public String content;
    public String regdate;

    public TodoDto() {}

    public TodoDto(int num, String content, String regdate) {
        this.num = num;
        this.content = content;
        this.regdate = regdate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
}
