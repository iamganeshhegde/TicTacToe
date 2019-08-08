package com.example.tictactoe.datastructure_questions

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_datastructure_questions.*

 class DataStructureQuestionsActivity : AppCompatActivity() {

//    var arrayOfInt = arrayOf(1, 2, 6, 5, 8, 9)
    var arrayOfInt = arrayOf(1, 5, 10, 6, 11)
    var number = 10;
    var TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_datastructure_questions)

        btnFindMin.setOnClickListener {
            checkTheNextBig()

//            tvCheckTextInt.text = 0
        }

        btnFinAladinMagic.setOnClickListener {
            findAlladinMagicDistance()
        }

        btnFindScatterPalindrome.setOnClickListener {
            findScatteredPalindrome()
        }

        btnFindMissingVal.setOnClickListener {
            findMissingNumber()
        }



    }

     private fun findMissingNumber() {

         //[-1,-1,6,1,9,3,2,-1,4,-1]  -- find missing number from 0-9
         //ans - [-1,1,2,3,4,-1,6,-1,-1,9]


         var inputArray = arrayOf(-1,-1,6,1,9,3,2,-1,4,-1)

         var answerArray = arrayOfNulls<Int>(10)


         var demoArray = IntArray(10){
             i -> -1
//             return@IntArray -1
         }

         for(i in 0 until answerArray.size){
             answerArray[i] = -1
         }


         for(i in 0 until inputArray.size){

             if(inputArray[i]> -1){

                 answerArray[inputArray[i]] = inputArray[i]
             }

         }




         for( i in 0 until answerArray.size){
             Log.i(TAG," answer array  " +answerArray[i].toString()+" "+demoArray[i])
         }

     }

     private fun findScatteredPalindrome() {

         //aabb --- a,aa,aab,aabb,a,ab,b,bb,b


         var stringToCheck = "aabb"
         var finalList = ArrayList<String>()
         var finalSet = HashSet<String>()


         for(i in 0 until stringToCheck.length){
             for( j in i until stringToCheck.length){
                 var subSetString = stringToCheck.substring(i,j+1)

                 finalList.add(subSetString)
                 finalSet.add(subSetString)
             }

         }


         for(i in 0 until finalList.size){

             Log.i(TAG,"Subset of strings List :"+finalList.get(i))
         }


         for(i in finalSet ){

             Log.i(TAG, "Subset of strings Set : $i")
         }
         Log.i(TAG,"Subset of strings Set :"+finalSet)



         /*  var iterator = finalSet.iterator()
           while (iterator.hasNext()){
               Log.i(TAG,"Subset of strings Set :"+ iterator.toString())


           }*/


     }

     private fun findAlladinMagicDistance() {
//                 [P,Q,R,S]           [P-Q,Q-R,R-S,S-P]
         //magic = [2,3,4,4],distance = [4,  2, 4,  3]
         /**
          * alladin starts from a place where he gets mentioned number of magic points. For every 1 magic points he can travel 1 km.
          * He can start from any point.
          * If he starts from P position then distance between point P and Q is 4 and distance[0]. So it is not possible. check next point.
          * If he starts from Q then he gets 3 magic points and distance between Q and R is 2. therefore it is possible.
          *  Start from the begining P and Check whether all paths can be traverse or not and find the magic power left at the end.
          *
          * Answer for above question is 0(magic points) at the end
          *
          */

         var arrayOfMagic = arrayOf(2,3,4,6)
         var arrayOfDistance = arrayOf(4,4,4,4)

         var startingPos = 0


         var totalMagicPowerAtTheEnd = -1


        labelLoop@ for(i in 0 until arrayOfMagic.size){

            totalMagicPowerAtTheEnd = arrayOfMagic[i]


            var j=i
            startingPos = i;


             while (j < i+arrayOfMagic.size){

                 var modValNext = (j+1)%arrayOfDistance.size
                 var modValCurrent = j%arrayOfDistance.size

                 if(totalMagicPowerAtTheEnd < arrayOfDistance[modValCurrent]){
                     totalMagicPowerAtTheEnd = -1
//                     initialMagic = arrayOfDistance[j+1]
                     break
                 }else{


                     var distanceCovered = arrayOfDistance[modValCurrent]
                     totalMagicPowerAtTheEnd = totalMagicPowerAtTheEnd - distanceCovered + arrayOfMagic[modValNext]

                     j++

                 }


             }


            Log.i(TAG,"Total Value of magic "+totalMagicPowerAtTheEnd)

            if(totalMagicPowerAtTheEnd >= 0){

                break@labelLoop
            }else{
                totalMagicPowerAtTheEnd = -1
            }


            Log.i(TAG,"Total Value of magic no value "+totalMagicPowerAtTheEnd)


        }

         if(totalMagicPowerAtTheEnd != -1){
             totalMagicPowerAtTheEnd = totalMagicPowerAtTheEnd - arrayOfMagic[startingPos]
         }

         Log.i(TAG,"Total Value of magic outside loop and start position  :"+totalMagicPowerAtTheEnd+" start: "+startingPos)













         //break only breaks inner loop
/*
         if we want to break out of outer loop too then we need to add return
         or add label to the loop we need to break
*/
     /**  var x=5

         loopi@for(i in 0..5){

             for(j in 0.. 5)
             {
                 if(i== 3){
                     Log.i(TAG,"break "+ i.toString())
                     break@loopi
                 }
                 Log.i(TAG,"for inside"+ i.toString())
             }
             Log.i(TAG,"for outside"+ i.toString())

         }*/



     }

     private fun checkTheNextBig() {


        var map = HashMap<Int, Int>()
        var finalAnswer = 0
        var answerNumber =  1

        for (i in 0 until arrayOfInt.size) {
            var value = arrayOfInt[i] - number
            map.put(arrayOfInt[i], value)

            Log.i(TAG, "" + map.get(arrayOfInt[i]) + " val: " + map.getValue(arrayOfInt[i]))
        }

        var i = 1
        var j = 0;

        while( map.containsValue(answerNumber) ){
            answerNumber++
        }

        finalAnswer = answerNumber+number

        Log.i(TAG, finalAnswer.toString())
        Log.i(TAG, map.keys.toIntArray()[2].toString())

    }

}