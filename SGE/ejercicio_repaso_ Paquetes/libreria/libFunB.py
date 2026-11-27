    #funcion decoradora Simepre reciben una funcion
def cuantos(funcion):
        #con esto acojo a  todos los parametros
        #esta funcion engloba, dentro de esta llamaos a la funcion que ahy que decorar
        #
        def wrappper(*args,**kwargs):
            cadena1  = funcion(*args,**kwargs)
            contador = 0
            for char in cadena1:
                if char != "0":
                    contador +=1
            return contador
        return wrappper #sin parentesis

    #Funcion decorada
@cuantos  #con esto la decora y la relacionamos
def noCeros(cadena):
        #left y rigth , recibe como parametro si queemoa quitar algo en especifico
        return cadena.lstrip("0")

#Solo tengo que llamar a la funcion decorada
#print(noCeros("0000000000123124144120000gsffs"))

