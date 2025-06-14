from Expense import *


class ExpensesLoader(object):
    """A class for loading expenses from a file.
    """

    # We do not have an __init__ function and will call the default constructor

    def import_expenses(self, expenses, file):
        """Reads data from the given file and stores the expenses in the given expenses dictionary, where the expense
        type is the key and the value is an Expense object with the parameters expense type and total amount for that
        expense type.

        The same expense type may appear multiple times in the given file, so add all the amounts for the same
        expense types.

        Ignore expenses with missing amounts. If a line contains both an expense type and an expense amount,
        they will be separated by a colon (:).

        You can assume that if they exist, expense types are one-word strings and the amounts are numerical and can
        be casted to a float data type.

        Strip any whitespace before or after the expense types and amounts. Blank lines should be ignored.

        Expenses are case-sensitive. "coffee" is different from "Coffee".

        This method will be called twice in the main function in expenses.py with the same dictionary, but different
        files.

        This method doesn’t return anything.  Rather, it updates the given expenses dictionary based
        on the expenses in the given file.

        For example, after loading the expenses from the file, the expenses dictionary should look like
        this: {'food': Expense('food', 5.00), 'coffee': Expense('coffee', 12.40),
               'rent': Expense('rent', 825.00), 'clothes': Expense('clothes', 45.00),
               'entertainment': Expense('entertainment', 135.62), 'music': Expense('music', 324.00),
               'family': Expense('family', 32.45)}

        Note: You are not expected to handle negative numbers in your code
        """

        # TODO insert your code
        # Creating empty lists to store values for the expense_type and amount

        # Opening file and extracting contents as a list of lines.
        file_list = open(file).readlines()

        values = {}

        for i in file_list:
            i = i.split(":") # Splitting each line in expense file into a list containing the type and the total.

            #Removing blank lines
            if i[-1] == '\n': # Should skip any expenses with blank amounts
                continue

            i[0] = i[0].strip().lower() # Removing leading and trailing whitespaces and returning string in all lowercase.
            i[-1] = i[-1].strip() # Removing leading and trailing whitespaces and returning value amount.
            i[-1] = float(i[-1]) # Casting value amount to a float.

            #print(i[0], i[-1], type(i[-1])) # Check to see that all information has been correctly extracted and formatted.

            if expenses.get(i[0]) is None: # This checks that the key, value pair is not already in the dictionary.
                expenses[i[0]] = Expense(i[0], i[-1])
            else: # If already in the dictionary, it will just add the amount.
                expenses[i[0]].add_amount(i[-1])

        return expenses
