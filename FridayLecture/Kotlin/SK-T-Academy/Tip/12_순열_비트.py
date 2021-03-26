arr = [1, 2, 3]
N = len(arr)
sel = [0] * N

def perm(idx, check):
    if idx == N:
        print(sel)
        return

    for j in range(N):
        # j 번째 원소가 있으면 for 문으로 복귀
        if check & (1<<j): continue

        # idx 번째에 arr[j] 의 값을 넣고
        sel[idx] = arr[j]
        perm(idx+1, check | (1<<j)) # 원상 복귀 필요 X

perm(0, 0)