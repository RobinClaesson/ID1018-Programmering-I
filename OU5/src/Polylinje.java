public class Polylinje {

    private Punkt[] horn;
    private String farg = "svart";
    private int bredd = 1;

    public Polylinje() {
        this.horn = new Punkt[0];
    }

    public Polylinje(Punkt[] horn) {
        this.horn = new Punkt[horn.length];
        for (int i = 0; i < horn.length; i++)
            this.horn[i] = new Punkt(horn[i]);
    }

    public Polylinje(Polylinje polylinje)
    {
        this.horn = new Punkt[polylinje.horn.length];
        for(int i = 0; i < horn.length; i++)
            this.horn[i] = polylinje.horn[i];

        this.bredd = polylinje.bredd;
        this.farg = polylinje.farg;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("{[");

        for (Punkt p : horn)
            s.append(p.toString());

        s.append("], ");
        s.append(farg);
        s.append(", ");
        s.append(bredd);
        s.append("}");

        return s.toString();
    }

    public Punkt[] getHorn() {
        Punkt[] copy = new Punkt[horn.length];

        for (int i = 0; i < horn.length; i++)
            copy[i] = new Punkt(horn[i]);

        return copy;
    }

    public String getFarg() {
        return farg;
    }

    public int getBredd() {
        return bredd;
    }

    public void setFarg(String farg) {
        this.farg = farg;
    }

    public void setBredd(int bredd) {
        this.bredd = bredd;
    }

    public double langd() {
        double langd = 0;

        for (int i = 0; i < horn.length - 1; i++) {
            langd += horn[i].avstand(horn[i + 1]);
        }

        return langd;
    }

    public int antalHorn() {
        return horn.length;
    }

    public void laggTill(Punkt horn) {
        Punkt[] h = new Punkt[this.horn.length + 1];
        int i = 0;
        for (i = 0; i < this.horn.length; i++)
            h[i] = this.horn[i];
        h[i] = new Punkt(horn);
        this.horn = h;
    }

    public void laggTillFramfor(Punkt horn, String hornNamn) {

        //Skapar en array med rätt längd
        Punkt[] h = new Punkt[this.horn.length + 1];

        int i = 0;

        //Kopierar fram tills vi hittar den som vi ska lägga till framför eller tills vi stegat igenom hela listan
        while (!this.horn[i].getNamn().equals(hornNamn) && i < this.horn.length) {
            h[i] = this.horn[i];
            i++;
        }

        //Om vi inte gått igenom hela listan betyder det att vi har hittat ett hörn med rätt namn
        if (i < this.horn.length) {
            //Lägger till den nya
            h[i] = new Punkt(horn);


            //Kopierar resten av hörnen
            while (i < this.horn.length) {
                //Läggger till hörnen på i+1 eftersom vi har lagt till ett extra
                h[i + 1] = this.horn[i];
                i++;
            }
        }

        this.horn = h;
    }

    public void taBort(String hornNamn) {

        //Skapar ny array med rätt längd
        Punkt[] h = new Punkt[horn.length - 1];

        int i = 0;

        //Kopierar fram tills vi hittar den vi ska ta bort eller tills vi stegat igenom hela listan
        while (!horn[i].getNamn().equals(hornNamn) && i < horn.length) {
            //Kopierar referensen till nya arrayen
            h[i] = horn[i];
            i++;
        }

        //Om vi inte gått igenom hela listan betyder det att vi har hittat ett hörn med rätt namn
        if (i < horn.length) {
            //Hoppar över den vi inte vill ha
            i++;

            while (i < horn.length) {
                //Lägger till resten av hörnen på i-1 eftersom vi har gått en längre på gamla listan
                h[i - 1] = horn[i];
                i++;
            }
        }

        horn = h;
    }


    public class PolylinjeIterator {

        private int aktuell = -1;

        public PolylinjeIterator()
        {
            if(Polylinje.this.horn.length > 0)
                aktuell = 0;
        }

        public  boolean finnsHorn()
        {
            return  aktuell != -1;
        }

        public Punkt horn() throws java.util.NoSuchElementException
        {
            if(!finnsHorn())
                throw new java.util.NoSuchElementException("Slut på iterationen");

            return Polylinje.this.horn[aktuell];
        }

        public void gaFram()
        {
            //Stegar fram om det finns mer horn
            if(finnsHorn() && aktuell < Polylinje.this.horn.length -1)
                aktuell++;

            else
                aktuell = -1;
        }


    }
}
