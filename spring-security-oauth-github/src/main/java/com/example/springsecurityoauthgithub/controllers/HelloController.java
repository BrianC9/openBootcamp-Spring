package com.example.springsecurityoauthgithub.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // -> Controlador MVC
public class HelloController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping("/page1")
    public String pagina1(Model model,
                          @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                          @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("atributoPropio","Conexión correcta ");
        model.addAttribute("clienteName",client.getClientRegistration().getClientName());
        model.addAttribute("userName",user.getName());
        model.addAttribute("userAttributes",user.getAttributes());

        return "page1";
    }
@GetMapping("/page2")
    public String pagina2(
            Model model,
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OAuth2User user
            ){
        model.addAttribute("clienteName",client.getClientRegistration().getClientName());
        model.addAttribute("userName",user.getName());
        model.addAttribute("userAttributes",user.getAttributes());
        model.addAttribute("srcImg",user.getAttribute("avatar_url"));
        return "page2";
    }
}
