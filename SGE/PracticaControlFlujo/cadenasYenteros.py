numero_hijos = input("Introduce el número de hijos")
año_nacimiento = input("Introce el año de nacimiento")

respuesta = input("Quieres convertir el número de hijos de cadena a entero?")
if(respuesta.lower() == "si"):
        numero_hijos = int(numero_hijos)

respuesta2= input("Quieres convertir el año de nacimiento de tus hijos  a cadena de text?")
if(respuesta2.lower() == "si"):
        año_nacimiento = int(año_nacimiento)

print(type(numero_hijos))
print(type(año_nacimiento))

parametros = [numero_hijos,año_nacimiento]
flag = False
match parametros:
        case (str(numero_hijos),str(año_nacimiento)):
                flag = True
        case (str(numero_hijos),int(año_nacimiento)):
                flag = True
        case (int(numero_hijos),str(año_nacimiento)):
                flag = True
        case(int(numero_hijos),int(año_nacimiento)):
                flag = True

if(flag):
        numero_hijos= int(numero_hijos)
        año_nacimiento= int(año_nacimiento)
if(int(numero_hijos) and int(año_nacimiento)):
        print(f"El resultado de multiplicar {numero_hijos} * {año_nacimiento} es {numero_hijos*año_nacimiento}")


