
def perm(idx):
    if idx == N:
        print(arr)
    else:
        for i in range(idx, N):
            arr[idx], arr[i] = arr[i], arr[idx]
            perm(idx+1)
            arr[idx], arr[i] = arr[i], arr[idx]

arr = [1, 2, 3]
N = len(arr)
perm(0)