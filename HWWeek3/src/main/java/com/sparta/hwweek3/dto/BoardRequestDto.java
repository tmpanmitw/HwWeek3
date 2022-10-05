package com.sparta.hwweek3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardRequestDto {
    private String title;
    private String creater;
    private String contents;
    private String password;

    public BoardRequestDto(String title, String creater, String contents, String password) {
        this.title = title;
        this.creater = creater;
        this.contents = contents;
        this.password = password;
    }
}
