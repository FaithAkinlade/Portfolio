# Author: Faith Akinlade
# Date: 10/12/2025
# Description: This program computes the average of the values in the numbers.txt file.

import statistics as st

def main():
    # Reading numbers from file into list of integers
    with open("numbers.txt", 'r') as f:
        nums = []
        for i in f.readlines():
            print(i.strip())
            nums.append(int(i.strip()))
    f.close()

    # Finding and printing average
    average = st.mean(nums)
    print("\nThe average of the numbers is", average)

if __name__ == "__main__":
    main()
