package com.employee.controller;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    @PostMapping
    public String saveEmployee(@Valid @ModelAttribute Employee employee,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "employees/create";
        }

        // Check if email already exists
        if (employeeService.emailExists(employee.getEmail())) {
            bindingResult.rejectValue("email", "error.employee", "Email already exists");
            return "employees/create";
        }

        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully!");
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Employee not found!");
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee.get());
        return "employees/edit";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id,
                                @Valid @ModelAttribute Employee employeeDetails,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "employees/edit";
        }

        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Employee not found!");
            return "redirect:/employees";
        }

        // Check if email is unique (excluding current employee)
        if (!existingEmployee.get().getEmail().equals(employeeDetails.getEmail()) &&
            employeeService.emailExists(employeeDetails.getEmail())) {
            bindingResult.rejectValue("email", "error.employee", "Email already exists");
            return "employees/edit";
        }

        Employee employee = existingEmployee.get();
        employee.setFullName(employeeDetails.getFullName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());

        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully!");
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Employee not found!");
            return "redirect:/employees";
        }
        employeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
        return "redirect:/employees";
    }
}
