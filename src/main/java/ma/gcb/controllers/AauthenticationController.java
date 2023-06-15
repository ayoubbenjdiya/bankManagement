
package ma.gcb.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AauthenticationController
{
    @RequestMapping({ "/" })
    public String home() {
        return "redirect:/index";
    }
    
    @RequestMapping({ "/login" })
    public String login() {
        if (this.isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }
    
    private boolean isAuthenticated() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()) && authentication.isAuthenticated();
    }
}
