package com.xtremax.recruitment.livecoding.pathfinder;

import com.xtremax.recruitment.livecoding.pathfinder.model.Animal;
import com.xtremax.recruitment.livecoding.pathfinder.model.Bird;
import com.xtremax.recruitment.livecoding.pathfinder.model.Frog;
import com.xtremax.recruitment.livecoding.pathfinder.model.Rat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

/**
 * Pathfinder
 * Pathfinder purpose is to find minimum move taken by different animal for different path
 * Each path can have holes to the abyss.
 * We need to help those animals to plan their move so that they won't fall into the abyss.
 * <p>
 * Each animal can move for different distance:
 * Frog can move 3 block at once,
 * Rat can move only 1 block at a time,
 * Bird can move 8 block at once.
 * <p>
 * Path that need to be thread upon is defined as an array with 1 as safe block and 0 as abyss block.
 * Each animal starts its move from path's first index and end in path's last index.
 * <p>
 * <p>
 * ================================ ATTENTION PLEASE ================================
 * <p>
 * Your ONLY task is to implement the following two methods:
 * 1. findMinimumMoves
 * 2. showMoves
 * <p>
 * You are ALLOWED to
 * 1. Add new method(s) in this file.
 * 2. Add additional test case(s) in Main.
 * <p>
 * You are NOT ALLOWED to
 * 1. Add any new method in other files.
 * 2. Change signature of any existing methods.
 * Method signature includes
 * - method's name
 * - return type
 * - number of parameters
 * - parameters' type
 * - access modifier
 * <p>
 * --- Please make sure your code is error-free when built.
 */
public class PathFinder {

    public static void main(String[] args) {


        testResult(3, findMinimumMoves(new Frog(), new int[]{1, 1, 0, 0, 1, 1, 1}));
        testResult(0, findMinimumMoves(new Rat(), new int[]{1, 1, 0, 0, 1, 1, 1}));
        testResult(1, findMinimumMoves(new Bird(), new int[]{1, 1, 0, 0, 1, 1, 1}));
        System.out.println();
        testResult("- croak - - croak - croak", showMoves(new Frog(), new int[]{1, 1, 0, 0, 1, 1, 1}));
        testResult("- squeak dead", showMoves(new Rat(), new int[]{1, 1, 0, 0, 1, 1, 1}));
        testResult("- - - - - - chirp", showMoves(new Bird(), new int[]{1, 1, 0, 0, 1, 1, 1}));
    }

    private static void testResult(Object expected, Object actual) {
        if (Objects.equals(expected, actual)) {
            System.out.println("Answer is correct");
        } else {
            System.out.println("Answer is wrong. Expected result is '" + expected + "' but your result is '" + actual + "'");
        }
    }

    /**
     * FindMinimumMoves
     * <p>
     * Return minimum moves taken by animal to safely arrive at the end of path. Return 0 if there is no safe moves.
     * Example:
     * path = [1, 1, 0, 0, 1, 1, 1]
     * Frog has minimum moves taken = 3
     * Rat will fall into the abyss
     * Bird has minimum moves taken = 1
     */


    public static int findMinimumMoves(Animal animal, int[] path) {


        int current_pos = 0;
        int leaps_to_make = animal.getMaxLeap(); //
        int moves_made = 0;

        while ((current_pos + leaps_to_make) < path.length) {
            if (check_legal_leaps(current_pos, leaps_to_make, path) != -1) {
                current_pos = check_legal_leaps(current_pos, leaps_to_make, path);
                moves_made++;
                leaps_to_make = animal.getMaxLeap();
            } else {
                leaps_to_make--; //
                if (leaps_to_make == 0) {
                    return 0;

                }

            }
        }
        return moves_made + 1;// throw new NotImplementedException();
    }


    private static int check_legal_leaps(int currentPos, int leapsToMake, int[] path) {

        if (path[currentPos + leapsToMake] == 1) //# check if true at position to jump to
            return currentPos + leapsToMake;

        else
            return -1;


    }

    /**
     * ShowMoves
     * Return string of move taken with each animal will make sound after each move.
     * Show dying message if there is no safe move anymore. Animal will take longest move first.
     * <p>
     * Example:
     * path = [1, 1, 0, 0, 1, 1, 1]
     * frog = - croak - - croak - croak
     * rat  = - squeak dead
     * bird = - - - - - - chirp
     */
    public static String showMoves(Animal animal, int[] path) {

        int[] intArray = new int[path.length];
        int current_pos = 0;
        int leaps_to_make = animal.getMaxLeap();


        while ((current_pos + leaps_to_make) < path.length) {
            if (check_legal_leaps(current_pos, leaps_to_make, path) != -1) {
                current_pos = check_legal_leaps(current_pos, leaps_to_make, path);
                intArray[current_pos] = 1;

                leaps_to_make = animal.getMaxLeap();
            } else {
                leaps_to_make--; //
                if (leaps_to_make == 0) {
                    intArray[current_pos] = 2;
                    break;
                }
            }
        }
        intArray[path.length - 1] = 1;
        return getResult(intArray, animal);
    }

    private static String getResult(int[] pathTaken, Animal animal) {

        String result = "";

        for (int i = 0; i < pathTaken.length; i++) {
            if (pathTaken[i] == 1) {
                result += " " + animal.makeSound();

            } else if (pathTaken[i] == 2) {
                result += animal.dyingMessage();
                break;
            } else {
                if (i == 0) {
                    result += "-";
                } else {
                    result += " -";
                }
            }
        }
        return result;
    }
}


