package cn.mono.purchase.pojo;

import java.util.Date;

public class ApplyBoard {
    private Integer id;

    private Date startTime;

    private Date endTime;

    private String boardname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname == null ? null : boardname.trim();
    }

    @Override
    public String toString() {
        return "Apply_board{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", boardname='" + boardname + '\'' +
                '}';
    }

    public ApplyBoard() {
    }

    public ApplyBoard(Integer id, Date startTime, Date endTime, String boardname) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.boardname = boardname;
    }
}