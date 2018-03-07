package pl.softwarelady.sbj.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.softwarelady.sbj.jpa.UserModel;
import pl.softwarelady.sbj.services.UserService;

@Controller
public class UserController {

	private UserService userService;
	
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public String redirectToList(){
        return "redirect:/user/list";
    }
    
    @RequestMapping({"/user/list", "/user"})
    public String allUsers(Model model){
        model.addAttribute("users", userService.allUsers());
        return "user/list";
    }
    
    @RequestMapping("/user/show/{id}")
    public String getUser(@PathVariable String id, Model model){
        model.addAttribute("user", userService.getById(Long.valueOf(id)));
        return "user/show";
    }
    
    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        UserModel user = userService.getById(Long.valueOf(id));

        model.addAttribute("user", user);
        return "user/userForm";
    }
    
    @RequestMapping("/user/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserModel());
        return "user/userForm";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String merge(@Valid UserModel user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/userForm";
        }

        UserModel savedUser = userService.merge(user);

        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable String id){
        userService.delete(Long.valueOf(id));
        return "redirect:/user/list";
    }
}
