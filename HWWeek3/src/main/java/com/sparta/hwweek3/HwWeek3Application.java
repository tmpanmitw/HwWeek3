package com.sparta.hwweek3;

import com.sparta.hwweek3.dto.BoardRequestDto;
import com.sparta.hwweek3.entity.Board;
import com.sparta.hwweek3.repository.BoardRepository;
import com.sparta.hwweek3.service.BoardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class HwWeek3Application {

    public static void main(String[] args) {
        SpringApplication.run(HwWeek3Application.class, args);
    }
    // Week02Application.java 의 main 함수 아래에 붙여주세요.
    //[코드스니펫] JPA 실행 코드
    @Bean
    public CommandLineRunner demo(BoardRepository boardRepository, BoardService boardService) {
        return (args) -> {
            // 데이터 저장하기
            boardRepository.save(new Board("타이틀1", "작성자1", "내용1", "비밀번호1"));
            boardRepository.save(new Board("타이틀2", "작성자2", "내용2", "비밀번호2"));
            boardRepository.save(new Board("타이틀3", "작성자3", "내용3", "비밀번호3"));

            // 데이터 전부 조회하기
            List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc();
            for (int i=0; i<boardList.size(); i++) {
                Board board = boardList.get(i);
                System.out.println(board.getId());
                System.out.println(board.getTitle());
                System.out.println(board.getCreater());
                System.out.println(board.getContents());
                System.out.println(board.getPassword());
            }

            BoardRequestDto requestDto = new BoardRequestDto("타이틀수정", "작성자수정","내용수정","비밀번호수정");
            boardService.update(1L, requestDto);
            boardList = boardRepository.findAll();
            for (int i=0; i<boardList.size(); i++) {
                Board board = boardList.get(i);
                System.out.println(board.getId());
                System.out.println(board.getTitle());
                System.out.println(board.getCreater());
                System.out.println(board.getContents());
                System.out.println(board.getPassword());
            }

//            // 데이터 하나 조회하기
//            Board board = boardRepository.findById(1L).orElseThrow(
//                    () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
//            );

//            boardRepository.deleteAll();

        };
    }

}
