# Faith Akinlade
# 61534906
# I worked alone without help.

# import the random module
# use "winnings = random.randint(2, 10)" to generate a random int from 2 - 10 and store in a variable "winnings"
import random

# unit price of a lottery ticket
constant_lottery_unit_price = 2

# unit price of an apple
constant_apple_unit_price = .99

# unit price of a can of beans
constant_canned_beans_unit_price = 1.58

# unit price of a soda
constant_soda_unit_price = 1.23

# the user has initial $5 for shopping
money = 5

# the user has spent $0 initially
money_spent = 0

# the amounts of lottery tickets, apples, cans of beans, and sodas the user has purchased
lottery_amount, apple_amount, canned_beans_amount, soda_amount = 0, 0, 0, 0

# Displaying welcome message and presenting the supermarket menu to user.
print("Welcome to the Supermarket! Here's what we have in stock:")
print("- Lottery Tickets: $", round(constant_lottery_unit_price, 2), " /ea")
print("- Apples: $", round(constant_apple_unit_price, 2), " /ea")
print("- Cans of Beans: $", round(constant_canned_beans_unit_price, 2), " /ea")
print("- Soda: $", round(constant_soda_unit_price, 2), " /ea\n")

# telling user amount currenlty available to spend and asking if the user wants to buy a lottery ticket
print("You currently have ${} in your wallet.".format(money))
try: #catching input errors
    LT = str(input("First, would you like to purchase a $2 lottery ticket for a chance to win $2-$10? (y/n)"))
    LT

    if LT == "Y" or LT == "y":
        winnings = random.randint(2, 10)
        win_chance = random.randint(0, 2)
        total_winnings = 0
        if win_chance == 1:
            money += winnings
            money_spent += constant_lottery_unit_price
            money -= money_spent
            lottery_amount += 1
            lottery_win = money-5
            print("Congratulations! You just won ${}!\n".format(lottery_win))
        else:
            lottery_win = 0
            print("Sorry, you didn't win this one. Maybe next time!\n")
    else:
        lottery_win = 0
        print("No lottery tickets were purchased.\n")
except ValueError:
    print("Invalid input.\n")

# asking if the user wants to buy apples
print("You now have ${} available in your wallet.".format(round(money)))
try:
    A_ = str(input("Would you like to buy apple(s)? (y/n)"))
    A_

    if A_ == "Y" or A_ == "y":
        try:
            A = int(input("How many apples would you like to buy? (Please remember: Input must be an integer.)"))
            print(A)
            cost_of_apples = A * constant_apple_unit_price
            print("The user wants to buy {} apple(s). This will cost ${}".format(A, round(cost_of_apples, 2)))
            
            #checking if user has enough money.
            if cost_of_apples > money:
                print("Not enough money\n")
            else:
                apple_amount += A
                money_spent += cost_of_apples
                money -= cost_of_apples
                print("The user has enough money. {} apple(s) purchased\n".format(apple_amount))
        except ValueError:
            print("Invalid input.\n")
    else:
        print("No apples were purchased.\n")
except ValueError:
    print("Invalid input.\n")

# asking if the user wants to buy cans of beans
print("You now have ${} available in your wallet.".format(round(money)))
try:
    CB = str(input("Would you like to buy cans of beans? (y/n)"))
    CB

    if CB == "Y" or CB == "y":
        try:
            CB = int(input("How many cans of beans would you like to buy? (Please remember: Input must be an integer.)"))
            print(CB)
            cost_of_beans = CB * constant_canned_beans_unit_price
            print("The user wants to buy {} cans(s) of beans. This will cost ${}".format(CB, round(cost_of_beans, 2)))
            
            #checking if user has enough money.
            if cost_of_beans > money:
                print("Not enough money\n")
            else:
                canned_beans_amount += CB
                money_spent += cost_of_beans
                money -= cost_of_beans
                print("The user has enough money. {} can(s) of beans purchased\n".format(canned_beans_amount))
        except ValueError:
            print("Invalid input.\n")
    else:
        print("No cans of beans were purchased.\n")
except ValueError:
    print("Invalid input.\n")

# asking if the user wants to buy sodas
print("You now have ${} available in your wallet.".format(round(money, 2)))
try:
    S_ = str(input("Would you like to buy some soda(s)? (y/n)"))
    S_

    if S_ == "Y" or S_ == "y":
        try:
            S_ = int(input("How many soda(s) would you like to buy? (Please remember: Input must be an integer.)"))
            print(S_)
            cost_of_soda = S_ * constant_soda_unit_price
            print("The user wants to buy {} soda(s). This will cost ${}".format(S_, cost_of_soda))
            
            #checking if user has enough money.
            if cost_of_soda > money:
                print("Not enough money\n")
            else:
                soda_amount += S_
                money_spent += cost_of_soda
                money -= cost_of_soda
                print("The user has enough money. {} soda(s) purchased\n".format(soda_amount))
        except ValueError:
            print("Invalid input.\n")
    else:
        print("No sodas were purchased.\n")
except ValueError:
    print("Invalid input.\n")

# displaying final purchasing information and goodbye message
print("You now have ${} left in your wallet.".format(round(money, 2)))
print("Lottery ticket(s) purchased: ", 1)
print("Lottery winnings: ${}".format(lottery_win))
print("Apple(s) purchased: ", apple_amount)
print("Can(s) of beans purchased: ", canned_beans_amount)
print("Soda(s) purchased: ", soda_amount)
print("Total: ${}".format(round(money_spent, 2)))
print("Thanks for shopping with us! Come back soon!")