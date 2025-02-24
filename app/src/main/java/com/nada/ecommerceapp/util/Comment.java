package com.nada.ecommerceapp.util;

public class Comment {
    private String  content,uId,uname;


    public Comment() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }



    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }



    public Comment(String content, String uId,  String uname) {
        this.content = content;
        this.uId = uId;

        this.uname = uname;

    }
}
