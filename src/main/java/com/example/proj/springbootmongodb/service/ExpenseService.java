package com.example.proj.springbootmongodb.service;

import com.example.proj.springbootmongodb.model.Expense;
import com.example.proj.springbootmongodb.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }

    //function to add expense
    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }

    //function to update expense
    public void updateExpense(Expense expense){
        Expense savedExpense=expenseRepository.findById(expense.getId())
                .orElseThrow(()->new RuntimeException(
                        String.format("Cannot find expense by ID %s",expense.getId())
                ));

        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(savedExpense);

    }

    //function to get all expense
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    //function to get particular expense
    public Expense getExpenseByName(String name){
        return expenseRepository.findByName(name).orElseThrow(()->new RuntimeException(
                String.format("Cannot find expenses by name %s",name)
        ));
    }

    //delete expense
    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }
}
