package com.example.algoritmskotlinmodule

import kotlin.random.Random

/**
 *
 * QuickSort - алгорит на основе "разделяй и властвуй"
 *
 * 1) Выбор опорного элемента
 *
 * 2) Массив разделяется на две части:
 * - элементы меньше опорного
 * - элементы большие опорного
 *
 *
 * worst time: n²
 * best time: n * log(n)
 * average time: n * log(n)
 *
 * amount of memory: n
 *
 */

class QuickSort {
    fun <T : Comparable<T>> sort(array: Array<T>, start: Int = 0, end: Int = array.size - 1) {
        if (array.isEmpty()) return
        if (start >= end) return

        // Опорный элемент выбранный случайным образом
        val pivotIndex = Random.nextInt(start, end + 1)
        val pivot = array[pivotIndex]

        var i = start
        var j = end
        while (i <= j) {
            while (array[i] < pivot) {
                i++
            }
            while (array[j] > pivot) {
                j--
            }
            if (i <= j) {
                val tmp = array[i]
                array[i] = array[j]
                array[j] = tmp
                i++
                j--
            }
        }

        // Рекурсивная сортировка
        if (i < end) sort(array, i, end)
        if (0 < j) sort(array, start, j)
    }
}