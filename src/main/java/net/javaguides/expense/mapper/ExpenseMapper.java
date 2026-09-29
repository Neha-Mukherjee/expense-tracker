package net.javaguides.expense.mapper;

import net.javaguides.expense.dto.CategoryDto;
import net.javaguides.expense.dto.ExpenseDto;
import net.javaguides.expense.entity.Category;
import net.javaguides.expense.entity.Expense;

public class ExpenseMapper {
    public static ExpenseDto maptoExpenseDto(Expense expense){
        return new ExpenseDto(expense.getId(), expense.getAmount(),expense.getExpenseDate(),
                new CategoryDto(expense.getCategory().getId(),expense.getCategory().getName()));
    }
    public static Expense maptoExpense(ExpenseDto expenseDto){
        Category category=new Category();
        category.setId(expenseDto.categoryDto().id());
        return new Expense(expenseDto.id(), expenseDto.amount(),expenseDto.expenseDate(),category);
    }
}

