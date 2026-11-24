import psycopg2

conexion = None
cursor1 = None
conexion = psycopg2.connect(
    host="localhost",
    port=5433,
    database="dvdrental",
    user="odoo",
    password="odoo",
)
cursor1 = conexion.cursor()
identificadores = []
titulos = []
sql = "SELECT film_id,title FROM film where length between 100 and 120"
cursor1.execute(sql)
datosFichero = open("peliculas.txt", "w")
for registro in cursor1.fetchmany(5):
    #for registro in cursor1.fetchmany(5):
    identificadores.append(registro[0])
    titulos.append(registro[1])
    datosFichero.write(f'')
identificadores.sort(reverse=True)
titulos.sort(reverse=True)
identificadores = tuple(identificadores)
titulos = tuple(titulos)
for i in range(5):
    print(f'{float(identificadores[i]):06.2f}')
    print(f'{str(titulos[i]):>20s}')
    datosFichero.write(str(identificadores[i]) +" : "+ titulos[i]+"\n")
