def Snail(N):
    arr = [[0]*N for _ in range(N)]
    dr = [0, 1, 0, -1]
    dc = [1, 0, -1, 0]
    mode = 0

    r = 0
    c = 0
    arr[r][c] = 1
    for i in range(2, N**2+1):
        r += dr[mode]
        c += dc[mode]
        arr[r][c] = i
        if 0 <= r+dr[mode] < N and 0 <= c+dc[mode] < N and not arr[r+dr[mode]][c+dc[mode]]:
            continue
        elif mode != 3:
            mode +=1
        else: mode = 0
    return arr

snail = Snail(3)
for i in range(len(snail)):
    for j in range(len(snail[0])):
        print(snail[i][j], end=" ")
    print()

# 비트연산자