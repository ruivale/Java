/**
 * Java intro.
 * 
 * Classname: pt.intro.java.afirst.Intro
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
 * Description: 
 * Java é uma linguagem de programação de alto nível, orientada a objectos e multiplataforma.
 * Foi criada pela Sun Microsystems em 1995 mas actualmente pertence à Oracle. 
 * A linguagem é utilizada para desenvolvimento de aplicações empresariais, web, móveis (Android), 
 * entre outros.
 * 
 * Um dos principais lemas do Java é:
 *    - "Write Once, Run Anywhere" (WORA) – escreve uma vez, corre em qualquer lugar, graças à JVM
 *    Java Virtual Machine, que permite executar código Java em diferentes sistemas operativos sem 
 *    necessidade de recompilação. Para o efeito, basta que cada sistema operativo onde se pretende
 *    executar código Java tenha instalada a versão nativa da JVM.
 * 
 * Características:
 *    - o código é compilado para "bytecode" que pode ser executado por qualquer JVM instalada;
 *    - segue os princípios de programação orientada a objectos, como encapsulamento, herança, polimorfismo e abstracção;
 *    - as variáveis devem ser declaradas com um tipo específico que não pode ser alterado durante a execução;
 *    - gestão automática de memória ("GC" ou "Garbage Collector") que trata da limpeza de objectos não utilizados;
 *    - vasta lista de bibliotecas e "frameworks" com várias finalidades tais como Swing/JavaFX, colecções, IO, etc;
 *    - multitarefas e concorrência permitindo a execução simultânea de múltiplas tarefas acedendo a objectos partilhados;
 *    - gestão de excepções a ausência de apontadores, como em C/C++, ajudam a evitar erros comuns;
 *    - vasta comunidade de suporte;
 *    - etc.
 * 
 * Conceitos:
 * 
 *  Classes e objectos;
 *      class Plane {
 *         String strMaker;
 *         String strModel;
 *         int nSeats;
 *      
 *         Plane(String strMaker, String strModel, int nSeats) {
 *           this.strMaker = strMaker;
 *           this.strModel = strModel;
 *           this.nSeats = nSeats;
 *         }
 *      
 *         void takeOff() {
 *            System.out.println("The " + this.strMaker + "/" + this.strModel + ", with " +this.nPlaces+ " seats, is taking off...");
 *         }
 *      }
 *      
 *      public class Test {
 *         public static void main(String[] args) {
 *           // creating planes
 *           // creating "airbus/350"...
 *           Plane planeAirbus350 = new Plane();
 *           planeAirbus350.strMaker = "Airbus";
 *           planeAirbus350.strModel = "350";
 *           planeAirbus350.nSeats = 375;
 *      
 *           // creating "boeing/787"...
 *           Plane planeBoeing787 = new Plane("Boeing", "787", 290);
 *      
 *           // invoking "takeOff" method...
 *           planeAirbus350.takeOff();
 *           planeBoeing787.takeOff();
 *         }
 *      }
 *      
 *  Pilares POO ("Plain Old Object") em Java (aka POJO ("Plain Old Java Object")):
 *      - encapsulamento, i.e., uso de modificadores de acesso como private, protected, public;
 *      - herança, i.e., pelo uso da palavra chave "extends";
 *      - polimorfismo, i.e., pela sobrecarga ("overload") e sobrescrita ("override");
 *      - abstracção, i.e., pelo uso de classes abstractas e interfaces.
 *      
 *  Excepções:
 *      try {
 *        int division = 10 / 0;
 *      } catch(ArithmeticException e) {
 *        System.out.println("Exception: 10 divided by 0!");
 *      }
 * 
 *  Interfaces a classe abstractas:
 *      - permitem estabelecer contratos e funcionalidades comuns;
 * 
 *  Coleções ("Collections Framework"):
 *      - fornece estruturas de dados como List, Set, Map, etc como p.e.:
 *        List<String> listaPlaneMakers = new ArrayList<>();
 *        listaPlaneMakers.add("Airbus");
 *        listaPlaneMakers.add("Boeing");
 *        listaPlaneMakers.add("Embraer");
 *        System.out.println(listaPlaneMakers);
 * 
 * Ambiente de exceução Java (JRE, JDK, JVM):
 *    - JVM ("Java Virtual Machine"), interpreta o "bytecode" e executa-o no sistema operativo.
 *    - JRE ("Java Runtime Environment"), é conposto pela JVM e bibliotecas  essenciais que permitem executar aplicações Java.
 *    - JDK ("Java Development Kit"), é composto pelo JRE e de ferramentas de desenvolvimento como p.e. o compilador "javac", etc.
 * 
 * Vantagens e desvantagens do Java:
 *  Vantagens:
 *    - portabilidade;
 *    - grande suporte da comunidade;
 *    - ferramentas de desenvolvimento maduras;
 *    - código robusto e seguro;
 *  Desvantagens:
 *    - performance inferior a linguagens nativamente compiladas, como p.e. o C/C++;
 *    - sintaxe verbosa em comparação com, p.e., Python;
 *    - a JVM consume mais memória do que programas nativos;
 * 
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class Intro {

 /**
  * The Intro default constructor.
  */
  public Intro(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("Intro").append("").toString();
  }

}
