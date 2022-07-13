package com.example.ecommerce.Controllers;

import com.example.ecommerce.DAO.ProviderRepository;
import com.example.ecommerce.Models.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/provider")
public class ProviderController {
    @Autowired
    private ProviderRepository iprovider;
    @GetMapping("/all")
    public List<Provider> getall(){
        return iprovider.findAll();
    }
    @GetMapping("/getone/{Id}")
    public Provider getone(@PathVariable Long Id){
        return iprovider.findById(Id).orElse(null);
    }
    @PostMapping("/save")
    public Provider savecustomer(@RequestBody Provider p) {
        return iprovider.save(p);
    }
    @PutMapping("/update/{Id}")
    public Provider update(@RequestBody Provider p, @PathVariable Long Id){
        p.setId(Id);
        return iprovider.saveAndFlush(p);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteProvider(@PathVariable Long Id) {
        HashMap message=new HashMap();
        try{
            iprovider.deleteById(Id);
            message.put("etat","provider deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","provider not deleted");
            return message;
        }
    }
}
