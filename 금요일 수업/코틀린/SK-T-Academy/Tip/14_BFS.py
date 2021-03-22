# BFS : 너비 우선 탐색

def BFS(start):
    queue.append(start)
    visited[start] = 1

    while queue:
        cur = queue.pop(0)
        print(cur)

        for i in range(1, 8):
            if adj[cur][i] == 1 and visited[i] == 0:
                queue.append(i)
                visited[i] = 1

queue = []
adj = [[0 for _ in range(8)] for _ in range(8)]
edge = [1, 2, 1, 3, 2, 4, 2, 5, 4, 6, 5, 6, 6, 7, 3, 7]

for i in range(0, len(edge), 2):
    adj[edge[i]][edge[i+1]] = 1
    adj[edge[i+1]][edge[i]] = 1

visited = [0] * 8

BFS(5)