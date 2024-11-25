package spring_study.springmvc.boards;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.springmvc.domain.Board;

import java.util.List;
import java.util.Optional;



@Transactional
public interface BoardRepositoryImpl extends JpaRepository<Board, Long>, BoardRepository {
    @Override
    Board save(Board board);

    @Override
    List< Board > findAll( );

    @Override
    Optional<Board> findById( double id);
}
