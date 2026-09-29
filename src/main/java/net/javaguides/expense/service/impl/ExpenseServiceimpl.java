package net.javaguides.expense.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.ExpenseDto;
import net.javaguides.expense.entity.Category;
import net.javaguides.expense.entity.Expense;
import net.javaguides.expense.exceptions.ResourceNotFoundException;
import net.javaguides.expense.mapper.ExpenseMapper;
import net.javaguides.expense.repository.CategoryRepository;
import net.javaguides.expense.repository.ExpenseRepository;
import net.javaguides.expense.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceimpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        //convertexpensedto to expenseentity
        Expense expense= ExpenseMapper.maptoExpense(expenseDto);
        //save expense entity to db
        Expense savedExpense=expenseRepository.save(expense);
        //convert saved expense entity into expensedto
        return ExpenseMapper.maptoExpenseDto(savedExpense);

    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {
    Expense expense=    expenseRepository.findById(expenseId).orElseThrow(()->new ResourceNotFoundException("Expense not found with id"+ expenseId));

        return ExpenseMapper.maptoExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses=expenseRepository.findAll();
       return expenses.stream().map(expense -> ExpenseMapper.maptoExpenseDto(expense))
                .collect(Collectors.toList());

    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense=    expenseRepository.findById(expenseId).orElseThrow(()->new ResourceNotFoundException("Expense not found with id"+ expenseId));
        //update expense amount
        expense.setAmount(expenseDto.amount());
        //update expense date
        expense.setExpenseDate(expenseDto.expenseDate());
        //updatecategory
        if(expenseDto.categoryDto()!=null){
            //get the category entity by id
           Category category= categoryRepository.findById(expenseDto.categoryDto().id()).orElseThrow(()->new ResourceNotFoundException("category notfound with id "+expenseDto.categoryDto().id()));
           expense.setCategory(category);
        }

        //update expense entity
        Expense updatedExpense = expenseRepository.save(expense);
        //convert expense entity to expensedto
        return ExpenseMapper.maptoExpenseDto(updatedExpense);



    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense=expenseRepository.findById(expenseId).orElseThrow(()->new ResourceNotFoundException("Expense not found with id"+ expenseId));
        expenseRepository.delete(expense);
    }

}
