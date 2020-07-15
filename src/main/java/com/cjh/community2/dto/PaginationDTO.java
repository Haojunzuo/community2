package com.cjh.community2.dto;

import lombok.Data;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

//分页操作的页码信息管理
//PaginationDTO类是一个用来进行分页的数据传输对象，里面包括一个List存放泛型，还有关于页面是否有上下页，是否有第一最后一页，当前页面上的显示的页码等信息，时候用model传输到
//HTML页面上的就是PaginationDTO对象。
@Data
public class PaginationDTO<T> {
    private List<T> data;
    //是否有向前按钮
    private boolean showPrevious;
    //第一页按钮
    private boolean showFirstPage;
    //是否有向后按钮
    private boolean showNext;
    //最后一页按钮
    private boolean showEndPage;
    //当前页面
    private int page;
    //页面要展示的pages
    private List<Integer> pages = new ArrayList<>();

    private int totalPage;


    public void setPagination(int totalPage, int page) {
        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否显示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否显示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}
