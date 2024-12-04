package spring_study.springmvc.boards.errors;

public class PasswordMismatchException extends Exception{
    private static final String message = "비밀번호가 일치하지 않습니다.";
    public PasswordMismatchException() {
        super( message);
    }
}
