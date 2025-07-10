
### 📖 2. Variáveis, tipos de dados e operadores
1. [Tipos de dados primitivos (int, double, char, boolean, etc.)](#tipos-de-dados-primitivos-int-double-char-boolean-etc)
2. [Variáveis e constantes](#variáveis-e-constantes)
3. [Conversão de tipos ("casting" e "promotion")](#conversão-de-tipos-casting-e-promotion)
4. [Operadores aritméticos, relacionais, lógicos e bit a bit](#operadores-aritméticos-relacionais-lógicos-e-bit-a-bit)
5. [Introdução às Strings Java (métodos básicos)](#introdução-às-strings-java-métodos-básicos)
    

---
---
### 📖 **Variáveis, tipos de dados e operadores**  

#### Tipos de dados primitivos (int, double, char, boolean, etc.)  
| Tipo       | Tamanho (bits) | Intervalo de Valores                                         |
|------------|----------------|---------------------------------------------------------------|
| `byte`     | 8              | -128 a 127                                                    |
| `short`    | 16             | -32.768 a 32.767                                              |
| `int`      | 32             | -2.147.483.648 a 2.147.483.647                                |
| `long`     | 64             | -9.223.372.036.854.775.808 a 9.223.372.036.854.775.807        |
| `float`    | 32             | ±3,4 × 10³⁸ (aproximadamente), ~7 dígitos decimais           |
| `double`   | 64             | ±1,8 × 10³⁰⁸ (aproximadamente), ~15 dígitos decimais         |
| `char`     | 16             | 0 a 65.535 (valores Unicode)                                 |
| `boolean`  | 1 (teórico)\*  | `true` ou `false`                                             |

\* `boolean` não tem tamanho fixo definido (depende da implementação da JVM)

Notas:  
• ***byte, short, char, int*** e **long** são conhecidos como tipos integrais, pois têm valores inteiros (números inteiros, positivos ou negativos). Por exemplo, -8, 17 e 625 são todos números inteiros.  

• **char** é utilizado para caracteres, por exemplo ‘a’, ‘b’, ‘?’ ou ‘+’ (note-se as plicas). No código, *char c = 'a';* significa que a variável c representa a letra a. Como os computadores armazenam todos os caracteres (no teclado) como números (binários), precisamos de um sistema de codificação para mapear os caracteres em números e vice-versa. O Java usa o Padrão de codificação Unicode, que garante um número único para cada caractere, independentemente plataforma, idioma, etc. e é por isso que o **char** usa dois *bytes* em vez de um. De facto, na perspectiva do computador, *char c = 'a';* é o mesmo que *char c = 97;* onde 97 é o valor decimal para ‘a’ em Unicode. Obviamente, nós, humanos, preferimos a representação de letras.  

• **short** e **char** requerem dois bytes, mas têm intervalos diferentes. Note-se que **short** pode representar números negativos, enquanto o **char** não consegue. Em contraste, o **char** pode armazenar números como 65.000, enquanto que **short** não pode.  

• **float** e **double** são para números de vírgula flutuante. Por outras palavras, números que têm casas decimais, como 23,78 ou -98,453. Estes números de vírgula flutuante podem usar notação científica como por exemplo o 130000,0 que pode ser expresso como *double d1=1,3e+5;* e 0,13 pode ser expresso como *double d2=1,3e-1;*.

---
---
#### Variáveis e constantes
A representação dos vários tipos de variáveis pode ser expressa da seguinte forma:  
• **Decimal**: Base 10; números 0..9. Este é o padrão;  
• **Hexadecimal**: Base 16; números 0..9 e letras a..f (ou A..F). Usa-se o prefixo **0x** ou **0X** para indicar que este é um literal hexadecimal;  
• **Binário**: Base 2; números 0..1. Usa-se o prefixo **0b** ou **0B** para indicar que este é um literal binário.  

Alguns fragmentos que exemplificam a representação:  
```java
// representing the value 30 (decimal) in decimal, hexadecimal and binary

// decimal
int dec = 30;

// hexadecimal 
int hex = 0x1E;    // ((1 x 16^1) + (14 (E in hexa) * 16^0) = 16 + 14 = 30 

// bynary
int bin = 0b11110; // 16 + 8 + 4 + 2 = 30
```

Um número literal, como o 22, é considerado um **int** por omissão. Se se quiser que 22 seja tratado como **long** (em vez de **int**), deve adicionar um **L** maiúsculo ou minúsculo.  
Exemplo:
```java
int x = 10;
long n = 10L; // uppercase L is preferred, as the lowercase l is similar to the number 1 (one).
```

Os números de vírgula flutuante comportam-se de forma semelhante. Um decimal é, por omissão, um **double**. Para ter qualquer número decimal tratado como **float** (em vez de **double**), deve-se adicionar um **F** maiúsculo ou minúsculo. Assumindo que o intervalo dos valores a atribuir à variável não é um problema, um motivo para usar o **float** em vez do **double** é a poupança no uso de memória uma vez que o **float** requer 4 *bytes* enquanto que o **double** requer 8 *bytes*.  
Exemplo:
```java
double d = 10,34;
float f = 10,34F;
```

Exemplos de **byte**, **short**, **char** e **boolean**:
```java
byte bytee = 127;         // setting it to the MAX value a byte can handle [-127..128]
short shortee = 32.767;   // setting it to the MAX value a short can handle [-32.768..32.767]
char c = 'a';
boolean b1 = true;
boolean b2 = false;
```

---
---
#### Conversão de tipos ("casting" e "promotion")
Os diferentes tipos primitivos podem ser ordenados pela seguinte ordem the grandeza:  
*byte < short < int < long < float < double*

*Casting* é uma conversão forçada de um tipo para outro e que pode ser explícita ou implícita.
*Widening cast* (implícito) ocorre quando se converte de um tipo para um tipo *maior*:
```java
int i = 42;
double d = i; // ok, int -> double
```

*Narrowing cast* (explícito) ocorre quando se converte de um tipo para um tipo *menor* com possível perda de dados:
```java
double d = 3.14;
int i = (int) d; // result: 3 (decimal part gone)
```

No "narrowing cast" existem potenciais problemas que devem ser antecipados. Por exemplo, na seguinte atribuição:
```java
int big = 130;
byte small = (byte) big; // result: -126 (overflow)
```
Como o valor 130 não *encaixa* no intervalo de valores que um *byte* pode conter, ocorre um *overflow* o que faz com que o variável resultante, a *small* não contenha o valor correcto. 


*Promotion* é uma conversão automática de um tipo *menor* para um tipo *maior*. Por exemplo:
```java
int x = 10;
long y = x; // auto promotion from int to long
```

O Java *promove* automaticamente tipos em expressões aritméticas:
```java
byte b1 = 10;
byte b2 = 20;
// b1 + b2 becomes an int. If we want a byte, we should *cast it*
byte result = (byte)(b1 + b2);
```

Existe a possibilidade do uso de *cast* entre objectos. Tomemos as classes Plane e as, mais especificas, Airbus e Boeing e estas Airbus e Boeing são *filhas* da classe Plane (*uma classe ser filha de outra é um assunto que nos debruçaremos noutra sessão*). Basicamente, podemos visualizar o conceito como classes genéricas e classes específicas e que estas últimas estão *ligadas* às primeiras.

Podemos proceder ao *upcasting* de uma classe *filha* para a sua classe *mãe*:
```java
Airbus airbus = new Airbus();
Boeing boeing = new Boeing();
Plane planeAirbus = airbus; // auto upcasting
Plane planeBoeing = boeing; // auto upcasting
```

Podemos proceder ao *downcasting* de uma classe *mãe* para a sua classe *filha* mas precisamos de usar um *cast* explícito:
```java
Plane planeAirbus = new Airbus();
Plane planeBoeing = new Boeing();
Airbus airbus = (Airbus)planeAirbus; // explicit cast 
Boeing boeing = (Boeing)planeBoeing; // explicit cast 
```

Nesta converção temos de garantir que os objectos *planeAirbus* e *planeBoeing* são mesmo do tipo Airbus e Boeing senão a conversão pode causar uma excepção (*ClassCastException*). Adiantando o tema que será apresentado noutra sessão, esta condição pode ser testada fazendo uso do *instanceof* do Java.



---
---
#### Operadores aritméticos, relacionais, lógicos e bit a bit
##### Os operadores aritméticos são usados para operações com tipos numéricos: 
- Adição (+): 3 + 2 = 5
- Subtracção (-): 5 - 1 = 4
- Multiplicação (*): 3 * 3 = 9
- Divisão (/): 9 / 2 = 4 (*int*)
- Módulo/Resto (%): 7 % 2 = 1 (resto da divisão)

Nota:
- *int* / *int* resulta num *int* sem casas decimais
- double result = 7.0 / 2; // resulta em 3.5, i.e., com casas decimais


##### Os operadores relacionais são usados para comparar valores:
- **Igual a (==)**: 2 == 2 -> true
- **Diferente de (!=)**: 5 != 8 -> true
- **Maior que (>)**: 5 > 3 -> true
- **Menor que (<)**: 3 < 5 -> true
- **Maior ou igual a (>=)**: 6 >= 6 -> true
- **Menor ou igual a (<=)**: 6 <= 6 -> true


##### Os operadores lógicos são usados com valores *boolean* (true / false):
- **'E' lógico (&&)**: se ambas as condições forem *true* -> true; false nas outras situações
- **'Ou' lógico (||)**: se ambas as condições forem *false* -> false; true em todas as outras situações
- **'Não' lógico (!)**: inverte um valor *boolean* !true -> false


##### Os operadores bit a bit manipulam bits directamente e são usados em contextos de baixo nível:
- **'E' bit a bit (&)**: 1&1 = 1; 1&0 = 0; 0&0 = 0 (5 & 3 = 0101 & 0011 = 0001 = 1)
- **'Ou' bit a bit (|)**: 1|1 = 1; 1|0 = 1; 0|0 = 0; (5 | 3 = 0101 | 0011 = 0111 = 7)
- **'XOr' bit a bit (^)**: 1^1 = 0; 1^0 = 1 (5 ^ 3 = 0101 ^ 0011 = 0110 = 6)
- **'Not' bit a bit (~)**: inverte todos os bits (~5 = ~00000101 = 11111010 = -6)
- **Shift à esquerda (<<)**: desloca bits à esquerda (*2) (5 << 1 = 0101 << 1 = 1010 = 10)
- **Shift à direita (>>)**: desloca bits à direita (/2) (5 >> 1 = 0101 >> 1 = 0010 = 2)
- **Shift lógico à direita (>>>)**: preenche com zeros à esquerda (só para *int* e *long* positivos)



📤 Nota: como converter um número negativo, em complemento de dois, para decimal?  
Se o bit mais à esquerda (mais significativo) for 0, é positivo. Se for 1, é negativo e para descobrir o seu valor usamos as seguintes operações:
- invertem-se os bits;  
- soma-se um (1);
- o valor obtido é o módulo, ou seja, o número positivo;  
- adicione o sinal negativo


Exemplo para 11111010:  
- bit mais à esquerda é um (1) logo é negativo;
- invertem-se os bits: 11111010 -> **00000101** = **5**;
- soma-se um (1): 5 + 1 = **6**;  
- adicionando o sinal negativo temos: **-6**


---
---
#### Introdução às Strings Java (métodos básicos)
***String*** é uma classe que representa uma sequência de caracteres. As *String*s em Java são conhecidas por serem imutáveis, i.e., não é possível alterar uma *String* depois de ser criada.

```java
String strName = "Maria";
```

##### Métodos básicos:
- *.length()*: retorna o número de caracteres;
- *.charAt(int index)*: retorna o caractere na posição *index*; 
- *.toUpperCase() / .toLowerCase()*: converte para maiúsculas ou minúsculas;
- *.equals(String str) / .equalsIgnoreCase(String str)*: compara as duas *String*s  
- *.substring(int beginIndex) / .substring(int beginIndex, int endIndex)*: extrai parte da *String*;
- *.contains(CharSequence s)*: verifica se contém a sequência dada; 
- *.startsWith(String str) / .endsWith(String str)*: verifica se a *String* começa ou termina com os parâmetros dados; 
- *.indexOf(String str) / .lastIndexOf(String str)*: retorna a primeira e última ocorrência de uma sub*String*;
- *.replace(CharSequence old, CharSequence new)*: substitui partes de uma *String*; 
- *.trim()*: remove espaços do início e do fim da *String*.  
