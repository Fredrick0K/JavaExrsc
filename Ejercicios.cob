*> /*Ejercicio 1*/
*>  IDENTIFICATION DIVISION.
*>  PROGRAM-ID. MOSTRAR-DATOS.
*>  DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         77 edad PIC 9(02) VALUE 25.
*>         77 nombre PIC A(10) VALUE 'Juan'.
*>         01 importe PIC 99V9 VALUE 26.2.
*>  PROCEDURE DIVISION.
*>         DISPLAY nombre"TIENE "edad " "importe.
*> STOP RUN. 

*> EJERCIOCIO 2

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. SUMAR.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 NUM1 PIC 9(2) VALUE 10.
*>         01 NUM2 PIC 9(2) VALUE 5.
*>         01 RES PIC --- VALUE ZERO.
*> PROCEDURE DIVISION.
*>         ADD NUM1 NUM2 GIVING RES.
*>         DISPLAY "RESULTADO "RES.
*> STOP RUN.

*> EJERCICIO 3

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. VALIDADOR.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 EDAD PIC 9(3) VALUE 17.
*> PROCEDURE DIVISION.
*>         IF EDAD < 18 THEN
*>            DISPLAY "MENOR"
*>            ELSE
*>            DISPLAY "NO MENOR"
*>            END-IF.
*> STOP RUN.

*> EJERCICIO 4

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. CONTADOR.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 CONTADOR PIC 9(2) VALUE 1.
*> PROCEDURE DIVISION.
*>         PERFORM 10 TIMES
*>            ADD 1 TO CONTADOR
*>            DISPLAY CONTADOR 
*>            END-PERFORM
*> STOP RUN.

*> EJERCICIO 5

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. PARRAFO-VALIDADOR.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 EDAD PIC 9(3) VALUE 8.
*> PROCEDURE DIVISION.
*>     PERFORM VERIFICAR-EDAD.
*> STOP RUN.

*> VERIFICAR-EDAD.

*> IF EDAD < 18 THEN
*>   DISPLAY "MENOR"
*> ELSE 
*>   DISPLAY "NO MENOR"
*> END-IF
*> GOBACK.

*> EJERCICIO 6

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. SALARIO-Y-EDAD.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 EDAD PIC 9(3) VALUE 17.
*>         01 SALARIO PIC 9(4) VALUE 2500.
*> PROCEDURE DIVISION.
*>         IF EDAD > 18 AND SALARIO > 2000 THEN
*>            DISPLAY "Mayor de edad y salario alto"
*>            ELSE IF EDAD > 18 AND SALARIO < 2000
*>            DISPLAY "Mayor de edad y salario bajo"
*>            ELSE 
*>            DISPLAY "Menor de edad"
*>            END-IF.
*> STOP RUN.

*> EJERCICIO 7

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. DIAS-DE-LA-SEMANA.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 DIA PIC 9(1) VALUE 5.
*> PROCEDURE DIVISION.
*>         EVALUATE DIA
*>            WHEN 1
*>            DISPLAY "LUNES"
*>            WHEN 2
*>            DISPLAY "MARTES"
*>            WHEN 3
*>            DISPLAY "MIERCOLES"
*>            WHEN 4
*>            DISPLAY "JUEVES"
*>            WHEN 5
*>            DISPLAY "VIERNES"
*>            WHEN 6
*>            DISPLAY "SABADO"
*>            WHEN 7
*>            DISPLAY "DOMINGO"
*>            END-EVALUATE.
*> STOP RUN.

*> EJERCICIO 8

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. TABLA.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 NOMBRE PIC X(8) OCCURS 10 TIMES.
*>         01 I PIC 9(2).
*> PROCEDURE DIVISION.
*>   PERFORM VARYING I FROM 1 BY 1 UNTIL I > 10
*>         DISPLAY "NOMBRE " I 
*>   END-PERFORM.
*> STOP RUN.

*> EJERCICIO 9

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. LISTA.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 DATOS OCCURS 10 TIMES.
*>         05 NOMBRE PIC A(6) VALUE SPACES.
*>         05 EDAD PIC 9(2) VALUE ZERO.
          
*>         01 I PIC 9(2).
*> PROCEDURE DIVISION.
*>   PERFORM VARYING I FROM 1 BY 1 UNTIL I > 10
*>         DISPLAY "Name: " NOMBRE(I)
*>            DISPLAY "Age : " EDAD(I)
*>   END-PERFORM.
*> STOP RUN.

