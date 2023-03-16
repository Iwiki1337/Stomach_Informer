package Calculations

class Graph {
    public var Points : MutableList<Float> = mutableListOf<Float>();

    constructor(data: List<Float>){
        for(value in data) {
            if(!Points.add(value)) throw Exception("Can't add point $value to the graph!");
        }
    }

    public fun CalculateTrendLine(depth : Int) : Line{

        if (Points.size < 2) 
            throw Exception("Unable to calculate trend line of graph with less than two points!");
        if ((depth < 2) or (depth > Points.size))
            throw Exception("The depth of trend line calculation must be in range from 2 to number of points in graph!");

        var s0 : Float = depth.toFloat();
        var s1 : Float = 0.0f;
        var s2 : Float = 0.0f;
        var t0 : Float = 0.0f;
        var t1 : Float = 0.0f;

        for (i in (Points.size - depth) until Points.size){
            var t = i.toFloat();
            s1 += t;
            s2 += t * t;
            t0 += Points[i];
            t1 += t * Points[i];
        }

        var k = (t1 * s0 - t0 * s1) / (s2 * s0 - s1 * s1);
        var b = (t0 - s1 * k) / s0;
        return Line(k, b);
    }
}