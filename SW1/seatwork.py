def convert(usd):
    indianRuppe = usd * ind
    british = usd * brt
    china = usd * chn

    return indianRuppe, british, china
ind = 82.74
brt = 0.73
chn = 6.45



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
            print(f"{usd:}\t\t{indianRuppe:.2f}\t\t\t{british:.2f}\t\t{china:.2f}")
        print()
main()
