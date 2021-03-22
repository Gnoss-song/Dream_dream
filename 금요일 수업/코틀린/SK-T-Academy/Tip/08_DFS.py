def DFS(n):
    stack = [0] * V
    visit = [0] * (V+1)
    top = -1

    top += 1
    stack[top] = n

    while top >= 0:
        cur = stack[top]
        top -= 1

        if visit[cur] == 0:
            print(cur, end=" ")
            visit[cur] = 1

        for i in range(1, V+1):
            if adj[cur][i] == 1 and visit[i] == 0:
                top += 1
                stack[top] = i

V, E = map(int, input().split()) # V : 정점의 갯수, E : 간선의 갯수
edge = list(map(int, input().split())) # 간성 정보
adj = [[0 for _ in range(V+1)] for _ in range(V+1)]

for i in range(E):
    n1, n2 = edge[2*i], edge[2*i+1]
    adj[n1][n2] = 1
    adj[n2][n1] = 1

DFS(1)

'''
7 8
1 2 1 3 2 4 2 5 4 6 5 6 6 7 3 7
'''