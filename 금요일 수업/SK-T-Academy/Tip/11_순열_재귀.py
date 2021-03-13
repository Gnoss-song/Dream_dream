def perm(idx):
    if idx == N:
        print(sel)
    else:
        for i in range(N):
            if check[i] == 0:
                sel[idx] = arr[i]
                check[i] = 1
                perm(idx+1)
                check[i] = 0

arr = [1, 2, 3, 4]
N = len(arr)
sel = [0] * N
check = [0] * N
cnt = 0
perm(0)