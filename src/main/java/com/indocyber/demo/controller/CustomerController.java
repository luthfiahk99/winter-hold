package com.indocyber.demo.controller;

import com.indocyber.demo.dto.category.CategoryGridDTO;
import com.indocyber.demo.dto.customer.CustomerGridDTO;
import com.indocyber.demo.dto.customer.UpsertCustomerDTO;
import com.indocyber.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String membershipNumber,
                        @RequestParam(defaultValue = "") String fullName,
                        Model model){

        List<CustomerGridDTO> grid = customerService.getCustomerGrid(page, membershipNumber, fullName);

        long totalPages = customerService.getTotalPages(membershipNumber, fullName);

        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("membershipNumber", membershipNumber);
        model.addAttribute("fullName", fullName);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Customer Index");

        return "customer/customer-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) String membershipNumber, Model model) {

        if (membershipNumber != null) {

            UpsertCustomerDTO dto = customerService.getUpdateCustomer(membershipNumber);

            model.addAttribute("customer", dto);
            model.addAttribute("type", "Update");
            model.addAttribute("breadCrumbs", "Customer Index / Update Customer");
        } else {

            UpsertCustomerDTO dto = new UpsertCustomerDTO();

            model.addAttribute("customer", dto);
            model.addAttribute("type", "Insert");
            model.addAttribute("breadCrumbs", "Customer Index / Insert Customer");
        }
        return "customer/customer-form";
    }

    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("instructor") UpsertCustomerDTO dto,
                         BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {

            if(dto.getMembershipNumber() != null) {
                model.addAttribute("type", "Update");
                model.addAttribute("breadCrumbs", "Customer Index / Update Customer");
            } else {
                model.addAttribute("type", "Insert");
                model.addAttribute("breadCrumbs", "Customer Index / Insert Customer");
            }

            return "customer/customer-form";
        } else {

            customerService.insertCustomer(dto);

            return "redirect:/customer/index";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String membershipNumber, Model model){

        Long dependentLoan = customerService.dependentLoan(membershipNumber);

        if(dependentLoan > 0){
            model.addAttribute("dependencies", dependentLoan);
            model.addAttribute("breadCrumbs", "Customer Index / Fail To Delete Customer");

            return "customer/customer-delete";
        }

        customerService.deleteById(membershipNumber);
        return "redirect:/customer/index";
    }
}
