package com.example.tictactoe.java_coding_examples

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_java_coding.*
import java.lang.Exception
import java.util.*

class JavaCodingExamples: View.OnClickListener,AppCompatActivity() {




    companion object{

        var integerYT:Int = 0

        fun returnInt():Int{

            integerYT = 10

            return integerYT;
        }
    }




    override fun onClick(v: View?) {

        when(v){

            btnFibonaci -> {

                var totalValue = callFibonaciMethod(4)
                Log.i(TAG,"Sum of fibonacii"+totalValue)
            }
        }

    }

    private fun callFibonaciMethod(number: Int):Int {

        if(number <= 1){
            return number
        }else{
            var left = callFibonaciMethod(number-1)
            var right = callFibonaciMethod(number-2)
//            return callFibonaciMethod(number-1)+callFibonaciMethod(number-2)

            return left+right
        }


    }

    lateinit var s : Stack<String>
    var TAG  = JavaCodingExamples::class.java.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_coding)



        btnFibonaci.setOnClickListener(this)

        //swap any 2 things


        var a = IntArray(10)
        var b = IntArray(10)

        run {
            var i = 0
            var j = 10
            while (i < 10 && j > 0) {
                a[i] = i
                b[i] = j
                i++
                j--
            }
        }

        for (i in a.indices) {
            Log.e("before  swap", "" + a[i])
            Log.e("before  swap", "" + b[i])

        }

//        b=a
//        a = swapMethod(b, a.copyInto(b,0,0,b.size) )
        a = swapMethod(b, {b=a}() )
        for (i in a.indices) {
            Log.e("after  swap A", "" + a[i])
            Log.e("after  swap B", "" + b[i])

        }


        a = a.reversedArray()
        var newMergedArray = merge2Arrays(a,b)

        for(i in 0 until newMergedArray.size){
            Log.i(TAG,"After merging "+newMergedArray[i].toString())
        }





       /* var arraySwapA:IntArray = intArrayOf(1,2,3,4,5,6)
        var arraySwapB = intArrayOf(6,5,4,3,2,1)



//        Arrays.copyOf(arraySwapA)

//        var temp = Arrays.copyOf(arraySwapA,arraySwapA.size)
        arraySwapA = swapMethod(arraySwapB, arraySwapB=arraySwapA )

        Log.i(TAG, arraySwapA.toString())*/

            //example to check it hits the thundercloud or not
        var arrayOfClouds = arrayOf(0,0,1,0,1,0,0,1,0)

        var checkCloudsHitting = checkCloudsHitting(arrayOfClouds, 0)
        Log.i(TAG,"arrayOfClouds: "+ checkCloudsHitting.toString())


        //example to check strings matching or not
        var n =12

        var  s: String = "DDUUDDUDUUUD"

        var countingValleys = countingValleys(n, s)
        Log.i(TAG, countingValleys.toString())



        //example to check repeatation of a in a string
        var arrayOfRepeatations = arrayOf("abaabaabaaba")

        var count = countingAs(arrayOfRepeatations,10)



        kingdomQuestion.setOnClickListener {
            kingdomAnswer()
        }

        Log.i(TAG, checkFinally().toString())


        //working normal stack functions
//        stackExample()


        //working - used to remove duplicate elements in array
        RemoveDuplicateArrayElements()

        //remove duplicate after sorting
        RemoveDuplicateSorting()

        //todo
        QueueExample()



        var arrayA = intArrayOf(1,2,3,4,5,6,7,8,9,10,11,12)
        var arrayB = intArrayOf(1,3,4,5,6,7,8,9,10,11,12)

       // var sizeA = arrayB.size
        var sizeB = arrayB.size



//        checkDifferentElement(arrayA,arrayB,sizeB)

        var start = 0;
        var end = sizeB - 1

        var index:Int = arrayB.size

       checkDifferentElementUsingBinarySearch(arrayA,arrayB,start,end,index)


        //binary search 3 in arrayA
        var element:Int = binarySearchFromA(arrayA,0,arrayA.size-1,3,-1)
        Log.i(TAG,"The element is : "+(arrayA[element]))
        Log.i(TAG,"The index is : "+element)




