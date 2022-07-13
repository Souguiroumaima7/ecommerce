package com.example.ecommerce.Controllers;

import com.example.ecommerce.DAO.CategoryRepository;
import com.example.ecommerce.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository icat;
    @GetMapping("/all")
    public List<Category> getall(){
        return icat.findAll();
    }
    @GetMapping("/getone/{Id}")
    public Category getone(@PathVariable Long Id){
        return icat.findById(Id).orElse(null);
    }
    @PostMapping("/save")
    public Category savecategory(@RequestBody Category c) {
        return icat.save(c);
    }
    @PutMapping("/update/{Id}")
    public Category update(@RequestBody Category c, @PathVariable Long Id){
        c.setId(Id);
        return icat.saveAndFlush(c);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteCategory(@PathVariable Long Id) {
        HashMap message=new HashMap();
        try{
            icat.deleteById(Id);
            message.put("etat","category deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","category not deleted");
            return message;
        }
    }
}
