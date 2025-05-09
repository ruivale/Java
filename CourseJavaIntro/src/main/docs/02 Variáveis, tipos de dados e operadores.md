
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


---
---
#### Operadores aritméticos, relacionais, lógicos e bit a bit


---
---
#### Introdução às Strings Java (métodos básicos)


