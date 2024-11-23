package spring_study.springmvc.boards;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.springmvc.domain.Board;

public interface JpaBoardRepository extends JpaRepository<Board, Long>, BoardRepository {
    @Override
    Board save(Board board);
}
