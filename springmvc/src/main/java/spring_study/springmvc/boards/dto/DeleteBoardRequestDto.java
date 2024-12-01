package spring_study.springmvc.boards.dto;

import lombok.*;

@Getter
public class DeleteBoardRequestDto  extends PasswordMatchRequestDto{
    public DeleteBoardRequestDto(){
        super();
    }
    @Builder
    public DeleteBoardRequestDto( String inputPassword ) {
        super( inputPassword );
    }
}
