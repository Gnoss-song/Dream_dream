# 시간 복잡도 : O(n^2)

def BubbleSort(n):
    for i in range(len(n)-1, 0, -1):
        for j in range(i):
            if n[j] > n[j+1]:
                n[j], n[j+1] = n[j+1], n[j]
    return n

n = [55, 7, 78, 12, 42]
print(BubbleSort(n))

# CountingSort