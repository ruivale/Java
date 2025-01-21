/**
 * Java intro.
 * 
 * Classname: pt.intro.java.afirst.HelloWorld
 * Copyright (C) 2024 RGV
 * Email: ruivale at gmail dot com
 *
 * This is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */
package pt.intro.java.afirst;

/**
 * <p>
 * Description: a simple Java class.
 *
 * Escrever, compilar e executar um programa Java
 *      Esta classe vai servir para uma primeira compilação e execução de um programa Java. O ficheiro
 *      tem a extensão ".java" e contém o método "main".
 *      
 *      A sua compilação consiste em executar o comando "javac", disponível na pasta "bin" da instalação
 *      do JDK, indicando a classe pt.intro.java.afirst.HelloWorld como a classe a compilar. Este comando 
 *      traduzirá o ".java" para um ".class" que contém o "bytecode" da classe. Exemplo:
 *       $javac src\main\java\pt\intro\java\afirst\HelloWorld.java
 *      
 *      A execução é em tudo semelhante à compilação mas o comando a usar é o "java" indicando a este que
 *      a classe inicial a executar é a pt.intro.java.afirst.HelloWorld. Exemplo:
 *       $java -cp src\main\java\ pt.intro.java.afirst.HelloWorld
 *      ou
 *       $ cd src\main\java\ 
 *       $java pt.intro.java.afirst.HelloWorld
 *
 * 
 * Sintaxe básica e estrutura de um programa Java
 *      O nosso programa Java básico HelloWorld segue a seguinte estrutura:
 *        - declaração de pacote (opcional): 
 *            package pt.intro.java.afirst;
 *        - importação de classes (opcional): 
 *            import nomeDoPacote.NomeDaClasse;
 *        - definição de classe: 
 *            public class HelloWorld{ ... }
 *        - método principal (ponto de entrada do programa): 
 *            public static void main(String[] args) { ... }
 *        - instruções: 
 *            declaração de variáveis, chamadas de métodos, estruturas de controlo, etc.
 * 
 *</p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class HelloWorld {
  public static void main(final String[] args){
    System.out.println("Hello world!");
  }
}
