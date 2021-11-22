/**
 * Paging dto
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {
    private int currentPageNo = 1; // current page number
    private int recordCountPerPage = 20;// number of posts on one page (setting)
}
