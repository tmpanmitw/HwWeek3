package com.sparta.hwweek3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hwweek3.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity // 테이블임을 나타냅니다.
public class Board extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;

    @Column(nullable = false)
    private String creater;

    @Column(nullable = false)
    private String contents;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public Board(String title, String creater, String contents, String password) { //생성자
        this.title = title;
        this.creater = creater;
        this.contents = contents;
        this.password = password;
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.creater = requestDto.getCreater();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.creater = requestDto.getCreater();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
}