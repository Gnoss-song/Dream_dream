package kr.co.softcampus.playground

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-04-27
 * @desc
 */
class ItemModel {

    var items: ArrayList<ItemEntity?> = ArrayList()
    var selectAllState:Boolean=false

    fun toggleAllItemClick() { //전체 선택 메서드
//이해를 돕기 위해 메인 루프에서 진행하지만 베스트는 -> 스래드 처리
        for(item in items){
            item?.let {
                it.isChecked=selectAllState.not() //상태값 반전
            }
        }
        selectAllState= selectAllState.not() //모델의 상태값 반전
    }

    fun toggleEachItemClick(pos: Int) { // 개개인의 선택 메서드
        items[pos]?.let {
            it.isChecked = it.isChecked.not() //상태값 반전
        }
    }

    fun makeTestItems(){ //테스트 아이템 만들기 메서드
        items.clear()
        for(i in 0 until 100){
            var item =ItemEntity()
            item.title= "$i 번째 아이템 "
            item.contents="내용부"
            items.add(item)
        }
    }


    inner class ItemEntity {
        var isChecked: Boolean = false
        var title: String? = null
        var contents: String? = null
    }
}