# 시간 복잡도 : O(MN)
def BruteForce(string, key):
    M = len(string)
    N = len(key)
    i = 0
    j = 0
    while i < M and j < N:
        if string[i] != key[j]:
            i = i - j
            j = -1
        i += 1
        j += 1
    if j == N: return i - N
    else: return -1

string = 'This is a book'
key = "bo"
print(BruteForce(string, key))