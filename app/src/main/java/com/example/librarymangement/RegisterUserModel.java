package com.example.librarymangement;

public class RegisterUserModel {
    private String name;
    private String id;
    private String username;

    private String password;

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getid() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String cname;
    private String cid;
    private String phone;
    private String fbook;
    private String sbook;
    private String tbook;

    public String getcname(){return cname;}
    public void setcname(String cname){this.cname=cname;}
    public String getcid(){return cid;}
    public void setcid(String cid){this.cid=cid;}
    public String getphone(){return phone;}
    public void setphone(String phone){this.phone=phone;}
    public String getfbook(){return fbook;}
    public void setfbook(String fbook){this.fbook=fbook;}
    public String getsbook(){return sbook;}
    public void setsbook(String sbook){this.sbook=sbook;}
    public String gettbook(){return tbook;}
    public void settbook(String tbook){this.tbook=tbook;}

    private String bname;
    private String bid;
    private String price;

    public String getbname(){return bname;}
    public void setbname(String bname){this.bname=bname;}
    public String getprice(){return price;}
    public void setprice(String price){this.price=price;}

}
