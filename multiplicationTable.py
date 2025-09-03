while True:
    col = int(input("Enter a column: "))
    row = int(input("Enter a row: "))
    search = int(input("Enter number to search: "))
    
    if col <= 0 or row <= 0:
        print("INVALID!!")
        break
    
    for x in range(1, col + 1 ):
        for y in range(1, row + 1):
            product = x * y
            if product == search:
                print(f"[{product}]", end=" ")
            else:
                print(f"{product}", end=" ")
        print()
