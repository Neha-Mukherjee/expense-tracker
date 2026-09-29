package net.javaguides.expense.controller;


import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.ExpenseDto;
import net.javaguides.expense.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;

    //create expense rest api
    @PostMapping
    public ResponseEntity<ExpenseDto>createExpense(@RequestBody ExpenseDto expenseDto){
        ExpenseDto savedExpense = expenseService.createExpense(expenseDto);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    //get expense by id rest api
     @GetMapping("{id}")
    public ResponseEntity<ExpenseDto>getExpense(@PathVariable ("id") Long expenseId){
        ExpenseDto expense=expenseService.getExpenseById(expenseId);
        return ResponseEntity.ok(expense);
    }

    //get all expenses
    @GetMapping
    public ResponseEntity <List<ExpenseDto>> getAllExpenses(){
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    // update expense rest api
    @PutMapping("{id}")
    public ResponseEntity<ExpenseDto>updateExpense(@PathVariable("id") Long expenseId,@RequestBody ExpenseDto expenseDto){
        ExpenseDto updatedExpense= expenseService.updateExpense(expenseId , expenseDto);
        return ResponseEntity.ok(updatedExpense);
    }
    //delete expense restapi
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteExpense(@PathVariable("id") Long expenseId){
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
