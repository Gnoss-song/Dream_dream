package chap_4_2

class AnalyticsManager private constructor() {

    companion object Singleton {
        private var instance: AnalyticsManager? = null

        fun getAnalyticsManager(): AnalyticsManager =
                instance ?: synchronized(this) {
                    return@synchronized instance ?: AnalyticsManager().also {
                        instance = it
                    }
                }
    }
    fun firebaseSendEvent(analyticsEventName: String) = println(analyticsEventName)
}

fun main(){
    //val singletonManager =  AnalyticsManager.Singleton
    //val analyticsManager = singletonManager.getAnalyticsManager()

    //val analyticsManager = AnalyticsManager.Singleton.getAnalyticsManager()
    val analyticsManager = AnalyticsManager.getAnalyticsManager()

    analyticsManager.firebaseSendEvent("Login Time")
    analyticsManager.firebaseSendEvent("Shopping Time")
}