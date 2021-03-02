absent =[2,5]
no_book = [7]
for student in range(1,11):
    if student in absent:
        continue
    elif student in no_book:
        print("{0}, 수업 여기까지야.".format(student))
        break
    print("{0}, 책 읽어봐".format (student))
