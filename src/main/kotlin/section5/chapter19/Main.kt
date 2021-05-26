package section5.chapter19

import section5.chapter19.core.GenericGraphSearch
import section5.chapter19.representations.WeightedAdjacencyList

fun main() {

    // val graph = WeightedAdjacencyList<String>()
    val graph = WeightedAdjacencyList<String>()

    val singapore = graph.createVertex("Singapore")
    val tokyo = graph.createVertex("Tokyo")
    val hongKong = graph.createVertex("Hongkong")
    val detroit = graph.createVertex("Detroit")
    val sanFrancisco = graph.createVertex("San Francisco")
    val washingtonDC = graph.createVertex("Washington DC")
    val austinTexas = graph.createVertex("Austin Texas")
    val seattle = graph.createVertex("Seattle")

    graph.addUndirectedEdge(singapore, hongKong, 300.0)
    graph.addUndirectedEdge(singapore, tokyo, 500.0)
    graph.addUndirectedEdge(hongKong, tokyo, 250.0)
    graph.addUndirectedEdge(tokyo, detroit, 450.0)
    graph.addUndirectedEdge(tokyo, washingtonDC, 300.0)
    graph.addUndirectedEdge(hongKong, sanFrancisco, 600.0)
    graph.addUndirectedEdge(detroit, austinTexas, 50.0)
    graph.addUndirectedEdge(austinTexas, washingtonDC, 292.0)
    graph.addUndirectedEdge(sanFrancisco, washingtonDC, 337.0)
    graph.addUndirectedEdge(washingtonDC, seattle, 277.0)
    graph.addUndirectedEdge(sanFrancisco, seattle, 218.0)
    graph.addUndirectedEdge(austinTexas, sanFrancisco, 297.0)

    println(graph)
    println(graph.getWeight(singapore, tokyo))
    println()

    println("San Francisco Outgoing Flights")
    println("------------------------------")
    graph.getVertexEdges(sanFrancisco).forEach {
        println("from: ${it.source.data} to: ${it.destination.data}")
    }

    val paths = GenericGraphSearch.pathCount(singapore, tokyo) { v -> graph.getVertexEdges(v).map { it.destination } }
    print(paths)

}
