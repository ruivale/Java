package exp.threads.starvingphilosophers;

//{{{  Starving_Philosophers.java
//{{{  class Canteen {
class Canteen {
  //{{{

  //{{{  COMMENT documentation
  //
  //Philosphers eat chickens.  They queue up at the canteen to use the `get'
  //method.  Inside the `get' method, they get served straight away so long
  //as there are some chickens ready.  Otherwise they `wait' on stand-by.
  //
  //The chef cooks chickens.  When he has a batch ready, he also queues up
  //at the canteen in order to use the `put' method.  This involves setting
  //down the batch -- which takes around 3 seconds -- and clearing the stand-by
  //queue (of philosophers waiting for chickens) by calling `notifyAll'.
  //
  //{{{  note 1
  //
  //Clearly, the Chef should not have to queue up with the Philosophers to
  //get into the Canteen.  The Philosophers should have their own queue and
  //the Chef should only have to contend with one Philospher when dealing with
  //the chickens in the canteen.  Such a separate queue for the Philosophers is
  //just like the seperate queues for readers/writers that we needed for the
  //securely shared Channel or Buffer class.  However, we are modelling this
  //Canteen on the CubbyHole class (to show the danger of starvation it contains)
  //and CubbyHole has its queue shared by both readers and writers.
  //
  //}}}
  //
  //{{{  note 2
  //
  //All methods, apart from the `run' method, are executed as part of the thread
  //of control of their calling objects.  This is particularly clear here, where
  //we see them speaking *as* those calling objects.  For instance, the `get'
  //method is part of the life of the calling Philosopher and `put' is part of
  //the life of the Chef.  So, this Canteen "object" has bits of Philosopher-
  //algorithm and bits of Chef-algorithm for its methods -- a somewhat confused
  //set of roles for something that's supposed to be a Canteen.  There's nothing
  //in these methods that are oriented towards the life of the Canteen.
  //
  //So, alongside the semantic imprecision of `wait/notify' and alongside the
  //non-compositional (or, if you prefer, non-referentially-transparent) semantics
  //of methods in general, we have to come to terms with the idea that no methods
  //(apart from `run') are oriented towards the "objects" of which they are
  //supposed to be a part.  So much for "object-orientation" in the formal, and
  //quite arbitrary, definition of the term!  I believe that the first two
  //problems referenced in this paragraph are a direct consequence of the third.
  //These problems are not special to this Canteen, but are universal across
  //all passive objects.
  //
  //The Canteen should, of course, be implemented as an active object connected
  //to the Philosophers and the Chef via simple channels or buffers.  Then, we
  //wouldn't have the problems arising from this design.  This is left as an
  //exercise!
  //
  //}}}
  //
  //}}}
  private int n_chickens = 0;

  //{{{  synchronized get (no chickens ==> wait)
  public synchronized int get(int id) {
    //{{{
    while (n_chickens == 0) {
      //{{{  moan
      System.out.println("(" + System.currentTimeMillis() + ") "
        + "Phil " + id + ": wot, no chickens?  I'll WAIT ... ");
      //}}}
      //{{{  get on stand-by queue, letting in the next philosopher (or chef)
      try {
        wait();
      } catch (InterruptedException e) {
      }
      //}}}
    }
    //{{{  thanks
    System.out.println("(" + System.currentTimeMillis() + ") "
      + "Phil " + id
      + ": those chickens look good ... one please ...");
    //}}}
    n_chickens--;
    return 1;
    //}}}
  }

  //}}}
  //{{{  synchronized put (takes at least 3 seconds)
  public synchronized void put(int value) {
    //{{{
    //{{{  bring in the tray
    System.out.println("(" + System.currentTimeMillis() + ") "
      + "Chef  : ouch ... make room ... this dish is very hot ... ");
    //}}}
    //{{{  take 3 seconds to set down the dish (meanwhile a queue may be developing)
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
    }
    //}}}
    n_chickens += value;
    //{{{  announce arrival of more chickens
    System.out.println("(" + System.currentTimeMillis() + ") "
      + "Chef  : more chickens ... "
      + n_chickens + " now available ... NOTIFYING ...");
    //}}}
    //{{{  put any waiting philosophers back on the main queue
    notifyAll();
    //}}}
    //}}}
  }

  //}}}
  //}}}
}

