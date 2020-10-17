package kr.ac.yonsei.recommender.word.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {

    private int currentPageNo = 1;//현재 페이지 번호
    private int pageSize = 10;//페이지 리스트에 게시되는 페이지 건 수 (설정)
    private int recordCountPerPage = 20;//한 페이지당 게시되는 건 수 (설정)
    private int totalRecordCount = 0;//전체 건 수
    private int totalPageCount = 0;//전체 페이지 개수
    private int firstRecordIndex = 0;//첫 페이지 인덱스 번호(글 번호)
    private int lastRecordIndex = 0;//마지막 페이지 인덱스 번호(글 번호)
    private int firstPageNoOnPageList = 1;//페이지 리스트의 첫 페이지 번호
    private int lastPageNoOnPageList = 1;//페이지 리스트의 마지막 페이지 번호

    public int getTotalPageCount() {
        totalPageCount = ((getTotalRecordCount()-1)/getRecordCountPerPage()) + 1;
        return totalPageCount;
    }

    public int getFirstPageNoOnPageList() {
        firstPageNoOnPageList = ((getCurrentPageNo()-1)/getPageSize())*getPageSize() + 1;
        return firstPageNoOnPageList;
    }

    public int getLastPageNoOnPageList() {
        lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
        if(lastPageNoOnPageList > getTotalPageCount()){
            lastPageNoOnPageList = getTotalPageCount();
        }
        return lastPageNoOnPageList;
    }

    public int getFirstRecordIndex() {
        firstRecordIndex = (getCurrentPageNo()-1) * getRecordCountPerPage();
        return firstRecordIndex;
    }

    public int getLastRecordIndex() {
        lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
        return lastRecordIndex;
    }
}
