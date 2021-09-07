package com.example.proj.springbootmongodb.controller;

import com.example.proj.springbootmongodb.model.Expense;
import com.example.proj.springbootmongodb.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    //adds a new expense
    @PostMapping
    public ResponseEntity addExpense(@RequestBody Expense expense){
    expenseService.addExpense(expense);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //updates a particular response
    @PutMapping
    public ResponseEntity updateExpense(@RequestBody Expense expense) {
        expenseService.updateExpense(expense);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //returns all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    //get expenses by name
    @GetMapping("/{name}")
    public ResponseEntity<Expense> getExpenseByName(@PathVariable String name){
        return ResponseEntity.ok(expenseService.getExpenseByName(name));
    }

    //deletes all expenses
    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable String id){
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
