package com.mycompany.traintrack.client.igu;

public enum Station {
    
    //----------------------Belén-Atlántico
    Belén(42, 298),
    Pedregal(90, 298),
    Metrópolis(133, 298),
    Demasa(166, 298),
    Pecosa(194, 298),
    Pavas_Centro(227, 298),
    Jacks(257, 298),
    AyA(288, 298),
    La_Salle(317, 298),
    Contraloría(350, 298),
    Barrio_Cuba(381, 298),
    Estación_Pacífico(422, 298),
    Plaza_Víquez(463, 298),
    La_Corte(480, 276),
    //----------------------Belén-Atlántico
    
    //Central
    Estación_Atlántico(480, 237),
    //Central
    
    //---------------------Heredia-Atlántico
    Heredia(362, 107),
    Miraflores(388, 135),
    Santa_Rosa(412, 162),
    Colima(436, 189),
    Calle_Blancos(457, 210),
    //---------------------Heredia-Atlántico
    
    
    //---------------------Alajuela-Heredia
    Alajuela(136, 107),
    Bulevar_Aeropuerto(185, 107),
    Río_Segundo(223, 107),
    San_Joaquín(272, 107),
    San_Francisco(320, 107),
    //---------------------Alajuela-Heredia
    
    
    
    //---------------------Atlántico-Paraíso
    UCR(524, 237),
    U_Latina(569, 237),
    Freses(615, 237),
    UACA(660, 237),
    Tres_Ríos(699, 237),
    Cartago(735, 237),
    Los_Ángeles(777, 237),
    Oreamuno(813, 237),
    Paraíso(855, 237);
    //---------------------Atlántico-Paraíso
    

    private final int x;
    private final int y;

    Station(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
