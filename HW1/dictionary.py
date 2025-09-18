mydict = {}
matrix = int(input("Matrix size: "))
for x in range(matrix):
    val = input(f"Shopping Item {x+1}: ")
    mydict[x] = val
while True:
    print("What would you like to do: [C]hange item [R]emove [D]isplay [S]earch")
    choice = input("-") 
    if choice == '*':
        break
    if choice.lower() == "d":
        print("Displaying Values")
        print("Key   Value")
        for x, y in mydict.items():
            print(f"{x}     {y}")
    elif choice.lower() == "s":
        search = int(input("Enter key to search: "))
        if search in mydict:
            print(f"Found {mydict[search]} item")
        else:
            print("I'm sorry, not in the cart")
    elif choice.lower() == "r":
        search = int(input("Enter key to remove: "))
        if search in mydict:
            removeVal = mydict.pop(search)
            print(f"The key {search} with value {removeVal} has been deleted")
        else:
            print("Key not found")
    elif choice.lower() == "c":
        search = int(input("Enter key to change: "))
        if search in mydict:
            print(f"Found {mydict[search]} item")
            change = input("Enter new value: ")
            mydict[search] = change
        else:
            print("I'm sorry, not in the cart")