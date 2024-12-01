package spring_study.springmvc.boards;

import jakarta.transaction.Transactional;
import spring_study.springmvc.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryImpl extends  BoardRepository {
    @Transactional
    @Override
    Board save(Board board);

    @Override
    List< Board > findAll( );

    @Override
    Optional<Board> findById( double id);
}
