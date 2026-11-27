
def verdad(*args):
    flag= True
    for elemento in args:
        if(elemento == False):
            flag==False
            break

    return [flag, len(args)]

#lambca que recibe 3 yb devulve la multipicacion d eellos
producto = lambda a,b,c: a* b * c

#Lambda recibe dos cadenas y devulve la oncatencaiopn x2
doble = lambda cad1,cad2 : (cad1+cad2)*2

##probamos
#print(verdad(True,True,False,True))

#print(producto(6,9,8))