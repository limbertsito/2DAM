# Compila todos los .c de la carpeta:   make        Borra los ejecutables:   make clean
CFLAGS = -Wall -Wextra
PROGS  = $(patsubst %.c,%,$(wildcard *.c))

all: $(PROGS)

%: %.c
	gcc $(CFLAGS) -o $@ $<

clean:
	rm -f $(PROGS)

.PHONY: all clean
