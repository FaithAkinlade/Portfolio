# TODO: Students, fill out statement of work header
# Student Name in Canvas: Faith Akinlade
# Penn ID: 61534906
# Did you do this homework on your own (yes / no): yes
# Resources used outside course materials: 
# I referenced from my ice_cream_stand,py submission; 
# https://stackoverflow.com/questions/50037992/find-maximum-int-value-from-list-which-contain-both-str-and-int-python; 
# https://www.w3schools.com/python/ref_math_floor.asp#:~:text=The%20math.,necessary%2C%20and%20returns%20the%20result.

# import statements
from random import shuffle, choice

# TODO: Write the functions as described in the instructions
def get_user_input(question):
    # TODO: Write your code here
    """
    Takes in a string as an argument. This is to be used as the question you want the user to be asked.
    Gets input from the user and removes trailing and leading whitespace and makes all letters lowercase.
    Reprompts if the user input is 0 after removing leading and trailing whitespaces.
    Note:
    - If the input is a number, it casts and return an integer type.
    - If the input is a power card, it returns the power card as an uppercase string.
    - If the input is any other string, it returns the string as a lowercase string.
    Returns: The post-processed user input.
    """
    answer = ""
    while answer == "":
        answer = input(question).strip().lower()
        answer
        #Check if input is a number
        if answer.isdigit() == True:
            return int(answer)
        #Check if input is a power card
        elif answer in ['soh', 'dot', 'dmt']:
            return answer.upper()
        #Check if input is a string but not a power card
        elif type(answer) == str and answer not in ['SOH', 'DOT', 'DMT']:
            return answer.lower()
        #Reprompt if input does not meet any of the above qualifications and length is 0
        else:
            answer = ""

def setup_water_cards():
    """
    Creates a shuffled list of water cards with pre-defined values and quantities.
    Hint: Use the shuffle function from the random module.
    Returns: The water cards as a list of integers.
    """
    #Create water cards list
    water_cards = ([1] * 30) + ([5] * 15) + ([10] * 8)
    #Shuffle water cards list
    shuffle(water_cards)

    return water_cards

def setup_power_cards():
    """
    Create a shuffled list of power cards with pre-defined values and quantities.
    Hint: Use the shuffle function from the random module.
    Returns: The power cards as a list of strings.
    """
    #Create list of power cards
    power_cards = (['SOH'] * 10) + (['DOT'] * 2) + (['DMT'] * 3)
    #Shuffle power cards list
    shuffle(power_cards)
    
    return power_cards

def setup_cards():
    """
    Sets up both the water card and power card piles as described in the setup_water_cards and setup_power_cards functions.
    Returns: A 2-tuple containing the water cards pile and the power cards pile, respectively.
    (Each pile is represented by a list.)
    """
    #Returning 2-tuple with water cards pile first then power cards pile second.
    return (setup_water_cards(), setup_power_cards())

def get_card_from_pile(pile, index):
    """
    Removes the entry at the specified index of the given pile (water or power) and modifies the pile by reference.
    Returns: the entry at the specified index. HINT: Use the pop function.
    """
    #Saving the entry at the specified index into new variable
    card_picked = pile[index]
    #Removing same entry from pile
    pile.pop(index)
    
    return card_picked
pile = [1,2,3]
c = pile[0]
pile.pop(0)
print(c, pile)

d = pile[0]
pile.pop(0)
print(d, pile)

def arrange_cards(cards_list):
    """
    Arrange the players cards such that:
    -The first three indices are water cards, sorted in ascending order.
    -The last two indices are power cards, sorted in alphabetical order.
    Returns: Nothing
    """
    #Creating separate lists for water cards and power cards within a player's deck.
    first_three = []
    last_two = []

    #Using for loop to appends card to the appropriate list based on card type.
    for i in range(len(cards_list)):
        if cards_list[i] in [1, 5, 10]:
            first_three.append(cards_list[i])
        else:
            last_two.append(cards_list[i])

    #Sorting by ascending and alphabetical order
    first_three = sorted(first_three)
    last_two = sorted(last_two)

    cards_list.clear()
    for i in range(len(first_three)):
        cards_list.append(first_three[i])
    for i in range(len(last_two)):
        cards_list.append(last_two[i])

def deal_cards(water_cards_pile, power_cards_pile):
    """
    Deals cards to player 1 and player 2. Each player would get 3 water cards and 2 power cards. 
    Then, call the arrange_cards function to arrange the cards.
    When dealing, alternately take off a card from the first entry in the pile.
    Returns: A 2-tuple containing the player 1`s hand and player 2`s hand, respectively. (Each hand should be represented by a list.)
    """
    #Defining hands for player_1 and player_2
    player_1 = []
    player_2 = []

    #Dealing water cards for the players
    for i in range(3):
        player_1.append(get_card_from_pile(water_cards_pile, i))
        player_2.append(get_card_from_pile(water_cards_pile, i))
    
    #Dealing power cards for the players
    for i in range(2):
        player_1.append(get_card_from_pile(power_cards_pile, i))
        player_2.append(get_card_from_pile(power_cards_pile, i))
    
    arrange_cards(player_1)
    arrange_cards(player_2)

    return player_1, player_2

