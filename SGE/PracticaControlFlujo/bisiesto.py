year = input("Introduce el año:")
if (year is None ) :
    print("ADVERTENCIA : EL valor introducido es nulo")
else :
    year = int(year)
    if ((year % 4 == 0 and year % 100 != 0) or year % 400 == 0):
        print("El año es bisiesto")
    else:
        print("No es un año bisiesto")