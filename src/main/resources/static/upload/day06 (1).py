def multiple(n) :
    if n == 0 or n ==1 :
        return 1
    else :
        return n * multiple(n-1)

number = int(input('정수를 입력하세요 : '))
fac = multiple(number)
print(fac)


dataArray = [ 32 , 15 , 10 , 18 , 33 , 42 , 21 ]
class TreeNode :
    def __init__(self):
        self.left = None
        self.data = None
        self.right = None

rootNode = TreeNode()
rootNode.data = dataArray[0]

for num in dataArray[1:] :
    node = TreeNode()
    node.data = num
    current = rootNode
    while True :
        if num < current.data :
            if current.left == None:
                current.left = node
                break
            current= current.left
        elif num > current.data :
            if current.right == None :
                current.right = node
                break
            current = current.right
        else :
            break




