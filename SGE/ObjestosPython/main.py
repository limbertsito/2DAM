# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


class StarWarsDroid:
    pass

c3po = StarWarsDroid()
r2d2 = StarWarsDroid()
bb8 = StarWarsDroid()

type(c3po)

class Droid:

    """Constructor"""
    def _init_(self,name):
        self.hidden_name=name

    @property
    def name(self):
        print("dentro del getter")
        return self.hidden_name
    @name.setter
    def name(self,name):
        print("dentro del setter")
        self.hidden_name=name


    def switch_on(self):
        print("Hola soy droide")
    def switch_off(self):
        print("adiossss")

k2so=Droid()
k2so.switch_on()
k2so.switch_off()

droid=Droid("BB-8")
print(droid.name)

"""Valores calculador"""
resp = 3*2
