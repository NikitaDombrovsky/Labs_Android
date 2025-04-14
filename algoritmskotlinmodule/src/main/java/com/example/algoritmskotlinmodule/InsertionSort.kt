package com.example.algoritmskotlinmodule

/**
 *
 * Массив делится на две части: отстортированную и неотсортированную
 *
 * Элементы из неотсортированной части перемещаются в правильное место в отсортированной части
 *
 * worst time: n²
 * best time: n
 * average time: n²
 *
 * amount of time: 1
 *
 */

class InsertionSort {
    companion object {

        fun <T : Comparable<T>> sort(array: Array<T>) {
            val arraySize = array.size
            for (i in 1 until arraySize) {
                val current = array[i]
                // Последний элемент в отсортрованной части
                var j = i - 1
                // Сдвигает элементы больше current вправо
                while (j >= 0 && array[j] > current) {
                    array[j + 1] = array[j]
                    j--
                }
                array[j + 1] = current
            }
        }
    }
}