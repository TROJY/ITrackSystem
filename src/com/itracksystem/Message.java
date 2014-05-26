package com.itracksystem;
public class Message {
     
    int _id;
    String _msg;
   String date;
     
    public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
	public Message(){
         
    }
    public Message(int id, String msg ,String date){
        this._id = id;
        this._msg = msg;
        this.date=date;
    }
     
    public Message(String msg){
        this._msg = msg;
    }
    public int getID(){
        return this._id;
    }
     
    public void setID(int id){
        this._id = id;
    }
     
    public String getMsg(){
        return this._msg;
    }
     
    public void setMsg(String msg){
        this._msg = msg;
    }
}