
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
       fichero.close()

a1=arbol("nogal","arbol",False,"angiosperma",True)
a1.setVegetal()
a1.getVegetal()