def apply_overflow(tank_level):
    """
    If necessary, apply the overflow rule discussed in the “About the Assignment” section of this assignment.
    Returns: The tank level. If no overflow occurred, this is just the starting tank level.
    """
    if tank_level > 80:
        overflow = tank_level - 80
        tank_level = 80 - overflow
        return tank_level
    else:
        return tank_level

def use_card(player_tank, card_to_use, player_cards, opponent_tank):
    """
    Get a card from the player`s hand, and update the tank level based on the card that was used. 
    - This does not include drawing a replacement card, so after using the card, 
    the player_cards size will only be 4 cards.
    Apply overflow if necessary.
    Return a 2-tuple containing the player`s tank and the opponent`s tank, respectively.
    """
    #Checking if card is water card and making changes to tank if it is.
    if type(card_to_use) == int:
        player_tank += card_to_use
    elif card_to_use == 'SOH':
        player_tank += int(opponent_tank/2)
        opponent_tank -= int(opponent_tank/2)
    elif card_to_use == 'DOT':
        opponent_tank = 0
    elif card_to_use == 'DMT':
        player_tank = player_tank * 2

    player_cards.remove(card_to_use)
    player_tank = apply_overflow(player_tank)

    return player_tank, opponent_tank

def discard_card(card_to_discard, player_cards, water_cards_pile, power_cards_pile):
    """
    Discard the given card from the player`s hand and return it to the bottom of the 
    appropriate pile. (Water cards should go in the water card pile and power cards should
    go in the power card pile.) The bottom of the pile is the last index in the list.
    Same as use_card(), this function does not include drawing a replacement card, so after 
    calling this function, the player_cards size will only be 4 cards.
    This function does not return anything.
    """
    #Removing card from player's deck and placing at bottom of water cards pile if the card is a water card.
    if type(card_to_discard) == int:
        water_cards_pile.append(card_to_discard)
    #Removing card from player's deck and placing at bottom of power cards pile if the card is a power card.
    else:
        power_cards_pile.append(card_to_discard)

    player_cards.remove(card_to_discard)


def filled_tank(tank):
    """
    Determine if the tank level is between the maximum and minimum fill values (inclusive).
    Returns: A boolean representing whether the tank is filled.
    This will be True if the tank is filled.
    """
    #Determining if value is between 75 and 80 inclusive
    if tank >= 75 and tank <= 80:
        return True

def check_pile(pile, pile_type):
    """
    Checks if the given pile is empty. If so, call the pile`s setup function to replenish the pile.
    - pile_type is a string to determine what type of pile you are checking (“water” or “power”)
    This function does not return anything.
    """
    #Checks if pile is empty
    if len(pile) == 0:
        #If yes checks if pile is water or power and replenishes accordingly
        if pile_type == "water":
            pile.append(setup_water_cards())
        elif pile_type == "power":
            pile.append(setup_power_cards())
    else:
        pile = pile