*> EJERCICIO 10

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. ESTADOS.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 ESTADO PIC X.
*>         88 ACTIVO VALUE "A".
*>         88 INACTIVO VALUE "I".
*>         88 BLOQUEADO VALUE "B".
*>         01 I PIC 9(2).
*> PROCEDURE DIVISION.
*> MOVE "B" TO ESTADO.
*>   EVALUATE ESTADO
*>         WHEN "A"
*>            DISPLAY "USUARIO ACTIVO"
*>            WHEN "I" 
*>            DISPLAY "USUARIO INACTIVO"
*>            WHEN "B"
*>            DISPLAY "USUARIO BLOQUADO"
*>            END-EVALUATE.
*> STOP RUN.

*> EJERCICIO 11

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. FORMAT-FECHA.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 FECHA .
*>         05 DIA PIC 9(2) VALUE 23.
*>         05 MES PIC 9(2) VALUE 2.
*>         05 ANYO PIC 9(4) VALUE 2022.
*>         01 I PIC 9(2).
*> PROCEDURE DIVISION.
*>   DISPLAY DIA "-" MES "-" ANYO.
*> STOP RUN.

*> EJERCICIO 12

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. STR-UNSTR.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 NOMBRE PIC A(10) VALUE "JUAN".
*>         01 APELLIDO PIC A(10) VALUE "DANES".
*>         01 NOMCOMPLETO PIC A(20).
        
*>         01 TEXTO PIC A(150) VALUE "The Saab 95 Aero SportCombi was Saabs flagship competing with the 5 series and E class; Hello peaky vro".
        
*>            01 TEXTO1 PIC A(80).
*>         01 TEXTO2 PIC A(70).
*> PROCEDURE DIVISION.

*>   STRING NOMBRE DELIMITED BY SPACE
*>    " " DELIMITED BY SIZE
*>    APELLIDO DELIMITED BY SIZE 
*>    INTO NOMCOMPLETO
*>   END-STRING.
*>   DISPLAY NOMCOMPLETO.
*>   UNSTRING TEXTO 
*>         DELIMITED BY ";"
*>            INTO TEXTO1, TEXTO2
*>   END-UNSTRING.
*>   DISPLAY TEXTO1.
*>   DISPLAY TEXTO2.
*> STOP RUN.

*> EJERCICIO 13

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. OPS-COMPUTE.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 B PIC 9(2) VALUE 10.
*>         01 H PIC 9(2) VALUE 5.
*>         01 RESULT PIC ---.
*> PROCEDURE DIVISION.
*>   COMPUTE RESULT = (b*h).
*>   DISPLAY "AREA -> " RESULT.
*>   COMPUTE RESULT = (b+h)*2.
*>   DISPLAY "PERIMETRO -> " RESULT.
*> STOP RUN.

*> EJERCICIO 14

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. VARIABLES-EDITADAS.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 NUM PIC -ZZ,ZZZ.99 VALUE -1564.22.
*> PROCEDURE DIVISION.
  
*>   MOVE 8751.22 TO NUM.
*>   DISPLAY NUM.
  
*> STOP RUN.

*> EJERCICIO 15

*> IDENTIFICATION DIVISION.
*> PROGRAM-ID. VARIABLES-EDITADAS.
*> DATA DIVISION.
*>     WORKING-STORAGE SECTION.
*>         01 I PIC 9(1).
*>         01 EMPLEADOS.
*>           05 EMPLEADO OCCURS 5 TIMES.
*>             10 EMP-NOM PIC A(20).
*>             10 EMP-EDA PIC 9(2).
*>             10 EMP-EST PIC X.
*>               88 ACTIVE VALUE "A".
*>               88 INACTIVE VALUE "I".
*>               88 BLOCKED VALUE "B".
*> PROCEDURE DIVISION.
  
*>   MOVE "BOB" TO EMP-NOM(1).
*>   MOVE 27 TO EMP-EDA(1).
*>   MOVE "A" TO EMP-EST(1).
  
*>   MOVE "TAILR" TO EMP-NOM(2).
*>   MOVE 44 TO EMP-EDA(2).
*>   MOVE "I" TO EMP-EST(2).
  
*>   PERFORM VARYING I FROM 1 BY 1 UNTIL I > 5
*>     IF EMP-NOM(I) NOT = SPACES
*>       DISPLAY "NAME -> " EMP-NOM(I)
*>       DISPLAY "AGE -> " EMP-EDA(I)
*>       DISPLAY "STATUS -> " EMP-EST(I)
*>       DISPLAY "-------------------"
*>     END-IF
*>   END-PERFORM.
  
*> STOP RUN.