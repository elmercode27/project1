def convert(usd):
    indianRuppe = usd * 82.74
    british = usd * 0.73
    china = usd * 6.45
    
    return indianRuppe, british, china 




def main():
    while True:
        dollars = input("Enter dollar ($) (*to exit): ")
    
        if dollars == "*":
            print("Bye")
            break
        
        parts = dollars.split("@")
        dollars = []
        for x in parts:
            x = x.strip()
            if x.replace(".", "", 1).isdigit():
                dollars.append(float(x))
        print("\nDollar ($)\tIndian Rupee (R)\tBritish (Pound)\tChina (Y)")
        for usd in dollars:
            indianRuppe, british, china = convert(usd)
            print(f"{usd:.2f}\t\t{indianRuppe:.2f}\t\t\t{british:.2f}\t\t\t{china:.2f}")
        print()
main()