def human_play(human_tank, human_cards, water_cards_pile, power_cards_pile, opponent_tank):
    """
    -Show the human player`s water level and then the computer player`s water level.
    -Show the human player their hand and ask them if they want to use or discard a card. If the human player enters an invalid answer, reprompt until a valid answer is entered.
    -Carry out the human`s turn based on the action they have chosen (based on user input).
    -Be sure to use the get_user_input function.
    -Print the card the human player uses or discards. If the human player enters a card to use or discard which is not in their hand, reprompt until a valid card is entered.
    -Once the human has used or discarded a card, draw a new card of the same type they just used/discarded.
    -Make sure that the human`s hand is still properly arranged after adding the new card.
    -Returns: A 2-tuple containing the human`s tank level and the computer`s tank level, respectively.
    """
    #Letting player know that it is their turn
    print("\n=== Human Player's turn ===")
    print("Your water level is at: ", human_tank)
    print("Computer's water level is at: ", opponent_tank)
    print("Here is your hand: ", human_cards)
    
    #Prompting player to use or discard and reprompting if answer is invalid using while loop
    player_move = ""

    while player_move == "":
        player_move = get_user_input("Do you want to use or discard a card (u / d)? ")
        player_move
        
        #Defining what happens when player chooses 'u'
        if player_move == "u":
            card_to_use = ""
            
            #Asking which card the player wants to play from their deck and reprompting if card is not in player's deck
            while card_to_use == "":
                card_to_use = get_user_input("Which card do you want to use?")
                card_to_use
                
                if card_to_use in human_cards:
                    #Calling use function for player's card
                    human_tank, opponent_tank = use_card(human_tank, card_to_use, human_cards, opponent_tank)
                    
                    print("Now playing with card: ", card_to_use)
                    
                    #If card chosen for turn is a water card, player must pick new water card and same for power card
                    if type(card_to_use) == int:
                        new_card = get_card_from_pile(water_cards_pile, 0)
                        print("Now drawing water card: ", new_card)
                    else:
                        new_card = get_card_from_pile(power_cards_pile, 0)
                        print("Now drawing power card: ", new_card)

                else:
                    card_to_use = ""

        #Defining what happens when user chooses 'd'
        elif player_move == "d":
            card_to_discard = ""

            #Asking which card to discard?
            while card_to_discard == "":
                card_to_discard = get_user_input("Which card do you want to discard?")
                card_to_discard
                
                if card_to_discard in human_cards:
                    #Calling use function for player's card
                    discard_card(card_to_discard, human_cards, water_cards_pile, power_cards_pile)
                    print("Now playing with card: ", card_to_discard)
                    
                    #If card chosen for turn is a water card, player must pick new water card and same for power card
                    if type(card_to_discard) == int:
                        new_card = get_card_from_pile(water_cards_pile, 0)
                        print("Now drawing water card: ", new_card)
                    else:
                        new_card = get_card_from_pile(power_cards_pile, 0)
                        print("Now drawing power card: ", new_card)

                else:
                    card_to_discard = ""
        
        #Returns empty string and reprompts if niether u nor d is chosen
        else:
            player_move = ""

    #Picking up new card 
    human_cards.insert(2, new_card)

    #Arranging new deck
    arrange_cards(human_cards)

    #Printing player water level, computer water level and player's deck after player's move
    print("Your water level is now at: ", human_tank)
    print("Computer's water level is now at: ", opponent_tank)
    print("Your cards are now: ", human_cards)

    return human_tank, opponent_tank


def computer_play(computer_tank, computer_cards, water_cards_pile, power_cards_pile, opponent_tank):
    """
    This is the function where we execute the computer`s strategy.
    You are supposed to be somewhat creative here, but make sure your code is deterministic (not random).
    The computer`s strategy should consider all of its cards when playing. For example, you
    should not have a strategy where the computer always plays the first water card or the
    first power card.
    The computer should not “cheat.” They should not be able to see any cards from the
    human`s hand, the water card pile, or power card pile. When they draw a card, they
    should only see that card and no cards from the rest of the water or power card pile.
    This function should carry out the computer`s turn based on the action that the computer chooses.
    -Remember to handle potential overflows.
    -Print the card the computer player uses or discards.
    -Once the computer has used or discarded a card, give them a new card of the same type they just used/discarded.
    -Make sure that the computer`s hand is still properly arranged after adding the new card.
    -Return a 2-tuple containing the computer`s tank level and the human`s tank level, respectively.
    """
    #Letting player know that it is the computer player's turn
    print("\n=== Computer Player's turn ===")
    print("Computer's water level is at: ", computer_tank)
    print("Your water level is at: ", opponent_tank)

    #Deciding the card that computer will play with.
    card_to_use = ""
    
    #Here, we program situations where the computer may decide to use or discard a card.(Main strategy: try not to let the other guy win!)
    #1. If the opponent is close to winning and computer is far behind(40 points or more), the computer will play a power card.
    if opponent_tank > 65 and opponent_tank < 75 and computer_tank <= 40:

        #Deciding the power card to play depending on what power cards the computer has.
        if 'DOT' in computer_cards:
            card_to_use = 'DOT'
        elif 'SOH' in computer_cards and 'DOT' not in computer_cards:
            card_to_use = 'SOH'
        elif 'SOH' not in computer_cards and 'DOT' not in computer_cards:
            card_to_use = 'DMT'
        
        #Calling use function to execute computer's move
        computer_tank, opponent_tank = use_card(computer_tank, card_to_use, computer_cards, opponent_tank)

    #2. If the computer's tank is 65 and it has a 10, it will play a 10 else it will play the highest water card or a DOT card if it has one.
    elif computer_tank == 65:
        if 10 in computer_cards and 'DOT' in computer_cards:
            card_to_use = 10
        elif 10 not in computer_cards and 'DOT' in computer_cards:
            card_to_use = 'DOT'
        else:
            card_to_use = max(computer_cards[0:3])
        #Calling use function to execute computer's move
        computer_tank, opponent_tank = use_card(computer_tank, card_to_use, computer_cards, opponent_tank)

    #3. If the computer's tank is less than or equal to half the opponent's tank and it has an SOH card it will play that or play a DMT.
    elif computer_tank <= opponent_tank/2 and computer_tank != 0:
        if 'SOH' in computer_cards:
            card_to_use = 'SOH'
        elif 'DMT' in computer_cards:
            card_to_use = 'DMT'
        else:
            card_to_use= max(computer_cards[0:3])
         #Calling use function to execute computer's move
        computer_tank, opponent_tank = use_card(computer_tank, card_to_use, computer_cards, opponent_tank)

    #4. Computer will not play a power card if both players are at 0.
    elif computer_tank == 0 and opponent_tank == 0:
        card_to_use = max([i for i in computer_cards if isinstance(i, int)])
        computer_tank, opponent_tank = use_card(computer_tank, card_to_use, computer_cards, opponent_tank)

    #5. Computer chooses winning power card
    #lif 

    #6. In any other case, computer will play the largest water card
    else:
        card_to_use = max([i for i in computer_cards if isinstance(i, int)])
         #Calling use function to execute computer's move
        computer_tank, opponent_tank = use_card(computer_tank, card_to_use, computer_cards, opponent_tank)

    print("Computer is playing with card: ", card_to_use)
    print("Computer's water level is now at: ", computer_tank)
    print("Your water level is now at: ", opponent_tank)
    
    #Picking up new card for compute player
    if type(card_to_use) == int:
        new_card = get_card_from_pile(water_cards_pile, 0)
    else:
        new_card = get_card_from_pile(power_cards_pile, 0)
    
    computer_cards.append(new_card)
    arrange_cards(computer_cards)
    #computer_tank = apply_overflow(computer_tank)

    return computer_tank, opponent_tank

