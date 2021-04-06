arr = [[1,2,3],[4,5,6],[7,8,9]]

# 행 우선 순회
for i in range(len(arr)):
    for j in range(len(arr[0])):
        print(arr[i][j], end=" ")
    print()
print("------")

# 열 우선 순회
for j in range(len(arr[0])):
    for i in range(len(arr)):
        print(arr[i][j], end=" ")
    print()
print("------")

# 지그재그 순회
for i in range(len(arr)):
    for j in range(len(arr[0])):
        print(arr[i][j + (len(arr[0])-1-2*j)*(i%2)], end=" ")
    print()
print("------")

# 전치 행렬
for i in range(len(arr)):
    for j in range(len(arr[0])):
        if i < j:
            arr[i][j], arr[j][i] = arr[j][i], arr[i][j]
for i in range(len(arr)):
    for j in range(len(arr[0])):
        print(arr[i][j], end=" ")
    print()