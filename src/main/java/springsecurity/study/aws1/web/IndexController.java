package springsecurity.study.aws1.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springsecurity.study.aws1.config.auth.LoginUser;
import springsecurity.study.aws1.config.auth.dto.SessionUser;
import springsecurity.study.aws1.service.posts.PostsService;
import springsecurity.study.aws1.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    /**
     * @LoginUser SessionUser user
     * 기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨.
     * 이걸 사용하게 되면 어느 컨트롤러든지 세션 정보를 가져올 수 있게 됨.
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
    /*  Model
        서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
        여기에서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달함.
    */
        model.addAttribute("posts",postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
