
class vegetal:
    totalV= 0
    def __init__(self,n,tipo,terrestre,re):
        self.nombre= n
        self.tipo=tipo
        self.terrestre=terrestre
        self.reproduccion= re
        vegetal.totalV+=1
    def setVegetal(self):
        nombre_fichero= self.nombre+".txt"
        fichero= open(nombre_fichero,"w")
        fichero.write(self.nombre+"-"+self.tipo+"-"+str(self.terrestre)+"-"+self.reproduccion)
        fichero.close()

    def getVegetal(self):
        nombre_fichero= self.nombre+".txt"
        fichero= open(nombre_fichero,"r")
        datos=fichero.readline().strip().split("-")
        nombre= datos[0]
        tipo = datos[1]
        terrestre = datos[2]
        reproduccion = datos[3]
        print(f"{nombre =} {tipo=} {terrestre= } {reproduccion=}")
        fichero.close()

v1=vegetal("manzano","arbol",True,"angiosperma")
v1.setVegetal()
v1.getVegetal()
class arbol (vegetal):
    caduca = bool
    def __init__(self,n,t,te,re,ca):
        super().__init__(n,t,te,re)
        self.caduca= ca

    def setVegetal(self):

       super().setVegetal()
       nombre_fichero = self.nombre + ".txt"
       fichero = open(nombre_fichero, "a")
       fichero.write("-"+str(self.caduca))
       fichero.close() #Cerramos el fichero

    def __eq__(self, other):
        return self.caduca == other.caduca

a1=arbol("nogal","arbol",False,"angiosperma",True)
a2=arbol("naranjo","arbol",False,"angiosperma",False)

a1.setVegetal()
a1.getVegetal()

if(a1.__eq__(a2)):
    print("SOn iguales ambos caduda")
else:
    print("No son iguales")
#Si usamos la logica de la funcion magica se implementa en este caso seria a1 == a2