//        merge sort -working
//        var mergableArray = intArrayOf(1,3,5,9,7,2)
        var mergableArray = intArrayOf(1,3,2)


        var mergeSortclass = MergeSortClass()

        mergeSortclass.mergeSortClass(mergableArray,0,mergableArray.size-1)



        //2nd method -working

//        val numbers = mutableListOf(38,27,43,3,9,82,10)
//        val sortedList = mergeSort(numbers)
//        println("Unsorted: $numbers")
//        println("Sorted: $sortedList")



        // calling methods
//        mergeSort(mergableArray,0,mergableArray.size-1)

        /*for(i in 0 until mergableArray.size)
            Log.i(TAG,"The element of mergesort ARRAY : "+mergableArray[i])*/



        //calling merge method -3 - working

//        var mergeSortNew = MergeSort()
//
//        var arrayOfLong:Array<Long> = arrayOf(1,4,6,2,3,8)
//        var sort = mergeSortNew.sort(arrayOfLong)
//
//        for(i in 0..sort.size -1){
//            Log.i(TAG,"array sorted is : "+sort[i])
//
//        }


    }

    private fun merge2Arrays(a: IntArray, b: IntArray):IntArray {

        var i=0;
        var j=0;
        var x=0
        var newMegedArray = IntArray(a.size+b.size)

        while(i < a.size  && j< b.size){

            if(a[i]<b[j]){
                newMegedArray[x] = a[i]
                i++

            }else{
                newMegedArray[x] = b[j]
                j++
            }
            x++

        }

        while (j < b.size){
            newMegedArray[x] = b[j]
            j++
            x++
        }

        while (i < a.size){
            newMegedArray[x] = a[i]
            i++
            x++
        }

        return newMegedArray

    }

    /*private fun swapMethod(array1: IntArray, array2: Any): IntArray {
        return array1

    }*/

    fun swapMethod(a: IntArray, unit: Unit): IntArray {

        return a

    }

    private fun countingAs(arrayOfRepeatations: Array<String>, totalLettersToCheck: Int): Int {


        var size = arrayOfRepeatations.size

        return 10;

    }

    private fun checkCloudsHitting(arrayOfClouds: Array<Int>, start: Int): Int {




        var size = arrayOfClouds.size
        var jump = 0
        var i=1;
        var j = size-1;

        while(j > i){

            if(  arrayOfClouds[i+1]== 0){
                i += 2

            }else{
                i++
            }
            jump++


        }
        if(j==i){
            jump++
        }


        return jump


     /*   for(i in 0 until size){

            if(arrayOfClouds[i+1] == 1){


            }else{

            }

            jump++
        }*/


    }


    fun countingValleys(n: Int, s: String): Int {

            var array = s.toCharArray()
            var totalCount = 0
            var temp = 0
            for(i in 0 .. n-1){

                if(array[i].toString().equals("U")){
                    temp++
                }else{
                    temp--
                }

                if(array[i].toString().equals("U") && temp == 0){
                    totalCount++
                }


            }

            System.out.println(totalCount.toString())

            return totalCount

        }


    private fun checkFinally():Int {

        try {
            return 10

        }catch (e:Exception){
            e.printStackTrace()
        }

        finally {
            return 30
        }

    }

    private fun kingdomAnswer() {


    }


    fun mergeSort(list: List<Int>): List<Int> {
        if (list.size <= 1) {
            return list
        }

        val middle = list.size / 2
        var left = list.subList(0,middle);
        var right = list.subList(middle,list.size);

        return merge(mergeSort(left), mergeSort(right))
    }


    fun merge(left: List<Int>, right: List<Int>): List<Int>  {
        var indexLeft = 0
        var indexRight = 0
        var newList : MutableList<Int> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft] <= right[indexRight]) {
                newList.add(left[indexLeft])
                indexLeft++
            } else {
                newList.add(right[indexRight])
                indexRight++
            }
        }

        while (indexLeft < left.size) {
            newList.add(left[indexLeft])
            indexLeft++
        }

        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }

        return newList;
    }









    private fun binarySearchFromA(arrayA: IntArray, start: Int, end: Int, searchElement: Int,element:Int):Int {


        var localElement = element

//        if(start<=end){



        if(start > end){
            return -1
        }




            var mid = (start+end )/2


            if( searchElement == arrayA[mid] ){
                return mid
            }else if(arrayA[mid] > searchElement){

                return binarySearchFromA(arrayA,0,mid-1,searchElement,localElement)

            }else {
               return binarySearchFromA(arrayA,mid+1,end,searchElement,localElement)

            }

//        }

    }



    private fun checkDifferentElementUsingBinarySearch(
        arrayA: IntArray,
        arrayB: IntArray,
        start: Int,
        end: Int,
        index: Int
    ) {


        var localIndex = index;



        //1st iteration
        //1 2 "3" 4 5 6 -A
        //1 3 "4" 5 6  -B


        //2nd iteration
        //  "1" 2   -A
        //  "1" 3   -B

        if(start <= end){
            var mid = (start+end)/2

            if(arrayB[mid] == arrayA[mid]){

                //Element is greater than mid

                Log.i(TAG,"The element is   greater than mid ")

                var  startSmall = mid+1
                checkDifferentElementUsingBinarySearch(arrayA, arrayB, startSmall, end, localIndex)

            }else{
                //The element is either mid or less than that
                Log.i(TAG,"Element is mid or less than mid ")

                localIndex = mid

                var endSmall = mid -1
                checkDifferentElementUsingBinarySearch(arrayA, arrayB, start, endSmall, localIndex)
            }
        }else{

            Log.i(TAG,"Element is : "+arrayA[localIndex])
            Log.i(TAG,"index is : "+localIndex)

        }




    }

    private fun mergeSort(mergableArray: IntArray, start: Int, end: Int) {


        if(start < end){

            return
        }


        var middle = start+end/2
        mergeSort(mergableArray,start,middle)
        mergeSort(mergableArray,middle+1,end)

        merge(mergableArray,start,middle,end)



    }

    private fun merge(mergableArray: IntArray, start: Int, middle: Int, end: Int) {


        var left = middle - start +1
        var right = end - middle

        var leftArray = IntArray(left)
        var rightArray  = IntArray(right)

        for(i in 0 until left){
            leftArray[i] = mergableArray[start+i]
        }

        for(j in 0 until right){
            rightArray[j] = mergableArray[middle+1+j]
        }

        var i=0
        var j=0
        var k = start


        while(i < left && j < right){
            if(leftArray[i] <= rightArray[j]){
                mergableArray[k] = leftArray[i]
                i++
            }else{
                mergableArray[k] = rightArray[j]
                j++
            }
        }


        while( i < left){
            mergableArray[k++] = leftArray[i++]
        }

        while(j < right){
            mergableArray[k++] = rightArray[j++]
        }


    }

    private fun checkDifferentElement(
        arrayA: IntArray,
        arrayB: IntArray,
        size: Int

    ) {
        var index:Int = size
        var start = 0;
        var end = size-1

        while(start<=end){

            var mid = (start+end)/2

            if(arrayB[mid] == arrayA[mid]){

                //Element is greater than mid

                Log.i(TAG,"The element is   greater than mid ")

//                index = arrayA[mid]
                start = mid+1

            }else{
                //The element is either mid or less than that
                Log.i(TAG,"Element is mid or less than mid ")

//                index = arrayA[mid]

                index = mid

                end = mid -1


                Log.i(TAG,"changing index is :"+index)


            }
        }


        Log.i(TAG,"index is :"+index)
        Log.i(TAG,"Element is :"+arrayA[index])

    }

    private fun QueueExample() {


        var q: Queue<String> = PriorityQueue<String>()

        q.add("Ganesh")
        q.add("Ankit")
        q.add("Ankit")


        while(q.isNotEmpty()){
            Log.i(TAG,"Queue"+q.element())
            q.remove()
        }

//        var queue:Queue<String> = Queue<String>
    }

    // Array with no duplicate elements sorted

    private fun RemoveDuplicateSorting() {

        var array = arrayOf(1,2,3,4,4,5,2,3,1)

        var tempArray:IntArray = IntArray(array.size)

        array.sort()

        var j=0
        for(i in 0..array.size -2){
            if(array[i] != array[i+1]){
                tempArray[j++] = array[i]
            }
        }

        tempArray[j++] = array[array.size-1]

        for(i in 0..tempArray.size-1){
            Log.i(TAG,"Sorted"+ tempArray[i].toString())

        }


    }

    // Array with no duplicate elements  non-sorted
    private fun RemoveDuplicateArrayElements() {

        var array = arrayOf(1,2,3,4,4,5,2,3,1)

        var tempArray:IntArray = IntArray(array.size)

        var x=-1;

        for(i in 0..array.size-1){

            var isUnique = true

            for(j in (i+1)..array.size-1){

                if((array[i].equals(array[j])))
                {

                    isUnique = false
                }
            }


            if(isUnique){
                x++
                tempArray[x] = array[i]
            }

        }

        for(i in 0..tempArray.size-1){
            Log.i(TAG,"Not sorted"+ tempArray[i].toString())
        }





    }

    private fun stackExample() {
        s = Stack<String>()

        s.push("abc")
        s.push("Hello")

        Log.i(TAG,s.toString())


        s.pop()
        Log.i(TAG,s.toString())

    }

}

