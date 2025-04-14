package com.example.algoritmskotlinmodule

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        containsDuplicate(arrayOf(1,2,3,1))
        //quickSort()
    }

    fun bubbleSort() {
        val arrayInt = arrayOf(1, 43, 7, 0)
        BubbleSort.sortImproved(arrayInt)
        val arrayString = arrayOf("banana", "apple", "cherry", "date")
        BubbleSort.sortImproved(arrayString)
        Log.e("!", "!")
    }

    fun insertionSort() {
        val array = arrayOf(5, 3, 4, 1, 2)
        println("Before sorting: ${array.joinToString(", ")}")

        InsertionSort.sort(array)

        println("After sorting: ${array.joinToString(", ")}")
    }

    fun containsDuplicate(nums: Array<Int>): Boolean {
        val arraySize = nums.size
        for (i in 0 until arraySize - 1) {
            for (j in 0 until arraySize - 1 - i) {
                if (nums[j] == nums[j + 1]) {
                    return true
                }
            }
        }
        return false
    }
    fun mergeSort() {
        val array = arrayOf(5, 3, 4, 1, 2)
        println("Before sorting: ${array.joinToString(", ")}")

        MergeSort.sort(array)

        println("After sorting: ${array.joinToString(", ")}")

    }

    fun quickSort() {
        val array = arrayOf("banana", "apple", "cherry", "date")
        //val array = arrayOf(5, 3, 4, 1, 2)
        println("Before sorting: ${array.joinToString(", ")}")

        val test = QuickSort()
        test.sort(array, 1, 2)
        //  QuickSort.sort(array, 1, 2)

        // QuickSort.sort(arrayOf(1,2,3), 1, 3)

        println("After sorting: ${array.joinToString(", ")}")
    }
    fun selectionSort(){

    }
}