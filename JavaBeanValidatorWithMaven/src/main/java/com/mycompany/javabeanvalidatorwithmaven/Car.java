package com.mycompany.javabeanvalidatorwithmaven;



import jakarta.validation.constraints.*;

public class Car
{
    // ---------------|  Member fields  |----------------------------
    @NotNull
    private String manufacturer;

    @NotNull
    @Size ( min = 2, max = 14 )
    private String licensePlate;

    @Min ( 2 )
    private int seatCount;

    // ---------------|  Constructors  |----------------------------
    public Car ( String manufacturer , String licensePlate , int seatCount )
    {
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
    }

    // ---------------|  Object overrides  |----------------------------

    @Override
    public String toString ( )
    {
        return "Car{ " +
                "manufacturer='" + manufacturer + '\'' +
                " | licensePlate='" + licensePlate + '\'' +
                " | seatCount=" + seatCount +
                " }";
    }
}