def main():
    # TODO: Write your code as described in the instructions
    """
    ● The main function would be where you would structure the flow of your game, calling the functions defined above, as needed.
    ● As noted in the “User Interface” section of this assignment, remember to choose a random player to go first.
    ● For each turn, a player can use a card (then draw a new card) or discard a card (then draw a new card).
    ● To help you with the structure, a flowchart of the game is shown:
    """
    print("Welcome to the WATER TANK game and play against the computer!")
    print("The first player to fill their tank wins the game. Go get 'em!")

    input('Press enter to start the game! \n')

    program_running = True
    #Randomly choose first player
    players = ["Human", "Computer"]
    first_to_play = choice(players)

    #Setup game
    setup_cards()
    human_tank = 0
    computer_tank = 0
    water_cards_pile = setup_cards()[0]
    power_cards_pile = setup_cards()[1]

    #Deal cards
    cards_delt = deal_cards(water_cards_pile, power_cards_pile)
    human_cards = cards_delt[0]
    computer_cards = cards_delt[1]
    
    #Print out who is going first
    print("The {} Player has been selected to go first.".format(first_to_play))

    while program_running:
        if first_to_play == "Human":
            human_tank, computer_tank = human_play(human_tank, human_cards, water_cards_pile, power_cards_pile, computer_tank)

            #Check if player won. End game, notify players and print out closing message if they did
            if filled_tank(human_tank) == True: #or filled_tank(computer_tank) == True:
                program_running = False
                winner = "Human"
                print("\n=== Game Over ===")
                print("{} Player won".format(winner))
                break

            #Check if water card pile is empty
            check_pile(water_cards_pile, "water")

            #Check if power cards pile is empty
            check_pile(power_cards_pile, "power")

            computer_tank, human_tank = computer_play(computer_tank, computer_cards, water_cards_pile, power_cards_pile, human_tank)
            
            #Check if player won. End game, notify players and print out closing message if they did
            if filled_tank(computer_tank) == True:
                program_running = False
                winner = "Computer"
                print("\n=== Game Over ===")
                print("{} Player won".format(winner))
                break

            #Check if water card pile is empty
            check_pile(water_cards_pile, "water")

            #Check if power cards pile is empty
            check_pile(power_cards_pile, "power")
        else:
            computer_tank, human_tank = computer_play(computer_tank, computer_cards, water_cards_pile, power_cards_pile, human_tank)
            
            #Check if player won. End game, notify players and print out closing message if they did
            if filled_tank(computer_tank) == True:
                program_running = False
                winner = "Computer"
                print("\n=== Game Over ===")
                print("{} Player won".format(winner))
                break

            #Check if water card pile is empty
            check_pile(water_cards_pile, "water")

            #Check if power cards pile is empty
            check_pile(power_cards_pile, "power")

            human_tank, computer_tank = human_play(human_tank, human_cards, water_cards_pile, power_cards_pile, computer_tank)
            #Check if player won. End game, notify players and print out closing message if they did
            if filled_tank(human_tank) == True:
                program_running = False
                winner = "Human"
                print("\n=== Game Over ===")
                print("{} Player won".format(winner))
                break

            #Check if water card pile is empty
            check_pile(water_cards_pile, "water")

            #Check if power cards pile is empty
            check_pile(power_cards_pile, "power")

if __name__ == '__main__':
    main()