//}}}
//{{{  class Chef extends Thread {
class Chef
  extends Thread {
  //{{{

  //{{{  COMMENT documentation
  //
  //A chef is an active object.  He cooks chickens in batches of four -- taking
  //around 2 seconds per batch -- and then takes then to the canteen.
  //
  //This cycle continues indefinitely.
  //
  //}}}
  private Canteen canteen;

  //{{{  constructor
  public Chef(Canteen canteen) {
    this.canteen = canteen;
    start();
  }

  //}}}
  //{{{  run
  public void run() {
    //{{{
    int n_chickens;

    //{{{  starting
    System.out.println("(" + System.currentTimeMillis() + ")"
      + " Chef  : starting ... ");
    //}}}
    while (true) {
      //{{{  cook 4 chickens
      System.out.println("(" + System.currentTimeMillis() + ")"
        + " Chef  : cooking ... ");
      //{{{  cook for around 2 seconds
      try {
        sleep(2000);
      } catch (InterruptedException e) {
      }
      //}}}
      n_chickens = 4;
      System.out.println("(" + System.currentTimeMillis() + ")"
        + " Chef  : " + n_chickens
        + " chickens, ready-to-go ... ");
      //}}}
      canteen.put(n_chickens);
    }
    //}}}
  }

  //}}}
  //}}}
}

//}}}
//{{{  class Phil extends Thread {
class Phil
  extends Thread {
  //{{{

  //{{{  COMMENT documentation
  //
  //A philosopher thinks for a while -- around 3 seconds -- and then goes to the
  //canteen for food, consuming what he gets straight away.   This cycle continues
  //indefinitely.
  //
  //Except, that is, for philosopher 0 ...  who refuses to think and just keeps
  //going to the canteen.  He will get his come-uppance shortly.
  //
  //}}}
  private int id;
  private Canteen canteen;

  //{{{  constructor
  public Phil(int id, Canteen canteen) {
    this.id = id;
    this.canteen = canteen;
    start();
  }

  //}}}
  //{{{  run
  public void run() {
    //{{{
    int chicken;

    //{{{  starting
    System.out.println("(" + System.currentTimeMillis() + ")"
      + " Phil " + id + ": starting ... ");
    //}}}
    while (true) {
      //{{{  everyone, bar philosopher 0, has a little think
      if (id > 0) {
        //{{{  think for around 3 seconds
        try {
          sleep(3000);
        } catch (InterruptedException e) {
        }
        //}}}
      }
      //}}}
      //{{{  want chicken
      System.out.println("(" + System.currentTimeMillis() + ")"
        + " Phil " + id + ": gotta eat ... ");
      //}}}
      chicken = canteen.get(id);
      //{{{  consume chicken
      System.out.println("(" + System.currentTimeMillis() + ")"
        + " Phil " + id + ": mmm ... that's good ... ");
      //}}}
    }
    //}}}
  }

  //}}}
  //}}}
}

//}}}
//{{{  class College {
class College {
  //{{{

  //{{{  COMMENT documentation
  //
  //The College consists of 5 Philosophers, a Chef and the Canteen.  The Chef and
  //the Philosophers are "active" objects.  The Canteen is a "passive" object
  //through which the Philosophers and the Chef have to interact.
  //
  //The Canteen is implemented in the style of the CubbyHole example from Sun's
  //Java Tutorial.  As such, it exposes each Philosopher to the danger of
  //starvation (by infinite overtaking by fellow Philosophers).  Sadly, this
  //does happen with the particular timing circumstances set up in this
  //demonstration.  Happily, it's the greedy Philosopher that starves -- he
  //never even gets one meal, despite being in the Canteen the whole time!
  //
  //}}}
  //{{{  main
  public static void main(String argv[]) {
    //{{{

    int n_philosophers = 5;

    Canteen canteen = new Canteen();

    Chef chef = new Chef(canteen);

    Phil[] phil = new Phil[n_philosophers];

    for (int i = 0; i < n_philosophers; i++) {
      phil[i] = new Phil(i, canteen);
    }

    //}}}
  }

  //}}}
  //}}}
}

//}}}

//}}}
