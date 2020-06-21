package com.gdou.gas.controller.field;

import com.gdou.gas.service.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("fieldList", fieldService.findAll());
        return "admin/field/add";
    }
}
