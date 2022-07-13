package com.example.ecommerce.Controllers;

import com.example.ecommerce.DAO.UserRepository;
import com.example.ecommerce.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/user")

public class UserContoller {
    @Autowired
    private UserRepository iuser;
    @GetMapping("/all")
    public List<User> getall(){
        return iuser.findAll();
    }
    @GetMapping("/getone/{Id}")
    public User getone(@PathVariable Long Id){
        return iuser.findById(Id).orElse(null);
    }
    @PostMapping("/save")
    public User saveuser(@RequestBody User u){
        return iuser.save(u);
    }
    @PutMapping("/update/{Id}")
    public User update(@RequestBody User u, @PathVariable Long Id){
        u.setId(Id);
        return iuser.saveAndFlush(u);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteUser(@PathVariable Long Id) {
        HashMap message=new HashMap();
        try{
            iuser.deleteById(Id);
            message.put("etat","user deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","user not deleted");
            return message;
        }
    }

}
