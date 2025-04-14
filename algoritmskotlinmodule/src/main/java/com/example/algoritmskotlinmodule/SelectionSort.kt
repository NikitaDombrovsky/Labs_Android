package com.example.algoritmskotlinmodule

/**
 *
 * Selection sort алгоритм который
 * по принципу поиска самого маленького (большого) элемента
 * в неотсортированной части и перевещение его в остортированную
 *
 *
 * worst time: n²
 * best time: n²
 * average time: n²
 *
 * amount of memory: 1
 *
 */

class SelectionSort {

    fun <T : Comparable<T>> sort(array: Array<T>) {
        val arraySize = array.size
        for (i in 0 until arraySize - 1) {
            var min = i
            for (j in i + 1 until arraySize) {
                if (array[min] > array[j]) {
                    min = j
                }
            }
            if (min != i) {
                val tmp = array[i]
                array[i] = array[min]
                array[min] = tmp
            }
        }
    }

}