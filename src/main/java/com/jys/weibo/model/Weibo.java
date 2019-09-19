package com.jys.weibo.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

@Document(indexName = "wb", type = "wb")
public class Weibo implements Serializable {

    private static final long serialVersionUID = -1469001180247736550L;
    public int id;
    public int userId;
    public int wbType;
    public Date wbSendTime;
    public Date wbCreateTime;
    public Date wbModifyTime;
    public int wbImageId;
    public int wbVideoId;
    public int wbMusicId;
    public String wbContent;
    public int wbCommentId;
    public int wbCommentCnt;
    public int wbZanCnt;
    public int wbZfCnt;
    public int wbScCnt;
    public int wbLookCnt;
    public WbUser wbUser;
    public MultipartFile file;//图片

    public MultipartFile getFile1() {
        return file1;
    }
    @Transient
    public void setFile1(MultipartFile file1) {
        this.file1 = file1;
    }

    public MultipartFile file1;//视频
    public String wbImagePath;

    public String wbVideoPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWbType() {
        return wbType;
    }

    public void setWbType(int wbType) {
        this.wbType = wbType;
    }

    public Date getWbSendTime() {
        return wbSendTime;
    }

    public void setWbSendTime(Date wbSendTime) {
        this.wbSendTime = wbSendTime;
    }

    public Date getWbCreateTime() {
        return wbCreateTime;
    }

    public void setWbCreateTime(Date wbCreateTime) {
        this.wbCreateTime = wbCreateTime;
    }

    public Date getWbModifyTime() {
        return wbModifyTime;
    }

    public void setWbModifyTime(Date wbModifyTime) {
        this.wbModifyTime = wbModifyTime;
    }

    public int getWbImageId() {
        return wbImageId;
    }

    public void setWbImageId(int wbImageId) {
        this.wbImageId = wbImageId;
    }

    public int getWbVideoId() {
        return wbVideoId;
    }

    public void setWbVideoId(int wbVideoId) {
        this.wbVideoId = wbVideoId;
    }

    public int getWbMusicId() {
        return wbMusicId;
    }

    public void setWbMusicId(int wbMusicId) {
        this.wbMusicId = wbMusicId;
    }

    public String getWbContent() {
        return wbContent;
    }

    public void setWbContent(String wbContent) {
        this.wbContent = wbContent;
    }

    public int getWbCommentId() {
        return wbCommentId;
    }

    public void setWbCommentId(int wbCommentId) {
        this.wbCommentId = wbCommentId;
    }

    public int getWbCommentCnt() {
        return wbCommentCnt;
    }

    public void setWbCommentCnt(int wbCommentCnt) {
        this.wbCommentCnt = wbCommentCnt;
    }

    public int getWbZanCnt() {
        return wbZanCnt;
    }

    public void setWbZanCnt(int wbZanCnt) {
        this.wbZanCnt = wbZanCnt;
    }

    public int getWbZfCnt() {
        return wbZfCnt;
    }

    public void setWbZfCnt(int wbZfCnt) {
        this.wbZfCnt = wbZfCnt;
    }

    public int getWbScCnt() {
        return wbScCnt;
    }

    public void setWbScCnt(int wbScCnt) {
        this.wbScCnt = wbScCnt;
    }

    public int getWbLookCnt() {
        return wbLookCnt;
    }

    public void setWbLookCnt(int wbLookCnt) {
        this.wbLookCnt = wbLookCnt;
    }

    public WbUser getWbUser() {
        return wbUser;
    }

    public void setWbUser(WbUser wbUser) {
        this.wbUser = wbUser;
    }

    public MultipartFile getFile() {
        return file;
    }
    @Transient
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getWbImagePath() {
        return wbImagePath;
    }

    public void setWbImagePath(String wbImagePath) {
        this.wbImagePath = wbImagePath;
    }

    public String getWbVideoPath() {
        return wbVideoPath;
    }

    public void setWbVideoPath(String wbVideoPath) {
        this.wbVideoPath = wbVideoPath;
    }
}
