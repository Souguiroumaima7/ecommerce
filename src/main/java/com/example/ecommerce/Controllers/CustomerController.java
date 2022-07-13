package com.example.ecommerce.Controllers;

import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/Customer")

public class CustomerController {
    @Autowired
    private CustomerRepository icust;
    @GetMapping("/all")
    public List<Customer> getall(){
        return icust.findAll();
    }
    @GetMapping("/getone/{Id}")
    public Customer getone(@PathVariable Long Id){
        return icust.findById(Id).orElse(null);
    }
    @PostMapping("/save")
    public Customer savecustomer(@RequestBody Customer c) {
        return icust.save(c);
    }
    @PutMapping("/update/{Id}")
    public Customer update(@RequestBody Customer c, @PathVariable Long Id){
        c.setId(Id);
        return icust.saveAndFlush(c);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteCustomer(@PathVariable Long Id) {
        HashMap message=new HashMap();
        try{
            icust.deleteById(Id);
            message.put("etat","customer deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","customer not deleted");
            return message;
        }
    }
}