class MergeSortClass {
    fun mergeSortClass(mergableArray: IntArray, start: Int, end: Int) {
        if(start < end){

            var middle = (start+end)/2
            mergeSortClass(mergableArray,start,middle)
            mergeSortClass(mergableArray,middle+1,end)

            var mergArraylist = merge(mergableArray,start,middle,end)


            for(i in 0 until mergableArray.size)
                Log.i(TAG,"The element of mergesort ARRAY : "+mergArraylist[i])

        }





    }

    private fun merge(mergableArray: IntArray, start: Int, middle: Int, end: Int):IntArray {


        var left = middle - start +1
        var right = end - middle

        var leftArray = IntArray(left)
        var rightArray  = IntArray(right)

        for(i in 0 until left){
            leftArray[i] = mergableArray[start+i]
        }

        for(j in 0 until right){
            rightArray[j] = mergableArray[middle+1+j]
        }

        var i=0
        var j=0
        var k = start


        while(i < left && j < right){
            if(leftArray[i] <= rightArray[j]){
                mergableArray[k] = leftArray[i]
                i++
            }else{
                mergableArray[k] = rightArray[j]
                j++
            }
            k++
        }


        while( i < left){
            mergableArray[k++] = leftArray[i++]
        }

        while(j < right){
            mergableArray[k++] = rightArray[j++]
        }


        for(x in 0 until mergableArray.size){
            Log.i(TAG,mergableArray[x].toString())
        }

        return mergableArray

    }


}


