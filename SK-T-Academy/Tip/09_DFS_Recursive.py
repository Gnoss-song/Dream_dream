def DFS_Recursive(n):
    print(n, end=" ")
    visit[n] = 1
    for i in range(1, V+1):
        if adj[n][i] == 1 and visit[i] == 0:
            DFS_Recursive(i)


V, E = map(int, input().split())
edge = list(map(int, input().split()))
adj = [[0 for _ in range(V+1)] for _ in range(V+1)]

for i in range(E):
    n1, n2 = edge[2*i], edge[2*i+1]
    adj[n1][n2] = 1
    adj[n2][n1] = 1

visit = [0] * (V+1)
DFS_Recursive(1)

'''
7 8
1 2 1 3 2 4 2 5 4 6 5 6 6 7 3 7
'''