# Question:

You have two unmarked bottles that can hold 3 liters and 5 liters respectively, and a bathtub with unlimited water. You are allowed to either fill a bottle, empty a bottle or pour one bottle into the other until either the source bottle is empty or the target bottle is full.

- how can 1 liter be measured?
- how can 4 liters be measured?

Implement a solution that would solve this problem and find the minimum amount of steps required to measure the desired amount of water (1 and 4 liters).
Note that the program should find the shortest solution given only the information above, i.e. the volume of the unmarked bottles and the desired target amount.


# Solution:
All codes are in Java and under Intellij. 
Here are two classes in it. One is "Bottle" class,and the other is "Main" class.
* The "Bottle" class is for construct the 3L and 5L bottle. It also defines how a bottle would be filled, emptied and transfer water to the other bottle. Furthermore, here are also 3 boolean variables to flag whether a bottle has just filled, emptied or transferred water.
  * To each bottle, here are three behaviors.
    * fillBottle - make the bottle to the full volume.
    * emptyBottle - make the current volume to zero.
    * transferBottle - if the current volume of the bottle is more than zero. Then this bottle can transfer its water to the  other bottle until it is empty itself or the other bottle is filled up and under this situation it can keep the water left in itself.
* The "Main" class is to get solutions by calling functions and fields in the "Bottle" class. 
  * Find the simplest solution.
    * Start 100 times searches in "Main" class. 
    * In each search
    1. It begins byfilling up a big bottle or a small bottle, as it is meaningsless to fill both two or empty them or transfer water between them. And here I use random number between 0 and 1 and switch case to decide which bottle to begin. 
    2. By using random number among 0 t0 5 to call randomly the 3 behaviors (fill, empty, transfer) of 5L bottle and the same 3 behaviors of 3L bottle to try out a solutin that can get target volume within **10** steps.*Here are five rules which are about how bottle functions shoulde be randomly called. Please read it in comments in codes*
    3. If here is one solution found in a searching, then save the result as a string
    4. Save all solution strings unrepeatly in an ArrayList.
    5. Bubble sort the Arraylist and print it out in Console. The user can easily find out the simplest solution.


# How to check the result:
* In console user can input the target volume that is wanted.
* It would run 100 times check all possible solutions that can solve the problem within 10 steps. 
* Then the solutions would be saved in a ArrayList named as "results" and print out from simple to complex. User can easily find out the simplest solution.
