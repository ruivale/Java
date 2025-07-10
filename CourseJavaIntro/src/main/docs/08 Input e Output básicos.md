
### 📖 8. "Input" e "Output" básicos
1. [Utilização da classe Scanner para "input"](#)
2. ["Output" básico com System.out.println](#)
3. [Formatando "output"](#)
    

##### Recapitulando os métodos básicos:
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


As *String*s ***não mudam***! 
```java
String strName = "Maria";
strName.toLowerCase(); // strName is still "Maria" but it's method invocation returns a new *String*
System.out.println(strName); // strName is still "Maria"

strName = strName.toLowerCase(); // strName is now "maria"
System.out.println(strName); // "maria"
```

A chamada ao método *.toLowerCase()* retorna uma *String* nova mas não modifica a original.

#### As *String*s são imutáveis porque:
- segurança: evitam que sejam alteradas sem controlo;
- performance e *caching*: uso de um *String pool* ou cache de *String*s permite o seu reaproveitamento e como são imutáveis, é seguro fazê-lo;
- *Thread safety**: como não podem ser modificadas, podem ser acedidas por várias *entidades* sem que haja a necessidade de haver controlo no seu acesso. 


(*) - debruçar-nos-emos sobre ***Thread***s nos últimos capítulos da formação

