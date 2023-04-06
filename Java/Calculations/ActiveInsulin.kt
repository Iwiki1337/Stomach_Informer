package Calculations

import java.lang.Exception
import java.time.LocalDateTime
import java.time.Duration

import java.time.format.DateTimeFormatter

class ActiveInsulin {
    private class Injection(dose : Float, time : LocalDateTime) {
        public var Dose : Float = dose;
        public var Time : LocalDateTime = time;
    }
    private var injections : MutableList<Injection> = mutableListOf<Injection>();

    public fun AddInjection(dose : Float, time : LocalDateTime){
        if(!injections.add(Injection(dose, time)))
            throw Exception("Can't add an ingection to the list of injections");
    }

    //todo: write a function for active insulin concentration, now it's linear function

    //Calculates insulin concentration at givven time
    public fun CalculateInsulin(time : LocalDateTime) : Float{
        var insulin : Float = 0.0f;
        val k : Float = 0.0001f;
        for (injection in injections){
            var delta : Long = java.time.Duration.between(injection.Time, time).getSeconds();
            if(delta < 0) continue;
            var localInsulin : Float = injection.Dose - k * delta;
            insulin += if(localInsulin > 0.0f) localInsulin else 0.0f;
        }
        return insulin;
    }
}
