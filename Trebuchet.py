# Author: Faith Akinlade
# Date: 10/06/2025
# Description: This program records the distances a projectile travels during a number of trials and keeps track of
# the three furthest distances using Lists.

topThreeDist = [0,0,0]
topThreeTrials = [0,0,0]
addTrials = "Y"
currentTrial = 1

while addTrials == "Y":
    topThreeDist = sorted(topThreeDist, reverse= True)
    newIntDist = int(input(f"Please enter a new distance for trial {currentTrial}: "))

    if newIntDist > topThreeDist[0]:
        topThreeDist[0], topThreeDist[1], topThreeDist[2] = newIntDist, topThreeDist[0], topThreeDist[1]
        topThreeTrials[0], topThreeTrials[1], topThreeTrials[2] = currentTrial, topThreeTrials[0], topThreeTrials[1]
    elif topThreeDist[1] < newIntDist <= topThreeDist[0]:
        topThreeDist[1], topThreeDist[2] = newIntDist, topThreeDist[1]
        topThreeTrials[1], topThreeTrials[2] = currentTrial, topThreeTrials[1]
    elif topThreeDist[2] < newIntDist <= topThreeDist[1]:
        topThreeDist[2] = newIntDist
        topThreeTrials[2] = currentTrial
    else:
        continue

    addTrials = input("Would you like to enter a new distance and trial number (Y/N)? ").strip()

    # Incrementing the trial number.
    if addTrials == "Y":
        currentTrial += 1

# Finding largest width.
width = max(len("Trial"), len("Distance"), len(str(max(topThreeDist))),len(str(max(topThreeTrials))))

# Printing resulting table with adjusted width.
print("Here are the top 3 Farthest Distances and their numbers!")
print("Trial".ljust(width),"Distance".ljust(width))
for i in range(3):
    print(str(topThreeTrials[i]).ljust(width),
          str(topThreeDist[i]).ljust(width))
