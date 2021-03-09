# 시간 복잡도 : O(n+k) -> n은 리스트의 길이, k 는 정수의 최대값

def CountingSort(A, B, k):
    Count = [0] * (k+1)
    for i in range(len(A)):
        Count[A[i]] += 1

    for i in range(1, len(Count)):
        Count[i] += Count[i-1]

    for i in range(len(A)-1, -1, -1):
        B[Count[A[i]]-1] = A[i]
        Count[A[i]] -= 1

    return B

A = [0, 4, 1, 3, 1, 2, 4, 1]
B = list(A)
print(CountingSort(A, B, 4))

# 2차원 배열