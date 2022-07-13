package com.example.ecommerce.Controllers;
import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.DAO.OrderRepository;
import com.example.ecommerce.Models.Customer;
import com.example.ecommerce.Models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderRepository iorder ;
    @Autowired
    private CustomerRepository icus ;
    @GetMapping("/all")
    public List<Order> getall(){
        return iorder.findAll();
    }
    @GetMapping("/getone/{Id}")
    public Order getone(@PathVariable Long Id){
        return iorder.findById(Id).orElse(null);
    }



    @PostMapping("/save/{idcustomer}")
    public Order saveorder(@RequestBody Order o,@PathVariable Long idcustomer) {
        Customer c=icus.findById(idcustomer).orElse(null);
        o.setCustomer(c);
        return iorder.save(o);
    }




    @PutMapping("/update/{Id}")
    public Order update(@RequestBody Order o , @PathVariable Long Id ){
        o.setId(Id);
        return iorder.saveAndFlush(o);
    }




    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteorder(@PathVariable Long Id) {
        HashMap message=new HashMap();
        try{
            iorder.deleteById(Id);
            message.put("etat","order deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","order not deleted");
            return message;
        }
    }
}
