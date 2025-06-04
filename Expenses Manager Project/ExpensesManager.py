import Expense
from Expense import *


class ExpensesManager(object):
    """A class for managing expenses in a dictionary.
    """

    # We do not have an __init__ function and will call the default constructor

    def get_expense(self, expenses, expense_type):
        """Returns the Expense object for the given expense type in the given expenses dictionary.

        Prints a friendly message and returns None if the expense type doesn't exist.

        (Note: Printing a friendly message means that the program should not raise an error or otherwise terminate.
        Simply tell the user that the requested expense type does not exist and continue the program.

        Also note that None is a specific keyword in Python of NoneType. You should not return a string “None” from
        this method.)
        """

        # TODO insert your code
        try: # Catching input errors
            # Checking if expense type exists in expense dictionary
            if expenses.get(expense_type) is not None:
                # Returning corresponding Expense object if expense_type exists.
                return expenses[expense_type]
        except ValueError:
            print("Woops! Looks like you don't have an expense like that! Let's keep going!")

    def add_expense(self, expenses, expense_type, value):
        """If the expense_type already exists in the given expenses dictionary, add the value to the associated
        Expense object amount.

        Otherwise, create a new entry in the expenses dictionary with the expense_type as the
        key and the value as an Expense object with the given expense_type and value parameters.

        Prints the updated Expense object.

        This method doesn’t return anything.
        """

        # TODO insert your code
        # Checking if expense_type exists in expenses dictionary.
        if expenses.get(expense_type) is not None:
            # Adding value to object amount if expense_type exists
            expenses[expense_type].add_amount(value)
        else:
            # Creating new entry in the expenses dictionary with expense_type as key and Expense object with the type and amount parameters as value.
            expenses[expense_type] = Expense(expense_type, value)
        # Printing updated Expense object
        print(expenses)

    def deduct_expense(self, expenses, expense_type, value):
        """From the given expenses dictionary, get the Expense object associated with the given expense_type and
        deduct the given value from the amount.

        Raises a RuntimeError if the given value is greater than the existing amount of the Expense object. Note: You
        are not supposed to use try/except to catch the RuntimeError you raised. We expect the method to raise a
        RuntimeError if the value is greater than the existing total of the expense type.

        Prints a friendly message if the expense type doesn't exist from the given expenses dictionary. (Note:
        Printing a friendly message means that the program should not raise an error or otherwise terminate. Simply
        tell the user that the requested expense type does not exist and continue the program.)

        Print the updated Expense object if RuntimeError is not raised.

        This method doesn’t return anything.
        """

        # TODO insert your code
        if expenses.get(expense_type) is None:
            # Trying operation and catching errors if the expense type does not exist.
            try:
                expenses[expense_type].deduct_amount(value)
                print(expenses[expense_type])
            # Printing friendly message if invalid input is given.
            except RuntimeError:
                print("Woops! Looks like you don't have an expense like that! Let's keep going!")
            except AttributeError:
                print("Woops! Looks like you don't have an expense like that! Let's keep going!")
            except KeyError:
                print("Woops! Looks like you don't have an expense like that! Let's keep going!")
        else:
            # Raising runtime error if value is greater than the existing amount of the Expense object.
            if value > expenses[expense_type].amount:
                raise RuntimeError
            # Deducting the value given from Expense object amount and printing updated Expense object if no error.
            else:
                expenses[expense_type].deduct_amount(value)
                print(expenses[expense_type])

    def update_expense(self, expenses, expense_type, value):
        """From the given expenses dictionary, update the Expense object associated with the given expense_type and
        use the given value.

        Prints a friendly message if the expense type doesn't exist.  Note: Printing a friendly message means that
        the program should not raise an error or otherwise terminate. Simply tell the user that the requested expense
        type does not exist and continue the program.

        Prints the updated Expense object if it exists.

        This method doesn’t return anything.
        """

        # TODO insert your code
        # Using try to catch any invalid input and prevent program from stopping.
        try:
            if expenses.get(expense_type) is None:
                # Trying operation and catching errors if the expense type does not exist.
                try:
                    expenses[expense_type].amount = value
                    # Printing updated Expense object if expense type exists.
                    print(expenses[expense_type])
                except AttributeError:
                    # Printing friendly message if invalid input is given.
                    print("Woops! Looks like you don't have an expense like that! Let's keep going!")
                except KeyError:
                    print("Woops! Looks like you don't have an expense like that! Let's keep going!")
                except TypeError:
                    print("Woops! Looks like you don't have an expense like that! Let's keep going!")
            else:
                # Updating amount for expense type is expense type exists
                expenses[expense_type].amount = value
                # Printing updated Expense object if expense type exists.
                print(expenses[expense_type])
        except:
            print("Woops! Looks like you don't have an expense like that! Let's keep going!")

    def sort_expenses(self, expenses, sorting):
        """Converts the key:value pairs in the given expenses dictionary to a list of tuples containing the expense
        type and amount of the Expense object (Expense.expense_type, Expense.amount) and sorts based on the given
        sorting argument.

        If the sorting argument is the string ‘expense_type’, sorts the list of tuples based on the expense type
        (e.g. ‘rent’) in ascending alphabetical order of the expense_type, e.g. sorted results: ("coffee", 5.0),
        ("food", 5000.0), ("rent", 1000.0)

        Otherwise, if the sorting argument is ‘amount’, sorts the list of tuples based on the total expense amount
        (e.g. 825.0) in descending order of the expense amount, e.g. sorted results: ("food", 5000.0), ("rent",
        1000.0), ("coffee", 5.0)

        Returns the list of sorted tuples. (Note: If the given sorting argument is not an acceptable value
        (e.g. ‘expense_type’ or 'amount'), this method does nothing except print a friendly message and return None.)
        """

        # TODO insert your code
        # Checking if sorted argument is an acceptable value.
        if sorting == "expense_type" or sorting == "amount":
            # Initiating empty list to store and sort tuples.
            sorted_list = []

            # Iterating over expenses dictionary and appending tuples to create list of tuples.
            for key, val in expenses.items():
                sorted_list.append((key, val.amount))

            # Sorting list of tuples based on specified sorting criteria: amount or expense_type.
            if sorting == "expense_type":
                # Sorting in ascending order based on expense_type.
                sorted_list.sort(key = lambda expense: expense[0])
            else:
                # Sorting in descending order based on total amount.
                sorted_list.sort(key=lambda amount: amount[1], reverse=True)

            return sorted_list
        else:
            print("Hey! Looks like we don't have anything like that! It's ok. Try something else!")
            return None
    def export_expenses(self, expenses, expense_types, file):
        """Exports the Expense objects associated with the given expense_types from the given expenses dictionary to
        the given file.

        Do not append to the file. If the function is called again and the given file already exists, make sure it
        overwrites what was previously in the file instead of appending to it.

        Iterates over the given expenses dictionary, filters based on the given expense types (a list of strings),
        and exports to a file.  Skips any expense type in the given list of expense types that doesn't exist.

        If the expenses argument is the dictionary {"food": Expense("food", 5000.00), "rent": Expense("rent",
        1000.00), "coffee": Expense("coffee", 5.00), "clothes": Expense("clothes", 58.92)} and the expense_types
        argument is the list of strings [‘coffee', 'clothes', 'rent’], exports a file containing: coffee: 5.00
        clothes: 58.92 rent: 1000.00

        If the expenses argument is the dictionary {"food": Expense("food", 5000.00), "rent": Expense("rent",
        1000.00), "coffee": Expense("coffee", 5.00), "clothes": Expense("clothes", 58.92)} and the expense_types
        argument is the list of strings [‘coffee', 'clothes', 'sports’], exports a file containing: coffee: 5.00
        clothes: 58.92

        Note, the specified expense type 'sports' does not exist in the expenses dictionary, so it is ignored.

        If an item is duplicated in the given expense types, don’t worry about it, just export the data as is. You
        should not deduplicate the expense types.

        Note: Each purchase should be written on its own line in the output file for example if it were the later example
        that we gave you above, the output file would look like the below:
        coffee: 12.40
        clothes: 45.00

        This method doesn’t return anything.
        """

        # TODO insert your code
        # Creating a new file in write mode.
        f = open(file, "w")
        lines = [] # Creating empty list to store information which will be exported.

        # Iterating over expense_types list.
        for i in expense_types:
            # Iterating over expenses dictionary.
            for key, val in expenses.items():
                # Extracting the corresponding Expense objects from expenses dictionary for each expense_type.
                if key == i:
                    # Appending the expense objects to the empty list.
                    lines.append(val.__str__()+'\n')
        # Writing the data from newly filled list into the file.
        f.writelines(lines)