public class MergeSort  {

    public fun sort(arr: Array<Long>): Array<Long> {
        sortArrayPiece(arr, 0, arr.size - 1);
        return arr;
    }

    /**
     * Sorts a piece of input array using recursive calls of itself
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    private fun sortArrayPiece(arr: Array<Long>, startIndex: Int, endIndex: Int){
        var pieceSize = endIndex - startIndex + 1;
        if (pieceSize == 1){
            return; //Single element piece case
        }
        var middleElementIndex = Math.floor((startIndex + endIndex) / 2.0).toInt();
        sortArrayPiece(arr, startIndex, middleElementIndex);
        sortArrayPiece(arr, middleElementIndex + 1, endIndex);
        merge(arr, startIndex, middleElementIndex, endIndex);
    }

    /**
     * Merges two subarrays of initial array arr: [startIndex; middleIndex] and [middleIndex + 1; endIndex]
     * in ascending order.
     * @param arr
     * @param startIndex
     * @param middleIndex
     * @param endIndex
     */
    private fun merge(arr: Array<Long>, startIndex: Int, middleIndex: Int, endIndex: Int){
        var leftArray = arr.copyOfRange(startIndex, middleIndex + 1); //Left bound is exclusive, right - inclusive
        var rightArray = arr.copyOfRange(middleIndex + 1, endIndex + 1);
        var i = 0;
        var j = 0;
        for (k in startIndex..endIndex){
            if ( (i <= leftArray.size - 1) && ( (j >= rightArray.size) || (leftArray[i] <= rightArray[j]) ) ){
                arr[k] = leftArray[i];
                i++;
            }else {
                arr[k] = rightArray[j];
                j++;
            }
        }
    }

}
