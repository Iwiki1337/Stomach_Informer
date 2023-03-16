import kotlin.collections

package Calculations


class Graph {
    public var Points : MutableList<Float> = mutableListOf<Float>();

    constructor(var data: List<Float>){
        for(value in data) {
            if(!Points.add(value)) throw Exception("Can't add point $value to the graph!");
        }
    }

    public fun CalculateTrendLine(var depth : Uint) -> Line{
        var s0 : Float = depth;
        var s1 : Float = 0;
        var s2 : Float = 0;
        var t0 : Float = 0;
        var t1 : Float = 0;

        for (i in (Points.size - depth) until Points.size){
            s1 += i;
            s2 += i * i;
            t0 += Points[i];
            t1 += i * Points[i];
        }

        var k : Float = 0;
        var b : Float = 0;

        k = (t1 * s0 - t0 * s1) / (s2 * s0 - s1 * s1);
        b = (t0 - s1 * k) / so;
        return Line(k, b);
    }
}