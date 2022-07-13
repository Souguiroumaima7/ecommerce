package com.example.ecommerce.Controllers;

import com.example.ecommerce.DAO.CategoryRepository;
import com.example.ecommerce.DAO.OrderRepository;
import com.example.ecommerce.DAO.ProductRepository;
import com.example.ecommerce.DAO.SubcategoryRepository;
import com.example.ecommerce.Models.Category;
import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Models.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/subcategory")
public class SubcategoryController {
    @Autowired
    private SubcategoryRepository isub;



    @Autowired
    private CategoryRepository ica ;

    @GetMapping("/all")
    public List<Subcategory> getall(){
        return isub.findAll();
    }


    @GetMapping("/getone/{Id}")
    public Subcategory getone(@PathVariable Long Id){
        return isub.findById(Id).orElse(null);
    }


    @PostMapping("/save/{idcat}")
    public Subcategory savesubcategory(@RequestBody Subcategory s,  @PathVariable Long idcat) {

        Category c = ica.findById(idcat).orElse(null) ;
        s.setCategory(c);
        return isub.save(s);
    }


    @PutMapping("/update/{Id}")
    public Subcategory update(@RequestBody Subcategory s, @PathVariable Long Id){
        s.setId(Id);
        return isub.saveAndFlush(s);
    }




    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteSubcategory(@PathVariable Long Id) {
        HashMap message=new HashMap();
        try{
            isub.deleteById(Id);
            message.put("etat","subcategory deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","subcategory not deleted");
            return message;
        }
    }
}
