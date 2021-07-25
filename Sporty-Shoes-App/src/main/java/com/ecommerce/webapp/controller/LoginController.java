package com.ecommerce.webapp.controller;

import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.webapp.entity.Login;
import com.ecommerce.webapp.entity.Shoes;
import com.ecommerce.webapp.exception.LoginIDNotFound;
import com.ecommerce.webapp.exception.ProductNotFound;
import com.ecommerce.webapp.repository.LoginRepository;





@Controller
public class LoginController {
	
	@Autowired
    private LoginService userService;
@Autowired LoginRepository loginrepo;
                                   
    @GetMapping("/login")
           
    public ModelAndView login() {
    	ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user ) {
    	
    	Login authUser = userService.login(user.getUsername(), user.getPassword());
    	

    	System.out.print(authUser);
    	if(Objects.nonNull(authUser)) 
    	{	
  
    		return "redirect:/";
    	
    		
    	} else {
    		return "redirect:/login";
    		
    		
    	
    	}

}
    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {
    	
	  
        return "redirect:/login";
    }
    @PutMapping("/changepassword/{id}")
	public Login updatePassword(@RequestBody Login login, @PathVariable(value = "id") long loginId) {
		
		Login fetcheduser = this. loginrepo.findById(loginId)
				.orElseThrow(() -> new LoginIDNotFound("Product Not found wit id " + loginId));
		// set new values to change password
		fetcheduser.setUsername(login.getUsername());
		fetcheduser.setPassword(login.getPassword());
		return this.loginrepo.save(fetcheduser);
    }	
}