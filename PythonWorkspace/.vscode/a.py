def open_account():
    print("새로운 계좌가 생성되었습니다.")

def deposit(balance,money) :
    print("입금이 완료되었습니다. 잔액은 {0}입니다.".format(balance + money))
    return balance + money


def withdraw(balance,money):
    if balance>= money:
        print("출금이 완료되었습니다. 잔액은{0}입니다.".format(balance - money))
        return balance - money
    else :
        print("출금이 불가능합니다. 잔액은{0}입니다.".format(balance))
        return balance

def withdraw_night(balance,money):
    commision = 100
    return commision, balance - money - commision


balance = 0
balance = deposit(balance,1000)
# balance = withdraw(balance,500)
# balance = withdraw_night(balance,300)
commision,balance = withdraw_night(balance, 500)
print("수수료는 {0}원이며, 잔액은 {1}입니다.".format(commision,balance))

