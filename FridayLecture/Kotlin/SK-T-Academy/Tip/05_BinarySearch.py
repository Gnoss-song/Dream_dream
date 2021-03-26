def BinarySearch(A, key):
    start = 0
    end = len(A) - 1
    while start <= end:
        middle = (start + end) // 2
        if A[middle] > key:
            end = middle - 1
        elif A[middle] < key:
            start = middle + 1
        else:
            return True
    return False

A = [1, 2, 3, 4, 5, 6]
print(BinarySearch(A, 2))

# SelectionAlgorithm