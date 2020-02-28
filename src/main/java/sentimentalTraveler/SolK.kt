package sentimentalTraveler
import kotlin.math.max

lateinit var paths: Array<List<Int>?>
fun solution(arr: IntArray): Int {

    val connections = Array(arr.size){mutableSetOf<Int>()}
    for (i in arr.indices){
        if(i != arr[i]){
            connections[arr[i]].add(i)
            connections[i].add(arr[i])
        }
    }

    paths = arrayOfNulls(arr.size-1)

    println(kotlin.system.measureTimeMillis {
        for (node in paths.indices){
            //paths.forEach { println(it) }
            if(!connections[node].contains(node+1) && paths[node] == null)
                explorePath(node, mutableListOf(), Array(connections.size){connections[it].toMutableSet()})
        }
    })

    var count = arr.size
    var location = 0
    var visited = 1

    println(kotlin.system.measureTimeMillis{
        for(from in 0..paths.lastIndex){
            var till = from+1
            tills@while(till <= arr.lastIndex){
                steps@while (location < till){
                    if(!paths[location].isNullOrEmpty()){
                        val path = paths[location]
                        if(from > 0 && path!!.any { from > it }){
                            break@tills
                        }
                        if(path!!.any{it > till}){
                            till = max(till, path.max()!! - 1)
                            break@steps
                        }
                    }
                    visited++
                    location++
                }
                if(visited == till - from + 1) {
                    count++
                }
                till++
            }
            location = from+1
            visited = 1
        }
    })

    return count
}

fun explorePath(current: Int, steps: MutableList<Int>, connections: Array<MutableSet<Int>>){
    if(current > 0 && paths[current-1] == null && steps.contains(current-1)){
        paths[current-1] = steps.takeLastWhile { it != current-1 }
    }
    if(current < paths.size && paths[current] == null && steps.contains(current+1)){
        paths[current] = steps.takeLastWhile { it != current+1 }
    }
    //println("Current: $current, steps: $steps")
    steps.add(current)
    connections[current].forEach {
        connections[it].remove(current)
        explorePath(it, steps, connections)
    }
    steps.remove(current)
}