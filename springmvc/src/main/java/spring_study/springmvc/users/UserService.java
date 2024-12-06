package spring_study.springmvc.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_study.springmvc.domain.User;
import spring_study.springmvc.users.dto.JoinUserRequestDto;
import spring_study.springmvc.users.dto.JoinUserResponseDto;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public JoinUserResponseDto join( JoinUserRequestDto dto ) {
        // 등록: dto -> entity
        User user = User.builder()
                .email( dto.getEmail() )
                .name( dto.getName() )
                .password( dto.getPassword() )
                .build();

        // repository
        userRepository.save( user );

        // entity -> dto
        return JoinUserResponseDto.builder();
    }


}
