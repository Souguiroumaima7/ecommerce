package com.example.ecommerce.Controllers;

import com.example.ecommerce.DAO.ProductRepository;
import com.example.ecommerce.DAO.ProviderRepository;
import com.example.ecommerce.DAO.SubcategoryRepository;
import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Models.Provider;
import com.example.ecommerce.Models.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductRepository iproduct;
    @Autowired
    private SubcategoryRepository isub ;
    @Autowired
    private ProviderRepository iprov ;
    @GetMapping("/all")
    public List<Product> getall(){
        return iproduct.findAll();
    }
    @GetMapping("/getone/{Id}")
    public Product getone(@PathVariable Long Id){
        return iproduct.findById(Id).orElse(null);
    }

    @PostMapping("/save/{idsub}/{idprod}")
    public Product saveproduct(@RequestBody Product p, @PathVariable  Long idsub, @PathVariable  Long idprod) {

 Subcategory s = isub.findById(idsub).orElse(null);
 p.setSubcategory(s);

        Provider Pro = iprov.findById(idprod).orElse(null);
        p.setProvider(Pro);
        return iproduct.save(p);
    }
    @PutMapping("/update/{Id}")
    public Product update(@RequestBody Product p, @PathVariable Long Id){
        p.setId(Id);
        return iproduct.saveAndFlush(p);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteProduct(@PathVariable Long Id) {
        HashMap message=new HashMap();
        try{
            iproduct.deleteById(Id);
            message.put("etat","product deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","product not deleted");
            return message;
        }
    }
}
