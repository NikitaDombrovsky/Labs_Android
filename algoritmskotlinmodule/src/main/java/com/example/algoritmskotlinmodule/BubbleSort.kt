package com.example.algoritmskotlinmodule

/**
 *
 * Bubble sort (пузырьковая сортировка), иногда называемая
 * sinking sort (потоковая сортировка) - алгоритм который многократно сравнивает список элемент
 * за элементом при необходимости меняя их значениями
 *
 */
class BubbleSort {

    companion object {
        /**
         * worst time: n²
         * best time: n²
         * average time: n²
         * amount of memory: 1
         *
         * Возвращает (Меняет) лист любого типа который реализует Comparable
         * Всегда выполняет все итерации даже если массив уже отсортирован
         */
        fun <T : Comparable<T>> sort(array: Array<T>) {
            val arraySize = array.size
            for (i in 0 until arraySize - 1) {
                for (j in 0 until arraySize - 1 - i) {
                    if (array[j] > array[j + 1]) {
                        val tmp = array[j + 1]
                        array[j + 1] = array[j]
                        array[j] = tmp
                    }
                }
            }
        }

        /**
         * worst time: n²
         * best time: n
         * average time: n²
         * amount of memory: 1
         *
         * Возвращает (Меняет) лист любого типа который реализует Comparable
         * Если неодной перестановки не выполнено то флаг isCorted остается true
         * и сортировка прекращается
         */
        fun <T : Comparable<T>> sortImproved(array: Array<T>) {
            val arraySize = array.size
            var isSorted = true
            for (i in 0 until arraySize - 1) {
                for (j in 0 until arraySize - 1 - i) {
                    if (array[j] > array[j + 1]) {
                        val tmp = array[j + 1]
                        array[j + 1] = array[j]
                        array[j] = tmp

                        isSorted = false
                    }
                }
                if (isSorted) break
            }
        }
    }
}