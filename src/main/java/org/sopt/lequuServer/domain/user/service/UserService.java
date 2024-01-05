package org.sopt.lequuServer.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.lequuServer.domain.user.dto.request.SocialLoginRequestDto;
import org.sopt.lequuServer.domain.user.dto.response.UserLoginResponseDto;
import org.sopt.lequuServer.domain.user.model.SocialPlatform;
import org.sopt.lequuServer.domain.user.model.User;
import org.sopt.lequuServer.domain.user.repository.UserJpaRepository;
import org.sopt.lequuServer.global.auth.fegin.kakao.KakaoLoginService;
import org.sopt.lequuServer.global.auth.jwt.JwtProvider;
import org.sopt.lequuServer.global.auth.jwt.TokenDto;
import org.sopt.lequuServer.global.auth.security.UserAuthentication;
import org.sopt.lequuServer.global.exception.model.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.lequuServer.global.exception.enums.ErrorType.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final JwtProvider jwtProvider;
    private final UserJpaRepository userRepository;

    private final KakaoLoginService kakaoLoginService;

    @Transactional
    public UserLoginResponseDto login(String socialAccessToken, SocialLoginRequestDto request) {

        socialAccessToken = parseTokenString(socialAccessToken);

        SocialPlatform socialPlatform = request.getSocialPlatform();
        String socialId = login(socialPlatform, socialAccessToken);

        boolean isRegistered = isUserBySocialAndSocialId(socialPlatform, socialId);
        if (!isRegistered) {
            User user = User.builder()
                    .socialPlatform(socialPlatform)
                    .socialId(socialId).build();

            userRepository.save(user);
        }

        User loginUser = getUserBySocialAndSocialId(socialPlatform, socialId);
        // 카카오 로그인은 정보 더 많이 받아올 수 있으므로 추가 설정
        if (socialPlatform == SocialPlatform.KAKAO) {
            kakaoLoginService.setKakaoInfo(loginUser, socialAccessToken);
        }

        TokenDto tokenDto = jwtProvider.issueToken(new UserAuthentication(loginUser.getId(), null, null));

        return UserLoginResponseDto.of(loginUser, tokenDto);
    }

    @Transactional
    public TokenDto reissueToken(String refreshToken) {

        refreshToken = parseTokenString(refreshToken);

        Long userId = jwtProvider.validateRefreshToken(refreshToken);
        validateUserId(userId);  // userId가 DB에 저장된 유효한 값인지 검사

        jwtProvider.deleteRefreshToken(userId);
        return jwtProvider.issueToken(new UserAuthentication(userId, null, null));
    }

    @Transactional
    public void logout(Long userId) {
        jwtProvider.deleteRefreshToken(userId);
    }

    private void validateUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(NOT_FOUND_USER_ERROR);
        }
    }

    private User getUserBySocialAndSocialId(SocialPlatform socialPlatform, String socialId) {
        return userRepository.findBySocialPlatformAndSocialId(socialPlatform, socialId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER_ERROR));
    }

    private boolean isUserBySocialAndSocialId(SocialPlatform socialPlatform, String socialId) {
        return userRepository.existsBySocialPlatformAndSocialId(socialPlatform, socialId);
    }

    private String login(SocialPlatform socialPlatform, String socialAccessToken) {
        switch (socialPlatform.toString()) {
            case "KAKAO":
                return kakaoLoginService.getKakaoId(socialAccessToken);
            default:
                throw new CustomException(INVALID_SOCIAL_ACCESS_TOKEN);
        }
    }

    private static String parseTokenString(String tokenString) {
        String[] strings = tokenString.split(" ");
        if (strings.length != 2) {
            throw new CustomException(INVALID_TOKEN_HEADER_ERROR);
        }
        return strings[1];
    }
}