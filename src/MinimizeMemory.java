/**
 * @author Dustin Cassell
 * @since 7 Mar 2022
 */

import java.util.List;

public class MinimizeMemory {

    /**
     * Purpose: Finds the minimum amount of contiguous memory that includes up to m number of
     *          memory addresses.
     * @param process Array of the memory sizes in contiguous order.
     * @param m total number of memory slots to be removed.
     * @return Total memory left after delete.
     */
    public int minimizeMemory(List<Integer> process, int m) {
        int minMemory = 0, maxMemory = 0, tempMemory = 0;
        int rightPtr = m - 1;
        int leftPtr = 0;

        // If the process list is 1 process.
        if (process.size() <= 1) {
            return 0;
        }

        // if the m value is larger than the available memory addresses.
        if (m > process.size()) {
            return -1;
        }

        // Add the memory sizes to find the max memory.
        for (int i = 0; i < process.size(); ++i) {
            maxMemory += process.get(i);
        }
        minMemory = maxMemory; // this is for future comparisons to find smaller memory sizes.

        // add only the memory sizes for the first m - 1 addresses.
        for (int i = 0; i < m - 1; ++ i) {
            tempMemory += process.get(i);
        }

        // Use a while loop rather than for loops because nested for loops were way too expensive.
        while (rightPtr <= process.size() - 1 && leftPtr < process.size() - 1) {
            tempMemory += process.get(rightPtr);
            if (minMemory > maxMemory - tempMemory) {
                // Replaces memory size if new combo is smaller than previous memory minimum.
                minMemory = maxMemory - tempMemory;
            }
            tempMemory -= process.get(leftPtr);
            ++rightPtr;
            ++leftPtr;
        }

        return minMemory;
    }

}
