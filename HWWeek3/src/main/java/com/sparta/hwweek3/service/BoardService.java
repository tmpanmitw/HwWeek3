package com.sparta.hwweek3.service;

import com.sparta.hwweek3.dto.BoardRequestDto;
import com.sparta.hwweek3.entity.Board;
import com.sparta.hwweek3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class BoardService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final BoardRepository boardRepository;

    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도록
    // 스프링에게 알려줌
    //@RequiredArgsConstructor으로 대체
    //    public BoardService(BoardRepository boardRepository) {
    //        this.boardRepository = boardRepository;
    //    }

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board1 = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board1.update(requestDto);
        return board1.getId();
    }
}