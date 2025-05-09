
### 📖 1. Introdução à Programação e Java
1. [O que é a programação?](#o-que-é-a-programação)
2. [Visão geral do Java e das suas características (independência de plataforma (WORA), POO, etc.)](#visão-geral-do-java-e-das-suas-características-independência-de-plataforma-wora-poo-etc)
3. [Configurar o ambiente de desenvolvimento Java (JDK, IDEs como IntelliJ, Eclipse ou NetBeans)](#configurar-o-ambiente-de-desenvolvimento-java-jdk-e-ides)
4. [Escrever, compilar e executar um programa Java (o típico HelloWorld)](#escrever-compilar-e-executar-um-programa-java-o-típico-helloworld)
5. [Sintaxe básica e estrutura de um programa Java](#sintaxe-básica-e-estrutura-de-um-programa-java)


---
---
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
     // create planes...
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
Vamos criar a classe *HelloWorld*, pertencente ao *package pt.intro.java.afirst*. Esta classe servirá como um primeiro exemplo de compilação e execução de um programa Java.  
A primeira criação, compilação e execução será manual, i.e., sem uso de IDEs e para isso vamos criar uma estrutura de pastas que nos permita gerir mais facilmente todo este processo.  

Vamos criar uma pasta com o nome *HelloWorld*:
```sh 
$mkdir HelloWorld
$cd HelloWorld
```

Vamos criar as pastas *src/pt/intro/java/afirst* e outra com o nome *classes*:
```sh 
$mkdir -p src/pt/intro/java/afirst
$mkdir classes
```


Vamos de seguida criar o ficheiro *HelloWorld.java*, na pasta *src/pt/intro/java/afirst*. Este ficheiro, assim como qualquer ficheiro *java*, deve ter a extensão *.java*. Neste caso, como a classe deverá ser usada como classe executável, contém o método *main*:
```java
package pt.intro.java.afirst;

public class HelloWorld {
  public static void main(final String[] args){
    System.out.println("Hello world!");
  }
}
```

A compilação consiste em executar o comando "javac", disponível na pasta "bin" da instalação do JDK, especificando a classe *pt.intro.java.afirst.HelloWorld* como aquela a compilar. Este comando irá traduzir o ficheiro *.java* para um ficheiro *.class* que contém o *bytecode* da classe.  
Exemplo: 
```sh 
 $javac -d classes src\pt\intro\java\afirst\HelloWorld.java  
```

A execução é muito semelhante à compilação, mas o comando a utilizar é o *java*, especificando que a classe inicial a executar é a *pt.intro.java.afirst.HelloWorld*.  
Exemplo:  
```sh
 $java -cp classes pt.intro.java.afirst.HelloWorld  
```
ou  
```sh
 $cd classes  
 $java pt.intro.java.afirst.HelloWorld
```

---
---
#### Sintaxe básica e estrutura de um programa Java
O nosso programa Java básico "HelloWorld" segue a seguinte estrutura:
  - declaração de pacote (opcional):
    ```java
    package pt.intro.java.afirst;
    ```
  - importação de classes (opcional e, neste exemplo, inexistente):  
      ```java
      import packageName.ClassName;
      ```
  - definição da classe:  
      ```java
      public class HelloWorld { ... }
      ```
  - método main (ponto de entrada do programa):  
      ```java
      public static void main(String[] args) { ... }
      ```
  - instruções:  
      declarações de variáveis, chamadas de métodos, estruturas de controlo, etc.
     
     
     
