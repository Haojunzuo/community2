package com.cjh.community2.entity;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String title;
    private String description;
    private long gmtcreate;
    private long gmtmodified;
    private int creator;
    private int commentcount;
    private int viewcount;
    private int likecount;
    private String tag;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public long getGmtcreate() {
//        return gmtcreate;
//    }
//
//    public void setGmtcreate(long gmtcreate) {
//        this.gmtcreate = gmtcreate;
//    }
//
//    public long getGmtmodified() {
//        return gmtmodified;
//    }
//
//    public void setGmtmodified(long gmtmodified) {
//        this.gmtmodified = gmtmodified;
//    }
//
//    public int getCreator() {
//        return creator;
//    }
//
//    public void setCreator(int creator) {
//        this.creator = creator;
//    }
//
//    public int getCommentcount() {
//        return commentcount;
//    }
//
//    public void setCommentcount(int commentcount) {
//        this.commentcount = commentcount;
//    }
//
//    public int getViewcount() {
//        return viewcount;
//    }
//
//    public void setViewcount(int viewcount) {
//        this.viewcount = viewcount;
//    }
//
//    public int getLikecount() {
//        return likecount;
//    }
//
//    public void setLikecount(int likecount) {
//        this.likecount = likecount;
//    }
//
//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }
}
