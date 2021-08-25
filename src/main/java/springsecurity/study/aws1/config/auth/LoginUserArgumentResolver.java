package springsecurity.study.aws1.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import springsecurity.study.aws1.config.auth.dto.SessionUser;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    /**
     * supportsParameter()
     * 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단함.
     * 여기서는 파라미터에 @LoginUser annotation이 붙어 있고,
     * 파라미터클래스 타입이 SessionUser.class인 경우 true를 반환함.
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    /**
     * resolveArgument()
     * 파라미터에 전달할 객체를 생성함. 여기에서는 세션에서 객체를 가져옴
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
