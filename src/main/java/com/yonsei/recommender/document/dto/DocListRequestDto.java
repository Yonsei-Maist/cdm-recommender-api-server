package com.yonsei.recommender.document.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class DocListRequestDto {

    @NotEmpty
    private String userId;

}
