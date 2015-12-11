package com.example.ricardo.my_final_proyect.Player;

public class Player {
    private String name;
    private String matricula;
    private String status;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Player(String matricula, String name, String status) {
        this.matricula = matricula;
        this.name = name;
        this.status = status;
    }



}
