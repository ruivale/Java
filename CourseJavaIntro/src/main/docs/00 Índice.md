
# Formação básica Java (draft)


<a href="file:///index.html">file://index.html  a href</a>  
<a href="file:///./index.html">file://./index.html a href</a>  
[index.html](file:///../../../target/reports/apidocs/index.html)  
[index.html in browser](../../../target/reports/apidocs/index.html)  
[Google](https://www.google.com)  
See the [../../../target/reports/apidocs/index.html](../../../target/reports/apidocs/index.html) for setup instructions.
See the [C://Temp//index.html](C://Temp//index.html) for setup instructions.




___
___
## 📖 Índice
### 📖 1. [Introdução à Programação e Java](#📖-introdução-à-programação-e-java)
     O que é a programação?
     Visão geral do Java e das suas características (independência de plataforma (WORA), POO, etc.)
     Configurar o ambiente de desenvolvimento Java (JDK e IDEs)
     Escrever, compilar e executar um programa Java (o típico HelloWorld)
     Sintaxe básica e estrutura de um programa Java

### 📖 2. [Variáveis, tipos de dados e operadores]
     Tipos de dados primitivos (int, double, char, boolean, etc.)
     Variáveis e constantes
     Conversão de tipos ("casting" e "promotion")
     Operadores aritméticos, relacionais, lógicos e bit a bit
     Introdução às Strings Java (métodos básicos)

### 📖 3. Controlo de fluxo
     Tomada de decisão: if, else, if else, switch, etc.
     Ciclos: for, while, do-while

### 📖 4. Funções (Métodos)
     Declarar e chamar métodos
     Parâmetros e valores de retorno
     "Overloading" de método
     Compreender o método main()
     Âmbito das variáveis

### 📖 5. Noções básicas de programação orientada a objectos (POO)
     Classes e objetos
     Campos e métodos
     Construtores
     A palavra-chave "this"
     Modificadores de acesso (público, privado, protegido)
     Introdução ao encapsulamento
     equals(), hashCode() e clone()

### 📖 6. Noções básicas de "arrays"
     Definir e usar "arrays"
     Iterando sobre "arrays"
     "Arrays" multidimensionais

### 📖 7. Tratamento de erros
     Blocos try-catch
     Lançando exceções
     Noções básicas sobre exceções comuns (por exemplo, NullPointerException, ArrayIndexOutOfBoundsException)

### 📖 8. "Input" e "Output" básicos
     Utilização da classe Scanner para "input"
     "Output" básico com System.out.println
     Formatando "output"

### 📖 9. Trabalhar com "Strings"
     Manipulação de "strings" (charAt, substring, indexOf, etc.)
     Comparação de "strings" e imutabilidade
     Noções básicas de StringBuilder e StringBuffer

### 📖 10. Introdução aos pacotes Java e API
     Noções básicas sobre pacotes e importações
     Bibliotecas Java comuns (java.util, java.io, etc.)
     Utilizando a documentação da API Java

### 📖 11. Manuseamento básico de ficheiros (opcional para principiantes)
     Leitura e gravação em ficheiros
     Utilizando classes como File, FileReader e FileWriter

### 📖 12. Introdução ao "debug" e às melhores práticas de "debug"
     Técnicas de "debug" num IDE
     Escrever código limpo e legível
     Comentários e documentação do código

### 📖 13. Introdução básica aos conceitos avançados
     Introdução à herança
     Noções básicas de polimorfismo
     Membros estáticos
     Interfaces e classes abstratas, classes, enums e record (visão geral)

### 📖 14. Breve resumo das "Collections"
     Introdução às interfaces "List", "Map" e "Set"
     Uso básico das implementações das interfaces "List", "Map" e "Set"

### 📖 15. Breve introdução às programação/execução assíncrona
     O que é
     Benefícios
     Diferença entre programação/execução síncrona e assíncrona
     Threads
     Executor e ExecutorService
     Future e Callable
     CompletableFuture e tarefas assíncronas

---
---
## 🔧 Projeto prático
    Sistema bancário simples





___
___
___

### 📖 **Introdução à Programação e Java**
#### O que é a programação?

---
---
#### Visão geral do Java e das suas características (independência de plataforma (WORA), POO, etc.)

Java é uma linguagem de programação de alto nível, orientada a objetos e multiplataforma.  
Foi criada pela Sun Microsystems em 1995 e atualmente pertence à Oracle.  
A linguagem é usada para desenvolver aplicações empresariais, web, móveis (Android) e de outros tipos.

##### Lema principal

> "Escreve uma vez, corre em qualquer lado" (*Write Once, Run Anywhere* - WORA)

Graças à **JVM (Java Virtual Machine)**, o código Java pode ser executado em diferentes sistemas operativos sem necessidade de recompilação.  
Para que isto funcione, cada sistema operativo deve ter uma versão nativa da JVM instalada.

---

##### Funcionalidades

- O código é compilado para *bytecode*, que pode ser executado em qualquer JVM instalada;
- Segue os princípios da programação orientada a objetos: encapsulamento, herança, polimorfismo e abstração;
- As variáveis devem ser declaradas com um tipo específico e não podem ser alteradas em tempo de execução;
- Gestão automática de memória através do *Garbage Collector* (GC);
- Conjunto vasto de bibliotecas e *frameworks* como Swing, JavaFX, coleções, IO, etc.;
- Suporte para *multithreading* e concorrência;
- Tratamento de exceções e ausência de apontadores (como em C/C++), evitando erros comuns;
- Grande apoio da comunidade;
- E muito mais.

---

##### Conceitos

###### Classes e Objetos

```java
class Plane {
   String strMaker;
   String strModel;
   int nSeats;

   Plane(String strMaker, String strModel, int nSeats) {
     this.strMaker = strMaker;
     this.strModel = strModel;
     this.nSeats = nSeats;
   }

   void takeOff() {
      System.out.println(
          "The " + this.strMaker + "/" + this.strModel + 
          " plane, with " + this.nSeats + 
          " seats, it's taking off.");
   }
}

public class Test {
   public static void main(String[] args) {
     // criar aviões
     Plane planeAirbus350 = new Plane();
     planeAirbus350.strMaker = "Airbus";
     planeAirbus350.strModel = "350";
     planeAirbus350.nSeats = 375;

     Plane planeBoeing787 = new Plane("Boeing", "787", 290);

     planeAirbus350.takeOff();
     planeBoeing787.takeOff();
   }
}
```

---

###### Pilares da Programação Orientada a Objetos (POO)

- **Encapsulamento**: usando `private`, `protected`, `public`;
- **Herança**: com a palavra-chave `extends`;
- **Polimorfismo**: por sobrecarga e sobrescrita de métodos;
- **Abstração**: usando classes abstratas e interfaces.

---

###### Exceções

```java
try {
  int division = 10 / 0;
} catch (ArithmeticException e) {
  System.out.println("Exceção: 10 dividido por 0!");
}
```

---

###### Interfaces e Classes Abstratas

- Permitem definir contratos e funcionalidades partilhadas entre classes.

---

###### Coleções (***Collections Framework***)

Estruturas como ***`List`, `Set`, `Map`***, etc.

```java
List<String> planeMakers = new ArrayList<>();
planeMakers.add("Airbus");
planeMakers.add("Boeing");
planeMakers.add("Embraer");
System.out.println(planeMakers);
```

---

##### Ambiente de Execução Java

- **JVM (Java Virtual Machine)**: interpreta o bytecode e executa-o no sistema operativo;
- **JRE (Java Runtime Environment)**: contém a JVM e bibliotecas essenciais;
- **JDK (Java Development Kit)**: inclui o JRE e ferramentas como o compilador `javac`.

---

##### Vantagens e Desvantagens

##### Vantagens

- Portabilidade entre sistemas;
- Apoio forte da comunidade;
- Ferramentas de desenvolvimento maduras;
- Código robusto e seguro.

###### Desvantagens

- Desempenho inferior comparado com linguagens compiladas como C/C++;
- Sintaxe mais verbosa (em relação a, por exemplo, Python);
- A JVM consome mais memória do que programas nativos.

---
---

#### Configurar o ambiente de desenvolvimento Java (JDK e IDEs)

##### ✅ Verificar a versão do Java

No terminal ou na linha de comandos, escreve o seguinte comando e pressiona *Enter*:

```bash
java -version
```

---

##### 📦 Instalar o Java

###### JDK 21 (última versão LTS - Suporte a Longo Prazo) está disponível em:

- [Amazon Corretto](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)  
- [Oracle OpenJDK](https://jdk.java.net/java-se-ri/21)  
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java21)  

###### JDK 24 (versão mais recente) está disponível em:

- [Amazon Corretto](https://docs.aws.amazon.com/corretto/latest/corretto-24-ug/downloads-list.html)  
- [Oracle OpenJDK](https://jdk.java.net/java-se-ri/24)  
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java24)  

🔗 Mais fontes: [https://javaalmanac.io/](https://javaalmanac.io/)

---

##### 💻 Instalar um Ambiente de Desenvolvimento Integrado (IDE)

###### NetBeans
- [Transferir](https://netbeans.apache.org/front/main/download/)

###### **Vantagens:**
- IDE oficialmente suportada pela Oracle (historicamente).
- Bom suporte para Java SE, Java EE e JavaFX, pronto a usar.
- Suporte incorporado para Maven e Ant.
- Construtor gráfico (Matisse) facilita o design visual de interfaces.
- Mais leve do que algumas outras IDEs.

###### **Desvantagens:**
- Desenvolvimento de novas funcionalidades mais lento em comparação com IntelliJ ou VS Code.
- Interface pode parecer desatualizada.
- Menos extensível e personalizável.
- O desempenho pode degradar-se em projetos grandes.

---

###### IntelliJ IDEA
- [Transferir](https://www.jetbrains.com/idea/download/)

###### **Vantagens:**
- Excelente autocompletar, navegação e ferramentas de refatoração.
- Sugestões inteligentes e contextuais de código.
- Forte integração com ferramentas de build (Maven, Gradle), controlo de versões, Docker, bases de dados, etc.
- A edição Ultimate oferece suporte abrangente a aplicações empresariais, Spring e desenvolvimento web.

###### **Desvantagens:**
- A edição Ultimate é paga e relativamente cara.
- Pode ser exigente em termos de recursos do sistema.
- Curva de aprendizagem mais acentuada para iniciantes.

---

###### Visual Studio Code
- [Transferir](https://code.visualstudio.com/download)

###### **Vantagens:**
- Leve e muito rápido a arrancar.
- Altamente personalizável com extensões (suporte a Java com extensões como "Extension Pack for Java").
- Excelente para projetos com várias linguagens (ex: Java + JavaScript, Java + Python).
- Integração excelente com Git e boas ferramentas de depuração.

###### **Desvantagens:**
- Não é uma IDE completa por defeito — requer extensões para funcionalidades essenciais de Java.
- Refatoração e navegação menos poderosas comparadas com IntelliJ ou Eclipse.
- Gerir projetos Java empresariais pode ser complicado.

---

###### Eclipse
- [Transferir](https://www.eclipse.org/downloads/packages/)

###### **Vantagens:**
- IDE madura e amplamente utilizada em ambientes empresariais.
- Muito extensível através de plugins.
- Suporte sólido para Java EE e outras linguagens (via plugins).
- Boa integração com ferramentas de build e controlo de versões.

###### **Desvantagens:**
- Interface gráfica e experiência de utilização frequentemente consideradas desatualizadas.
- Ecossistema de plugins pode ser confuso e inconsistente.
- Utiliza bastante memória e tem tempos de arranque lentos.
- Pode apresentar instabilidades ou lentidão em projetos grandes.

---
---

#### Escrever, compilar e executar um programa Java (o típico HelloWorld)

#### Sintaxe básica e estrutura de um programa Java

     
     
     
