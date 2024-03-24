/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csvreader;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
class MergeSort {
    
        //MergeSort will be responsible for coordinating 
        //the parallel sorting process using threads and merging the results to obtain the final sorted list.
        private List<Integer> data;
        private int numThreads;
        private List<MergeSortThread> threads;

        //constructor is responsible for configuring an 
        //instance of the MergeSort class with the data and number of threads needed for the sorting process
        
        public MergeSort(List<Integer> data, int numThreads) {
            this.data = data;
            this.numThreads = numThreads;
            threads = new ArrayList<>();
        }

        //This code creates a set of threads to execute the parallel sort algorithm
        public List<Integer> sortDescending() {
        MergeSortThread mainThread = new MergeSortThread(0, data.size() - 1);threads.add(mainThread);
        
            for (int i = 0; i < numThreads; i++) {
                MergeSortThread t = new MergeSortThread(mainThread.left, mainThread.right);
                threads.add(t);
                t.start();
            }
            
            //code ensures that program execution waits until all threads have completed their 
            //sorting task before continuing, to ensure that the sorted list of integers is returned correctly.
            try {
            for (MergeSortThread t : threads) {
                t.join();
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }   

        return data;
        }
        private class MergeSortThread extends Thread {

            //The left and right indices are used to determine the range of list elements that the thread should sort.
            private int left;
            private int right;

            public MergeSortThread(int left, int right) {
                this.left = left;
                this.right = right;
            }
            
            @Override
            public void run() {
            if (left < right) {
                int mid = (left + right) / 2;

                MergeSortThread leftThread = new MergeSortThread(left, mid);
                MergeSortThread rightThread = new MergeSortThread(mid + 1, right);

                leftThread.start();
                rightThread.start();
                
                try {
                    leftThread.join();
                    rightThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int i = left;
                int j = mid + 1;
                
                List<Integer> temp = new ArrayList<>();
                
                //Created a loop to compare and add the elements from the two halves of the list 
                //ordered by the leftThread and rightThread threads
                
                while (i <= mid && j <= right) {
                    if (data.get(i) > data.get(j)) {
                        temp.add(data.get(i));
                        i++;
                    } else {
                        temp.add(data.get(j));
                        j++;
                    }
                }
                
                while (i <= mid) {
                    temp.add(data.get(i));
                    i++;
                }

                while (j <= right) {
                    temp.add(data.get(j));
                    j++;
                }
for (int k = left; k <= right; k++) {
                    data.set(k, temp.get(k - left));
                }
            }
        }
    }
}
