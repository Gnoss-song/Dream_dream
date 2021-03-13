//매우중요함 여러번 반복하기.
import kotlin.random.Random;

fun main(args:Array<String>){
        val awesomePoint = Random.nextInt(0, 30000)
        println("Awesome 포인트점수는 $awesomePoint 입니다")

        var awesomeRating = ""

        //전통적인 if
        if(awesomePoint >= 1000 && awesomePoint <= 9999){ //1000~9999점은 Bronze 등급
            awesomeRating = "Bronze"
        }else if(awesomePoint > 9999 && awesomePoint <= 19999){ //9999~19999점은 Silver 등급
            awesomeRating = "Silver"
        }else if(awesomePoint >= 20000){//20000점 이상은 Gold 등급
            awesomeRating = "Gold"
        }else{
            awesomeRating = "Brass"     //0~999점은 Brass 등급
        }
        println("당신은 $awesomeRating 등급입니다")

        /*
         * 코틀린은 if가 식이므로 다음이 가능하다
         */
        awesomeRating = if(awesomePoint >= 1000 && awesomePoint <= 9999) //1000~9999점은 Bronze 등급
                           "Bronze"
                       else if(awesomePoint > 9999 && awesomePoint <= 19999) //9999~19999점은 Silver 등급
                           "Silver"
                       else if(awesomePoint >= 20000)//20000점 이상은 Gold 등급
                           "Gold"
                       else
                           "Brass"     //0~999점은 Brass 등급

        println("당신은 $awesomeRating 등급입니다")
}