from libreria.libFunA import producto,doble
from libreria import libFunB as libB

def llamaFuncion(primero,/,*,segundo=2):
    try:
        assert segundo != 0 #tiene que dar lo bueno

        match primero:
            ##caundo trabamos con numero de variable sytipos podemos
            #poner nombres a las variabels para trabajr con ellos
            case [str(x),str(y)]:
                return doble(x,y)
            case [int(x),int(y),int(z)]:
                return producto(x,y,z)
            case [str(x)]:
                return libB.noCeros(x)
            case _:
                return "Error : No tienen un formato válido"
    except TypeError:
        print("Error: tipo de dato no valido")
    except AssertionError:
        print("Error: El segundo parametro tiene que ser diferente de 0")
    except Exception:
        print("Error : Se ha producido un error general")
    else:
        print("Ejecutado sin excepciones")

print(llamaFuncion([10,20,30],segundo= 0))
print(llamaFuncion(["003403403"],segundo=1))
print(llamaFuncion([6,3,5],segundo=8))
print(llamaFuncion([12,"cinco"],segundo=3))
print(llamaFuncion(["Arriba","Abajo"]))
