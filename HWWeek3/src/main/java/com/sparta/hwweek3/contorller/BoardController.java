package com.sparta.hwweek3.contorller;

import com.sparta.hwweek3.dto.BoardRequestDto;
import com.sparta.hwweek3.entity.Board;
import com.sparta.hwweek3.repository.BoardRepository;
import com.sparta.hwweek3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

//   2. 전체 게시글 목록 조회 API
    @GetMapping("/api/boards")
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

//  3. 게시글 작성 API
    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.
    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        // requestDto 는, 생성 요청을 의미합니다.
        // 강의 정보를 만들기 위해서는 강의 제목과 튜터 이름이 필요하잖아요?
        // 그 정보를 가져오는 녀석입니다.

        // 저장하는 것은 Dto가 아니라 Course이니, Dto의 정보를 course에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        Board board = new Board(requestDto);

        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return boardRepository.save(board);
    }

//   4. 게시글 조회 API
    @GetMapping("/api/boards/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardRepository.findById(id).get();
    }

//    5. 게시글 비밀번호 확인 API
    @PostMapping("/api/boards/{id}")
    public String checkPasswordBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        if (board.getPassword().equals(requestDto.getPassword())){
            return "비밀번호 일치";
        }else{
            return "비밀번호 불일치"; ////서비스
        }
    }

//   6. 게시글 수정 API
    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.update(id, requestDto);
        return id;
    }

//  7. 게시글 삭제 API
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}