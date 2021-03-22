def Partition(a, begin, end):
    pivot = (begin + end) // 2
    L = begin
    R = end
    while L < R:
        while(a[L]<a[pivot] and L<R) : L += 1
        while(a[R]>=a[pivot] and L<R) : R -= 1
        if L<R:
            if L == pivot : pivot = R
            a[L], a[R] = a[R], a[L]
    a[pivot], a[R] = a[R], a[pivot]
    return R

def QuickSort(a, begin, end):
    if begin < end:
        p = Partition(a, begin, end)
        QuickSort(a, begin, p-1)
        QuickSort(a, p+1, end)
    return a

a = [69, 10, 30, 2, 16, 8, 31, 22]
begin = 0
end = 7
print(QuickSort(a, begin, end))