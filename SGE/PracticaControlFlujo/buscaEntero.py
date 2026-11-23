
texto=input("introduce texto")
encontrado=False
while encontrado ==False :
    for i in range(len(texto)):
        if(texto[i].isnumeric()):
            print(f" se ha encontrado un entero {texto[i]}")
            encontrado = True
            break
if (encontrado==False):
    print("No se ha encontrado ningun valor numerico")