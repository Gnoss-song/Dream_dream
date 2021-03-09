# k번째로 작은 원소 찾기
def SelectionAlgorithm(list, k):
    for i in range(k):
        min_index = i
        for j in range(i+1,len(list)):
            if list[min_index] > list[j]:
                min_index = j
        list[i], list[min_index] = list[min_index], list[i]
    return list[k-1]

A = [5, 3, 2, 4 ,1 ,6]
print(SelectionAlgorithm(A, 3))

# 선택 정렬(SelectionSort) -> 교환 횟수가 버블, 삽입정렬보다 작다.
# 시간 복잡도 : O(n^2)
def SelectionSort(A):
    for i in range(len(A)-1):
        min_index = i
        for j in range(i+1, len(A)):
            if A[min_index] > A[j]:
                min_index = j
        A[i], A[min_index] = A[min_index], A[i]
    return A

print(SelectionSort(A))

# BruteForce